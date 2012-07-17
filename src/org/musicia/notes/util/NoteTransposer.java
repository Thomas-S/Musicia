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
package org.musicia.notes.util;

import java.util.ArrayList;
import java.util.List;

import org.musicia.notes.model.Note;
import org.musicia.notes.model.NoteOrder;

import com.google.inject.Inject;

/**
 * This class provides the functionality to transpose notes.
 * 
 * @author Thomas Schulz
 * @version 1.1
 */
public class NoteTransposer {

	// TODO Find a nice way to output note sequences.

	/**
	 * The note list that shall be transposed.
	 */
	private List<Note> notesToTranspose = new ArrayList<Note>();

	/**
	 * The transposed note list.
	 */
	private List<Note> transposedNotes = new ArrayList<Note>();

	/**
	 * The absolute note order.
	 */
	@Inject private NoteOrder noteOrder;

	/**
	 * Creates a new note transposer.
	 * 
	 * @param noteToTranspose
	 *            The note to transpose.
	 */
	public NoteTransposer(final Note noteToTranspose) {
		this.notesToTranspose.add(noteToTranspose);
	}

	/**
	 * Creates a new note transposer.
	 * 
	 * @param notesToTranspose
	 *            The notes to transpose.
	 */
	public NoteTransposer(final List<Note> notesToTranspose) {
		this.notesToTranspose.addAll(notesToTranspose);
	}

	/**
	 * Transposes the given note by the given distance.
	 * 
	 * @param note
	 *            The note to transpose
	 * @param distance
	 *            The transposition distance.
	 * @return ArrayList<String> containing the transposed notes.
	 */
	public Note getTransposedNote(final Note note, final int distance) {
		return noteOrder.getTransposedNote(note, distance);
	}

	/**
	 * Transposes the given notes by the given distances.
	 * 
	 * @param distances
	 *            The transposition distances.
	 * @return ArrayList<String> containing the transposed notes.
	 */
	public ArrayList<Note> getTransposedNotes(final List<Integer> distances) {
		for (Integer distance : distances) {
			for (final Note note : notesToTranspose) {
				transposedNotes.add(noteOrder.getTransposedNote(note, distance));
			}
		}
		return new ArrayList<Note>(transposedNotes);
	}

	/**
	 * @return Returns the transposed notes.
	 */
	public List<Note> getTransposedNotes() {
		return new ArrayList<Note>(transposedNotes);
	}

	/**
	 * @param notesToTranspose
	 *            The notes to transpose.
	 */
	public void setNotesToTranspose(final List<Note> notesToTranspose) {
		this.notesToTranspose = new ArrayList<Note>(notesToTranspose);
	}

}
