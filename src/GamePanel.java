import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Ship orc2;
    public Timer timer;

    public GamePanel() {
        timer = new Timer(50, this);
        timer.start();

        try {
            Image image = ImageIO.read(new File("C:\\Users\\xiaom\\Downloads\\Telegram Desktop\\untitled2 (2)\\GameJava\\src\\orc.png"));
            orc = new Ship(50, 50, 10, 0, image, new Weapon(), 2);
            Image image2 = ImageIO.read(new File("C:\\Users\\xiaom\\Downloads\\Telegram Desktop\\untitled2 (2)\\GameJava\\src\\orc2.png"));
            orc2 = new Ship(200, 200, 10, 0, image2, new Weapon(), 2);
            orc.weapon.setEnemy(orc2);
            orc2.weapon.setEnemy(orc);
        } catch (IOException e) {
            System.out.println("Облом.");
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
        g.drawString("P1: "+ orc.getHP() + " vs P2: " + orc2.getHP(), 40, 40);
        if (orc.getHP() <= 0) {
            g.setColor(Color.RED); // Устанавливаем цвет текста
            g.setFont(new Font("Arial", Font.BOLD, 96)); // Устанавливаем шрифт текста
            g.drawString("P2 WINS", 200, 200);
            timer.stop();
        }
        if (orc2.getHP() <= 0) {
            g.setColor(Color.RED); // Устанавливаем цвет текста
            g.setFont(new Font("Arial", Font.BOLD, 96)); // Устанавливаем шрифт текста
            g.drawString("P1 WINS", 200, 200);
            timer.stop();
        }

        orc.draw(g);
        orc2.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc.direction.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc.direction.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc.direction.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc.direction.left = true;
        }
        if (e.getKeyCode() == 87) {
            orc2.direction.up = true;
        }
        if (e.getKeyCode() == 83) {
            orc2.direction.down = true;
        }
        if (e.getKeyCode() == 68) {
            orc2.direction.right = true;
        }
        if (e.getKeyCode() == 65) {
            orc2.direction.left = true;
        }

        if(e.getKeyCode() == 17)
        {
            int x = orc.x + orc.image.getWidth(null) / 2;
            int y = orc.y + orc.image.getHeight(null) / 2;
            double angle = orc.angle;

            orc.weapon.bullets.add(new Bullet(x, y, 10, angle, null, 1));
        }

        if(e.getKeyCode() == 32)
        {
            int x = orc2.x + orc2.image.getWidth(null) / 2;
            int y = orc2.y + orc2.image.getHeight(null) / 2;
            double angle = orc2.angle;

            orc2.weapon.bullets.add(new Bullet(x, y, 10, angle, null, 1));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc.direction.left = false;
        }
        if (e.getKeyCode() == 87) {
            orc2.direction.up = false;
        }
        if (e.getKeyCode() == 83) {
            orc2.direction.down = false;
        }
        if (e.getKeyCode() == 68) {
            orc2.direction.right = false;
        }
        if (e.getKeyCode() == 65) {
            orc2.direction.left = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (orc != null) {
            orc.move();
        }
        if (orc2 != null) {
            orc2.move();
        }
        repaint();
    }
}
