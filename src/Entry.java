import java.time.LocalDate;
import java.util.Scanner;

public abstract class Entry {
    /*
        I didn't make this super clear in the spreadsheet, but since this is a parent class, you put the fields here, not in the child classes.
        Furthermore, it would be in this class where you put the getters and setters to reduce redundancy.
        Since the fields and getters/setters are in the parent class, the children classes can implement them due to inheritance.
     */
    private String name;
    private String description;
    private String category;
    private int recurrence;
    private double amount;
    /*
        We're going to use a LocalDate type here for a bunch of different reasons.
        The most prevalent one is that input validation and data manipulation are much easier by using this data type rather than something like a string.
     */
    private LocalDate beginDate;

    public Entry() {
    }

    public Entry(String name, String description, String category, int recurrence, double amount, LocalDate beginDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.recurrence = recurrence;
        this.amount = amount;
        this.beginDate = beginDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }


    /*
        These are some of the abstract classes I want to implement in the children classes.
        You would want to make these abstract because we are going to have differing output statements between the children classes.
     */

    public abstract void add(Scanner in);

    public abstract void remove();

    public abstract void edit();
}
