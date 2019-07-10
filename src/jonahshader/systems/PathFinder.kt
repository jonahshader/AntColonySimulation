package jonahshader.systems

import jonahshader.environment.Layer

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

class Path {
    private var pathData = arrayOf<Direction>()

    fun getDirections() : Array<Direction> = pathData
    fun addDirection(direction: Direction) {
        pathData += direction
    }
}

fun offsetPointWithDirection(point: PointInt, direction: Direction) {
    when (direction) {
        Direction.UP -> point.y++
        Direction.DOWN -> point.y--
        Direction.LEFT -> point.x--
        Direction.RIGHT -> point.x++
    }
}

fun getPath(start: PointInt, end: PointInt, collisionLayer: Layer) : Path {
    val startSearch = Array2D<Int>(collisionLayer.width, collisionLayer.height)
    val endSearch = Array2D<Int>(collisionLayer.width, collisionLayer.height)


}