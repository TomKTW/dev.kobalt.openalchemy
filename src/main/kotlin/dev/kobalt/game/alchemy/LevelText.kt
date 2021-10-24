package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class LevelText : GameObject() {

    var texture: FontTexture? = null
    var value: Int = 0
    var enabled = true

    override fun draw(batch: SpriteBatch) {
        if (enabled) texture!!.draw(batch, value.toString(), x, y)
    }

}

