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