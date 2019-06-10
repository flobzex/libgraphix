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

import java.awt.Polygon;
import java.util.ArrayList;

/**
 * MkModel
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibModel {
	
	private Polygon basePolygon;

	private ArrayList<Float> xCoords;
	private ArrayList<Float> yCoords;
	
	/**
	 * Creates a new {@code MkModel}.
	 */
	public GLibModel() {
		this.xCoords = new ArrayList<Float>();
		this.yCoords = new ArrayList<Float>();
	}
	
	/**
	 * Adds a new vertex.
	 * 
	 * @param x X-axis position
	 * @param y Y-axis position
	 */
	public void vertex(float x, float y) {
		this.basePolygon = null;

		this.xCoords.add(x);
		this.yCoords.add(y);
	}
	
	/**
	 * Converts the stored coordinates
	 * to a {@code Polygon} object.
	 * 
	 * @return the created polygon.
	 */
	Polygon getBasePolygon() {
		if (basePolygon == null) {
			basePolygon = new Polygon();
			
			for (int i = 0; i < xCoords.size(); i++) {
				int xCoord = xCoords.get(i).intValue();
				int yCoord = yCoords.get(i).intValue();
				
				basePolygon.addPoint(xCoord, yCoord);
			}
		}
		
		return basePolygon;
	}
}
