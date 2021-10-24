package dev.kobalt.game.alchemy

import com.badlogic.gdx.Files
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main(args: Array<String>) {
    val game = AlchemyGame()
    Lwjgl3Application(game, Lwjgl3ApplicationConfiguration().apply {
        setTitle("OpenAlchemy ${game.version}")
        setForegroundFPS(60)
        setIdleFPS(60)
        setWindowIcon(Files.FileType.Classpath, "kobalt-lab-icon.gif")
        setWindowedMode(640, 480)
    })
}