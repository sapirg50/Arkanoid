package levels;

import animation.AnimationRunner;
import animation.Animation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.SpriteCollection;
import game.GameEnvironment;
import game.ScoreTrackingListener;
import game.BallRemover;
import game.BlockRemover;
import game.Collidable;
import game.sprite.Ball;
import game.sprite.Block;
import game.sprite.Border;
import game.sprite.LevelNameIndicator;
import game.sprite.LivesIndicator;
import game.sprite.Paddle;
import game.sprite.ScoreIndicator;
import game.sprite.Sprite;
import geomtry.Point;
import geomtry.Rectangle;

import java.util.Map;
import java.awt.Color;
import java.util.HashMap;

import game.Counter;


/**
 * The type levels.GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter scoreGame;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformationType levelInformation;
    //Listeners:
    private BlockRemover blockListener;
    private BallRemover ballListener;
    private ScoreTrackingListener scoreListener;
    private KeyboardSensor keyboard;

    /**
     * Instantiates a new levels.GameLevel.
     *
     * @param keyboard         the keyboard
     * @param ar               the ar
     * @param numOfLives       the num of lives
     * @param score            the score
     * @param levelInformation the level information
     */
    public GameLevel(KeyboardSensor keyboard, AnimationRunner ar, Counter numOfLives,
                     Counter score, LevelInformationType levelInformation) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockListener = new BlockRemover(this, new Counter(this.levelInformation.numberOfBlocksToRemove()));
        this.ballListener = new BallRemover(this, new Counter(this.levelInformation.numberOfBalls()));
        this.scoreListener = new ScoreTrackingListener(score);
        this.scoreGame = scoreListener.getCurrentScore();
        this.lives = numOfLives;
        this.running = true;
        this.runner = ar;
        this.keyboard = keyboard;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * Gets paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     * Initialize a new game: create the Blocks and game.sprite.Ball (and game.sprite.Paddle) and add them to the game.
     */
    public void initialize() {
        Sprite background;
        if (this.levelInformation.getBackgroundType().equals("i")) {
            background = this.levelInformation.getBgImage();
        } else {
            background = this.levelInformation.getBgColor();
        }
        background.addToGame(this);
        Map<Integer, Color> colors = new HashMap<>();
        Sprite upperSide = new Border(new Rectangle(0, 25, 800, 25), Color.LIGHT_GRAY);
        Sprite leftSide = new Border(new Rectangle(0, 50, 25, 550), Color.LIGHT_GRAY);
        Sprite rightSide = new Border(new Rectangle(775, 50, 25, 550), Color.LIGHT_GRAY);
        Sprite lowerSide = new Block(new Rectangle(25, 600, 750, 25), Color.LIGHT_GRAY, colors, null, 0);
        //Sprite lowerSide = new Border(new Rectangle(30, 570, 740, 30), Color.LIGHT_GRAY);
        colors.put(1, Color.LIGHT_GRAY);
        //makes the frame and it to the game:
        Sprite[] frame = {upperSide, leftSide, rightSide, lowerSide};
        for (int i = 0; i < 4; i++) {
            frame[i].addToGame(this);
        }
        //this.levelInformation.getBackground().addToGame(this);
        Sprite indicator = new ScoreIndicator(this.scoreGame, new Rectangle(new Point(260, 0), 280, 25), Color.BLACK);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives, new Rectangle(new Point(0, 0),
                260, 25), Color.BLACK);
        LevelNameIndicator levelName = new LevelNameIndicator(this.levelInformation.levelName(),
                new Rectangle(new Point(540, 0), 260, 25), Color.BLACK);
        levelName.addToGame(this);
        indicator.addToGame(this);
        livesIndicator.addToGame(this);
        //makes the paddle and add it to the game:
        this.paddle = new Paddle(new Rectangle(new Point(400
                - (this.levelInformation.paddleWidth() / 2.0), 580), this.levelInformation.paddleWidth(),
                10), this.keyboard, this.levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
        this.createBlocks(); //makes the blocks and add them to the game
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
        }
    }

    /**
     * Create balls.
     * <p>
     * makes the balls and add them to the game:
     */
    public void createBallsOnTopOfPaddle() {
        Ball ball;
        double x = this.getPaddle().getBlockSize().getUpperLeft().getX()
                + this.getPaddle().getBlockSize().getWidth() / 2;
        double y = this.getPaddle().getBlockSize().getUpperLeft().getY() - 5;
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            ball = new Ball(new Point(x, y), 5, Color.WHITE, this.environment);
            ball.addToGame(this);
            ball.addHitListener(this.ballListener);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
        }
        this.ballListener.getRemainingBalls().setValue(this.levelInformation.numberOfBalls());
    }

    /**
     * Run.
     */
    public void run() {
        while (this.lives.getValue() > 0 && this.blockListener.getRemainingBlocks().getValue() != 0) {
            this.playOneTurn();
            this.paddle.getBlockSize().setUpperLeft(new Point((AnimationRunner.WIDTH - 85.0) / 2,
                    AnimationRunner.HEIGHT - 10));
            this.lives.decrease(1);
        }
        if (this.blockListener.getRemainingBlocks().getValue() == 0) {
            this.scoreGame.increase(100);
        }
        playOneTurn();
    }


    /**
     * Run.
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.paddle.getBlockSize().setUpperLeft((new Point(400
                - this.levelInformation.paddleWidth() / 2.0, 560)));
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
        if (this.blockListener.getRemainingBlocks().getValue() == 0) {
            this.scoreGame.increase(100);
        }
    }

    /**
     * Game finished boolean.
     *
     * @return the boolean
     */
    public boolean gameFinished() {
        return !(this.lives.getValue() > 0 && this.blockListener.getRemainingBlocks().getValue() > 0);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.white);
        this.sprites.notifyAllTimePassed();
        if (this.ballListener.getRemainingBalls().getValue() == 0) {
            this.lives.decrease(1);
        }
        //stopping condition
        if (this.blockListener.getRemainingBlocks().getValue() == 0
                || this.ballListener.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}