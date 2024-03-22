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
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.viewport.StretchViewport

fun Pixmap.toTexture(): Texture = Texture(this)
fun Pixmap.mask(source: Pixmap, mask: Pixmap): Pixmap {
    val pixmapWidth = source.width
    val pixmapHeight = source.height
    val pixelColor = Color()
    val maskPixelColor = Color()
    val oldBlending: Pixmap.Blending = blending
    blending = Pixmap.Blending.None
    for (x in 0 until pixmapWidth) {
        for (y in 0 until pixmapHeight) {
            // Get source pixel.
            Color.rgba8888ToColor(pixelColor, source.getPixel(x, y))
            // Get mask pixel.
            Color.rgba8888ToColor(maskPixelColor, mask.getPixel(x, y))
            // Multiply source and mask pixel from red channel.
            pixelColor.a = pixelColor.a * maskPixelColor.r
            setColor(pixelColor)
            drawPixel(x, y)
        }
    }
    blending = oldBlending
    return this
}

fun mergedTexture(source: Texture, mask: Texture): Texture {
    val result = source.combine(mask)
    source.dispose()
    mask.dispose()
    return result
}

fun Texture.toPixmap(): Pixmap {
    if (!textureData.isPrepared) {
        textureData.prepare()
    }
    val pixmap = Pixmap(
        width,
        height,
        textureData.format
    )
    pixmap.drawPixmap(textureData.consumePixmap(), 0, 0)
    return pixmap
}

fun Texture.toRegion(rows: Int, columns: Int, width: Int, height: Int): Array<TextureRegion> {
    return Array(rows * columns) {
        val row = it / rows
        val column = it % rows
        TextureRegion(this, row * width, column * height, width, height)
    }
}

fun Texture.combine(mask: Texture): Texture {
    val sourcePixmap = this.toPixmap()
    val maskPixmap = mask.toPixmap()
    val resultPixmap = Pixmap(sourcePixmap.width, sourcePixmap.height, Pixmap.Format.RGBA8888)

    val sourcePixelColor = Color()
    val maskPixelColor = Color()

    (0..sourcePixmap.width).forEach { x ->
        (0..sourcePixmap.height).forEach { y ->
            Color.rgba8888ToColor(sourcePixelColor, sourcePixmap.getPixel(x, y))
            Color.rgba8888ToColor(maskPixelColor, maskPixmap.getPixel(x, y))
            sourcePixelColor.a = maskPixelColor.r
            resultPixmap.setColor(sourcePixelColor)
            resultPixmap.drawPixel(x, y)
        }
    }

    val resultTexture = resultPixmap.toTexture()
    resultPixmap.dispose()
    maskPixmap.dispose()
    sourcePixmap.dispose()
    return resultTexture
}


fun Texture.applyLinearFilter(): Texture {
    setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    return this
}

fun Rectangle.contains(cursor: Cursor): Boolean {
    return contains(cursor.position.x, cursor.position.y)
}

fun orthographicCamera() = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()).also {
    it.translate(it.viewportWidth / 2, it.viewportHeight / 2)
}

fun stretchViewport(camera: Camera) = StretchViewport(camera.viewportWidth, camera.viewportHeight, camera)

fun spriteBatch() = SpriteBatch().apply { color = Color.WHITE }