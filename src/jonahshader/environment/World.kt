package jonahshader.environment

import jonahshader.creatures.DumbAnt
import jonahshader.creatures.PhysicalCreature
import java.awt.Color

class World(val width: Int, val height: Int) {
    val dirtLayer = Layer(width, height, Color(120, 80, 20))

    var creatures = arrayOf<PhysicalCreature>()

    init {
        for (i in 1..250)
            creatures += DumbAnt((Math.random() * width).toInt(), dirtLayer.height - 1, dirtLayer)
    }

    fun run() {
        creatures.forEach(PhysicalCreature::run)
    }
}