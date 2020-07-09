package game;

import java.io.IOException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScores;

    /**
     * Instantiates a new High scores table.
     * <p>
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>(this.size + 1);
    }

    /**
     * Add.
     * <p>
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        this.highScores.add(score);
        Collections.sort(this.highScores, new Comparator<ScoreInfo>() {
            public int compare(ScoreInfo player, ScoreInfo player1) {
                return player1.getScore() - player.getScore();
            }
        });
        if (this.highScores.size() > this.size) {
            this.highScores.remove(this.size);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     * <p>
     * The list is sorted such that the highest scores come first.
     *
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Sets size.
     *
     * @param newSize the new size
     */
    public void setSize(int newSize) {
        this.size = newSize;
    }

    /**
     * Sets high scores.
     *
     * @param highScores1 the high scores 1
     */
    public void setHighScores(List<ScoreInfo> highScores1) {
        this.highScores = highScores1;
    }

    /**
     * Gets rank.
     * <p>
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score the score
     * @return the rank of the current score
     */
    public int getRank(int score) {
        for (int i = 0; i < this.getHighScores().size(); i++) {
            if (this.getHighScores().get(i).getScore() < score) {
                return i + 1;
            }
        }
        return this.getHighScores().size() + 1;
    }

    /**
     * Ask or not boolean.
     *
     * check if we need to ask the name
     * @param score the score
     * @return the boolean
     */
    public Boolean askOrNot(int score) {
        return (this.getRank(score) - 1 < this.size());
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable temp = HighScoresTable.loadFromFile(filename);
        this.setHighScores(temp.highScores);
        // this.setSize(temp.size);
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(this);
            out.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable temp = null;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            temp = (HighScoresTable) in.readObject();
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return temp;
    }
}