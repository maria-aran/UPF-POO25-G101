import java.util.Scanner;

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
        System.out.println("To string " + record.toString());

        // Close the scanner at the end
        scan.close();
    }

}