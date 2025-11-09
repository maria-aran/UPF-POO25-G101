package Lab3;

import Lab1.Record;
import Lab1.Vector;

import Lab2.Dataset;
import Lab2.NormalizedDataset;
import Lab2.StandardizedDataset;

public class Model {
    private Vector parameter;

    public Model(int dim){

        double[] values = new double[dim + 1];

        for (int i = 0; i < values.length; i++) {
            values[i] = 0.0; 
        }
        this.parameter = new Vector(values);
    }

    public pa
        
}

