package Lab3;

import Lab1.Vector;
import Lab1.Record; 
import Lab2.Dataset;

import java.util.List;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

public class SupervisedLearner {

    private Dataset dataset;
    private Algorithm algorithm;
    private Model model;

    // Constructor: dataset and algorithm known, model created empty
    public SupervisedLearner(Dataset ds, Algorithm alg) {
        this.dataset = ds;
        this.algorithm = alg;
        this.model = new Model(ds.getDim());
    }

    // Train model using algorithm
    public void solve() {
        this.model = algorithm.solve(dataset);
    }

    // Predict output for a new input (needs augmentation)
    public double predict(Vector input) {
        Vector Aug = input.augment();
        return model.predict(Aug);
    }

    //θ = X⁺ y, where X⁺ = (XᵀX)⁻¹ Xᵀ
    public void solveWithPseudoInverse() {
        List<Record> data = dataset.getData();
        int n = data.size();
        int d = dataset.getDim();

        // Create X matrix (n x (d+1)) with augmented input vectors as rows
        DMatrixRMaj X = new DMatrixRMaj(n, d + 1);
        DMatrixRMaj y = new DMatrixRMaj(n, 1);

        // Fill X and y matrices
        int i = 0;
        for (Record r : data) {
            Vector augmented = r.getInput().augment();

         
            for (int j = 0; j < d + 1; j++) {
                X.set(i, j, augmented.getValue(j));
            }
            y.set(i, 0, r.getOutput());
            i++;
        }

        // Compute Moore-Penrose pseudo-inverse X⁺
        DMatrixRMaj X_pseudo = new DMatrixRMaj(d + 1, n);
        CommonOps_DDRM.pinv(X, X_pseudo);

        // Compute optimal parameters θ* = X⁺ * y
        DMatrixRMaj theta = new DMatrixRMaj(d + 1, 1);
        CommonOps_DDRM.mult(X_pseudo, y, theta);

        // Convert result to Vector and update model
        double[] params = new double[d + 1];
        for (int j = 0; j < d + 1; j++) {
            params[j] = theta.get(j, 0);
        }
        model.setParams(new Vector(params));
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
