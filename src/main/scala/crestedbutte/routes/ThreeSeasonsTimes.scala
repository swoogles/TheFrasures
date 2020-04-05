package crestedbutte.routes

import crestedbutte.{
  BusSchedule,
  Location,
  NamedRoute,
  RestaurantWithSchedule,
  RouteName,
}
import crestedbutte.time.BusDuration.toBusDuration

/*

  // TODO How should I indicate when it arrives back at Mountaineer Square?
  //    Currently, it's hard to determine what buses you could catch from the Square.
 */
object ThreeSeasonsTimes
    extends NamedRoute(
      RouteName("Three Seasons Loop"),
      RouteWithTimes(
        RestaurantWithSchedule(
          Location.MountaineerSquare,
          BusSchedule("08:00", "22:45", 15.minutes),
        ),
        Seq(
          (Location.ThreeSeasons, 1.minutes),
          (Location.MountainSunrise, 1.minutes),
          (Location.UpperChateaux, 0.minutes),
          (Location.LowerChateaux, 1.minutes),
        ),
      ),
    )
