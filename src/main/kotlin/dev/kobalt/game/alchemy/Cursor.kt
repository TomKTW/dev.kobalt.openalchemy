package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

class Cursor {

    enum class State { Normal, Hover, Drag }

    var arrowTexture: Texture? = null
    var hoverTexture: Texture? = null
    var dragTexture: Texture? = null
    var position: Vector3 = Vector3()
    var hotspot: Vector2 = Vector2()
    var state: State = State.Normal

    fun draw(batch: SpriteBatch) {
        batch.draw(
            when (state) {
                State.Normal -> arrowTexture
                State.Hover -> hoverTexture
                State.Drag -> dragTexture
            },
            position.x - hotspot.x,
            position.y - hotspot.y
        )
    }

    fun update(camera: Camera, x: Float, y: Float) {
        position.x = x
        position.y = y
        camera.unproject(position)
        camera.update()
    }

}