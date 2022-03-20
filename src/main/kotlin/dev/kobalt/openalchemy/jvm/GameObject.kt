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

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

open class GameObject {

    val rectangle: Rectangle = Rectangle()

    inline var x: Float
        get() = rectangle.x
        set(value) {
            rectangle.x = value
        }

    inline var y: Float
        get() = rectangle.y
        set(value) {
            rectangle.y = value
        }

    inline var width: Float
        get() = rectangle.width
        set(value) {
            rectangle.width = value
        }

    inline var height: Float
        get() = rectangle.height
        set(value) {
            rectangle.height = value
        }

    open fun contains(x: Float, y: Float): Boolean {
        return rectangle.contains(x, y)
    }

    open fun draw(batch: SpriteBatch) {
    }

}