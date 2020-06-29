package crestedbutte

case class RestaurantWithStatus(
  restaurantWithSchedule: RelationshipMoment,
  carryOutStatus: RestaurantStatus,
  deliveryStatus: RestaurantStatus,
)
