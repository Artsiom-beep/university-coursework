package Body;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Ellipse2D;

public class GamePanelLevel2 extends JPanel implements ActionListener {

    int WIDTH, HEIGHT;
    Rectangle player;
    int playerWidth, playerHeight;
    ArrayList<Rectangle> enemies;
    ArrayList<Rectangle> Smartenemy;
    ArrayList<Ellipse2D> apples;
    ArrayList<Rectangle> bullets;
    ArrayList<Rectangle> hurracaine;
    Random rand = new Random();
    Timer timer = new Timer(10, this);
    JButton restartButton;
    JButton level2Button;

    PlayerKeyListener keyListener;

    public GamePanelLevel2(int width, int height, JButton restartButton, JButton level2Button) {
        setDoubleBuffered(true);

        this.restartButton = restartButton;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.level2Button = level2Button;

        enemies = new ArrayList<>();
        Smartenemy = new ArrayList<>();
        apples = new ArrayList<>();
        bullets = new ArrayList<>();
        hurracaine = new ArrayList<>();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        playerWidth = WIDTH / 100;
        playerHeight = WIDTH / 100;

        int startX = WIDTH / 2 - playerWidth / 2;
        int startY = HEIGHT / 2 - playerHeight / 2;
        player = new Rectangle(startX, startY, playerWidth, playerHeight);

        keyListener = new PlayerKeyListener();
        this.addKeyListener(keyListener);

        timer.start();

        SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
    }

    public void actionPerformed(ActionEvent e) {
        int speed = 20;

         if (keyListener.leftPressed && player.x > 0) {
            player.x -= speed;
        }
        if (keyListener.rightPressed && player.x + player.width < WIDTH) {
            player.x += speed;
        }
        if (keyListener.upPressed && player.y > 0) {
            player.y -= speed;
        }
        if (keyListener.downPressed && player.y + player.height < HEIGHT) {
            player.y += speed;
        }


        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
         g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
         g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);


        g.setColor(Color.GREEN);
        g.fillRect(player.x, player.y, player.width, player.height);
    }
}
