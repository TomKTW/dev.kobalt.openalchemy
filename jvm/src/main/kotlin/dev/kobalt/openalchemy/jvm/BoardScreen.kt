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
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Pixmap
import ktx.graphics.use
import kotlin.properties.Delegates


class BoardScreen(val game: dev.kobalt.openalchemy.jvm.AlchemyGame) : dev.kobalt.openalchemy.jvm.BaseScreen() {

    val camera = orthographicCamera()
    val viewport = stretchViewport(camera)
    val batch = spriteBatch()
    val sound get() = game.sound
    val assets = BoardAssets()
    val input = BoardInput(this)

    val boardBackground = BoardBackground()
    val board = Board()
    val discardBackground = DiscardBackground()
    val discardBubbleContainer = DiscardBubbleContainer()
    val newGameButton = NewGameButton()
    val difficultyButton = DifficultyButton()
    val modeButton = ModeButton()
    val optionsButton = OptionsButton()
    val discardButton = DiscardButton()
    val helpButton = HelpButton()
    val soundButton = SoundButton()
    val quitButton = QuitButton()
    val selectGameWindow = SelectGameWindow()
    val optionsWindow = OptionsWindow()
    val cursor = Cursor()
    val currentTile = CurrentTile()
    val gems = Gems()
    val levelText = LevelText()
    val scoreText = ScoreText()
    val overText = OverText()
    val bigExplosion = dev.kobalt.openalchemy.jvm.BigExplosion()

    var tileTypes: Array<Tile.Type> = emptyArray()
    var tileSuits: Array<Tile.Suit> = emptyArray()
    var multiplier = 0

    var level by Delegates.observable(-1) { property, oldValue, newValue ->
        levelText.value = when (difficulty) {
            0 -> newValue
            1 -> newValue - 5
            2 -> newValue - 10
            else -> 0
        }
        gems.counter = when (difficulty) {
            0 -> newValue - 1
            1 -> newValue - 6
            2 -> newValue - 11
            else -> 0
        }
        multiplier = when {
            newValue < 0 -> 0
            newValue < 6 -> 1
            newValue < 11 -> 2
            else -> 4
        }
        board.level = newValue
        tileTypes = Tile.Type.arrayFor(newValue)
        tileSuits = Tile.Suit.arrayFor(newValue)
    }

    var score by Delegates.observable(0) { property, oldValue, newValue ->
        scoreText.value = newValue
    }

    var difficulty by Delegates.observable(0) { property, oldValue, newValue ->
        selectGameWindow.difficulty = newValue
        difficultyButton.mode = newValue
    }

    var mode by Delegates.observable(0) { property, oldValue, newValue ->
        selectGameWindow.type = newValue
        modeButton.mode = newValue
    }

