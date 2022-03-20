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

import com.badlogic.gdx.Game
import kotlin.system.exitProcess

class AlchemyGame : Game() {

    lateinit var splashScreen: dev.kobalt.openalchemy.jvm.SplashScreen
    lateinit var boardScreen: dev.kobalt.openalchemy.jvm.BoardScreen

    val version = "2021.10.24.15.00.24.352"
    val module = dev.kobalt.openalchemy.jvm.ModulePlayer()
    val sound = dev.kobalt.openalchemy.jvm.Sound()

    override fun create() {
        splashScreen = dev.kobalt.openalchemy.jvm.SplashScreen(this)
        boardScreen = dev.kobalt.openalchemy.jvm.BoardScreen(this)
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

