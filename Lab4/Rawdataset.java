package Lab4;

import java.util.ArrayList;

public class RawDataset extends Dataset{
    
    public RawDataset(int d){
        super.Dataset(d);
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

    @Override
    public Record transform(Record r) {
        Vector standardizedInput = r.getInput().subtract(mi).divide(si); //Now each input has mean 0 and standard deviation 1
        double standardizedOutput = (r.getOutput() - mo) / so;          //And each output has mean 0 and standard deviation 1
        return new Record(standardizedInput, standardizedOutput);
    }

    @Override
    public double output(double d) {
        return output;
    }
}
