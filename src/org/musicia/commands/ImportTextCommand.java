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
package org.musicia.commands;

import org.musicia.io.TextFileReader;

/**
 * 
 * This command imports a given String into a text file. <br>
 * The result of the import is returned by <code>getResult()</code>.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class ImportTextCommand implements Command {

	/**
	 * The name of the file to import.
	 */
	private final String fileName;

	/**
	 * The result of the import.
	 */
	private String result;

	/**
	 * 
	 * Creates a new ImportTextCommand.
	 * 
	 * @param fileName
	 *            The name of the file to import.
	 */
	public ImportTextCommand(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void execute() {
		final TextFileReader tfr = new TextFileReader();
		result = tfr.readFileAndReturnContent(fileName);
	}

	/**
	 * @return Returns the result after execution.
	 */
	public String getResult() {
		return result;
	}

}
