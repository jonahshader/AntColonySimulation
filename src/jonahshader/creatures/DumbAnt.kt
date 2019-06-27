package jonahshader.creatures

import jonahshader.environment.Layer
import processing.core.PGraphics

class DumbAnt(x: Int, y: Int, private val collisionLayer: Layer) : CreatureMovement(x, y, collisionLayer), PhysicalCreature {
    private var hasDirtBlock = false

    init {
        energy = 10
    }

    override fun run() {
        tryMove(1, 0)
        if (!collisionLayer.readSpace(x, y - 1, true))
            y--

        if (!hasDirtBlock) {
            if (collisionLayer.readSpace(x, y - 1, false)) {
                collisionLayer.writeSpace(x, y - 1, false)
                hasDirtBlock = true
            }
        } else {
            if (!collisionLayer.readSpace(x, y + 1, true)) {
                collisionLayer.writeSpace(x, y + 1, true)
                hasDirtBlock = false
            }
        }
    }

    override fun draw(graphics: PGraphics) {
        graphics.stroke(0f, 0f, 0f)
        graphics.strokeWeight(1f)
        graphics.point(x.toFloat(), y.toFloat())
    }
}