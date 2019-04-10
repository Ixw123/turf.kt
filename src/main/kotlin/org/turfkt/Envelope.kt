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
import geojsonkt.BBox

private fun envelope(vararg g: Geojson): Polygon{
    return when (geometry) {
        g.toPolygon()
    }
}

fun Geometry.envelope() {
    return when (geometry) {
        is Polygon -> geometry.envelope()
        is MultiPolygon -> geometry.envelope()
        is Point -> return 0.0
        is MultiPoint -> return 0.0
        is LineString -> return -> geometry.envelope()
        is MultiLineString -> return -> geometry.envelope()
        else -> throw UnsupportedOperationException("Can not calculate area of unrecognized Geometry type: ${this::class.java.name}")
        }
    }
}

fun Geometry.envelope(): Polygon = envelope(this)
