# turf.kt

### Advanced, cross platform, geospatial analysis for Kotlin.

turf.kt is a multi-platform Kotlin library for spatial analysis based on [Turf](https://turfjs.org) .

turf.kt uses GeoJSON data classes provided by the geojson.kt project. turf.kt expects the data to be standard <a href='http://en.wikipedia.org/wiki/World_Geodetic_System'>WGS84</a> longitude, latitude coordinates. Check out <a href='http://geojson.io/#id=gist:anonymous/844f013aae8354eb889c&map=12/38.8955/-77.0135'>geojson.io</a> for a tool to easily create this data.


## Progress

Below's an on going list of the Turf functions which currently exist within turf.kt:
Last updated 02/25/19
  
### Measurement
- [x] along
- [ ] area
- [ ] bbox
- [ ] bboxPolygon
- [x] bearing
- [ ] center
- [ ] centerOfMass
- [ ] centroid
- [x] destination
- [x] distance
- [ ] envelope
- [ ] length
- [ ] midpoint
- [ ] pointOnFeature
- [ ] polygonTangents
- [ ] pointToLineDistance
- [ ] rhumbBearing
- [ ] rhumbDestination
- [ ] rhumbDistance
- [ ] square
- [ ] greatCircle

### Coordinate Mutation
- [ ] cleanCoords
- [ ] flip
- [ ] rewind
- [ ] round
- [ ] truncate

### Transformation
- [ ] bboxClip
- [ ] bezierSpline
- [ ] buffer
- [ ] circle
- [ ] clone
- [ ] concave
- [ ] convex
- [ ] difference
- [ ] dissolve
- [ ] intersect
- [ ] lineOffset
- [ ] simplify
- [ ] tesselate
- [ ] transformRotate
- [ ] transformTranslate
- [ ] transformScale
- [ ] union
- [ ] voronoi

### Feature Conversion
- [ ] combine
- [ ] explode
- [ ] flatten
- [ ] lineToPolygon
- [ ] polygonize
- [ ] polygonToLine

### Misc
- [ ] kinks
- [ ] lineArc
- [ ] lineChunk
- [ ] lineIntersect
- [ ] lineOverlap
- [ ] lineSegment
- [ ] lineSlice
- [ ] lineSliceAlong
- [ ] lineSplit
- [ ] mask
- [ ] nearestPointOnLine
- [ ] sector
- [ ] shortestPath
- [ ] unkinkPolygon

### Helper
See geojson.kt.

### Random
- [ ] randomPosition
- [ ] randomPoint
- [ ] randomLineString
- [ ] randomPolygon

### Data
- [ ] sample

### Interpolation
- [ ] interpolate
- [ ] isobands
- [ ] isolines
- [ ] planepoint
- [ ] tin

### Joins
- [ ] pointsWithinPolygon
- [ ] tag

### Grids
- [ ] hexGrid
- [ ] pointGrid
- [ ] squareGrid
- [ ] triangleGrid

### Classification
- [ ] nearestPoint

### Aggregation
- [ ] collect
- [ ] clustersDbScan
- [ ] clustersKMeans

### Meta
- [ ] coordAll
- [ ] coordEach
- [ ] coordReduce
- [ ] featureEach
- [ ] featureReduce
- [ ] flattenEach
- [ ] flattenReduce
- [ ] getCoord
- [ ] getCoords
- [ ] getGeom
- [ ] ~~getType~~ Use `Geometry:type`
- [ ] geomEach
- [ ] geomReduce
- [ ] propEach
- [ ] propReduce
- [ ] segmentEach
- [ ] segmentReduce
- [ ] getCluster
- [ ] clusterEach
- [ ] clusterReduce

### Assertions
See geojson.kt

### Booleans
- [ ] booleanClockwise
- [ ] booleanContains
- [ ] booleanCrosses
- [ ] booleanDisjoint
- [ ] booleanEqual
- [ ] booleanOverlap
- [ ] booleanParallel
- [ ] booleanPointInPolygon
- [ ] booleanPointOnLine
- [ ] booleanWithin

### Unit Conversion 
- [ ] bearingToAzimuth
- [ ] convertArea
- [ ] convertLength
- [x] degreesToRadians -> `Double.toRadians()`
- [x] lengthToRadians
- [ ] lengthToDegrees
- [x] radiansToLength
- [x] radiansToDegrees -> `Double.toDegrees()`
- [ ] toMercator
- [ ] toWgs84
