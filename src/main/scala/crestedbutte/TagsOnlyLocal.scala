package crestedbutte

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

import com.billding.time.BusTime
import crestedbutte.dom.Bulma
import crestedbutte.routes.MemoryGroup
import crestedbutte.time.{
  ClosedForTheDay,
  DailyHours,
  DailyInfo,
  HoursOfOperation,
}
import org.scalajs.dom.html.{Anchor, Div}
import scalatags.JsDom
import scalatags.JsDom.TypedTag

object TagsOnlyLocal {
  import scalatags.JsDom.all._

  def overallPageLayout(pageMode: AppMode.Value,
                        restaurantGroups: Seq[MemoryGroup]) =
    div(id := "container")(
      Bulma.menu(
        restaurantGroups.map { restaurantGroup =>
          Bulma.Button.anchor(
            restaurantGroup.name.humanFriendlyName,
          )(data("route") := restaurantGroup.componentName)
        },
        "Our Links",
      ),
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
        Bulma.Button.basic(
          a(href := "mailto:bill@billdingsoftware.com")("Email Me!"),
        ),
      ),
      if (pageMode == AppMode.Development) {
        div(
          // Dev stuff here.
        )
      } else div(),
    )

  def busScheduleDiv(containerName: String) =
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

  //  <a href="tel:123-456-7890">123-456-7890</a>
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

  def renderDeliverySchedule(
    hoursOfOperationOpt: Option[HoursOfOperation],
  ) =
    renderHoursOfOperation(hoursOfOperationOpt, "Delivery")

  def renderPickupSchedule(
    hoursOfOperationOpt: Option[HoursOfOperation],
  ) =
    renderHoursOfOperation(hoursOfOperationOpt, "Pickup")

  def renderHoursOfOperation(
    hoursOfOperationOpt: Option[HoursOfOperation],
    scheduleHeader: String,
  ) =
    div(cls := "hours-of-operation")(
      div(cls := "hours-header")(
        div(cls := "hours-header-top")(scheduleHeader),
        div(cls := "hours-header-bottom")("Schedule"),
      ),
      hoursOfOperationOpt match {
        case Some(hoursOfOperation) =>
          div(
            renderDailySchedule(hoursOfOperation.sunday),
            renderDailySchedule(hoursOfOperation.monday),
            renderDailySchedule(hoursOfOperation.tuesday),
            renderDailySchedule(hoursOfOperation.wednesday),
            renderDailySchedule(hoursOfOperation.thursday),
            renderDailySchedule(hoursOfOperation.friday),
            renderDailySchedule(hoursOfOperation.saturday),
          )
        case None =>
          div(cls := "service-unavailable")("Not available.")
      },
    )

  def renderDayName(dayOfWeek: DayOfWeek): String =
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

  def renderDailyhours(dailyHours: DailyHours) =
    div(cls := "daily-hours")(
      div(cls := "day")(
        div(cls := "day-name")(
          renderDayName(dailyHours.dayOfWeek),
        ),
      ),
      div(cls := "hours")(
        dailyHours.hoursSegment.map(
          hoursSegment =>
            div(
              hoursSegment.open.toDumbAmericanString + "-" +
              hoursSegment.close.toDumbAmericanString,
            ),
        ),
      ),
    )

  def renderDailySchedule(
    dailySchedule: DailyInfo,
  ) =
    dailySchedule match {
      case closedForTheDay: ClosedForTheDay =>
        renderClosedForTheDay(closedForTheDay)
      case dailyHours: DailyHours => renderDailyhours(dailyHours)
    }

  def renderClosedForTheDay(closedForTheDay: ClosedForTheDay) =
    div(cls := "daily-hours")(
      div(cls := "day")(
        div(cls := "day-name")(
          renderDayName(closedForTheDay.dayOfWeek),
        ),
      ),
      div(cls := "hours")(
        " *CLOSED* ",
      ),
    )

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
//          if (carryOutStatus == Open || deliveryStatus == Open)
//            div(location.name + "Open now!")
//          else
          div(
            restaurantWithStatus.location.humanFriendlyName,
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

  def activateModal(targetName: String): Unit =
    org.scalajs.dom.document.body
      .querySelector(targetName)
      .classList
      .add("is-active")

  def modalContentElementNameTyped(location: Name, routeName: Name) =
    data("schedule-modal") := modalContentElementName(location,
                                                      routeName)

  def modalContentElementName(location: Name, routeName: Name) =
    "modal_content_" + routeName.elementName + "_" + location.elementName

  def renderExternalAction(externalAction: ExternalAction) =
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
      restaurantGroup.allRestaurants.map {
        case restaurant: RelationshipMoment => {
          TagsOnlyLocal.createBusTimeElement(restaurant)
        }
      },
    )

  def renderExternalActions(
    externalActions: ExternalActionCollection,
  ) =
    renderExternalAction(
      externalActions.primary,
    ) +: externalActions.others.map(
      externalAction => renderExternalAction(externalAction),
    )

  def svgIconForAlarm(name: String,
                      classes: String,
                      busTime: BusTime) =
    img(
      cls := "glyphicon " + classes,
      src := s"/glyphicons/svg/individual-svg/$name",
      alt := "Thanks for riding the bus!",
      data("lossless-value") := busTime.toString,
      verticalAlign := "middle",
    )

  def svgIcon(name: String) =
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
