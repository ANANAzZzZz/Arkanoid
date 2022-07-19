package ANANAzZzZz.game.entities;

public class Color {
    private final int red;
    private final int green;
    private final int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getFloatRed() {
        return toFloat(red);
    }

    public float getFloatGreen() {
        return toFloat(green);
    }

    public float getFloatBlue() {
        return toFloat(blue);
    }

    private float toFloat(int colorValue) {
        return colorValue / 255f;
    }
}
