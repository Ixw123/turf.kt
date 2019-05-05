import geojsonkt.*
import turfkt.centroid

fun GeoJson.centerOfMass(geojson: GeoJson): Position = when (this) {
        is Point -> coordinates
        is Polygon -> {
            val coords = coords(this)
            var centre = this.centroid()
            var sx = 0
            var sy = 0
            var sArea = 0
            var i: Int
            var pi: Double
            var pj: Double
            var xi: Double
            var xj: Double
            var yi: Double
            var yj: Double
            var a: Double
            var translation = centre
        }
        /*
        var coords = [];
        coordEach(geojson, function (coord) {
            coords.push(coord);
        });

        // First, we neutralize the feature (set it around coordinates [0,0]) to prevent rounding errors
        // We take any point to translate all the points around 0
        var centre = centroid(geojson, {properties: options.properties});
        var translation = centre.geometry.coordinates;
        var sx = 0;
        var sy = 0;
        var sArea = 0;
        var i, pi, pj, xi, xj, yi, yj, a;

        var neutralizedPoints = coords.map(function (point) {
            return [
                point[0] - translation[0],
                point[1] - translation[1]
            ];
        });

        for (i = 0; i < coords.length - 1; i++) {
        // pi is the current point
        pi = neutralizedPoints[i];
        xi = pi[0];
        yi = pi[1];

        // pj is the next point (pi+1)
        pj = neutralizedPoints[i + 1];
        xj = pj[0];
        yj = pj[1];

        // a is the common factor to compute the signed area and the final coordinates
        a = xi * yj - xj * yi;

        // sArea is the sum used to compute the signed area
        sArea += a;

        // sx and sy are the sums used to compute the final coordinates
        sx += (xi + xj) * a;
        sy += (yi + yj) * a;
    }

        // Shape has no area: fallback on turf.centroid
        if (sArea === 0) {
            return centre;
        } else {
            // Compute the signed area, and factorize 1/6A
            var area = sArea * 0.5;
            var areaFactor = 1 / (6 * area);

            // Compute the final coordinates, adding back the values that have been neutralized
            return point([
                translation[0] + areaFactor * sx,
                translation[1] + areaFactor * sy
            ], options.properties);
        }
        default:
        // Not a polygon: Compute the convex hull and work with that
        var hull = convex(geojson);

        if (hull) return centerOfMass(hull, {properties: options.properties});
        // Hull is empty: fallback on the centroid
        else return centroid(geojson, {properties: options.properties});
    }
}

         */

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