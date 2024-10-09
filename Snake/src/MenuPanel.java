import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    GameFrame gameFrame;
    private Image backgroundImg;

    public MenuPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        this.setLayout(new GridBagLayout());
        backgroundImg = new ImageIcon(getClass().getResource("/assets/snake.jpg")).getImage();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton startButton = new JButton("Start");
        JButton shopButton = new JButton("Shop");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        // Start Button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.showGamePanel();  // Starts game
            }
        });

        shopButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Shop coming soon!");
            }
        });

        // Settings Button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.showSettingsMenu();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // This will terminate the application
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(startButton, gbc);

        gbc.gridy = 1;
        this.add(shopButton, gbc);
        gbc.gridy = 2;
        this.add(settingsButton, gbc);
        gbc.gridy = 3;
        this.add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
    }
}
