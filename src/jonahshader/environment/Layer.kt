package jonahshader.environment

import java.awt.Color

/*
A 2D array of booleans that can be read and written to.
Reading is safe, meaning that any coordinate can be read without throwing and error.
Anything out of bounds will be false.
Writing is not safe. Must be in bounds.
 */
class Layer(val width: Int, val height: Int, val layerColor: Color) {
    private var grid = arrayOf<Array<Boolean>>()

    init {
        // fill grid with proper array dimensions
        for (y in 0 until height) {
            var array = arrayOf<Boolean>()
            for (x in 0 until width) {
                array += false
            }
            grid += array
        }
    }

    fun readSpace(x: Int, y: Int, outOfBoundsValue: Boolean): Boolean {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return outOfBoundsValue
        return grid[y][x]
    }

    fun writeSpace(x: Int, y: Int, value: Boolean) {
        grid[y][x] = value
    }
}