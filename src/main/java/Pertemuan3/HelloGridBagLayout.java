package Pertemuan3;

import java.awt.*;
import javax.swing.*;

public class HelloGridBagLayout extends JFrame {

    public HelloGridBagLayout() {
        // Set default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create header label
        JLabel headerLabel = new JLabel("Layout in action: GridBagLayout", JLabel.CENTER);

        // Create control panel with FlowLayout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Create main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(new Dimension(300, 300));  // Set panel size

        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);  // Apply layout
        GridBagConstraints gbc = new GridBagConstraints();

        // Add buttons with GridBagConstraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JButton("Button 1"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Button 2"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JButton("Button 3"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JButton("Button 4"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;  // Span across two columns
        panel.add(new JButton("Button 5"), gbc);

        // Add panel to control panel
        controlPanel.add(panel);

        // Set layout for the frame and add components
        this.setLayout(new GridLayout(2, 1));
        this.add(headerLabel);
        this.add(controlPanel);

        // Set frame size
        this.setSize(400, 400);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloGridBagLayout h = new HelloGridBagLayout();
                h.setVisible(true);
            }
        });
    }
}
