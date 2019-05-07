package turfkt

import geojsonkt.*
import turfkt.*

fun Position.midpoint(to: Position): Position {
    val dist = distance(to)
    val heading = bearing(to)

    return destination(dist/2, heading)
}

fun Point.midpoint(to: Position): Position = coordinate.midpoint(to)

// I think this would need the index of the starting coordinate to use
fun Feature<Point>.midpoint(to: Position): Position = geometry.coordinate.midpoint(to)