package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        System.out.println("Testing LAB 4\n");

        RawDataset dataset = new RawDataset(2);
        
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 6.0));
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 9.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 8.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 2.0}), 11.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 1.0}), 10.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 13.0));

        Vector testInput = new Vector(new double[]{1.5, 1.5});
        double expected = 2*1.5 + 3*1.5 + 1; // 8.5

        // Tests on Raw Data

        // Gradient Descent
        System.out.println("=== Tests on Raw Data ===");
        System.out.println("=== Gradient Descent ===");
        GradientDescent gd = new GradientDescent(0.001, 0.0001);
        SupervisedLearner gdLearner = new SupervisedLearner(dataset, gd);
        gdLearner.solve();
        double gdPrediction = gdLearner.predict(testInput);
        System.out.println("Model: " + gdLearner);
        System.out.println("Prediction: " + gdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(gdPrediction - expected));
        System.out.println();

        // Stochastic Gradient Descent
        System.out.println("=== Stochastic Gradient Descent ===");
        StochasticGradientDescent sgd = new StochasticGradientDescent(0.0005, 3, 15000);
        SupervisedLearner sgdLearner = new SupervisedLearner(dataset, sgd);
        sgdLearner.solve();
        double sgdPrediction = sgdLearner.predict(testInput);
        System.out.println("Model: " + sgdLearner);
        System.out.println("Prediction: " + sgdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(sgdPrediction - expected));
        System.out.println();

        // Compare GD vs SGD
        System.out.println("=== Gradient Descent and Stochastic Gradient Descent Comparison ===");
        System.out.println("Prediction difference: " + Math.abs(gdPrediction - sgdPrediction));
        boolean similarPredictions = Math.abs(gdPrediction - sgdPrediction) < 0.5;
        System.out.println("Predictions similar? " + similarPredictions);
        System.out.println();

        // === Test with an Standardized Dataset ===
        System.out.println("=== Standardized Dataset Tests ===");
        StandardizedDataset stdDataset = dataset.standardize();

        // Gradient Descent on standardized data 
        System.out.println("=== Gradient Descent ===");
        GradientDescent gdStd = new GradientDescent(0.01, 0.0001);
        SupervisedLearner gdStdLearner = new SupervisedLearner(stdDataset, gdStd);
        gdStdLearner.solve();
        double gdStdPrediction = gdStdLearner.predict(testInput);
        System.out.println("Prediction: " + gdStdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(gdStdPrediction - expected));

        System.out.println("SGD on Standardized Data:");
        StochasticGradientDescent sgdStd = new StochasticGradientDescent(0.0001, 4, 15000);
        SupervisedLearner sgdStdLearner = new SupervisedLearner(stdDataset, sgdStd);
        sgdStdLearner.solve();
        double sgdStdPrediction = sgdStdLearner.predict(testInput);
        System.out.println("Prediction: " + sgdStdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(sgdStdPrediction - expected));

        boolean standardizedWorks = Math.abs(gdStdPrediction - expected) < 0.5 && Math.abs(sgdStdPrediction - expected) < 0.5;
        System.out.println("Standardized dataset works? " + standardizedWorks);
        System.out.println();


        // Stochastic Gradient Descent Parameter Tuning 
        System.out.println("=== Stochastic Gradient Descent Parameter Tuning ===");
        
        System.out.println("\nLearning Rates (batch=3, iterations=8000):");
        double[] learningRates = {0.001, 0.0005, 0.0001};
        for (double lr : learningRates) {
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(lr, 3, 8000);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  LR=" + lr + " -> Error=" + String.format("%.4f", error));
        }
        
        System.out.println("\n Batch Sizes (LR=0.0005, iterations=8000):");
        int[] batchSizes = {2, 3, 4};
        for (int batchSize : batchSizes) {
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(0.0005, batchSize, 8000);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  Batch=" + batchSize + " -> Error=" + String.format("%.4f", error));
        }
        System.out.println();

        // === Final Results ===
        System.out.println("=== FINAL RESULTS ===");
        boolean gdWorks = Math.abs(gdPrediction - expected) < 0.5;
        boolean sgdWorks = Math.abs(sgdPrediction - expected) < 0.5;
        boolean stdWorks = Math.abs(gdStdPrediction - expected) < 0.5;
        boolean algorithmsSimilar = Math.abs(gdPrediction - sgdPrediction) < 0.5;
        
        System.out.println("Gradient Descent works: " + gdWorks);
        System.out.println("Stochastic GD works: " + sgdWorks);
        System.out.println("Standardized data works: " + stdWorks);
        System.out.println("Algorithms give similar results: " + algorithmsSimilar);
        
        boolean allTestsPass = gdWorks && sgdWorks && stdWorks && algorithmsSimilar;
        System.out.println("\nALL TESTS PASSED: " + allTestsPass);
    }
}