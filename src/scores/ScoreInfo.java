package scores;

import java.io.Serializable;

/**
 * The type Score info.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
}
