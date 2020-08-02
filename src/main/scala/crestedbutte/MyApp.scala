package crestedbutte

import java.time.{Instant, OffsetDateTime}
import java.util.concurrent.TimeUnit

import com.billding.time.{ColoradoClock, FixedClock, TurboClock}
import crestedbutte.Browser.Browser
import crestedbutte.dom.{
  BulmaBehaviorLocal,
  DomManipulation,
  ServiceWorkerBillding,
}
import crestedbutte.routes._
import org.scalajs.dom.experimental.serviceworkers._
import zio.clock._
import zio.console.Console
import zio.duration.Duration
import zio.{App, Schedule, ZIO, ZLayer}

import scala.util.{Failure, Success}

object MyApp extends App {

  override def run(
    args: List[String],
  ): ZIO[zio.ZEnv, Nothing, zio.ExitCode] = {
    val myEnvironment =
      ZLayer.succeed(BrowserLive.browser) ++ Console.live ++
      ZLayer.succeed(ColoradoClock.live)

    fullApplicationLogic.provideLayer(myEnvironment).exitCode
  }

  def loopLogic(
    pageMode: AppMode.Value,
    restaurantGroups: Seq[MemoryGroup],
  ): ZIO[Browser, Nothing, Unit] =
    for {
      serviceAreaOpt <- QueryParameters.getOptional(
        "route",
      )
      selectedRestaurantGroup: MemoryGroup = serviceAreaOpt
        .flatMap(
          serviceAreaParam =>
            restaurantGroups.find(
              _.name
                .elementNameMatches(serviceAreaParam),
            ),
        )
        .getOrElse(restaurantGroups.head)

      _ <- ZIO.succeed(
        println(
          "selectedRestaurantGroup: " + selectedRestaurantGroup.name.humanFriendlyName,
        ),
      )
      _ <- updateUpcomingArrivalsOnPage(selectedRestaurantGroup,
                                        restaurantGroups)
      _ <- ModalBehavior.addModalOpenBehavior
      _ <- ModalBehavior.addModalCloseBehavior
      _ <- UnsafeCallbacks.attachCardClickBehavior
    } yield ()

  private val restaurantGroups: Seq[MemoryGroup] =
    Seq(
      RecommendedCharities,
    )

  def deserializeTimeString(
    rawTime: String,
  ): OffsetDateTime =
    OffsetDateTime.parse(
      s"2020-02-20T${rawTime}:00.00-07:00",
    )

  val fullApplicationLogic =
    for {
      browser    <- ZIO.access[Browser](_.get)
      console    <- ZIO.access[Console](_.get)
      clockParam <- ZIO.access[Clock](_.get)
      pageMode <- QueryParameters
        .getOptional("mode", AppMode.fromString)
        .map { _.getOrElse(AppMode.Production) }
      _ <- DomManipulation.createAndApplyPageStructure(
        TagsOnlyLocal
          .overallPageLayout(pageMode, restaurantGroups)
          .render,
      )
      _ <- UnsafeCallbacks.attachMenuBehavior
      fixedTime <- QueryParameters.getRequired("time",
                                               deserializeTimeString)
      clock = if (fixedTime.isDefined)
        ZLayer.succeed(
          TurboClock.TurboClock(
            s"2020-02-20T${fixedTime.get.toString}:00.00-07:00",
          ),
        )
      else ZLayer.succeed(clockParam)
      environmentDependencies = ZLayer.succeed(browser) ++ ZLayer
        .succeed(console) ++ clock
      loopingLogic = loopLogic(
        pageMode,
        restaurantGroups,
      ).provideLayer(
        environmentDependencies,
      )
      _ <- BulmaBehaviorLocal.addMenuBehavior(
        loopingLogic,
      )
      _ <- loopingLogic
//        .repeat(Schedule.spaced(Duration.apply(600, TimeUnit.SECONDS)))
    } yield {
      0
    }

  def updateCurrentRestaurantInfoInCity(
    restaurantGroup: MemoryGroup,
    currentlySelectedRestaurantGroup: MemoryGroup,
  ): ZIO[Browser, Nothing, Unit] =
    if (restaurantGroup == currentlySelectedRestaurantGroup) {
      DomManipulation.updateContentInsideElementAndReveal(
        restaurantGroup.componentName,
        TagsOnlyLocal
          .structuredSetOfUpcomingArrivals(
            restaurantGroup,
          )
          .render,
        ElementNames.contentName,
      )
    }
    else {
      DomManipulation.hideElement(
        restaurantGroup.componentName,
      )
    }

  def updateUpcomingArrivalsOnPage(
    selectedRestaurantGroup: MemoryGroup,
    restaurantGroups: Seq[MemoryGroup],
  ) =
    for {
      modalIsOpen <- DomMonitoring.modalIsOpen
      _ <- if (modalIsOpen) ZIO.succeed()
      else
        ZIO.collectAll(
          restaurantGroups.map(
            (restaurantGroup: MemoryGroup) =>
              updateCurrentRestaurantInfoInCity(
                restaurantGroup,
                selectedRestaurantGroup,
              ),
          ),
        )
    } yield ()

}
