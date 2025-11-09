package Lab1;
import java.util.Arrays;


public class Vector {

    //Attribute
    private double[] values;

    //Methods 

    // Constructor 1 
    public Vector(double[] values) {
        this.values = values;
    }

    // Constructor 2: create array of dim size filled with val
    public Vector(int dim, double val) {
        this.values = new double[dim];
        Arrays.fill(this.values, val);
    }

    //Getters

    // Get dimension
    public int getDim() {
        return values.length;
    }

    // Get value at index
    public double getValue(int index) {
        return values[index];
    }

    // Set value at index
    public void setValue(int index, double val) {
        values[index] = val;
    }

    public double[] getElems() {
        return values;
    }

    // Add two vectors
    public Vector add(Vector other) {

        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] + other.values[i];
        }
        return new Vector(result);
    }

    // Subtract two vectors
    public Vector subtract(Vector other) {

        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - other.values[i];
        }
        return new Vector(result);
    }

    // Multiply two vectors (element-wise)
    public Vector multiply(Vector other) {
        
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] * other.values[i];
        }
        return new Vector(result);
    }

    // Divide two vectors (element-wise)
    public Vector divide(Vector other) {
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] / other.values[i];
        }
        return new Vector(result);
    }

    // Multiply by scalar
    public Vector multiply(double scalar) {
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] * scalar;
        }
        return new Vector(result);
    }

    // Divide by scalar
    public Vector divide(double scalar) {
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] / scalar;
        }
        return new Vector(result);
    }

    // Square root of each element
    public Vector sqrt() {
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = Math.sqrt(values[i]);
        }
        return new Vector(result);
    }

    // Dot product
    public double dot(Vector other) {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i] * other.values[i];
        }
        return sum;
    }

    // Norm (length)
    public double norm() {
        double sum = 0;
        for (double v : values) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    // Convert to string
    public String toString() {
        return Arrays.toString(values);
    }

    //Lab 3 implementation:
    public Vector augment() {
        double[] augmented = new double[values.length + 1];

        for (int i = 0; i < values.length; i++) {
            augmented[i] = values[i];
        }
        augmented[values.length] = 1.0; 

        return new Vector(augmented);
    }
}
