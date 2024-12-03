import java.time.LocalDate;
import java.util.Scanner;

public abstract class Entry {
    private String name;
    private String description;
    private String category;
    private int recurrence;
    private double amount;
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

    protected static int promptForInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) {
                int recurrence = in.nextInt();
                in.nextLine();
                return recurrence;
            } else {
                System.out.println("Invalid. Please enter an integer.");
                in.nextLine();
            }
        }
    }

    protected static double promptForDouble(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextDouble()) {
                int amount = in.nextInt();
                in.nextLine();
                return amount;
            } else {
                System.out.println("Invalid. Please enter an integer.");
                in.nextLine();
            }
        }
    }

    protected static LocalDate promptForDate(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(in.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a valid date format.");
            }
        }
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

    public abstract void add(Scanner in);

    public abstract void remove(Scanner in);

    public abstract void edit(Scanner in);

    public abstract void showAll(Scanner in);

    public String toString() {
        if (this.recurrence == 1) {
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d time per month\nAmount: $%.2f\nBegin date: %s", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmount(), this.getBeginDate());
        } else {
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d times per month\nAmount: $%.2f\nBegin date: %s", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmount(), this.getBeginDate());
        }
    }

    public String toCSVString() {
        return name + "," + description + "," + category + "," + recurrence + "," + amount + "," + beginDate;
    }

}