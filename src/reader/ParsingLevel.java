package reader;

import animation.FillBackground;
import collision.Sprite;
import gameobject.Block;
import gameobject.Point;
import game.LevelInformation;
import settings.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.PatternSyntaxException;


/**
 * The type Parsing level.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ParsingLevel {
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private BlocksFromSymbolsFactory blockFactory;
    private Point startOfFirstBlock;
    private int numOfBlocks;
    private int rowHeight;
    private List<Block> blocks;

    /**
     * Instantiates a new Parsing level.
     *
     * @param level      the level
     * @param factory    the factory
     * @param blocksInfo the blocks info
     */
    public ParsingLevel(List<String> level, BlocksFromSymbolsFactory factory,
                        List<String> blocksInfo) {
        this.blockFactory = factory;
        //split the values by :
        Map<String, String> mapLevelInfo = new TreeMap<>();
        for (int i = 0; i < level.size(); i++) {
            String[] row = level.get(i).split(":");
            mapLevelInfo.put(row[0], row[1]);
        }
        try {
            this.startOfFirstBlock = new Point(Double.parseDouble(mapLevelInfo.get("blocks_start_x")),
                    Double.parseDouble(mapLevelInfo.get("blocks_start_y")));
            //add the parameters value.
            this.paddleSpeed = Integer.parseInt(mapLevelInfo.get("paddle_speed"));
            this.paddleWidth = Integer.parseInt(mapLevelInfo.get("paddle_width"));
            this.levelName = mapLevelInfo.get("level_name");
            this.ballVelocity = fromStringToVelocity(mapLevelInfo.get("ball_velocities"));
            this.background = new FillBackground(mapLevelInfo.get("background"), new Point(0, 0),
                    800, 600);
            this.numOfBlocks = Integer.parseInt(mapLevelInfo.get("num_blocks"));
            this.rowHeight = Integer.parseInt(mapLevelInfo.get("row_height"));
            fromSymbolToBlock(blocksInfo);
        } catch (RuntimeException e) {
            System.out.println("one of the level parameter is missing.");
            throw e;
        }
    }

    /**
     * @param velocity the string the represents velocity.
     * @return list of velocitt.
     */
    private List<Velocity> fromStringToVelocity(String velocity) {
        String[] ballsVelocity;
        //if there is more than 1 ball
        try {
            ballsVelocity = velocity.split(" ");
        } catch (PatternSyntaxException e) {
            ballsVelocity = new String[1];
            ballsVelocity[0] = velocity;
        }
        //split the balls speed and angle by , and add new velocity
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < ballsVelocity.length; i++) {
            String[] speedAngle = ballsVelocity[i].split(",");
            Velocity ballVelocitys = Velocity.fromAngleAndSpeed(Integer.parseInt(speedAngle[0])
                    , Integer.parseInt(speedAngle[1]));
            velocities.add(ballVelocitys);
        }
        return velocities;
    }

    /**
     * Gets level information.
     *
     * @return the level information
     */
    public LevelInformation getLevelInformation() {
        return new CreateLevel(this.ballVelocity, this.paddleSpeed, this.paddleWidth,
                this.levelName, this.background, this.blocks, this.numOfBlocks);
    }

    /**
     * @param blockSymbol the list of block symbols.
     */
    private void fromSymbolToBlock(List<String> blockSymbol) {
        List<Block> block = new ArrayList<Block>();
        int xpos = (int) this.startOfFirstBlock.getX();
        int ypos = (int) this.startOfFirstBlock.getY();
        for (int i = 0; i < blockSymbol.size(); i++) {
            int x = 0;
            for (int j = 0; j < blockSymbol.get(i).length(); j++) {
                String b = blockSymbol.get(i).substring(j, j + 1);
                if (this.blockFactory.isSpaceSymbol(b)) {
                    x += this.blockFactory.getSpaceWidth(b);
                } else if (this.blockFactory.isBlockSymbol(b)) {
                    block.add(blockFactory.getBlock(b, xpos + x, ypos));
                    x += this.blockFactory.getWidthBlock(b);
                }
            }
            ypos += this.rowHeight;
        }
        this.blocks = block;
    }
}