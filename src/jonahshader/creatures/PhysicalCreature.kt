package jonahshader.creatures

import processing.core.PGraphics

interface PhysicalCreature {
    fun run()
    fun draw(graphics: PGraphics) // assuming graphics.beginDraw() has already been called
}