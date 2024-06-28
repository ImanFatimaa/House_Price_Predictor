package codeclause;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private JTextField bedroomsField;
    private JTextField squareFootageField;
    private JLabel resultLabel;
    private JButton predictButton, homeButton, previousButton, forwardButton;
    private LinearRegression model;
    private String lastPrediction = "Predicted Price: $0.00";

    public MainWindow() {
        // Create the linear regression model
        model = new LinearRegression();

        // Load the dataset and train the model
        List<House> dataset = loadDataset();
        model.train(dataset);

        // Set up the GUI components
        setTitle("House Price Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(44, 62, 80)); // Dark background color

        JLabel bedroomsLabel = new JLabel("Bedrooms:");
        bedroomsLabel.setBounds(10, 137, 190, 67);
        bedroomsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        bedroomsLabel.setForeground(new Color(236, 240, 241)); // Light font color
        bedroomsField = new JTextField();
        bedroomsField.setBounds(10, 215, 367, 56);
        bedroomsField.setFont(new Font("Arial", Font.PLAIN, 18));
        bedroomsField.setBackground(new Color(189, 195, 199)); // Light background color

        JLabel squareFootageLabel = new JLabel("Square Footage:");
        squareFootageLabel.setBounds(397, 142, 190, 56);
        squareFootageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        squareFootageLabel.setForeground(new Color(236, 240, 241)); // Light font color
        squareFootageField = new JTextField();
        squareFootageField.setBounds(397, 215, 367, 56);
        squareFootageField.setFont(new Font("Arial", Font.PLAIN, 18));
        squareFootageField.setBackground(new Color(189, 195, 199)); // Light background color

        predictButton = new JButton("Predict Price");
        predictButton.setBounds(203, 314, 367, 142);
        predictButton.setFont(new Font("Arial", Font.BOLD, 18));
        predictButton.setBackground(new Color(52, 152, 219)); // Blue button color
        predictButton.setForeground(Color.WHITE); // White text color
        predictButton.setFocusPainted(false); // Remove focus painting on button
        predictButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30)); // Padding inside the button
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                predictPrice();
            }
        });
        inputPanel.setLayout(null);

        inputPanel.add(bedroomsLabel);
        inputPanel.add(bedroomsField);
        inputPanel.add(squareFootageLabel);
        inputPanel.add(squareFootageField);
        inputPanel.add(predictButton);
        getContentPane().add(inputPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBounds(0, 0, 784, 69);
        inputPanel.add(navigationPanel);
        navigationPanel.setBackground(new Color(44, 62, 80)); // Match input panel background color

        homeButton = new JButton("Home");
        homeButton.setBounds(10, 11, 138, 47);
        homeButton.setFont(new Font("Arial", Font.BOLD, 16));
        homeButton.setBackground(new Color(52, 152, 219)); // Blue button color
        homeButton.setForeground(Color.WHITE); // White text color
        homeButton.setFocusPainted(false); // Remove focus painting on button
        homeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHomeWindow();
            }
        });

        previousButton = new JButton("Previous");
        previousButton.setBounds(287, 11, 140, 47);
        previousButton.setFont(new Font("Arial", Font.BOLD, 16));
        previousButton.setBackground(new Color(52, 152, 219)); // Blue button color
        previousButton.setForeground(Color.WHITE); // White text color
        previousButton.setFocusPainted(false); // Remove focus painting on button
        previousButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText(lastPrediction);
            }
        });

        forwardButton = new JButton("Forward");
        forwardButton.setBounds(602, 11, 153, 47);
        forwardButton.setFont(new Font("Arial", Font.BOLD, 16));
        forwardButton.setBackground(new Color(52, 152, 219)); // Blue button color
        forwardButton.setForeground(Color.WHITE); // White text color
        forwardButton.setFocusPainted(false); // Remove focus painting on button
        forwardButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPredictionWindow();
            }
        });
        navigationPanel.setLayout(null);

        navigationPanel.add(homeButton);
        navigationPanel.add(previousButton);
        navigationPanel.add(forwardButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setBounds(0, 485, 784, 76);
        inputPanel.add(resultPanel);
        resultPanel.setBackground(new Color(44, 62, 80)); // Match input panel background color
        resultPanel.setLayout(null);
        resultLabel = new JLabel(lastPrediction);
        resultLabel.setBounds(238, 11, 375, 54);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        resultLabel.setForeground(new Color(236, 240, 241)); // Light font color
        resultPanel.add(resultLabel);

        setVisible(true);
    }

    private void predictPrice() {
        try {
            int bedrooms = Integer.parseInt(bedroomsField.getText());
            int squareFootage = Integer.parseInt(squareFootageField.getText());
            double predictedPrice = model.predict(bedrooms, squareFootage);
            if (predictedPrice < 0) {
                predictedPrice = 0;
            } else if (predictedPrice > 10000) {
                predictedPrice = 10000;
            }
            lastPrediction = String.format("Predicted Price: $%.2f", predictedPrice);
            resultLabel.setText(lastPrediction); // Update the result label directly
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openHomeWindow() {
        new HomeWindow().setVisible(true); // Opens the home window
        dispose(); // Closes the current main window
    }

    private void openPredictionWindow() {
        new PredictionWindow(lastPrediction).setVisible(true); // Opens the prediction window
    }

    private List<House> loadDataset() {
        // Dataset with entries for training purposes
        List<House> dataset = new ArrayList<>();
        dataset.add(new House(1, 1, 100));
        dataset.add(new House(2, 2, 200));
        return dataset;
    }

   
}
