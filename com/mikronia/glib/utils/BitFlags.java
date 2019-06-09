/*
 * MIT License
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

package com.mikronia.glib.utils;

/**
 * Bit flags
 *  
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public final class BitFlags {
	
	// static use only!
	private BitFlags() { }
	
	///////////////////////////////////
	
	/**
	 * Tests whether a flag is enabled.
	 * 
	 * @param flag flag ID
	 * @param flags where the flag is
	 * 
	 * @return {@code true} if the flag exists
	 * in the given flags, {@code false} otherwise.
	 */
	public static boolean getFlag(int flag, int flags) {
		return (flags & flag) == flag;
	}

	/**
	 * Sets the state of a flag.
	 * 
	 * @param flag flag ID
	 * @param flags where the flag is
	 * @param value new state
	 * @return the update version of {@code flags}.
	 */
	public static int setFlag(int flag, int flags, boolean value) {
		if (getFlag(flag, flags) != value)
			return (flags | flag);
		return flags;
	}
	
	/**
	 * Switches the value of {@code flag}.
	 * 
	 * @param flag flag ID
	 * @param flags where the flag is
	 * @return the update version of {@code flags}.
	 */
	public static int switchFlag(int flag, int flags) {
		return setFlag(flag, flags, !getFlag(flag, flags));
	}
}
