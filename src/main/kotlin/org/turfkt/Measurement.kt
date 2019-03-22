package org.turfkt

import geojsonkt.Geometry
import geojsonkt.Feature
import geojsonkt.FeatureCollection
import geojsonkt.Polygon
import geojsonkt.MultiPolygon
import geojsonkt.Point
import geojsonkt.MultiPoint
import geojsonkt.LineString
import geojsonkt.MultiLineString
//import geojsonkt.Position

import kotlin.math.sin
import kotlin.math.abs

private fun calculateArea( geom: Geometry): Number{
    var total = 0.0
    var i: Int
    when (geom) {
        is Polygon -> polygonArea(geom.coordinates)
        is MultiPolygon -> {
            for (i in 0..(geom.coordinates.map { it[0] }.size - 1)) {
                total += polygonArea(geom.get(i).coordinates)
            }
            return total
        }
        is Point -> return 0
        is MultiPoint -> return 0 
        is LineString -> return 0
        is MultiLineString -> return 0
        else -> throw UnsupportedOperationException("Can not calculate area of unrecognized Geometry type: ${geom::class.java.name}")
    }
    return 0
}

fun polygonArea(coordinates: Any): Double {
    var total = 0.0
    if (coordinates && coordinates.size > 0) {
        total += abs(ringArea(coordinates[0]))
        for (i: Int in 1..(coordinates[0][0].size - 1)){
            total -= abs(ringArea(coordinates[i]))
        }
    }
    return total
}
fun ringArea(val coords: Array<Number>): Double {
    var p1: Double
    var p2: Double
    var p3: Double
    var lowerIndex: Int
    var middleIndex: Int
    var upperIndex: Int
    var i: Int
    var total = 0.0
    const coordinateLength = coords.size

    if (coordinateLength > 2) {
        for (i in 0..(coordinateLength - 1)) {
            if (i == coordinateLength - 2) { // i = N - 2
                lowerIndex = coordinateLength - 2
                middleIndex = coordinateLength - 1
                upperIndex = 0
            }
            else if (i == coordinateLength - 1) { // i = N - 1
                lowerIndex = coordinateLength - 1
                middleIndex = 0
                upperIndex = 1
            }
            else { // i = 0 to N - 3
                lowerIndex = i 
                middleIndex = i + 1
                upperIndex = i + 2
            }
            p1 = T[lowerIndex]
            p2 = T[middleIndex]
            p3 = T[upperIndex]
            total += (p3[0].toRadians() - p1[0].toRadians()) * sin(p2[1].toRadians())
        }   
        total *= (EARTH_RADIUS * EARTH_RADIUS / 2)
    } 
    return total
}
//private fun area()
fun Geometry.area(geojson: Any) { return geojson.fold(geojson) { return value + calculateArea(geom) }
fun Feature.area(geojson: Any) = Geometry.area(geojson)

fun FeatureCollection.area(geojson: Any): Double {
    var total = 0.0
    for (feature in geojson) {
        total += Feature.area(feature)
    }
    return total
}