package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class DifficultyButton : GameObject() {

    var button: Texture? = null
    val buttonEasyPosition = 0
    val buttonAveragePosition = 1
    val buttonHardPosition = 2
    val buttonWidth = 66
    val buttonHeight = 20
    var buttonArray = Array<TextureRegion>(3).also { array ->
        repeat(3) {
            array.add(TextureRegion())
        }
    }
    var mode: Int = -1
    inline val buttonEasy get() = buttonArray[0]
    inline val buttonAverage get() = buttonArray[1]
    inline val buttonHard get() = buttonArray[2]
    inline var texture: Texture?
        get() = button
        set(value) {
            button = value;
            buttonEasy.texture = value
            buttonEasy.setRegion(0, buttonEasyPosition * buttonHeight, buttonWidth, buttonHeight)
            buttonAverage.texture = value
            buttonAverage.setRegion(0, buttonAveragePosition * buttonHeight, buttonWidth, buttonHeight)
            buttonHard.texture = value
            buttonHard.setRegion(0, buttonHardPosition * buttonHeight, buttonWidth, buttonHeight)
        }

    override fun draw(batch: SpriteBatch) {
        when(mode) {
            0 -> batch.draw(buttonEasy, x, y, width, height)
            1 -> batch.draw(buttonAverage, x, y, width, height)
            2 -> batch.draw(buttonHard, x, y, width, height)
        }
    }

}