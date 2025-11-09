package Lab3;

import Lab1.Vector;

public class Model {
    private Vector params;

    //constructor
    public Model (int dim){
        this.params= new Vector(dim+1, 0.0);
    }

    //getter
    public Vector getParams(){
        return params;
    }

    public double predict(Vector v){
        return v.dot(this.params);
    }

    public void update(Vector v, double rate){
        Vector gradientStep=v.multiply(rate);
        this.params=this.params.subtract(gradientStep);
    }
    
    public String toString(){
        return "Model parameters (theta): " + params.toString();
    }
}
