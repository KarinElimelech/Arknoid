package game;

import menu.Task;
import reader.LevelSpecificationReader;

import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game task.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class GameTask implements Task<Void> {
    private GameFlow gameFlow;

    /**
     * Instantiates a new Game task.
     *
     * @param gameFlow the game flow
     */
    public GameTask(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    /**
     * @return void.
     */
    public Void run() {

        File file = new File(this.gameFlow.getPath());
        Reader read = null;
        LevelSpecificationReader l = new LevelSpecificationReader();
        List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
        try {
            if (file.exists()) {
                read = new FileReader(file);
            } else {
                read = new InputStreamReader(ClassLoader.getSystemResourceAsStream(this.gameFlow.getPath()));
            }
            levelInformations = l.fromReader(read);
        } catch (IOException e) {
            System.out.println("can't open the file in the GameTask");
        }
        this.gameFlow.runLevels(levelInformations);
        return null;
    }
}
