
public class Record {

    // Attribu  tes
    private Vector input;
    private double output;

    // Methods

    // Constructor
    public Record(Vector i, double o) { //Constructor method always has the same name as the class
        input = i; //this. is used to refer to the instance used
        output = o;
    }

    // Getter
    public Vector getInput() {
        return input;
    }

    public Double getOutput() {
        return output;
    }

    //function to turn into a string
    public String toString() {
        String string_function = "Record: |Input =" + input + ", Output =" + output +"|";
        return string_function;
        }
    
}
