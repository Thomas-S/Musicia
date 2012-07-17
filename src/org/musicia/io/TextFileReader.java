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
package org.musicia.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.musicia.exceptions.MusiciaIOException;

/**
 * This class provides methods to read *.txt-Files.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class TextFileReader {

	// FIXME Solve path issues.

	/**
	 * The initial capacity of a StringBuilder.
	 */
	private static final int DEFAULT_CAPACITY = 256;

	/**
	 * Reads from the given text file which has to be in the same directory.
	 * 
	 * @param fileName
	 *            The name of the text file.
	 * @return Returns the content of the text file
	 */
	public String readFileAndReturnContent(final String fileName) {

		final StringBuilder result = new StringBuilder(DEFAULT_CAPACITY);

		try {
			final FileReader input = new FileReader(fileName);
			final BufferedReader bufferedReader = new BufferedReader(input);

			String line = bufferedReader.readLine();

			while (line != null) {
				result.append(line).append(System.getProperty("line.separator"));
				line = bufferedReader.readLine();
			}

			bufferedReader.close();

			return result.toString().trim();

		} catch (final FileNotFoundException e) {
			throw new MusiciaIOException("File not found.", e);
		} catch (final IOException e) {
			throw new MusiciaIOException("An I/O error occured.", e);
		}

	}

}
