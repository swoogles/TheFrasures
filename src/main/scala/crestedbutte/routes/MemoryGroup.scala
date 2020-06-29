package crestedbutte.routes

import crestedbutte.{Name, RelationshipMoment}

case class MemoryGroup(
  name: Name,
  allRestaurants: Seq[RelationshipMoment],
) {
  val componentName = name.elementName
}
