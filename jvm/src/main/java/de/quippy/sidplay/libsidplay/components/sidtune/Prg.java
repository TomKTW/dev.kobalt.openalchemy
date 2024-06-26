/**
 *                         C64 PRG file format support.
 *                         ----------------------------
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package de.quippy.sidplay.libsidplay.components.sidtune;

import static de.quippy.sidplay.libsidplay.components.sidtune.SidTune.SIDTUNE_COMPATIBILITY_BASIC;
import static de.quippy.sidplay.libsidplay.components.sidtune.SidTune.LoadStatus.LOAD_ERROR;
import static de.quippy.sidplay.libsidplay.components.sidtune.SidTune.LoadStatus.LOAD_NOT_MINE;
import static de.quippy.sidplay.libsidplay.components.sidtune.SidTune.LoadStatus.LOAD_OK;
import de.quippy.sidplay.libsidplay.components.sidtune.SidTune.LoadStatus;

public class Prg {

	private static final String _sidtune_format_prg = "Tape image file (PRG)";

	private static final String _sidtune_truncated = "ERROR: File is most likely truncated";

	private SidTune sidtune;

	private SidTuneInfo info;

	public Prg(SidTune sidTune) {
		this.sidtune = sidTune;
		this.info = sidTune.info;
	}

	protected LoadStatus PRG_fileSupport(final String fileName,
			Buffer_sidtt /* Buffer_sidtt<const uint_least8_t>& */dataBuf) {
		int lastIndexOf = fileName.lastIndexOf(".");
		String ext = lastIndexOf != -1 ? fileName.substring(lastIndexOf) : "";
		if (!ext.equalsIgnoreCase(".prg") && !ext.equalsIgnoreCase(".c64")) {
			return LOAD_NOT_MINE;
		}

		info.formatString = _sidtune_format_prg;
		if (dataBuf.len() < 2) {
			info.formatString = _sidtune_truncated;
			return LOAD_ERROR;
		}

		// Automatic settings
		info.songs = 1;
		info.startSong = 1;
		info.compatibility = SIDTUNE_COMPATIBILITY_BASIC;
		info.numberOfInfoStrings = 0;

		// Create the speed/clock setting table.
		sidtune.convertOldStyleSpeedToTables(~0, info.clockSpeed);
		return LOAD_OK;
	}

}
