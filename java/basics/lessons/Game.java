import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 400;
    private final int HEIGHT = 600;

    private Rectangle player;
    private final int PLAYER_WIDTH = 40;
    private final int PLAYER_HEIGHT = 20;
    private int playerSpeed = 10;

    private ArrayList<Rectangle> enemies;
    private int enemySpeed = 4;
    private Timer timer;
    private Random rand;

    private boolean gameOver = false;

    public Game() {
        JFrame frame = new JFrame("Avoid the Enemies!");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(this);

        player = new Rectangle(WIDTH / 2 - PLAYER_WIDTH / 2, HEIGHT - 50, PLAYER_WIDTH, PLAYER_HEIGHT);
        enemies = new ArrayList<>();
        rand = new Random();

        timer = new Timer(20, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLUE);
        g.fillRect(player.x, player.y, player.width, player.height);

        g.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        }

        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over!", 100, HEIGHT / 2);
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (rand.nextInt(20) == 0) {
            int x = rand.nextInt(WIDTH - 30);
            enemies.add(new Rectangle(x, 0, 30, 30));
        }

        Iterator<Rectangle> it = enemies.iterator();
        while (it.hasNext()) {
            Rectangle enemy = it.next();
            enemy.y += enemySpeed;

            if (enemy.intersects(player)) {
                gameOver = true;
            }

            if (enemy.y > HEIGHT) {
                it.remove();
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameOver) {
            if (key == KeyEvent.VK_LEFT && player.x > 0) {
                player.x -= playerSpeed;
            } else if (key == KeyEvent.VK_RIGHT && player.x < WIDTH - PLAYER_WIDTH) {
                player.x += playerSpeed;
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new Game();
    }
}
