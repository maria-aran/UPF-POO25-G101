package Lab4;

public class GradientDescent extends Algorithm {
    
    //Attributes
    private double stoppingCriterion;

    // Constructor 
    public GradientDescent(double learningRate, double stoppingCriterion, Predictor p) {
        super(learningRate, p);
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

            Vector recordGradient = predictor.computeGradient(model.getparams(), Aug, y);
            grad = grad.add(recordGradient.multiply(1.0 / n));
        }
        return grad;
    }

    public Model solve(Dataset ds) {
        
        Model model = new Model(ds.getDim());
        Vector grad;
        int iteration = 0;
        int maxIterations = 5000;

        do {
            grad = gradient(ds, model);
            model.update(grad, learningRate);
            iteration++; 

            if (iteration >= maxIterations) {
                break;}

        } while (grad.norm() > stoppingCriterion);

        return model;
    }
}
