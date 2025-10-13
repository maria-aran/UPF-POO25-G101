import java.util.Arrays;

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