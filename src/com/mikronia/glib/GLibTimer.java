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

import com.mikronia.glib.utils.glib.GLibChecks;

/**
 * MkTimer
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibTimer {

	// static use only!
	private GLibTimer() { }
	
	// scheduled application
	private static GLibApp application;
	
	// properties
	private static boolean running;

	// the thread
	private static Thread thread;
	
	// fields
	private static String info = "...";
	private static int frames = 0;
	private static int ticks = 0;

	/**
	 * Initializes the timer.
	 * 
	 * @param app the application
	 */
	static void initialize(GLibApp app) {
		if (running)
			throw new IllegalStateException(
				"MkTimer was already set up.");
		
		GLibTimer.application = app;
		
		// initialize thread
		thread = new Thread(() -> GLibChecks.perform(() -> {
			performGameLoop();
		}, "performGameLoop", GLibTimer.class), "MkTimer");
		
		running = true;
		thread.start();
	}
	
	/**
	 * Terminates the timer.
	 */
	public static void terminate() {
		if (thread == null)
			throw new IllegalStateException(
				"MkTimer wasn't set up yet.");
		running = false;
	}
	
	/////////////////////////
	
	/**
	 * Gets the number of frames
	 * rendered by this timer.
	 */
	public static int frames() {
		return frames;
	}
	
	/**
	 * Gets the number of ticks
	 * performed by this timer.
	 */
	public static int ticks() {
		return ticks;
	}
	
	/**
	 * Creates a {@code String}
	 * object containing a brief
	 * description of this timer.
	 */
	public static String info() {
		return info;
	}
	
	/////////////////////////
	
	/**
	 * Loop scope.
	 */
	private static void performGameLoop() throws Exception {
		long frameCounterMarker = System.currentTimeMillis();
		
		// timer stuff
		final double frameRate = GLib.getFrames();
		long sleepTime = (long) (1000 / frameRate);
		double nanos = 1.0e9 / frameRate;
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		
		int frames = 0;
		int ticks = 0;
		
		// application stuff
		application.init();
		
		while (running) {
			var canvas = application.canvas();
			var batch = canvas.getGraphicsBatch();
			
			// count delta time
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / nanos;
			lastTime = currentTime;
			
			// update application
			while (delta > 1) {
				// update application
				application.update(delta--);
				ticks++;
			}

			// prepare canvas
			canvas.prepareToDraw();
			
			if (batch != null) {
				// render application
				application.render(batch);
				frames++;
				
				// show buffer
				canvas.drawOnScreen();
			}
			
			// sleep thread
			if (!GLib.hasUnlimitedFrames())
				Thread.sleep(sleepTime);

			// calculate FPS and UPS
			if (System.currentTimeMillis() - frameCounterMarker >= 1000) {
				GLibTimer.info = String.format("%d fps, %d ups", frames, ticks);				
				GLibTimer.frames = frames;
				GLibTimer.ticks = ticks;
				
				frameCounterMarker += 1000;
				
				frames = 0;
				ticks = 0;
			}
		}
		
		// terminate GLib
		thread.join();
	}
}
