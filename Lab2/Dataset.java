package Lab2;

import java.util.*;

//we want to use an array or some other structure, not a dataset, which stores records and 
//provides mean/standard deviations computations.


public class Dataset {
    protected int dim; //dim: int
    protected ArrayList<Record> data;

    public Dataset(int d){
        this.dim=d;
        //this.data= new ArrayList<>();
        }

    public int getDim(){
        return dim;
    }

    public getData(){

    }

    //add record into dataset, need to ensure that the dimension of the input vector in the new
    // record matches the Dataset's established dimnension!!
    public void addRecord(Record r){
        data.add(r);
    }

    public Vector meanInput(){

    }

    public Vector stdInput(){

    }

    public double meanOutput(){

    }

    public double stdOutput(){

    }