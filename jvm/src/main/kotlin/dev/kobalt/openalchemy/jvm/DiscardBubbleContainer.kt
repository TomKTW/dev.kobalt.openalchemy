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
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import java.lang.Float

class DiscardBubbleContainer : GameObject() {

    var backgroundTexture: Texture? = null
    var foregroundTexture: Texture? = null
    var tinyTexture: Texture? = null
    var burst: Texture? = null
    var burstSequence = Array<TextureRegion>(7).also { array -> repeat(7) { array.add(TextureRegion()) } }
    var burstAnimation = Animation(1f / 30f, burstSequence, Animation.PlayMode.NORMAL)
    var burstState = 0f
    inline var burstTexture: Texture?
        get() = burst
        set(value) {
            burst = value; burstSequence.forEachIndexed { index, sequence ->
                sequence.texture = value
                sequence.setRegion(index * 14, 0, 10, 14)
            }
        }
    var wave: Texture? = null
    var waveSequence = Array<TextureRegion>(7).also { array -> repeat(7) { array.add(TextureRegion()) } }
    var waveAnimation = Animation(1f / 8f, waveSequence, Animation.PlayMode.LOOP)
    var waveState = 0f
    inline var waveTexture: Texture?
        get() = wave
        set(value) {
            wave = value; waveSequence.forEachIndexed { index, sequence ->
                sequence.texture = value
                sequence.setRegion(0, index * 5, 106, 5)
            }
        }

    var counter = 0

    inline val counterOffset get() = -100f + Float.min(100f, (30f * counterAnimationPosition))

    var counterAnimationPosition = 0f

    override fun draw(batch: SpriteBatch) {
        if (counter > counterAnimationPosition) {
            counterAnimationPosition = counterAnimationPosition + Gdx.graphics.deltaTime * 3f
            if (counter < counterAnimationPosition) { counterAnimationPosition = counter.toFloat()}
        } else if(counter < counterAnimationPosition) {
            counterAnimationPosition = counterAnimationPosition - Gdx.graphics.deltaTime * 3f
            if (counter > counterAnimationPosition) { counterAnimationPosition = counter.toFloat()}
        }
        burstState += Gdx.graphics.deltaTime
        waveState += Gdx.graphics.deltaTime
        batch.draw(waveAnimation.getKeyFrame(waveState), x, y + counterOffset + height - 1)
        batch.draw(backgroundTexture, x, y + counterOffset, width, height)
        batch.draw(foregroundTexture, x, y + counterOffset, width, height)
    }

}