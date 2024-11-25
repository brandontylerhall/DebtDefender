import java.util.ArrayList;

public abstract class Pay extends TBD {
    private String item;
    private double amount;
    private static ArrayList<Pay> pays = new ArrayList<>();

    public Pay(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }
}