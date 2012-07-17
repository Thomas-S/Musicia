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
package org.musicia.gui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 * The window of the Musicia user interface.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class MusiciaWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3668429239574324791L;

	/**
	 * The default title of the window.
	 */
	private static final String DEFAULT_TITLE = "Musicia";

	/**
	 * The default width and height of the window.
	 */
	private static final Dimension DEFAULT_DIMENSION = new Dimension(600,450);

	/**
	 * The x-coordinate of the top left corner.
	 */
	private static final int TOP_LEFT_X = 100;

	/**
	 * The y-coordinate of the top left corner.
	 */
	private static final int TOP_LEFT_Y = 100;

	/**
	 * Creates a <b>default</b> Window for the <b>Musicia</b> application with the following properties:<br>
	 * - default preferred size of (600,450) <br>
	 * - default initial location at (100,100) <br>
	 * - it's content pane has a BorderLayout <br>
	 * - is visible
	 */
	public MusiciaWindow() {
		super(DEFAULT_TITLE);
		setPreferredSize(DEFAULT_DIMENSION);
		setLocation(TOP_LEFT_X, TOP_LEFT_Y);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
	}

	/**
	 * Creates a new visible <b>custom</b> Window that exits a program on close. <br>
	 * Use this constructor to create a Window for an <b>arbitrary</b> application.
	 * 
	 * @param title
	 *            The title of the window.
	 * @param width
	 *            The width of the window.
	 * @param height
	 *            The height of the window.
	 * @param x
	 *            The x-coordinate of the top left corner where the window is initially located.
	 * @param y
	 *            The y-coordinate of the top left corner where the window is initially located.
	 */
	public MusiciaWindow(String title, int width, int height, int x, int y) {
		super(title);
		Dimension custom = new Dimension(width, height);
		setPreferredSize(custom);
		setLocation(x, y);
		setVisible(true);
	}

}
