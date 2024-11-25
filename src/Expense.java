

public class Expense extends TBD {
    private String item;
    private String paymentMethod;
    private String dueDate;
    private double amount;

    public Expense(String item, String paymentMethod, String dueDate, double amount) {
        this.paymentMethod = paymentMethod;
        this.dueDate = dueDate;
    }
}