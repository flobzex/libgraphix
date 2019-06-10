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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.mikronia.glib.utils.glib.GLibChecks;

/**
 * GLib graphics batch
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibBatch {
	
	private final Graphics2D graphics;
	
	private final int screenWidth;
	private final int screenHeight;
	
	private BufferedImage sprite;
	private String spritesheet;
	
	private Color clearColor;
	
	/**
	 * Creates a new {@code MkBatch}.
	 * 
	 * @param g screen graphics
	 * @param width screen width
	 * @param height screen height
	 */
	GLibBatch(Graphics2D g, int width, int height) {
		this.graphics = g;
		this.screenWidth = width;
		this.screenHeight = height;
		this.clearColor = Color.black;
		this.sprite = null;
	}

	// TEXTURE PROPERTIES //
	
	public void bindSpritesheet(String spritesheet) {
		GLibChecks.assertNotNull(spritesheet, "Spritesheet name cannot be null.");
		
		this.spritesheet = spritesheet;
	}
	
	public void bindSprite(int x, int y) {
		if (this.spritesheet == null)
			return;
		
		this.bindSprite(spritesheet, x, y);
	}
	
	public void bindSprite(int x, int y, int w, int h) {
		if (this.spritesheet == null)
			return;
		
		this.bindSprite(spritesheet, x, y, w, h);
	}
	
	public void bindSprite(String spritesheet, int x, int y) {
		this.sprite = GLibSpritesheets.getSpriteCell(spritesheet, x, y);
	}
	
	public void bindSprite(String spritesheet, int x, int y, int w, int h) {
		this.sprite = GLibSpritesheets.getSpritesheetArea(spritesheet, x, y, w, h);
	}
	
	public void unbindSprite() {
		this.sprite = null;
	}
	
	public void unbindSpritesheet() {
		this.spritesheet = null;
	}
	
	// BASIC SHAPES (LINES)
	
	public void drawLine(float x1, float y1, float x2, float y2) {
		graphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}
	
	public void drawRect(float x, float y, float w, float h) {
		graphics.drawRect((int) x, (int) y, (int) w, (int) h);
	}
	
	public void drawOval(float x, float y, float w, float h) {
		graphics.drawOval((int) x, (int) y, (int) w, (int) h);
	}
	
	public void drawRoundRect(float x, float y, float w, float h, float arcWidth, float arcHeight) {
		graphics.drawRoundRect((int) x, (int) y, (int) w, (int) h, (int) arcWidth, (int) arcHeight);
	}
	
	// BASIC SHAPES (FILLED)
	
	public void fillRect(float x, float y, float w, float h) {
		graphics.fillRect((int) x, (int) y, (int) w, (int) h);
	}
	
	public void fillOval(float x, float y, float w, float h) {
		graphics.fillOval((int) x, (int) y, (int) w, (int) h);
	}
	
	public void fillRoundRect(float x, float y, float w, float h, float arcWidth, float arcHeight) {
		graphics.fillRoundRect((int) x, (int) y, (int) w, (int) h, (int) arcWidth, (int) arcHeight);
	}
	
	// MODELS //
	
	public void drawModel(String modelName) {
		var model = GLibModels.model(modelName);
		
		graphics.drawPolygon(model.getBasePolygon());
	}
	
	public void fillModel(String modelName) {
		var model = GLibModels.model(modelName);
		
		graphics.fillPolygon(model.getBasePolygon());
	}
	
	// TEXTURE //
	
	public void drawSprite(float x, float y) {
		if (sprite == null) return;
		
		graphics.drawImage(sprite, (int) x, (int) y, null);
	}
	
	public void drawSprite(float x, float y, float w, float h) {
		if (sprite == null) return;
		
		graphics.drawImage(sprite, (int) x, (int) y, (int) w, (int) h, null);
	}
	
	// TEXT //
	
	public void drawText(String text, float x, float y, GLibFont font) {
		if (text == null || text.trim().isEmpty()) return;
		
		graphics.setFont(font.getBaseFont());

		var fontMetrics = graphics.getFontMetrics();
		var textSize = fontMetrics.getStringBounds(text, graphics);

		float textWidth = (float) textSize.getWidth();
		float textHeight = (float) textSize.getHeight();
		float textXOffset = (float) Math.abs(textSize.getX());
		float textYOffset = (float) Math.abs(textSize.getY());
		
		graphics.drawString(text, x + textXOffset, y + textYOffset);

		if (font.isStrike())
			drawLine(x, y + textYOffset * 0.75f, x + textWidth, y + textYOffset * 0.75f);

		if (font.isUnderline())
			drawLine(x, y + textHeight, x + textWidth, y + textHeight);
	}
	
	// COLORS //
	
	public void color(float r, float g, float b, float a) {
		float newRed = constraintsNumber(r, 0.0f, 1.0f);
		float newGreen = constraintsNumber(g, 0.0f, 1.0f);
		float newBlue = constraintsNumber(b, 0.0f, 1.0f);
		float newAlpha = constraintsNumber(a, 0.0f, 1.0f);
		
		graphics.setColor(new Color(newRed, newGreen, newBlue, newAlpha));
	}

	public void color(float r, float g, float b) {
		color(r, g, b, 1.0f);
	}
	
	public void color(int r, int g, int b, int a) {
		float newRed = constraintsNumber(r, 0, 255);
		float newGreen = constraintsNumber(g, 0, 255);
		float newBlue = constraintsNumber(b, 0, 255);
		float newAlpha = constraintsNumber(a, 0, 255);
		
		graphics.setColor(new Color(newRed, newGreen, newBlue, newAlpha));
	}
	
	public void color(int r, int g, int b) {
		color(r, g, b, 255);
	}
	
	public void color(int rgba) {
		graphics.setColor(new Color(rgba, true));
	}
	
	// CLEAR COLOR //
	
	public void clearColor(float r, float g, float b, float a) {
		float newRed = constraintsNumber(r, 0.0f, 1.0f);
		float newGreen = constraintsNumber(g, 0.0f, 1.0f);
		float newBlue = constraintsNumber(b, 0.0f, 1.0f);
		float newAlpha = constraintsNumber(a, 0.0f, 1.0f);
		
		clearColor = new Color(newRed, newGreen, newBlue, newAlpha);
	}
	
	// GRAPHICS //
	
	public void translate(float x, float y) {
		graphics.translate(x, y);
	}
	
	public void scale(float x, float y) {
		graphics.scale(x, y);
	}
	
	public void rotate(float angle, float x, float y) {
		graphics.rotate(angle, x, y);
	}
	
	public void stroke(float intensity) {
		graphics.setStroke(new BasicStroke(intensity));
	}
	
	public void clearScreen() {
		graphics.setColor(clearColor);
		graphics.fillRect(0, 0, screenWidth, screenHeight);
	}
	
	/////////////////////////
	
	private static float constraintsNumber(float val, float min, float max) {
		return val < min ? min : val > max ? max : val;
	}
}
