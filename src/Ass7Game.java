import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import game.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DialogManager;
import game.Counter;
import game.GameFlow;
import game.HighScoresTable;
import game.LevelInformation;
import game.ScoreInfo;
import io.LevelSpecificationReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = animationRunner.getKeyboardSensor();
        //read the level definition:
        InputStream is = null;
        if (args.length == 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        }
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        Menu<Task<Void>> subMenu = new MenuAnimation<>(keyboardSensor, animationRunner, "Select Levels");
        List<String> keys = new ArrayList<>();
        List<String> path = new ArrayList<>();
        List<String> message = new ArrayList<>();
        readSetLevels(read, keys, message, path);
        //high scores table:
        HighScoresTable tableTest = new HighScoresTable(7);
        File fileName = new File("highscores");
        try {
            tableTest.load(fileName);
        } catch (Exception e) {
            System.out.println();
        }
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboardSensor, animationRunner, "Arkanoid");
        menu.addSubMenu("s", "Start", subMenu);
        menu.addSelection("h", "High Scores", new Task<Void>() {
            @Override
            public Void run() {
                Animation a1 = new HighScoresAnimation(tableTest, keyboardSensor);
                Animation a1k = new KeyPressStoppableAnimation(keyboardSensor, "space", a1);
                animationRunner.run(a1k);
                return null;
            }
        });
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        });
        for (int i = 0; i < keys.size(); i++) {
            String namePath = path.get(i);
            subMenu.addSelection(keys.get(i), message.get(i), new Task<Void>() {
                @Override
                public Void run() {
                    Counter score = new Counter(0);
                    Counter lives = new Counter(10);
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(namePath);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    LevelSpecificationReader readLevels = new LevelSpecificationReader();
                    List<LevelInformation> levelInformationList = readLevels.fromReader(reader);
                    GameFlow game = new GameFlow(animationRunner, keyboardSensor, lives, score);
                    game.runLevels(levelInformationList);
                    DialogManager dialog = gui.getDialogManager();
                    if (tableTest.askOrNot(score.getValue())) {
                        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                        tableTest.add(new ScoreInfo(name, game.getScore().getValue()));
                    }
                    try {
                        tableTest.save(fileName);
                    } catch (Exception e) {
                        System.out.println("cannot save file high_scores");
                    }
                    Animation a1 = new HighScoresAnimation(tableTest, keyboardSensor);
                    Animation a1k = new KeyPressStoppableAnimation(keyboardSensor, "space", a1);
                    animationRunner.run(a1k);
                    return null;
                }
            });
        }

        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> taskMenu = menu.getStatus();
            taskMenu.run();
            menu.reset();
            subMenu.reset();
        }
    }

    /**
     * Read set levels.
     *
     * @param br      the br
     * @param keys    the keys
     * @param message the message
     * @param path    the path
     * @pardam br the br
     */
    public static void readSetLevels(BufferedReader br, List<String> keys, List<String> message, List<String> path) {
        String line;
        String[] words;
        LineNumberReader numberReader = new LineNumberReader(br);
        try {
            while ((line = numberReader.readLine()) != null) {
                if (numberReader.getLineNumber() % 2 == 1) {
                    words = line.split(":");
                    keys.add(words[0]);
                    message.add(words[1]);
                } else {
                    path.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        } finally {
            try {
                numberReader.close();
            } catch (IOException e) {
                System.out.println("Cannot close LineNumberReader");
            }
        }
    }
}