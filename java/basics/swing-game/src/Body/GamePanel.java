package Body;
import javax.swing.*;
import javax.swing.text.ElementIterator;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


class GamePanel extends JPanel implements ActionListener, KeyListener {

int WIDTH, HEIGHT;
Rectangle player;
int playerWidth, playerHeight;
int playerX, playerY;
float speed = 10;
ArrayList<Rectangle> enemies;
ArrayList<Rectangle> Smartenemy;
ArrayList<Ellipse2D> apples;
Random rand = new Random();
Timer timer = new Timer(10, this);
boolean left, right, up, down;
boolean gameOver = false;
boolean gameFinished = false;
boolean gameStarted = true;
int welcomeTimer = 300;
int score = 0;
int GameScore = 5;
private JButton restartButton;
public JButton level2Button;


public GamePanel(int width, int height, JButton restartButton, JButton level2Button) {
    setDoubleBuffered(true);
    this.restartButton = restartButton;
    this.WIDTH = width;
    this.HEIGHT = height;
    this.level2Button = level2Button;

    enemies = new ArrayList<>();
    Smartenemy = new ArrayList<>();
    apples = new ArrayList<>();

    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(this);

    playerWidth = WIDTH / 50;
    playerHeight = WIDTH / 50;

    int startX = WIDTH / 2 - playerWidth / 2;
    int startY = HEIGHT / 2 - playerHeight / 2;
    player = new Rectangle(startX, startY, playerWidth, playerHeight);

    timer.start();
}


public void actionPerformed(ActionEvent e){


    if (gameStarted) {
        welcomeTimer--;
        if (welcomeTimer <= 0) {
            gameStarted = false;
        }
        repaint();
        return;
    }


    if (gameOver) {
        return;
    }

    if (left && player.x > 0) player.x -= speed * 1.2f;
    if (right && player.x < WIDTH - playerWidth * 2) player.x += speed * 1.2f;
    if (up && player.y > 0) player.y -= speed * 1.2f;
    if (down && player.y < HEIGHT - playerHeight * 3) player.y += speed * 1.2f;

    player.x = Math.max(0, Math.min(player.x, WIDTH - playerWidth));
    player.y = Math.max(0, Math.min(player.y, HEIGHT - playerHeight));

    if (rand.nextInt(10) == 0) {
        int enemySize = WIDTH / 27;
        enemies.add(new Rectangle(rand.nextInt(WIDTH - enemySize), 0, enemySize, enemySize));
    }

    if (rand.nextInt(18) == 0) {
        int smartEnemySize = WIDTH / 40;
        Smartenemy.add(new Rectangle(rand.nextInt(WIDTH - smartEnemySize), 0, smartEnemySize, smartEnemySize));
    }

    if(rand.nextInt(100) == 0){
        int appleSize = WIDTH / 50;
        apples.add(new Ellipse2D.Float(rand.nextInt(WIDTH - appleSize), rand.nextInt(HEIGHT - appleSize), appleSize, appleSize));
    }

    for (int i = 0; i < enemies.size(); i++) {
        Rectangle enemy = enemies.get(i);
        enemy.y += speed;
        enemy.x += rand.nextInt(15) - 7;

        if (enemy.y > HEIGHT) {
            enemies.remove(i);
            i--;
        } else if (player.intersects(enemy)) {
            gameOver = true;
            timer.stop();
            restartButton.setVisible(true);
            level2Button.setVisible(true);
        }
    }

    for (int i = 0; i < Smartenemy.size(); i++) {
        Rectangle smartEnemy = Smartenemy.get(i);
        smartEnemy.y += speed / 2;

        if (smartEnemy.x < player.x) {
            smartEnemy.x += rand.nextInt(3);  
        } else if (smartEnemy.x > player.x) {
            smartEnemy.x -= rand.nextInt(3);
        } else {
            smartEnemy.x += rand.nextInt(3);
        }

        if (smartEnemy.y > HEIGHT) {
            Smartenemy.remove(i);
            i--;
        } else if (player.intersects(smartEnemy)) {
            gameOver = true;
            timer.stop();
            restartButton.setVisible(true);
            level2Button.setVisible(true);
        }
    }

    for(int i = 0; i < apples.size(); i++) {
        Ellipse2D apple = apples.get(i);
        if (player.intersects(apple.getBounds())) {
            score++;
            apples.remove(i);
            i--;
        }
    }

    if(score == GameScore){
        gameFinished = true;
        restartButton.setVisible(true);
        level2Button.setVisible(true);
        timer.stop();
    }

    repaint();
}

protected void paintComponent(Graphics g) {

    
        super.paintComponent(g);

         Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);


        if (gameStarted) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        g2d.drawString("GET READY!", WIDTH / 2 - WIDTH/9, HEIGHT / 2);
        g2d.drawString("Collect 5 apples to win!", WIDTH / 2 - WIDTH / 6, HEIGHT / 2 + HEIGHT / 10);
        return;
    }

    if(gameFinished) {
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        g2d.drawString("You Win!", WIDTH / 2 - WIDTH/9, HEIGHT / 2);
    }


        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            g2d.drawString("Game Over. Your Score: " + score, WIDTH / 2 - WIDTH / 6, HEIGHT / 2);

            return;
        }

        g2d.setColor(Color.CYAN);
        g2d.fill(player);

        g2d.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g2d.fill(enemy);
        }

        g2d.setColor(Color.YELLOW);
        for (Rectangle smartEnemy : Smartenemy) {
            g2d.fill(smartEnemy);
        }

        g2d.setColor(Color.GREEN);
        for (Ellipse2D apple : apples) {
            g2d.fill(apple);
        }

    }



public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_A) {
        left = true;
    }
    if (key == KeyEvent.VK_D) {
        right = true;
    }
    if (key == KeyEvent.VK_W) {
        up = true;
    }
    if (key == KeyEvent.VK_S) {
        down = true;
    }
    
}


public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_A) {
        left = false;
    }
    if (key == KeyEvent.VK_D) {
        right = false;
    }
    if (key == KeyEvent.VK_W) {
        up = false;
    }
    if (key == KeyEvent.VK_S) {
        down = false;
    }



}

public void keyTyped(KeyEvent e) {
    // Not used
}


public void restartGame() {
    score = 0;
    gameOver = false;
    gameFinished = false;
    gameStarted = true;
    welcomeTimer = 300;

    enemies.clear();
    Smartenemy.clear();
    apples.clear();

    player.x = WIDTH / 2 - playerWidth / 2;
    player.y = HEIGHT / 2 - playerHeight / 2;

    restartButton.setVisible(false); 
    level2Button.setVisible(false);
    
    if (!timer.isRunning()) {
        timer.start();
    }
    
    this.requestFocusInWindow();
    repaint();
}


public void levelTwo(){
    


}




    
}




