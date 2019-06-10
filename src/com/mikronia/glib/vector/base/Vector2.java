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

package com.mikronia.glib.vector.base;

/**
 * 2-axis vector
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public abstract class Vector2<T extends Number> {

	protected T x;
	protected T y;
	
	/**
	 * Instantiates this object.
	 * 
	 * @param x X-axis coordinate
	 * @param y Y-axis coordinate
	 */
	public Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	/////////////////////////
	
	/**
	 * Sets the coordinates of this
	 * vector.
	 * 
	 * @param x new X-axis coordinate
	 * @param y new Y-axis coordinate
	 */
	public void move(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds the given values to the
	 * coordinates of this vector.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 */
	public abstract void add(T x, T y);
	
	/**
	 * Subtracts the given values from
	 * the coordinates of this vector.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 */
	public abstract void sub(T x, T y);
	
	/**
	 * Divides the coordinates of this
	 * vector by the given values.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 */
	public abstract void mul(T x, T y);
	
	/**
	 * Divides the coordinates of this
	 * vector by the given values.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 */
	public abstract void div(T x, T y);

	/**
	 * Calculates the dot product of
	 * this vector and the given vector.
	 * 
	 * @param another another vector
	 * @return the dot product.
	 */
	public abstract T dot(Vector2<T> another);
	
	/**
	 * Calculates the cross product of
	 * this vector and the given vector.
	 * 
	 * @param another another vector
	 * @return the cross product.
	 */
	public abstract T cross(Vector2<T> another);
	
	/////////////////////////
	
	/**
	 * Gets the X-axis value of this
	 * vector.
	 * 
	 * @return the X-axis value.
	 */
	public T x() {
		return x;
	}
	
	/**
	 * Gets the Y-axis value of this
	 * vector.
	 * 
	 * @return the Y-axis value.
	 */
	public T y() {
		return y;
	}
	
	/**
	 * Gets a string representation
	 * of this object.
	 * 
	 * @return a string representation
	 * of this object.
	 */
	public String toString() {
		return String.format("%s[x=%d,y=%d]",
				getClass().getName(), x, y);
	}
}
