package dev.kobalt.game.alchemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import ktx.graphics.use

class SplashScreen(val game: AlchemyGame) : BaseScreen() {

    open class GameObject(
        x: Float,
        y: Float,
        width: Float,
        height: Float
    ) {

        val rectangle: Rectangle = Rectangle(x, y, width, height)

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
            return rectangle.contains(x,y)
        }

        open fun draw(batch: SpriteBatch, texture: Texture) {
            batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
        }

        open fun draw(batch: SpriteBatch, texture: TextureRegion) {
            batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
        }
    }

    class Background(x: Float, y: Float, width: Float, height: Float, val texture: Texture) : GameObject(x, y, width, height) {

        fun draw(batch: SpriteBatch){
            batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
        }

    }

    class ProgressBar(x: Float, y: Float, width: Float, height: Float, val texture: Texture) : GameObject(x, y, width, height) {

        private val progressBarRegion = TextureRegion(texture)
        var value: Float = 0.0f

        fun draw(batch: SpriteBatch) {
            progressBarRegion.regionHeight = texture.height
            progressBarRegion.regionWidth = (width * value).toInt()
            batch.draw(progressBarRegion, x, y, progressBarRegion.regionWidth.toFloat(), height)
        }

    }

    class PlayButton(x: Float, y: Float, width: Float, height: Float, val texture: Texture) : GameObject(x, y, width, height) {

        fun draw(batch: SpriteBatch){
            batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height)
        }

    }

    val camera = orthographicCamera()
    val viewport = stretchViewport(camera)
    val batch = spriteBatch()
    val assets = SplashAssets()

    val background = Background(0f, 0f, 640f,480f, assets.background)
    val progress = ProgressBar(158f, 84f, 321f, 24f, assets.progress)
    val playButton = PlayButton(193f, 26f, 256f, 44f, assets.playButton)

    override fun show() {
        super.show()
        game.boardScreen.assets.load()
    }

    override fun render(delta: Float) {
        camera.update()
        progress.value = game.boardScreen.assets.progress
        if (Gdx.input.justTouched() && game.boardScreen.assets.progress >= 1.0f) {
            game.switchToGameScreen()
        }
        clear()
        batch.use {
            background.draw(it)
            progress.draw(it)
            batch.color = Color.DARK_GRAY
            it.draw(assets.logo,26f + 4f,28f, 112f, 112f)
            batch.color = Color.WHITE
            it.draw(assets.logo,26f,28f + 4f, 112f, 112f)
            batch.color = Color.YELLOW
            assets.mainFontTexture.draw(it, "Version ${game.version}", 320f, 112f)
            if (game.boardScreen.assets.progress >= 1.0f) {
                batch.color = Color.WHITE
                playButton.draw(it)
                batch.color = Color.YELLOW
                assets.playFontTexture.draw(it, "CLICK TO BEGIN.", 320f, 36f)
            }
            batch.color = Color.WHITE
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height)
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }

}