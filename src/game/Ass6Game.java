package game;

import animation.AnimationRunner;
import biuoop.GUI;
import menu.Task;

/**
 * The type Ass 6 game.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Ass6Game {
    /**
     * the start of the game.
     *
     * @param path - the path.
     */
    private static void run(String path) {
        biuoop.GUI gui = new GUI("game", 800, 600);
        AnimationRunner animation = new AnimationRunner(gui, 60);
        GameFlow gameFlow = new GameFlow(gui, animation, path);
        while (true) {
            gameFlow.createMenu();
            gameFlow.getMenu().setStatus();
            animation.run(gameFlow.getMenu());
            Task<Void> task = gameFlow.getMenu().getStatus();
            if (task != null) {
                task.run();
                gameFlow.getMenu().setStatus();
            } else {
                animation.run(gameFlow.getSubMenu());
                task = gameFlow.getSubMenu().getStatus();
                if (task != null) {
                    task.run();
                    gameFlow.getMenu().setStatus();
                }
            }
            gameFlow.getMenu().setStatus();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String path = "level_sets.txt";
        if (args.length > 0) {
            path = args[0];
        }
        run(path);
    }
}
