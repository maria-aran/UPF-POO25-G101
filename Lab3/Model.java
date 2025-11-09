package Lab3;


import Lab1.Vector;

public class Model {

    private Vector params;

    // Constructor: initialize θ = 0
    public Model(int dim) {
        this.params = new Vector(dim + 1, 0.0); // +1 for bias
    }

    // Getter
    public Vector getparams() {
        return params;
    }

    
    // Predict output for an (augmented) input vector
    public double predict(Vector Augmented) {
        return params.dot(Augmented);
    }

    // Update parameters using gradient descent
    public void update(Vector gradient, double learningRate) {
        params = params.subtract(gradient.multiply(learningRate));
    }

    //method for Moore-Penrose inverse
    public void setParams(Vector newParams) {
        this.params = newParams;
    }

    @Override
    public String toString() {
        return "Model parameters: " + params.toString();
    }
}
