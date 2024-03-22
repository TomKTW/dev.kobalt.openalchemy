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

import com.badlogic.gdx.InputProcessor

class BoardInput(val screen: BoardScreen) : InputProcessor {
    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (screen.currentTile.enabled && button == 1) { screen.discardButton.onTouchUp?.invoke(); return true  }
        screen.newGameButton.touchDown(screen.cursor)
        screen.optionsButton.touchDown(screen.cursor)
        screen.discardButton.touchDown(screen.cursor)
        screen.helpButton.touchDown(screen.cursor)
        screen.soundButton.touchDown(screen.cursor)
        screen.quitButton.touchDown(screen.cursor)
        screen.board.touchDown(screen.cursor)
        if (screen.optionsWindow.enabled) screen.optionsWindow.touchDown(screen.cursor)
        if (!screen.optionsWindow.enabled && screen.selectGameWindow.enabled) screen.selectGameWindow.touchDown(screen.cursor)
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        screen.newGameButton.touchUp(screen.cursor)
        screen.optionsButton.touchUp(screen.cursor)
        screen.discardButton.touchUp(screen.cursor)
        screen.helpButton.touchUp(screen.cursor)
        screen.soundButton.touchUp(screen.cursor)
        screen.quitButton.touchUp(screen.cursor)
        if (screen.optionsWindow.enabled) screen.optionsWindow.touchUp(screen.cursor)
        if (!screen.optionsWindow.enabled && screen.selectGameWindow.enabled)  screen.selectGameWindow.touchUp(screen.cursor)
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        screen.cursor.update(screen.camera, screenX.toFloat(), screenY.toFloat())
        screen.currentTile.apply { x = screen.cursor.position.x; y = screen.cursor.position.y }
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        screen.cursor.update(screen.camera, screenX.toFloat(), screenY.toFloat())
        screen.currentTile.apply { x = screen.cursor.position.x; y = screen.cursor.position.y }
        screen.newGameButton.hover(screen.cursor, true)
        screen.optionsButton.hover(screen.cursor, true)
        screen.discardButton.hover(screen.cursor, true)
        screen.helpButton.hover(screen.cursor, true)
        screen.soundButton.hover(screen.cursor, true)
        screen.quitButton.hover(screen.cursor, true)
        screen.optionsWindow.hover(screen.cursor,screen.optionsWindow.enabled)
        screen.selectGameWindow.hover(screen.cursor, screen.selectGameWindow.enabled && !screen.optionsWindow.enabled)
        screen.board.hover(screen.cursor, screen.currentTile, true)
        screen.cursor.state =
            if (screen.newGameButton.isHover() || screen.optionsButton.isHover() || screen.discardButton.isHover() || screen.helpButton.isHover() || screen.soundButton.isHover() || screen.quitButton.isHover() ||screen.selectGameWindow.isHover() || screen.optionsWindow.isHover()) Cursor.State.Hover else Cursor.State.Normal
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }

}