package Lab4;

public abstract class Predictor {

    public abstract double predict(Vector params, Vector augmentedInput);
    public abstract Vector computeGradient(Vector params, Vector augmentedInput, double actualOutput);
}