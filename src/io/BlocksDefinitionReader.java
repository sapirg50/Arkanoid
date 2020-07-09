package io;

import construction.BlockCreateType;
import construction.BlockCreator;
import construction.BlocksFromSymbolsFactory;
import construction.ColorsParser;

import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;
import java.io.IOException;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private List<String> lines = new ArrayList<String>();

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        BufferedReader buffer = new BufferedReader(reader);
        Map<Integer, Color> colorList;
        Map<Integer, String> imageList;
        String line;
        String[] split, words;
        Integer width = null;
        String symbol = null;
        BlockCreateType bc;
        String[] defaultSplit = null;
        try {
            while ((line = buffer.readLine()) != null) {
                bc = new BlockCreateType();
                colorList = new HashMap<>();
                imageList = new HashMap<>();
                if (line.startsWith("default")) {
                    defaultSplit = line.split(" ");
                }
                if (line.startsWith("sdef")) {
                    split = line.split(" ");
                    for (String index : split) {
                        words = index.split(":");
                        if (words[0].equals("symbol")) {
                            symbol = words[1];
                        }
                        if (words[0].equals("width")) {
                            width = Integer.parseInt(words[1]);
                        }
                    }
                    spacerWidths.put(symbol, width);
                }
                if (line.startsWith("bdef")) {
                    split = line.split(" ");
                    String[] temp = split[1].split(":");
                    symbol = temp[1];
                    if (defaultSplit != null) {
                        updateBlockCreateType(bc, defaultSplit, colorList, imageList);
                    }
                    updateBlockCreateType(bc, split, colorList, imageList);
                    if (!bc.isFull()) {
                        System.out.println("Error! not enough data");
                        System.exit(1);
                    } else {
                        blockCreators.put(symbol, bc);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        } finally {
            try {
                buffer.close();
            } catch (IOException e) {
                System.out.println("Cannot close Buffered Reader");
            }
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * update Block Create Type.
     *
     * @param bc the BlockCreateType.
     * @param split the string.
     * @param colors the color map.
     * @param images the image map.
     */
    private static void updateBlockCreateType(BlockCreateType bc, String[] split,
                                              Map<Integer, Color> colors, Map<Integer, String> images) {
        ColorsParser fixColor = new ColorsParser();
        String[] words;
        Integer num;
        for (String index : split) {
            words = index.split(":");
            for (int i = 0; i < words.length; i++) {
                if (words[0].equals("height")) {
                    bc.setHeight(Integer.parseInt(words[1]));
                }
                if (words[0].equals("width")) {
                    bc.setWidth(Integer.parseInt(words[1]));
                }
                if (words[0].equals("hit_points")) {
                    bc.setHitPoints(Integer.parseInt(words[1]));
                }
                if (words[0].equals("stroke")) {
                    //String colorString = ;
                    bc.setStroke(fixColor.colorFromString(words[1].substring(6, words[1].length() - 1)));
                }
                if (words[0].contains("fill")) {
                    String[] splitFill = words[0].split("-");
                    if (splitFill.length == 2) {
                        num = Integer.parseInt(splitFill[1]);
                    } else {
                        num = 1;
                    }
                    if (words[1].contains("color")) {
                        colors.put(num, fixColor.colorFromString(words[1].substring(6, words[1].length() - 1)));
                        bc.setColorList(colors);
                    } else {
                        images.put(num, words[1].substring(6, words[1].length() - 1));
                        bc.setImageList(images);
                    }
                }
            }
        }
    }

}