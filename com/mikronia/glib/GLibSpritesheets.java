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

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Spritesheet manager
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibSpritesheets {
	
	// spritesheet list
	private static HashMap<String, GLibSpritesheet> spritesheets = new HashMap<String, GLibSpritesheet>();
	
	// sprite list
	private static HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	/////////////////////////
	
	// static use only!
	private GLibSpritesheets() { }
	
	/**
	 * Loads a spritesheet.
	 * 
	 * @param spritesheetName a reference to the spritesheet (e.g. 'player_attack')
	 * @param spritesheet the spritesheet image
	 * @param cellWidth width of the cell in the spritesheet
	 * @param cellHeight height of the cell in the spritesheet
	 */
	public static void loadSpritesheet(String spritesheetName, BufferedImage spritesheet, int cellWidth, int cellHeight) {
		validateSpritesheetData(spritesheetName, spritesheet, cellWidth, cellHeight);
		
		// stores the spritesheet
		spritesheets.put(spritesheetName, new GLibSpritesheet(spritesheet, cellWidth, cellHeight));
	}

	/**
	 * Deletes all the spritesheets.
	 */
	public static void deleteSpritesheets() {
		spritesheets.clear();
		sprites.clear();
	}
	
	/////////////////////////
	
	/**
	 * Gets a spritesheet.
	 */
	static GLibSpritesheet getSpritesheet(String spritesheetName) {
		validateSpritesheetExistence(spritesheetName);
		return spritesheets.get(spritesheetName);
	}
	
	/**
	 * Gets an area from the spritesheet.
	 */
	static BufferedImage getSpritesheetArea(String spritesheetName, int spriteX, int spriteY, int spriteW, int spriteH) {
		var spritesheet = getSpritesheet(spritesheetName);
		
		validBounds(spriteX, spriteY, spriteW, spriteH, spritesheet);

		var reference = createSpriteName(spritesheetName, spriteX, spriteY, spriteW, spriteH);
		
		if (!sprites.containsKey(reference)) {
			// gets the sprite from the spritesheet
			var sprite = spritesheet.get(spriteX, spriteY, spriteW, spriteH);
			
			// stores the created sprite
			sprites.put(spritesheetName, sprite);
		}
		
		return sprites.get(reference);
	}
	
	static BufferedImage getSpriteCell(String spritesheetName, int cellX, int cellY) {
		var spritesheet = getSpritesheet(spritesheetName);
		
		int spriteW = spritesheet.getCellWidth();
		int spriteH = spritesheet.getCellHeight();
		int coordX = cellX * spriteW;
		int coordY = cellY * spriteH;
		
		if(!validBounds(coordX, coordY, spriteW, spriteH, spritesheet))
			return null;
		
		var reference = createSpriteName(spritesheetName, coordX, coordY, spriteW, spriteH);

		if (!sprites.containsKey(reference)) {
			// gets the sprite from the spritesheet
			var sprite = spritesheet.get(coordX, coordY, spriteW, spriteH);

			// stores the created sprite
			sprites.put(reference, sprite);
		}
		
		return sprites.get(reference);
	}
	
	/////////////////////////
	
	private static String createSpriteName(String spritesheetName, int x, int y, int w, int h) {
		return new StringBuilder(spritesheetName).append(x).append(y).append(w).append(h).toString();
	}
	
	private static void validateSpritesheetData(String spritesheetName, BufferedImage spritesheet, int cellWidth, int cellHeight) {
		if (spritesheetName == null)
			throw new NullPointerException("The spritesheet name must not be null.");
		if (spritesheetName.trim().isEmpty())
			throw new NullPointerException("The spritesheet name must not be empty.");
		if (spritesheets.containsKey(spritesheetName))
			throw new IllegalArgumentException("The spritesheet already exists: " + spritesheetName);
		if (spritesheet == null)
			throw new NullPointerException("The spritesheet must not be null.");
	}
	
	private static void validateSpritesheetExistence(String spritesheetName) {
		if (spritesheetName == null)
			throw new NullPointerException("The spritesheet name must not be null.");
		if (spritesheetName.trim().isEmpty())
			throw new NullPointerException("The spritesheet name must not be empty.");
		if (!spritesheets.containsKey(spritesheetName))
			throw new IllegalArgumentException("Inexistent spritesheet: " + spritesheetName);
	}

	private static boolean validBounds(int spriteX, int spriteY,
			int spriteW, int spriteH, GLibSpritesheet spritesheet) {
		int spritesheetW = spritesheet.getSheet().getWidth();
		int spritesheetH = spritesheet.getSheet().getHeight();
		return !(spriteX < 0 || (spriteX + spriteW) > spritesheetW ||
				spriteY < 0 || (spriteY + spriteH) > spritesheetH);
	}
}

