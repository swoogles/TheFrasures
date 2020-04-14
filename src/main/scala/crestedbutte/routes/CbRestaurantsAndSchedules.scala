package crestedbutte.routes

import java.time.DayOfWeek

import crestedbutte.time.BusDuration.toBusDuration
import crestedbutte.time.{DailyHours, HoursOfOperation}
import crestedbutte.{BusSchedule, Location, NamedRoute, PhoneNumber, RestaurantGroupName, RestaurantWithSchedule, Website}

object CbRestaurantsAndSchedules
    extends RestaurantGroup(
      RestaurantGroupName("Crested Butte Restaurants"),
      Seq(
        RestaurantWithSchedule(
          Location.BrickOven,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-5044", "Order!"),
          Website.global("http://brickovencb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/BrickOvenCB/",
          ),
          Some(
            HoursOfOperation("17:00", "20:00")
          ),
        ),
        RestaurantWithSchedule(
          Location.Bonez,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-5118", "Order!"),
          Website.global("https://www.bonez.co/"),
          Website.facebookPage(
            "https://www.facebook.com/BONEZ-662144153840370/",
          ),
        ),
        RestaurantWithSchedule(
          Location.ButteBagels,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-5630", "Order!"),
          Website.global("https://butte-bagels.com/"),
          Website.facebookPage(
            "https://www.facebook.com/pages/category/Bagel-Shop/Butte-Bagels-1225240390980501/",
          ),
        ),
        RestaurantWithSchedule(
          Location.CoalCreekGrill,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-6645", "Order!"),
          Website.global("http://www.coalcreekgrill.com/"),
          Website.facebookPage(
            "https://www.facebook.com/CoalCreekGrillCB/",
          ),
        ),
        RestaurantWithSchedule(
          Location.TheDivvy,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-787-5447", "Order!"),
          Website.global("https://thedivvycrestedbutte.com/"),
          Website.facebookPage(
            "https://www.facebook.com/TheDivvyCB/",
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
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-2783", "Order!"),
          Website.global("https://www.cbsouthgeneralstore.com/"),
          Website.facebookPage(
            "https://www.facebook.com/cbsouthgeneralstore/",
          ),
        ),
        RestaurantWithSchedule(
          Location.McGills,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-5240", "Order!"),
          Website.global("https://www.mcgillscrestedbutte.com/"),
          Website.facebookPage(
            "https://www.facebook.com/pages/McGills-At-Crested-Butte/119847854694618",
          ),
        ),
        RestaurantWithSchedule(
          Location.Montanyas,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-799-3206", "Order!"),
          Website.global("https://www.montanyarum.com/shop"),
          Website.facebookPage(
            "https://www.facebook.com/MontanyaDistillers/",
          ),
        ),
        RestaurantWithSchedule(
          Location.OctopusCoffee,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-312-5394", "Order!"),
          Website.global("https://www.octopuscoffeecb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/octopuscoffeecb/",
          ),
        ),
        RestaurantWithSchedule(
          Location.Pitas,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-0897", "Order!"),
          Website.global("http://pitasinparadise.com/"),
          Website.facebookPage(
            "https://www.facebook.com/PitasInParadise/",
          ),
        ),
        RestaurantWithSchedule(
          Location.SecretStash,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-209-5159", "Order!"),
          Website.global("http://www.secretstash.com/"),
          Website.facebookPage(
            "https://www.facebook.com/TheSecretStashPizza",
          ),
        ),
        RestaurantWithSchedule(
          Location.Slogar,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-5765", "Order!"),
          Website.global("https://www.slogarcb.com/"),
          Website.facebookPage(
            "https://www.facebook.com/slogarqueen/",
          ),
        ),
        RestaurantWithSchedule(
          Location.Tullys,
          BusSchedule("03:00", "03:01", 1.minutes),
          PhoneNumber("970-349-2444", "Order!"),
          Website.global("https://www.tullyscbsouth.com/"),
          Website.facebookPage(
            "https://www.facebook.com/tullys456/",
          ),
        ),
      ),
    ) {}
