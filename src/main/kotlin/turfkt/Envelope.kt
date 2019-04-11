package org.turfkt

import geojsonkt.*

fun envelope(g: Geometry): Polygon = when(g) {
    is Polygon, 
    is MultiPolygon,
    is LineString,
    is MultiLineString -> g.bbox!!.toPolygon()

    is Point,
    is MultiPoint -> throw UnsupportedOperationException("Can not BBox Point or Multipoint Geometry type: ${g::class.java.name}")

    else -> throw UnsupportedOperationException("Can not BBox unrecognized Geometry type: ${g::class.java.name}")
}

fun Geometry.envelope(): Polygon = envelope(this)
