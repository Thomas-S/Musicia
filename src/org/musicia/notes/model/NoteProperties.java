/** License (BSD Style License):
 *  Copyright (c) 2011
 *  Thomas Schulz
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  - The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package org.musicia.notes.model;

import com.google.inject.Inject;

/**
 * This class encapsulates the essential properties of a note.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
class NoteProperties {

	/**
	 * The pitch of the note [0-10].
	 */
	int octave = 5;

	/**
	 * This field models the duration of a note (e.g. 0.25 for a quarter note).
	 */
	double durationAsDouble = 0.25;

	/**
	 * This field models the duration of a note (e.g. "q" for a quarter note).
	 */
	String durationAsString = "q";

	/**
	 * The table that contains durations as string and double.
	 */
	@Inject(optional = true)
	NoteDurationTable durationTable;

	/**
	 * Creates new note properties.
	 * 
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 * @param duration
	 *            The duration of the note as floating point number [0.25 for quarter].
	 */
	NoteProperties(int octave, double duration) {
		this.octave = octave;
		this.durationAsDouble = duration;
		this.durationAsString = durationTable.getAsString(duration);
	}

	/**
	 * Creates new note properties.
	 * 
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 * @param duration
	 *            The duration of the note as string ["q" for quarter].
	 */
	NoteProperties(int octave, String duration) {
		this.octave = octave;
		this.durationAsString = duration;
		this.durationAsDouble = durationTable.getAsDouble(duration);
	}

	// ESCA-JAVA0131:
	/**
	 * Checks if two NoteNames are equal.
	 * 
	 * @param properties
	 *            The NoteProperties to compare this NoteProperties against.
	 * @return Returns true only if noteNames are equal in octave and duration.
	 */
	boolean equals(NoteProperties properties) {
		return (this.octave == properties.octave && this.durationAsString.equals(properties.durationAsString));
	}

	public String toString() {
		return String.valueOf(octave) + durationAsString;
	}

}
