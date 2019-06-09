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

package com.mikronia.glib;

import java.util.Objects;

/**
 * GLib WindowEvent
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibWindowEvent {
	
	/** Raised when the window is opened. */
	public static final int WINDOW_OPENED			= 0;
	
	/** Raised when the window is iconified. */
	public static final int WINDOW_ICONIFIED		= 1;
	
	/** Raised when the window is deiconified. */
	public static final int WINDOW_DEICONIFIED		= 2;
	
	/** Raised when the window gains focus. */
	public static final int WINDOW_FOCUS_GAINED		= 3;
	
	/** Raised when the window loses focus. */
	public static final int WINDOW_FOCUS_LOST		= 4;
	
	/** Raised when the window is resized. */
	public static final int WINDOW_RESIZED			= 5;
	
	/** Raised when the window is moved. */
	public static final int WINDOW_MOVED 	 		= 6;
	
	/** Raised when the close button of the window is clicked. */
	public static final int WINDOW_CLOSING			= 7;
	
	///////////////////////////////////
	
	// event's source window
	private final GLibWindow source;
	
	// time when event was raised
	private final long raiseTime;
	
	// event code
	private final int event;
	
	/**
	 * Instantiates this object.
	 * 
	 * @param event event code
	 */
	GLibWindowEvent(GLibWindow source, int event) {
		Objects.requireNonNull(source, "source == null!");
		
		this.raiseTime = System.currentTimeMillis();
		
		this.source = source;
		this.event = event;
	}
	
	/**
	 * Gets the time when the event
	 * was raised in milliseconds.
	 */
	public long getLaunchTime() {
		return raiseTime;
	}
	
	/**
	 * Gets the window that raised
	 * this event.
	 */
	public GLibWindow getSource() {
		return source;
	}
	
	/**
	 * Gets the event code.
	 */
	public int getEvent() {
		return event;
	}
}
