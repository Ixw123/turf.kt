package geojsonkt

import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.Double.Companion.NEGATIVE_INFINITY

//@Suppress("DIVISION_BY_ZERO")
//const val positiveInfinity: Double = 1.0 / 0.0
//@Suppress("DIVISION_BY_ZERO")
//const val negativeINfinity: Double = -1.0 / 0.0

fun BBox(swlon: Double, swlat: Double, nelon: Double, nelat: Double) =
        BBox(doubleArrayOf(swlon, swlat, nelon, nelat))

fun BBox(swlon: Double, swlat: Double, swelevation: Double,
         nelon: Double, nelat: Double, neelevation: Double) =
        BBox(doubleArrayOf(swlon, swlat, swelevation, nelon, nelat, neelevation))

fun BBox(sw: Position, ne: Position): BBox {
    if(sw.elevation.isNaN() || ne.elevation.isNaN())
        return BBox(doubleArrayOf(sw.longitude, sw.latitude, ne.longitude, ne.latitude))

    return BBox(doubleArrayOf(sw.longitude, sw.latitude, sw.elevation, ne.longitude, ne.latitude, ne.elevation))
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class BBox(private val values: DoubleArray) {
    companion object

    val southWest: Position
        get() = when(values.size) {
            4 -> Position(values[0], values[1])
            6 -> Position(values[0], values[1], values[2])
            else -> throw IllegalStateException("Unexpected number of values, 4 or 6 expected but ${values.size} were found.")
        }

    val northEast: Position
        get()  = when(values.size) {
            4 -> Position(values[2], values[3])
            6 -> Position(values[3], values[4], values[5])
            else -> throw IllegalStateException("Unexpected number of values, 4 or 6 expected but ${values.size} were found.")
        }
}

/**
 * Returns a [Polygon] equivalent to this [BBox].
 */
fun BBox.toPolygon(): Polygon {
    val north = northEast.latitude
    val east = northEast.longitude
    val west = southWest.longitude
    val south = southWest.latitude
    val northWest = Position(west, north)
    val southEast = Position(east, south)

    return Polygon(arrayOf(arrayOf(northWest, southWest, southEast, northEast, northWest)))
}

fun GeoJson.bbox() = {
    when(this) {
        is Point -> throw UnsupportedOperationException("Can not calculate BBox of Point Geometry type: ${this::class.java.name}")
        is LineString -> bbox(this)
        is Polygon -> bbox(this)
        is MultiPoint -> throw UnsupportedOperationException("Can not calculate BBox of MultiPoint Geometry type: ${this::class.java.name}")
        is MultiLineString -> bbox(this)
        is MultiPolygon -> bbox(this)
        is GeometryCollection -> bbox(this)
        else -> throw UnsupportedOperationException("Can not calculate BBox of unrecognized Geometry type: ${this::class.java.name}")
    }
}
private fun bbox(gc: GeometryCollection): BBox {
    val result = BBox(doubleArrayOf(POSITIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY, NEGATIVE_INFINITY))
    val coords = coords(gc)
    for(g in gc.geometries) {
        for(p in coords) {
            if (result.southWest[0] > p.x) { result.southWest[0] = p.x }
            if (result.southWest[1] > coords[1]) { result.southWest[1] = coords[1] }
            if (result.northEast[0] < coords[0]) { result[2] = coords[0] }
            if (result.northEast[1] < coords[1]) { result[3] = coords[1] }
        }
    }

    return result
}

private fun bbox(geometry: Geometry): BBox {

    val result = BBox(doubleArrayOf(POSITIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY, NEGATIVE_INFINITY))
    for (p in geometry.) {
        if (result.southWest[0] > p.x) {
            result.southWest[0] = p.x
        }
        if (result.southWest[1] > p.y) {
            result[1] = coord[1]
        }
        if (result.northEast[0] < p.x) {
            result[2] = coord[0]
        }
        if (result.northEast[1] < p.y) {
            result[3] = coord[1]
        }
    }
    return result;
}

fun Array<Position>.bbox(): BBox {
    var result = BBox(doubleArrayOf(POSITIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY, NEGATIVE_INFINITY))
    for(p in this) {
        if (result.southWest.get(0) > p.x) {
            result.southWest.get(0) = p.x
        }
        if (result.southWest[1] > p.y) {
            result[1] = coord[1]
        }
        if (result.northEast[0] < p.x) {
            result[2] = coord[0]
        }
        if (result.northEast[1] < p.y) {
            result[3] = coord[1]
        }
    }

    return Position(x / size, y / size)
}

private fun coords(g: Geometry): Sequence<Position> = when(g) {
    is Point -> arrayOf(g.coordinate).asSequence()
    is LineString -> g.coordinates.asSequence()
    is Polygon -> g.coordinates.first().asSequence()
    is MultiPoint -> g.coordinates.asSequence()
    is MultiLineString -> g.coordinates.flatten().asSequence()
    is MultiPolygon -> g.coordinates.flatMap { it.first().toList() }.asSequence()
    is GeometryCollection -> g.geometries.asSequence().map { coords(it) }.flatten()
    else -> emptySequence()
}
