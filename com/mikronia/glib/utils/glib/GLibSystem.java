/*
 * MIT License
 * 
 * Copyright (c) 2019 Thaynan Silva
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mikronia.glib.utils.glib;

import com.mikronia.glib.GLib;
import com.mikronia.glib.GLibAudio;
import com.mikronia.glib.GLibInput;
import com.mikronia.glib.GLibTimer;

/**
 * GLib system utilities
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibSystem {

	private GLibSystem() { }
	
	/**
	 * Crashes the application.
	 * 
	 * @param error error message
	 * @param cause error cause (optional)
	 */
	public static void crashApplication(String error, Throwable cause) {
		System.err.printf("FATAL ERROR: ", error).println();
		
		if (cause != null)
			System.err.println(createExceptionString(cause));
		
		// destroy stuff
		GLibInput.terminate();
		GLibTimer.terminate();
		GLibAudio.terminate();
		
		// quits app
		GLib.getApplication().exit();

		// destroys application
		System.exit(1);
	}
	
	public static void crashApplication(String error) {
		crashApplication(error, null);
	}
	
	private static String createExceptionString(Throwable ex) {
		var sb = new StringBuilder();
		
		sb.append(ex.getClass().getName())
		  .append(": ").append(ex.getMessage());

		for (var stack : ex.getStackTrace())
			sb.append("\n\tat").append(stack);
		
		return sb.toString();
	}
}
