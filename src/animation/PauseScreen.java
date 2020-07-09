package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    private long timeUnit;
    private long beginTime;
    private int numOfRounds;
    private int handX, handY;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
        this.timeUnit = 100;
        this.numOfRounds = 1;
        this.beginTime = System.currentTimeMillis();
        this.handX = 400;
        this.handY = 270;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(60, 80, 167));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        //P
        d.fillRectangle(100, 100, 100, 25);
        d.fillRectangle(100, 125, 25, 100);
        d.fillRectangle(100, 150, 100, 25);
        d.fillRectangle(175, 100, 25, 50);
        //A
        d.fillRectangle(225, 100, 100, 25);
        d.fillRectangle(225, 125, 25, 100);
        d.fillRectangle(300, 125, 25, 100);
        d.fillRectangle(225, 150, 100, 25);
        //U
        d.fillRectangle(425, 100, 25, 100);
        d.fillRectangle(350, 100, 25, 100);
        d.fillRectangle(350, 200, 100, 25);
        //S
        d.fillRectangle(475, 100, 100, 25);
        d.fillRectangle(475, 200, 100, 25);
        d.fillRectangle(475, 100, 25, 50);
        d.fillRectangle(475, 150, 100, 25);
        d.fillRectangle(550, 150, 25, 50);
        //E
        d.fillRectangle(600, 100, 25, 100);
        d.fillRectangle(600, 100, 100, 25);
        d.fillRectangle(600, 150, 100, 25);
        d.fillRectangle(600, 200, 100, 25);
        //CLOCK:
        d.setColor(new Color(137, 135, 134));
        d.fillCircle(d.getWidth() / 2, 350, 100);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(d.getWidth() / 2, 350, 85);
        d.setColor(Color.BLACK);
        d.drawCircle(d.getWidth() / 2, 350, 100);
        d.drawCircle(d.getWidth() / 2, 350, 85);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 15, 295, "12", 30);
        d.drawText(d.getWidth() / 2 - 80, 360, "3", 30);
        d.drawText(d.getWidth() / 2 - 5, 430, "6", 30);
        d.drawText(d.getWidth() / 2 + 65, 360, "9", 30);
        if (((System.currentTimeMillis() - this.beginTime) >= this.timeUnit * this.numOfRounds)) {
            this.numOfRounds++;
            moveHand();
        }
        d.drawLine(d.getWidth() / 2, 350, this.handX, this.handY);
        for (int i = 0; i < 5; i++) {
            d.drawLine(d.getWidth() / 2 + i, 350, d.getWidth() / 2 + 10 + i, 300);
            d.drawLine(d.getWidth() / 2 + i, 350, d.getWidth() / 2 + 50 + i, 400);
        }
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 180, d.getHeight() / 2 + 200, "press space to continue", 32);
    }

    /**
     * Move hand.
     *
     * Move the hand of the clock.
     */
    public void moveHand() {
        if ((this.handX >= 400 && this.handX < 480) && (this.handY >= 270 && this.handY < 350)) {
            this.handX += 1;
            this.handY += 1;
        } else if ((this.handX >= 400 && this.handX <= 480) && (this.handY >= 350 && this.handY < 530)) {
            this.handX -= 1;
            this.handY += 1;
        } else if ((this.handX > 320 && this.handX < 400) && (this.handY > 350 && this.handY < 530)) {
            this.handX -= 1;
            this.handY -= 1;
        } else { //(this.handX >= 370 && this.handX < 450) && (this.handY >= 270 && this.handY < 350)
            this.handX += 1;
            this.handY -= 1;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}