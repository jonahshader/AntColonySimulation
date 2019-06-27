package jonahshader

import jonahshader.environment.World
import jonahshader.environment.WorldRenderer
import jonahshader.environment.generateTerrain
import processing.core.PApplet

class App : PApplet() {
    private val tileSize = 32
    private val world = World(1920 / tileSize, 1080 / tileSize)
    private lateinit var renderer: WorldRenderer

    override fun settings() {
        size(world.width * tileSize, world.height * tileSize)
        noSmooth()
    }

    override fun setup() {
        renderer = WorldRenderer(world, this)
        generateTerrain(world.dirtLayer)
    }

    override fun draw() {
        background(255)
        scale(1f, -1f)
        translate(0f, -height.toFloat())
        world.run()
        renderer.renderToScreen()
    }

    override fun keyPressed() {
        save("screenshot.png")
    }
}

fun main() {
    PApplet.main("jonahshader.App")
}