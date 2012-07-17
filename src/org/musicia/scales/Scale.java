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
package org.musicia.scales;

import java.util.ArrayList;
import java.util.List;

import org.musicia.exceptions.MusiciaUnsupportedOperationException;
import org.musicia.notes.model.Note;
import org.musicia.notes.model.NoteOrder;

import com.google.inject.Inject;

/**
 * 
 * This class implements the common behavior of scales.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public abstract class Scale {

	/**
	 * The absolute note order.
	 */
	@Inject(optional = true)
	private NoteOrder chromatics;

	/**
	 * The notes of the major scale.
	 */
	private final ArrayList<Note> scale = new ArrayList<Note>(NUMBER_OF_NOTES_PER_SCALE);

	/**
	 * The start index of the left half of the circle of fifth.
	 */
	private static final int START_INDEX_OF_LEFT_CIRCLE = 7;

	/**
	 * The number of notes per scale.
	 */
	private static final int NUMBER_OF_NOTES_PER_SCALE = 8;

	private void buildScale() {
		if (shouldUseCircleOfFifth()) {
			final Note rootNote = getRootNote();
			final List<Note> correctCircle = getCorrectCircleOfFifth();
			final List<Integer> indexes = getIndexes(chromatics.indexOf(rootNote));

			int i;
			for (i = 0; i < START_INDEX_OF_LEFT_CIRCLE; i++) {
				if (rootNote.equalsName(correctCircle.get(i))) {
					for (final Integer in : indexes) {
						scale.add(chromatics.noteAt(in));
					}
				} else if (rootNote.equalsName(correctCircle.get(i).getEquivalent())) {
					for (final Integer in : indexes) {
						scale.add(chromatics.noteAt(in).getEquivalent());
					}
				}
			}
			for (i = START_INDEX_OF_LEFT_CIRCLE; i < 13; i++) {
				if (rootNote.equalsName(correctCircle.get(i))) {
					for (final Integer in : indexes) {
						scale.add(chromatics.noteAt(in).getEquivalent());
					}
				} else if (rootNote.equalsName(correctCircle.get(i).getEquivalent())) {
					for (final Integer in : indexes) {
						scale.add(chromatics.noteAt(in));
					}
				}
			}
		} else {
			throw new MusiciaUnsupportedOperationException(
					"Please implement method to compute scales without the circle of fifth.");
		}
	}

	/**
	 * Delivers the note at <code>position</code> of this scale.<br>
	 * 
	 * @param position
	 *            The 1-based position of the note (1-8).
	 * @return Returns the note at the given position of this scale.
	 */
	public Note get(final int position) {
		return scale.get(position - 1);
	}

	/**
	 * @return Returns the specific circle of fifth which should be used to define this scale.
	 */
	protected abstract List<Note> getCorrectCircleOfFifth();

	/**
	 * Computes the numeric values of the 8 notes related to<br>
	 * this scale starting with the given root note index. <br>
	 * <br>
	 * 
	 * Example: C5 Major Scale returns {60, 62, 64, 65, 67, 69, 71, 72}
	 * 
	 * @param startIndex
	 *            The index of the root note of this scale.
	 * @return Returns the desired numeric values of the notes.
	 */
	private List<Integer> getIndexes(final int startIndex) {
		int index = startIndex;
		final List<Integer> intervals = getScalePattern();
		final ArrayList<Integer> result = new ArrayList<Integer>(NUMBER_OF_NOTES_PER_SCALE);
		result.add(index);
		for (final Integer interval : intervals) {
			index += interval;
			result.add(index);
		}
		return result;
	}

	/**
	 * @return Returns the root note of this scale.
	 */
	protected abstract Note getRootNote();

	/**
	 * @return Returns the list of notes representing this scale.
	 */
	public ArrayList<Note> getScale() {
		if (scale.isEmpty()) {
			buildScale();
		}
		return new ArrayList<Note>(scale);
	}

	/**
	 * @return The 7 intervals representing this scale.
	 */
	protected abstract List<Integer> getScalePattern();

	/**
	 * @return Returns true only if the circle of fifth should be used for this scale.
	 */
	protected abstract boolean shouldUseCircleOfFifth();

}
