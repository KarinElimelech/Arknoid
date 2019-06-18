package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import menu.MenuBackground;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import gameobject.Point;
import menu.MenuAnimation;
import menu.ShowHiScoresTask;
import menu.Task;
import animation.FillBackground;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import scores.ScoreInfo;
import settings.Counter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Game flow.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class GameFlow {
    private AnimationRunner animation;
    private KeyboardSensor keyboard;
    private biuoop.GUI gui;
    private Counter score;
    private HighScoresTable table;
    private File file;
    private Counter lives;
    private String path;
    private MenuAnimation<Task<Void>> menu;
    private MenuAnimation<Task<Void>> subMenu;


    /**
     * Instantiates a new Game flow.
     *
     * @param gui       the gui
     * @param animation the animation
     * @param path      - the path.
     */
    public GameFlow(biuoop.GUI gui, AnimationRunner animation, String path) {
        this.gui = gui;
        this.animation = animation;
        this.keyboard = gui.getKeyboardSensor();
        this.path = path;
        setTableFile();
        setLivesScore();
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public MenuAnimation<Task<Void>> getMenu() {
        return this.menu;
    }

    /**
     * Gets sub menu.
     *
     * @return the sub menu
     */
    public MenuAnimation<Task<Void>> getSubMenu() {
        return subMenu;
    }

    /**
     * Sets table file.
     */
    public void setTableFile() {
        this.file = new File("src/game/highscores");
        this.table = HighScoresTable.loadFromFile(file);
        try {
            table.save(this.file);
        } catch (IOException e) {
            System.err.println("Unable to find file: " + file.getName());
        }
    }

    /**
     * Sets lives score.
     */
    public void setLivesScore() {
        this.lives = new Counter();
        lives.increase(4);
        this.score = new Counter();
    }

    /**
     * Create menu.
     */
    public void createMenu() {
        //create submenu
        InputStream inputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(this.path);
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        this.subMenu = new MenuAnimation<Task<Void>>(this.gui.getKeyboardSensor(),
                new FillBackground("LevelSet", new Point(0, 0), 800, 600));
        String line = null, key = null, message = null;
        GameTask gameTask = null;
        //read the submenu.
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 == 1) {
                    String[] levelInfo = line.split(":");
                    key = levelInfo[0];
                    message = levelInfo[1];
                } else {
                    gameTask = new GameTask(new GameFlow(this.gui, this.animation, line));
                }
                if (gameTask != null) {
                    subMenu.addSelection(key, message, gameTask);
                    gameTask = null;
                }
            }
        } catch (IOException e) {
            System.out.println("can't read levelSet file in the main");
            throw new RuntimeException(e);
        }

        this.menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(),
                new MenuBackground());
        KeyPressStoppableAnimation highScores = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                gui.getKeyboardSensor().SPACE_KEY
                , new HighScoresAnimation(table));
        ShowHiScoresTask showHiScoresTask = new ShowHiScoresTask(highScores, this.animation);
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "High Scores", showHiScoresTask);
        menu.addSelection("q", "Exit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.animation, lives, this.score);
            level.initialize();
            while (level.isThereBlocks() && lives.getValue() > 0) {
                level.playOneTurn();
            }
            if (!level.getLives()) {
                KeyPressStoppableAnimation endLife = new KeyPressStoppableAnimation(this.keyboard,
                        this.keyboard.SPACE_KEY
                        , new EndScreen(false, score.getValue()));
                this.animation.run(endLife);
                break;
            }

        }
        endGame();
    }

    /**
     * open dialog.
     */
    private void dialog() {
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Enter Name", "What is your name?", "Anonymous");
        ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
        this.table.add(scoreInfo);
    }

    /**
     * finish the game.
     */
    private void endGame() {
        if (this.table.getRank(this.score.getValue()) != -1) {
            dialog();
            if (lives.getValue() > 0) {
                KeyPressStoppableAnimation endScreen = new KeyPressStoppableAnimation(this.keyboard,
                        keyboard.SPACE_KEY, new EndScreen(true, score.getValue()));
            }
        }
        try {
            table.save(file);
        } catch (IOException e) {
            System.err.println("Unable to find file: " + file.getName());
        }
        KeyPressStoppableAnimation highScoresAnimation = new KeyPressStoppableAnimation(this.keyboard,
                this.keyboard.SPACE_KEY, new HighScoresAnimation(table));
        this.animation.run(highScoresAnimation);
    }
}