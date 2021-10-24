package dev.kobalt.game.alchemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.lang.Float.min

class OverText : GameObject() {

    var texture: FontTexture? = null
    var value: Int = 0
    var enabled: Boolean = true

    val max = 30f
    var progress = max

    override fun draw(batch: SpriteBatch) {
        if (enabled) {
            progress = min(max, progress + (max * Gdx.graphics.deltaTime))
            batch.setColor(1.0f, 1.0f, 1.0f, 3f - (progress / (max / 3)) )
            texture!!.draw(batch, value.toString(), x, y + (progress))
            batch.setColor(Color.WHITE)
        }
    }

}