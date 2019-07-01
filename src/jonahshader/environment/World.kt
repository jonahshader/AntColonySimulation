package jonahshader.environment

import jonahshader.creatures.ants.VerticalDiggerAnt
import jonahshader.creatures.ants.HorizontalDiggerAnt
import jonahshader.creatures.PhysicalCreature
import jonahshader.creatures.ants.ClimberAnt
import java.awt.Color

class World(val width: Int, val height: Int) {
    val dirtLayer = Layer(width, height, Color(120, 80, 20))

    var creatures = arrayOf<PhysicalCreature>()

    init {
        for (i in 1..75)
            creatures += VerticalDiggerAnt(
                (Math.random() * width).toInt(),
                dirtLayer.height - 1,
                dirtLayer
            )
        for (i in 1..25)
            creatures += HorizontalDiggerAnt(
                (Math.random() * width).toInt(),
                dirtLayer.height - 1,
                dirtLayer
            )
        for (i in 1..50)
            creatures += ClimberAnt(
                (Math.random() * width).toInt(),
                dirtLayer.height - 1,
                dirtLayer
            )
    }

    fun run() {
        creatures.forEach(PhysicalCreature::run)
    }
}