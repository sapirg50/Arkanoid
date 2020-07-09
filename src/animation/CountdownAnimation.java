package animation;

import biuoop.DrawSurface;
import game.Counter;
import game.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 * <p>
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double millSeconds;
    private Integer countFrom;
    private Counter displayingNumber;
    private int numOfRounds;
    private SpriteCollection gameScreen;
    private long beginTime;
    private double timeUnit;
    private boolean running;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.millSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.displayingNumber = new Counter(countFrom);
        this.numOfRounds = 1;
        this.gameScreen = gameScreen;
        this.running = true;
        this.timeUnit = this.millSeconds / (this.countFrom + 1);
        this.beginTime = System.currentTimeMillis();
    }

    /**
     * Sets num of rounds.
     *
     * @param numOfRounds1 the num of rounds
     */
    public void setNumOfRounds(int numOfRounds1) {
        this.numOfRounds = numOfRounds1;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (((System.currentTimeMillis() - this.beginTime) >= this.timeUnit * this.numOfRounds)) {
            this.displayingNumber.decrease(1);
            this.setNumOfRounds(this.numOfRounds + 1);
        }
        this.gameScreen.drawAllOn(d);
        if (this.countFrom >= this.numOfRounds) {
            d.setColor(Color.WHITE);
            d.fillCircle(d.getWidth() / 2 + 10, d.getHeight() / 2 - 15, 60);
            d.setColor(Color.BLACK);
            d.drawCircle(d.getWidth() / 2 + 10, d.getHeight() / 2 - 15, 60);
            d.drawText(d.getWidth() / 2 - 10, d.getHeight() / 2 + 10, this.displayingNumber.getValue().toString(), 80);
        }
        if (this.numOfRounds == this.countFrom + 1) {
            d.setColor(Color.WHITE);
            d.fillCircle(d.getWidth() / 2 + 10, d.getHeight() / 2 - 15, 60);
            d.setColor(Color.BLACK);
            d.drawCircle(d.getWidth() / 2 + 10, d.getHeight() / 2 - 15, 60);
            d.drawText(d.getWidth() / 2 - 36, d.getHeight() / 2 + 5, "GO", 60);
        }
        if (this.numOfRounds > this.countFrom + 1) {
            this.running = false;

        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}