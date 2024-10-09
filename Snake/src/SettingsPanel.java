import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    GameFrame gameFrame;
    JCheckBox gridCheckbox;
    JSlider speedSlider;

    boolean defaultBoardLines = true;
    int defaultDelay = 50;

    public SettingsPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        // Layout settings
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add empty border for padding
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // top, left, bottom, right

        // Grid Checkbox
        gridCheckbox = new JCheckBox("Show Grid Lines");
        gridCheckbox.setSelected(defaultBoardLines);
        gridCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the checkbox
        gridCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultBoardLines = gridCheckbox.isSelected();
            }
        });
        this.add(gridCheckbox);

        // Spacer to add some vertical space
        this.add(Box.createRigidArea(new Dimension(0, 20))); // Adds 20px vertical space

        // Slider for speed control
        JLabel speedLabel = new JLabel("Game Speed:");
        speedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the label
        this.add(speedLabel);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, defaultDelay);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the slider
        speedSlider.addChangeListener(e -> defaultDelay = speedSlider.getValue());
        this.add(speedSlider);

        // Spacer to add some vertical space
        this.add(Box.createRigidArea(new Dimension(0, 20))); // Adds 20px vertical space

        // Back button to go to the main menu
        JButton backButton = new JButton("Back to Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.showMenuPanel();
            }
        });
        this.add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);  // Set the background color to white
    }

    public void applySettingsToGamePanel(GamePanel gamePanel) {
        gamePanel.boardLines = defaultBoardLines;
        gamePanel.setDelay(defaultDelay);
    }
}
