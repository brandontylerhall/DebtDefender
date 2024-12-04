import java.time.LocalDate;
import java.util.Scanner;

public abstract class Entry {
    private String name;
    private String description;
    private String category;
    private int recurrence;
    private double amountPerPayment;
    private LocalDate beginDate;

    public Entry() {
    }

    public Entry(String name, String description, String category, int recurrence, double amountPerPayment, LocalDate beginDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.recurrence = recurrence;
        this.amountPerPayment = amountPerPayment;
        this.beginDate = beginDate;
    }

    public double getAmountPerMonth() {
        return amountPerPayment * recurrence;
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
                double amount = in.nextDouble();
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

    public double getAmountPerPayment() {
        return amountPerPayment;
    }

    public void setAmountPerPayment(double amountPerPayment) {
        this.amountPerPayment = amountPerPayment;
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
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d time per month\nAmount: $%.2f\nBegin date: %s\n", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmountPerPayment(), this.getBeginDate());
        } else {
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d times per month\nAmount: $%.2f\nBegin date: %s", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmountPerPayment(), this.getBeginDate());
        }
    }

    public String toCSVString() {
        return name + "," + description + "," + category + "," + recurrence + "," + amountPerPayment + "," + beginDate;
    }

}