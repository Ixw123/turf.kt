package turfkt

import geojsonkt.*
import turfkt.*

fun Position.midpoint(to: Position): Position {
    val dist = this.distance(to)
    val heading = this.bearing(to)
    val units = "" // not sure what to have this default to but i noticed it was not used at all in this instance

    return this.destination(dist/2, heading, units)
}

fun Point.midpoint(to: Position): Position {
    val dist = this.distance(to)
    val heading = this.bearing(to)
    val units = ""

    return this.destination(dist/2, heading, units)
}
// Not sure if you meant a feature of points or not but i think that is all that is implemented
fun Feature<Point>.midpoint(to: Position): Position {
    val dist = this.distance(to)
    val heading = this.bearing(to)
    val units = ""

    return this.destination(dist/2, heading, units)
}
