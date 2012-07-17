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
import org.musicia.notes.util.CircleOfFifth;

/**
 * Testing if the circle of fifth works properly.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class CircleOfFifthTest {

	/**
	 * unit under test
	 */
	private final CircleOfFifth cof = new CircleOfFifth();

	/**
	 * result under test
	 */
	private List<Note> result = null;

	/**
	 * correct major circle result
	 */
	private final List<Note> correctMajor = Arrays.asList(new Note("C"), new Note("G"), new Note("D"), new Note("A"), new Note(
			"E"), new Note("B"), new Note("F", "#"), new Note("G", "b"), new Note("D", "b"), new Note("A", "b"), new Note("E",
			"b"), new Note("B", "b"), new Note("F"));

	/**
	 * correct minor circle result
	 */
	private final List<Note> correctMinor = Arrays.asList(new Note("A"), new Note("E"), new Note("B"), new Note("F", "#"),
			new Note("C", "#"), new Note("G", "#"), new Note("D", "#"), new Note("E", "b"), new Note("B", "b"), new Note("F"),
			new Note("C"), new Note("G"), new Note("D"));

	/**
	 * Major circle
	 */
	@Test
	public void majorCircle() {
		result = cof.getMajorCircle();
		assertionTest(correctMajor);
	}

	/**
	 * Minor circle
	 */
	@Test
	public void minorCircle() {
		result = cof.getMinorCircle();
		assertionTest(correctMinor);
	}

	/**
	 * Assertion test
	 * 
	 * @param correct
	 *            The correct result to compare against.
	 */
	public void assertionTest(final List<Note> correct) {
		final String given = result.toString();
		final String expected = correct.toString();
		Assert.assertEquals("Notes are not equal!", expected, given);
	}

}
