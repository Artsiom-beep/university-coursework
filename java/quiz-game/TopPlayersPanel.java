import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class TopPlayersPanel {

    public static JPanel createTopPlayersPanel(CardLayout layout, JPanel container) {
    String[] columnNames = {"Name", "Score"};
    Object[][] topPlayers = {
        {"Alice", 95},
        {"Bob", 90},
        {"Charlie", 85},
        {"Diana", 80},
        {"Ethan", 75}
    };

    JPanel topPlayersPanel = new JPanel(new BorderLayout());
    Color greenColor = new Color(0, 100, 0);
    topPlayersPanel.setBackground(greenColor);

    
    JTable table = new JTable(topPlayers, columnNames) {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);
            c.setBackground(greenColor);
            c.setForeground(Color.WHITE);
            return c;
        }
    };


    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    
   
    table.getColumn("Name").setCellRenderer(centerRenderer);
    table.getColumn("Score").setCellRenderer(centerRenderer);
    
    table.setFont(new Font("Arial", Font.PLAIN, 18));
    table.setRowHeight(30);
    table.setEnabled(false);
    table.setForeground(Color.WHITE);
    table.setBackground(greenColor);
    table.setGridColor(new Color(50, 150, 50));
    
    
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(0, 80, 0));
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Arial", Font.BOLD, 18));

    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.getViewport().setBackground(greenColor);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    
    topPlayersPanel.add(scrollPane, BorderLayout.CENTER);

    
    JButton backFromTop = new JButton("Back");
    backFromTop.setBackground(new Color(200, 100, 50));
    backFromTop.setForeground(Color.WHITE);
    backFromTop.setFont(new Font("Arial", Font.BOLD, 16));
    topPlayersPanel.add(backFromTop, BorderLayout.SOUTH);

    backFromTop.addActionListener(e -> layout.show(container, "page1"));

   JButton backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.BOLD, 16));
    backButton.setBackground(new Color(200, 100, 50));
    backButton.setForeground(Color.WHITE);
    backButton.addActionListener(e -> layout.show(container, "page1"));
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(greenColor);
    buttonPanel.add(backButton);
    
    topPlayersPanel.add(buttonPanel, BorderLayout.SOUTH);

    return topPlayersPanel;
}
}
        