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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.musicia.notes.model.Note;

/**
 * This class provides methods to parse notes from a string with regular expressions.
 * 
 * TODO Implement parsing durations.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class NoteParser {
	
	// FIXME Parsing of notes of octave higher than 9 throws exception.

	/**
	 * The notes to parse.
	 */
	private String notesToParse;

	/**
	 * The parsed notes according to the pattern.
	 */
	private List<Note> parsedNotes;
	
	/**
	 * How many notes have been parsed.
	 */
	private int number_of_notes_parsed = 0;

	/**
	 * All valid sharp note names.
	 */
	private static final String VALID_SHARP_NOTE_NAMES_REGEXP = "[ACDFG][#]?";

	/**
	 * All valid flat note names.
	 */
	private static final String VALID_FLAT_NOTE_NAMES_REGEXP = "[ABDEG][b]?";

	/**
	 * The regular expression for at least one whitespace.
	 */
	private static final String AT_LEAST_ONE_WHITESPACE = "\\s+";

	/**
	 * The regular expression for valid notes. <b>
	 * with #: A, C, D, F, G
	 * with b: A, B, D, E, G
	 */
	private static final Pattern VALID_NOTE_NAMES = Pattern.compile(VALID_SHARP_NOTE_NAMES_REGEXP + "|"
			+ VALID_FLAT_NOTE_NAMES_REGEXP);

	/**
	 * The regular expression for valid notes plus octave from 0 to 9.
	 */
	private static final Pattern VALID_NOTES_TILL_OCTAVE_9 = Pattern.compile(VALID_SHARP_NOTE_NAMES_REGEXP + "[0-9]|"
			+ VALID_FLAT_NOTE_NAMES_REGEXP + "[0-9]");

	/**
	 * The regular expression for valid notes of octave 11 (no G# / Ab, A, A# / Bb , B).
	 */
	private static final Pattern VALID_NOTES_OF_OCTAVE_10 = Pattern.compile("[CDF][#]?[1]{1}?[0]{1}?|[EDG][b]?[1]{1}?[0]{1}?");

	/**
	 * Creates a new note parser without any notes to parse. <b>
	 * One must set the notes to parse manually.
	 */
	public NoteParser() {
	}

	/**
	 * Creates a new note parser.
	 * 
	 * @param notes
	 *            The notes to parse.
	 */
	public NoteParser(String notes) {
		this.notesToParse = notes;
		parseNotes();
	}

	private void parseNotes() {
		// TODO Clean up.
		String[] potentialNotes = notesToParse.split(AT_LEAST_ONE_WHITESPACE);
		parsedNotes = new ArrayList<Note>();
		for (String potentialNote : potentialNotes) {
			Matcher m1 = VALID_NOTE_NAMES.matcher(potentialNote);
			Matcher m2 = VALID_NOTES_TILL_OCTAVE_9.matcher(potentialNote);
			Matcher m3 = VALID_NOTES_OF_OCTAVE_10.matcher(potentialNote);
			if (m1.matches()) {
				String name = potentialNote.substring(0, 1);
				String accidental = potentialNote.substring(1);
				parsedNotes.add(new Note(name, accidental));
				number_of_notes_parsed++;
			} else if (m2.matches()) {
				if (potentialNote.length() == 2) {
					String name = potentialNote.substring(0, 1);
					String accidental = "";
					String octave = potentialNote.substring(1);
					parsedNotes.add(new Note(name, accidental, Integer.parseInt(octave)));
				} else {
					String name = potentialNote.substring(0, 1);
					String accidental = potentialNote.substring(1, 2);
					String octave = potentialNote.substring(2);
					parsedNotes.add(new Note(name, accidental, Integer.parseInt(octave)));
				}
				number_of_notes_parsed++;
			} else if (m3.matches()) {
				if (potentialNote.length() == 3) {
					String name = potentialNote.substring(0, 1);
					String accidental = "";
					String octave = potentialNote.substring(1);
					parsedNotes.add(new Note(name, accidental, Integer.parseInt(octave)));
				} else {
					String name = potentialNote.substring(0, 1);
					String accidental = potentialNote.substring(1, 2);
					String octave = potentialNote.substring(2);
					parsedNotes.add(new Note(name, accidental, Integer.parseInt(octave)));
				}
				number_of_notes_parsed++;
			}
		}

	}

	/**
	 * Parses the given notes and returns a list of Note-objects.
	 * 
	 * @param notes
	 *            The notes to parse.
	 * @return Returns the parsed Note list.
	 */
	public List<Note> parseAndReturn(String notes) {
		this.notesToParse = notes;
		number_of_notes_parsed = 0;
		parseNotes();
		return new ArrayList<Note>(parsedNotes);
	}

	/**
	 * @return Returns the parsedNotes.
	 */
	public List<Note> getParsedNotes() {
		return new ArrayList<Note>(parsedNotes);
	}
	
	/**
	 * @return Returns the number of parsed notes.
	 */
	public int getNumberOfParsedNotes() {
		return number_of_notes_parsed;
	}

}
