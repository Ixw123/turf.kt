fun center<P = Properties>(
        geojson: AllGeoJSON,
options: {properties?: P, bbox?: BBox, id?: Id } = {}
): Feature<Point> {
    val ext = bbox(geojson);
    val x = (ext[0] + ext[2]) / 2;
    val y = (ext[1] + ext[3]) / 2;
    return point([x, y], options.properties, options);
}