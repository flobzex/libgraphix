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

public class GLibMath {

	public static float cos(float angle) {
		return (float) Math.cos(angle);
	}
	
	public static float sin(float angle) {
		return (float) Math.sin(angle);
	}
	
	public static float normalizeAngle(float angle) {
		float newAngle = Math.abs(angle);
		
		while (newAngle > Math.PI)
			newAngle -= Math.PI;
		
		return angle > 0 ? newAngle : -newAngle;
	}
	
	public static double pithagoreanTheorem(double b, double c) {
		return Math.sqrt((b * b) + (c * c));
	}
	
	public static double euclideanDistance(double x1, double y1, double x2, double y2) {
		return pithagoreanTheorem((x2 - x1), (y2 - y1));
	}
	
	/**
	 * Reduces the given {@code value} by subtracting
	 * {@code amount} from it until it arrives {@code 0}.
	 * 
	 * @param value value to be reduced
	 * @param amount amount to be reduced
	 * @param precision precision factor
	 * @return the result of the reduced value.
	 */
	public static float decay(float value, float amount, float precision) {
		if (value == 0) return 0;
		
		// creates the new value
		float newValue = Math.abs(value);
		
		// makes amount absolute
		amount = Math.abs(amount);
		
		// reduces amount from value
		newValue -= amount;

		return (newValue < precision) ? 0 : ((value > 0) ? newValue : -newValue);
	}
}
