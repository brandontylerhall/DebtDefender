import java.util.ArrayList;

public abstract class Pay extends TBD{
    private string item;
    private double amount;




    private static ArrayList <Pay> pays = new ArrayList<>();

    public Pay( String item , double amount){
        this.item = item;
        this.amount = amount;
    }

    public string getItem() {
        return item;
    }

    public void setItem(string item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}