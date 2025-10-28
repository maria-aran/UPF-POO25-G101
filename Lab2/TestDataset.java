    package Lab2;

    import Lab1.Vector;

    import Lab1.Record;


    public class TestDataset {

        public static void main(String[] args) {

            System.out.println("---- Testing Dataset Class ----\n");

            // === Example data with more varied numbers ===
            Record r1 = new Record(new Vector(new double[]{1.0, 10.0, 5.0}), 100.0);
            Record r2 = new Record(new Vector(new double[]{2.0, 20.0, 15.0}), 200.0);
            Record r3 = new Record(new Vector(new double[]{3.0, 30.0, 25.0}), 300.0);
            Record r4 = new Record(new Vector(new double[]{4.0, 40.0, 35.0}), 400.0);

            // Create dataset with input dimension 3
            Dataset dataset = new Dataset(3);
            dataset.addRecord(r1);
            dataset.addRecord(r2);
            dataset.addRecord(r3);
            dataset.addRecord(r4);

            // Checking getters
            System.out.println("Dataset dimension correct? " + (dataset.getDim() == 3));
            System.out.println("Number of records correct? " + (dataset.getData().size() == 4));
            System.out.println("First record correct? " + dataset.getData().get(0).equals(r1));
            System.out.println("\n ");



            // Checking  methods: First we compute manually results
            double CmeanOutput = 250.0;
            double CstdOutput = 111.80339887498948;

            
            System.out.println("Mean output correct? " + (dataset.meanOutput()== CmeanOutput));
            System.out.println("standardize output correct? " + (dataset.stdOutput() == CstdOutput));
            System.out.println("\n");

            System.out.println("Mean Input Vector: " + dataset.meanInput());
            System.out.println("Mean Output Value: " + dataset.meanOutput());
            System.out.println("Std Input Vector: " + dataset.stdInput());
            System.out.println("Std Output Value: " + dataset.stdOutput());

            // === Testing Standardization ===
            System.out.println("\n ----Testing StandardizedDataset Class----");
            StandardizedDataset standardized = dataset.standardize();

            System.out.println("Standardized dataset created? " + (standardized != null));

            // Transform one record 
            Record transformed = standardized.transform(r1);
            System.out.println("Transformed Record: " + transformed);

            // Print standardized dataset info
            System.out.println("\nStandardized Dataset Info:\n");
            System.out.println(standardized);

            // === Verify mean and std after standardization ===
            Vector newMeanInput = standardized.meanInput();
            double newMeanOutput = standardized.meanOutput();
            Vector newStdInput = standardized.stdInput();
            double newStdOutput = standardized.stdOutput();

            System.out.println("Standardized Mean Input: " + newMeanInput);    // ~0
            System.out.println("Standardized Mean Output: " + newMeanOutput);  // ~0
            System.out.println("Standardized Std Input: " + newStdInput);      // ~1
            System.out.println("Standardized Std Output: " + newStdOutput);    // ~1

            System.out.println("\nEverything works!");
        }
    }

