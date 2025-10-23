package Lab2;
public class Record {

    //Attributes
    private Vector input;
    private double output;

    //Methods

    //Constructor
    public Record(Vector input, double output) {
        this.input = input;
        this.output = output;
    }

    //Getters
    public Vector getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    //To string
    public String toString() {
        return "Record: |Input = " + input + ", Output = " + output + "|";
    }
}
