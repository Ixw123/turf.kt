package org.turfkt

import kotlin.math.sin

// NOTE: Change RADIUS => earthRadius
const RADIUS = 6378137

fun getPolygonArea(out T): Double {
    var total = 0.0
    if (T && T.size > 0) {

    }
}
fun ringArea(out T): Double {
    var p1: Double
    var p2: Double
    var p3: Double
    var lowerIndex: Int
    var middleIndex: Int
    var upperIndex: Int
    var i: Int
    var total = 0.0
    const coordinateLength = T.size

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
        total *= (RADIUS * RADIUS / 2)
    } 
    return total
}

fun Feature.area(): Double {
} 
fun FeatureCollections.area(): Double {
    var total = 0.0

}