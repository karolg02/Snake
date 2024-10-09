import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;
    SettingsPanel settingsPanel;

    static final int SCREEN_WIDTH = 640;
    static final int SCREEN_HEIGHT = 640;

    public GameFrame() {
        // First settings
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        // Creating MenuPanel and SettingsPanel
        menuPanel = new MenuPanel(this);
        settingsPanel = new SettingsPanel(this);

        // Show the menu on startup
        this.add(menuPanel);
        this.setVisible(true);
    }

    // This method shows the game panel
    public void showGamePanel() {
        this.getContentPane().removeAll();
        gamePanel = new GamePanel(this); // Initialize a new game

        // Apply the current settings from SettingsPanel to GamePanel
        settingsPanel.applySettingsToGamePanel(gamePanel);

        this.add(gamePanel);
        this.revalidate();
        this.repaint();
        gamePanel.requestFocusInWindow();
    }

    // This method shows the menu
    public void showMenuPanel() {
        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.revalidate();
        this.repaint();
    }

    // This method shows the settings menu
    public void showSettingsMenu() {
        this.getContentPane().removeAll();
        this.add(settingsPanel);
        this.revalidate();
        this.repaint();
    }
}