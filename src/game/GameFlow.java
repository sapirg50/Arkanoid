package game;

import java.util.List;

import animation.AnimationRunner;
import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.YouWinScreen;
import biuoop.KeyboardSensor;
import levels.LevelInformationType;
import levels.GameLevel;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives, score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar    the ar
     * @param ks    the ks
     * @param lives the lives
     * @param score the score
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter lives, Counter score) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.lives = lives;
        this.score = score;
    }

    /**
     * Sets score.
     *
     * @param newScore the new score
     */
    public void setScore(Counter newScore) {
        this.score = newScore;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.keyboardSensor, this.animationRunner,
                    this.lives, score, (LevelInformationType) levelInfo);
            level.initialize();
            //level has more blocks and player has more lives
            while (!level.gameFinished()) {
                level.playOneTurn();
            }
            if (level.getLives().equals(0)) { //no more lives
                break;
            }
        }
        if (lives.getValue() == 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new GameOverScreen(this.score)));
        } else {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new YouWinScreen(this.score)));
        }
        //this.animationRunner.close();
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }
}