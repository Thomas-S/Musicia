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
import com.google.inject.Singleton;

/**
 * This class represents the circle of fifth. <br>
 * <b>CAUTION:</b> All computed notes have octave 5.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
@Singleton
public class CircleOfFifth {

	// TODO Split this circle up in major and minor
	// ...maybe creating an AbstractCircle
	
	/**
	 * The major circle.
	 */
	private List<Note> majorCircle = new ArrayList<Note>(NOTES_PER_OCTAVE + 1);

	/**
	 * The minor circle.
	 */
	private List<Note> minorCircle = new ArrayList<Note>(NOTES_PER_OCTAVE + 1);

	/**
	 * A is the root note of the minor circle.
	 */
	@Inject private NoteOrder order;

	/**
	 * C is the root note of the major circle.
	 */
	private static final Note C = new Note("C");

	/**
	 * A is the root note of the minor circle.
	 */
	private static final Note A = new Note("A");

	/**
	 * The distance of a fifth in half tones.
	 */
	private static final int FIFTH = 7;

	/**
	 * The distance of an octave in half tones.
	 */
	private static final int NOTES_PER_OCTAVE = 12;

	/**
	 * Creates a new circle of fifth. All notes are of the 5th octave.
	 */
	@Inject
	public CircleOfFifth() {
		buildCircle();
	}

	private void buildCircle() {
		// TODO Maybe save time if done with minor circle in parallel.

		List<Note> rightHalfMajor = new ArrayList<Note>(NOTES_PER_OCTAVE / 2);
		List<Note> leftHalfMajor = new ArrayList<Note>(NOTES_PER_OCTAVE / 2);

		Note currentMajor = C;
		
		List<Note> rightHalfMinor = new ArrayList<Note>(NOTES_PER_OCTAVE / 2);
		List<Note> leftHalfMinor = new ArrayList<Note>(NOTES_PER_OCTAVE / 2);

		Note currentMinor = A;
		
		int i = 0;
		
		while (i < NOTES_PER_OCTAVE) {
			if (i < (NOTES_PER_OCTAVE / 2)) {
				rightHalfMajor.add(currentMajor);
				rightHalfMinor.add(currentMinor);
			} else if (i == (NOTES_PER_OCTAVE / 2)){
				rightHalfMajor.add(currentMajor);
				leftHalfMajor.add(currentMajor.getEquivalent());
				rightHalfMinor.add(currentMinor);
				leftHalfMinor.add(currentMinor.getEquivalent());
			} else {
				leftHalfMajor.add(currentMajor.getEquivalent());
				leftHalfMinor.add(currentMinor.getEquivalent());
			}
			currentMajor = order.getTransposedNote(currentMajor, FIFTH);
			currentMinor = order.getTransposedNote(currentMinor, FIFTH);
			// preserve octave
			if (currentMajor.getOctave() != C.getOctave()) {
				currentMajor = new Note(currentMajor.getName(), currentMajor.getAccidental(), C.getOctave());
			}
			if (currentMinor.getOctave() != A.getOctave()) {
				currentMinor = new Note(currentMinor.getName(), currentMinor.getAccidental(), A.getOctave());
			}
			i++;
		}
		
		majorCircle.addAll(rightHalfMajor);
		majorCircle.addAll(leftHalfMajor);
		
		minorCircle.addAll(rightHalfMinor);
		minorCircle.addAll(leftHalfMinor);
	}

	/**
	 * @return Returns the major circle of fifth.
	 */
	public List<Note> getMajorCircle() {
		return new ArrayList<Note>(majorCircle);
	}

	/**
	 * @return Returns the minor circle of fifth.
	 */
	public List<Note> getMinorCircle() {
		return new ArrayList<Note>(minorCircle);
	}

}
