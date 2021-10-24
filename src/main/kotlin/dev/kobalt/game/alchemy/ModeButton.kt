package dev.kobalt.game.alchemy

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