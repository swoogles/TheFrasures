package crestedbutte.routes

import crestedbutte.{
  Details,
  ExternalActionCollection,
  Name,
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
            """
            Bill:
            Malaria is one of the deadliest, most easily prevented, diseases on Earth.
            This organization provides durable, insecticide-treated mosquito nets to hang over beds of those at-risk.
            Each net costs about $2.00, lasts for 3-4 years, and protects, on average, two people.
            Every 100-1,000 nets prevents one child death.
            """,
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
            """
            Hali:
            Health Education is one of the most important gifts we can give.
            This organization does culture-specific mass-media (via radio!) outreach in developing countries
            to teach skills and broaden medical knowledge.
            Focuses include, but are not limited to: sanitation, disease spread, and causes of early infant mortality.
            My family has always deemed education (and radio!) to be very important, so this cause is very close to my heart.
            """,
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
            """
              Bill & Hali:
              On a lighter, closer-to-home note, CBMT is our local community theatre.
              Hali has been performing here since 1998, and Bill has been involved since
              moving to Crested Butte 5 years ago. As everyone knows, the arts are struggling at the moment,
              as live performance is all but impossible.
              The 2 of us have bonded over theatre productions, and hope to do so well into the future.
              Please consider making a gift in our name!

              """.stripMargin,
          ),
        ),
      ),
    ) {}
