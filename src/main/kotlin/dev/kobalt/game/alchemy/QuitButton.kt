package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class QuitButton : GameObject() {

    var button: Texture? = null
    val buttonNormalPosition = 0
    val buttonHoverPosition = 1
    val buttonHoldPosition = 2
    val buttonWidth = 79
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