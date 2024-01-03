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

    private static int r1,r2,g1,g2,b1,b2,a1,a2,R,G,B,A;
    public static int lerp(int color1, int color2, float by) {
        r1 = (color1 >> 16) & 0xff;
        r2 = (color2 >> 16) & 0xff;
        g1 = (color1 >> 8) & 0xff;
        g2 = (color2 >> 8) & 0xff;
        b1 = color1 & 0xff;
        b2 = color2 & 0xff;
        a1 = (color1 >> 24) & 0xff;
        a2 = (color2 >> 24) & 0xff;

        R = (int) (r1 * (1 - by) + r2 * by);
        G = (int) (g1 * (1 - by) + g2 * by);
        B = (int) (b1 * (1 - by) + b2 * by);
        A = (int) (a1 * (1 - by) + a2 * by);
        return (A << 24 | R << 16 | G << 8 | B);
    }

    public static int lerp(float by, int... colors) {
        if(colors.length == 1) return colors[0];
        int i = (int) ((colors.length-1) * by);
        int color1 = colors[i];
        int color2;
        if(i+1 >= colors.length) color2 = colors[i];
        else color2 = colors[i+1];
        return lerp(color1, color2, ((by * colors.length) - i) / colors.length);
    }
    
}
