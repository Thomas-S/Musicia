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

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.musicia.notes.model.Note;
import org.musicia.notes.util.NoteParser;

/**
 * Testing if the note parser only parses valid notes.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class NoteParserTest {

	/**
	 * unit under test
	 */
	private NoteParser np = new NoteParser();

	/**
	 * result under test
	 */
	private List<Note> np_result = null;

	/**
	 * correct result
	 */
	private final List<Note> correct = Arrays.asList(new Note("A","",6), new Note("C","",0), new Note("G", "",10));

	/**
	 * Only C0, A6 and G10 are valid.
	 */
	private static final String testNotes = "A## Bbb A6 B#9 Cb C-1 Db11 E# Fb C0 G10 G#10 H R V G#10";

	/**
	 * Direct parsing via constructor
	 */
	@Test
	public void constructorParsing() {
		np = new NoteParser(testNotes);
		np_result = np.getParsedNotes();
		assertionTest();
	}

	/**
	 * Direct parsing via method call
	 */
	@Test
	public void methodParsing() {
		np_result = np.parseAndReturn(testNotes);
		assertionTest();
	}

	/**
	 * Assertion test
	 */
	public void assertionTest() {
		final String given = np_result.toString();
		final String expected = correct.toString();
		Assert.assertEquals("Notes are not equal!", expected, given);
	}

}
