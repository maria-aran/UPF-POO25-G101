package Lab2;

// extends dataset and 
public class StandardizedDataset extends Dataset {
    private Vector mi;
    private Vector si;
    private double mo;
    private double so;

    public StandardizedDataset(Dataset d, Vector mi, Vector si, double mo, double so){
        super(d.getDim()); //call dimension in constructor
        this.mi=mi;
        this.si=si;
        this.mo=mo;
        this.so=so;
    }

}

//call constructor of big class