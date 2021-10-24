package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class BoardBackground : GameObject() {

    var texture: Texture? = null

    override fun draw(batch: SpriteBatch) {
        batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
    }

}