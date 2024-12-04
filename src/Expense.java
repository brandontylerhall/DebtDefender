import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Expense extends Entry {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private final ArrayList<String> fields = new ArrayList<>(Arrays.asList("Name", "Description", "Category", "Recurrence", "Amount", "Begin Date"));

    public Expense() {
        super();
    }

    public Expense(String name, String description, String category, int recurrence, double amount, LocalDate beginDate) {
        super(name, description, category, recurrence, amount, beginDate);
    }

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public static Expense search(Scanner in, ArrayList<Expense> expenses, String type, String action) {
        if (expenses.isEmpty()) {
            System.out.printf("There is nothing to %s. Try adding an %s first.\n", action, type);
        }

        in.nextLine();

        Menu.textBox(expenses);
        System.out.printf("Select the name of the %s you would like to %s\n> ", type, action);

        String searchName = in.nextLine().toLowerCase();
        for (Expense entry : expenses) {
            if (entry.getClass().getSimpleName().equalsIgnoreCase(type) &&
                    entry.getName().toLowerCase().contains(searchName)) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public void add(Scanner in) {
        System.out.print("\nEnter the name of the expense\n> ");
        String name = in.nextLine();
        System.out.print("\nEnter the description of the expense\n> ");
        String description = in.nextLine();
        System.out.print("\nEnter the category of the expense\n> ");
        String category = in.nextLine();
        int recurrence = promptForInt(in, "\nEnter the recurrence of the expense per month\n> ");
        double amount = promptForDouble(in, "\nEnter the amount of the expense\n> ");
        LocalDate beginDate = promptForDate(in, "\nEnter the begin date of the expense (YYYY-MM-DD)\n> ");

        Expense expense = new Expense(name, description, category, recurrence, amount, beginDate);
        expenses.add(expense);
    }

    @Override
    public void remove(Scanner in) {
        Expense entryToRemove = Expense.search(in, expenses, "expense", "remove");

        if (entryToRemove != null) {
            expenses.remove(entryToRemove);
            System.out.println("\nExpense removed successfully!");
        } else {
            System.out.println("\nExpense not found.");
        }
    }

    @Override
    public void edit(Scanner in) {
        Expense entryToEdit = Expense.search(in, expenses, "expense", "edit");

        if (entryToEdit != null) {
            Menu.textBox(fields);

            boolean stop = false;
            while (!stop) {
                System.out.print("What would you like to edit?\n> ");
                String fieldToEdit = in.nextLine().toLowerCase();

                switch (fieldToEdit) {
                    case "name":
                        System.out.print("\nEnter the new name of the expense\n> ");
                        entryToEdit.setName(in.nextLine());
                        break;
                    case "description":
                        System.out.print("\nEnter the new description of the expense\n> ");
                        entryToEdit.setDescription(in.nextLine());
                        break;
                    case "category":
                        System.out.print("\nEnter the new category of the expense\n> ");
                        entryToEdit.setCategory(in.nextLine());
                        break;
                    case "recurrence":
                        System.out.print("\nEnter the new recurrence of the expense\n> ");
                        entryToEdit.setRecurrence(in.nextInt());
                        in.nextLine();
                        break;
                    case "amount":
                        System.out.print("\nEnter the new amount of the expense\n> ");
                        entryToEdit.setAmountPerPayment(in.nextDouble());
                        in.nextLine();
                        break;
                    case "begin date":
                        System.out.print("\nEnter the new begin date of the expense (YYYY-MM-DD)\n> ");
                        entryToEdit.setBeginDate(LocalDate.parse(in.nextLine()));
                        break;
                    default:
                        System.out.println("That field doesn't exist");
                        break;
                }

                System.out.print("\nWould you like to edit another field? (y/n)\n> ");

                String ans = in.nextLine();
                stop = ans.equalsIgnoreCase("n");
            }
            System.out.println("\nExpense updated successfully!");
        } else {
            System.out.println("\nExpense not found.");
        }
    }

    public void showAll(Scanner in) {
        if (expenses.isEmpty()) {
            System.out.println("\nNothing to show");
            return;
        }

        in.nextLine();

        boolean exit = false;
        while (!exit) {
            Menu.textBox(expenses);
            System.out.print("\nWould you like to see more details of a specific entry? [y/n]\n> ");
            String answer = in.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.print("\nEnter the name of the expense: ");
                String nameToSeeMoreOf = in.nextLine();

                boolean found = false;
                // Iterates through expenses, attempting to find the one the user wants
                for (Entry expense : expenses) {
                    if (expense.getName().toLowerCase().contains(nameToSeeMoreOf)) {
                        System.out.println(expense);
                        found = true;
                        break;
                    } else {
                        System.out.println("That name doesn't exist");
                    }
                }

                if (!found) {
                    System.out.println("\nExpense not found.");
                }
            } else if (answer.equalsIgnoreCase("n")) {
                exit = true;
            } else {
                System.out.println("Invalid option. Please select 'y' or 'n'.");
            }
        }
    }
}