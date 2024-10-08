import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    //40px dif on bar, if it isn't the first screen
    static final int SCREEN_WIDTH = 620;
    static final int SCREEN_HEIGHT = 580;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int DELAY = 50;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    boolean boardLines = true;
    Timer timer;
    Random random;

    InitialGameState initialState;
    GameFrame gameFrame; // Dodajemy referencję do GameFrame

    public GamePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this, gameFrame)); // Przekazujemy gameFrame do MyKeyAdapter
        startGame();

        initialState = new InitialGameState(x, y, bodyParts, applesEaten, direction);
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void setDelay(int delay) {
        this.DELAY = delay;
        if (timer != null) {
            timer.setDelay(delay);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if(boardLines){
            for(int i = 0; i < 640/UNIT_SIZE; i++){
                g.setColor(Color.GRAY);
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, 640);
                g.drawLine(0, i*UNIT_SIZE, 640, i*UNIT_SIZE);
            }
        }

        if (running) {

            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    if(applesEaten < 10){
                        g.setColor(Color.GREEN);
                    }else {
                        g.setColor(Color.YELLOW);
                    }
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    if(applesEaten < 10){
                        g.setColor(new Color(45, 180, 0));
                    }
                    if(applesEaten > 10){
                        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    }
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        // check if head touches right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        // check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        // check if game is running
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        // Game over Text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        // Press Enter to play again Text
        g.setColor(Color.gray);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press Enter to play again!", 200, (SCREEN_HEIGHT / 3) * 2);
    }

    public void resetGame() {
        bodyParts = initialState.initialBodyParts;
        applesEaten = initialState.initialApplesEaten;
        direction = initialState.initialDirection;

        System.arraycopy(initialState.initialX, 0, x, 0, initialState.initialBodyParts);
        System.arraycopy(initialState.initialY, 0, y, 0, initialState.initialBodyParts);

        newApple();
        running = true;

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(DELAY, this);
        timer.start();

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
}
