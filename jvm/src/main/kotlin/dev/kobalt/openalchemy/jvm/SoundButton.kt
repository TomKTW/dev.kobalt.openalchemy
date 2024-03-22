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
import com.badlogic.gdx.utils.Array
import kotlin.properties.Delegates

class SoundButton : GameObject() {

    var enabled: Boolean by Delegates.observable(true) { property, oldValue, newValue ->
        buttonNormal.texture = button
        buttonNormal.setRegion(0, buttonNormalPosition * buttonHeight, buttonWidth, buttonHeight)
        buttonHover.texture = button
        buttonHover.setRegion(0, buttonHoverPosition * buttonHeight, buttonWidth, buttonHeight)
        buttonHold.texture = button
        buttonHold.setRegion(0, buttonHoldPosition * buttonHeight, buttonWidth, buttonHeight)
    }

    var button: Texture? = null
    inline val buttonNormalPosition get() = if (enabled) 0 else 1
    inline val buttonHoverPosition get() = if (enabled) 2 else 4
    inline val buttonHoldPosition get() = if (enabled) 3 else 5
    val buttonWidth = 26
    val buttonHeight = 25
    var buttonArray = Array<TextureRegion>(3).also { array ->
        repeat(3) {
            array.add(TextureRegion())
        }
    }
    inline val buttonNormal get() = buttonArray[0]
    inline val buttonHover get() = buttonArray[1]
    inline val buttonHold get() = buttonArray[2]
    inline var texture: Texture?
        get() = button
        set(value) {
            button = value;
            buttonNormal.texture = value
            buttonNormal.setRegion(0, buttonNormalPosition * buttonHeight, buttonWidth, buttonHeight)
            buttonHover.texture = value
            buttonHover.setRegion(0, buttonHoverPosition * buttonHeight, buttonWidth, buttonHeight)
            buttonHold.texture = value
            buttonHold.setRegion(0, buttonHoldPosition * buttonHeight, buttonWidth, buttonHeight)
        }

    var state: State = State.Normal

    enum class State { Normal, Hover, Hold }

    override fun draw(batch: SpriteBatch) {
        batch.draw(when(state) {
            State.Normal -> buttonNormal
            State.Hover -> buttonHover
            State.Hold -> buttonHold
        }, x, y, width, height)
    }

    var onTouchDown: (() -> Unit)? = null
    var onTouchUp: (() -> Unit)? = null

    fun touchDown(cursor: Cursor) { state = if (contains(cursor) && state == State.Hover) State.Hold.also { onTouchDown?.invoke() } else State.Normal
    }
    fun touchUp(cursor: Cursor) { state = if (contains(cursor) && state == State.Hold) State.Hover.also { onTouchUp?.invoke() } else State.Normal
    }
    fun hover(cursor: Cursor, condition: Boolean) { state = if (contains(cursor) && condition) State.Hover else State.Normal
    }
    fun contains(cursor: Cursor) = rectangle.contains(cursor)

    fun isHover() = state == State.Hover

}