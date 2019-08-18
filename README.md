# Graphix

A small game library that allows you to make 2D games easily in Java

# Features

GLib does not provide many resources for game development yet, but
some of the resources you can find are:

**&#42; To be added**

* Spritesheet Manager
* Animation Managers
* Support for Joysticks **&#42;**
* Vector Utilities
* Math Utilities
* IO/Net Utilities **&#42;**

# Implementing GLib

The implementation of GLib is a very simple thing. Since GLib works
with an *Application System*, that is a way to provide functions for 
rendering and updating the game components, you'll have to create
one just as below:

```java
public class MyGame extends GLibApp {
  
  private GLibFont gameFont;
  
  @Override
  public void init() {
    // initialize game components
    this.gameFont = GLibFont.MONOSPACED.deriveFont(10.0f);
  }
  
  @Override
  public void render(GLibBatch batch) {
    // draw game components on screen
    batch.clearColor(0.0f, 0.0f, 0.0f, 0.0f);
    batch.clearScreen();
    
    batch.color(0xffff0000);
    batch.drawText("Hello, World!", 100, 100, gameFont);
  }
  
  @Override
  public void update() {
    // update game components
  }
  
  @Override
  public void end() {
    // unload game resources
  }
}
```

After implementing the structure above, you need to call GLib to
initialize your application, and it is done as it follows:

```java
public class MainClass {
  
  public static void main(String[] args) {
    var myGame = new MyGame();
    int width = 640;
    int height = 480;
    int pixelSize = 2;
    int fpsRate = 60;
    boolean infiniteFPS = false;
    boolean fullScreen = false;
    
    // initializes GLib and shows the window
    GLib.initialize(myGame, width, height, pixelSize,
                    fpsRate, infiniteFPS, fullScreen);
  }
}
```

GLib was compiled in Java 10.0.2 (*Java 10*) and therefore, uses
the new **Java Module System** implemented in *Java 9*. You may learn
more about the module system by clicking [here](https://www.oracle.com/corporate/features/understanding-java-9-modules.html).
