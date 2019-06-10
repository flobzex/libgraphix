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

import com.mikronia.glib.GLibBatch;

public class Animation {

	private AnimationFrame[] animationFrames;
	private String spritesheet;
	
	private long changeInterval;

	private int defaultFrame;
	private int currentFrame;
	private int totalFrames;
	
	// animation properties
	private long timer;
	
	public Animation(String spritesheet, AnimationFrame[] frames, int initialFrame, int defaultFrame, long changeInterval) {
		this.spritesheet = spritesheet;
		this.animationFrames = frames;
		this.currentFrame = initialFrame;
		this.changeInterval = changeInterval;
		this.totalFrames = frames.length;
		this.defaultFrame = defaultFrame;
		
		this.timer = System.currentTimeMillis();
	}

	/////////////////////////
	
	public void begin(GLibBatch batch) {
		// binds the animation spritesheet
		batch.bindSpritesheet(spritesheet);
		
		// binds the animation sprite
		int[] sprite = animationFrames[currentFrame].getSprite();
		
		batch.bindSprite(sprite[0], sprite[1]);
	}
	
	public void end(GLibBatch batch) {
		// unbinds previous elements
		batch.unbindSprite();
		batch.unbindSpritesheet();
	}
	
	/**
	 * Updates the animation.
	 */
	public void update() {
		long now = System.currentTimeMillis();
		
		if (now - timer > changeInterval) {
			// update timer
			timer = now;
			
			// determine next frame
			if (currentFrame + 1 >= totalFrames) {
				currentFrame = 0;
			} else currentFrame++;
		}
	}
	
	/**
	 * Sets the animation frame
	 * to the first frame.
	 */
	public void reset() {
		currentFrame = defaultFrame;
	}
	
	/////////////////////////
	
	public void setInterval(long newInterval) {
		this.changeInterval = newInterval;
	}
	
	/////////////////////////
	
	public AnimationFrame[] getFrames() {
		return animationFrames;
	}
	
	public String getSpritesheet() {
		return spritesheet;
	}
	
	public long changeInterval() {
		return changeInterval;
	}
	
	public int currentFrame() {
		return currentFrame;
	}
	
	public int totalFrames() {
		return totalFrames;
	}
}
