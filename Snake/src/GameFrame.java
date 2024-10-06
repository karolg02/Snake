import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;

    static final int SCREEN_WIDTH = 640;
    static final int SCREEN_HEIGHT = 640;

    public GameFrame() {
        //First settings
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        // Creating MenuPanel
        menuPanel = new MenuPanel(this);
        this.add(menuPanel);  //
        this.setVisible(true);
    }

    // This method shows game
    public void showGamePanel() {
        this.getContentPane().removeAll();
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.revalidate();
        this.repaint();
        gamePanel.requestFocusInWindow();
    }

    //this method shows menu
    public void showMenuPanel() {
        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.revalidate();
        this.repaint();
    }
}
