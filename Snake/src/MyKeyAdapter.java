import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {

    GamePanel gamePanel;

    // Constructor referenced to GamePanel
    public MyKeyAdapter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // left
                if (gamePanel.direction != 'R') {
                    gamePanel.direction = 'L';
                }
                break;
            case KeyEvent.VK_D: // right
                if (gamePanel.direction != 'L') {
                    gamePanel.direction = 'R';
                }
                break;
            case KeyEvent.VK_W: // up
                if (gamePanel.direction != 'D') {
                    gamePanel.direction = 'U';
                }
                break;
            case KeyEvent.VK_S: // down
                if (gamePanel.direction != 'U') {
                    gamePanel.direction = 'D';
                }
                break;
            case KeyEvent.VK_ENTER: // restart
                if (!gamePanel.running) {
                    gamePanel.resetGame();
                }
                break;
        }
    }
}
