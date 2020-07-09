package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.HighScoresTable;
import game.sprite.BackgroundImage;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private KeyboardSensor keyboard;
    private boolean stop;
    private BackgroundImage background;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param sensor the sensor
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor sensor) {
        this.scoresTable = scores;
        this.keyboard = sensor;
        this.stop = false;
        this.background = new BackgroundImage("background_images/table.jpg", 0, 0);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
//        d.drawRectangle(50, 50, 700, 50 * (this.scoresTable.size() + 1));
//        d.drawLine(100, 50, 100, 50 + 50 * (this.scoresTable.size() + 1));
//        d.drawLine(450, 50, 400, 50 + 50 * (this.scoresTable.size() + 1));
//        d.drawLine(50, 100, 750, 100);
//        for (int i = 0, y = 100; i < this.scoresTable.size(); i++, y += 50) {
//            d.drawLine(50, y, 850, y);
//            d.drawText(75, y + 25, i + 1 + "", 20);
//        }
        d.drawText(100, 100, "Name", 70);
        d.drawText(380, 100, "Score", 70);

        for (int i = 0, y = 150; i < this.scoresTable.getHighScores().size(); i++, y += 50) {
            d.drawText(100, y + 25, this.scoresTable.getHighScores().get(i).getName(), 50);
            d.drawText(400, y + 25, this.scoresTable.getHighScores().get(i).getScore() + "", 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
