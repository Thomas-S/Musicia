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
package org.musicia.core;

import org.musicia.gui.ui.UserInterface;
import org.musicia.notes.model.NoteDurationTable;
import org.musicia.notes.model.NoteOrder;
import org.musicia.notes.util.CircleOfFifth;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The main class.
 * 
 * @author Thomas Schulz
 * @version 1.1
 */
public class Application {
	
	public static final Injector injector = Guice.createInjector(new MusiciaModule());

	// TODO Refactor tests.

	// TODO User enters notes and app computes scale.

	private Application() {

	}
	
	private static void startWithGuice() {
		injector.getInstance(NoteOrder.class);
		injector.getInstance(NoteDurationTable.class);
		injector.getInstance(CircleOfFifth.class);
		
		// TODO Transposing chords and preserve modifications (major, minor, 7th, ...)
		injector.getInstance(UserInterface.class);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Transposing chords and preserve modifications (major, minor, 7th, ...)
		startWithGuice();
	}

}
