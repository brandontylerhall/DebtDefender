import java.time.LocalDate;
import java.util.Scanner;

public class Expense extends Entry {
    public Expense() {
    }

    // Since this class is inheriting fields from its parent class and has no class specific fields, we use super simply to reduce redundancy.
    public Expense(String name, String description, String category, int recurrence, double amount, LocalDate beginDate) {
        super(name, description, category, recurrence, amount, beginDate);
    }

    // Since we're extending Entry in this class, we HAVE to implement the abstract classes from before otherwise Java has a stroke.
    @Override
    public void add(Scanner in) {

    }

    @Override
    public void remove() {

    }

    @Override
    public void edit() {

    }
}