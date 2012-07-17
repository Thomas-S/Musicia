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
 * This class encapsulates the essential properties of a music note.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class Note {
	
	/**
	 * The name of the note (e.g. "C" or "A") and the (optional) accidental of a note.
	 */
	NoteName noteName = new NoteName(DEFAULT_NAME, DEFAULT_ACCIDENTAL);

	/**
	 * The essential properties of a note (octave and duration).
	 */
	NoteProperties properties = new NoteProperties(DEFAULT_OCTAVE, DEFAULT_DURATION);

	/**
	 * The default name.
	 */
	static final String DEFAULT_NAME = "C";

	/**
	 * The default accidental.
	 */
	static final String DEFAULT_ACCIDENTAL = "";

	/**
	 * The default octave.
	 */
	static final int DEFAULT_OCTAVE = 5;

	/**
	 * The default duration.
	 */
	static final double DEFAULT_DURATION = 0.25;

	/**
	 * Creates the "middle C" note with default octave (5) and default duration (quarter note).
	 */
	public Note() {
	}

	/**
	 * Creates a new note with the given name, without an accidental, with default octave (5) and
	 * default duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 */
	public Note(String name) {
		this.noteName = new NoteName(name, DEFAULT_ACCIDENTAL);
	}

	/**
	 * Creates a new note with the given name, the given accidental, with default octave (5) and
	 * default duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 */
	public Note(String name, String accidental) {
		this.noteName = new NoteName(name, accidental);
	}

	/**
	 * Creates a new note with the given name, the given accidental, the given octave and with
	 * default duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 */
	public Note(String name, String accidental, int octave) {
		this.noteName = new NoteName(name, accidental);
		this.properties = new NoteProperties(octave, DEFAULT_DURATION);
	}

	/**
	 * Creates a new note with the given name, the given accidental, the given octave and the
	 * given duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 * @param duration
	 *            The duration of the note as floating point number [e.g. 0.25 for quarter].
	 */
	public Note(String name, String accidental, int octave, double duration) {
		this.noteName = new NoteName(name, accidental);
		this.properties = new NoteProperties(octave, duration);
	}

	/**
	 * Creates a new note with the given name, the given accidental, the given octave and the
	 * given duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 * @param duration
	 *            The duration of the note as string [e.g. "q" for quarter].
	 */
	public Note(String name, String accidental, int octave, String duration) {
		this.noteName = new NoteName(name, accidental);
		this.properties = new NoteProperties(octave, duration);
	}

	/**
	 * Creates a new note with the given name, the given accidental, the given octave and the
	 * given duration (0.25 for quarter note).
	 * 
	 * @param name
	 *            The name of the note [A-G].
	 * @param accidental
	 *            The accidental of the note [#,b].
	 * @param octave
	 *            The pitch of the note [C-0 until G-10 where middle C is C-5].
	 * @param duration
	 *            The duration of the note as denominator of the fraction [e.g. "4" for quarter].
	 */
	public Note(String name, String accidental, int octave, int duration) {
		this.noteName = new NoteName(name, accidental);
		this.properties = new NoteProperties(octave, 1.0 / ((double) duration));
	}

	// ESCA-JAVA0131:
	/**
	 * Checks if two notes are equal in name, pitch and duration.
	 * 
	 * @param note
	 *            The note to compare this note against.
	 * @return Returns true only if both notes have the same name, pitch and duration.
	 */
	public boolean equals(Note note) {
		return this.noteName.equals(note.noteName) && this.properties.equals(properties);
	}

	/**
	 * Checks if two notes are equal in name.
	 * 
	 * @param note
	 *            The note to compare this note against.
	 * @return Returns true only if both notes have the same name.
	 */
	public boolean equalsName(Note note) {
		return this.noteName.equals(note.noteName);
	}
	
	/**
	 * Checks if two notes are equal in properties.
	 * 
	 * @param note
	 *            The note to compare this note against.
	 * @return Returns true only if both notes have the same properties.
	 */
	public boolean equalsProperties(Note note) {
		return this.properties.equals(note.properties);
	}

	/**
	 * Resolves the equivalent to the given note.
	 * 
	 * @return Returns the equivalent note.
	 */
	public Note getEquivalent() {
		return NoteName.resolveEquivalent(this);
	}

	/**
	 * Resolves the German equivalent to the given note.
	 * 
	 * @return Returns the German equivalent note.
	 */
	@Deprecated
	public Note getGermanEquivalent() {
		return NoteName.resolveGermanEquivalent(this);
	}

	/**
	 * Checks if a note has an accidental.
	 * 
	 * @return Returns true only if the given note has an accidental.
	 */
	public boolean hasAccidental() {
		return !this.noteName.accidental.equals("");
	}

	/**
	 * Only use this method for testing purposes.
	 * 
	 * @return Returns a simple string representation of the note (name and octave).
	 */
	public String toString() {
		return this.noteName.toString() + this.properties.toString();
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return noteName.name;
	}

	/**
	 * @return Returns the accidental.
	 */
	public String getAccidental() {
		return noteName.accidental;
	}
	
	/**
	 * @return Returns the name plus accidental.
	 */
	public String getFullName() {
		return noteName.fullName;
	}

	/**
	 * @return Returns the octave.
	 */
	public int getOctave() {
		return properties.octave;
	}

	/**
	 * @return Returns the duration.
	 */
	public double getDuration() {
		return properties.durationAsDouble;
	}

	/**
	 * @return Returns true only if accidental is "#".
	 */
	public boolean isSharp() {
		return noteName.isSharp();
	}

	/**
	 * @return Returns true only if accidental is "b".
	 */
	public boolean isFlat() {
		return noteName.isFlat();
	}

}
