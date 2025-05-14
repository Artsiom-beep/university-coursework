import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateYourLevelFirstStage {
    private static final String FILE_NAME = "questions.txt";

    public CreateYourLevelFirstStage() {
        JFrame frame = new JFrame("Create Your Level");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0, 0, 50));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Create Your Level");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));

        // Input fields
        JTextField questionField = createInputField(500);
        JTextField answerField = createInputField(500);

        mainPanel.add(wrapFieldWithLabel("Question: ", questionField));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(wrapFieldWithLabel("Correct Answer: ", answerField));
        mainPanel.add(Box.createVerticalStrut(30));

        // Buttons
        JButton addButton = createButton("Add Question", new Color(100, 200, 100));
        JButton backButton = createButton("Back to Main", new Color(100, 150, 250));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel);

        // Button actions
        backButton.addActionListener(e -> {
            frame.dispose();
            new Main(); // Assuming you have a class Main for the main menu
        });

        addButton.addActionListener(e -> {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (!question.isEmpty() && !answer.isEmpty()) {
                saveToFile(question, answer);
                JOptionPane.showMessageDialog(frame,
                        "Question saved to file!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                questionField.setText("");
                answerField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Please enter both question and answer!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Creates a text input field
    private JTextField createInputField(int width) {
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(width, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    // Wraps label and input field into a panel
    private JPanel wrapFieldWithLabel(String labelText, JTextField field) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(field);

        return panel;
    }

    // Creates a styled button
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    // Saves question and answer to a text file
    private void saveToFile(String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("[%s]%nQ: %s%nA: %s%n%n", timestamp, question, answer));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error saving question to file!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
