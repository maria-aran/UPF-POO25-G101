package Lab4;

import java.util.Random;

public class StochasticGradientDescent extends Algorithm {
    private int batchSize;
    private int iterations;
    private Random random;

    public StochasticGradientDescent(double lr, int bs, int it, Predictor p){
        super(lr, p);
        iterations = it;
        batchSize = bs;
        random = new Random();
    }
    
    public Vector stochasticGradient(Dataset ds, Model model) {

        int n = ds.getData().size();
        if (n == 0) {
            return new Vector(ds.getDim() + 1, 0.0);
        }

        int actualBatchSize = Math.min(batchSize, n);
        
        int[] r = random.ints(0, n).distinct().limit(actualBatchSize).toArray();
        Vector grad = new Vector(ds.getDim(), 0.0);
        grad = grad.augment();
        
        // Compute stochastic gradient
        for (int idx : r) {
            Record record = ds.getData().get(idx);
            Vector augmentedInput = record.getInput().augment();
            double actualOutput = record.getOutput();
            
            Vector recordGradient = predictor.computeGradient(model.getparams(), augmentedInput, actualOutput);
            grad = grad.add(recordGradient);
        }
        
        grad = grad.divide(actualBatchSize);
        return grad;
    }

    @Override
    public Model solve(Dataset dataset){

        Model model = new Model(dataset.getDim());
        int n = dataset.getData().size();
        
        if (n == 0) {
            System.out.println("Empty dataset, can't be solved");
            return model;
        }

        for (int iter = 0; iter < iterations; iter++) {

            Vector stochasticGrad = stochasticGradient(dataset, model);
            
            // θ = θ - learningRate * ∇L(θ)
            model.update(stochasticGrad, learningRate);
        }
        return model;
    }
}

