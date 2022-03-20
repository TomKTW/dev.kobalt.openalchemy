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

import de.quippy.javamod.main.JavaModMainBase
import de.quippy.javamod.main.gui.PlayThread
import de.quippy.javamod.main.playlist.PlayList
import de.quippy.javamod.multimedia.MultimediaContainer
import de.quippy.javamod.multimedia.MultimediaContainerManager
import de.quippy.javamod.multimedia.mod.ModContainer
import java.io.File
import java.net.URL
import java.util.*

class ModulePlayer: JavaModMainBase(false) {

    private val modFileName: URL? = null
    private val wavFileName: File? = null
    private val shuffle = false
    private val repeat = false
    private val initialVolume = 1.0f
    private val currentContainer: MultimediaContainer? = null
    private val playerThread: PlayThread? = null
    private val currentPlayList: PlayList? = null

    init {
        val props = Properties()
        props.setProperty(ModContainer.PROPERTY_PLAYER_ISP, Integer.parseInt("1"/*.substring(0, 1)*/).toString());
        props.setProperty(ModContainer.PROPERTY_PLAYER_STEREO, if (true) "2" else "1");
        props.setProperty(ModContainer.PROPERTY_PLAYER_WIDESTEREOMIX, if (false) "TRUE" else "FALSE");
        props.setProperty(ModContainer.PROPERTY_PLAYER_NOISEREDUCTION, if (false) "TRUE" else "FALSE");
        props.setProperty(ModContainer.PROPERTY_PLAYER_MEGABASS, if (false) "TRUE" else "FALSE");
        props.setProperty(ModContainer.PROPERTY_PLAYER_NOLOOPS, "0");
        props.setProperty(ModContainer.PROPERTY_PLAYER_MSBUFFERSIZE, Integer.toString(Integer.parseInt(/*op.substring(0,1)*/"100")));
        val sampleSizeInBits = Integer.parseInt("16");
        if (sampleSizeInBits!=8 && sampleSizeInBits!=16 && sampleSizeInBits!=24)
            throw RuntimeException("samplesize of " + sampleSizeInBits + " is not supported");
        props.setProperty(ModContainer.PROPERTY_PLAYER_BITSPERSAMPLE, Integer.toString(sampleSizeInBits));
        props.setProperty(ModContainer.PROPERTY_PLAYER_FREQUENCY, Integer.toString(Integer.parseInt("48000")));

        MultimediaContainerManager.configureContainer(props)
    }

    private val introContainer = MultimediaContainerManager.getMultimediaContainer("assets/music/alch_intro.s3m")
    private val introMixer = introContainer.createNewMixer().also {
        it.setVolume(0.5f)
    }
    private val introPlayThread = PlayThread(introMixer) {
        println(it.name)
    }

    private val musicContainer = MultimediaContainerManager.getMultimediaContainer("assets/music/1985-HeHasNoFace.s3m")
    private val musicMixer = musicContainer.createNewMixer().also {
        it.setVolume(0.5f)
    }
    private val musicPlayThread = PlayThread(musicMixer) {
        println(it.name)
    }

    fun playIntro() {
        if (musicPlayThread.isRunning) {
            musicPlayThread.stopMod()
        }
        introPlayThread.start()
    }

    fun playMusic() {
        if (introPlayThread.isRunning) {
            introPlayThread.stopMod()
        }
        musicPlayThread.start()
    }

    fun stop() {
        introMixer.pausePlayback()
        musicMixer.pausePlayback()
        introPlayThread.stopMod()
        musicPlayThread.stopMod()
    }

}