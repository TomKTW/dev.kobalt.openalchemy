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

class ModeButton : GameObject() {

    var button: Texture? = null
    val buttonStrategyPosition = 3
    val buttonTimeTrialPosition = 4
    val buttonWidth = 66
    val buttonHeight = 20
    var buttonArray = Array<TextureRegion>(2).also { array ->
        repeat(2) {
            array.add(TextureRegion())
        }
    }
    var mode: Int = -1
    inline val buttonStrategy get() = buttonArray[0]
    inline val buttonTimeTrial get() = buttonArray[1]
    inline var texture: Texture?
        get() = button
        set(value) {
            button = value;
            buttonStrategy.texture = value
            buttonStrategy.setRegion(0, buttonStrategyPosition * buttonHeight, buttonWidth, buttonHeight)
            buttonTimeTrial.texture = value
            buttonTimeTrial.setRegion(0, buttonTimeTrialPosition * buttonHeight, buttonWidth, buttonHeight)
        }

    override fun draw(batch: SpriteBatch) {
        when(mode) {
            0 -> batch.draw(buttonStrategy, x, y, width, height)
            1 -> batch.draw(buttonTimeTrial, x, y, width, height)
        }
    }

}