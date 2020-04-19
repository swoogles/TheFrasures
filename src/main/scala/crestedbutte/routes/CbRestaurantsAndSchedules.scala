package crestedbutte.routes

import java.time.DayOfWeek

import crestedbutte.interpolators.PhoneNumberValueInterpolator
import crestedbutte.time.{
  ClosedForTheDay,
  DailyHours,
  HoursOfOperation,
}
import crestedbutte.{
  AdvanceOrdersOnly,
  Location,
  PhoneNumber,
  RestaurantGroupName,
  RestaurantWithSchedule,
  StandardSchedule,
  Website,
}

object CbRestaurantsAndSchedules
    extends RestaurantGroup(
      RestaurantGroupName("Crested Butte Restaurants"),
      Seq(
        RestaurantWithSchedule(
          Location.BrickOven,
          PhoneNumber("970-349-5044", "Order!"),
          Website.global("http://brickovencb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/BrickOvenCB/",
          ),
          StandardSchedule(
            deliveryHours = Some(
              HoursOfOperation("17:00", "20:00"),
            ),
            carryOutHours = Some(
              HoursOfOperation("11:00", "20:00"),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.Bonez,
          PhoneNumber("970-349-5118", "Order!"),
          Website.global("https://www.bonez.co/"),
          Website.facebookPage(
            "https://www.facebook.com/BONEZ-662144153840370/",
          ),
          StandardSchedule(
            deliveryHours = Some(
              HoursOfOperation(
                sunday = ClosedForTheDay(DayOfWeek.SUNDAY),
                monday =
                  DailyHours("16:30", "19:30", DayOfWeek.MONDAY),
                tuesday =
                  DailyHours("16:30", "19:30", DayOfWeek.TUESDAY),
                wednesday =
                  DailyHours("16:30", "19:30", DayOfWeek.WEDNESDAY),
                thursday = ClosedForTheDay(DayOfWeek.THURSDAY),
                friday = ClosedForTheDay(DayOfWeek.FRIDAY),
                saturday = ClosedForTheDay(DayOfWeek.SATURDAY),
              ),
            ),
            carryOutHours = Some(
              HoursOfOperation(
                sunday = ClosedForTheDay(DayOfWeek.SUNDAY),
                monday =
                  DailyHours("16:30", "19:30", DayOfWeek.MONDAY),
                tuesday =
                  DailyHours("16:30", "19:30", DayOfWeek.TUESDAY),
                wednesday =
                  DailyHours("16:30", "19:30", DayOfWeek.WEDNESDAY),
                thursday = ClosedForTheDay(DayOfWeek.THURSDAY),
                friday = ClosedForTheDay(DayOfWeek.FRIDAY),
                saturday = ClosedForTheDay(DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.ButteBagels,
          PhoneNumber("970-349-5630", "Order!"),
          Website.global("https://butte-bagels.com/"),
          Website.facebookPage(
            "https://www.facebook.com/pages/category/Bagel-Shop/Butte-Bagels-1225240390980501/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation(
                sunday =
                  DailyHours("08:00", "14:00", DayOfWeek.SUNDAY),
                monday = ClosedForTheDay(DayOfWeek.MONDAY),
                tuesday = ClosedForTheDay(DayOfWeek.TUESDAY),
                wednesday =
                  DailyHours("08:00", "14:00", DayOfWeek.WEDNESDAY),
                thursday =
                  DailyHours("08:00", "14:00", DayOfWeek.THURSDAY),
                friday =
                  DailyHours("08:00", "14:00", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("08:00", "14:00", DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
//        RestaurantWithSchedule(
//          Location.CoalCreekGrill,
//          BusSchedule("03:00", "03:01", 1.minutes),
//          PhoneNumber("970-349-6645", "Order!"),
//          Website.global("http://www.coalcreekgrill.com/"),
//          Website.facebookPage(
//            "https://www.facebook.com/CoalCreekGrillCB/",
//          ),
//        ),
        RestaurantWithSchedule(
          Location.TheDivvy,
          PhoneNumber("970-787-5447", "Order!"),
          Website.global("https://thedivvycrestedbutte.com/"),
          Website.facebookPage(
            "https://www.facebook.com/TheDivvyCB/",
          ),
          AdvanceOrdersOnly(
            """Pick-up/Carry Out by appointment only.
              |Will deliver if quarantined.
              |Must order 1 day in advance.
              |Taking orders 7 days a week""".stripMargin,
          ),
        ),
        RestaurantWithSchedule(
          Location.GasCafe,
          PhoneNumber("970-349-9656", "Order!"),
          Website.global("https://www.gascafe1stop.com/"),
          Website.facebookPage(
            "https://www.facebook.com/gascafeonestop/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation(
                sunday = ClosedForTheDay(DayOfWeek.SUNDAY),
                monday =
                  DailyHours("07:00", "15:00", DayOfWeek.MONDAY),
                tuesday =
                  DailyHours("07:00", "15:00", DayOfWeek.TUESDAY),
                wednesday =
                  DailyHours("07:00", "15:00", DayOfWeek.WEDNESDAY),
                thursday =
                  DailyHours("07:00", "15:00", DayOfWeek.THURSDAY),
                friday =
                  DailyHours("07:00", "15:00", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("07:00", "15:00", DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
//        RestaurantWithSchedule(
//          Location.Dogwood,
//          BusSchedule("03:00", "03:01", 1.minutes),
//          PhoneNumber("970-349-6338", "Order!"),
//          Website.global("https://thedogwoodcb.wordpress.com/"),
//          Website.facebookPage(
//            "https://www.facebook.com/thedogwoodcb/",
//          ),
//        ),
        RestaurantWithSchedule(
          Location.GeneralStore,
          PhoneNumber("970-349-2783", "Order!"),
          Website.global("https://www.cbsouthgeneralstore.com/"),
          Website.facebookPage(
            "https://www.facebook.com/cbsouthgeneralstore/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation("11:00", "19:00"),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.McGills,
          PhoneNumber("970-349-5240", "Order!"),
          Website.global("https://www.mcgillscrestedbutte.com/"),
          Website.facebookPage(
            "https://www.facebook.com/pages/McGills-At-Crested-Butte/119847854694618",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation("09:00", "14:00"),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.Montanyas,
          PhoneNumber("970-799-3206", "Order!"),
          Website.global("https://www.montanyarum.com/shop"),
          Website.facebookPage(
            "https://www.facebook.com/MontanyaDistillers/",
          ),
          AdvanceOrdersOnly(
            "Order by 4 pm on Thursday and your order will be ready for pick up on Friday between 4 - 7 pm.",
          ),
        ),
        RestaurantWithSchedule(
          Location.OctopusCoffee,
          PhoneNumber("970-312-5394", "Order!"),
          Website.global("https://www.octopuscoffeecb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/octopuscoffeecb/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation(
                sunday =
                  DailyHours("10:00", "15:00", DayOfWeek.SUNDAY),
                monday =
                  DailyHours("10:00", "15:00", DayOfWeek.MONDAY),
                tuesday = ClosedForTheDay(DayOfWeek.TUESDAY),
                wednesday = ClosedForTheDay(DayOfWeek.WEDNESDAY),
                thursday = ClosedForTheDay(DayOfWeek.THURSDAY),
                friday =
                  DailyHours("10:00", "15:00", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("10:00", "15:00", DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.Pitas,
          PhoneNumber("970-349-0897", "Order!"),
          Website.global("http://pitasinparadise.com/"),
          Website.facebookPage(
            "https://www.facebook.com/PitasInParadise/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation("12:00", "18:00"),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.SecretStash,
          PhoneNumber("970-209-5159", "Order!"),
          Website.global("http://www.secretstash.com/"),
          Website.facebookPage(
            "https://www.facebook.com/TheSecretStashPizza",
          ),
          StandardSchedule(
            deliveryHours = Some(
              HoursOfOperation(
                sunday =
                  DailyHours("16:30", "19:30", DayOfWeek.SUNDAY),
                monday = ClosedForTheDay(DayOfWeek.MONDAY),
                tuesday = ClosedForTheDay(DayOfWeek.TUESDAY),
                wednesday = ClosedForTheDay(DayOfWeek.WEDNESDAY),
                thursday =
                  DailyHours("16:30", "19:30", DayOfWeek.THURSDAY),
                friday =
                  DailyHours("16:30", "19:30", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("16:30", "19:30", DayOfWeek.SATURDAY),
              ),
            ),
            carryOutHours = Some(
              HoursOfOperation(
                sunday =
                  DailyHours("16:30", "19:30", DayOfWeek.SUNDAY),
                monday = ClosedForTheDay(DayOfWeek.MONDAY),
                tuesday = ClosedForTheDay(DayOfWeek.TUESDAY),
                wednesday = ClosedForTheDay(DayOfWeek.WEDNESDAY),
                thursday =
                  DailyHours("16:30", "19:30", DayOfWeek.THURSDAY),
                friday =
                  DailyHours("16:30", "19:30", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("16:30", "19:30", DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location.Slogar,
          PhoneNumber("970-349-5765", "Order!"),
          Website.global("https://www.slogarcb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/slogarqueen/",
          ),
          AdvanceOrdersOnly(
            """Place order Thursday-Friday BY 6 on FRIDAYS!
              |We will call/text you with confirmation on SATURDAY
              |to tell you what time to pick up your order on SUNDAY.""".stripMargin,
          ),
        ),
        RestaurantWithSchedule(
          Location.SoupCon,
          PhoneNumber("970-349-5448", "Order!"),
          Website.global("https://www.soupconcb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/Soupconcb",
          ),
          AdvanceOrdersOnly(
            "Order by 5:00PM Friday. Pickup between 5:00 and 7:30 Saturday.",
          ),
        ),
        RestaurantWithSchedule(
          Location.Tullys,
          PhoneNumber("970-349-2444", "Order!"),
          Website.global("https://www.tullyscbsouth.com/"),
          Website.facebookPage(
            "https://www.facebook.com/tullys456/",
          ),
          StandardSchedule(
            deliveryHours = None,
            carryOutHours = Some(
              HoursOfOperation(
                sunday = ClosedForTheDay(DayOfWeek.SUNDAY),
                monday = ClosedForTheDay(DayOfWeek.MONDAY),
                tuesday =
                  DailyHours("16:00", "21:00", DayOfWeek.TUESDAY),
                wednesday =
                  DailyHours("16:00", "21:00", DayOfWeek.WEDNESDAY),
                thursday =
                  DailyHours("16:00", "21:00", DayOfWeek.THURSDAY),
                friday =
                  DailyHours("16:00", "21:00", DayOfWeek.FRIDAY),
                saturday =
                  DailyHours("16:00", "21:00", DayOfWeek.SATURDAY),
              ),
            ),
          ),
        ),
      ),
    ) {}
