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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * MkGame Window
 * 
 * @author Thaynan M. Silva
 * @version 1.0
 */
public final class GLibWindow {

	// graphics devices
	private static final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static final GraphicsDevice defaultDevice = environment.getDefaultScreenDevice();
	
	// window listeners
	private ArrayList<GLibWindowListener> listeners = new ArrayList<GLibWindowListener>();
	
	// underlying window
	private final JFrame window;
	
	// the canvas
	private final GLibCanvas canvas;

	// fullscreen
	private boolean fullscreen;

	/**
	 * Instantiates this object.
	 */
	GLibWindow(GLibCanvas canvas) {
		Objects.requireNonNull(canvas, "canvas == null!");
		
		this.canvas = canvas;
		this.fullscreen = false;

		// configure window
		window = new JFrame("Mk");
		window.setMinimumSize(new Dimension(200, 200));
		window.setContentPane(new JPanel(new BorderLayout()));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		// adds the canvas
		window.getContentPane().add(canvas, BorderLayout.CENTER);
		window.getContentPane().setBackground(Color.BLACK);
		
		// centralize window
		centralize();
		
		// configure window state listener
		window.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_OPENED);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_ICONIFIED);
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_DEICONIFIED);
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_CLOSING);
				
				// terminate GLib
				GLib.terminate();
			}
			
			@Override
			public void windowClosed(WindowEvent e) { }
			
			@Override
			public void windowActivated(WindowEvent e) { }
			
			@Override
			public void windowDeactivated(WindowEvent e) { }
		});

		// configure element listener
		window.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_RESIZED);
			}
			
			@Override
			public void componentMoved(java.awt.event.ComponentEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_MOVED);
			}
			
			@Override
			public void componentShown(java.awt.event.ComponentEvent e) { }
			
			@Override
			public void componentHidden(java.awt.event.ComponentEvent e) { }
		});
		
		// configure focus listener
		window.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_FOCUS_GAINED);
			}
			
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				fireEvent(GLibWindowEvent.WINDOW_FOCUS_LOST);
			}
		});
	}
	
	// ACTIONS //
	
	/**
	 * Shows the window.
	 */
	public void show() {
		window.setVisible(true);
	}
	
	/**
	 * Hides the window.
	 */
	public void hide() {
		window.setVisible(false);
	}
	
	/**
	 * Disposes the window.
	 */
	public void dispose() {
		window.dispose();
	}
	
	/**
	 * Moves the window to the
	 * center of the screen.
	 */
	public void centralize() {
		setRelativeTo(null);
	}
	
	/**
	 * Packs the window.
	 */
	public void pack() {
		window.pack();
	}
	
	/**
	 * Adds a listener to this window.
	 * 
	 * @param listener the listener to be added
	 */
	public void addListener(GLibWindowListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes a listener from this window.
	 * 
	 * @param listener the listener to be removed
	 */
	public void removelistener(GLibWindowListener listener) {
		listeners.remove(listener);
	}
	
	// SETTERS //

	/**
	 * Sets the window title.
	 * 
	 * @param t new title
	 */
	public void setTitle(String t) {
		window.setTitle(t);
	}

	/**
	 * Sets the icon of this window.
	 * 
	 * @param icon the icon (64x64
	 * image recommended)
	 */
	public void setIcon(Image icon) {
		window.setIconImage(icon);
	}
	
	/**
	 * Sets the icon of this window.
	 * 
	 * @param icon the icon (64x64
	 * image recommended)
	 */
	public void setIcons(Image icon) {
		window.setIconImage(icon);
	}
	
	/**
	 * Sets the icon of this window.
	 * 
	 * @param icons the image icons
	 */
	public void setIcons(List<Image> icons) {
		window.setIconImages(icons);
	}
	
	/**
	 * Sets the window size.
	 * 
	 * @param w new width
	 * @param h new height
	 */
	public void setSize(int w, int h) {
		window.setSize(new Dimension(w, h));
		window.revalidate();
	}
	
	/**
	 * Sets the window position on the screen.
	 * 
	 * @param x new X position
	 * @param y new Y position
	 */
	public void setPosition(int x, int y) {
		window.setLocation(x, y);
	}
	
	/**
	 * Sets the window position relative to
	 * the given component.
	 * 
	 * @param base source component
	 */
	public void setRelativeTo(Component base) {
		window.setLocationRelativeTo(base);
	}
	
	/**
	 * Changes the window state to fullscreen or windowed mode.
	 * 
	 * @param fullscreen window fullscreen state
	 */
	public synchronized void setFullscreen(boolean fullscreen) {
		if (this.fullscreen == fullscreen)
			return;
		
		if (fullscreen) {
			defaultDevice.setFullScreenWindow(window);
			this.fullscreen = true;
		} else {
			defaultDevice.setFullScreenWindow(null);
			this.fullscreen = false;
		}
	}
	
	// GETTERS //
	
	/**
	 * Gets the canvas of this window.
	 */
	public GLibCanvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Gets the title of this window.
	 */
	public String getTitle() {
		return window.getTitle();
	}

	/**
	 * Gets the icon of this window.
	 */
	public Image getIcon() {
		return window.getIconImage();
	}
	
	/**
	 * Gets the icons of this window.
	 */
	public List<Image> getIcons() {
		return window.getIconImages();
	}
	
	/**
	 * Gets the size of this window.
	 */
	public Dimension getSize() {
		return window.getSize();
	}
	
	/**
	 * Gets the position of this window
	 * on the screen.
	 */
	public Point getPosition() {
		return window.getLocation();
	}
	
	/**
	 * Gets the window fullscreen state.
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	/////////////////////////

	private void fireEvent(int event) {
		var e = new GLibWindowEvent(this, event);
		listeners.forEach((l) -> l.eventPerformed(e));
	}
}
