/*
 * dev.kobalt.openalchemy
 * Copyright (C) 2022 Tom.K
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.kobalt.openalchemy.jvm

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