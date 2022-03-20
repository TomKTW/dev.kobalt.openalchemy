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
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle


class SelectGameWindow : GameObject() {

    var backgroundTexture: Texture? = null
    var difficultyHighlightTexture: Texture? = null
    var modeHighlightTexture: Texture? = null
    var newGameButtonTexture: Texture? = null
    var startTutorialButtonTexture: Texture? = null
    var titleTexture: FontTexture? = null
    var fontTexture: FontTexture? = null
    var onTouchDown: ((Rectangle) -> Unit)? = null
    var onTouchUp: ((Rectangle) -> Unit)? = null
    var enabled: Boolean = true
    var difficulty = -1
    var type = -1

    var difficultyEasyRectangle = Rectangle(x + 93f, y + 156f, 89f, 67f)
    var difficultyMediumRectangle = Rectangle(x + 191f, y + 156f, 89f, 67f)
    var difficultyHardRectangle = Rectangle(x + 291f, y + 156f, 89f, 67f)
    var typeStrategyRectangle = Rectangle(x + 92f, y + 83f, 132f, 48f)
    var typeTimedRectangle = Rectangle(x + 247f, y + 83f, 132f, 48f)
    var tutorialRectangle = Rectangle(x + 4f, y + 11f, 180f, 51f)
    var newGameRectangle = Rectangle(x + 278f, y + 11f, 180f, 51f)

    var difficultyEasyState = State.Normal
    var difficultyMediumState = State.Normal
    var difficultyHardState = State.Normal
    var typeStrategyState = State.Normal
    var typeTimedState = State.Normal
    var tutorialState = State.Normal
    var newGameState = State.Normal

    enum class State { Normal, Hover, Hold }

    inline var xAlt: Float
        get() = x
        set(value) {
            x = value
            difficultyEasyRectangle.set(x + 93f, y + 156f, 89f, 67f)
            difficultyMediumRectangle.set(x + 191f, y + 156f, 89f, 67f)
            difficultyHardRectangle.set(x + 291f, y + 156f, 89f, 67f)
            typeStrategyRectangle.set(x + 92f, y + 83f, 132f, 48f)
            typeTimedRectangle.set(x + 247f, y + 83f, 132f, 48f)
            tutorialRectangle.set(x + 4f, y + 11f, 180f, 51f)
            newGameRectangle.set(x + 278f, y + 11f, 180f, 51f)
        }

    inline var yAlt: Float
        get() =y
        set(value) {
           y = value
            difficultyEasyRectangle.set(x + 93f, y + 156f, 89f, 67f)
            difficultyMediumRectangle.set(x + 191f, y + 156f, 89f, 67f)
            difficultyHardRectangle.set(x + 291f, y + 156f, 89f, 67f)
            typeStrategyRectangle.set(x + 92f, y + 83f, 132f, 48f)
            typeTimedRectangle.set(x + 247f, y + 83f, 132f, 48f)
            tutorialRectangle.set(x + 4f, y + 11f, 180f, 51f)
            newGameRectangle.set(x + 278f, y + 11f, 180f, 51f)
        }

    override fun draw(batch: SpriteBatch) {
        if (enabled) {
            batch.draw(backgroundTexture, x, y, width, height)
            if (difficulty == 0)
                difficultyEasyRectangle.also { batch.draw(difficultyHighlightTexture, it.x, it.y, it.width, it.height) }
            if (difficulty == 1)
                difficultyMediumRectangle.also {
                    batch.draw(
                        difficultyHighlightTexture,
                        it.x,
                        it.y,
                        it.width,
                        it.height
                    )
                }
            if (difficulty == 2)
                difficultyHardRectangle.also { batch.draw(difficultyHighlightTexture, it.x, it.y, it.width, it.height) }
            if (type == 0)
                typeStrategyRectangle.also { batch.draw(modeHighlightTexture, it.x, it.y, it.width, it.height) }
            if (type == 1)
                typeTimedRectangle.also { batch.draw(modeHighlightTexture, it.x, it.y, it.width, it.height) }
            if (tutorialState != State.Normal)
                tutorialRectangle.also { batch.draw(startTutorialButtonTexture, it.x, it.y, it.width, it.height) }
            if (newGameState != State.Normal)
                newGameRectangle.also { batch.draw(newGameButtonTexture, it.x, it.y, it.width, it.height) }

            titleTexture!!.draw(batch, "WELCOME TO", x + 290f,y + 370f)
            titleTexture!!.draw(batch, "ALCHEMY", x + 290f,y + 340f)
            batch.color = Color.LIGHT_GRAY
            fontTexture!!.draw(batch, "First time players should just", x + 290f,y + 320f)
            fontTexture!!.draw(batch, "figure it out on their own.", x + 290f,y + 300f)

            batch.color = if (difficulty == 0) Color.GREEN else Color.GRAY
            fontTexture!!.draw(batch, "Easy", x + 135f,y + 167f)
            batch.color = if (difficulty == 1) Color.GREEN else Color.GRAY
            fontTexture!!.draw(batch, "Average", x + 235f,y + 167f)
            batch.color = if (difficulty == 2) Color.GREEN else Color.GRAY
            fontTexture!!.draw(batch, "Hard", x + 333f,y + 167f)
            batch.color = if (type == 0) Color.ORANGE else Color.GRAY
            fontTexture!!.draw(batch, "Strategy", x + 164f,y + 97f)
            batch.color = if (type == 1) Color.ORANGE else Color.GRAY
            fontTexture!!.draw(batch, "Time Trial", x + 325f,y + 97f)
            batch.color = Color.WHITE
        }
    }


