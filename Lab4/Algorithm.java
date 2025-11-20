package Lab4;
public class Algorithm {

    //Attributes
    private double learningRate;
    private double stoppingCriterion;

    // Constructor 
    public Algorithm(double learningRate, double stoppingCriterion) {
        this.learningRate = learningRate;
        this.stoppingCriterion = stoppingCriterion;
    }

    //* grad = (1/n) * sum_i x_i * (prediction - y_i)
    public Vector gradient(Dataset ds, Model model) {
        int n = ds.getData().size();
        int dimAug = ds.getDim() + 1; // augmented dimension
        Vector grad = new Vector(dimAug, 0.0);

        if (n == 0) return grad; // avoid div by zero

        for (Record r : ds.getData()) {
            Vector Aug = r.getInput().augment();
            double y = r.getOutput();
            double prediction = model.predict(Aug);
            double error = prediction - y;
            // accumulate (1/n) * x_i * error
            grad = grad.add(Aug.multiply(error / n));
        }
        return grad;
    }

    public Model solve(Dataset ds) {
        Model model = new Model(ds.getDim());
        Vector grad;

        do {
            grad = gradient(ds, model);
            model.update(grad, learningRate);
        } while (grad.norm() > stoppingCriterion);

        return model;
    }

    @Override
    public String toString() {
        return "Algorithm(learningRate=" + learningRate + ", stoppingCriterion=" + stoppingCriterion + ")";
    }
}

