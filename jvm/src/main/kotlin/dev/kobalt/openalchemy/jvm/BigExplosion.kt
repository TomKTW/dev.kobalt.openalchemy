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

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class BigExplosion : dev.kobalt.openalchemy.jvm.GameObject() {

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