package menu;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;

/**
 * The type Show high scores task.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ShowHiScoresTask implements Task<Void> {
    private KeyPressStoppableAnimation highScoresTable;
    private AnimationRunner runner;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param scores the scores
     * @param runner the runner
     */
    public ShowHiScoresTask(KeyPressStoppableAnimation scores, AnimationRunner runner) {
        this.highScoresTable = scores;
        this.runner = runner;
    }

    /**
     * @return none.
     */
    public Void run() {
        this.highScoresTable.setStop();
        this.runner.run(highScoresTable);
        return null;
    }
}
