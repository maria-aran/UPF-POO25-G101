package Lab4;

public class LogisticRegressionPredictor extends Predictor{
    
    // Sigmoid function: 1 / (1 + e^(-z))
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }
    
    @Override
    public double predict(Vector params, Vector augmentedInput) {
        // Logistic regression: f(x) = 1 / (1 + e^(-θ·x))
        double linearCombination = params.dot(augmentedInput);
        return sigmoid(linearCombination);
    }
    
    @Override
    public Vector computeGradient(Vector params, Vector augmentedInput, double actualOutput) {
        // Gradient: x_i * (sigmoid(θ·x_i) - y_i)
        double prediction = predict(params, augmentedInput);
        double error = prediction - actualOutput;
        return augmentedInput.multiply(error);
    }
}