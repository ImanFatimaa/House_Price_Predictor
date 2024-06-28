package codeclause;

import javax.swing.*;
import java.awt.*;

public class PredictionWindow extends JFrame {
    public PredictionWindow(String lastPrediction) {
        setTitle("Price Prediction");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        JLabel predictionLabel = new JLabel(lastPrediction, SwingConstants.CENTER);
        predictionLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(predictionLabel, BorderLayout.CENTER);

        setVisible(true);
    }
}
