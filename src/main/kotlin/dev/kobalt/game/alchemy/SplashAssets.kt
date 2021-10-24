package dev.kobalt.game.alchemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Disposable

class SplashAssets : Disposable {

    val background = Texture(Gdx.files.local("assets/images/title.jpg")).applyLinearFilter()
    val progress = Texture(Gdx.files.local("assets/images/greenbar.png")).applyLinearFilter()
    val logo = Texture(Gdx.files.classpath("kobalt-lab-logo.png")).applyLinearFilter()
    val playButton = Pixmap(1, 1, Pixmap.Format.RGBA8888).also { it.setColor(0.0f, 0.0f, 0.0f, 0.5f); it.fill() }.toTexture()
    val mainFont = mergedTexture(
        Texture(Gdx.files.local("assets/images/_font1.png")).toPixmap().apply { setColor(Color.WHITE); fill() }
            .toTexture(),
        Texture(Gdx.files.local("assets/images/_font1.png"))
    ).applyLinearFilter()
    val playFont = mergedTexture(
        Texture(Gdx.files.local("assets/images/_afont1.gif")).toPixmap().apply { setColor(Color.WHITE); fill() }
            .toTexture(),
        Texture(Gdx.files.local("assets/images/_afont1.gif"))
    ).applyLinearFilter()

    override fun dispose() {
        background.dispose()
        progress.dispose()
        logo.dispose()
        mainFont.dispose()
        playFont.dispose()
        playButton.dispose()
    }

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

    // Reference: /assets/data/afont1.txt
    val playFontCharacterBounds = arrayOf(
        'A' to (Rectangle(   0f,  0f, 12f, 22f)),
        'B' to (Rectangle(  12f,  0f,  9f, 22f)),
        'C' to (Rectangle(  21f,  0f, 15f, 22f)),
        'D' to (Rectangle(  36f,  0f, 12f, 22f)),
        'E' to (Rectangle(  48f,  0f, 12f, 22f)),
        'F' to (Rectangle(  60f,  0f, 10f, 22f)),
        'G' to (Rectangle(  70f,  0f, 15f, 22f)),
        'H' to (Rectangle(  85f,  0f, 13f, 22f)),
        'I' to (Rectangle(  98f,  0f,  4f, 22f)),
        'J' to (Rectangle( 102f,  0f,  8f, 22f)),
        'K' to (Rectangle( 110f,  0f, 14f, 22f)),
        'L' to (Rectangle( 124f,  0f,  9f, 22f)),
        'M' to (Rectangle( 133f,  0f, 16f, 22f)),
        'N' to (Rectangle( 149f,  0f, 13f, 22f)),
        'O' to (Rectangle( 162f,  0f, 19f, 22f)),
        'P' to (Rectangle( 181f,  0f,  9f, 22f)),
        'Q' to (Rectangle( 190f,  0f, 19f, 22f)),
        'R' to (Rectangle( 209f,  0f, 13f, 22f)),
        'S' to (Rectangle( 222f,  0f, 11f, 22f)),
        'T' to (Rectangle( 233f,  0f, 11f, 22f)),
        'U' to (Rectangle( 244f,  0f, 12f, 22f)),
        'V' to (Rectangle( 256f,  0f, 12f, 22f)),
        'W' to (Rectangle( 268f,  0f, 20f, 22f)),
        'X' to (Rectangle( 288f,  0f, 16f, 22f)),
        'Y' to (Rectangle( 304f,  0f, 15f, 22f)),
        'Z' to (Rectangle( 319f,  0f, 13f, 22f)),
        '0' to (Rectangle( 332f,  0f, 19f, 22f)),
        '1' to (Rectangle( 351f,  0f,  8f, 22f)),
        '2' to (Rectangle( 359f,  0f, 13f, 22f)),
        '3' to (Rectangle( 372f,  0f, 13f, 22f)),
        '4' to (Rectangle( 385f,  0f, 15f, 22f)),
        '5' to (Rectangle( 400f,  0f, 13f, 22f)),
        '6' to (Rectangle( 413f,  0f, 14f, 22f)),
        '7' to (Rectangle( 427f,  0f, 13f, 22f)),
        '8' to (Rectangle( 440f,  0f, 13f, 22f)),
        '9' to (Rectangle( 453f,  0f, 13f, 22f)),
        '-' to (Rectangle( 466f,  0f, 10f, 22f)),
        ',' to (Rectangle( 476f,  0f,  5f, 22f)),
        '.' to (Rectangle( 481f,  0f,  5f, 22f)),
        '?' to (Rectangle( 486f,  0f, 11f, 22f)),
        '$' to (Rectangle( 497f,  0f, 10f, 22f)),
        '*' to (Rectangle( 507f,  0f, 12f, 22f)),
        '!' to (Rectangle( 519f,  0f,  4f, 22f)),
        '+' to (Rectangle( 523f,  0f, 11f, 22f)),
        '@' to (Rectangle( 534f,  0f, 19f, 22f)),
    ).toMap()

    val mainFontTexture = FontTexture(mainFont, mainFontCharacterBounds, 6)
    val playFontTexture = FontTexture(playFont, playFontCharacterBounds ,6)
}