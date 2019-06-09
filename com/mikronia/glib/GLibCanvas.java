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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * GLib canvas
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibCanvas extends Canvas {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage pixelatedBuffer  = null;
	private BufferStrategy graphicsBuffer  = null;
	
	private Graphics2D sourceGraphics      = null;
	private GLibBatch graphicsBatch        = null;
	
	private int pixelSize                  = 1;

	private boolean graphicsValidated      = false;
	
	/**
	 * Instantiates this object.
	 */
	public GLibCanvas() {
		setFocusable(true);
		requestFocus();
		
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
	}

	// ACTIONS //
	
	/**
	 * Show drawn stuff on the screen.
	 */
	void prepareToDraw() {
		var parent = getParent();
		
		// does not prepare if parent component is
		// not valid or isn't showing.
		if (parent == null || !parent.isShowing())
			return;
		
		// does not prepare if current graphics
		// are valid.
		if (graphicsValidated)
			return;
		
		fitCanvasToParent();
		createGraphicsElements();
		
		graphicsValidated = true;
	}
	
	void drawOnScreen() {
		if (!graphicsValidated)
			return;
		
		drawBufferOnScreen();
	}
	
	private void fitCanvasToParent() {
		setSize(getParent().getSize());
	}
	
	private void createGraphicsElements() {
		int imageWidth = getWidth() / pixelSize;
		int imageHeight = getHeight() / pixelSize;
		
		if (pixelSize == 1) {
			graphicsBuffer = getBufferStrategy();
			pixelatedBuffer = null;
			
			sourceGraphics = (Graphics2D) graphicsBuffer.getDrawGraphics();
		} else {
			pixelatedBuffer = new BufferedImage(imageWidth,
					imageHeight, BufferedImage.TYPE_INT_RGB);
			
			sourceGraphics = (Graphics2D) pixelatedBuffer.getGraphics();
		}
		
		// set this app's graphics as default
		GLibHints.applyHintsToGraphics(sourceGraphics);
		
		// applies default rendering hints
		applyInitialRenderingHints();
		
		// creates graphics batch
		graphicsBatch = new GLibBatch(sourceGraphics, imageWidth, imageHeight);
	}
	
	private void applyInitialRenderingHints() {
		GLib.setHint(GLib.TEXT_ANTIALIAS_MODE, GLib.DEFAULT_SYSTEM);
		GLib.setHint(GLib.INTERPOLATION_MODE, GLib.BICUBIC);
		GLib.setHint(GLib.RENDERING_MODE, GLib.FAST);
		GLib.setHint(GLib.COLOR_RENDERING_MODE, GLib.FAST);
		GLib.setHint(GLib.STROKE_MODE, GLib.PURE);
		GLib.setHint(GLib.TEXT_ANTIALIAS, GLib.FALSE);
		GLib.setHint(GLib.ANTIALIAS, GLib.FALSE);
		GLib.setHint(GLib.DITHERING, GLib.FALSE);
		GLib.setHint(GLib.FRAC_METRICS, GLib.FALSE);
	}
	
	private void drawBufferOnScreen() {
		if (pixelSize != 1) {
			// draw image
			sourceGraphics.drawImage(pixelatedBuffer, 0, 0, getWidth(), getHeight(), null);
		}

		graphicsBuffer.show();
	}
	
	@Override
	public BufferStrategy getBufferStrategy() {
		if (super.getBufferStrategy() == null) {
			this.createBufferStrategy(2);
		}
		return super.getBufferStrategy();
	}
	
	/**
	 * Invalidates the canvas.
	 */
	void invalidateGraphics() {
		graphicsValidated = false;
	}
	
	/////////////////////////
	
	// SETTERS //
	
	public void setPixelSize(int pixelSize) {
		int maxPixelSize = calculateMaximumPixelSize(
			getWidth() / Math.max(pixelSize, 1), getHeight() / Math.max(pixelSize, 1));

		if (pixelSize < 1 || pixelSize > maxPixelSize)
			throw new IllegalArgumentException(String.format(
					"Pixel size must be from 1 to %d. You typed %d", maxPixelSize, pixelSize));
		
		this.pixelSize = pixelSize;
	}

	private int calculateMaximumPixelSize(int width, int height) {
		int totalPixels = width * height;
		int maximumPixelSize = 1;
		
		while ((totalPixels / maximumPixelSize) >= 1000) {
			maximumPixelSize++;
		}
		
		return maximumPixelSize;
	}

	// GETTERS //

	public int getPixelSize() {
		return pixelSize;
	}

	/////////////////////////
	
	public int getScreenWidth() {
		return getWidth() / pixelSize;
	}
	
	public int getScreenHeight() {
		return getHeight() / pixelSize;
	}

	/////////////////////////
	
	public GLibBatch getGraphicsBatch() {
		return graphicsBatch;
	}
	
	@Override
	public String toString() {
		return String.format("%s{pixelSize=%d, width=%d, height=%d}",
			getClass().getName(), pixelSize, getWidth() / pixelSize, getHeight() / pixelSize);
	};	
}
