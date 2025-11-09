package Lab3;

import Lab1.Vector;
import Lab1.Record;
import Lab2.Dataset;

public class TestLearner {
    public static void main(String[] args) {

        System.out.println("Testing Algorithm and SupervisedLearner...");

        Dataset ds = new Dataset(2);
        
        // Simple linear relationship: y = 2*x1 + 3*x2 + 1
        ds.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 6.0));  // 2*1 + 3*1 + 1 = 6
        ds.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 9.0));  // 2*1 + 3*2 + 1 = 9
        ds.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 8.0));  // 2*2 + 3*1 + 1 = 8
        ds.addRecord(new Record(new Vector(new double[]{2.0, 2.0}), 11.0)); // 2*2 + 3*2 + 1 = 11

        System.out.println("Dataset: n=" + ds.getData().size() + ", d=" + ds.getDim());

        Algorithm alg = new Algorithm(0.01, 0.0001);

        System.out.println("\n=== Testing Gradient Descent ===");
        SupervisedLearner gdLearner = new SupervisedLearner(ds, alg);
        gdLearner.solve();
        System.out.println("Gradient Descent Model: " + gdLearner);

        // --- Test Moore-Penrose Inverse ---
        System.out.println("\n=== Testing Moore-Penrose Inverse ===");
        SupervisedLearner mpLearner = new SupervisedLearner(ds, alg);
        mpLearner.solveWithPseudoInverse();
        System.out.println("Moore-Penrose Model: " + mpLearner);

        // --- Compare predictions ---
        System.out.println("\n=== Prediction Comparison ===");
        Vector testInput = new Vector(new double[]{1.5, 1.5});
        
        double gdPrediction = gdLearner.predict(testInput);
        double mpPrediction = mpLearner.predict(testInput);
        double expected = 2*1.5 + 3*1.5 + 1; // 3 + 4.5 + 1 = 8.5
        
        System.out.println("Test input: " + testInput);
        System.out.println("Gradient Descent prediction: " + gdPrediction);
        System.out.println("Moore-Penrose prediction: " + mpPrediction);
        System.out.println("Expected value: " + expected);
        System.out.println("GD Error: " + Math.abs(gdPrediction - expected));
        System.out.println("MP Error: " + Math.abs(mpPrediction - expected));

        // Test on training data to verify both methods
        System.out.println("\n=== Training Data Verification ===");
        System.out.println("Input\t\tActual\tGD Pred\tMP Pred");
        for (Record r : ds.getData()) {
            Vector input = r.getInput();
            double actual = r.getOutput();
            double gdPred = gdLearner.predict(input);
            double mpPred = mpLearner.predict(input);
            System.out.printf("%s\t%.1f\t%.3f\t%.3f\n", input, actual, gdPred, mpPred);
        }

        System.out.println("All methods tested successfully!");
    }
}