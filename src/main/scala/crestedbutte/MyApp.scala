package crestedbutte

import java.util.concurrent.TimeUnit

import crestedbutte.ElementNames.ThreeSeasonsLoop
import crestedbutte.routes.{ThreeSeasonsTimes, TownShuttleTimes}
import crestedbutte.time.BusTime
import org.scalajs.dom
import zio.clock._
import zio.console.Console
import zio.duration.Duration
import zio.{App, Schedule, ZIO}
import org.scalajs.dom._
import org.scalajs.dom.experimental.{
  Notification,
  NotificationOptions
}
import org.scalajs.dom.experimental.serviceworkers._

import scala.collection.mutable
import scala.scalajs.js
import scala.util.{Failure, Success}
// TODO Ew. Try to get this removed after first version of PWA is working
import scala.concurrent.ExecutionContext.Implicits.global

object MyApp extends App {

  override def run(
    args: List[String]
  ): ZIO[zio.ZEnv, Nothing, Int] = {
    val myEnvironment
      : zio.clock.Clock with zio.console.Console with Browser =
      new Clock.Live with Console.Live with BrowserLive

    registerServiceWorker()

    (for {
      pageMode <- getCurrentPageMode
      _ <- DomManipulation.createAndApplyPageStructure(
        pageMode
      ) // TODO Base on queryParam
      _ <- registerServiceWorker()
      _ <- NotificationStuff.addNotificationPermissionRequestToButton
//      _ <- NotificationsStuff.addAlarmBehaviorToTimes
      _ <- NotificationStuff.displayNotificationPermission
      _ <- updateUpcomingArrivalsOnPage
        .provide(
          // TODO Try to provide *only* a clock here.
          if (pageMode == AppMode.Development)
            new LateNightClock.Fixed
            with Console.Live with BrowserLive
          else
            new Clock.Live with Console.Live with BrowserLive
        )
        .flatMap { _ =>
          NotificationStuff.addAlarmBehaviorToTimes
        }
        .flatMap { _ =>
          NotificationStuff.checkSubmittedAlarms
        }
        // Currently, everytime I refresh, kills the modal
        .repeat(Schedule.spaced(Duration.apply(30, TimeUnit.SECONDS)))
    } yield {
      0
    }).provide(myEnvironment)
  }

  def updateUpcomingArrivalsForRoute(
    componentName: String,
    readableRouteName: String,
    schedules: Seq[BusScheduleAtStop]
  ) =
    for {
      upcomingArrivalAtAllTownShuttleStops <- TimeCalculations
        .getUpComingArrivalsWithFullSchedule(
          Route(schedules)
        )
      _ <- DomManipulation.updateUpcomingBusSectionInsideElement(
        componentName,
        TagsOnly.structuredSetOfUpcomingArrivals(
          upcomingArrivalAtAllTownShuttleStops,
          readableRouteName
        )
      )
    } yield ()

  val updateUpcomingArrivalsOnPage
    : ZIO[Browser with Clock with Console, Nothing, Unit] =
    for {
      _ <- updateUpcomingArrivalsForRoute(
        ElementNames.TownShuttles.containerName,
        ElementNames.TownShuttles.readableRouteName,
        TownShuttleTimes.townShuttleStops
      )
      upcomingArrivalAtCondoloopStops <- TimeCalculations
        .getUpComingArrivalsWithFullSchedule(
          Route(ThreeSeasonsTimes.allStops)
        )
      _ <- DomManipulation.updateUpcomingBusSectionInsideElement(
        ElementNames.ThreeSeasonsLoop.containerName,
        TagsOnly.structuredSetOfUpcomingArrivals(
          upcomingArrivalAtCondoloopStops,
          ElementNames.ThreeSeasonsLoop.readableRouteName
        )
      )
    } yield ()

  val getCurrentPageMode =
    ZIO.environment[Browser].map { browser =>
      UrlParsing
        .getUrlParameter(
          browser.browser.window().location.toString,
          "mode" // TODO Ugly string value
        )
        .flatMap(rawString => AppMode.fromString(rawString))
        .getOrElse(AppMode.Production)
    }

  def registerServiceWorker() =
    ZIO
      .environment[Browser]
      .map { browser =>
        toServiceWorkerNavigator(browser.browser.window().navigator).serviceWorker
          .register("./sw-opt.js")
          .toFuture
          .onComplete {
            case Success(registration) =>
              registration.active.postMessage(
                "Submitting a message to the serviceWorker!"
              )
              println(
                "registerServiceWorker: registered service worker in a monad properly accesing the env"
              )
              registration.update()
            case Failure(error) =>
              println(
                s"registerServiceWorker: service worker registration failed > ${error.printStackTrace()}"
              )
          }
      }
}
