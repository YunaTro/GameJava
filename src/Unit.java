import java.awt.*;

public abstract class Unit implements Moving, Drawing {
    public int x = 0;
    public int y = 0;
    public int speed = 0;
    public double angle = 0;
    public Image image;
    public int xx = 0;
    public int yy = 0;
    public Direction direction;

    public Unit(int x, int y, int speed, double angle, Image image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.image = image;
        direction = new Direction();
    }
    public boolean intersection (int x, int y, int w, int h){
        int x1b = x + w / 10;
        int y1b = y + h / 10;
        int x2b = x + w - w / 10;
        int y2b = y + h - h / 10;
        int x1s = this.x + image.getWidth(null) / 10;
        int y1s = this.y + image.getHeight(null) / 10;
        int x2s = this.x + image.getWidth(null) - image.getWidth(null) / 10;
        int y2s = this.y + image.getHeight(null) - image.getHeight(null) / 10;
        if (x1b > x1s && x2b < x2s && y1b > y1s && y2b < y2s)
            return true;
        else
            return false;
    }
}
