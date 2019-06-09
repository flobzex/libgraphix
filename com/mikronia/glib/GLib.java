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
import com.mikronia.glib.utils.glib.GLibSystem;

/**
 * GLib master
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLib {

	////////////// VALUES /////////////

	// unknown element
	public static final int UNKNOWN						= 0xFFF0;
	
	// default value
	public static final int DEFAULT						= 0xFFF1;

	// system's default value
	public static final int DEFAULT_SYSTEM				= 0xFFF2;
	
	// true/false values
	public static final int FALSE						= 0x0000;
	public static final int TRUE						= 0x0001;

	// rendering/coloring modes
	public static final int FAST						= 0x1001;
	public static final int FANCY						= 0x1002;
	
	// image interpolation modes
	public static final int BICUBIC						= 0x1003;
	public static final int BILINEAR					= 0x1004;
	public static final int NEAREST						= 0x1005;
	
	// text anti-aliasing modes
	public static final int GASP						= 0x1006;
	public static final int HRGB						= 0x1007;
	public static final int HBGR						= 0x1008;
	public static final int VRGB						= 0x1009;
	public static final int VBGR						= 0x100A;
	
	// stroke mode
	public static final int PURE						= 0x100B;
	public static final int NORMALIZE					= 0x100C;

	/////////// PROPERTIES ////////////
	
	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  BICUBIC
	 *  BILINEAR
	 *  NEAREST
	 *  DEFAULT
	 * </pre>
	 */
	public static final int INTERPOLATION_MODE			= 0x2001;
	
	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  GASP
	 *  HRGB
	 *  HBGR
	 *  VRGB
	 *  VBGR
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int TEXT_ANTIALIAS_MODE			= 0x2002;

	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  FAST
	 *  FANCY
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int RENDERING_MODE				= 0x2003;
	
	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  FAST
	 *  FANCY
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int COLOR_RENDERING_MODE		= 0x2004;
	
	/**
	 * 
	 * 
	 * Valid values:
	 * 
	 * <pre>
	 *  BICUBIC
	 *  BILINEAR
	 *  NEAREST
	 *  DEFAULT
	 * </pre>
	 */
	public static final int ALPHA_INTERPOLATION_MODE	= 0x2005;
	
	/**
	 * 
	 * 
	 * Valid values:
	 * 
	 * <pre>
	 *  PURE
	 *  NORMALIZE
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int STROKE_MODE					= 0x2006;
	
	////////////// HINTS //////////////
	
	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  TRUE
	 *  FALSE
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int ANTIALIAS					= 0x3001;

	/**
	 * Valid values:
	 * 
	 * <pre>
	 *  TRUE
	 *  FALSE
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int DITHERING					= 0x3002;
	
	/**
	 * Enables/disables anti-aliasing for texts.
	 * 
	 * Valid values:
	 * 
	 * <pre>
	 *  TRUE
	 *  FALSE
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int TEXT_ANTIALIAS				= 0x3003;
	
	/**
	 * Determines whether the font metrics are
	 * calculated with full precision.
	 * 
	 * Valid values:
	 * 
	 * <pre>
	 *  TRUE
	 *  FALSE
	 *  DEFAULT
	 *  DEFAULT_SYSTEM
	 * </pre>
	 */
	public static final int FRAC_METRICS				= 0x3004;
	
	///////// CONFIGURATIONS //////////
	
	private static GLibApp application;
	
	private static boolean unlimitedFrames;

	private static int frameRate;
	
	// static use only!
	private GLib() { }
	
	// actions
	
	/**
	 * Initializes the application.
	 * 
	 * @param application the application
	 * @param width image width
	 * @param height image height
	 * @param pixelSize pixel size
	 * @param fullscreen indicates whether the application
	 * will start with fullscreen mode
	 * @param fps frames per second
	 * @param unlimitedFPS indicates whether the application
	 * will not limit the frames to {@code fps}.
	 * <p>
	 * <b>NOTE</b>: the unlimited frame rate does not work
	 * well with the pixel size 1.
	 */
	public static void initialize(GLibApp application, int width, int height, int pixelSize, int fps, boolean fullscreen, boolean unlimitedFPS) {
		GLib.application = GLibChecks.requireNonNull(application, "Application must not be null!");
		
		// test if frameRate is from 1 to 999
		GLibChecks.assertFalse(fps < 0 && fps > 1000, "Frame frame must be from 1 up to 999.");
		
		// store values
		GLib.unlimitedFrames = unlimitedFPS;
		GLib.frameRate = fps;
		
		// configure window
		var window = GLib.application.window();
		window.setSize(width, height);
		window.centralize();
		window.show();
		
		// set window as fullscreen
		window.setFullscreen(fullscreen);
		
		// configure canvas
		var canvas = GLib.application.canvas();
		canvas.setPixelSize(pixelSize);
		
		// initialize components
		GLibTimer.initialize(GLib.application);
		GLibInput.initialize();
		GLibAudio.initialize();
	}
	
	/**
	 * Terminates the application with
	 * no errors.
	 */
	public static void terminate() {
		validateApplicationInitialization();
		
		GLibAudio.terminate();
		GLibInput.terminate();
		GLibTimer.terminate();
		
		application.exit();

		System.exit(0);
	}
	
	/////////////////////////
	
	// setters 
	
	/**
	 * Sets the value of a hint.
	 * 
	 * @param property the property
	 * @param value value to be set
	 */
	public static void setHint(int hint, int value) {
		validateApplicationInitialization();
		
		GLibHints.set(hint, value);
	}
	
	/**
	 * Updates the pixel size.
	 * 
	 * @param pixelSize new pixel size
	 */
	public static void setPixelSize(int pixelSize) {
		validateApplicationInitialization();
		
		// get canvas form app
		var canvas = application.canvas();
		
		// updates the pixel size
		canvas.setPixelSize(pixelSize);
		
		// invalidates the canvas to recreate graphics
		canvas.invalidateGraphics();
	}
	
	// getters
	
	/**
	 * Gets the value of a hint.
	 * 
	 * @param hint the property
	 * @return the value of the property
	 * or {@link #UNKNOWN} if the given
	 * {@code property} is invalid.
	 */
	public static int getHint(int hint) {
		validateApplicationInitialization();
		return GLibHints.get(hint);
	}
	
	/**
	 * Gets the pixel size.
	 */
	public static int getPixelSize() {
		validateApplicationInitialization();
		return application.canvas().getPixelSize();
	}
	
	public static int getWindowWidth() {
		validateApplicationInitialization();
		return application.window().getSize().width;
	}
	
	public static int getWindowHeight() {
		validateApplicationInitialization();
		return application.window().getSize().height;
	}
	
	public static int getScreenWidth() {
		validateApplicationInitialization();
		return application.canvas().getScreenWidth();
	}
	
	public static int getScreenHeight() {
		validateApplicationInitialization();
		return application.canvas().getScreenHeight();
	}

	/**
	 * Gets the state of unlimited frames.
	 */
	public static boolean hasUnlimitedFrames() {
		return unlimitedFrames;
	}
	
	/**
	 * Gets the number of frames.
	 */
	public static int getFrames() {
		return frameRate;
	}
	
	/**
	 * Gets the loaded application.
	 */
	public static GLibApp getApplication() {
		validateApplicationInitialization();
		return application;
	}
	
	/////////////////////////
	
	/*
	 * Tests whether the application was already initialized
	 * and crashes the engine if it was not.
	 */
	private static void validateApplicationInitialization() {
		if (application != null) return;
		
		GLibSystem.crashApplication("The application wasn't initialized yet.");
	}
}
