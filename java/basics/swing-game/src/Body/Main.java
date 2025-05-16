package Body;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;


        JFrame Menu = new JFrame ("Menu");
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.setLayout(new BorderLayout());
        Menu.setSize(width, height);

        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(width/2, height/10));

        JButton survButton = new JButton("Survival Mode");
        survButton.setPreferredSize(new Dimension(width/2, height/10));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Dodge enemies attack!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 


        JLabel titleLabel2 = new JLabel("This mini-game helps you to train your reflexes");
        titleLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.setPreferredSize(new Dimension(width / 4, height / 15)); 
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        survButton.setPreferredSize(new Dimension(width / 4, height / 15)); 
        survButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(height / 6)); 
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(height / 20));
        centerPanel.add(titleLabel2);
        centerPanel.add(Box.createVerticalStrut(height / 20)); 
        centerPanel.add(startButton);
        centerPanel.add(Box.createVerticalStrut(height / 20));
        centerPanel.add(survButton);



        Menu.add(centerPanel, BorderLayout.CENTER);
        Menu.setLocationRelativeTo(null);
        Menu.setVisible(true);



        startButton.addActionListener(ev -> {

            Menu.dispose(); 
            JFrame frame = new JFrame("Dodge enemies attack!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout()); 

            

            JButton restartButton = new JButton("Restart");
            restartButton.setVisible(false);

            JButton levelTwoButton = new JButton("Level 2");
            levelTwoButton.setVisible(false);

            GamePanel gamePanel = new GamePanel(width, height, restartButton, levelTwoButton);
            GamePanelLevel2 gamePanelLevel2 = new GamePanelLevel2(width, height, restartButton, levelTwoButton);

            frame.add(gamePanel, BorderLayout.CENTER);   
            frame.add(restartButton, BorderLayout.SOUTH); 
            frame.add(levelTwoButton, BorderLayout.NORTH);

            restartButton.addActionListener(e -> {
                gamePanel.restartGame();
                           
            });

            levelTwoButton.addActionListener(e -> {
                
                frame.remove(gamePanel);
                frame.add(gamePanelLevel2, BorderLayout.CENTER);
                gamePanelLevel2.requestFocusInWindow();
                levelTwoButton.setVisible(false);
                restartButton.setVisible(false);
                

            });

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
