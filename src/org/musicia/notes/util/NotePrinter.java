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

/**
 * 
 * This class is responsible for printing notes as a proper string representation.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class NotePrinter {

	/**
	 * The notes to print.
	 */
	private List<Note> notesToPrint;

	/**
	 * The initial capacity of a StringBuilder.
	 */
	private static final int DEFAULT_CAPACITY = 256;

	/**
	 * 
	 * Creates a new NotePrinter.
	 * 
	 * @param notesToPrint
	 *            The notes to print.
	 */
	public NotePrinter(List<Note> notesToPrint) {
		this.notesToPrint = new ArrayList<Note>(notesToPrint);
	}

	/**
	 * @param notesToPrint
	 *            The notes to print.
	 */
	public void setNotesToPrint(List<Note> notesToPrint) {
		this.notesToPrint = new ArrayList<Note>(notesToPrint);
	}
	
	/**
	 * @return The notes to print.
	 */
	public List<Note> getNotesToPrint() {
		return new ArrayList<Note>(notesToPrint);
	}

	/**
	 * Returns a formatted String which contains the notesToPrint. <br>
	 * If <code>notesPerLine</code> is -1 then no line break will be made.
	 * 
	 * @param notesPerLine
	 *            The number of notes to print before line break.
	 * @return The note names.
	 */
	public String printNoteNames(int notesPerLine) {
		StringBuilder sb = new StringBuilder(DEFAULT_CAPACITY);
		if (notesPerLine == -1) {
			for (Note note : notesToPrint) {
				sb.append(note.getFullName()).append(" ");
			}
		} else {
			int notesPrinted = 0;
			for (Note note : notesToPrint) {
				if (notesPrinted % notesPerLine != 0 || notesPrinted == 0) {
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
				} else {
					sb.append(System.getProperty("line.separator"));
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
				}
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Returns an indexed String which contains the notesToPrint. <br>
	 * If <code>notesPerLine</code> is -1 then no line break will be made.
	 * 
	 * @param notesPerLine
	 *            The number of notes to print before line break.
	 * @param start
	 *            Where to start the enumeration.
	 * @return The note names.
	 */
	public String printIndexedNoteNames(int notesPerLine, int start) {
		StringBuilder sb = new StringBuilder(DEFAULT_CAPACITY);
		int i = start;
		if (notesPerLine == -1) {
			for (Note note : notesToPrint) {
				sb.append(i).append(": ").append(note.getFullName()).append(" ");
				i++;
			}
		} else {
			sb.append(i).append(": ");
			i++;
			int notesPrinted = 0;
			for (Note note : notesToPrint) {
				if (notesPrinted % notesPerLine != 0 || notesPrinted == 0) {
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
				} else {
					sb.append(System.getProperty("line.separator")).append(i).append(": ");
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
					i++;
				}
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Returns a formatted String which contains the notesToPrint. <br>
	 * If <code>notesPerLine</code> is -1 then no line break will be made.
	 * 
	 * @param notesPerLine
	 *            The number of notes to print before line break.
	 * @return The note names.
	 */
	public String printFullyQualifiedNoteNames(int notesPerLine) {
		StringBuilder sb = new StringBuilder(DEFAULT_CAPACITY);
		if (notesPerLine == -1) {
			for (Note note : notesToPrint) {
				sb.append(note.getFullName()).append(" ");
			}
		} else {
			int notesPrinted = 0;
			for (Note note : notesToPrint) {
				if (notesPrinted % notesPerLine != 0 || notesPrinted == 0) {
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
				} else {
					sb.append(System.getProperty("line.separator"));
					sb.append(note.getFullName()).append(" ");
					notesPrinted++;
				}
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Returns an indexed String which contains the notesToPrint. <br>
	 * If <code>notesPerLine</code> is -1 then no line break will be made.
	 * 
	 * @param notesPerLine
	 *            The number of notes to print before line break.
	 * @param start
	 *            Where to start the enumeration.
	 * @return The note names.
	 */
	public String printIndexedFullyQualifiedNoteNames(int notesPerLine, int start) {
		StringBuilder sb = new StringBuilder(DEFAULT_CAPACITY);
		int i = start;
		if (notesPerLine == -1) {
			for (Note note : notesToPrint) {
				sb.append(i).append(": ").append(note.toString()).append(" ");
				i++;
			}
		} else {
			sb.append(i).append(": ");
			int notesPrinted = 0;
			for (Note note : notesToPrint) {
				if (notesPrinted % notesPerLine != 0 || notesPrinted == 0) {
					sb.append(note.toString()).append(" ");
					notesPrinted++;
				} else {
					sb.append(System.getProperty("line.separator")).append(i).append(": ");
					sb.append(note.toString()).append(" ");
					notesPrinted++;
					i++;
				}
			}
		}
		return sb.toString().trim();
	}
}
