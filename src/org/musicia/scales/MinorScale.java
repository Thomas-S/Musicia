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

import java.util.Arrays;
import java.util.List;

import org.musicia.notes.model.Note;
import org.musicia.notes.util.CircleOfFifth;

import com.google.inject.Inject;

/**
 * This class provides a minor scale.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class MinorScale extends Scale {

	/**
	 * The root of the scale.
	 */
	private final Note rootNote;

	/**
	 * The circle of fifth.
	 */
	@Inject(optional = true)
	private CircleOfFifth circleOf5th;

	/**
	 * 
	 * Creates a new MinorScale.
	 * 
	 * @param rootNote
	 *            The root note of this minor scale.
	 */
	public MinorScale(final Note rootNote) {
		this.rootNote = rootNote;
	}

	@Override
	protected Note getRootNote() {
		return rootNote;
	}

	@Override
	protected List<Integer> getScalePattern() {
		return Arrays.asList(2, 1, 2, 2, 1, 2, 2);
	}

	@Override
	protected boolean shouldUseCircleOfFifth() {
		return true;
	}

	@Override
	protected List<Note> getCorrectCircleOfFifth() {
		return circleOf5th.getMinorCircle();
	}

}
