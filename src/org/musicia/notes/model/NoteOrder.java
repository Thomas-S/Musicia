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

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * This class provides static methods for accessing the absolute order of notes. <br>
 * <b>CAUTION:</b> There are only sharp notes in this list.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
@Singleton
public class NoteOrder {

	// TODO Create method that throws IllegalArgumentException.

	// TODO Clean up.

	/**
	 * The absolute note order.
	 */
	private final ArrayList<Note> noteOrder = new ArrayList<Note>(TOTAL_NUMBER_OF_NOTES);

	/**
	 * The total number of supported notes.
	 */
	private static final int TOTAL_NUMBER_OF_NOTES = 128;

	/**
	 * The highest supported octave.
	 */
	private static final int HIGHEST_OCTAVE = 10;

	/**
	 * The number of notes per octave.
	 */
	private static final int NUMBER_OF_NOTES_PER_OCTAVE = 12;

	private static boolean noteExceedsSupportedRange(final int index) {
		return noteExceedsSupportedRange(index, 0);
	}

	private static boolean noteExceedsSupportedRange(final int index, final int distance) {
		return (index + distance >= TOTAL_NUMBER_OF_NOTES) || (index + distance < 0);
	}

	/**
	 * Creates a new absolute note order.
	 */
	@Inject
	public NoteOrder() {
		assignNotes();
	}

	/**
	 * This method assigns all 128 possible (sharp) notes.
	 */
	private void assignNotes() {

		for (int i = 0; i < HIGHEST_OCTAVE; i++) {
			noteOrder.add(new Note("C", "", i));
			noteOrder.add(new Note("C", "#", i));
			noteOrder.add(new Note("D", "", i));
			noteOrder.add(new Note("D", "#", i));
			noteOrder.add(new Note("E", "", i));
			noteOrder.add(new Note("F", "", i));
			noteOrder.add(new Note("F", "#", i));
			noteOrder.add(new Note("G", "", i));
			noteOrder.add(new Note("G", "#", i));
			noteOrder.add(new Note("A", "", i));
			noteOrder.add(new Note("A", "#", i));
			noteOrder.add(new Note("B", "", i));
		}
		noteOrder.add(new Note("C", "", HIGHEST_OCTAVE));
		noteOrder.add(new Note("C", "#", HIGHEST_OCTAVE));
		noteOrder.add(new Note("D", "", HIGHEST_OCTAVE));
		noteOrder.add(new Note("D", "#", HIGHEST_OCTAVE));
		noteOrder.add(new Note("E", "", HIGHEST_OCTAVE));
		noteOrder.add(new Note("F", "", HIGHEST_OCTAVE));
		noteOrder.add(new Note("F", "#", HIGHEST_OCTAVE));
		noteOrder.add(new Note("G", "", HIGHEST_OCTAVE));
	}

	/*
	 * This method is fast because there are at most 12 iterations necessary.
	 */
	private int fastSearch(final Note note) {

		if (note.getAccidental().equals("b")) {
			return fastSearch(note.getEquivalent());
		}

		final int o = note.getOctave();
		for (int i = NUMBER_OF_NOTES_PER_OCTAVE * o; i < NUMBER_OF_NOTES_PER_OCTAVE * (o + 1); i++) {
			if (noteOrder.get(i).equalsName(note)) {
				return i;
			}
		}
		throw new IllegalArgumentException(String.format("The note [%s] is invalid.", note));
	}

	/**
	 * Convenience method. Delivers the direct predecessor of the note.
	 * 
	 * @param note
	 *            The note to decrease.
	 * @return Returns the note a half tone below the given note.
	 */
	public Note getPredecessor(final Note note) {
		return getTransposedNote(note, -1);
	}

	/**
	 * Convenience method. Delivers the direct successor of the note.
	 * 
	 * @param note
	 *            The note to increase.
	 * @return Returns the note a half tone above the given note.
	 */
	public Note getSuccessor(final Note note) {
		return getTransposedNote(note, 1);
	}

	/**
	 * Convenience method. Computes the desired transposed note by the given distance.
	 * 
	 * @param note
	 *            The note to transpose.
	 * @param distance
	 *            The distance for transposition.
	 * @return Returns a transposed note.
	 */
	public Note getTransposedNote(final Note note, final int distance) {
		final int indexOfNote = fastSearch(note);
		if (noteExceedsSupportedRange(indexOfNote, distance)) {
			throw new IllegalArgumentException(String.format(
					"The transposition of the note [%s] by [%s] exceeds the range of supported notes.", note, distance));
		}
		return noteAt(indexOfNote + distance);
	}

	/**
	 * Returns the index of the note.
	 * <b>NOTE:</b> A flat note will never be returned.
	 * 
	 * @param note
	 *            The note to get the index from.
	 * @return Returns the index of the note in the note order.
	 */
	public int indexOf(final Note note) {
		return fastSearch(note);
	}

	/**
	 * Returns the note at the given index.
	 * 
	 * @param index
	 *            The index of the desired note.
	 * @return Returns the note at specific index.
	 */
	public Note noteAt(final int index) {
		if (noteExceedsSupportedRange(index)) {
			throw new IllegalArgumentException(String.format("The index [%s] by [%s] exceeds the" + " range of supported notes.",
					index));
		}
		return noteOrder.get(index);
	}

}
