public class TestRecord {
    public static void main(String[] args) {

        System.out.println("Testing Record Class...");

        //Example inputs
        Vector exampleVector = new Vector(new double[]{3, 6, 9});
        double exampleOutput = 12.5;
        Record record = new Record(exampleVector, exampleOutput);

        //Checking values match
        System.out.println("Input correct? " + record.getInput().equals(exampleVector));
        System.out.println("Output correct? " + (record.getOutput() == exampleOutput));
        System.out.println("To string correct? " + record.toString().equals("Record: |Input = [3.0, 6.0, 9.0], Output = 12.5|"));

        //Printing values
        System.out.println("\n=== Record Information ===");
        System.out.println("Input Vector: " + record.getInput());
        System.out.println("Output Value: " + record.getOutput());
        System.out.println("To string: " + record.toString());


        System.out.println("\nTesting Vector Class...");

        //Creating two example vectos with both constructors
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v2 = new Vector(3, 2.0);

        //Testing the methods
        Vector sum = v1.add(v2);
        Vector diff = v1.subtract(v2);
        Vector mult = v1.multiply(v2);
        Vector div = v1.divide(v2);
        Vector scalarMult = v1.multiply(2);
        Vector scalarDiv = v1.divide(2);
        Vector sqrt = v1.sqrt();
        double dot = v1.dot(v2);
        double norm = v1.norm();

        //Printing and checking if everything works
        System.out.println("Dimension correct? " + (v1.getDim() == 3));
        System.out.println("Get value correct? " + (v1.getValue(0) == 1.0));
        System.out.println("Addition correct? " + sum.toString().equals("[3.0, 4.0, 5.0]"));
        System.out.println("Subtraction correct? " + diff.toString().equals("[-1.0, 0.0, 1.0]"));
        System.out.println("Multiplication correct? " + mult.toString().equals("[2.0, 4.0, 6.0]"));
        System.out.println("Division correct? " + div.toString().equals("[0.5, 1.0, 1.5]"));
        System.out.println("Scalar multiply correct? " + scalarMult.toString().equals("[2.0, 4.0, 6.0]"));
        System.out.println("Scalar divide correct? " + scalarDiv.toString().equals("[0.5, 1.0, 1.5]"));
        System.out.println("Sqrt correct? " + sqrt.toString().equals("[" + Math.sqrt(1) + ", " + Math.sqrt(2) + ", " + Math.sqrt(3) + "]"));
        System.out.println("Dot product correct? " + (dot == 12.0));
        System.out.println("Norm correct? " + (norm == Math.sqrt(14)));

        //Print information
        System.out.println("\n=== Vector Information ===");
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        System.out.println("Sum: " + sum);
        System.out.println("Subtract: " + diff);
        System.out.println("Multiply: " + mult);
        System.out.println("Divide: " + div);
        System.out.println("Multiply by scalar: " + scalarMult);
        System.out.println("Divide by scalar: " + scalarDiv);
        System.out.println("Square root: " + sqrt);
        System.out.println("Dot product: " + dot);
        System.out.println("Norm of v1: " + norm);
    }
}
