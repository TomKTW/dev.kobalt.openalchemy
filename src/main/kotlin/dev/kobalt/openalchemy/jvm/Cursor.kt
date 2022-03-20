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

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

class Cursor {

    enum class State { Normal, Hover, Drag }

    var arrowTexture: Texture? = null
    var hoverTexture: Texture? = null
    var dragTexture: Texture? = null
    var position: Vector3 = Vector3()
    var hotspot: Vector2 = Vector2()
    var state: State = State.Normal

    fun draw(batch: SpriteBatch) {
        batch.draw(
            when (state) {
                State.Normal -> arrowTexture
                State.Hover -> hoverTexture
                State.Drag -> dragTexture
            },
            position.x - hotspot.x,
            position.y - hotspot.y
        )
    }

    fun update(camera: Camera, x: Float, y: Float) {
        position.x = x
        position.y = y
        camera.unproject(position)
        camera.update()
    }

}