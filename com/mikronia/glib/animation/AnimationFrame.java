/*
 * MIT License
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

package com.mikronia.glib.animation;

import com.mikronia.glib.utils.glib.GLibChecks;
import com.mikronia.glib.vector.Vector2i;

/**
 * Animation frame
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public final class AnimationFrame {
	
	// animation sprite coordinates
	private final int[] sprite;

	/**
	 * Creates a new instance of this sprite.
	 * 
	 * @param spriteX sprite X-axis coordinate
	 * @param spriteY sprite Y-axis coordinate
	 */
	public AnimationFrame(int spriteX, int spriteY) {
		this.sprite = new int[] { spriteX, spriteY };
	}

	/**
	 * Gets the animation frame sprite.
	 * @return the frame sprite.
	 */
	public int[] getSprite() {
		return sprite;
	}

	/////////////////////////

	/**
	 * Creates an array of animation frames
	 * with the given objects.
	 * 
	 * @param frames vectors to be transformed to frames.
	 * @return an array of animation frames containing the
	 * currently created animation frames.
	 */
	public static AnimationFrame[] of(Vector2i... frames) {
		GLibChecks.assertNotNull(frames, "Parameter 'frame' is null!");
		
		AnimationFrame[] animationFrames = new AnimationFrame[frames.length];
		
		for (int i = 0; i < frames.length; i++) {
			GLibChecks.assertNotNull(frames[i],
					String.format("Vector at position %d is null!", i));
			
			animationFrames[i] = new AnimationFrame(frames[i].x(), frames[i].y());
		}
		
		return animationFrames;
	}
}