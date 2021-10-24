package dev.kobalt.game.alchemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class ScoreText : GameObject() {

    var enabled = true
    var texture: FontTexture? = null
    var value: Int = 0

    var counterAnimationPosition = 0f

    override fun draw(batch: SpriteBatch) {
        if (value > counterAnimationPosition) {
            counterAnimationPosition = (counterAnimationPosition + Gdx.graphics.deltaTime * 50f)
            if (value < counterAnimationPosition) { counterAnimationPosition = value.toFloat()}
        } else if(value < counterAnimationPosition) {
            counterAnimationPosition = (counterAnimationPosition - Gdx.graphics.deltaTime * 50f)
            if (value > counterAnimationPosition) { counterAnimationPosition = value.toFloat()}
        }
        if (enabled) {
            texture!!.draw(batch, counterAnimationPosition.toInt().toString(), x, y)
        }
    }

}