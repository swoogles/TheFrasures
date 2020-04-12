package crestedbutte

import crestedbutte.dom.Bulma
import crestedbutte.time.{BusDuration, BusTime}
import org.scalajs.dom.html.{Anchor, Div}
import scalatags.JsDom

object TagsOnlyLocal {
  import scalatags.JsDom.all._

  def overallPageLayout(pageMode: AppMode.Value,
                        allComponentData: Seq[ComponentData]) =
    div(id := "container")(
      Bulma.menu(
        allComponentData.map { componentData =>
          a(
            cls := "route",
            data("route") := componentData.componentName,
          )(componentData.namedRoute.routeName.userFriendlyName)
        },
        "Restaurants",
      ),
      allComponentData.map(
        singleComponentData =>
          busScheduleDiv(singleComponentData.componentName),
      ),
      if (pageMode == AppMode.Development) {
        div(
          button(id := ElementNames.Notifications.requestPermission,
                 cls := "button")(
            "Request Notifications Permission",
          ),
          button(
            id := ElementNames.Notifications.submitMessageToServiceWorker,
            cls := "button",
          )(
            "SubmitMessage to SW",
          ),
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
      button(
        onclick :=
          s"window.location.href = '${website.url}';",
        cls := "button",
      )(
        img(
          cls := "glyphicon",
          src := "/glyphicons/svg/individual-svg/glyphicons-basic-417-globe.svg",
          alt := "Call Late Night Shuttle!",
        ),
        website.name,
      ),
    )

  //  <a href="tel:123-456-7890">123-456-7890</a>
  def safeRideLink(
    safeRideRecommendation: PhoneNumber,
  ): JsDom.TypedTag[Div] =
    div(cls := "call-button")(
      button(
        onclick :=
          s"window.location.href = 'tel:${safeRideRecommendation.number}';",
        cls := "button",
      )(
        img(
          cls := "glyphicon",
          src := "/glyphicons/svg/individual-svg/glyphicons-basic-465-call.svg",
          alt := "Call Late Night Shuttle!",
        ),
        safeRideRecommendation.name,
      ),
    )

  def phoneLink(
    phoneNumber: PhoneNumber,
  ): JsDom.TypedTag[Anchor] =
    a(href := s"tel:${phoneNumber.number}", cls := "link")(
      phoneNumber.name,
    )

  def renderWaitTime(duration: BusDuration) =
    if (duration.toMinutes == 0)
      "Leaving!"
    else
      duration.toMinutes + " min."

  def createBusTimeElement(
    location: Location.Value,
    content: JsDom.TypedTag[Div],
    website: Website,
    facebookPage: Website,
    /* TODO: waitDuration: Duration*/
  ): JsDom.TypedTag[Div] =
    Bulma.card(
      div(cls := "restaurant-information")(
        div(cls := "restaurant-name")(
          div(location.name),
        ),
        //        div(cls := "")(
        //          div(location.altName),
        //        ),
        div(cls := "restaurant-call")(
          content,
        ),
      ),
      List(
        renderWebsiteLink(website),
        renderWebsiteLink(facebookPage),
      ),
    )

  def activateModal(targetName: String): Unit =
    org.scalajs.dom.document.body
      .querySelector(targetName)
      .classList
      .add("is-active")

  def modalContentElementNameTyped(location: Location.Value,
                                   routeName: RouteName) =
    data("schedule-modal") := modalContentElementName(location,
                                                      routeName)

  def modalContentElementName(location: Location.Value,
                              routeName: RouteName) =
    "modal_content_" + routeName.name + "_" + location.elementName

  def structuredSetOfUpcomingArrivals(
    upcomingArrivalComponentData: UpcomingArrivalComponentData,
  ) =
    div(
      div(cls := "route-header")(
        span(cls := "route-header_name")(
          upcomingArrivalComponentData.routeName.userFriendlyName,
        ),
      ),
      upcomingArrivalComponentData.upcomingArrivalInfo.map {
        case RestaurantWithSchedule(
            location: Location.Value,
            times: Seq[BusTime],
            phoneNumber: PhoneNumber,
            website: Website,
            facebookPage: Website,
            ) => {
          TagsOnlyLocal.createBusTimeElement(
            location,
            safeRideLink(phoneNumber),
            website,
            facebookPage,
          )
        }
      },
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
      alt := "Thanks for riding the bus!",
    )
  /*
  glyphicons-basic-591-map-marker.svg
  glyphicons-basic-417-globe.svg
  glyphicons-basic-262-direction-empty.svg
  glyphicons-basic-581-directions.svg
  glyphicons-basic-697-directions-sign.svg

 */

}