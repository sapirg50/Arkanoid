package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.sprite.BackgroundImage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private List<String> key;
    private List<String> message;
    private List<T> value;
    private List<Boolean> isSub;
    private List<Menu<T>> menus;
    private KeyboardSensor keyboardSensor;
    private T status;
    private AnimationRunner runner;
    private String title;
    private BackgroundImage background;

    /**
     * Instantiates a new Menu animation.
     *
     * @param sensor          the sensor
     * @param runnerAnimation the runner animation
     * @param name            the name
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner runnerAnimation, String name) {
        this.title = name;
        this.keyboardSensor = sensor;
        this.key = new ArrayList<>();
        this.message = new ArrayList<>();
        this.value = new ArrayList<>();
        this.isSub = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.stop = false;
        this.runner = runnerAnimation;
        this.background = new BackgroundImage("background_images/main.jpg", 0, 0);
    }

    //@SuppressWarnings("unchecked")
    @Override
    public void addSubMenu(String newKey, String newMessage, Menu<T> newSubMenu) {
        this.key.add(newKey);
        this.message.add(newMessage);
        this.menus.add(newSubMenu);
        this.value.add(null);
        this.isSub.add(true);
    }

    @Override
    public void addSelection(String newKey, String newMessage, T returnVal) {
        this.key.add(newKey);
        this.message.add(newMessage);
        this.value.add(returnVal);
        this.menus.add(null);
        this.isSub.add(false);
    }

    @Override
    public T getStatus() {
        return status;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        //draw the text:
        d.setColor(Color.WHITE);
        d.drawText(50, 100, this.title, 50);
        // d.setColor(Color.black);
        for (int i = 0; i < this.key.size(); i++) {
            d.drawText(50, 200 + i * 70, "(" + this.key.get(i) + ")  " + this.message.get(i), 50);
        }
        for (int i = 0; i < this.key.size(); i++) {
            if (this.keyboardSensor.isPressed(key.get(i))) {
                if ((menus.get(i) != null) && (this.isSub.get(i))) {
                    this.runner.run(this.menus.get(i));
                    this.status = menus.get(i).getStatus();
                } else if (value.get(i) != null) {
                    this.status = this.value.get(i);
                }
                this.stop = true;
                return;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void reset() {
        this.stop = false;
        this.status = null;
    }
}
