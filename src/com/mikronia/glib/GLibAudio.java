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

import java.io.InputStream;
import java.util.ArrayList;

import com.mikronia.glib.audio.JOrbisClip;
import com.mikronia.glib.utils.glib.GLibChecks;


/**
 * GLib audio manager
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibAudio {
	
	private static ArrayList<GLibAudio> loadedPlayers = new ArrayList<GLibAudio>();
	
	// current audio clip
	private JOrbisClip clip;
	
	// determines whether audio is being played
	private boolean playing = false;
	private boolean looping = false;
	
	// current gain
	private float balance = 0.0f;
	private float gain = -1.0f;
	
	// local instancing only!
	private GLibAudio() { }

	/**
	 * Initializes the audio system.
	 */
	public static void initialize() {
		// yet, it does nothing
	}
	
	/////////////////////////

	/**
	 * 
	 * @param in audio input stream
	 * @return a new {@code GLibAudio} object.
	 */
	public static GLibAudio loadAudio(InputStream in) {
		GLibAudio audio = new GLibAudio();
		
		// attempts to load the audio file
		GLibChecks.perform(() -> {
			audio.clip = new JOrbisClip(in);
		}, "loadAudio", GLibAudio.class);
		
		return audio;
	}
	
	/////////////////////////

	/**
	 * Starts playing the audio.
	 */
	public void play() {
		if (!playing) {
			clip.play();
		}
	}
	
	/**
	 * Pauses the audio.
	 */
	public void pause() {
		clip.pause();
	}
	
	/**
	 * Starts playing the audio from
	 * where it stopped.
	 */
	public void resume() {
		if (clip.isPaused()) {
			clip.resume();
		}
	}
	
	/**
	 * Enables looping for this audio.
	 */
	public void loop() {
		clip.loop();
		
		// set as looping
		this.looping = true;
	}
	
	/**
	 * 
	 */
	public void reset() {
		if (!playing) {
			clip.stop();
			clip.play();
		}
	}
	
	/**
	 * 
	 */
	public void stop() {
		if (!clip.stopped()) {
			clip.stop();
		}
		
		// set as not playing
		playing = false;
		looping = false;
	}
	
	/**
	 * Frees the system resources used
	 * by this audio.
	 */
	public void flush() {
		stop();
		
		clip.close();
	}

	/////////////////////////
	
	/**
	 * Restores the default balance level.
	 */
	public void resetBalance() {
		clip.setBalance(0.0f);
	}
	
	/**
	 * Restores the default gain level.
	 */
	public void resetGain() {
		clip.setGain(-1.0f);
	}
	
	/////////////////////////
	
	/**
	 * Attempts to set the balance between
	 * the two speakers. -1.0 is full left
	 * speak, 1.0 if full right speaker. 
	 * 
	 * @param balance new balance level
	 */
	public void setBalance(float balance) {
		clip.setBalance(balance);
		
		// store new balance level
		this.balance = balance;
	}
	
	/**
	 * Attempts to set the gain (volume)
	 * for this audio.
	 * 
	 * @param gain new gain level
	 */
	public void setGain(float gain) {
		clip.setGain(gain);
		
		// store new gain level
		this.gain = gain;
	}
	
	/////////////////////////
	
	/**
	 * @returns {@code true} if the audio is
	 * playing, otherwise returns {@code false}.
	 */
	public boolean isPlaying() {
		return playing;
	}
	
	/**
	 * @return {@code true} if the audio is
	 * paused, otherwise returns {@code false}.
	 */
	public boolean isPaused() {
		return clip.isPaused();
	}
	
	/**
	 * @return {@code true} if the audio was
	 * stopped, otherwise returns {@code false}.
	 */
	public boolean isStopped() {
		return clip.stopped();
	}
	
	/**
	 * @return {@code true} if looping is
	 * enabled for this audio, otherwise
	 * returns {@code false}.
	 */
	public boolean isLooping() {
		return looping;
	}
	
	/////////////////////////
	
	/**
	 * @return the balance of this audio.
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * @return the gain of this audio.
	 */
	public float getGain() {
		return gain;
	}
	
	/////////////////////////
	
	/**
	 * Terminates audio system.
	 */
	public static void terminate() {
		// destroys all loaded resources
		for (var audio : loadedPlayers) {
			audio.stop();
			audio.flush();
		}
	}
}
