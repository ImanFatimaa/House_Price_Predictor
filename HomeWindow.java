package codeclause;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow extends JFrame {
    public HomeWindow() {
        setTitle("Welcome to House Price Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        welcomePanel.setBackground(new Color(44, 62, 80)); // Dark background color

        JLabel titleLabel = new JLabel("House Price Predictor", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); // Fun font and larger size
        titleLabel.setForeground(new Color(236, 240, 241)); // Light font color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel("Press 'Next' to reach Main Page ", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Larger font size
        infoLabel.setForeground(new Color(189, 195, 199)); // Light font color
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 22)); // Larger font size
        nextButton.setBackground(new Color(52, 152, 219)); // Blue button color
        nextButton.setForeground(Color.WHITE); // White text color
        nextButton.setFocusPainted(false); // Remove focus painting on button
        nextButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30)); // Padding inside the button
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainWindow();
            }
        });

        welcomePanel.add(titleLabel);
        welcomePanel.add(Box.createVerticalStrut(20));
        welcomePanel.add(infoLabel);
        welcomePanel.add(Box.createVerticalStrut(50));
        welcomePanel.add(nextButton);

        add(welcomePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openMainWindow() {
        new MainWindow().setVisible(true); // Opens the main window
        dispose(); // Closes the current home window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeWindow();
            }
        });
    }
}
