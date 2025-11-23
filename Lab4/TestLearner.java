package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        System.out.println("Testing LAB 4\n");

        // === LINEAR REGRESSION TESTS ===
        System.out.println("=== LINEAR REGRESSION TESTS ===");
        
        RawDataset dataset = new RawDataset(2);
        
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 6.0));
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 9.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 8.0));
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 2.0}), 11.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 1.0}), 10.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 13.0));

        Vector testInput = new Vector(new double[]{1.5, 1.5});
        double expected = 2*1.5 + 3*1.5 + 1; // 8.5

        // Create predictors
        Predictor linearPredictor = new LinearRegressionPredictor();

        // Tests on Raw Data
        System.out.println("=== Tests on Raw Data ===");
        
        // Gradient Descent
        System.out.println("=== Gradient Descent ===");
        GradientDescent gd = new GradientDescent(0.001, 0.0001, linearPredictor);
        SupervisedLearner gdLearner = new SupervisedLearner(dataset, gd);
        gdLearner.solve();
        double gdPrediction = gdLearner.predict(testInput);
        System.out.println(gdLearner);
        System.out.println("Prediction: " + gdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(gdPrediction - expected));
        System.out.println();

        // Stochastic Gradient Descent
        System.out.println("=== Stochastic Gradient Descent ===");
        StochasticGradientDescent sgd = new StochasticGradientDescent(0.0005, 3, 15000, linearPredictor);
        SupervisedLearner sgdLearner = new SupervisedLearner(dataset, sgd);
        sgdLearner.solve();
        double sgdPrediction = sgdLearner.predict(testInput);
        System.out.println(sgdLearner);
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
        GradientDescent gdStd = new GradientDescent(0.01, 0.0001, linearPredictor);
        SupervisedLearner gdStdLearner = new SupervisedLearner(stdDataset, gdStd);
        gdStdLearner.solve();
        double gdStdPrediction = gdStdLearner.predict(testInput);
        System.out.println("Prediction: " + gdStdPrediction + " (Expected: " + expected + ")");
        System.out.println("Error: " + Math.abs(gdStdPrediction - expected));

        System.out.println("SGD on Standardized Data:");
        StochasticGradientDescent sgdStd = new StochasticGradientDescent(0.0001, 4, 15000, linearPredictor);
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
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(lr, 3, 8000, linearPredictor);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  LR=" + lr + " -> Error=" + String.format("%.4f", error));
        }
        
        System.out.println("\n Batch Sizes (LR=0.0005, iterations=8000):");
        int[] batchSizes = {2, 3, 4};
        for (int batchSize : batchSizes) {
            StochasticGradientDescent sgdTest = new StochasticGradientDescent(0.0005, batchSize, 8000, linearPredictor);
            SupervisedLearner learner = new SupervisedLearner(dataset, sgdTest);
            learner.solve();
            double prediction = learner.predict(testInput);
            double error = Math.abs(prediction - expected);
            System.out.println("  Batch=" + batchSize + " -> Error=" + String.format("%.4f", error));
        }
        System.out.println();

        // === LOGISTIC REGRESSION TESTS ===
        System.out.println("=== LOGISTIC REGRESSION TESTS ===");
        
        // Create a dataset with probabilities (outputs between 0 and 1)
        RawDataset probDataset = new RawDataset(2);
        
        // Simple logistic relationship - probabilities between 0 and 1
        probDataset.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 0.1));
        probDataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 0.3));
        probDataset.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 0.4));
        probDataset.addRecord(new Record(new Vector(new double[]{2.0, 2.0}), 0.7));
        probDataset.addRecord(new Record(new Vector(new double[]{3.0, 1.0}), 0.6));
        probDataset.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 0.9));

        Vector testProbInput = new Vector(new double[]{1.5, 1.5});

        // Create logistic predictor
        Predictor logisticPredictor = new LogisticRegressionPredictor();

        // Test Logistic Regression with Gradient Descent
        System.out.println("\n=== Logistic Regression with Gradient Descent ===");
        GradientDescent gdLogistic = new GradientDescent(0.1, 0.0001, logisticPredictor);
        SupervisedLearner gdLogisticLearner = new SupervisedLearner(probDataset, gdLogistic);
        gdLogisticLearner.solve();
        
        double gdLogisticPrediction = gdLogisticLearner.predict(testProbInput);
        System.out.println(gdLogisticLearner);
        System.out.println("Prediction: " + gdLogisticPrediction);
        System.out.println("Prediction is probability (0-1): " + (gdLogisticPrediction >= 0 && gdLogisticPrediction <= 1));

        // Test Logistic Regression with Stochastic Gradient Descent
        System.out.println("\n=== Logistic Regression with Stochastic Gradient Descent ===");
        StochasticGradientDescent sgdLogistic = new StochasticGradientDescent(0.05, 3, 10000, logisticPredictor);
        SupervisedLearner sgdLogisticLearner = new SupervisedLearner(probDataset, sgdLogistic);
        sgdLogisticLearner.solve();
        
        double sgdLogisticPrediction = sgdLogisticLearner.predict(testProbInput);
        System.out.println("Model: " + sgdLogisticLearner);
        System.out.println("Prediction: " + sgdLogisticPrediction);
        System.out.println("Prediction is probability (0-1): " + (sgdLogisticPrediction >= 0 && sgdLogisticPrediction <= 1));

        // Compare both logistic regression algorithms
        System.out.println("\n=== Logistic Regression Comparison ===");
        System.out.println("Prediction difference: " + Math.abs(gdLogisticPrediction - sgdLogisticPrediction));
        boolean bothProbabilities = (gdLogisticPrediction >= 0 && gdLogisticPrediction <= 1) && (sgdLogisticPrediction >= 0 && sgdLogisticPrediction <= 1);
        System.out.println("Both produce valid probabilities? " + bothProbabilities);

        // Test logistic regression with standardized data
        System.out.println("\n=== Logistic Regression on Standardized Data ===");
        StandardizedDataset stdProbDataset = probDataset.standardize();
        
        GradientDescent gdLogisticStd = new GradientDescent(0.001, 0.5, logisticPredictor);
        SupervisedLearner gdLogisticStdLearner = new SupervisedLearner(stdProbDataset, gdLogisticStd);
        gdLogisticStdLearner.solve();
        
        double gdLogisticStdPrediction = gdLogisticStdLearner.predict(testProbInput);
        System.out.println("Standardized prediction: " + gdLogisticStdPrediction);
        System.out.println("Valid probability: " + (gdLogisticStdPrediction >= 0 && gdLogisticStdPrediction <= 1));

        // === FINAL RESULTS ===
        System.out.println("\n=== FINAL RESULTS ===");
        boolean gdWorks = Math.abs(gdPrediction - expected) < 0.5;
        boolean sgdWorks = Math.abs(sgdPrediction - expected) < 0.5;
        boolean stdWorks = Math.abs(gdStdPrediction - expected) < 0.5;
        boolean algorithmsSimilar = Math.abs(gdPrediction - sgdPrediction) < 0.5;
        boolean logisticWorks = bothProbabilities;
        
        System.out.println("Gradient Descent works: " + gdWorks);
        System.out.println("Stochastic GD works: " + sgdWorks);
        System.out.println("Standardized data works: " + stdWorks);
        System.out.println("Algorithms give similar results: " + algorithmsSimilar);
        System.out.println("Logistic regression works: " + logisticWorks);
        
        boolean allTestsPass = gdWorks && sgdWorks && stdWorks && algorithmsSimilar && logisticWorks;
        System.out.println("\nALL TESTS PASSED: " + allTestsPass);
        System.out.println("EXTRA POINT (Logistic Regression) IMPLEMENTED: " + logisticWorks);
    }
}