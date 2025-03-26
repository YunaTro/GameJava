import java.awt.*;
import java.sql.SQLOutput;
import java.util.LinkedList;

public class Weapon implements Attack {
    public LinkedList<Bullet> bullets;
    public Ship enemy;

    public Weapon()  {
        bullets = new LinkedList<>();
    }

    public void setEnemy(Ship enemy) {
        this.enemy = enemy;
    }

    @Override
    public void toAttack(HealthPoint enemy) {

    }

    public void attack_check() {
        for (int i = 0; i < bullets.size(); ++i) {
            if (enemy.intersection(bullets.get(i).x, bullets.get(i).y, 10, 10)) {
                enemy.setHP(enemy.getHP() - bullets.get(i).damage);
                bullets.remove(i);

                attack_check();
                return;
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
            if (bullets.get(i).x < -100 || bullets.get(i).x > 2000) {
                bullets.remove(i);
                return;
            }
            if (bullets.get(i).y < -100 || bullets.get(i).y > 2000) {
                bullets.remove(i);
                return;
            }
        }
    }

    public void move() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }
        attack_check();
    }
}
