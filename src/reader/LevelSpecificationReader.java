package reader;

import game.LevelInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        //create list of level information and block and then add them to one big list.
        List<List<String>> allLevels = new ArrayList<List<String>>();
        List<List<String>> allBlocks = new ArrayList<List<String>>();
        List<Reader> readBlock = new ArrayList<Reader>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                List<String> levels = new ArrayList<String>();
                List<String> blocks = new ArrayList<String>();
                if (line.equals("") || line.contains("#")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    line = bufferedReader.readLine();
                    while (!(line.equals("END_LEVEL"))) {
                        if (line.equals("") || line.contains("#")) {
                            line = bufferedReader.readLine();
                            continue;
                        } else if (line.contains("block_definitions")) {
                            Reader blockReader = new InputStreamReader(ClassLoader
                                    .getSystemResourceAsStream(line.substring(line.indexOf(":") + 1)));
                            readBlock.add(blockReader);
                        } else if (line.equals("START_BLOCKS")) {
                            line = bufferedReader.readLine();
                            while (!(line.equals("END_BLOCKS"))) {
                                if (line.contains("#")) {
                                    continue;
                                }
                                blocks.add(line);
                                line = bufferedReader.readLine();
                            }
                        } else {
                            levels.add(line);
                        }
                        line = bufferedReader.readLine();
                    }
                    allLevels.add(levels);
                    allBlocks.add(blocks);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("can't read from the level information file.");
        }
        List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
        for (int i = 0; i < allLevels.size(); i++) {
            ParsingLevel level = new ParsingLevel(allLevels.get(i),
                    BlocksDefinitionReader.fromReader(readBlock.get(i)),
                    allBlocks.get(i));
            levelInformations.add(level.getLevelInformation());
        }
        return levelInformations;
    }

}
