package jonahshader.creatures.ants

import jonahshader.creatures.CreatureMovement
import jonahshader.creatures.PhysicalCreature
import jonahshader.environment.Layer
import processing.core.PGraphics

class ClimberAnt(x: Int, y: Int, private val collisionLayer: Layer) : CreatureMovement(x, y, collisionLayer),
    PhysicalCreature {
    private var hasDirtBlock = false

    init {
        energy = 100000000000
    }

    override fun run() {
        if (!collisionLayer.readSpace(x - 1, y, true) &&
            !collisionLayer.readSpace(x + 1, y, true) &&
            !collisionLayer.readSpace(x, y - 1, true) &&
            !collisionLayer.readSpace(x - 1, y - 1, true) &&
            !collisionLayer.readSpace(x + 1, y - 1, true)) {
            y--
        }


        var horizontal = Math.random() > 0.5
        var flip = Math.random() > 0.5

        var changeX = if (horizontal) if (flip)  1 else -1 else 0
        var changeY = if (!horizontal) if (flip) 1 else -1 else 0


        if (!hasDirtBlock) {
            if (collisionLayer.readSpace(x + changeX, y + changeY, false)) {
                collisionLayer.writeSpace(x + changeX, y + changeY, false)
                hasDirtBlock = true
            }
        } else {
            if (!collisionLayer.readSpace(x + changeX, y + changeY, true)) {
                collisionLayer.writeSpace(x + changeX, y + changeY, true)
                hasDirtBlock = false
            }
        }

        horizontal = Math.random() < 0.5
        flip = Math.random() < 0.5

        changeX = if (horizontal) if (flip)  1 else -1 else 0
        changeY =  if (Math.random() < 0.25) 1 else 0

        tryMove(changeX, changeY)

    }

    override fun draw(graphics: PGraphics) {
        graphics.stroke(0f, 200f, 0f)
        graphics.strokeWeight(1f)
        graphics.point(x.toFloat(), y.toFloat())
    }
}