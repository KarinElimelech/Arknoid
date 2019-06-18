package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Blocks definition reader.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();
        List<String> listOfSettings = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                listOfSettings.add(line);
            }
        } catch (IOException e) {
            System.out.println("can't read block definition");
        }
        List<String> spacersDefinitions = new ArrayList<String>();
        List<String> blockDefinitions = new ArrayList<String>();
        List<String> defaultDefinitions = new ArrayList<String>();
        splitToList(listOfSettings, spacersDefinitions, blockDefinitions, defaultDefinitions);
        for (int j = 0; j < blockDefinitions.size(); j++) {
            BlockFactory blockCreator = new BlockFactory(blockDefinitions.get(j), defaultDefinitions);
            blocksFromSymbolsFactory.addBlockCreator(blockCreator.getBlockName(), blockCreator);
        }
        findSymbol(spacersDefinitions, blocksFromSymbolsFactory);
        return blocksFromSymbolsFactory;
    }

    /**
     * @param listOfSetting      list.
     * @param spacersDefinitions the space definition.
     * @param blockDefinitions   the block definition.
     * @param defaultDefinitions the default definition.
     */
    private static void splitToList(List<String> listOfSetting, List<String> spacersDefinitions,
                                    List<String> blockDefinitions, List<String> defaultDefinitions) {
        for (int i = 0; i < listOfSetting.size(); i++) {
            if (listOfSetting.get(i).startsWith("bdef")) {
                blockDefinitions.add(listOfSetting.get(i)
                        .substring(listOfSetting.get(i).indexOf(" ") + 1));
            } else if (listOfSetting.get(i).startsWith("sdef")) {
                spacersDefinitions.add(listOfSetting.get(i).
                        substring(listOfSetting.get(i).indexOf(" ") + 1));
            } else if (listOfSetting.get(i).startsWith("default")) {
                defaultDefinitions.add(listOfSetting.get(i).
                        substring(listOfSetting.get(i).indexOf(" ") + 1));
            }
        }
    }

    /**
     * @param spacersDefinition        the space definitions.
     * @param blocksFromSymbolsFactory the block from symbol factory.
     */
    private static void findSymbol(List<String> spacersDefinition,
                                   BlocksFromSymbolsFactory blocksFromSymbolsFactory) {
        for (int k = 0; k < spacersDefinition.size(); k++) {
            String symbol = spacersDefinition.get(k)
                    .substring(spacersDefinition.get(k).indexOf(":") + 1);
            String[] s = symbol.split(" ");
            String space = s[1].substring(s[1].indexOf(":") + 1);
            blocksFromSymbolsFactory.addSpacer(s[0], Integer.parseInt(space));
        }
    }
}
