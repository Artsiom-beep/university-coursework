import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Engly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(new Color(0, 0, 50));

        CardLayout layout = new CardLayout();
        JPanel panel1 = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;


        JPanel container = new JPanel(layout);
        panel1.setBackground(new Color(0, 100, 0));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel Welcome = new JLabel("Welcome to Engly!");
        Welcome.setFont(new Font("Arial", Font.BOLD, 28));
        Welcome.setForeground(Color.WHITE);
        panel1.add(Welcome, gbc);

        gbc.gridy = 1;
        JLabel instruction = new JLabel("Please select an option:");
        instruction.setFont(new Font("Arial", Font.BOLD, 16));
        instruction.setForeground(Color.WHITE);
        panel1.add(instruction, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        JButton chooseTaskType = new JButton("Choose Task Type");
        chooseTaskType.setBackground(new Color(100, 150, 250));
        chooseTaskType.setForeground(Color.WHITE);
        panel1.add(chooseTaskType, gbc);


        gbc.gridy = 3;
        JButton create = new JButton("Create your own level!");
        create.setBackground(new Color(200, 150, 50));
        create.setForeground(Color.WHITE);
        panel1.add(create, gbc);

        create.addActionListener(e -> {
                new CreateYourLevelFirstStage(); 
        });

       
        gbc.gridy = 4;
        JButton top = new JButton("Top Players");
        top.setBackground(new Color(150, 50, 200));
        top.setForeground(Color.WHITE);
        panel1.add(top, gbc);

        gbc.gridy = 5;
        JLabel label1 = new JLabel("Our policy");
        label1.setForeground(Color.WHITE);
        panel1.add(label1, gbc);

        gbc.gridy = 6;
        JButton goToPage2 = new JButton("Policy");
        goToPage2.setBackground(new Color(0, 100, 0));
        goToPage2.setForeground(Color.WHITE);
        panel1.add(goToPage2, gbc);


        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(new Color(0, 100, 0));

        gbc.gridy = 0;
        JLabel policyTitle = new JLabel("Policy");
        policyTitle.setForeground(Color.WHITE);
        panel2.add(policyTitle, gbc);

        gbc.gridy = 1;
        JLabel policy = new JLabel("<html>" +
                "Old McDonald had a duck,<br>" +
                "E-I-E-I-O<br>" +
                "And on his farm he had a duck,<br>" +
                "E-I-E-I-O<br>" +
                "With a quack-quack here,<br>" +
                "And a quack-quack there,<br>" +
                "Here a quack, there a quack,<br>" +
                "Everywhere a quack-quack!<br><br>" +
                "Old McDonald had a duck,<br>" +
                "E-I-E-I-O!" +
                "</html>");
        policy.setFont(new Font("Arial", Font.PLAIN, 20));
        policy.setForeground(Color.WHITE);
        panel2.add(policy, gbc);

        gbc.gridy = 2;
        JButton back = new JButton("Back");
        back.setBackground(new Color(200, 50, 50));
        back.setForeground(Color.WHITE);
        panel2.add(back, gbc);


        JPanel panelTaskTypes = new JPanel(new GridBagLayout());
        panelTaskTypes.setBackground(new Color(0, 100, 0));

        gbc.gridy = 0;
        JLabel selectTypeLabel = new JLabel("Select task type:");
        selectTypeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        selectTypeLabel.setForeground(Color.WHITE);
        panelTaskTypes.add(selectTypeLabel, gbc);

        gbc.gridy = 1;
        JButton abcdButton = new JButton("ABCD");
        abcdButton.setBackground(new Color(100, 150, 250));
        abcdButton.setForeground(Color.WHITE);
        panelTaskTypes.add(abcdButton, gbc);

        gbc.gridy = 2;
        JButton backToMain2 = new JButton("Back");
        backToMain2.setBackground(new Color(200, 100, 50));
        backToMain2.setForeground(Color.WHITE);
        panelTaskTypes.add(backToMain2, gbc);

        JPanel panel3 = new JPanel(new GridBagLayout());
        panel3.setBackground(new Color(0, 100, 0));

        gbc.gridy = 0;
        JButton chooseLevel = new JButton("Choose level:");
        chooseLevel.setBackground(new Color(20, 150, 50));
        chooseLevel.setForeground(Color.WHITE);
        panel3.add(chooseLevel, gbc);

        gbc.gridy = 1;
        JButton a1Button = new JButton("A1-A2");
        a1Button.setBackground(new Color(50, 200, 50));
        a1Button.setForeground(Color.WHITE);
        panel3.add(a1Button, gbc);

        gbc.gridy = 2;
        JButton b1Button = new JButton("B1-B2");
        b1Button.setBackground(new Color(50, 50, 200));
        b1Button.setForeground(Color.WHITE);
        panel3.add(b1Button, gbc);

        gbc.gridy = 3;
        JButton c1Button = new JButton("C1-C2");
        c1Button.setBackground(new Color(200, 50, 50));
        c1Button.setForeground(Color.WHITE);
        panel3.add(c1Button, gbc);

        gbc.gridy = 4;
        JButton backToTaskTypes = new JButton("Back");
        backToTaskTypes.setBackground(new Color(200, 100, 50));
        backToTaskTypes.setForeground(Color.WHITE);
        panel3.add(backToTaskTypes, gbc);


        


        JPanel a1a2 = QuestionPanel.createQuestionPanel("A1-A2 Level", QuestionData.A1A2_QUESTIONS, 
                QuestionData.A1A2_ANSWERS, layout, container);

        JPanel b1b2 = QuestionPanel.createQuestionPanel("B1-B2 Level", QuestionData.B1B2_QUESTIONS, 
                QuestionData.B1B2_ANSWERS, layout, container);

        JPanel c1c2 = QuestionPanel.createQuestionPanel("C1-C2 Level", QuestionData.C1C2_QUESTIONS, 
                QuestionData.C1C2_ANSWERS, layout, container);

        JPanel topPlayersPanel = TopPlayersPanel.createTopPlayersPanel(layout, container);


        container.add(panel1, "page1");
        container.add(panel2, "page2");
        container.add(panelTaskTypes, "taskTypes");
        container.add(panel3, "chooseLevel");
        container.add(a1a2, "A1-A2");
        container.add(b1b2, "B1-B2");
        container.add(c1c2, "C1-C2");
        container.add(topPlayersPanel, "topPlayers");

        
        goToPage2.addActionListener(e -> layout.show(container, "page2"));
        back.addActionListener(e -> layout.show(container, "page1"));
        chooseTaskType.addActionListener(e -> layout.show(container, "taskTypes"));
        backToMain2.addActionListener(e -> layout.show(container, "page1"));
        abcdButton.addActionListener(e -> layout.show(container, "chooseLevel"));
        backToTaskTypes.addActionListener(e -> layout.show(container, "taskTypes"));

        a1Button.addActionListener(e -> layout.show(container, "A1-A2"));
        b1Button.addActionListener(e -> layout.show(container, "B1-B2"));
        c1Button.addActionListener(e -> layout.show(container, "C1-C2"));
        
        top.addActionListener(e -> layout.show(container, "topPlayers"));

        frame.add(container);
        frame.setVisible(true);
    }
}