    override fun show() {

        Gdx.graphics.setCursor(Gdx.graphics.newCursor(Pixmap(1, 1, Pixmap.Format.RGBA8888), 0, 0))
        Gdx.input.inputProcessor = input
        boardBackground.apply {
            x = 0f; y = 0f; width = 640f; height = 480f
            texture = assets.boardBackground
        }
        board.apply {
            xAlt = 161f; yAlt = 24f; width = 468f; height = 416f
            level2texture = assets.level2Background
            level3texture = assets.level3Background
            level4texture = assets.level4Background
            level5texture = assets.level5Background
            level6texture = assets.level6Background
            level7texture = assets.level7Background
            level8texture = assets.level8Background
            level9texture = assets.level9Background
            blockTexture = assets.tileBlock
            symbolsTexture = assets.tileSymbols
            hoverTexture = assets.tileHover
            goldTexture = assets.tileGold
            onClickField = { middle, up, right, down, left ->
                val canPlaceTile = middle.tile.isNone()
                        && !(up.tile.isNone() && right.tile.isNone() && down.tile.isNone() && left.tile.isNone())
                        && currentTile.tile.matches(up.tile)
                        && currentTile.tile.matches(right.tile)
                        && currentTile.tile.matches(down.tile)
                        && currentTile.tile.matches(left.tile)


                when {
                    currentTile.tile.isSkull() -> {
                        if (!middle.tile.isNone()) {
                            middle.tile.setNone()
                            playSound(assets.skullMoveSound)
                            bigExplosion.explosionState = 0f
                            bigExplosion.x = middle.rectangle.x - 8
                            bigExplosion.y = middle.rectangle.y - 16
                            nextCurrentTile()
                        } else {
                            playSound(assets.invalidMoveSound)
                        }
                    }
                    currentTile.tile.isBlock() -> {
                        if (canPlaceTile || fields.all { it.all { it.tile.isNone() } }) {
                            applyTileToField(middle, up, right, down, left)
                        } else {
                            playSound(assets.invalidMoveSound)
                        }
                    }
                    currentTile.tile.isSymbol() -> {
                        if (canPlaceTile) {
                            applyTileToField(middle, up, right, down, left)
                        } else {
                            playSound(assets.invalidMoveSound)
                        }
                    }
                    currentTile.tile.isNone() -> {
                    }
                }

            }
        }
        discardBackground.apply {
            x = 40f; y = 0f; width = 66f; height = 157f
            texture = assets.discardBackground
        }
        discardBubbleContainer.apply {
            x = 31f; y = 60f; width = 83f; height = 103f
            backgroundTexture = assets.discardBubbleBackground
            foregroundTexture = assets.discardBubbleForeground
            waveTexture = assets.discardBubbleWave
        }
        gems.apply {
            x = 171f; y = 443f; width = 445f; height = 29f
            gemsTexture = assets.levelGems
            gemsWithSymbolsTexture = assets.levelGemsSymbols
        }
        newGameButton.apply {
            x = 32f; y = 344f; width = 81f; height = 24f
            texture = assets.actionButtonsStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { showSelectGameWindow() }
        }
        difficultyButton.apply {
            x = 38f; y = 311f; width = 66f; height = 20f
            texture = assets.glassButtonsStrip
        }
        modeButton.apply {
            x = 38f; y = 275f; width = 66f; height = 20f
            texture = assets.glassButtonsStrip
        }
        optionsButton.apply {
            x = 32f; y = 225f; width = 81f; height = 24f
            texture = assets.actionButtonsStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { if (optionsWindow.enabled) hideSettings() else showSettings() }
        }
        discardButton.apply {
            x = 32f; y = 182f; width = 81f; height = 24f
            texture = assets.actionButtonsStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { nextCurrentTile(); increaseDiscardCounter() }
        }
        helpButton.apply {
            x = 22f; y = 44f; width = 25f; height = 24f
            texture = assets.helpActionButtonStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { }
        }
        soundButton.apply {
            x = 102f; y = 44f; width = 26f; height = 25f
            texture = assets.soundActionButtonStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { soundButton.enabled = !soundButton.enabled }
        }
        quitButton.apply {
            x = 34f; y = 18f; width = 79f; height = 25f
            texture = assets.quitActionButtonStrip
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = { Gdx.app.exit() }
        }
        levelText.apply {
            texture = FontTexture(assets.levelFont, assets.levelFontCharacterBounds, 6)
            enabled = false
            x = 407f; y = 4f
        }
        scoreText.apply {
            texture = FontTexture(assets.scoreFont, assets.scoreFontCharacterBounds, 6)
            enabled = false
            x = 74f; y = 410f
        }
        overText.apply {
            texture = FontTexture(assets.overFont, assets.overFontCharacterBounds, 6)
            enabled = false
            x = 0f; y = 0f
        }
        currentTile.apply {
            blockTexture = assets.tileBlock
            glowTexture = assets.glow
            skullTexture = assets.tileSkulls
            symbolsTexture = assets.tileSymbols
            redCrossedCircleTexture = assets.redCrossCircle
            width = 50f; height = 50f
            enabled = false
        }
        cursor.apply {
            hotspot.set(26f, 28f)
            arrowTexture = assets.cursorArrow
            hoverTexture = assets.cursorHover
            dragTexture = assets.cursorDrag
        }
        selectGameWindow.apply {
            xAlt = 160f; yAlt = 0f; width = 480f; height = 444f
            backgroundTexture = assets.selectGameBackground
            difficultyHighlightTexture = assets.selectGameDifficultyButtonSelect
            modeHighlightTexture = assets.selectGameModeButtonSelect
            startTutorialButtonTexture = assets.selectGameTutorialHover
            newGameButtonTexture = assets.selectGameNewButtonHover
            titleTexture = FontTexture(assets.titleFont, assets.titleFontCharacterBounds, 6)
            fontTexture = FontTexture(assets.mainFont, assets.mainFontCharacterBounds, 6)
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = {
                when (it) {
                    difficultyEasyRectangle -> this@BoardScreen.difficulty = 0
                    difficultyMediumRectangle -> this@BoardScreen.difficulty = 1
                    difficultyHardRectangle -> this@BoardScreen.difficulty = 2
                    typeStrategyRectangle -> this@BoardScreen.mode = 0
                    typeTimedRectangle -> this@BoardScreen.mode = 1
                    tutorialRectangle -> {
                    }
                    newGameRectangle -> startNewGame()
                }
            }
        }
        optionsWindow.apply {
            xAlt = 140f; yAlt = 0f; width = 500f; height = 464f
            backgroundTexture = assets.dialogWindow
            sliderTexture = assets.dialogSlider
            sliderGemTexture = assets.dialogSliderGem
            buttonTexture = assets.dialogButton
            checkedTexture = assets.dialogChecked
            uncheckedTexture = assets.dialogUnchecked
            titleTexture = FontTexture(assets.titleFont, assets.titleFontCharacterBounds, 6)
            fontTexture = FontTexture(assets.mainFont, assets.mainFontCharacterBounds, 6)
            enabled = false
            version = game.version
            onTouchDown = { playSound(assets.clickSound) }
            onTouchUp = {
                when (it) {
                    closeRectangle -> hideSettings()
                }
            }
        }
        bigExplosion.apply {
            width = 71f; height = 100f
            texture = assets.bigExplosion
        }
        showSelectGameWindow()
    }

