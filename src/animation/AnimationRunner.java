package animation;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    private GUI gui;
    private int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui1 the gui 1
     */
    public AnimationRunner(GUI gui1) {
        this.gui = gui1;
        this.framesPerSecond = 60;
    }

    /**
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        /*
        prepare a smooth animations that displays 60 different frames in a second.
        This means that each frame in the animation can last 1000 / framesPerSecond milliseconds
        */
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        Sleeper sleeper = new biuoop.Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * close.
     * <p>
     * close the gui
     */
    public void close() {
        this.gui.close();
    }
}
