package crestedbutte

case class RelationshipMoment(
  location: Name,
  externalActions: ExternalActionCollection,
  businessDetails: Details,
)

object RelationshipMoment {}

case class Details(description: String)
