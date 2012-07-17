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
package org.musicia.sound;

import java.util.ArrayList;
/**
 * JFugue - API for Music Programming
 * Copyright (C) 2003-2008  David Koelle
 *
 * http://www.jfugue.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
import org.jfugue.Player;
import org.musicia.notes.model.Note;

/**
 * This class provides methods to play nice sounds.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class MusiciaPlayer extends Player {

	/**
	 * The stored notes to play.
	 */
	private String notesToPlay = "";

	/**
	 * A list of notes.
	 */
	private ArrayList<Note> listOfNotes = new ArrayList<Note>();

	/**
	 * Creates a new MusiciaPlayer with an empty playlist.
	 */
	public MusiciaPlayer() {
	}

	/**
	 * Creates a new MusiciaPlayer and stores the given formatted notes to play. <br>
	 * Examples for exactly formatted strings would be: <br>
	 * -- "C C#5q D#4h" <br>
	 * -- "A B C D"
	 * 
	 * @param formattedNotes
	 *            The formatted notes to play.
	 */
	public MusiciaPlayer(String formattedNotes) {
		notesToPlay = formattedNotes;
	}

	/**
	 * Creates a new MusiciaPlayer and stores the given note to play.
	 * 
	 * @param note
	 *            The notes to play.
	 */
	public MusiciaPlayer(Note note) {
		listOfNotes.add(note);
		setNotesNoPlay();
	}

	/**
	 * Creates a new MusiciaPlayer and stores the given notes to play.
	 * 
	 * @param notes
	 *            The notes to play.
	 */
	public MusiciaPlayer(ArrayList<Note> notes) {
		listOfNotes.addAll(notes);
		setNotesNoPlay();
	}

	/**
	 * Plays the stored notes.
	 */
	public void play() {
		if (!notesToPlay.isEmpty()) {
			super.play(notesToPlay);
		}
	}

	/**
	 * @return Returns the notes to play.
	 */
	public String getNotesToPlay() {
		return notesToPlay;
	}

	/**
	 * @param listOfNotes
	 *            The listOfNotes to play.
	 */
	public void setListOfNotes(ArrayList<Note> listOfNotes) {
		this.listOfNotes = new ArrayList<Note>(listOfNotes);
		setNotesNoPlay();
	}

	private void setNotesNoPlay() {
		StringBuffer chainedNotes = new StringBuffer(256);
		for (Note note : listOfNotes) {
			if (note.isFlat()) {
				// The super class only accepts sharp notes.
				chainedNotes.append(note.getEquivalent().toString());
			} else {
				chainedNotes.append(note.toString());
			}
			chainedNotes.append(" ");
		}
		notesToPlay = chainedNotes.toString().trim();
	}

}
