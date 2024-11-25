

public class Expense extends TBD{
    private String item;
    private String paymentMethod;
    private String dueDate;
    private double amount;

    public Expense(String item, String paymentMethod, String dueDate, double amount){

        super(item, amount );
        this.paymentMethod = paymentMethod;
        this.dueDate = dueDate;



    }

    public double getAmount() {
        return amount;
    }
    public String getpaymentMethod(){
        return paymentMethod;
    }
    public String getdueDate(){
        return dueDate;

    }

    public void setpaymentMethod(){
        this.paymentMethod = paymentMethod;
    }
    public void setdueData(){
        this.getdueDate = dueDate;
    }
    public void setamount(){
        this.amount = amount;
    }