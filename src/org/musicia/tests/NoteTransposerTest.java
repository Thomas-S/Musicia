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
package org.musicia.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.musicia.notes.model.Note;
import org.musicia.notes.util.NoteParser;
import org.musicia.notes.util.NoteTransposer;

/**
 * Testing if the note transposer works properly.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class NoteTransposerTest {

	/**
	 * correct major circle result
	 */
	private final List<Note> correct = Arrays.asList(new Note("B", "", 4), new Note("G", "", 7), new Note("C"), new Note("G",
			"#", 7), new Note("C", "#"), new Note("A", "", 7));

	/**
	 * correct transposition from constructor
	 */
	@Test
	public void constructorTransposition() {

		final NoteParser p = new NoteParser("C G#7");

		final NoteTransposer t = new NoteTransposer(p.getParsedNotes());

		List<Integer> distances = new ArrayList<Integer>();
		for (int i = -1; i < 2; i++) {
			distances.add(i);
		}

		Assert.assertEquals("Not equal.", correct.toString(), (t.getTransposedNotes(distances).toString()));
	}
}
