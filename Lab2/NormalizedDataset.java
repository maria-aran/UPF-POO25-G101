package Lab2;

import Lab1.Record;
import Lab1.Vector;


public class NormalizedDataset extends Dataset{
    
    //Attributes
    private Vector minInput;  
    private Vector maxInput;  
    private double minOutput; 
    private double maxOutput;
    
    //COnstructor
    public NormalizedDataset(Dataset d, Vector minInput, Vector maxInput, double minOutput, double maxOutput) {
        super(d.getDim()); // dimension is taken from Dataset
        this.minInput = minInput;
        this.maxInput = maxInput;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
        }

    //Getters
    public Vector getMinInput() { 
        return minInput; }
    public Vector getMaxInput() { 
        return maxInput; }
    public double getMinOutput() { 
        return minOutput; }
    public double getMaxOutput() {
        return maxOutput; }

    public Record transform(Record r) {
        Vector rangeInput = maxInput.subtract(minInput);
        Vector normalizedInput = r.getInput().subtract(minInput).divide(rangeInput);
        
        double rangeOutput = maxOutput - minOutput;
        double normalizedOutput = (r.getOutput() - minOutput) / rangeOutput;
        
        return new Record(normalizedInput, normalizedOutput);
    }

    
    public String toString() {
        return "NormalizedDataset( MinInput=" + minInput + ", MaxInput=" + maxInput + ", MinOutput=" + minOutput + ", MaxOutput=" + maxOutput + ")";    }
}
