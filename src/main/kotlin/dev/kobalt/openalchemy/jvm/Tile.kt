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

import com.badlogic.gdx.graphics.Color

class Tile {

    var type: Type = Type.None
    var suit: Suit = Suit.None

    enum class Type(val position: Int) {
        None(-1),
        Block(-1),
        Skull(-1),
        SymbolAries(0),
        SymbolTaurus(1),
        SymbolGemini(2),
        SymbolCancer(3),
        SymbolLeo(4),
        SymbolLibra(5),
        SymbolScorpio(6),
        SymbolSagittarius(7),
        SymbolCapricorn(8),
        SymbolAquarius(9),
        SymbolPisces(10),
        SymbolMercury(11),
        SymbolVenus(12),
        SymbolUranus(13),
        SymbolNeptune(14),
        SymbolPentagram(15),
        SymbolDroplet(16),
        SymbolOm(17),
        SymbolLightning(18),
        SymbolGlass(19),
        SymbolDish(20),
        SymbolBlank(21);

        companion object {
            val level1 = arrayOf(SymbolAries, SymbolTaurus, SymbolGemini, SymbolCancer, SymbolLeo)
            val level2 = level1
            val level3 = level2 + SymbolLibra
            val level4 = level3 + SymbolScorpio
            val level5 = level4 + SymbolSagittarius
            val level6 = level5
            val level7 = level6 + SymbolCapricorn
            val level8 = level7 + SymbolAquarius
            val level9 = level8 + SymbolPisces
            val level10 = level9
            val level11 = level10 + SymbolMercury
            val level12 = level11 + SymbolVenus
            val level13 = level12 + SymbolUranus
            val level14 = level13 + SymbolNeptune
            val level15 = level14
            val level16 = level15 + SymbolPentagram
            val level17 = level16 + SymbolDroplet
            val level18 = level17
            val level19 = level18 + SymbolOm
            val level20 = level19 + SymbolLightning
            val level21 = level20 + SymbolGlass
            val level22 = level21
            val level23 = level22 + SymbolDish
            val level24 = level23 + SymbolBlank
            val levelMax = level24

            fun arrayFor(level: Int): Array<Type> {
                return when (level) {
                    1 -> level1
                    2 -> level2
                    3 -> level3
                    4 -> level4
                    5 -> level5
                    6 -> level6
                    7 -> level7
                    8 -> level8
                    9 -> level9
                    10 -> level10
                    11 -> level11
                    12 -> level12
                    13 -> level13
                    14 -> level14
                    15 -> level15
                    16 -> level16
                    17 -> level17
                    18 -> level18
                    19 -> level19
                    20 -> level20
                    21 -> level21
                    22 -> level22
                    23 -> level23
                    24 -> level24
                    else -> levelMax
                }
            }
        }
    }

    enum class Suit(val color: Color) {
        None(Color.CLEAR),
        Red(Color.RED),
        Green(Color.GREEN),
        Blue(Color.BLUE),
        Magenta(Color.MAGENTA),
        Yellow(Color.YELLOW),
        Cyan(Color.CYAN),
        Orange(Color.ORANGE),
        White(Color.WHITE),
        Gray(Color.GRAY);

        companion object {
            val level1 = arrayOf(Red, Green, Blue, Magenta)
            val level2 = level1 + Yellow
            val level3 = level2
            val level4 = level3
            val level5 = level4
            val level6 = level5 + Cyan
            val level7 = level6
            val level8 = level7
            val level9 = level8
            val level10 = level9 + Orange
            val level11 = level10
            val level12 = level11
            val level13 = level12
            val level14 = level13
            val level15 = level14 + White
            val level16 = level15
            val level17 = level16
            val level18 = level17 + Gray
            val level19 = level18
            val level20 = level19
            val level21 = level20
            val level22 = level21
            val level23 = level22
            val level24 = level23
            val levelMax = level24

            fun arrayFor(level: Int): Array<Suit> {
                return when (level) {
                    1 -> level1
                    2 -> level2
                    3 -> level3
                    4 -> level4
                    5 -> level5
                    6 -> level6
                    7 -> level7
                    8 -> level8
                    9 -> level9
                    10 -> level10
                    11 -> level11
                    12 -> level12
                    13 -> level13
                    14 -> level14
                    15 -> level15
                    16 -> level16
                    17 -> level17
                    18 -> level18
                    19 -> level19
                    20 -> level20
                    21 -> level21
                    22 -> level22
                    23 -> level23
                    24 -> level24
                    else -> levelMax
                }
            }
        }
    }

    fun isNone() = type == Type.None
    fun isBlock() = type == Type.Block
    fun isSkull() = type == Type.Skull
    fun isSymbol() = type.position != -1

    fun setNone() {
        type = Type.None; suit = Suit.None
    }

    fun setBlock() {
        type = Type.Block; suit = Suit.None
    }

    fun setSkull() {
        type = Type.Skull; suit = Suit.None
    }

    fun setSymbol(type: Type, suit: Suit) {
        this.type = type; this.suit = suit
    }

    fun matches(tile: Tile): Boolean {
        return if (isSymbol() && tile.isSymbol()) {
            type == tile.type || suit == tile.suit
        } else isBlock() || tile.isBlock() || isNone() || tile.isNone()
    }
}