package dev.kobalt.game.alchemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class BigExplosion : GameObject() {

    var explosion: Texture? = null
    val explosionRows = 1
    val explosionColumns = 15
    val explosionWidth = 71
    val explosionHeight = 100
    var explosionSequence = Array<TextureRegion>(explosionRows * explosionColumns).also { array ->
        repeat(explosionRows * explosionColumns) {
            array.add(TextureRegion())
        }
    }
    var explosionAnimation = Animation(1f / 30f, explosionSequence, Animation.PlayMode.NORMAL)
    var explosionState = 30f
    inline var texture: Texture?
        get() = explosion
        set(value) {
            explosion = value; explosionSequence.forEachIndexed { index, sequence ->
                val row = index / explosionColumns
                val column = index % explosionColumns
                sequence.texture = value
                sequence.setRegion(column * explosionWidth, row * explosionHeight, explosionWidth, explosionHeight)
            }
        }

    var enabled: Boolean = true

    override fun draw(batch: SpriteBatch) {
        if (enabled) {
            explosionState += Gdx.graphics.deltaTime
            batch.draw(explosionAnimation.getKeyFrame(explosionState), x, y)
        }
    }

}