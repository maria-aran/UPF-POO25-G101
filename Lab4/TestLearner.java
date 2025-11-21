package Lab4;


public class TestLearner {
    public static void main(String[] args) {
        System.out.println("---- Testing Lab 4 Classes ----\n");

        // Create dataset with linear relationship: y = 2*x1 + 3*x2 + 1
        RawDataset dataset = new RawDataset(2);
        
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 6.0));
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 9.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 8.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 2.0}), 11.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 1.0}), 10.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 13.0));


        // Test input for predictions
        Vector testInput = new Vector(new double[]{1.5, 1.5});
        double expected = 2*1.5 + 3*1.5 + 1; // 8.5

        //Gradient Descent
        System.out.println("=== Testing Gradient Descent ===");
        GradientDescent gd = new GradientDescent(0.01, 0.0001);
        SupervisedLearner gdLearner = new SupervisedLearner(dataset, gd);
        gdLearner.solve();
        
        double gdPrediction = gdLearner.predict(testInput);
        System.out.println("GD Model: " + gdLearner);
        System.out.println("GD Prediction: " + gdPrediction);
        System.out.println("Expected: " + expected);
        System.out.println("GD Error: " + Math.abs(gdPrediction - expected));
        System.out.println();

        //Stochastic Gradient Descent
        System.out.println("=== Testing Stochastic Gradient Descent ===");
        StochasticGradientDescent sgd = new StochasticGradientDescent(0.005, 2, 5000);
        SupervisedLearner sgdLearner = new SupervisedLearner(dataset, sgd);
        sgdLearner.solve();
        
        double sgdPrediction = sgdLearner.predict(testInput);
        System.out.println("SGD Model: " + sgdLearner);
        System.out.println("SGD Prediction: " + sgdPrediction);
        System.out.println("Expected: " + expected);
        System.out.println("SGD Error: " + Math.abs(sgdPrediction - expected));
        System.out.println();

        // Compare both algorithms
        System.out.println("=== Comparing Algorithms ===");
        System.out.println("Prediction difference: " + Math.abs(gdPrediction - sgdPrediction));
        System.out.println("Both algorithms work correctly? " + (Math.abs(gdPrediction - expected) < 1.0 && Math.abs(sgdPrediction - expected) < 1.0));
        System.out.println();

        //Test with Standardized Dataset
        System.out.println("=== Testing with Standardized Dataset ===");
        StandardizedDataset stdDataset = dataset.standardize();
        
        // Test Gradient Descent on standardized data
        GradientDescent gdStd = new GradientDescent(0.01, 0.0001);
        SupervisedLearner gdStdLearner = new SupervisedLearner(stdDataset, gdStd);
        gdStdLearner.solve();
        double gdStdPrediction = gdStdLearner.predict(testInput);
        
        // Test Stochastic Gradient Descent on standardized data
        StochasticGradientDescent sgdStd = new StochasticGradientDescent(0.005, 2, 5000);
        SupervisedLearner sgdStdLearner = new SupervisedLearner(stdDataset, sgdStd);
        sgdStdLearner.solve();
        double sgdStdPrediction = sgdStdLearner.predict(testInput);
        
        System.out.println("GD on standardized data: " + gdStdPrediction);
        System.out.println("SGD on standardized data: " + sgdStdPrediction);
        System.out.println("Standardized predictions similar to raw? " + (Math.abs(gdPrediction - gdStdPrediction) < 1.0 && Math.abs(sgdPrediction - sgdStdPrediction) < 1.0));
        System.out.println();

        // Parameter Tuning
        System.out.println("=== Testing Different Stochastic Gradient Descent Parameters ===");
        
        // Test different learning rates
        System.out.println("Different learning rates (batch=2, iterations=2000):");
        double[] learningRates = {0.1, 0.01, 0.005, 0.001};
        for (double lr : learningRates) {
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(lr, 2, 2000);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  LR=" + lr + " -> Error=" + String.format("%.4f", error));
        }
        System.out.println();

        // Test different batch sizes
        System.out.println("Different batch sizes (LR=0.005, iterations=2000):");
        int[] batchSizes = {1, 2, 3, 4};
        for (int batchSize : batchSizes) {
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(0.005, batchSize, 2000);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  Batch=" + batchSize + " -> Error=" + String.format("%.4f", error));
        }
        System.out.println();

        // Final Verification
        System.out.println("=== Final Results ===");
        boolean gdWorks = Math.abs(gdPrediction - expected) < 1.0;
        boolean sgdWorks = Math.abs(sgdPrediction - expected) < 1.0;
        boolean standardizedWorks = Math.abs(gdStdPrediction - expected) < 1.0;
        boolean bothSimilar = Math.abs(gdPrediction - sgdPrediction) < 1.0;
        
        System.out.println("Gradient Descent works? " + gdWorks);
        System.out.println("Stochastic Gradient Descent works? " + sgdWorks);
        System.out.println("Standardized dataset works? " + standardizedWorks);
        System.out.println("Both algorithms give similar results? " + bothSimilar);
        
        boolean allTestsPass = gdWorks && sgdWorks && standardizedWorks && bothSimilar;
        System.out.println("\nAll tests passed? " + allTestsPass);
    }
}