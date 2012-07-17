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

/**
 * This class encapsulates the name and the accidental of a note.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
class NoteName {

	/**
	 * The name of the note (e.g. "C" or "A").
	 */
	String name = "C";

	/**
	 * The (optional) accidental of a note ("", "#" or "b").
	 */
	String accidental = "";
	
	/**
	 * The concatenation of name and accidental.
	 */
	String fullName = "C";
	
	/**
	 * Creates a new note name.
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 */
	NoteName(String name, String accidental) {
		String literalAccidental = "";
		if (accidental.equals("#")) {
			literalAccidental = "sharp";
		} else if (accidental.equals("b")) {
			literalAccidental = "flat";
		}
		this.name = name;
		this.accidental = accidental;
		this.fullName = NoteNameEnum.valueOf(name + literalAccidental).toString();
	}

	/**
	 * Resolves the equivalent to the given note or returns this if note has no accidental.
	 * 
	 * @param note
	 *            The note to get the equivalent from.
	 * @return Returns the equivalent note.
	 */
	static Note resolveEquivalent(Note note) {

		if (!note.hasAccidental()) {
			return note;
		}

		Note[][] possibleEquivalents = getPossibleEquivalents(note);

		// TODO Abstract from iteration.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				if (note.equals(possibleEquivalents[i][j])) {
					if (j == 0) {
						return possibleEquivalents[i][1];
					} else {
						return possibleEquivalents[i][0];
					}
				}
			}
		}

		throw new IllegalArgumentException(String.format("Could not resolve equivalent note to [%s].", note));
	}

	// ESCA-JAVA0076:
	private static Note[][] getPossibleEquivalents(Note note) {
		int o = note.properties.octave;
		double d = note.properties.durationAsDouble;

		Note[][] possibleEquivalents = new Note[5][2];

		possibleEquivalents[0][0] = new Note("A", "#", o, d);
		possibleEquivalents[0][1] = new Note("B", "b", o, d);
		possibleEquivalents[1][0] = new Note("C", "#", o, d);
		possibleEquivalents[1][1] = new Note("D", "b", o, d);
		possibleEquivalents[2][0] = new Note("D", "#", o, d);
		possibleEquivalents[2][1] = new Note("E", "b", o, d);
		possibleEquivalents[3][0] = new Note("F", "#", o, d);
		possibleEquivalents[3][1] = new Note("G", "b", o, d);
		possibleEquivalents[4][0] = new Note("G", "#", o, d);
		possibleEquivalents[4][1] = new Note("A", "b", o, d);

		return possibleEquivalents;
	}

	/**
	 * Resolves the German equivalent to the given note or <br>
	 * returns this if note has no accidental and is not "B".
	 * 
	 * @param note
	 *            The note to get the German equivalent from.
	 * @return Returns the German equivalent note.
	 */
	static Note resolveGermanEquivalent(Note note) {
		if (!note.hasAccidental() && (!note.noteName.fullName.equals("B"))) {
			return note;
		}

		Note[][] possibleEquivalents = getPossibleGermanEquivalents(note);

		// TODO Abstract from iteration.
		for (int i = 0; i < 9; i++) {
			if (note.equals(possibleEquivalents[i][0])) {
				return possibleEquivalents[i][1];
			}
		}

		throw new IllegalArgumentException(String.format("Could not resolve German equivalent note to [%s].", note));
	}

	// ESCA-JAVA0076:
	private static Note[][] getPossibleGermanEquivalents(Note note) {
		int o = note.properties.octave;
		double d = note.properties.durationAsDouble;

		Note[][] possibleEquivalents = new Note[9][2];

		possibleEquivalents[0][0] = new Note("A", "#", o, d);
		possibleEquivalents[0][1] = new Note("A", "is", o, d);
		possibleEquivalents[1][0] = new Note("B", "", o, d);
		possibleEquivalents[1][0] = new Note("H", "", o, d);
		possibleEquivalents[2][0] = new Note("B", "b", o, d);
		possibleEquivalents[2][1] = new Note("b", "", o, d);
		possibleEquivalents[3][0] = new Note("C", "#", o, d);
		possibleEquivalents[3][1] = new Note("C", "is", o, d);
		possibleEquivalents[4][0] = new Note("D", "b", o, d);
		possibleEquivalents[4][1] = new Note("D", "es", o, d);
		possibleEquivalents[5][0] = new Note("E", "b", o, d);
		possibleEquivalents[5][1] = new Note("E", "s", o, d);
		possibleEquivalents[6][0] = new Note("F", "#", o, d);
		possibleEquivalents[6][1] = new Note("F", "is", o, d);
		possibleEquivalents[7][0] = new Note("G", "#", o, d);
		possibleEquivalents[7][1] = new Note("G", "is", o, d);
		possibleEquivalents[8][0] = new Note("G", "b", o, d);
		possibleEquivalents[8][1] = new Note("G", "es", o, d);

		return possibleEquivalents;
	}

	// ESCA-JAVA0131:
	/**
	 * Checks if two NoteNames are equal.
	 * 
	 * @param noteName
	 *            The noteName to compare this noteName against.
	 * @return Returns true only if noteNames are equal in name and accidental.
	 */
	boolean equals(NoteName noteName) {
		return this.fullName.equals(noteName.fullName);
	}

	@Override
	public String toString() {
		return fullName;
	}

	/**
	 * @return Returns true only if accidental is "#".
	 */
	boolean isSharp() {
		return accidental.equals("#");
	}

	/**
	 * @return Returns true only if accidental is "b".
	 */
	boolean isFlat() {
		return accidental.equals("b");
	}

}
