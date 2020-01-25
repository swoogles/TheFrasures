import java.io.IOException
import java.time.LocalTime
import java.util.concurrent.TimeUnit

import org.scalajs.dom.html.Table
import scalatags.{JsDom, Text}
import zio.{App, Schedule, ZIO}
import zio.console.{putStrLn, _}
import zio.clock._
import zio._
import zio.duration.Duration

object MyApp extends App {

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] = {
    val logic: ZIO[Console with Clock, IOException, Int] = for {
      _ <- putStr("name: ")
      name <- ZIO.succeed("Hardcoded Harry")
      now <- currentDateTime
      _ <- putStrLn(s"hello, $name - $now")
    } yield (0)

    currentDateTime
    for {
      _ <- DomManipulation.addWelcomeMessage()
      now <- currentDateTime
      _ <- putStrLn("CurrentLocalTime: " + now.toLocalTime)
      _ <- DomManipulation.addElementToPage(DomManipulation.createBusScheduleTable(BusTimes.fullDayOfBusStarts))

      _ <- BusTimes.printBusInfo
      _ <- ScheduleSandbox.liveBusses
//        (left, right) <- putStrLn("left: " + left + " right: " + right)
    } yield {
      0

    }
  }
}

object ScheduleSandbox {
  /*
  Regular Winter Schedule: November 27th through April 12th

    Every 15 Minutes from 7:10 AM to Midnight
                                        Stop	Times	            First Bus	  Last Bus
    Old Town Hall (2nd and Elk)	        :10, :25, :40, :55	    7:10 AM	    11:40 PM
    6th and Belleview	                  :59, :14, :29, :44	    7:14 AM	    11:44 PM
    4-Way Stop	                        :00, :15, :30, :45 	    7:15 AM	    11:45 PM
    Teocalli	                          :01, :16, :31, :46	    7:16 AM	    11:46 PM
    Mountaineer Square/Transit Center	  :00, :15, :30, :45	    7:30 AM 	  12:00 AM
   */

  val driveFromMountaineerToTeocalli =
    Schedule.once && Schedule.spaced(duration.durationInt(2).seconds)

  def realBusSchedule(numberOfBussesPerDay: Int): Schedule[Clock, Any, (Int, Int)] =
    Schedule.recurs(numberOfBussesPerDay) &&
      Schedule.spaced(duration.durationInt(2).seconds)

  def singleBusRoute(number: Int) =
    DomManipulation.appendMessageToPage(s"Bus #$number is leaving old town hall!")
      .flatMap(_ => ZIO.sleep(duration.durationInt(4).seconds))
      .flatMap(_ => DomManipulation.appendMessageToPage(s"Bus #$number arrived at clarks!"))
      .flatMap(_ => ZIO.sleep(duration.durationInt(1).seconds))
      .flatMap(_ => DomManipulation.appendMessageToPage(s"Bus #$number arrived at 4 way!"))
      .flatMap(_ => ZIO.sleep(duration.durationInt(1).seconds))
      .flatMap(_ => DomManipulation.appendMessageToPage(s"Bus #$number arrived at Teocalli!"))
      .flatMap(_ => ZIO.sleep(duration.durationInt(5).seconds)) // TODO Proper amount here
      .flatMap(_ => DomManipulation.appendMessageToPage(s"Bus #$number arrived at Mountaineer Square!"))

  val secondBus =
    ZIO.sleep(duration.durationInt(4).seconds).flatMap(_ => singleBusRoute(2))

  val thirdBus =
    ZIO.sleep(duration.durationInt(8).seconds).flatMap(_ => singleBusRoute(3))

  val liveBusses =
    ZIO.reduceAllPar(singleBusRoute(1), List(secondBus, thirdBus)){ case (_, _) => ()}

//      .repeat(realBusSchedule(5))
}

object BusTimes {
  val startTime = LocalTime.parse("07:00:00")
  val endTime = LocalTime.parse("23:00:00")
  val totalBusRunTime = java.time.Duration.between(startTime, endTime)
  val numberOfBusesPerDay = totalBusRunTime.getSeconds / java.time.Duration.ofMinutes(15).getSeconds
  val fullDayOfBusStarts =
    List.range(0, numberOfBusesPerDay)
    .map(index => startTime.plus(java.time.Duration.ofMinutes(15).multipliedBy(index)))

  val printBusInfo =
    for {
      _ <- putStrLn("First Bus: " +  BusTimes.startTime)
      _ <- putStrLn("Last Bus: " +  BusTimes.endTime)
      _ <- putStrLn("Total Bus Run Time:" +  BusTimes.totalBusRunTime)
      _ <- putStrLn("Number of buses per day:" +  BusTimes.numberOfBusesPerDay)
    } yield ()
}

object DomManipulation {
  import org.scalajs.dom
  import dom.document
//  import scalatags.Text.all._
  import scalatags.JsDom.all._

  def createBusScheduleTable(times: List[LocalTime]): JsDom.TypedTag[Table] = {
    table(
      times
        .map(time => tr(td(time.toString)))
    )
  }

  def appendMessageToPage(message: String) =
    for {
      _ <- putStrLn(message)
      _ <-
        ZIO {
          println("Are we actually adding the new messages?")
          val paragraph = div(message)
          document.body.querySelector("#logs").appendChild(paragraph.render)
        }
          .catchAll( error => ZIO.succeed("Guess we don't care about failed dom manipulation") )
    } yield ()

  def addWelcomeMessage() = ZIO {
    val paragraph = p("Let's Make a Bus Schedule App!")
    document.body.appendChild(paragraph.render)
  }
    .catchAll( error => ZIO.succeed("Guess we don't care about failed dom manipulation") )

  def addElementToPage(element: JsDom.TypedTag[Table]) = ZIO {
    document.body.appendChild(element.render)
  }
    .catchAll( error => ZIO.succeed("Guess we don't care about failed dom manipulation") )
}
