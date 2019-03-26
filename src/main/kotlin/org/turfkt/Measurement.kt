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
import geojsonkt.Position

import kotlin.math.sin
import kotlin.math.abs

// For testing purposes
//val EARTHRADIUS = 6378137

private fun calculateArea( geom: Geometry): Double{
    var total = 0.0
    //var i: Int
    when (geom) {
        is Polygon -> { 
            //val size = geom.coordinates.size
            return polygonArea(geom.coordinates)
        }
        is MultiPolygon -> {
            //val size = geom.coordinates.size
            for (i in 0..(geom.coordinates.size - 1)) {
                total += polygonArea(geom.coordinates[i])
            }
            return total
        }
        is Point -> return 0.0
        is MultiPoint -> return 0.0
        is LineString -> return 0.0
        is MultiLineString -> return 0.0
        else -> throw UnsupportedOperationException("Can not calculate area of unrecognized Geometry type: ${geom::class.java.name}")
    }
}

private fun polygonArea(coordinates: Array<Array<Position>>): Double {
    var total = 0.0
    //val coordSize = coordinates.size
    if (coordinates.isNotEmpty()) {
        total += abs(ringArea(coordinates[0]))
        for (i: Int in 1..(coordinates.size - 1)){
            total -= abs(ringArea(coordinates[i]))
        }
    }
    return total
}
private fun ringArea(coords: Array<Position>): Double {
    var p1: Position
    var p2: Position
    var p3: Position
    var lowerIndex: Int
    var middleIndex: Int
    var upperIndex: Int
    //var i: Int
    var total = 0.0

    val coordinateLength = coords.size

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
            p1 = coords[lowerIndex]
            p2 = coords[middleIndex]
            p3 = coords[upperIndex]
            total += (p3[0].toRadians() - p1[0].toRadians()) * sin(p2[1].toRadians())
        }   
        total *= (EARTH_RADIUS * EARTH_RADIUS / 2)
    } 
    return total
}
//private fun area()
fun Geometry.area() = calculateArea(this)
fun Polygon.area() = calculateArea(this)
fun MultiPolygon.area() = calculateArea(this)
fun Feature<*>.area(): Double {
    return when (geometry) {
        is Polygon -> geometry.area()
        is MultiPolygon -> geometry.area()
        is Point -> return 0.0
        is MultiPoint -> return 0.0
        is LineString -> return 0.0
        is MultiLineString -> return 0.0
        else -> throw UnsupportedOperationException("Can not calculate area of unrecognized Geometry type: ${this::class.java.name}")
        }
    }

fun FeatureCollection.area(): Double {
    var total = 0.0
    for (feature in features) {
        total += feature.area()
    }
    return total
}

//fun main() {
//    val polygon1: Array<Array<Position>>
//    var position1 = Position(doubleArrayOf(125.0, -15.0))
//    var position2 = Position(doubleArrayOf(113.0, -22.0))
//    var position3 = Position(doubleArrayOf(154.0, -27.0))
//    var position4 = Position(doubleArrayOf(144.0, -15.0))
//    var position5 = Position(doubleArrayOf(125.0, -15.0))
//    polygon1 = arrayOf(arrayOf(position1, position2, position3, position4, position5))
//    val poly1 = Polygon(polygon1)
//    val area1 = poly1.area()
//    println("poly1 area: $area1")
//}