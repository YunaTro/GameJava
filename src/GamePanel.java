import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship ship;
    public Ship ship1;
    public Timer timer;

    public GamePanel() {
        timer = new Timer(50, this);
        timer.start();

        try {
            InputStream is = Main.class.getResourceAsStream("/ship.png");
            if (is == null) {
                throw new IOException("Image not found");
            }
            Image image = ImageIO.read(is);
            InputStream is2 = Main.class.getResourceAsStream("/ship1.png");
            if (is2 == null) {
                throw new IOException("Image not found");
            }
            Image image2 = ImageIO.read(is2);
            ship = new Ship(50, 200, 10, 0, image, new Weapon(), 10);
            ship1 = new Ship(300, 200, 10, 0, image2, new Weapon(), 10);
            ship.weapon.setEnemy(ship1);
            ship1.weapon.setEnemy(ship);
        } catch (IOException e) {
            System.out.println("Image not found");
            throw new RuntimeException(e);
        }

        setFocusable(true);
        addKeyListener(this);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK); // Устанавливаем цвет текста
        g.setFont(new Font("Arial", Font.BOLD, 18)); // Устанавливаем шрифт текста
        g.drawString("Player1: "+ ship.getHP() + " HP vs Player2: " + ship1.getHP() + " HP", 40, 40);
        if (ship.getHP() <= 0) {
            g.setColor(Color.RED); // Устанавливаем цвет текста
            g.setFont(new Font("Arial", Font.BOLD, 48)); // Устанавливаем шрифт текста
            g.drawString("Player2 WINS", 100, 200);
            timer.stop();
        }
        if (ship1.getHP() <= 0) {
            g.setColor(Color.RED); // Устанавливаем цвет текста
            g.setFont(new Font("Arial", Font.BOLD, 48)); // Устанавливаем шрифт текста
            g.drawString("Player1 WINS", 100, 200);
            timer.stop();
        }

        ship.draw(g);
        ship1.draw(g);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.direction.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.direction.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.direction.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.direction.left = true;
        }
        if (e.getKeyCode() == 87) {
            ship1.direction.up = true;
        }
        if (e.getKeyCode() == 83) {
            ship1.direction.down = true;
        }
        if (e.getKeyCode() == 68) {
            ship1.direction.right = true;
        }
        if (e.getKeyCode() == 65) {
            ship1.direction.left = true;
        }

        if(e.getKeyCode() == 17)
        {
            int x = ship.x + ship.image.getWidth(null) / 2;
            int y = ship.y + ship.image.getHeight(null) / 2;
            double angle = ship.angle;

            ship.weapon.bullets.add(new Bullet(x, y, 10, angle, null, 1));
        }

        if(e.getKeyCode() == 32)
        {
            int x = ship1.x + ship1.image.getWidth(null) / 2;
            int y = ship1.y + ship1.image.getHeight(null) / 2;
            double angle = ship1.angle;

            ship1.weapon.bullets.add(new Bullet(x, y, 10, angle, null, 1));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.direction.left = false;
        }
        if (e.getKeyCode() == 87) {
            ship1.direction.up = false;
        }
        if (e.getKeyCode() == 83) {
            ship1.direction.down = false;
        }
        if (e.getKeyCode() == 68) {
            ship1.direction.right = false;
        }
        if (e.getKeyCode() == 65) {
            ship1.direction.left = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ship != null) {
            ship.move();
        }
        if (ship1 != null) {
            ship1.move();
        }
        repaint();
    }
}
