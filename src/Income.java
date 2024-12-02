import java.time.LocalDate;
import java.util.Scanner;

// This class wouldn't be abstract as it is a "kind of" entry, thus it would be a child of entry.
public class Income extends Entry {
    public Income() {
    }

    // Since this class is inheriting fields from its parent class and has no class specific fields, we use super simply to reduce redundancy.
    public Income(String name, String description, String category, int recurrence, double amount, LocalDate beginDate) {
        super(name, description, category, recurrence, amount, beginDate);
    }

    public Income(String name, double amount) {
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