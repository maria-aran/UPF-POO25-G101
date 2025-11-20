package Lab4;
import java.util.ArrayList;

    public abstract class Dataset {
  
        protected int dim;                // Dimension of input vectors
        protected ArrayList<Record> data; // List of records

        // Constructor
        public Dataset(int dim) {
                this.dim = dim;
                this.data = new ArrayList<>();
            }

        // Getter for dimension
        public int getDim() {
                return dim;
            }

        // Getter for the data list
        public ArrayList<Record> getData() {
                return data;
            }

        // Add a new record to the dataset
        public void addRecord(Record r) {
                if (r.getInput().getDim() != dim) { //Verify that the dimention of new record martches the correct number of input dimensions
                    System.out.println("Record dimension mismatch! Not added.");
                    return; // Return before it does anything since the new record cannot be added
                }
                else{data.add(r);}
            }

        // toString
        public String toString() {
                return "Dataset | dim = " + dim + ", data= " + data + "|";
            }
        
        public abstract Record transform(Record r);

        public abstract double output(double d);
    }
