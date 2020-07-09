package game.sprite;

import biuoop.DrawSurface;
import levels.GameLevel;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Background image.
 */
public class BackgroundImage implements Sprite {
    private String imageName;
    private int x, y;
    private Image img;

    /**
     * Instantiates a new Background image.
     *
     * @param imageName the image name
     * @param x         the x
     * @param y         the y
     */
    public BackgroundImage(String imageName, int x, int y) {
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imageName);
        try {
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("there is no such File" + this.imageName);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(this.x, this.y, this.img);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
