package io;

import construction.BlocksFromSymbolsFactory;
import construction.ColorsParser;
import game.LevelInformation;
import game.Velocity;
import game.sprite.Block;
import levels.LevelInformationType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private BlocksFromSymbolsFactory bl;
    private LevelInformationType lv;
    private BufferedReader buffer;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(Reader reader) {
        String line;
        List<LevelInformation> levelInformationList = new ArrayList<>();
        this.buffer = new BufferedReader(reader);
        // BlocksFromSymbolsFactory bl = null;
        try {
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("START_LEVEL")) {
                    lv = new LevelInformationType();
                    while (!line.startsWith("END_LEVEL")) {
                        line = buffer.readLine();
                        startLevels(line);
                    }
                    if (!lv.isFull()) {
                        System.out.println("Error! not enough data");
                        System.exit(1);
                    } else {
                        levelInformationList.add(lv);
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
        return levelInformationList;
    }

    /**
     * Start levels.
     *
     * @param line the line
     */
    public void startLevels(String line) {
        String[] words, ballsV, speedAngle;
        List<Velocity> velocityList;
        words = line.split(":");
        if (words[0].equals("level_name")) {
            lv.setLevelName(line.substring(11).trim());
        }
        if (words[0].equals("ball_velocities")) {
            ballsV = words[1].split(" ");
            velocityList = new ArrayList<>();
            for (int i = 0; i < ballsV.length; i++) {
                speedAngle = ballsV[i].split(",");
                Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(speedAngle[0]),
                        Double.parseDouble(speedAngle[1]));
                velocityList.add(v);
                lv.setVelocityList(velocityList);
            }
        }
        if (words[0].equals("background")) {
            ColorsParser fixColor = new ColorsParser();
            if (words[1].contains("image")) {
                lv.setImage(words[1].substring(6, words[1].length() - 1));
            }
            if (words[1].contains("color")) {
                lv.setColor(fixColor.colorFromString(words[1].substring(6, words[1].length() - 1)));
            }
        }
        if (words[0].equals("paddle_speed")) {
            lv.setSpeedPaddle(Integer.parseInt(words[1].trim()));
        }
        if (words[0].equals("paddle_width")) {
            lv.setPaddleWidth(Integer.parseInt(words[1].trim()));
        }
        if (words[0].equals("block_definitions")) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(words[1].trim());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            bl = BlocksDefinitionReader.fromReader(reader);
        }
        if (words[0].equals("blocks_start_x")) {
            lv.setStatX(Integer.parseInt(words[1].trim()));
        }
        if (words[0].equals("blocks_start_y")) {
            lv.setStatY(Integer.parseInt(words[1].trim()));
        }
        if (words[0].equals("row_height")) {
            lv.setRowHeight(Integer.parseInt(words[1].trim()));
        }
        if (words[0].equals("num_blocks")) {
            lv.setNumOfBlocks(Integer.parseInt(words[1].trim()));
        }
        if (line.startsWith("START_BLOCKS")) {
            lv.setBlockList(startBlock(line));
        }
    }

    /**
     * Start block list.
     *
     * @param line the line
     * @return the list
     */
    public List<Block> startBlock(String line) {
        String symbol;
        int x = lv.getStatX();
        int y = lv.getStatY();
        List<Block> blockList = new ArrayList<>();
        Block b;
        while (!line.startsWith("END_BLOCKS")) {
            try {
                line = buffer.readLine();
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
            for (int i = 0; i < line.length(); i++) {
                symbol = line.substring(i, i + 1);
                if (bl.isSpaceSymbol(symbol)) {
                    x += bl.getSpaceWidth(symbol);
                }
                if (bl.isBlockSymbol(symbol)) {
                    b = bl.getBlock(symbol, x, y);
                    x += bl.getBlockWidth(symbol);
                    blockList.add(b);
                }
            }
            x = lv.getStatX();
            y += lv.getRowHeight();
        }
        return blockList;
    }
}
