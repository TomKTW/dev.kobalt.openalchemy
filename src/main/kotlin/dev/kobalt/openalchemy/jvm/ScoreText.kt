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