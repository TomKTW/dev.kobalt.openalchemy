package dev.kobalt.game.alchemy

import com.badlogic.gdx.math.Rectangle

class Field(
    val column: Int,
    val row: Int,
    val rectangle: Rectangle
) {

    var gold = false
    var hover = false
    val tile = Tile()

}