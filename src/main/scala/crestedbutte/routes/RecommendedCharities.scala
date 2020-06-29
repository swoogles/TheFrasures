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
                .global("https://www.againstmalaria.com/donate.aspx"),
            ),
          ),
          Details(
            "This is one of the most effective ways to save lives.",
          ),
        ),
        RelationshipMoment(
          Name("Seu Jorge Concert"), // September 9, 2017
          ExternalActionCollection(
            VisitHomePage(Website.global("somewebsite")),
          ),
          Details(
            """This was the first trip we took together!
              | I had never seen the Life Aquatic, or heard of Seu Jorge, but it was an opportunity to take a weekend trip
              | with this new chick and see what she was all about.
              | I smote her with my cover of Rocky Top at Karaoke after the concert.
              |""".stripMargin,
          ),
        ),
        RelationshipMoment(
          Name("HUMP! Film Festival"), //  September 30, 2017
          ExternalActionCollection(
            VisitHomePage(
              Website.global("https://humpfilmfest.com/"),
            ),
          ),
          Details(
            """
              | We had both heard about this from Dan Savage, but had never had anyone to go with.
              | Very early in our courtship, one of us referenced it.
              | Even though we had only been hanging out for a week, and the festival was months in the future,
              |   I had a good feeling about it, and purchased tickets.
              | It would end up being one of our foundational memories/experiences together!
              |""".stripMargin,
          ),
        ),
        RelationshipMoment(
          Name("Mega Christmas 2017 Road trip"),
          ExternalActionCollection(
            VisitHomePage(
              Website.global(""),
            ),
          ),
          Details(
            """
              |
              |
              |""".stripMargin,
          ),
        ),
      ),
    ) {}
