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

import com.mikronia.glib.vector.base.Vector3;

/**
 * 3-axis double precision vector
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public class Vector3d extends Vector3<Double> {
	
	/**
	 * Instantiates this object.
	 * 
	 * @param x a X-axis coordinate
	 * @param y a Y-axis coordinate
	 * @param z a Z-axis coordinate
	 */
	public Vector3d(Double x, Double y, Double z) {
		super(x, y, z);
	}
	
	/**
	 * Creates a new instance of this object with
	 * the coordinates {@code (0.0d, 0.0d, 0.0d)}.
	 */
	public Vector3d() {
		super(0.0d, 0.0d, 0.0d);
	}

	@Override
	public void add(Double x, Double y, Double z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	@Override
	public void sub(Double x, Double y, Double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}

	@Override
	public void mul(Double x, Double y, Double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
	}

	@Override
	public void div(Double x, Double y, Double z) {
		if (x == 0 || y == 0 || z == 0)
			return;

		this.x /= x;
		this.y /= y;
		this.z /= z;
	}
	
	@Override
	public Double dot(Vector3<Double> another) {
		return x * another.x() + y * another.y() + z * another.z();
	}

	@Override
	public Double cross(Vector3<Double> another) {
		return x * another.z() - y * another.y() - z * another.x();
	}
}
