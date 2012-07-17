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
import org.musicia.scales.MajorScale;
import org.musicia.scales.MinorScale;
import org.musicia.scales.Scale;

/**
 * Testing if the scales are built correctly.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class ScaleTest {

	/**
	 * unit under test
	 */
	private final Scale cMajorScale = new MajorScale(new Note("C"));

	/**
	 * unit under test
	 */
	private final Scale cSharpMajorScale = new MajorScale(new Note("C", "#"));

	/**
	 * unit under test
	 */
	private final Scale aMinorScale = new MinorScale(new Note("A"));

	/**
	 * unit under test
	 */
	private final Scale aFlatMinorScale = new MinorScale(new Note("A", "b"));

	/**
	 * result under test
	 */
	private List<Note> result = null;

	/**
	 * correct c major scale result
	 */
	private List<Note> correctCMajor = Arrays.asList(new Note("C"), new Note("D"), new Note("E"), new Note("F"), new Note("G"),
			new Note("A"), new Note("B"), new Note("C", "", NEXT_OCTAVE));

	/**
	 * correct c# major scale result
	 */
	private List<Note> correctCSharpMajor = Arrays.asList(new Note("C", "#"), new Note("D", "#"), new Note("F"), new Note("F",
			"#"), new Note("G", "#"), new Note("A", "#"), new Note("C", "", NEXT_OCTAVE), new Note("C", "#", NEXT_OCTAVE));

	/**
	 * correct a minor scale result
	 */
	private List<Note> correctAMinor = Arrays.asList(new Note("A"), new Note("B"), new Note("C", "", NEXT_OCTAVE), new Note("D",
			"", NEXT_OCTAVE), new Note("E", "", NEXT_OCTAVE), new Note("F", "", NEXT_OCTAVE), new Note("G", "", NEXT_OCTAVE),
			new Note("A", "", NEXT_OCTAVE));

	/**
	 * correct a flat minor scale result
	 */
	private List<Note> correctAFlatMinor = Arrays.asList(new Note("A", "b"), new Note("B", "b"), new Note("B"),
			new Note("D", "b", NEXT_OCTAVE), new Note("E", "b", NEXT_OCTAVE), new Note("E", "", NEXT_OCTAVE), new Note("G", "b",
					NEXT_OCTAVE), new Note("A", "b", NEXT_OCTAVE));

	/**
	 * expected next octave
	 */
	private static final int NEXT_OCTAVE = 6;

	/**
	 * C-Major scale
	 */
	@Test
	public void cMajorScale() {
		result = cMajorScale.getScale();
		assertionTest(correctCMajor);
	}

	/**
	 * C#-Major scale
	 */
	@Test
	public void cSharpMajorScale() {
		result = cSharpMajorScale.getScale();
		assertionTest(correctCSharpMajor);
	}

	/**
	 * A-Minor scale
	 */
	@Test
	public void aMinorScale() {
		result = aMinorScale.getScale();
		assertionTest(correctAMinor);
	}

	/**
	 * Ab-Minor scale
	 */
	@Test
	public void aFlatMinorScale() {
		result = aFlatMinorScale.getScale();
		assertionTest(correctAFlatMinor);
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