    override fun render(delta: Float) {
        camera.update()
        clear()
        batch.use {
            discardBackground.draw(it)
            discardBubbleContainer.draw(it)
            boardBackground.draw(it)
            board.draw(it)
            selectGameWindow.draw(it)
            optionsWindow.draw(it)
            newGameButton.draw(it)
            difficultyButton.draw(it)
            modeButton.draw(it)
            optionsButton.draw(it)
            discardButton.draw(it)
            helpButton.draw(it)
            soundButton.draw(it)
            quitButton.draw(it)
            gems.draw(it)
            bigExplosion.draw(it)
            levelText.draw(it)
            scoreText.draw(it)
            overText.draw(it)
            currentTile.draw(it)
            cursor.draw(it)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }

    fun playSound(value: Sound) {
        if (soundButton.enabled) {
            value.play()
        }
    }

    fun advanceToNextStage() {
        assets.levelUpSound.play()
        level += 1
    }

    fun increaseDiscardCounter() {
        if (level > 0 && discardBubbleContainer.counter <= 3) {
            if (discardBubbleContainer.counter == 3) {
                playSound(assets.gameOverSound)
                showSelectGameWindow()
            } else {
                playSound(assets.discardIncreaseSound)
                discardBubbleContainer.counter += 1
                if (discardBubbleContainer.counter > 2) playSound(assets.discardWarningSound)
            }
        }
    }

    fun decreaseDiscardCounter() {
        if (level > 0 && discardBubbleContainer.counter > 0) {
            playSound(assets.discardDecreaseSound)
            discardBubbleContainer.counter -= 1
            if (discardBubbleContainer.counter > 2) playSound(assets.discardWarningSound)
        }
    }

    fun showSettings() {
        optionsWindow.enabled = true
        currentTile.enabled = false
        board.enabled = false
    }

    fun hideSettings() {
        optionsWindow.enabled = false
        currentTile.enabled = !selectGameWindow.enabled
        board.enabled = !selectGameWindow.enabled
    }

    fun showSelectGameWindow() {
        selectGameWindow.enabled = true
        scoreText.enabled = false
        levelText.enabled = false
        overText.enabled = false
        currentTile.enabled = false
        bigExplosion.enabled = false
        board.enabled = false
        discardBubbleContainer.counter = 0
        resetScore()
        level = 0
    }

    fun startNewGame() {
        selectGameWindow.enabled = false
        scoreText.enabled = true
        levelText.enabled = true
        overText.enabled = true
        currentTile.enabled = true
        bigExplosion.enabled = true
        currentTile.tile.setBlock()
        currentTile.cannotPlace = false
        board.enabled = true
        board.fields.forEach { it.forEach { it.tile.setNone(); it.gold = false } }
        level = when (difficulty) {
            0 -> 1
            1 -> 6
            2 -> 11
            else -> 0
        }
        discardBubbleContainer.counter = 0
        resetScore()
    }

    fun resetScore() {
        score = 0
        scoreText.counterAnimationPosition = 0f
    }

    fun nextCurrentTile() {
        val isBoardEmpty = board.fields.all { it.all { it.tile.isNone() } }
        if (isBoardEmpty) {
            currentTile.tile.setBlock()
        } else if ((1..20).random() == 1) {
            currentTile.tile.setSkull()
        } else if ((1..20).random() == 1) {
            currentTile.tile.setBlock()
        } else {
            currentTile.tile.setSymbol(tileTypes.random(), tileSuits.random())
        }
        currentTile.scaleOffsetTime = 0f

        var valid = false
        board.fields.forEach { fieldRow ->
            fieldRow.forEach { field ->
                if (currentTile.tile.isSymbol() || currentTile.tile.isBlock()) {
                    val up = board.getField(field.column, field.row - 1)
                    val right = board.getField(field.column + 1, field.row)
                    val down = board.getField(field.column, field.row + 1)
                    val left = board.getField(field.column - 1, field.row)
                    val isValid = field.tile.isNone()
                            && !(up.tile.isNone() && right.tile.isNone() && down.tile.isNone() && left.tile.isNone())
                            && currentTile.tile.matches(up.tile)
                            && currentTile.tile.matches(right.tile)
                            && currentTile.tile.matches(down.tile)
                            && currentTile.tile.matches(left.tile)
                            || (currentTile.tile.isBlock() && isBoardEmpty)
                    if (isValid) {
                        valid = true
                    }
                } else if (currentTile.tile.isSkull()) {
                    val isValid = !field.tile.isNone()
                    if (isValid) {
                        valid = true
                    }
                }
            }
        }
        currentTile.cannotPlace = !valid
    }

    fun applyTileToField(middle: Field, up: Field, right: Field, down: Field, left: Field) {
        val middleWasGold = middle.gold
        var addedScore = 0
        if (middle.gold) {
            var count = 0
            count += if (!up.tile.isNone()) 1 else 0
            count += if (!right.tile.isNone()) 1 else 0
            count += if (!down.tile.isNone()) 1 else 0
            count += if (!left.tile.isNone()) 1 else 0
            addedScore += when (count) {
                0 -> 1
                1 -> 1
                2 -> 2
                3 -> 3
                4 -> 5
                else -> 0
            }
        } else {
            var count = 0
            count += if (!up.tile.isNone()) 1 else 0
            count += if (!right.tile.isNone()) 1 else 0
            count += if (!down.tile.isNone()) 1 else 0
            count += if (!left.tile.isNone()) 1 else 0
            addedScore += when (count) {
                0 -> 5
                1 -> 5
                2 -> 15
                3 -> 30
                4 -> 50
                else -> 0
            }
        }
        middle.tile.type = currentTile.tile.type
        middle.tile.suit = currentTile.tile.suit
        middle.gold = true
        repeat(8) { column ->
            var cleared = false
            if (board.fields[column].all { !it.tile.isNone() }) {
                board.fields[column].forEach { it.tile.setNone() }
                cleared = true
            }
            if (cleared) {
                assets.vanishSound.play()
                discardBubbleContainer.counter = 0
                addedScore += if (middleWasGold) 5 else 50
            }
        }
        repeat(9) { column ->
            var cleared = false
            if (board.fields.all { fieldRow ->
                    !fieldRow[column].tile.isNone()
                }) {
                board.fields.forEach { it[column].tile.setNone() }
                cleared = true
            }
            if (cleared) {
                assets.vanishSound.play()
                discardBubbleContainer.counter = 0
                addedScore += if (middleWasGold) 5 else 50
            }
        }
        nextCurrentTile()
        if (board.fields.all { it.all { it.gold } }) {
            board.fields.forEach { it.forEach { it.tile.setNone(); it.gold = false } }
            currentTile.tile.setBlock()
            currentTile.cannotPlace = false
            assets.levelCompleteSound.play()
            advanceToNextStage()
        }
        overText.x = middle.rectangle.x + middle.rectangle.width / 2
        overText.y = middle.rectangle.y + middle.rectangle.height / 2
        overText.progress = 0f
        overText.value = addedScore * multiplier
        score += addedScore * multiplier
        playSound(assets.validMoveSound)
        decreaseDiscardCounter()
    }

}


