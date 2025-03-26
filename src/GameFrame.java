import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(int size) throws HeadlessException {
        setBounds(100, 100, size, size);
        gamePanel = new GamePanel();
        add(gamePanel);
        setFocusable(true);
        addKeyListener(gamePanel);
        setVisible(true);
    }
}
