package dev.kobalt.game.alchemy

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Gems : GameObject() {

    var gems: Texture? = null
    inline var gemsTexture: Texture?
        get() = gems
        set(value) {
            gems = value
            firstGem.apply { texture = value; setRegion(0, 0, 29, 29) }
            secondGem.apply { texture = value; setRegion(66, 0, 29, 29)}
            thirdGem.apply { texture = value; setRegion(136, 0, 29, 29)}
            fourthGem.apply { texture = value; setRegion(206, 0, 29, 29)}
            fifthGem.apply { texture = value; setRegion( 276, 0, 29, 29)}
            sixthGem.apply { texture = value; setRegion(346, 0, 29, 29)}
            seventhGem.apply { texture = value; setRegion( 416, 0, 29, 29)}
        }

    var gemsWithSymbols: Texture? = null
    inline var gemsWithSymbolsTexture: Texture?
        get() = gemsWithSymbols
        set(value) {
            gemsWithSymbols = value
            firstGemSymbol.apply { texture = value; setRegion(0, 0, 29, 29) }
            secondGemSymbol.apply { texture = value; setRegion(66, 0, 29, 29)}
            thirdGemSymbol.apply { texture = value; setRegion(136, 0, 29, 29)}
            fourthGemSymbol.apply { texture = value; setRegion(206, 0, 29, 29)}
            fifthGemSymbol.apply { texture = value; setRegion( 276, 0, 29, 29)}
            sixthGemSymbol.apply { texture = value; setRegion(346, 0, 29, 29)}
            seventhGemSymbol.apply { texture = value; setRegion( 416, 0, 29, 29)}
        }

    var firstGem = TextureRegion()
    var secondGem = TextureRegion()
    var thirdGem = TextureRegion()
    var fourthGem = TextureRegion()
    var fifthGem = TextureRegion()
    var sixthGem = TextureRegion()
    var seventhGem = TextureRegion()

    var firstGemSymbol = TextureRegion()
    var secondGemSymbol = TextureRegion()
    var thirdGemSymbol = TextureRegion()
    var fourthGemSymbol = TextureRegion()
    var fifthGemSymbol = TextureRegion()
    var sixthGemSymbol = TextureRegion()
    var seventhGemSymbol = TextureRegion()

    var counter = 0

    override fun draw(batch: SpriteBatch) {
        if (counter >= 1) { firstGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 2) { secondGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 3) { thirdGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 4) { fourthGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 5) { fifthGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 6) { sixthGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 7) { seventhGem.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 8) { firstGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 9) { secondGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 10) { thirdGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 11) { fourthGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 12) { fifthGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 13) { sixthGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
        if (counter >= 14) { seventhGemSymbol.also { batch.draw(it, x+ it.regionX, y + it.regionY, it.regionWidth.toFloat(), it.regionHeight.toFloat()) } }
    }

}