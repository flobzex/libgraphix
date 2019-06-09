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

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import com.mikronia.glib.utils.glib.GLibChecks;
import com.mikronia.glib.utils.glib.GLibSystem;

/**
 * GLib font
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public class GLibFont {
	
	public static final GLibFont MONOSPACED		= new GLibFont(Font.MONOSPACED, GLibFont.PLAIN, GLibFont.DEFAULT_SIZE);
	public static final GLibFont DIALOG			= new GLibFont(Font.DIALOG, GLibFont.PLAIN, GLibFont.DEFAULT_SIZE);
	public static final GLibFont DIALOG_INPUT	= new GLibFont(Font.DIALOG_INPUT, GLibFont.PLAIN, GLibFont.DEFAULT_SIZE);
	public static final GLibFont SANS_SERIF		= new GLibFont(Font.SANS_SERIF, GLibFont.PLAIN, GLibFont.DEFAULT_SIZE);
	public static final GLibFont SERIF			= new GLibFont(Font.SERIF, GLibFont.PLAIN, GLibFont.DEFAULT_SIZE);
	
	public static final float DEFAULT_SIZE  = 10.0f;
	
	public static final int PLAIN			= 0;
	public static final int ITALIC			= 1;
	public static final int BOLD			= 2;
	public static final int UNDERLINE		= 4;
	public static final int STRIKEOUT		= 8;

	///////////////////////////////////
	
	private final Font baseFont;
	
	private final String fontName;
	private final float fontSize;

	private final int fontStyle;

	private GLibFont(String name, int style, float size) {
		this.fontName = name;
		this.fontStyle = style;
		this.fontSize = size;
		
		this.baseFont = createFontDerivation(
			new Font(name, 0, (int) size), style, size);
	}

	private GLibFont(Font baseFont, int style, float size) {
		this.fontName = baseFont.getName();
		this.fontStyle = style;
		this.fontSize = size;
		
		this.baseFont = createFontDerivation(baseFont, style, size);
	}

	///////////////////////////////////

	public GLibFont deriveFont(int style) {
		return new GLibFont(fontName, style, fontSize);
	}

	public GLibFont deriveFont(float size) {
		return new GLibFont(fontName, fontStyle, size);
	}
	
	public GLibFont deriveFont(int style, float size) {
		return new GLibFont(fontName, style, size);
	}

	///////////////////////////////////

	public static GLibFont loadFont(InputStream in) {
		GLibFont createdFont = null;
		
		try {
			var baseFont = Font.createFont(Font.TRUETYPE_FONT, in);
			createdFont = getFont(baseFont, PLAIN, DEFAULT_SIZE);
		} catch (FontFormatException | IOException e) {
			GLibSystem.crashApplication("Could not load the font.", e);
		}
		
		return createdFont;
	}
	
	///////////////////////////////////

	public static GLibFont getFont(String name, int style, float size) {
		GLibChecks.assertNotNull(name, "font_name_null");
		return new GLibFont(name, style, size);
	}
	
	public static GLibFont getFont(java.awt.Font baseFont, int style, float size) {
		GLibChecks.assertNotNull(baseFont, "baseFont == null!");
		return new GLibFont(baseFont, style, size);
	}
	
	///////////////////////////////////

	public boolean isPlain() {
		return fontStyle == PLAIN;
	}

	public boolean isItalic() {
		return (fontStyle & ITALIC) == ITALIC;
	}

	public boolean isBold() {
		return (fontStyle & BOLD) == BOLD;
	}

	public boolean isUnderline() {
		return (fontStyle & UNDERLINE) == UNDERLINE;
	}

	public boolean isStrike() {
		return (fontStyle & STRIKEOUT) == STRIKEOUT;
	}

	///////////////////////////////////

	public String getFontName() {
		return fontName;
	}

	public float getFontSize() {
		return fontSize;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	///////////////////////////////////

	private Font createFontDerivation(Font base, int style, float size) {
		int javaFontStyle = 0;
		
		if (isItalic())
			javaFontStyle |= java.awt.Font.ITALIC;
		
		if (isBold())
			javaFontStyle |= java.awt.Font.BOLD;
		
		return base.deriveFont(javaFontStyle, size);
	}
	
	Font getBaseFont() {
		return baseFont;
	}
}
