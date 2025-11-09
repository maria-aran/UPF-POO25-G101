package Lab3;

import Lab1.Vector;
import Lab1.Record;
import Lab2.Dataset;

public class TestLearner{
        public static void main() {

        System.out.println("Testing Algorithm and SupervisedLearner...");

        Dataset ds = new Dataset(2);

        ds.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 5.0));
        ds.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 11.0));

        // --- Test Algorithm methods ---
        Algorithm alg = new Algorithm(0.1, 0.0001);
        Model model = new Model(ds.getDim());

        // Check gradient computation
        Vector grad = alg.gradient(ds, model);
        System.out.println("Gradient: " + grad);

        // Check solve method (gradient descent)
        Model trainedModel = alg.solve(ds);
        System.out.println("Trained model: " + trainedModel);

        // --- Test SupervisedLearner methods ---
        SupervisedLearner learner = new SupervisedLearner(ds, alg);

        // Train the learner
        learner.solve();
        System.out.println("Learner model after solve: " + learner);

        // Predict a value
        Vector testInput = new Vector(new double[]{2.0, 3.0});
        double prediction = learner.predict(testInput);
        System.out.println("Prediction for [2.0, 3.0]: " + prediction);

        // Test Moore-Penrose Inverse only
        SupervisedLearner mpLearner = new SupervisedLearner(ds, alg);
        mpLearner.solveWithPseudoInverse();
        System.out.println("Moore-Penrose Model: " + mpLearner);

        // Test prediction
        Vector testInput2 = new Vector(new double[]{2.0, 3.0});
        double mpPrediction = mpLearner.predict(testInput2);
        System.out.println("MP Prediction for [2.0, 3.0]: " + mpPrediction);
        System.out.println("All methods tested successfully!");
    }
}
