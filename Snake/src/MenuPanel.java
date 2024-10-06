import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    GameFrame gameFrame;

    public MenuPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton startButton = new JButton("Start");
        JButton settingsButton = new JButton("Settings");

        // Start Button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.showGamePanel();  // Starts game
            }
        });

        // Settings Button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Settings coming soon!");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(startButton, gbc);

        gbc.gridy = 1;
        this.add(settingsButton, gbc);
    }
}
