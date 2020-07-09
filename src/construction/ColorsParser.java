package construction;

import java.awt.Color;


/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string color.
     *
     * @param colorString the color string
     * @return the color
     */
    public Color colorFromString(String colorString) {
        if (colorString.contains("RGB")) {
            String[] num = colorString.split(",");
            num[0] = num[0].substring(4);
            num[2] = num[2].substring(0, num[2].length() - 1);
            return (new Color(Integer.parseInt(num[0]), Integer.parseInt(num[1]), Integer.parseInt(num[2])));
        } else {
            return findColor(colorString);
        }
    }

    /**
     * Find color color.
     *
     * @param c the c
     * @return the color
     */
    public Color findColor(String c) {
        Color color = null;
        switch (c.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "darkgray":
                color = Color.DARK_GRAY;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "green":
                color = Color.GREEN;
                break;

            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightgray":
                color = Color.LIGHT_GRAY;
                break;
            case "magneta":
                color = Color.MAGENTA;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
            default:
                color = Color.BLACK;
                break;
        }
        return color;
    }
}

