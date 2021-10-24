package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle

class Board : GameObject() {

    var level2texture: Texture? = null
    var level3texture: Texture? = null
    var level4texture: Texture? = null
    var level5texture: Texture? = null
    var level6texture: Texture? = null
    var level7texture: Texture? = null
    var level8texture: Texture? = null
    var level9texture: Texture? = null
    var level = 0
    var enabled: Boolean = true

    var onClickField: ((middle: Field, up: Field, right: Field, down: Field, left: Field) -> Unit)? = null

    var hoverTexture: Texture? = null
    var goldTexture: Texture? = null

    var blockTexture: Texture? = null
    var symbols: Texture? = null
    val symbolsRows = 1
    val symbolsColumns = 22
    val symbolsWidth = 50
    val symbolsHeight = 50
    var symbolsArray = com.badlogic.gdx.utils.Array<TextureRegion>(symbolsRows * symbolsColumns).also { array ->
        repeat(symbolsRows * symbolsColumns) {
            array.add(TextureRegion())
        }
    }
    inline var symbolsTexture: Texture?
        get() = symbols
        set(value) {
            symbols = value
            symbolsArray.forEachIndexed { index, sequence ->
                val row = index / symbolsColumns
                val column = index % symbolsColumns
                sequence.texture = value
                sequence.setRegion(column * symbolsWidth, row * symbolsHeight, symbolsWidth, symbolsHeight)
            }
        }

    inline var xAlt: Float
        get() = x
        set(value) {
            x = value
            fields.forEachIndexed { row, fieldRow ->
                fieldRow.forEachIndexed { column, field ->
                    field.rectangle.set(x + (column * 52), y + (row * 52), 50f, 50f)
                }
            }
        }

    inline var yAlt: Float
        get() =y
        set(value) {
            y = value
            fields.forEachIndexed { row, fieldRow ->
                fieldRow.forEachIndexed { column, field ->
                    field.rectangle.set(x + (column * 52), y + (row * 52), 50f, 50f)
                }
            }
        }

    val fields = Array(8) { row ->
        Array(9) { column ->
            Field(
                column,
                row,
                Rectangle(x + (column * 52), y + (row * 52), 50f, 50f)
            )
        }
    }


    override fun draw(batch: SpriteBatch) {
        if (enabled) {
            when (level) {
                0, 1 -> {
                }
                2 -> batch.draw(level2texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                3 -> batch.draw(level3texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                4 -> batch.draw(level4texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                5 -> batch.draw(level5texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                6 -> batch.draw(level6texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                7 -> batch.draw(level7texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                8 -> batch.draw(level8texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                else -> batch.draw(level9texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
            }

            fields.forEach { fieldRow ->
                fieldRow.forEach { field ->
                    if (field.hover) batch.draw(
                        hoverTexture,
                        field.rectangle.x,
                        field.rectangle.y,
                        field.rectangle.width,
                        field.rectangle.height
                    )
                    if (field.gold) batch.draw(
                        goldTexture,
                        field.rectangle.x,
                        field.rectangle.y,
                        field.rectangle.width,
                        field.rectangle.height
                    )
                    when {
                        field.tile.isBlock() -> batch.draw(
                            blockTexture,
                            field.rectangle.x,
                            field.rectangle.y,
                            field.rectangle.width,
                            field.rectangle.height
                        )
                        field.tile.isSymbol() -> batch.apply {
                            color = field.tile.suit.color
                            draw(
                                symbolsArray[field.tile.type.position],
                                field.rectangle.x,
                                field.rectangle.y,
                                field.rectangle.width,
                                field.rectangle.height
                            )
                            color = Color.WHITE
                        }
                    }
                }
            }
        }
    }

    val outOfBoundsField = Field(-1,-1,Rectangle())

    fun getField(column: Int, row: Int): Field {
        return fields.getOrNull(row)?.getOrNull(column) ?: outOfBoundsField
    }

    fun touchDown(cursor: Cursor) {
        if (enabled && rectangle.contains(cursor)) {
            fields.forEachIndexed { row, fieldRow ->
                fieldRow.forEachIndexed { column, field ->
                    if (field.rectangle.contains(cursor)) {
                        onClickField?.invoke(
                            field,
                            getField(field.column, field.row - 1),
                            getField(field.column + 1, field.row),
                            getField(field.column, field.row + 1),
                            getField(field.column - 1, field.row),
                        )
                    }
                }
            }
        }
    }

    fun touchUp(cursor: Cursor) {
        if (enabled) {

        }
    }

    fun hover(cursor: Cursor, currentTile: CurrentTile, condition: Boolean) {
        if (enabled) {
            fields.forEach { fieldRow ->
                fieldRow.forEach { field ->
                    if (!field.rectangle.contains(cursor)) {
                        field.hover = false
                    } else if (currentTile.tile.isSkull()) {
                        field.hover = !field.tile.isNone()
                    } else if (currentTile.tile.isBlock()) {
                        val up = getField(field.column, field.row - 1)
                        val right = getField(field.column + 1, field.row)
                        val down = getField(field.column, field.row + 1)
                        val left = getField(field.column - 1, field.row)
                        field.hover = field.tile.isNone()
                                && !(up.tile.isNone() && right.tile.isNone() && down.tile.isNone() && left.tile.isNone())
                                && currentTile.tile.matches(up.tile)
                                && currentTile.tile.matches(right.tile)
                                && currentTile.tile.matches(down.tile)
                                && currentTile.tile.matches(left.tile)
                                || fields.all { it.all { it.tile.isNone() } }
                    } else if (currentTile.tile.isSymbol()) {
                        val up = getField(field.column, field.row - 1)
                        val right = getField(field.column + 1, field.row)
                        val down = getField(field.column, field.row + 1)
                        val left = getField(field.column - 1, field.row)
                        field.hover = field.tile.isNone()
                                && !(up.tile.isNone() && right.tile.isNone() && down.tile.isNone() && left.tile.isNone())
                                && currentTile.tile.matches(up.tile)
                                && currentTile.tile.matches(right.tile)
                                && currentTile.tile.matches(down.tile)
                                && currentTile.tile.matches(left.tile)
                    }
                }
            }
        }
    }

}