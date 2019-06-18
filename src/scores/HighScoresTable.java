package scores;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> highScores;
    private int maxSize;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<>();
        this.maxSize = size;
    }

    /**
     * Add.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int rank = getRank(score.getScore());
        if (rank != -1) {
            if (this.highScores.size() == this.maxSize) {
                this.highScores.remove(this.maxSize - 1);
            }
            this.highScores.add(rank, score);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return this.highScores.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        if (this.getHighScores().isEmpty()
                || score > this.getHighScores().get(0).getScore()) {
            return 0;
        } else if (this.size() < this.maxSize) {
            for (int i = 1; i < this.highScores.size(); i++) {
                if (score < this.highScores.get(i).getScore()) {
                    if (this.getHighScores().size() > i + 1
                            && score < this.getHighScores().get(i + 1).getScore()) {
                        continue;
                    }
                    return i + 1;
                }
            }
        } else {
            for (int j = 0; j < this.maxSize - 1; j++) {
                if (score > this.getHighScores().get(j + 1).getScore()
                        && score < this.getHighScores().get(j).getScore()) {
                    return j + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            HighScoresTable table = (HighScoresTable) objectInputStream.readObject();
            this.highScores = table.getHighScores();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to load file: " + filename.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load class for object in file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed loading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }


    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            table.load(filename);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to load from file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed load from file");
            e.printStackTrace(System.err);
        }
        return table;
    }

}
