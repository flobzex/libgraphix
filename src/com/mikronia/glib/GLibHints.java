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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.util.HashMap;

import com.mikronia.glib.utils.glib.GLibSystem;

/**
 * GLib hint manager
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
final class GLibHints {

	private static HashMap<Integer, Integer> hints = new HashMap<Integer, Integer>();
	
	private static Graphics2D sourceGraphics = null;
	
	/////////////////////////

	public static void set(int property, int value) {
		updateHintValue(property, value);
	}

	public static int get(int property) {
		if (!hints.containsKey(property))
			return GLib.UNKNOWN;
		return hints.get(property);
	}

	/////////////////////////
	
	public static void applyHintsToGraphics(Graphics2D g) {
		sourceGraphics = g;
	}
	
	/////////////////////////
	
	private static void updateHintValue(int hint, int value) {
		Key hintKey = (Key) getHintKey(hint);
		var hintVal = getHintValue(hint, value);
		
		// changes the rendering hint
		sourceGraphics.setRenderingHint(hintKey, hintVal);
	}

	/////////////////////////
	
	private static Object getHintKey(int key) {
		if (key == GLib.INTERPOLATION_MODE) {
			return RenderingHints.KEY_INTERPOLATION;
		} else if (key == GLib.TEXT_ANTIALIAS_MODE) {
			return RenderingHints.KEY_TEXT_ANTIALIASING;
		} else if (key == GLib.RENDERING_MODE) {
			return RenderingHints.KEY_RENDERING;
		} else if (key == GLib.COLOR_RENDERING_MODE) {
			return RenderingHints.KEY_COLOR_RENDERING;
		} else if (key == GLib.ALPHA_INTERPOLATION_MODE) {
			return RenderingHints.KEY_INTERPOLATION;
		} else if (key == GLib.STROKE_MODE) {
			return RenderingHints.KEY_STROKE_CONTROL;
		} else if (key == GLib.ANTIALIAS) {
			return RenderingHints.KEY_ANTIALIASING;
		} else if (key == GLib.DITHERING) {
			return RenderingHints.KEY_DITHERING;
		} else if (key == GLib.TEXT_ANTIALIAS) {
			return RenderingHints.KEY_TEXT_ANTIALIASING;
		} else if (key == GLib.FRAC_METRICS) {
			return RenderingHints.KEY_FRACTIONALMETRICS;
		}
		
		throwKeyError(key);

		return null;
	}
	
	private static Object getHintValue(int hint, int value) {
		if (hint == GLib.INTERPOLATION_MODE) {
			return getInterpolationMode(value);
		} else if (hint == GLib.TEXT_ANTIALIAS_MODE) {
			return getTextAntialiasMode(value);
		} else if (hint == GLib.RENDERING_MODE) {
			return getRenderingMode(value);
		} else if (hint == GLib.COLOR_RENDERING_MODE) {
			return getColorRenderingMode(value);
		} else if (hint == GLib.ALPHA_INTERPOLATION_MODE) {
			return getAlphaInterpolationMode(value);
		} else if (hint == GLib.STROKE_MODE) {
			return getStrokeMode(value);
		} else if (hint == GLib.ANTIALIAS) {
			return getAntialiasing(value);
		} else if (hint == GLib.DITHERING) {
			return getDithering(value);
		} else if (hint == GLib.TEXT_ANTIALIAS) {
			return getTextAntialiasing(value);
		} else if (hint == GLib.FRAC_METRICS) {
			return getFractionalMetrics(value);
		}
		
		throwKeyError(hint);

		return null;
	}
	
	/////////////////////////
	
	private static Object getInterpolationMode(int value) {
		if (value == GLib.BICUBIC) {
			return RenderingHints.VALUE_INTERPOLATION_BICUBIC;
		} else if (value == GLib.BILINEAR) {
			return RenderingHints.VALUE_INTERPOLATION_BILINEAR;
		} else if (value == GLib.NEAREST) {
			return RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_INTERPOLATION_BICUBIC;
		}
		
		throwValueError(GLib.INTERPOLATION_MODE, value);
		
		return null;
	}
	
	private static Object getTextAntialiasMode(int value) {
		if (value == GLib.GASP) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_GASP;
		} else if (value == GLib.HRGB) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB;
		} else if (value == GLib.HBGR) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR;
		} else if (value == GLib.VRGB) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB;
		} else if (value == GLib.VBGR) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
		}
	
		throwValueError(GLib.TEXT_ANTIALIAS_MODE, value);

		return null;
	}
	
	private static Object getRenderingMode(int value) {
		if (value == GLib.FANCY) {
			return RenderingHints.VALUE_RENDER_QUALITY;
		} else if (value == GLib.FAST) {
			return RenderingHints.VALUE_RENDER_SPEED;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_RENDER_SPEED;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_RENDER_DEFAULT;
		}
		
		throwValueError(GLib.RENDERING_MODE, value);
	
		return null;
	}
	
	private static Object getColorRenderingMode(int value) {
		if (value == GLib.FANCY) {
			return RenderingHints.VALUE_COLOR_RENDER_QUALITY;
		} else if (value == GLib.FAST) {
			return RenderingHints.VALUE_COLOR_RENDER_SPEED;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_COLOR_RENDER_SPEED;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_COLOR_RENDER_DEFAULT;
		}

		throwValueError(GLib.COLOR_RENDERING_MODE, value);
		
		return null;
	}
	
	private static Object getAlphaInterpolationMode(int value) {
		if (value == GLib.FANCY) {
			return RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY;
		} else if (value == GLib.FAST) {
			return RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT;
		}
		
		throwValueError(GLib.ALPHA_INTERPOLATION_MODE, value);
		
		return null;
	}
	
	private static Object getStrokeMode(int value) {
		if (value == GLib.PURE) {
			return RenderingHints.VALUE_STROKE_PURE;
		} else if (value == GLib.NORMALIZE) {
			return RenderingHints.VALUE_STROKE_NORMALIZE;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_STROKE_PURE;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_STROKE_DEFAULT;
		}

		throwValueError(GLib.STROKE_MODE, value);
		
		return null;
	}
	
	private static Object getAntialiasing(int value) {
		if (value == GLib.TRUE) {
			return RenderingHints.VALUE_ANTIALIAS_ON;
		} else if (value == GLib.FALSE) {
			return RenderingHints.VALUE_ANTIALIAS_OFF;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_ANTIALIAS_OFF;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_ANTIALIAS_DEFAULT;
		}

		throwValueError(GLib.ANTIALIAS, value);
		
		return null;
	}
	
	private static Object getDithering(int value) {
		if (value == GLib.TRUE) {
			return RenderingHints.VALUE_DITHER_ENABLE;
		} else if (value == GLib.FALSE) {
			return RenderingHints.VALUE_DITHER_DISABLE;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_DITHER_DISABLE;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_DITHER_DEFAULT;
		}

		throwValueError(GLib.DITHERING, value);

		return null;
	}
	
	private static Object getTextAntialiasing(int value) {
		if (value == GLib.TRUE) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
		} else if (value == GLib.FALSE) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
		}

		throwValueError(GLib.TEXT_ANTIALIAS, value);

		return null;
	}

	private static Object getFractionalMetrics(int value) {
		if (value == GLib.TRUE) {
			return RenderingHints.VALUE_FRACTIONALMETRICS_ON;
		} else if (value == GLib.FALSE) {
			return RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
		} else if (value == GLib.DEFAULT) {
			return RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
		} else if (value == GLib.DEFAULT_SYSTEM) {
			return RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT;
		}

		throwValueError(GLib.FRAC_METRICS, value);

		return null;
	}
	
	/////////////////////////
	
	private static void throwKeyError(int key) {
		String ks = Integer.toHexString(key);
		
		// crashes the application
		GLibSystem.crashApplication(String.format(
				"Invalid hint key: #%s", ks));
	}
	
	private static void throwValueError(int key, int value) {
		String ks = Integer.toHexString(key);
		String vs = Integer.toHexString(value);

		// crashes the application
		GLibSystem.crashApplication(String.format(
				"Invalid value #%s for hint #%s", ks, vs));
	}
}
