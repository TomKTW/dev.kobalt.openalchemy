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

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import java.lang.Float

class CurrentTile : GameObject() {

    var enabled: Boolean = false
    var blockTexture: Texture? = null
    var glowTexture: Texture? = null
    var symbols: Texture? = null
    val symbolsRows = 1
    val symbolsColumns = 22
    val symbolsWidth = 50
    val symbolsHeight = 50
    var symbolsArray = Array<TextureRegion>(symbolsRows * symbolsColumns).also { array ->
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

    var skull: Texture? = null
    val skullRows = 4
    val skullColumns = 8
    val skullWidth = 50
    val skullHeight = 50
    var skullSequence = Array<TextureRegion>(skullRows * skullColumns).also { array ->
        repeat(skullRows * skullColumns) {
            array.add(TextureRegion())
        }
    }
    var skullAnimation = Animation(1f / 30f, skullSequence, Animation.PlayMode.LOOP)
    var skullState = 0f
    inline var skullTexture: Texture?
        get() = skull
        set(value) {
            skull = value; skullSequence.forEachIndexed { index, sequence ->
                val row = index / skullColumns
                val column = index % skullColumns
                sequence.texture = value
                sequence.setRegion(column * skullWidth, row * skullHeight, skullWidth, skullHeight)
            }
        }

    var redCrossedCircleTexture: Texture? = null

    var tile = Tile()

    var offsetX = 24f
    var offsetY = 24f

    var scaleOffsetTime = 1f
    var scaleOffsetPeriod = 1f
    var scaleOffsetScale = 5f

    var cannotPlace = false

    inline val scaleOffsetX get() = -(offsetX * scaleOffsetTime)
    inline val scaleOffsetY get() = -(offsetY * scaleOffsetTime)

    var cannotPlaceTime = 1f

    override fun draw(batch: SpriteBatch) {
        scaleOffsetTime = Float.min(scaleOffsetPeriod, scaleOffsetTime + Gdx.graphics.deltaTime * scaleOffsetScale)
        cannotPlaceTime += Gdx.graphics.deltaTime * 3f
        if (enabled) {
            batch.setColor(1.0f, 1.0f, 1.0f, 0.5f)
            batch.draw(glowTexture, x - 35, y - 35)
            batch.color = Color.WHITE
            when {
                tile.isSkull() -> {
                    skullState += Gdx.graphics.deltaTime
                    batch.draw(
                        skullAnimation.getKeyFrame(skullState, true),
                        x + scaleOffsetX, y + scaleOffsetY,
                        width * scaleOffsetTime, height * scaleOffsetTime
                    )
                }
                tile.isBlock() -> {
                    batch.draw(
                        blockTexture,
                        x + scaleOffsetX, y + scaleOffsetY,
                        width * scaleOffsetTime, height * scaleOffsetTime
                    )
                }
                tile.isSymbol() -> {
                    batch.color = tile.suit.color
                    batch.draw(symbolsArray[tile.type.position],
                        x + scaleOffsetX, y + scaleOffsetY,
                        width * scaleOffsetTime, height * scaleOffsetTime
                    )
                    batch.color = Color.WHITE
                }
            }
            if (cannotPlace) {
                batch.setColor(1.0f, 1.0f, 1.0f, cannotPlaceTime % 2)
                batch.draw(redCrossedCircleTexture, x - 32, y - 32)
                batch.color = Color.WHITE
            }
        }
    }

}