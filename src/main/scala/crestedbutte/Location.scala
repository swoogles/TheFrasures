package crestedbutte

object Location extends Enumeration {

  protected case class Val(name: String,
                           altName: String = "",
                           gpsCoordinates: GpsCoordinates =
                             GpsCoordinates(0, 0))
      extends super.Val(name) {

    val elementName =
      name.map(letter => if (letter.isLetter) letter else "_")
  }
  import scala.language.implicitConversions

  implicit def valueToStopLocationVal(x: Value): Val =
    x.asInstanceOf[Val]

  type StopLocation = Value

  // TODO Check ordering of all coordinates
  val OldTownHall: Val =
    Val("Old Town Hall",
        "(Malardi Theater)",
        GpsCoordinates(38.869538, -106.987547))

  val Clarks: Val = Val("6th/Belleview",
                        "(Clarks Grocery)",
                        GpsCoordinates(38.866970, -106.981499)) //

  val FourWayUphill: Val = Val(
    "4-way",
    "(To Mountain)",
    GpsCoordinates(38.870355, -106.980905)
  ) // gps
  val TeocalliUphill: Val = Val(
    "Teocalli",
    "(To Mountain)",
    GpsCoordinates(38.872718, -106.980830)
  ) //
  val MountaineerSquare: Val = Val(
    "Mountaineer Square",
    "(CBMR)",
    GpsCoordinates(38.900902, -106.966650)
  ) //  // This is rough. Maps seems to be off...
  val TeocalliDownhill: Val = Val(
    "Teocalli",
    "(To Downtown)",
    GpsCoordinates(38.872726, -106.981037)
  ) //
  val FourwayDownhill: Val = Val(
    "4-way",
    "(To Downtown)",
    GpsCoordinates(38.869944, -106.981503)
  ) //

  // Condo loop entries
  val ThreeSeasons: Val =
    Val("Three Seasons")

  val MountainSunrise: Val =
    Val("Mountain Sunrise")

  val UpperChateaux: Val =
    Val("Upper Chateaux")

  val LowerChateaux: Val = Val("Lower Chateaux / Marcellina")

  // RTA stops.
  val GunnisonCommunitySchools: Val = Val(
    "Gunnison Community Schools"
  )

  val EleventhAndVirginia: Val = Val("Eleventh & Virgina")

  val Safeway: Val =
    Val("Safeway", "(Spruce & Highway 50)")

  val TellerAndHighwayFifty: Val =
    Val("Teller & Highway 50", "")

  val Western: Val =
    Val("Western", "Colorado & Ohio")

  val DenverAndHighwayOneThirtyFive: Val =
    Val("Denver & Highway 135", "(City Market)")

  val SpencerAndHighwayOneThirtyFive: Val =
    Val("Spencer & Highway 135", "(Walmart)")

  val TallTexan: Val =
    Val("TallTexan", "(Flag Stop)")

  val OhioCreek: Val =
    Val("OhioCreek", "(Flag Stop)")

  val Almont: Val =
    Val("Almont", "(Flag Stop)")

  val CBSouth: Val =
    Val("CB South", "(Red Mtn Park)")

  val Riverland: Val =
    Val("Riverland", "(Flag Stop)")

  val BrushCreek: Val =
    Val("Brush Creek", "(Flag Stop)")

  val Riverbend: Val =
    Val("Riverbend", "(Flag Stop)")

  // BEGIN Condo loop stops
  val Whetstone: Val =
    Val("Whetstone")

  val ColumbineCondo: Val =
    Val("ColumbineCondo")

  val CinnamonMtn: Val =
    Val("Cinnamon Mtn / Gothic")

  val MtCbTownHall: Val =
    Val("Mt CB Town Hall")

  val UpperParadiseRoad: Val =
    Val("UpperParadiseRoad")

  val LowerParadiseRoad: Val =
    Val("LowerParadiseRoad")

  val EaglesNestCondos: Val =
    Val("EaglesNestCondos")
  // END Condo loop stops

}
