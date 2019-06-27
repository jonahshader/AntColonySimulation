package jonahshader.creatures.patterns

import jonahshader.environment.TileType

// this class is similar tot Layer, but it stores a 2d array of the TileType enum.
// a pattern is used to describe a structure the creatures can construct.

class Pattern(val width: Int, val height: Int) {
    var patternData = arrayOf<Array<TileType>>()

    init {
        for (y in 0 until height) {
            var array = arrayOf<TileType>()
            for (x in 0 until width) {
                array += TileType.NOTHING
            }
            patternData += array
        }
    }

    // does not prevent out of bounds writing
    fun readSpace(x: Int, y: Int) : TileType {
        return patternData[y][x]
    }

    // does not prevent out of bounds reading
    fun writeSpace(x: Int, y: Int, value: TileType) {
        patternData[y][x] = value
    }
}