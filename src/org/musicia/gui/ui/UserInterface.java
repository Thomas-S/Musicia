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
package org.musicia.gui.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.musicia.commands.ExitCommand;
import org.musicia.commands.ExportTextCommand;
import org.musicia.commands.ImportTextCommand;
import org.musicia.commands.ShowCopyrightCommand;
import org.musicia.commands.ShowInformationCommand;
import org.musicia.gui.window.MusiciaWindow;
import org.musicia.gui.window.MusiciaWindowContent;
import org.musicia.notes.model.Note;
import org.musicia.notes.util.NoteParser;
import org.musicia.notes.util.NotePrinter;
import org.musicia.notes.util.NoteTransposer;
import org.musicia.scales.MajorScale;
import org.musicia.scales.MinorScale;
import org.musicia.scales.Scale;

/**
 * This class provides a complex graphical user interface.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
public class UserInterface {

	// TODO Use command pattern (and handler).

	// TODO Use GridBagLayout.

	// TODO Clean up.
	
	// TODO Use own thread for gui - new Runnable { void run() ... }

	/**
	 * The window of the user interface.
	 */
	private final MusiciaWindow w = new MusiciaWindow();

	/**
	 * The content panel of the window.
	 */
	private final MusiciaWindowContent wc = new MusiciaWindowContent(new GridBagLayout());

	/**
	 * Creates a new UserInterface.
	 */
	public UserInterface() {
		addWindowListener();
		buildMenu();
		buildWindowContent();
	}

	private void addWindowListener() {
		w.addWindowListener(new MusiciaWindowListener());
	}

	private void buildMenu() {
		final JMenuBar topMenuBar = new JMenuBar();

		final JMenu fileMenu = new JMenu("File");
		final JMenu helpMenu = new JMenu("Help");

		final JMenuItem importMenuItem = buildAndReturnMenuItem("Import", "Import", new ImportListener("Musicia.txt"));
		final JMenuItem exportMenuItem = buildAndReturnMenuItem("Export", "Export", new ExportListener("Musicia.txt"));
		final JMenuItem quitMenuItem = buildAndReturnMenuItem("Quit", "Quit", new QuitListener());

		final JMenuItem aboutMenuItem = buildAndReturnMenuItem("About", "About", new AboutListener());

		fileMenu.add(importMenuItem);
		fileMenu.add(exportMenuItem);
		fileMenu.add(quitMenuItem);

		helpMenu.add(aboutMenuItem);

		topMenuBar.add(fileMenu);
		topMenuBar.add(helpMenu);

		w.getContentPane().add(topMenuBar, BorderLayout.NORTH);
	}

	/**
	 * 
	 * This method systematically creates menu items with the given parameters as properties.
	 * 
	 * @param name
	 *            The name of the JMenuItem.
	 * @param toolTipText
	 *            The text of the tool tip of the JMenuItem.
	 * @param listener
	 *            The ActionListener of the JMenuItem.
	 * @return Returns the JMenuItem with the given specific parameters.
	 */
	private static JMenuItem buildAndReturnMenuItem(final String name, final String toolTipText, final ActionListener listener) {
		final JMenuItem mi = new JMenuItem(name);
		mi.setToolTipText(toolTipText);
		mi.addActionListener(listener);
		return mi;
	}

	private void buildWindowContent() {
		final JTextArea editTextArea = new JTextArea("Text");
		editTextArea.setText("G D C D");
		final JScrollPane editScrollPane = new JScrollPane(editTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		final String rb1Text = "Transpose note(s) [-11 till +11].";
		final String rb2Text = "Compute major scale.";
		final String rb3Text = "Compute minor scale.";

		final String rb4Text = "Note names plus octave.";
		final String rb5Text = "Just note names.";

		final JRadioButton eval1 = buildAndReturnRadioButtons(rb1Text);
		final JRadioButton eval2 = buildAndReturnRadioButtons(rb2Text);
		final JRadioButton eval3 = buildAndReturnRadioButtons(rb3Text);

		final JRadioButton out1 = buildAndReturnRadioButtons(rb4Text);
		final JRadioButton out2 = buildAndReturnRadioButtons(rb5Text);

		final ButtonGroup evalMods = new ButtonGroup();
		evalMods.add(eval1);
		evalMods.add(eval2);
		evalMods.add(eval3);
		evalMods.setSelected(eval1.getModel(), true);

		final ButtonGroup outMods = new ButtonGroup();
		outMods.add(out1);
		outMods.add(out2);
		outMods.setSelected(out1.getModel(), true);

		final JTextArea resultTextArea = new JTextArea("Result");
		resultTextArea.setText("Result");
		final JScrollPane resultScrollPane = new JScrollPane(resultTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		final JLabel editHeader = new JLabel("EditHeader");
		editHeader.setText("Please enter one or more notes to work with:");

		final JLabel evalHeader = new JLabel("EvaluationHeader");
		evalHeader.setText("Possible Evaluations:");

		final JLabel outHeader = new JLabel("OutputHeader");
		outHeader.setText("Possible outputs:");

		final JButton evaluate = new JButton("Evaluate");
		evaluate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				final ButtonModel b = evalMods.getSelection();
				final String evalCommand = b.getActionCommand();
				if (evalCommand.equals(eval1.getActionCommand())) {
					doTransposingRoutine();
				} else if (evalCommand.equals(eval2.getActionCommand())) {
					doMajorScaleRoutine();
				} else if (evalCommand.equals(eval3.getActionCommand())) {
					doMinorScaleRoutine();
				}
			}

			private void doTransposingRoutine() {
				final NoteParser p = new NoteParser(editTextArea.getText());

				final NoteTransposer t = new NoteTransposer(p.getParsedNotes());

				final List<Integer> distances = new ArrayList<Integer>();
				for (int i = -11; i < 12; i++) {
					distances.add(i);
				}

				printNotes(t.getTransposedNotes(distances), p.getNumberOfParsedNotes(), -11);

			}

			private void printNotes(final List<Note> notesToPrint, final int notesPerLine, final int i) {
				final NotePrinter np = new NotePrinter(notesToPrint);
				final ButtonModel b = outMods.getSelection();
				final String outCommand = b.getActionCommand();
				if (outCommand.equals(out1.getActionCommand())) {
					resultTextArea.setText(np.printIndexedFullyQualifiedNoteNames(notesPerLine, i));
				} else if (outCommand.equals(out2.getActionCommand())) {
					resultTextArea.setText(np.printIndexedNoteNames(notesPerLine, i));
				}
			}

			private void doMajorScaleRoutine() {
				final NoteParser p = new NoteParser(editTextArea.getText());

				final Scale ms = new MajorScale(p.getParsedNotes().get(0));

				final NotePrinter np = new NotePrinter(ms.getScale());

				final ButtonModel b = outMods.getSelection();
				final String outCommand = b.getActionCommand();

				if (outCommand.equals(out1.getActionCommand())) {
					resultTextArea.setText(np.printFullyQualifiedNoteNames(-1));
				} else if (outCommand.equals(out2.getActionCommand())) {
					resultTextArea.setText(np.printNoteNames(-1));
				}
			}

			private void doMinorScaleRoutine() {
				final NoteParser p = new NoteParser(editTextArea.getText());

				final Scale ms = new MinorScale(p.getParsedNotes().get(0));

				final NotePrinter np = new NotePrinter(ms.getScale());

				final ButtonModel b = outMods.getSelection();
				final String outCommand = b.getActionCommand();

				if (outCommand.equals(out1.getActionCommand())) {
					resultTextArea.setText(np.printFullyQualifiedNoteNames(-1));
				} else if (outCommand.equals(out2.getActionCommand())) {
					resultTextArea.setText(np.printNoteNames(-1));
				}
			}

		});

		final JButton output = new JButton("Export");
		evaluate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				final ButtonModel b = evalMods.getSelection();
				final String command = b.getActionCommand();
				if (command.equals(out1.getActionCommand())) {
					exportAsFQNote();
				} else if (command.equals(out2.getActionCommand())) {
					exportAsSimpleNotes();
				}
			}

			private void exportAsFQNote() {
				// TODO Auto-generated method stub

			}

			private void exportAsSimpleNotes() {
				// TODO Auto-generated method stub

			}
		});

		final GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 5;
		c.ipady = 5;

		c.insets = new Insets(5, 15, 0, 20);

		assignConstraintParameters(c, 0, 1, 2, 2);
		wc.add(editHeader, c);
		assignConstraintParameters(c, 0, 3, 2, 2);
		wc.add(editScrollPane, c);

		c.insets = new Insets(5, 20, 0, 15);
		assignConstraintParameters(c, 2, 1, 2, 1);
		wc.add(evalHeader, c);
		assignConstraintParameters(c, 2, 2, 2, 1);
		wc.add(eval1, c);
		assignConstraintParameters(c, 2, 3, 2, 1);
		wc.add(eval2, c);
		assignConstraintParameters(c, 2, 4, 2, 1);
		wc.add(eval3, c);

		c.insets = new Insets(5, 20, 0, 15);
		assignConstraintParameters(c, 2, 5, 2, 1);
		wc.add(outHeader, c);
		assignConstraintParameters(c, 2, 6, 2, 1);
		wc.add(out1, c);
		assignConstraintParameters(c, 2, 7, 2, 1);
		wc.add(out2, c);

		c.insets = new Insets(5, 5, 0, 5);
		assignConstraintParameters(c, 0, 5, 1, 1);
		wc.add(evaluate, c);
		assignConstraintParameters(c, 1, 5, 1, 1);
		wc.add(output, c);

		assignConstraintParameters(c, 0, 6, 2, 2);
		wc.add(resultScrollPane, c);

		w.getContentPane().add(wc, BorderLayout.CENTER);
		w.pack();

	}

	/**
	 * 
	 * This method systematically creates menu items with the given name as name and action command.
	 * 
	 * @param name
	 *            The name of the JRadioButton and its ActionCommand.
	 * @return Returns the JRadioButton with the given specific parameters.
	 */
	private static JRadioButton buildAndReturnRadioButtons(final String name) {
		final JRadioButton rb = new JRadioButton(name);
		rb.setActionCommand(name);
		return rb;
	}

	/**
	 * @param c
	 *            GridBagConstraints
	 * @param x
	 *            gridx
	 * @param y
	 *            gridy
	 * @param dx
	 *            gridwidth
	 * @param dy
	 *            gridheight
	 */
	private static void assignConstraintParameters(final GridBagConstraints c, final int x, final int y, final int dx,
			final int dy) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = dx;
		c.gridheight = dy;
	}

	// ESCA-JAVA0118:
	private static class ImportListener implements ActionListener {

		/**
		 * The name of the text file to import.
		 */
		private final String fileName;

		/**
		 * 
		 * Creates a new ImportListener.
		 * 
		 * @param fileName
		 *            The name of the text file.
		 */
		ImportListener(final String fileName) {
			this.fileName = fileName;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			final ImportTextCommand importText = new ImportTextCommand(fileName);
			importText.execute();
			final ShowInformationCommand showInformation = new ShowInformationCommand(importText.getResult(), "Import");
			showInformation.execute();
		}

	}

	// ESCA-JAVA0118:
	private static class ExportListener implements ActionListener {

		/**
		 * The name of the text file to export.
		 */
		private final String fileName;

		/**
		 * 
		 * Creates a new ExportListener.
		 * 
		 * @param fileName
		 *            The name of the text file.
		 */
		ExportListener(final String fileName) {
			this.fileName = fileName;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new ExportTextCommand("Testzwecke", fileName).execute();
		}

	}

	// ESCA-JAVA0118:
	private static class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			new ExitCommand().execute();
		}

	}

	// ESCA-JAVA0118:
	private static class AboutListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			new ShowCopyrightCommand().execute();
		}

	}

	// ESCA-JAVA0118:
	private static class MusiciaWindowListener implements WindowListener {

		@Override
		public void windowOpened(final WindowEvent e) {
		}

		@Override
		public void windowClosing(final WindowEvent e) {
			new ExitCommand().execute();
		}

		@Override
		public void windowClosed(final WindowEvent e) {
		}

		@Override
		public void windowIconified(final WindowEvent e) {
		}

		@Override
		public void windowDeiconified(final WindowEvent e) {
		}

		@Override
		public void windowActivated(final WindowEvent e) {
		}

		@Override
		public void windowDeactivated(final WindowEvent e) {
		}

	}

}
