package jonahshader.creatures.patterns

class Pattern(val width: Int, val height: Int) {
    var patternData = arrayOf<Array<Int>>()

    init {
        for (y in 0 until height) {
            var array = arrayOf<Int>()
            for (x in 0 until width) {
                array += 0
            }
            patternData += array
        }
    }
}