package dev.kobalt.game.alchemy

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import kotlin.system.exitProcess

class AlchemyGame : Game() {

    lateinit var splashScreen: SplashScreen
    lateinit var boardScreen: BoardScreen

    val version = "2021.10.24.15.00.24.352"
    val module = ModulePlayer()
    val sound = Sound()

    override fun create() {
        splashScreen = SplashScreen(this)
        boardScreen = BoardScreen(this)
        module.playIntro()
        setScreen(splashScreen)
    }

    fun switchToGameScreen() {
        module.playMusic()
        setScreen(boardScreen)
    }

    override fun dispose() {
        super.dispose()
        module.stop()
        exitProcess(0)
    }

}