    fun touchDown(cursor: Cursor) {
        difficultyEasyState =
            if (difficultyEasyRectangle.contains(cursor)) State.Hold.also { onTouchDown?.invoke(difficultyEasyRectangle) } else State.Normal
        difficultyMediumState = if (difficultyMediumRectangle.contains(cursor)) State.Hold.also {
            onTouchDown?.invoke(difficultyMediumRectangle)
        } else State.Normal
        difficultyHardState =
            if (difficultyHardRectangle.contains(cursor)) State.Hold.also { onTouchDown?.invoke(difficultyHardRectangle) } else State.Normal
        typeStrategyState =
            if (typeStrategyRectangle.contains(cursor)) State.Hold.also { onTouchDown?.invoke(typeStrategyRectangle) } else State.Normal
        typeTimedState =
            if (typeTimedRectangle.contains(cursor)) State.Hold.also { onTouchDown?.invoke(typeTimedRectangle) } else State.Normal
        tutorialState =
            if (tutorialRectangle.contains(cursor) && isValidSelection()) State.Hold.also { onTouchDown?.invoke(tutorialRectangle) } else State.Normal
        newGameState =
            if (newGameRectangle.contains(cursor) && isValidSelection()) State.Hold.also { onTouchDown?.invoke(newGameRectangle) } else State.Normal
    }

    fun touchUp(cursor: Cursor) {
        difficultyEasyState =
            if (difficultyEasyRectangle.contains(cursor)) State.Hover.also { onTouchUp?.invoke(difficultyEasyRectangle) } else State.Normal
        difficultyMediumState = if (difficultyMediumRectangle.contains(cursor)) State.Hover.also {
            onTouchUp?.invoke(difficultyMediumRectangle)
        } else State.Normal
        difficultyHardState =
            if (difficultyHardRectangle.contains(cursor)) State.Hover.also { onTouchUp?.invoke(difficultyHardRectangle) } else State.Normal
        typeStrategyState =
            if (typeStrategyRectangle.contains(cursor)) State.Hover.also { onTouchUp?.invoke(typeStrategyRectangle) } else State.Normal
        typeTimedState =
            if (typeTimedRectangle.contains(cursor)) State.Hover.also { onTouchUp?.invoke(typeTimedRectangle) } else State.Normal
        tutorialState =
            if (tutorialRectangle.contains(cursor) && isValidSelection()) State.Hover.also { onTouchUp?.invoke(tutorialRectangle) } else State.Normal
        newGameState =
            if (newGameRectangle.contains(cursor) && isValidSelection()) State.Hover.also { onTouchUp?.invoke(newGameRectangle) } else State.Normal
    }

    fun isValidSelection() = type != -1 && difficulty != -1

    fun hover(cursor: Cursor, condition: Boolean) {
        difficultyEasyState = if (difficultyEasyRectangle.contains(cursor) && condition) State.Hover else State.Normal
        difficultyMediumState =
            if (difficultyMediumRectangle.contains(cursor) && condition) State.Hover else State.Normal
        difficultyHardState = if (difficultyHardRectangle.contains(cursor) && condition) State.Hover else State.Normal
        typeStrategyState = if (typeStrategyRectangle.contains(cursor) && condition) State.Hover else State.Normal
        typeTimedState = if (typeTimedRectangle.contains(cursor) && condition) State.Hover else State.Normal
        tutorialState = if (tutorialRectangle.contains(cursor) && condition && isValidSelection()) State.Hover else State.Normal
        newGameState = if (newGameRectangle.contains(cursor) && condition && isValidSelection()) State.Hover else State.Normal
    }

    fun isHover() = difficultyEasyState == State.Hover
            || difficultyMediumState == State.Hover
            || difficultyHardState == State.Hover
            || typeStrategyState == State.Hover
            || typeTimedState == State.Hover
            || tutorialState == State.Hover
            || newGameState == State.Hover

    fun containsIfVisible(cursor: Cursor) = rectangle.contains(cursor) && enabled

}