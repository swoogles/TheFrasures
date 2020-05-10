package crestedbutte.routes

import crestedbutte.time.{ClosedAllDay, Hours, HoursOfOperation}
import crestedbutte.{
  Details,
  ExternalActionCollection,
  Name,
  PhoneNumber,
  Restaurant,
  VisitHomePage,
  Website,
}

object SignificantRelationshipMoments
    extends RestaurantGroup(
      Name("Significant Relationship Moments"),
      Seq(
        Restaurant(
          Name("Bruce's 60th Birthday"),
          ExternalActionCollection(
            VisitHomePage(Website.global("somewebsite")),
          ),
          Details(
            "This is where we met!",
          ),
        ),
      ),
    ) {}
