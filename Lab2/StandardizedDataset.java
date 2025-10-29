package Lab2;

import Lab1.Record;
import Lab1.Vector;

public class StandardizedDataset extends Dataset {

    //Attributes
    private Vector mi; // mean of inputs
    private Vector si; // std of inputs
    private double mo; // mean of outputs
    private double so; // std of outputs

    // Constructor: use super() to set dim
    public StandardizedDataset(Dataset d, Vector mi, Vector si, double mo, double so) {
        super(d.getDim()); // inherit dim from Dataset
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
        // Initially the data list is empty; we'll fill it using transform()
    }

    /**
     * Transform a record: (x - mean) / std
     */
    public Record transform(Record r) {
        Vector standardizedInput = r.getInput().subtract(mi).divide(si); //Now each input has mean 0 and standard deviation 1
        double standardizedOutput = (r.getOutput() - mo) / so;          //And each output has mean 0 and standard deviation 1
        return new Record(standardizedInput, standardizedOutput);
    }

    // Simple toString showing statistics
    public String toString() {
        return "StandardizedDataset(meanInput=" + mi + ", stdInput=" + si + ", meanOutput=" + mo + ", stdOutput=" + so + ")";
    }
}
