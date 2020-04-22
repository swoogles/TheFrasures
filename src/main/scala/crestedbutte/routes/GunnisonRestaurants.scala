package crestedbutte.routes

import crestedbutte.time.{ClosedAllDay, Hours, HoursOfOperation}
import crestedbutte.{
  CallLocation,
  ExternalActionCollection,
  Location,
  PhoneNumber,
  RestaurantGroupName,
  RestaurantWithSchedule,
  StandardSchedule,
  VisitFacebookPage,
  VisitHomePage,
  Website,
}

object GunnisonRestaurants
    extends RestaurantGroup(
      RestaurantGroupName("Gunnison Restaurants"),
      Seq(
        RestaurantWithSchedule(
          Location("5 B's Bar-B-Q"),
          PhoneNumber("970-641-7360", "Order!"),
          Website.global("https://www.5bsbbq.com/"),
          Website.facebookPage(
            "https://www.facebook.com/5BsBBQ/",
          ),
          StandardSchedule.carryOutAndDelivery(
            HoursOfOperation(
              sunday = ClosedAllDay,
              monday = ClosedAllDay,
              tuesday = Hours("11:00", "19:00"),
              wednesday = Hours("11:00", "19:00"),
              thursday = Hours("11:00", "19:00"),
              friday = Hours("11:00", "19:00"),
              saturday = Hours("11:00", "19:00"),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location("Agave"),
          PhoneNumber("970-417-7489", "Order!"),
          Website.global("https://www.agavefamilymexican.com/"),
          Website.facebookPage(
            "https://www.facebook.com/agavefamilymexican/",
          ),
          StandardSchedule.carryOutOnly(
            HoursOfOperation.everyDay("10:30", "20:00"),
          ),
        ),
        RestaurantWithSchedule(
          Location("Anjeo"),
          ExternalActionCollection(
            CallLocation(PhoneNumber("970-641-1427")),
            Seq(
              VisitHomePage(
                Website.facebookPage(
                  "https://www.facebook.com/AnejoBistroBar/",
                ),
              ),
            ),
          ),
          Some(
            StandardSchedule.carryOutOnly(
              HoursOfOperation(
                sunday = ClosedAllDay,
                monday = Hours("11:30", "20:00"),
                tuesday = Hours("11:30", "20:00"),
                wednesday = Hours("11:30", "20:00"),
                thursday = Hours("11:30", "20:00"),
                friday = Hours("11:30", "20:00"),
                saturday = Hours("11:30", "20:00"),
              ),
            ),
          ),
        ),
        RestaurantWithSchedule(
          Location("Arby's"),
          PhoneNumber("970-641-8851"),
          Website.global(
            "https://locations.arbys.com/co/gunnison/800-e-tomichi-ave-temp.html",
          ),
          Website.facebookPage(
            "https://www.facebook.com/Arbys-468185087012524/",
          ),
          StandardSchedule.carryOutOnly(
            HoursOfOperation.everyDay("10:00", "20:00"),
          ),
        ),
        RestaurantWithSchedule(
          Location("Blackstock Bistro"),
          ExternalActionCollection(
            VisitHomePage(
              Website.onlineOrder(
                "https://us.orderspoon.com/blackstock-bistro",
              ),
            ),
            Seq(
              VisitHomePage(
                Website.global("http://blackstockbistro.com/"),
              ),
              // TODO Ask them which actions they prefer
//              VisitFacebookPage(
//                Website.facebookPage(
//                  "https://www.facebook.com/Blackstock-Bistro-1440692279549356/",
//                ),
//              ),
              CallLocation(PhoneNumber("970-641-4394")),
            ),
          ),
        ),
      ),
    )