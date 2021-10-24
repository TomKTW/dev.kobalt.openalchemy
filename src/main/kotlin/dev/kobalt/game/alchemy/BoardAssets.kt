package dev.kobalt.game.alchemy

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

class BoardAssets : BaseAssets() {

    inline val boardBackground: Texture get() = assets["boardBackground"]
    inline val cursorArrow: Texture get() = assets["cursorArrow"]
    inline val cursorHover: Texture get() = assets["cursorHover"]
    inline val cursorDrag: Texture get() = assets["cursorDrag"]
    inline val dialogWindow: Texture get() = assets["dialogWindow"]
    inline val glow: Texture get() = assets["glow"]
    inline val tileSymbols: Texture get() = assets["tileSymbols"]
    inline val tileSkulls: Texture get() = assets["tileSkulls"]
    inline val tileBlock: Texture get() = assets["tileBlock"]
    inline val tileHover: Texture get() = assets["tileHover"]
    inline val tileGold: Texture get() = assets["tileGold"]
    inline val mainFont: Texture get() = assets["mainFont"]
    inline val scoreFont: Texture get() = assets["scoreFont"]
    inline val levelFont: Texture get() = assets["levelFont"]
    inline val overFont: Texture get() = assets["overFont"]
    inline val titleFont: Texture get() = assets["titleFont"]
    inline val redCrossCircle: Texture get() = assets["redCrossCircle"]
    inline val discardBackground: Texture get() = assets["discardBackground"]
    inline val discardBubbleBurst: Texture get() = assets["discardBubbleBurst"]
    inline val discardBubbleBackground: Texture get() = assets["discardBubbleBackground"]
    inline val discardBubbleForeground: Texture get() = assets["discardBubbleForeground"]
    inline val discardBubbleTiny: Texture get() = assets["discardBubbleTiny"]
    inline val discardBubbleWave: Texture get() = assets["discardBubbleWave"]
    inline val actionButtonsStrip: Texture get() = assets["actionButtonsStrip"]
    inline val glassButtonsStrip: Texture get() = assets["glassButtonsStrip"]
    inline val quitActionButtonStrip: Texture get() = assets["quitActionButtonStrip"]
    inline val helpActionButtonStrip: Texture get() = assets["helpActionButtonStrip"]
    inline val soundActionButtonStrip: Texture get() = assets["soundActionButtonStrip"]
    inline val selectGameBackground: Texture get() = assets["selectGameBackground"]
    inline val selectGameDifficultyButtonSelect: Texture get() = assets["selectGameDifficultyButtonSelect"]
    inline val selectGameModeButtonSelect: Texture get() = assets["selectGameModeButtonSelect"]
    inline val selectGameNewButtonHover: Texture get() = assets["selectGameNewButtonHover"]
    inline val selectGameTutorialHover: Texture get() = assets["selectGameTutorialHover"]
    inline val dialogButton: Texture get() = assets["dialogButton"]
    inline val dialogSlider: Texture get() = assets["dialogSlider"]
    inline val dialogSliderGem: Texture get() = assets["dialogSliderGem"]
    inline val dialogChecked: Texture get() = assets["dialogChecked"]
    inline val dialogUnchecked: Texture get() = assets["dialogUnchecked"]
    inline val level2Background: Texture get() = assets["level2Background"]
    inline val level3Background: Texture get() = assets["level3Background"]
    inline val level4Background: Texture get() = assets["level4Background"]
    inline val level5Background: Texture get() = assets["level5Background"]
    inline val level6Background: Texture get() = assets["level6Background"]
    inline val level7Background: Texture get() = assets["level7Background"]
    inline val level8Background: Texture get() = assets["level8Background"]
    inline val level9Background: Texture get() = assets["level9Background"]
    inline val levelGems: Texture get() = assets["levelGems"]
    inline val levelGemsSymbols: Texture get() = assets["levelGemsSymbols"]
    inline val tinySparkleParticle: Texture get() = assets["tinySparkleParticle"]
    inline val bigExplosion: Texture get() = assets["bigExplosion"]
    inline val gameOverText: Texture get() = assets["gameOverText"]
    inline val clickSound: Sound get() = assets["clickSound"]
    inline val invalidMoveSound: Sound get() = assets["invalidMoveSound"]
    inline val validMoveSound: Sound get() = assets["validMoveSound"]
    inline val skullMoveSound: Sound get() = assets["skullMoveSound"]
    inline val vanishSound: Sound get() = assets["vanishSound"]
    inline val gameOverSound: Sound get() = assets["gameOverSound"]
    inline val levelCompleteSound: Sound get() = assets["levelCompleteSound"]
    inline val levelUpSound: Sound get() = assets["levelUpSound"]
    inline val discardDecreaseSound: Sound get() = assets["discardDecreaseSound"]
    inline val discardIncreaseSound: Sound get() = assets["discardIncreaseSound"]
    inline val discardWarningSound: Sound get() = assets["discardWarningSound"]
    inline val slidebackSound: Sound get() = assets["slidebackSound"]
    inline val voiceGameOverSound: Sound get() = assets["voiceGameOverSound"]
    inline val voiceLevelCompleteSound: Sound get() = assets["voiceLevelCompleteSound"]
    inline val voiceWelcomeSound: Sound get() = assets["voiceWelcomeSound"]

