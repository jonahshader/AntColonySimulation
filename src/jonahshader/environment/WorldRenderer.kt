package jonahshader.environment

import jonahshader.creatures.PhysicalCreature
import processing.core.PApplet
import processing.core.PGraphics

class WorldRenderer(private val world: World, private val graphics: PApplet) {
    var image: PGraphics = graphics.createGraphics(world.width, world.height)

    fun renderToScreen() {
        // draw to image buffer
        image.beginDraw()
        renderLayerToImage(world.dirtLayer, image)
        renderCreaturesToImage(world.creatures, image)
        image.endDraw()

        // render image buffer to screen
        graphics.image(image, 0f, 0f, graphics.width.toFloat(), graphics.height.toFloat())
    }

    private fun renderCreaturesToImage(creatures: Array<PhysicalCreature>, image: PGraphics) {
        creatures.forEach {
            it.draw(image)
        }
    }

    private fun renderLayerToImage(layer: Layer, image: PGraphics){
        // set color
        image.stroke(layer.layerColor.rgb)
        image.strokeWeight(1f)
        image.clear()

        for (x in 0 until layer.width)
            for (y in 0 until layer.height)
                if (layer.readSpace(x, y, false)) {
                    image.point(x.toFloat(), y.toFloat())
                }
    }
}

