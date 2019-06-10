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

package com.mikronia.glib.vector;

import com.mikronia.glib.vector.base.Vector2;

/**
 * 2-axis single precision vector
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public class Vector2f extends Vector2<Float> {
	
	/**
	 * Instantiates this object.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 */
	public Vector2f(Float x, Float y) {
		super(x, y);
	}
	
	/**
	 * Creates a new instance of this object with
	 * the coordinates {@code (0.0f, 0.0f)}.
	 */
	public Vector2f() {
		super(0.0f, 0.0f);
	}

	@Override
	public void add(Float x, Float y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public void sub(Float x, Float y) {
		this.x -= x;
		this.y -= y;
	}

	@Override
	public void mul(Float x, Float y) {
		this.x *= x;
		this.y *= y;
	}

	@Override
	public void div(Float x, Float y) {
		if (x == 0 || y == 0)
			return;

		this.x /= x;
		this.y /= y;
	}

	@Override
	public Float dot(Vector2<Float> another) {
		return this.x() * another.x() + this.y() * another.y();
	}

	@Override
	public Float cross(Vector2<Float> another) {
		return this.x() * another.y() - this.y() * another.x();
	}
}
