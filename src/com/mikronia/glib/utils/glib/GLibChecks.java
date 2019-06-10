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

package com.mikronia.glib.utils.glib;

/**
 * GLib assertion
 *
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public final class GLibChecks {

	/**
	 * Tests whether the given object is {@code null}.
	 * <p>
	 * The application crashes if the given object is {@code null}.
	 * 
	 * @param object an object
	 * @param error error message
	 */
	public static void assertNotNull(Object object, String error) {
		if (object != null) return;
		
		GLibSystem.crashApplication("The requested object is null.", new AssertionError(error));
	}
	
	/**
	 * Tests the value of the parameter {@code condition}.
	 * <p>
	 * The application crashes if {@code condition} is {@code true}.
	 * 
	 * @param condition the condition to be tested
	 * @param error error message
	 */
	public static void assertTrue(boolean condition, String error) {
		if (condition) return;
		
		GLibSystem.crashApplication(null, new AssertionError(error));
	}
	
	/**
	 * Tests the value of the parameter {@code condition}.
	 * <p>
	 * The application crashes if {@code condition} is {@code false}.
	 * 
	 * @param condition the condition to be tested
	 * @param error error message
	 */
	public static void assertFalse(boolean condition, String error) {
		if (!condition) return;
		
		GLibSystem.crashApplication(null, new AssertionError(error));
	}
	
	/**
	 * Attempts to perform the given {@code action} successfully.
	 * If any error occurs during the process, the application
	 * is destroyed.
	 * 
	 * @param action the action
	 * @param name the action name
	 * @param source action source
	 */
	public static void perform(GLibSafeAction action, String name, Object source) {
		try {
			action.actionPerformed();
		} catch (Exception e) {
			GLibSystem.crashApplication("Failed to perform the requested action: " + name, e);
		}
	}

	/**
	 * Validates the given object. This function will
	 * throw a {@code NullPointerException} if the
	 * given object is {@code null} and the app will
	 * be interrupted.
	 * 
	 * @param object the object to be tested
	 * @param error error message
	 * @return the given object.
	 */
	public static <T> T requireNonNull(T object, String error) {
		if (object == null) {
			Exception e = new NullPointerException(error);
			GLibSystem.crashApplication("The requested object is null.", e);
		}
		return object;
	}
	
	/////////////////////////

	/**
	 * GLib safe action
	 * 
	 * @since GLib 1.0.0
	 * @version 1.0
	 * 
	 * @author Thaynan M. Silva
	 */
	@FunctionalInterface
	public interface GLibSafeAction {

		/**
		 * Invoked when an action occurs.
		 * 
		 * @throws Exception if any error occur.
		 */
		public void actionPerformed() throws Exception;
		
	}
}
