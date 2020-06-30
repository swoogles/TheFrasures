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
      Name("Our Recommended Charities"),
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
            "They create localized health media in developing nations.",
          ),
        ),
        RelationshipMoment(
          Name("Crested Butte Mountain Theater"),
          ExternalActionCollection(
            VisitHomePage(
              Website
                .donate(
                  "https://www.cbmountaintheatre.org/donations.html",
                ),
            ),
          ),
          Details(
            """On a lighter, closer-to-home note, this is our local community theatre.
              | Hali has been performing here since middle school, and Bill has been involved since
              | moving to Crested Butte. They are having a really hard time surviving during
              | all of the Covid measures.
              |""".stripMargin,
          ),
        ),
      ),
    ) {}
