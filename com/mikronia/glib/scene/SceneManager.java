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

package com.mikronia.glib.scene;

/**
 * Scene manager
 * 
 * @since GLib 1.0.0
 * @version 1.0
 * 
 * @author Thaynan M. Silva
 */
public final class SceneManager {
	
	// instance of the scene manager
	private static final SceneManager defaultSceneManager = new SceneManager();

	// stored scenes
	private Scene previousScene;
	private Scene currentScene;
	
	// single instance only!
	private SceneManager() { }
	
	/////////////////////////
	
	/**
	 * Sets a new screen as current.
	 * @param newScene the new scene
	 */
	public void setScene(Scene newScene) {
		if (newScene == null) {
			// erases the current scene in
			// case of a null new scene
			this.previousScene = currentScene;
			this.currentScene = null;
			
			// fires the event of the scene
			this.previousScene.onEnd();
			
			// break function
			return;
		}
		
		// update the scenes
		this.previousScene = currentScene;
		this.currentScene = newScene;
		
		// fires the events of the scenes
		this.previousScene.onEnd();
		this.currentScene.onInit();
	}
	
	/////////////////////////
	
	/**
	 * Gets the current scene.
	 * @return the current scene.
	 */
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	/**
	 * Gets the current scene.
	 * @return the current scene.
	 */
	public Scene getPreviousScene() {
		return previousScene;
	}
	
	/////////////////////////
	
	/**
	 * Restores the previous scene
	 * (if there's a previous scene).
	 */
	public void restorePreviousScene() {
		if (hasPreviousScene()) {
			setScene(previousScene);
		}
	}

	/**
	 * Tests whether the scene manager
	 * has a previous scene.
	 * 
	 * @return {@code true} if there's
	 * a previous scene, otherwise
	 * returns {@code false}.
	 */
	public boolean hasPreviousScene() {
		return previousScene != null;
	}
	
	/////////////////////////
	
	/**
	 * Gets the default scene manager instance.
	 * @return the default scene manager.
	 */
	public static SceneManager getDefaultSceneManager() {
		return defaultSceneManager;
	}
}
