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

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import kotlin.properties.Delegates


class OptionsWindow : GameObject() {

    var backgroundTexture: Texture? by Delegates.observable(null) { property, oldValue, newValue ->
        backgroundNinePatch = NinePatch(newValue, 32, 32,32,32)
    }
    var sliderTexture: Texture? = null
    var sliderGemTexture: Texture? = null
    var checkedTexture: Texture? = null
    var uncheckedTexture: Texture? = null
    var buttonTexture: Texture? by Delegates.observable(null) { property, oldValue, newValue ->
        buttonNinePatch = NinePatch(newValue, 8, 8,8,8)
    }
    var titleTexture: FontTexture? = null
    var fontTexture: FontTexture? = null
    var onTouchDown: ((Rectangle) -> Unit)? = null
    var onTouchUp: ((Rectangle) -> Unit)? = null
    var enabled: Boolean = true

    var closeRectangle = Rectangle()

    var closeState = State.Normal
    enum class State { Normal, Hover, Hold }

    var backgroundNinePatch: NinePatch? = null
    var buttonNinePatch: NinePatch? = null

    fun updatePositions() {
        closeRectangle.set(x + 40f, y + 40f, 420f, 32f)
    }

    inline var xAlt: Float
        get() = x
        set(value) {
            x = value
            updatePositions()
        }

    inline var yAlt: Float
        get() =y
        set(value) {
            y = value
            updatePositions()
        }

    var version: String = "N/A"

    override fun draw(batch: SpriteBatch) {
        if (enabled) {
            backgroundNinePatch!!.draw(batch, x, y, width, height)
            titleTexture!!.draw(batch, "OPTIONS", x + width / 2, y + 380f)
            titleTexture!!.draw(batch,"ABOUT", x + width / 2, y + 310f)
            batch.color = Color.BLACK
            fontTexture!!.draw(batch,"No options available. (I can't think at this moment.)", x+ width / 2, y + 360f)
            fontTexture!!.draw(batch,"OpenAlchemy - Wrapper for Alchemy Deluxe from PopCap", x + width / 2, y + 290f)
            fontTexture!!.draw(batch,"Version - $version (Experimental)", x+ width / 2, y + 270f)
            fontTexture!!.draw(batch,"Developed by: Tom.K - Licensed under AGPL-3.0", x + width / 2, y + 250f)
            fontTexture!!.draw(batch,"", x + width / 2, y + 230f)
            fontTexture!!.draw(batch,"Made with:", x + width / 2, y + 210f)
            fontTexture!!.draw(batch,"Kotlin programming language (Apache-2.0)", x + width / 2, y + 190f)
            fontTexture!!.draw(batch,"LibGDX game framework (Apache-2.0)", x + width / 2, y + 170f)
            fontTexture!!.draw(batch,"LibKTX extensions (CC0-1.0)", x + width / 2, y + 150f)
            fontTexture!!.draw(batch,"JavaMod module music player (GPL-3.0)", x + width / 2, y + 130f)
            batch.color = Color.WHITE
            closeRectangle.also {
                buttonNinePatch!!.draw(batch, it.x, it.y, it.width, it.height)
                batch.color = Color.YELLOW
                fontTexture!!.draw(batch,"Close", it.x + it.width / 2, it.y + it.height / 8)
                batch.color = Color.WHITE
            }
        }
    }


    fun touchDown(cursor: Cursor) {
        closeState =
            if (closeRectangle.contains(cursor)) State.Hold.also { onTouchDown?.invoke(closeRectangle) } else State.Normal
    }

    fun touchUp(cursor: Cursor) {
        closeState =
            if (closeRectangle.contains(cursor)) State.Hover.also { onTouchUp?.invoke(closeRectangle) } else State.Normal
    }

    fun hover(cursor: Cursor, condition: Boolean) {
        closeState = if (closeRectangle.contains(cursor) && condition) State.Hover else State.Normal
    }

    fun isHover() = closeState == State.Hover

    fun containsIfVisible(cursor: Cursor) = rectangle.contains(cursor) && enabled

}