    var total = 64
    var loaded = 0
    inline val progress: Float get() = loaded.toFloat() / total.toFloat()


    override suspend fun onLoad() {
        super.onLoad()
        assets.apply {
            add(
                "boardBackground", mergedTexture(
                    load("assets/images/backdrop.jpg"),
                    load("assets/images/_backdrop.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "cursorArrow", mergedTexture(
                    load("assets/images/cursor1.gif"),
                    load("assets/images/_cursor1.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "cursorHover", mergedTexture(
                    load("assets/images/pointer2.gif"),
                    load("assets/images/_pointer2.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "cursorDrag", mergedTexture(
                    load("assets/images/overhand.gif"),
                    load("assets/images/_overhand.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "dialogWindow", mergedTexture(
                    load("assets/images/dialog.gif"),
                    load("assets/images/_dialog.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "glow", mergedTexture(
                    load<Texture>("assets/images/light.gif").toPixmap().apply { setColor(Color.WHITE); fill() }.toTexture(),
                    load("assets/images/light.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "tileSymbols", mergedTexture(
                    load("assets/images/symbols.gif"),
                    load("assets/images/_symbols.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "tileSkulls", mergedTexture(
                    load("assets/images/skulls.gif"),
                    load("assets/images/_skulls.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "tileHover", Pixmap(52, 52, Pixmap.Format.RGBA4444).also {
                    it.setColor(1.0f, 1.0f, 1.0f, 0.2f)
                    it.fill()
                }.let {
                    val texture = it.toTexture()
                    it.dispose()
                    texture
                }.applyLinearFilter()
            ); loaded += 1
            add(
                "tileGold", Pixmap(52, 52, Pixmap.Format.RGBA4444).also {
                    it.setColor(1.0f, 1.0f, 0.0f, 0.2f)
                    it.fill()
                }.let {
                    val texture = it.toTexture()
                    it.dispose()
                    texture
                }.applyLinearFilter()
            ); loaded += 1
            add("tileBlock", load<Texture>("assets/images/nullblock.png").applyLinearFilter()); loaded += 1
            add(
                "bigExplosion", mergedTexture(
                    load("assets/images/bigexplosion.gif"),
                    load("assets/images/bigexplosion.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "mainFont", mergedTexture(
                    load<Texture>("assets/images/_font1.png").toPixmap().apply { setColor(Color.WHITE); fill() }.toTexture(),
                    load("assets/images/_font1.png")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "scoreFont", mergedTexture(
                    load("assets/images/scorefont.gif"),
                    load("assets/images/_scorefont.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add(
                "titleFont", mergedTexture(
                    load("assets/images/titlefont.gif"),
                    load("assets/images/_titlefont.gif")
                ).applyLinearFilter()
            ); loaded += 1
            add("levelFont", load<Texture>("assets/images/levelfont1.png").applyLinearFilter()); loaded += 1
            add("overFont", load<Texture>("assets/images/overfont1.png").applyLinearFilter()); loaded += 1
            add("redCrossCircle", load<Texture>("assets/images/redcircle.png").applyLinearFilter()); loaded += 1
            add("discardBackground", load<Texture>("assets/images/forgeback2.gif").applyLinearFilter()); loaded += 1
            add("discardBubbleBurst", load<Texture>("assets/images/bubbleBurst.gif").applyLinearFilter()); loaded += 1
            add("discardBubbleBackground", load<Texture>("assets/images/bubbles2.png").applyLinearFilter()); loaded += 1
            add("discardBubbleForeground", load<Texture>("assets/images/bubbles7.gif").applyLinearFilter()); loaded += 1
            add("discardBubbleTiny", load<Texture>("assets/images/tinybubble1.gif").applyLinearFilter()); loaded += 1
            add("discardBubbleWave", load<Texture>("assets/images/wave1.gif").applyLinearFilter()); loaded += 1
            add("actionButtonsStrip", load<Texture>("assets/images/buttonstrip.gif").applyLinearFilter()); loaded += 1
            add("glassButtonsStrip", load<Texture>("assets/images/glassbuttons.gif").applyLinearFilter()); loaded += 1
            add("quitActionButtonStrip", load<Texture>("assets/images/quitgamestrip.gif").applyLinearFilter()); loaded += 1
            add("helpActionButtonStrip", load<Texture>("assets/images/helpstrip.png").applyLinearFilter()); loaded += 1
            add("soundActionButtonStrip", load<Texture>("assets/images/soundstrip.gif").applyLinearFilter()); loaded += 1
            add("selectGameBackground", load<Texture>("assets/images/gameselector_base.jpg").applyLinearFilter()); loaded += 1
            add("selectGameDifficultyButtonSelect", load<Texture>("assets/images/gameselector_greenon.jpg").applyLinearFilter()); loaded += 1
            add("selectGameModeButtonSelect", load<Texture>("assets/images/gameselector_redon.jpg").applyLinearFilter()); loaded += 1
            add("selectGameNewButtonHover", load<Texture>("assets/images/gameselector_newgameon.jpg").applyLinearFilter()); loaded += 1
            add("selectGameTutorialHover", load<Texture>("assets/images/gameselector_tuton.jpg").applyLinearFilter()); loaded += 1
            add("dialogButton", load<Texture>("assets/images/dbutton.png").applyLinearFilter()); loaded += 1
            add("dialogSlider", load<Texture>("assets/images/slider.png").applyLinearFilter()); loaded += 1
            add("dialogSliderGem", load<Texture>("assets/images/slidergem.png").applyLinearFilter()); loaded += 1
            add("dialogChecked", load<Texture>("assets/images/checked.png").applyLinearFilter()); loaded += 1
            add("dialogUnchecked", load<Texture>("assets/images/unchecked.png").applyLinearFilter()); loaded += 1
            add("level2Background", load<Texture>("assets/images/level2.jpg").applyLinearFilter()); loaded += 1
            add("level3Background", load<Texture>("assets/images/level3.jpg").applyLinearFilter()); loaded += 1
            add("level4Background", load<Texture>("assets/images/level4.jpg").applyLinearFilter()); loaded += 1
            add("level5Background", load<Texture>("assets/images/level5.jpg").applyLinearFilter()); loaded += 1
            add("level6Background", load<Texture>("assets/images/level6.jpg").applyLinearFilter()); loaded += 1
            add("level7Background", load<Texture>("assets/images/level7.jpg").applyLinearFilter()); loaded += 1
            add("level8Background", load<Texture>("assets/images/level8.jpg").applyLinearFilter()); loaded += 1
            add("level9Background", load<Texture>("assets/images/level9.jpg").applyLinearFilter()); loaded += 1
            add("levelGems", load<Texture>("assets/images/gems1.gif").applyLinearFilter()); loaded += 1
            add("levelGemsSymbols", load<Texture>("assets/images/gems2.gif").applyLinearFilter()); loaded += 1
            add("clickSound", load<Sound>("assets/sounds/click.ogg")); loaded += 1
            add("invalidMoveSound", load<Sound>("assets/sounds/bad.ogg")); loaded += 1
            add("validMoveSound", load<Sound>("assets/sounds/thunk.ogg")); loaded += 1
            add("skullMoveSound", load<Sound>("assets/sounds/skull.ogg")); loaded += 1
            add("vanishSound", load<Sound>("assets/sounds/finalvanish.ogg")); loaded += 1
            add("gameOverSound", load<Sound>("assets/sounds/endofgame.ogg")); loaded += 1
            add("levelCompleteSound", load<Sound>("assets/sounds/finalgong.ogg")); loaded += 1
            add("levelUpSound", load<Sound>("assets/sounds/gemchange.ogg")); loaded += 1
            add("discardDecreaseSound", load<Sound>("assets/sounds/forgedown.ogg")); loaded += 1
            add("discardIncreaseSound", load<Sound>("assets/sounds/melt.ogg")); loaded += 1
            add("discardWarningSound", load<Sound>("assets/sounds/warning.ogg")); loaded += 1
            add("slidebackSound", load<Sound>("assets/sounds/slideback.ogg")); loaded += 1
            add("voiceGameOverSound", load<Sound>("assets/sounds/v4-gameover.ogg")); loaded += 1
            add("voiceLevelCompleteSound", load<Sound>("assets/sounds/v4-levelcomplete.ogg")); loaded += 1
            add("voiceWelcomeSound", load<Sound>("assets/sounds/v4-welcome.ogg")); loaded += 1
        }
    }

    // Reference: /assets/data/scorefont.txt
    val scoreFontCharacterBounds = arrayOf(
        '0' to Rectangle(0f, 0f, 10f, 18f),
        '1' to Rectangle(0f + 10f, 0f, 10f, 18f),
        '2' to Rectangle(0f + 10f + 10f, 0f, 10f, 18f),
        '3' to Rectangle(0f + 10f + 10f + 10f, 0f, 10f, 18f),
        '4' to Rectangle(0f + 10f + 10f + 10f + 10f, 0f, 10f, 18f),
        '5' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f, 0f, 10f, 18f),
        '6' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f + 10f,  0f, 10f, 18f),
        '7' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f + 10f + 10f, 0f, 10f, 18f),
        '8' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f, 0f, 10f, 18f),
        '9' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f, 0f, 10f, 18f),
        '+' to Rectangle(0f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f + 10f, 0f, 10f, 18f)
    ).toMap()

    // Reference: /assets/data/levelfont1.txt
    val levelFontCharacterBounds = arrayOf(
        '0' to Rectangle(0f, 0f, 9f, 16f),
        '1' to Rectangle(0f + 9f, 0f, 5f, 16f),
        '2' to Rectangle(0f + 9f + 5f, 0f, 8f, 16f),
        '3' to Rectangle(0f + 9f + 5f + 8f, 0f, 10f, 16f),
        '4' to Rectangle(0f + 9f + 5f + 8f + 10f, 0f, 9f, 16f),
        '5' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f, 0f, 8f, 16f),
        '6' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f + 8f,  0f, 8f, 16f),
        '7' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f + 8f + 8f, 0f, 8f, 16f),
        '8' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f + 8f + 8f + 8f, 0f, 8f, 16f),
        '9' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f + 8f + 8f + 8f + 8f, 0f, 8f, 16f),
        '+' to Rectangle(0f + 9f + 5f + 8f + 10f + 9f + 8f + 8f + 8f + 8f + 8f, 0f, 8f, 16f)
    ).toMap()

    // Reference: /assets/data/overfont1.txt
    val overFontCharacterBounds = arrayOf(
        '0' to Rectangle(0f, 0f, 26f, 30f),
        '1' to Rectangle(0f + 26f, 0f, 17f, 30f),
        '2' to Rectangle(0f + 26f + 17f, 0f, 26f, 30f),
        '3' to Rectangle(0f + 26f + 17f + 26f, 0f, 24f, 30f),
        '4' to Rectangle(0f + 26f + 17f + 26f + 24f, 0f, 27f, 30f),
        '5' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f, 0f, 23f, 30f),
        '6' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f + 23f, 0f, 25f, 30f),
        '7' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f + 23f + 25f, 0f, 23f, 30f),
        '8' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f + 23f + 25f + 25f, 0f, 25f, 30f),
        '9' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f + 23f + 25f + 25f + 25f, 0f, 25f, 30f),
        '+' to Rectangle(0f + 26f + 17f + 26f + 24f + 27f + 23f + 25f + 25f + 25f + 23f, 0f, 23f, 30f)
    ).toMap()

    // Reference: /assets/data/font1.txt
    val mainFontCharacterBounds = arrayOf(
        'A' to (Rectangle(0f, 0f, 12f, 20f)),
        'B' to (Rectangle(12f, 0f, 10f, 20f)),
        'C' to (Rectangle(22f, 0f, 13f, 20f)),
        'D' to (Rectangle(35f, 0f, 12f, 20f)),
        'E' to (Rectangle(47f, 0f, 10f, 20f)),
        'F' to (Rectangle(57f, 0f, 11f, 20f)),
        'G' to (Rectangle(68f, 0f, 12f, 20f)),
        'H' to (Rectangle(80f, 0f, 13f, 20f)),
        'I' to (Rectangle(93f, 0f, 6f, 20f)),
        'J' to (Rectangle(99f, 0f, 6f, 20f)),
        'K' to (Rectangle(105f, 0f, 12f, 20f)),
        'L' to (Rectangle(117f, 0f, 10f, 20f)),
        'M' to (Rectangle(127f, 0f, 16f, 20f)),
        'N' to (Rectangle(143f, 0f, 13f, 20f)),
        'O' to (Rectangle(156f, 0f, 14f, 20f)),
        'P' to (Rectangle(170f, 0f, 10f, 20f)),
        'Q' to (Rectangle(180f, 0f, 14f, 20f)),
        'R' to (Rectangle(194f, 0f, 10f, 20f)),
        'S' to (Rectangle(204f, 0f, 10f, 20f)),
        'T' to (Rectangle(214f, 0f, 11f, 20f)),
        'U' to (Rectangle(225f, 0f, 13f, 20f)),
        'V' to (Rectangle(238f, 0f, 14f, 20f)),
        'W' to (Rectangle(252f, 0f, 16f, 20f)),
        'X' to (Rectangle(268f, 0f, 12f, 20f)),
        'Y' to (Rectangle(280f, 0f, 12f, 20f)),
        'Z' to (Rectangle(292f, 0f, 11f, 20f)),
        'a' to (Rectangle(303f, 0f, 6f, 20f)),
        'b' to (Rectangle(309f, 0f, 8f, 20f)),
        'c' to (Rectangle(317f, 0f, 7f, 20f)),
        'd' to (Rectangle(324f, 0f, 8f, 20f)),
        'e' to (Rectangle(332f, 0f, 7f, 20f)),
        'f' to (Rectangle(339f, 0f, 7f, 20f)),
        'g' to (Rectangle(346f, 0f, 7f, 20f)),
        'h' to (Rectangle(353f, 0f, 9f, 20f)),
        'i' to (Rectangle(362f, 0f, 5f, 20f)),
        'j' to (Rectangle(367f, 0f, 5f, 20f)),
        'k' to (Rectangle(372f, 0f, 8f, 20f)),
        'l' to (Rectangle(380f, 0f, 6f, 20f)),
        'm' to (Rectangle(386f, 0f, 12f, 20f)),
        'n' to (Rectangle(398f, 0f, 9f, 20f)),
        'o' to (Rectangle(407f, 0f, 8f, 20f)),
        'p' to (Rectangle(415f, 0f, 8f, 20f)),
        'q' to (Rectangle(423f, 0f, 7f, 20f)),
        'r' to (Rectangle(430f, 0f, 6f, 20f)),
        's' to (Rectangle(436f, 0f, 7f, 20f)),
        't' to (Rectangle(443f, 0f, 5f, 20f)),
        'u' to (Rectangle(448f, 0f, 8f, 20f)),
        'v' to (Rectangle(456f, 0f, 8f, 20f)),
        'w' to (Rectangle(464f, 0f, 11f, 20f)),
        'x' to (Rectangle(475f, 0f, 8f, 20f)),
        'y' to (Rectangle(483f, 0f, 8f, 20f)),
        'z' to (Rectangle(491f, 0f, 7f, 20f)),
        '0' to (Rectangle(498f, 0f, 9f, 20f)),
        '1' to (Rectangle(507f, 0f, 5f, 20f)),
        '2' to (Rectangle(512f, 0f, 7f, 20f)),
        '3' to (Rectangle(519f, 0f, 8f, 20f)),
        '4' to (Rectangle(527f, 0f, 8f, 20f)),
        '5' to (Rectangle(535f, 0f, 8f, 20f)),
        '6' to (Rectangle(543f, 0f, 9f, 20f)),
        '7' to (Rectangle(552f, 0f, 7f, 20f)),
        '8' to (Rectangle(559f, 0f, 9f, 20f)),
        '9' to (Rectangle(568f, 0f, 7f, 20f)),
        '`' to (Rectangle(575f, 0f, 5f, 20f)),
        '!' to (Rectangle(580f, 0f, 4f, 20f)),
        '@' to (Rectangle(584f, 0f, 14f, 20f)),
        '#' to (Rectangle(598f, 0f, 8f, 20f)),
        '$' to (Rectangle(606f, 0f, 7f, 20f)),
        '%' to (Rectangle(613f, 0f, 12f, 20f)),
        '&' to (Rectangle(625f, 0f, 11f, 20f)),
        '*' to (Rectangle(636f, 0f, 7f, 20f)),
        '(' to (Rectangle(643f, 0f, 6f, 20f)),
        ')' to (Rectangle(649f, 0f, 6f, 20f)),
        '+' to (Rectangle(655f, 0f, 11f, 20f)),
        '=' to (Rectangle(666f, 0f, 9f, 20f)),
        '\\' to (Rectangle(675f, 0f, 10f, 20f)),
        '/' to (Rectangle(685f, 0f, 10f, 20f)),
        '\'' to (Rectangle(695f, 0f, 4f, 20f)),
        '"' to (Rectangle(699f, 0f, 5f, 20f)),
        ':' to (Rectangle(704f, 0f, 4f, 20f)),
        '?' to (Rectangle(708f, 0f, 6f, 20f)),
        '-' to (Rectangle(714f, 0f, 5f, 20f)),
        '.' to (Rectangle(719f, 0f, 7f, 20f)),
        ',' to (Rectangle(726f, 0f, 7f, 20f))
    ).toMap()

    // Reference: /assets/data/titlefont.txt
    val titleFontCharacterBounds = arrayOf(
        'A' to (Rectangle(   0f,  0f, 22f, 34f)),
        'B' to (Rectangle(  22f,  0f, 20f, 34f)),
        'C' to (Rectangle(  42f,  0f, 23f, 34f)),
        'D' to (Rectangle(  65f,  0f, 23f, 34f)),
        'E' to (Rectangle(  88f,  0f, 19f, 34f)),
        'F' to (Rectangle( 107f,  0f, 18f, 34f)),
        'G' to (Rectangle( 125f,  0f, 23f, 34f)),
        'H' to (Rectangle( 148f,  0f, 25f, 34f)),
        'I' to (Rectangle( 173f,  0f, 11f, 34f)),
        'J' to (Rectangle( 184f,  0f, 13f, 34f)),
        'K' to (Rectangle( 197f,  0f, 24f, 34f)),
        'L' to (Rectangle( 221f,  0f, 20f, 34f)),
        'M' to (Rectangle( 241f,  0f, 29f, 34f)),
        'N' to (Rectangle( 270f,  0f, 24f, 34f)),
        'O' to (Rectangle( 294f,  0f, 27f, 34f)),
        'P' to (Rectangle( 321f,  0f, 19f, 34f)),
        'Q' to (Rectangle( 340f,  0f, 27f, 34f)),
        'R' to (Rectangle( 367f,  0f, 19f, 34f)),
        'S' to (Rectangle( 386f,  0f, 20f, 34f)),
        'T' to (Rectangle( 406f,  0f, 20f, 34f)),
        'U' to (Rectangle( 426f,  0f, 26f, 34f)),
        'V' to (Rectangle( 452f,  0f, 25f, 34f)),
        'W' to (Rectangle( 477f,  0f, 29f, 34f)),
        'X' to (Rectangle( 506f,  0f, 24f, 34f)),
        'Y' to (Rectangle( 530f,  0f, 24f, 34f)),
        'Z' to (Rectangle( 554f,  0f, 20f, 34f)),
        '0' to (Rectangle( 574f,  0f, 16f, 34f)),
        '1' to (Rectangle( 590f,  0f, 12f, 34f)),
        '2' to (Rectangle( 602f,  0f, 15f, 34f)),
        '3' to (Rectangle( 617f,  0f, 17f, 34f)),
        '4' to (Rectangle( 634f,  0f, 16f, 34f)),
        '5' to (Rectangle( 650f,  0f, 15f, 34f)),
        '6' to (Rectangle( 665f,  0f, 18f, 34f)),
        '7' to (Rectangle( 683f,  0f, 14f, 34f)),
        '8' to (Rectangle( 697f,  0f, 16f, 34f)),
        '9' to (Rectangle( 713f,  0f, 17f, 34f)),
        '!' to (Rectangle( 730f,  0f, 10f, 34f)),
        '?' to (Rectangle( 740f,  0f, 14f, 34f)),
        ',' to (Rectangle( 754f,  0f,  9f, 34f)),
        '.' to (Rectangle( 763f,  0f,  9f, 34f)),
        '+' to (Rectangle( 772f,  0f, 20f, 34f)),
        '@' to (Rectangle( 792f,  0f, 25f, 34f)),
        '$' to (Rectangle( 817f,  0f, 15f, 34f)),
        '&' to (Rectangle( 832f,  0f, 23f, 34f))
    ).toMap()
}