package Lab4;

public class LinearRegressionPredictor extends Predictor{
    
    @Override
    public double predict(Vector params, Vector augmentedInput) {
        // Linear regression: f(x) = θ·x
        return params.dot(augmentedInput);
    }
    
    @Override
    public Vector computeGradient(Vector params, Vector augmentedInput, double actualOutput) {
        // Gradient: x_i * (θ·x_i - y_i)
        double prediction = predict(params, augmentedInput);
        double error = prediction - actualOutput;
        return augmentedInput.multiply(error);
    }
}