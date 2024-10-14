import java.awt.*;

public class Bullet extends Unit {
    public int damage;

    public Bullet(int x, int y, int speed, double angle, Image image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(this.x, this.y, 10, 10);
    }

    @Override
    public void move() {
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);
        x += dy;
        y -= dx;
    }
}
