package Lab4;
public abstract class Algorithm {

    //Attributes
    protected double learningRate;
    protected Predictor predictor;

    // Constructor 
    public Algorithm(double learningRate, Predictor p) {
        this.learningRate = learningRate;
        predictor = p;
    }

    public abstract Model solve(Dataset ds);
}

