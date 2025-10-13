import java.util.Scanner;
import java.util.Arrays;

public class TestRecord {

    public static void main(){

        System.out.println("Testing...");

        //So we can scan the value of the attributes
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); //n= the number of elements

        // Create an array to store the vector values
        int[] values = new int[n];

        // Scan the elements
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value " + (i + 1) + ": ");
            values[i] = scan.nextInt();
        }

        // Create a Vector object
        Vector v = new Vector(values);

        // Ask for the output value
        System.out.print("Enter the output value: ");
        double output = scan.nextDouble();

        // Create a Record object
        Record record = new Record(v, output);

        // Display results
        System.out.println("\n=== Record Information ===");
        System.out.println("Input Vector: " + record.getInput());
        System.out.println("Output Value: " + record.getOutput());
        System.out.println("toString(): " + record.toString());

        // Close the scanner at the end
        scan.close();
    }

}

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

    public Vector getOutput() {
        return output;
    }

    //function to turn into a string
    public String toString() {
        String string_function = "Record: |Input =" + Input * ", Output =" + Output +"|";
        return string_function;
        }
    
}

//Auxiliar help for Record
class Vector {

    private int[] values; // attribute to store numbers

    // Constructor that receives an array of integers
    public Vector(int[] values) {
        this.values = values;
    }

    // toString() is used to print the Vector in a nice way
    public String toString() {
        return Arrays.toString(values);
    }
}