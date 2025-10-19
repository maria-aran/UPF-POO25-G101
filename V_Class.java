import java.util.Arrays;

public class V_Class {

    private double[] elems; // elements of the vector

    //create vector from array
    public V_Class(double[] e){
        this.elems=Arrays.copyOf(e,e.length);
    }


    //create vector of dimension dim, with all values=val
    public V_Class(int dim, double val){
        this.elems=new double[dim];
        for(int i=0 ; i< dim ; i++){
            this.elems[i]=val;
        }
    }

//methods

    //get dimension of vector
    public int getDim() {
        return elems.length;
    }

    //add vectors
    public V_Class add(V_Class v){
        checkDimension(v); //we make sure the two vectors are of the same size
        double[] result= new double[getDim()];
        for (int i = 0 ; i<getDim(); i++){
            result[i]=elems[i]+v.elems[i];
        }
        return new V_Class(result);
        
    }

    
    //substract vectors
    public V_Class substract(V_Class v){
        checkDimension(v); //we make sure the two vectors are of the same size
        double result[]= new double[getDim()];
        for (int i =0 ; i< getDim() ; i++){
            result[i]=elems[i]-v.elems[i];
        }
        return new V_Class(result);
    }


    //multiply vectors
    public V_Class multiply(V_Class v){
        checkDimension(v); //we make sure the two vectors are of the same size
        double result[]= new double[getDim()];
        for(int i= 0; i<getDim(); i++){
            result[i]=elems[i]*v.elems[i];
        }
        return new V_Class(result);
    }

    //divide vectors
    public V_Class divide(V_Class v){
        checkDimension(v); //we make sure the two vectors are of the same size
        double result[]= new double[getDim()];
        for( int i =0; i<getDim(); i++){
            if(v.elems[i]==0){
                result[i]=0;
            } else{
                result[i]=elems[i]/v.elems[i];
            }
        }
        return new V_Class(result);
    }

    //multiply vector by a scalar
    public V_Class multiply(double scalar){
        double[] result= new double[getDim()];
        for (int i =0; i<getDim(); i++){
            result[i]=elems[i]*scalar;
        }
        return new V_Class(result);
    }

    //divide vector by a scalar
    public V_Class divide(double scalar){
        if(scalar==0){
            System.out.println("Warning: Division by zero scalar. Returning null.");
            return null;
        }
        double[] result = new double[getDim()];
        for(int i =0; i<getDim(); i++){
            result[i]=elems[i]/scalar;
        }
        return new V_Class(result);
    }


    //square root of vector
    public V_Class sqrt(){
        double[] result= new double[getDim()];
        for(int i=0; i< getDim(); i++){
            result[i]=Math.sqrt(elems[i]);
        }
        return new V_Class(result);
    }


    //dot product of vector
    public double dotProduct(V_Class v){
        checkDimension(v); //we make sure the two vectors are of the same size
        double sum = 0;
        for (int i=0; i<getDim() ; i++)
            sum+=elems[i]*v.elems[i];
        return sum;

    }


    //norm of vector
    public double norm(){
        double sum=0;
        for(double e: elems)
            sum+=e*e;
        return Math.sqrt(sum);
    }


    //convert vector into string
    public String toString(){
        return Arrays.toString(elems);  }

    //helper to ensure both vectors are of the same size before running the methods 

    private void checkDimension(V_Class V){
        if (V.getDim() != getDim()){
            System.out.println("Dimension dismatch. Vectors must be of the same size.");
            return;
        }
    }


}
