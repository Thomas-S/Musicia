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

import java.util.HashMap;
import java.util.Map;

import org.musicia.exceptions.MusiciaIllegalArgumentException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * This class provides static methods to give representations of note durations.
 * 
 * @author Thomas Schulz
 * @version 1.0
 */
@Singleton
public class NoteDurationTable {

	/**
	 * The mapping from string to double.
	 */
	private Map<String, Double> stringToDouble = new HashMap<String, Double>(NUMBER_OF_SUPPORTED_DURATIONS);

	/**
	 * The mapping from double to string.
	 */
	private Map<Double, String> doubleToString = new HashMap<Double, String>(NUMBER_OF_SUPPORTED_DURATIONS);

	/**
	 * The number of supported durations.
	 */
	private static final int NUMBER_OF_SUPPORTED_DURATIONS = 15;

	@Inject
	public NoteDurationTable() {
		buildHashMap();
	}

	private void buildHashMap() {
		// ESCA-JAVA0076:
		stringToDouble.put("w", 1.0);
		stringToDouble.put("h.", 0.75);
		stringToDouble.put("h", 0.5);
		stringToDouble.put("q.", 0.375);
		stringToDouble.put("q", 0.25);
		stringToDouble.put("i.", 0.1875);
		stringToDouble.put("i", 0.125);
		stringToDouble.put("s.", 0.09375);
		stringToDouble.put("s", 0.0625);
		stringToDouble.put("t.", 0.046875);
		stringToDouble.put("t", 0.03125);
		stringToDouble.put("x.", 0.0234375);
		stringToDouble.put("x", 0.015625);
		stringToDouble.put("o.", 0.01171875);
		stringToDouble.put("o", 0.0078125);
		doubleToString.put(1.0, "w");
		doubleToString.put(0.75, "h.");
		doubleToString.put(0.5, "h");
		doubleToString.put(0.375, "q.");
		doubleToString.put(0.25, "q");
		doubleToString.put(0.1875, "i.");
		doubleToString.put(0.125, "i");
		doubleToString.put(0.09375, "s.");
		doubleToString.put(0.0625, "s");
		doubleToString.put(0.046875, "t.");
		doubleToString.put(0.03125, "t");
		doubleToString.put(0.0234375, "x.");
		doubleToString.put(0.015625, "x");
		doubleToString.put(0.01171875, "o.");
		doubleToString.put(0.0078125, "o");
	}

	/**
	 * @param s
	 *            The duration as string.
	 * @return Returns the duration as double.
	 */
	public double getAsDouble(String s) {
		if (stringToDouble.containsKey(s)) {
			return stringToDouble.get(s);
		}
		throw new MusiciaIllegalArgumentException(String.format("The note duration [%s] is not supported.", s));
	}

	/**
	 * @param d
	 *            The duration as double.
	 * @return Returns the duration as string.
	 */
	public String getAsString(double d) {
		if (doubleToString.containsKey(d)) {
			return doubleToString.get(d);
		}
		throw new MusiciaIllegalArgumentException(String.format("The note duration [%s] is not supported.", d));
	}

}
