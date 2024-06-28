package codeclause;

import java.util.List;

public class LinearRegression {
    private static final double PRICE_PER_BEDROOM = 100.0;
    private static final double PRICE_PER_SQUARE_FOOTAGE_UNIT = 1.0; // 1 unit represents 1 square footage

    public void train(List<House> dataset) {
        // No training necessary for this simple linear relationship
    }

    public double predict(int bedrooms, int squareFootage) {
        return (bedrooms * PRICE_PER_BEDROOM) + (squareFootage * PRICE_PER_SQUARE_FOOTAGE_UNIT);
    }
}
