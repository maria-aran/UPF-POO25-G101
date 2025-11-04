    package Lab2;

    import Lab1.Record;
    import Lab1.Vector;
    import java.util.ArrayList;

    public class Dataset {

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

        // Compute mean vector of inputs
            public Vector meanInput() {
                if (data.isEmpty()) return new Vector(dim, 0);

                double[] mean = new double[dim];
                for (Record r : data) {
                    double[] elems = r.getInput().getElems();
                    for (int i = 0; i < dim; i++) mean[i] += elems[i];
                }

                for (int i = 0; i < dim; i++) mean[i] /= data.size(); // divide by number of records
                return new Vector(mean);
            }

        // Compute mean of outputs
        public double meanOutput() {
            if (data.isEmpty()) return 0; //If there aren't any records, the mean is 0, also we avoid dividing by 0
            double sum = 0;
            for (Record r : data) sum += r.getOutput();
            return sum / data.size();
        }

        // Compute standard deviation vector of inputs

        public Vector stdInput() {
            Vector mean = meanInput();
            double[] var = new double[dim];
            for (Record r : data) {
                double[] elems = r.getInput().getElems();
                for (int i = 0; i < dim; i++) {
                    double diff = elems[i] - mean.getElems()[i];
                    var[i] += diff * diff;
                }
            }
            for (int i = 0; i < dim; i++) var[i] = Math.sqrt(var[i] / data.size());
            return new Vector(var);
        }

        // Compute standard deviation of outputs
        public double stdOutput() {
            double mean = meanOutput();
            double sumSq = 0;
            for (Record r : data) {
                double diff = r.getOutput() - mean;
                sumSq += diff * diff;
            }
            return Math.sqrt(sumSq / data.size());
        }

        // Create a standardized dataset
        public StandardizedDataset standardize() {
            Vector meanin = meanInput();
            Vector stdin = stdInput();
            double meanout = meanOutput();
            double stdout = stdOutput();

            StandardizedDataset sd = new StandardizedDataset(this, meanin, stdin, meanout, stdout);
            //New Dataset Std using the current instance

            // Transform each record and add to standardized dataset
            for (Record r : data) {
                sd.addRecord(sd.transform(r));
            }

            return sd;
        }

        // toString
        public String toString() {
            return "Dataset | dim = " + dim + ", data= " + data + "|";
        }
        
        public NormalizedDataset normalize() {
        if (data.isEmpty()) {
            return new NormalizedDataset(this, new Vector(dim, 0), new Vector(dim, 1), 0, 1);
        }
        
        double[] minIn = data.get(0).getInput().getElems().clone();
        double[] maxIn = data.get(0).getInput().getElems().clone();
        double minOut = data.get(0).getOutput();
        double maxOut = data.get(0).getOutput();
        
        for (Record r : data) {
            double[] elems = r.getInput().getElems();
            double output = r.getOutput();
            
            for (int i = 0; i < dim; i++) {
                if (elems[i] < minIn[i]) minIn[i] = elems[i];
                if (elems[i] > maxIn[i]) maxIn[i] = elems[i];
            }
            
            if (output < minOut) minOut = output;
            if (output > maxOut) maxOut = output;
        }
        
        NormalizedDataset nd = new NormalizedDataset(this, new Vector(minIn), new Vector(maxIn), minOut, maxOut);
        
        for (Record r : data) {
            Record transformedRecord = nd.transform(r);  
            nd.addRecord(transformedRecord);            
        }
        
        return nd;
    }
}
