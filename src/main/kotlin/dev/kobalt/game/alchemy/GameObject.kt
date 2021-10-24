package dev.kobalt.game.alchemy

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