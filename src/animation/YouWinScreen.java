package animation;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * The type You win screen.
 */
public class YouWinScreen implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new You win screen.
     *
     * @param scoreGame the score game
     */
    public YouWinScreen(Counter scoreGame) {
        this.stop = false;
        this.score = scoreGame;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0xFF98A1F3, true));
        d.fillRectangle(0, 0, 900, 600);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2, 350, 100);
        d.setColor(new Color(225, 220, 67));
        d.fillCircle(d.getWidth() / 2, 350, 85);
        d.setColor(Color.BLACK);
        d.drawCircle(d.getWidth() / 2, 350, 85);
        //d.fillCircle(d.getWidth() / 2 - 30, 330, 20);
        //d.fillCircle(d.getWidth() / 2 + 30, 330, 20);
        d.fillCircle(d.getWidth() / 2, 370, 50);
        d.setColor(new Color(225, 220, 67));
        d.fillCircle(d.getWidth() / 2, 350, 50);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2 - 30, 325, 20);
        d.fillCircle(d.getWidth() / 2 + 30, 325, 20);

        for (int i = 0; i < 30; i++) {
            d.drawText(120 + (i / 2), d.getHeight() / 3, "Y o u   W i n !", 100);
        }
        for (int i = 0; i < 18; i++) {
            d.drawText(i / 2 + 50, 530,
                    "Y o u r  s c o r e  i s  " + this.score.getValue(), 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}