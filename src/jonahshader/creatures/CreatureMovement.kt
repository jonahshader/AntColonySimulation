package jonahshader.creatures

import jonahshader.environment.Layer
import kotlin.math.absoluteValue

open class CreatureMovement(var x: Int, var y: Int, private val collisionLayer: Layer) {
    var energy = 0

    /**
     * exchanges energy between this and otherCreature
     */
    fun exchangeEnergy(amount: Int, otherCreature: CreatureMovement) {
        energy += amount
        otherCreature.energy -= amount
        if (energy < 0) {
            otherCreature.energy += energy
            energy = 0
        } else if (otherCreature.energy < 0) {
            energy += otherCreature.energy
            otherCreature.energy = 0
        }
    }

    /**
     * attempt moving in a direction
     * can only move up, down, left, or right (not diagonal)
     * can only move one unit at a time
     * moving takes one energy unit
     * returns true if successful
     */
    fun tryMove(deltaX: Int, deltaY: Int) : Boolean {
        if (deltaX == 0 || deltaY == 0) {
            if (deltaX.absoluteValue == 1 || deltaY.absoluteValue == 1) {
                if (!collisionLayer.readSpace(x + deltaX, y + deltaY, true)) {
                    if (energy > 0) {
                        energy--
                        x += deltaX
                        y += deltaY
                        return true
                    }
                }
            }
        }
        return false
    }
}