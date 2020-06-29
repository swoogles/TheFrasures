package crestedbutte

import java.time.{Instant, LocalDateTime, ZoneId, ZoneOffset}

import com.billding.time.BusTime
import crestedbutte.routes.MemoryGroup
import crestedbutte.time.{
  ClosedForTheDay,
  DailyHours,
  DailyHoursSegment,
  DailyInfo,
}
import zio.ZIO
import zio.clock.Clock

object TimeCalculations {

  def nextBusArrivalTime(timesAtStop: Seq[BusTime],
                         now: BusTime): Option[BusTime] =
    timesAtStop
      .find(stopTime => BusTime.catchableBus(now, stopTime))
      .filter(_ => now.isLikelyEarlyMorningRatherThanLateNight)

  def currentStatus(dailyInfo: DailyInfo,
                    now: BusTime): RestaurantStatus =
    dailyInfo match {
      case ClosedForTheDay(dayOfWeek) => Closed
      case DailyHours(
          dailyHoursSegments: Seq[DailyHoursSegment],
          dayOfWeek,
          ) => // TODO This will need to handle multiple time segments.
        if (dailyHoursSegments
              .map(statusForCurrentSegment(_, now))
              .exists(
                statusForCurrentSegment =>
                  statusForCurrentSegment.equals(Open),
              )) Open
        else Closed
    }

  def statusForCurrentSegment(hoursSegment: DailyHoursSegment,
                              now: BusTime): RestaurantStatus =
    hoursSegment match {
      case DailyHoursSegment(open, close) => // TODO This will need to handle multiple time segments.
        if (now.isBefore(open) && now.between(open).toMinutes > 30)
          OpeningSoon
        else if (now.isAfterOrEqualTo(open) && now.isBefore(close))
          if (now.between(close).toMinutes < 20)
            ClosingSoon
          else
            Open
        else
          Closed
    }

  val now: ZIO[Clock, Nothing, Instant] =
    for {
      clockProper <- ZIO.environment[Clock]
      now         <- clockProper.clock.currentDateTime
    } yield {
      now.toInstant
    }

}
