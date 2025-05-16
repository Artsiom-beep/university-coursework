package Body;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener {

    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean upPressed = false;
    public boolean downPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
