package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class FontTexture(
    val texture: Texture,
    val offsets: FontOffsetMap,
    val blankOffset: Int
) {

    val textureRegions = offsets.mapValues { (_, value) ->
        TextureRegion(texture, value.x.toInt(), value.y.toInt(), value.width.toInt(), value.height.toInt())
    }.toMap()

    fun getWidth(text: String): Int {
        var currentWidth = 0
        text.forEach {
            textureRegions[it]?.let {
                currentWidth += it.regionWidth
            } ?: run {
                currentWidth += blankOffset
            }
        }
        return currentWidth
    }

    fun draw(batch: SpriteBatch, text: String, x: Float, y: Float) {
        var currentOffset = 0
        val width = getWidth(text)
        text.forEach { char ->
            textureRegions[char]?.let {
                // charset[char]!!.second //it.regionWidth // Draw from x,y position.
                // Draw text aligned to middle.
                batch.draw(it,  (x + currentOffset) - (width / 2), y)
                currentOffset += it.regionWidth
            } ?: run {
                currentOffset += blankOffset
            }
        }
    }

}