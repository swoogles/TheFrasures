package crestedbutte

import java.time.DayOfWeek

import crestedbutte.dom.Bulma
import crestedbutte.routes.MemoryGroup
import org.scalajs.dom.html.{Anchor, Div}
import scalatags.JsDom

object TagsOnlyLocal {
  import scalatags.JsDom.all._

  def overallPageLayout(
    pageMode: AppMode.Value,
    restaurantGroups: Seq[MemoryGroup],
  ) =
    div(id := "container")(
      // Restore once other non-charity sections are added
//      Bulma.menu(
//        restaurantGroups.map { restaurantGroup =>
//          Bulma.Button.anchor(
//            restaurantGroup.name.humanFriendlyName,
//          )(data("route") := restaurantGroup.componentName)
//        },
//        "Our Links",
//      ),
      restaurantGroups.map(
        restaurantGroup =>
          busScheduleDiv(restaurantGroup.componentName),
      ),
      div(cls := "contact-me")(
        div(
          span("Created by "),
          a(href := "https://www.billdingsoftware.com")(
            "Billding Software LLC",
          ),
        ),
        div(
          "For corrections, confusion, kudos, or anger: ",
        ),
        a(href := "mailto:bill@billdingsoftware.com")("Email Me!"),
      ),
      if (pageMode == AppMode.Development) {
        div(
          // Dev stuff here.
        )
      }
      else div(),
    )

  def busScheduleDiv(
    containerName: String,
  ) =
    div(cls := ElementNames.BoxClass, id := containerName)(
      div(cls := "timezone"),
      div(id := ElementNames.contentName),
    )

  def renderWebsiteLink(
    website: Website,
  ): JsDom.TypedTag[Div] =
    div(cls := "call-button")(
      Bulma.Button.basic(
        div(
          img(
            cls := "glyphicon",
            src := "/glyphicons/svg/individual-svg/glyphicons-basic-417-globe.svg",
            alt := "Visit Website",
          ),
          span(verticalAlign := "top")(
            website.name,
          ),
        ),
      )(
        onclick :=
          s"window.location.href = '${website.url}';",
      ),
    )

  def phoneButton(
    safeRideRecommendation: PhoneNumber,
  ): JsDom.TypedTag[Div] =
    div(cls := "call-button")(
      Bulma.Button
        .basic(
          div(
            img(
              cls := "glyphicon",
              src := "/glyphicons/svg/individual-svg/glyphicons-basic-465-call.svg",
              alt := "Call Late Night Shuttle!",
            ),
            span(verticalAlign := "top")(
              safeRideRecommendation.name,
            ),
          ),
        )
        .apply(
          onclick :=
            s"window.location.href = 'tel:${safeRideRecommendation.number}';",
        ),
    )

  def phoneLink(
    phoneNumber: PhoneNumber,
  ): JsDom.TypedTag[Anchor] =
    a(href := s"tel:${phoneNumber.number}", cls := "link")(
      phoneNumber.name,
    )

  def renderDayName(
    dayOfWeek: DayOfWeek,
  ): String =
    // This doesn't work for some lame reason.
    // .getDisplayName(TextStyle.SHORT_STANDALONE, Locale.US),
    dayOfWeek match {
      case DayOfWeek.SUNDAY    => "Sun"
      case DayOfWeek.MONDAY    => "Mon"
      case DayOfWeek.TUESDAY   => "Tue"
      case DayOfWeek.WEDNESDAY => "Wed"
      case DayOfWeek.THURSDAY  => "Thur"
      case DayOfWeek.FRIDAY    => "Fri"
      case DayOfWeek.SATURDAY  => "Sat"
    }

  def createBusTimeElement(
    restaurantWithStatus: RelationshipMoment,
  ): JsDom.TypedTag[Div] = {
    val externalActions =
      renderExternalActions(
        restaurantWithStatus.externalActions,
      )
    Bulma.collapsedCardWithHeader(
      div(cls := "restaurant-header")(
        div(cls := "restaurant-name")(
          div(
            restaurantWithStatus.location.humanFriendlyName,
          ),
        ),
        div(cls := "restaurant-call")(
          renderExternalAction(
            restaurantWithStatus.externalActions.primary,
          ),
        ),
      ),
      div(
        svgIcon("glyphicons-basic-221-chevron-down.svg"),
      ),
      div(cls := "restaurant-information")(
        restaurantWithStatus.businessDetails.description,
      ),
      externalActions,
    )(
      data("location") := restaurantWithStatus.location.elementName,
    )
  }

  def activateModal(
    targetName: String,
  ): Unit =
    org.scalajs.dom.document.body
      .querySelector(targetName)
      .classList
      .add("is-active")

  def renderExternalAction(
    externalAction: ExternalAction,
  ) =
    externalAction match {
      case VisitHomePage(website) =>
        renderWebsiteLink(website)
      case VisitFacebookPage(website) =>
        renderWebsiteLink(website)
      case CallLocation(phoneNumber) =>
        phoneButton(phoneNumber)
    }

  def structuredSetOfUpcomingArrivals(
    restaurantGroup: MemoryGroup,
  ) =
    div(
      div(cls := "route-header")(
        span(cls := "route-header_name")(
          restaurantGroup.name.humanFriendlyName,
        ),
      ),
      // TODO Description text should be a MemoryGroup field.
      div(
        """
        Thank you for for your kindness towards us.
        In lieu of personal gifts, we would love for you to make a donation to one of the following charities.
        We still want to thank you, so if you choose to donate, please share your donation with us at this address!""",
      ),
      div(textAlign := "center",
          paddingTop := "20px",
          paddingBottom := "20px")(
        a(href := "mailto:frasureclan@gmail.com")(
          "frasureclan@gmail.com",
        ),
      ),
      restaurantGroup.allRestaurants.map {
        restaurant: RelationshipMoment =>
          TagsOnlyLocal.createBusTimeElement(restaurant)
      },
    )

  def renderExternalActions(
    externalActions: ExternalActionCollection,
  ) =
    externalActions.others.map(
      externalAction => renderExternalAction(externalAction),
    )

  def svgIcon(
    name: String,
  ) =
    img(
      cls := "glyphicon",
      src := s"/glyphicons/svg/individual-svg/$name",
      alt := "",
    )
  /*
  glyphicons-basic-591-map-marker.svg
  glyphicons-basic-417-globe.svg
  glyphicons-basic-262-direction-empty.svg
  glyphicons-basic-581-directions.svg
  glyphicons-basic-697-directions-sign.svg

 */

}
