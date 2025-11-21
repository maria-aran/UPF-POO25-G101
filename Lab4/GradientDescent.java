package Lab4;

public class GradientDescent extends Algorithm {
    
    //Attributes
    private double stoppingCriterion;

    // Constructor 
    public GradientDescent(double learningRate, double stoppingCriterion) {
        super(learningRate);
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
}
