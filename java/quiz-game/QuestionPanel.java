import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class QuestionPanel {
    public static JPanel createQuestionPanel(String levelTitle, String[][] questions, int[] correctAnswers, CardLayout layout, JPanel container) {
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBackground(new Color(0, 100, 0));
        
        JPanel questionArea = new JPanel(new GridBagLayout());
        questionArea.setBackground(new Color(0, 100, 0));
        
        JScrollPane scrollPane = new JScrollPane(questionArea);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(0, 100, 0));
        scrollPane.getViewport().setBackground(new Color(0, 100, 0));
        
        int[] questionIndex = {0};
        
        JLabel levelLabel = new JLabel(levelTitle);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 28));
        levelLabel.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("Back to Main Menu");
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        Consumer<Integer> updateQuestion = new Consumer<Integer>() {
            @Override
            public void accept(Integer index) {
                questionArea.removeAll();

                questionArea.add(levelLabel, createGridBagConstraints(0, 0, 2));
                
                JLabel questionLabel = new JLabel("<html>" + questions[index][0] + "</html>");
                questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
                questionLabel.setForeground(Color.WHITE);
                questionArea.add(questionLabel, createGridBagConstraints(0, 1, 2));

                String[] options = new String[questions[index].length - 1];
                System.arraycopy(questions[index], 1, options, 0, options.length);
                
                int lastRow = 1;
                
                for (int i = 0; i < options.length; i++) {
                    JButton optionButton = new JButton(options[i]);
                    int selectedIndex = i;
                    optionButton.addActionListener(e -> {
                        if (selectedIndex == correctAnswers[index]) {
                            JOptionPane.showMessageDialog(questionPanel, "Correct!");
                        } else {
                            JOptionPane.showMessageDialog(questionPanel, "Incorrect!");
                        }
                    });
                    questionArea.add(optionButton, createGridBagConstraints(0, 2 + i, 2));
                    lastRow = 2 + i;
                }
                
                JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                navigationPanel.setBackground(new Color(0, 100, 0));
                navigationPanel.add(backButton);
                navigationPanel.add(previousButton);
                navigationPanel.add(nextButton);

                questionArea.add(navigationPanel, createGridBagConstraints(0, lastRow + 1, 2));
                
                questionPanel.revalidate();
                questionPanel.repaint();
            }
        };

        backButton.addActionListener(e -> layout.show(container, "page1"));
        
        previousButton.addActionListener(e -> {
            if (questionIndex[0] > 0) {
                questionIndex[0]--;
                updateQuestion.accept(questionIndex[0]);
            }
        });
        
        nextButton.addActionListener(e -> {
            if (questionIndex[0] < questions.length - 1) {
                questionIndex[0]++;
                updateQuestion.accept(questionIndex[0]);
            }
        });
        
        updateQuestion.accept(questionIndex[0]);
        
        questionPanel.add(scrollPane, BorderLayout.CENTER);
        
        return questionPanel;
    }
    
    private static GridBagConstraints createGridBagConstraints(int x, int y, int gridWidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
}