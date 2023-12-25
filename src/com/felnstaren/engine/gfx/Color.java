package com.felnstaren.engine.gfx;

public class Color {

    public static final Color 
    RED = new Color(255, 0, 0),
    GREEN = new Color(0, 255, 0),
    BLUE = new Color(0, 0, 255);

    public int r,g,b,a = 255;
    public Color() {}
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getColor() {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }

    public void lerp(Color other, float by) {
        r = (int) (r * (1 - by) + other.r * by);
        g = (int) (g * (1 - by) + other.g * by);
        b = (int) (b * (1 - by) + other.b * by);
        a = (int) (a * (1 - by) + other.a * by);
    }

    public static Color lerp(Color c1, Color c2, float by) {
        Color out = new Color();
        out.r = (int) (c1.r * (1 - by) + c2.r * by);
        out.g = (int) (c1.g * (1 - by) + c2.g * by);
        out.b = (int) (c1.b * (1 - by) + c2.b * by);
        out.a = (int) (c1.a * (1 - by) + c2.a * by);
        return out;
    }

    public static int lerp(int color1, int color2, float by) {
        int r1 = (color1 >> 16) & 0xff;
        int r2 = (color2 >> 16) & 0xff;
        int g1 = (color1 >> 8) & 0xff;
        int g2 = (color2 >> 8) & 0xff;
        int b1 = color1 & 0xff;
        int b2 = color2 & 0xff;
        int a1 = (color1 >> 24) & 0xff;
        int a2 = (color2 >> 24) & 0xff;

        int r = (int) (r1 * (1 - by) + r2 * by);
        int g = (int) (g1 * (1 - by) + g2 * by);
        int b = (int) (b1 * (1 - by) + b2 * by);
        int a = (int) (a1 * (1 - by) + a2 * by);
        return (a << 24 | r << 16 | g << 8 | b);
    }
    
}
