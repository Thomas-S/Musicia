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
package org.musicia.exceptions;

import org.musicia.commands.ShowExceptionCommand;

/**
 * 
 * This runtime exception is thrown whenever a project-related error occurs. 
 *
 * @author Thomas Schulz
 * @version 1.0
 */
public abstract class MusiciaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Creates a new MusiciaIOException.
	 * 
	 * @param message
	 */
	protected MusiciaException(String message) {
		super(message);
		showException();
	}

	/**
	 * 
	 * Creates a new MusiciaIOException.
	 * 
	 * @param cause
	 */
	protected MusiciaException(Throwable cause) {
		super(cause);
		showException();
	}

	/**
	 * 
	 * Creates a new MusiciaIOException.
	 * 
	 * @param message
	 * @param cause
	 */
	protected MusiciaException(String message, Throwable cause) {
		super(message, cause);
		showException();
	}
	
	private void showException() {
		new ShowExceptionCommand(this).execute();
	}
	
	/**
	 * @return Returns the class name of the exception.
	 */
	public abstract String getExceptionType();
}
