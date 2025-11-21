package Lab4;
public abstract class Algorithm {

    //Attributes
    protected double learningRate;

    // Constructor 
    public Algorithm(double learningRate) {
        this.learningRate = learningRate;
    }

    public abstract Model solve(Dataset ds);
}

