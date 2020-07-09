package animation;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * The type Game over screen.
 */
public class GameOverScreen implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new Game over screen.
     *
     * @param scoreGame the score game
     */
    public GameOverScreen(Counter scoreGame) {
        this.stop = false;
        this.score = scoreGame;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(86, 220, 177));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2, 400, 100);
        d.setColor(new Color(225, 25, 31));
        d.fillCircle(d.getWidth() / 2, 400, 85);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2, 450, 35);
        d.setColor(new Color(225, 25, 31));
        d.fillCircle(d.getWidth() / 2, 460, 35);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2 - 30, 375, 20);
        d.fillCircle(d.getWidth() / 2 + 30, 375, 20);
        for (int i = 0; i < 15; i++) {
            d.drawCircle(d.getWidth() / 2, 400, 85 + i);
        }

        for (int i = 0; i < 20; i++) {
            d.drawText(i / 2 + 50, 560,
                    "Y o u r  s c o r e  i s  " + this.score.getValue(), 60);
        }
        //G
        d.setColor(Color.BLACK);
        d.fillRectangle(25, 25, 25, 100);
        d.fillRectangle(25, 25, 100, 25);
        d.fillRectangle(25, 100, 100, 25);
        d.fillRectangle(100, 63, 25, 50);
        d.fillRectangle(75, 63, 25, 25);
        //A
        d.fillRectangle(150, 25, 100, 25);
        d.fillRectangle(150, 25, 25, 100);
        d.fillRectangle(225, 25, 25, 100);
        d.fillRectangle(150, 75, 100, 25);
        //M
        d.fillRectangle(275, 25, 25, 100);
        d.fillRectangle(275, 25, 100, 25);
        d.fillRectangle(375, 25, 25, 100);
        d.fillRectangle(325, 25, 25, 100);
        //E
        d.fillRectangle(425, 25, 25, 100);
        d.fillRectangle(425, 25, 100, 25);
        d.fillRectangle(425, 63, 100, 25);
        d.fillRectangle(425, 100, 100, 25);
        //O
        d.fillRectangle(275, 150, 25, 100);
        d.fillRectangle(350, 150, 25, 100);
        d.fillRectangle(275, 150, 100, 25);
        d.fillRectangle(275, 225, 100, 25);
        //V
        for (int i = 0; i < 30; i++) {
            d.drawLine(400 + i, 150, 437 + i, 250);
            d.drawLine(475 + i, 150, 437 + i, 250);
        }
        //E
        d.fillRectangle(525, 150, 25, 100);
        d.fillRectangle(525, 150, 100, 25);
        d.fillRectangle(525, 188, 100, 25);
        d.fillRectangle(525, 225, 100, 25);
        //R
        d.fillRectangle(650, 150, 25, 100);
        d.fillRectangle(650, 150, 100, 25);
        d.fillRectangle(725, 175, 25, 25);
        d.fillRectangle(675, 188, 75, 25);
        for (int i = 0; i < 30; i++) {
            d.drawLine(680 + i, 188 + 25, 720 + i, 250);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}