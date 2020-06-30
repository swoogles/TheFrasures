package crestedbutte.routes

import crestedbutte.time.{ClosedAllDay, Hours, HoursOfOperation}
import crestedbutte.{
  Details,
  ExternalActionCollection,
  Name,
  PhoneNumber,
  RelationshipMoment,
  VisitHomePage,
  Website,
}

object RecommendedCharities
    extends MemoryGroup(
      Name("Our recommended charities"),
      Seq(
        RelationshipMoment(
          Name("Against Malaria Foundation"),
          ExternalActionCollection(
            VisitHomePage(
              Website
                .donate("https://www.againstmalaria.com/donate.aspx"),
            ),
            Seq(
              VisitHomePage(
                Website
                  .giveWell("https://www.givewell.org/charities/AMF"),
              ),
            ),
          ),
          Details(
            "This is one of the most effective ways to save lives.",
          ),
        ),
        RelationshipMoment(
          Name("Development Media International"),
          ExternalActionCollection(
            VisitHomePage(
              Website
                .donate(
                  "https://www.developmentmedia.net/donate.html",
                ),
            ),
            Seq(
              VisitHomePage(
                Website
                  .giveWell("https://www.givewell.org/charities/DMI"),
              ),
            ),
          ),
          Details(
            "This is one of the most effective ways to save lives.",
          ),
        ),
      ),
    ) {}
