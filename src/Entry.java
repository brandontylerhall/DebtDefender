import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Entry class is an abstract class that represents a generic financial entry,
 * which can be either an income or an expense. It provides common fields and methods
 * for managing financial data.
 */
public abstract class Entry {
    private String name;
    private String description;
    private String category;
    private int recurrence;
    private double amountPerPayment;
    private LocalDate beginDate;

    /**
     * Constructs an Entry object with default values.
     */
    public Entry() {
    }

    /**
     * Constructs an Entry object with specific details.
     *
     * @param name             The name of the entry.
     * @param description      A description of the entry.
     * @param category         The category of the entry.
     * @param recurrence       How many times, per month, the entry occurs.
     * @param amountPerPayment The amount of each payment for the entry.
     * @param beginDate        The date when the entry began.
     */
    public Entry(String name, String description, String category, int recurrence, double amountPerPayment, LocalDate beginDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.recurrence = recurrence;
        this.amountPerPayment = amountPerPayment;
        this.beginDate = beginDate;
    }

    /**
     * Calculates and returns the total amount for the entry per month.
     *
     * @return The total amount per month.
     */
    public double getAmountPerMonth() {
        return amountPerPayment * recurrence;
    }

    /**
     * Prompts the user to enter an integer value and validates the input.
     *
     * @param in     The Scanner object for user input.
     * @param prompt The prompt message to display to the user.
     * @return The valid integer entered by the user.
     */
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

    /**
     * Prompts the user to enter a double value and validates the input.
     *
     * @param in     The Scanner object for user input.
     * @param prompt The prompt message to display to the user.
     * @return The valid double entered by the user.
     */
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

    /**
     * Prompts the user to enter a date in the format YYYY-MM-DD and validates the input.
     *
     * @param in     The Scanner object for user input.
     * @param prompt The prompt message to display to the user.
     * @return The valid LocalDate entered by the user.
     */
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

    // Getters and setters for all fields

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

    // Abstract methods to be implemented by subclasses

    /**
     * Adds a new entry.
     *
     * @param in The Scanner object for user input.
     */
    public abstract void add(Scanner in);

    /**
     * Removes an existing entry.
     *
     * @param in The Scanner object for user input.
     */
    public abstract void remove(Scanner in);

    /**
     * Edits an existing entry.
     *
     * @param in The Scanner object for user input.
     */
    public abstract void edit(Scanner in);

    /**
     * Shows all entries.
     *
     * @param in The Scanner object for user input.
     */
    public abstract void showAll(Scanner in);

    /**
     * Returns a string representation of the entry.
     *
     * @return The string representation of the entry.
     */
    @Override
    public String toString() {
        if (this.recurrence == 1) {
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d time per month\nAmount: $%.2f\nBegin date: %s\n", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmountPerPayment(), this.getBeginDate());
        } else {
            return String.format("\nName: %s\nDescription: %s\nCategory: %s\nRecurrence: %d times per month\nAmount: $%.2f\nBegin date: %s", this.getName(), this.getDescription(), this.getCategory(), this.getRecurrence(), this.getAmountPerPayment(), this.getBeginDate());
        }
    }

    /**
     * Returns a CSV string representation of the entry.
     *
     * @return The CSV string representation of the entry.
     */
    public String toCSVString() {
        return name + "," + description + "," + category + "," + recurrence + "," + amountPerPayment + "," + beginDate;
    }
}