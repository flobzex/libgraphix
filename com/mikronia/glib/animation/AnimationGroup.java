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

/**
 * Animation group
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public final class AnimationGroup {

	// animation frames
	private Animation[] animations;
	
	private int currentAnimation;
	
	/**
	 * Creates a new animation set
	 * 
	 * @param initialAnimation the initial animation index
	 * @param animations the animations
	 */
	public AnimationGroup(int initialAnimation, Animation... animations) {
		this.currentAnimation = initialAnimation;
		this.animations = animations;
	}
	
	/////////////////////////
	
	public void begin(GLibBatch batch) {
		getCurrentAnimation().begin(batch);
	}
	
	public void end(GLibBatch batch) {
		getCurrentAnimation().end(batch);
	}
	
	public void update() {
		getCurrentAnimation().update();
	}
	
	public void reset() {
		getCurrentAnimation().reset();
	}

	/////////////////////////
	
	public void setInterval(long newInterval) {
		getCurrentAnimation().setInterval(newInterval);
	}
	
	/////////////////////////
	
	public void setCurrentAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	/////////////////////////
	
	public Animation getCurrentAnimation() {
		return animations[currentAnimation];
	}
	
	public Animation[] getAnimations() {
		return animations;
	}
}
