package crestedbutte

case class Restaurant(
  location: Name,
  externalActions: ExternalActionCollection,
  businessDetails: Details,
)

object Restaurant {}

case class Details(description: String)
