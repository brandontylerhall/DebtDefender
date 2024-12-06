import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Income class represents a source of income and provides methods
 * for managing income data, including adding, removing, editing, and displaying incomes.
 */
public class Income extends Entry {
    private static ArrayList<Income> incomes = new ArrayList<>();
    private final ArrayList<String> fields = new ArrayList<>(Arrays.asList("Name", "Description", "Category", "Recurrence", "Amount", "Begin Date"));

    /**
     * Constructs an Income object with default values.
     */
    public Income() {
        super();
    }

    /**
     * Constructs an Income object with specific details.
     *
     * @param name             The name of the income.
     * @param description      A description of the income.
     * @param category         The category of the income.
     * @param recurrence       How many times, per month, the entry occurs.
     * @param amountPerPayment The amount of each income payment.
     * @param beginDate        The date when the income began.
     */
    public Income(String name, String description, String category, int recurrence, double amountPerPayment, LocalDate beginDate) {
        super(name, description, category, recurrence, amountPerPayment, beginDate);
    }

    /**
     * Returns the list of all incomes.
     *
     * @return The ArrayList of Income objects.
     */
    public static ArrayList<Income> getIncomes() {
        return incomes;
    }

    /**
     * Searches for an income by name and returns it.
     *
     * @param in      The Scanner object for user input.
     * @param incomes The list of incomes to search.
     * @param type    The type of entry ("income" or "expense").
     * @param action  The action to be performed on the entry (e.g., "remove", "edit").
     * @return The Income object if found, otherwise null.
     */
    public static Income search(Scanner in, ArrayList<Income> incomes, String type, String action) {
        if (incomes.isEmpty()) {
            System.out.printf("\nThere is nothing to %s. Try adding an %s first.\n", action, type);
            return null;
        }

        Menu.textBox(incomes);
        System.out.printf("Select the name of the %s you would like to %s\n> ", type, action);

        String searchName = in.nextLine().toLowerCase();
        for (Income entry : incomes) {
            if (entry.getClass().getSimpleName().equalsIgnoreCase(type) &&
                    entry.getName().toLowerCase().contains(searchName)) {
                return entry;
            }
        }

        return null;
    }

    /**
     * Prompts the user to enter the details of a new income and adds it to the list.
     *
     * @param in The Scanner object for user input.
     */
    @Override
    public void add(Scanner in) {
        System.out.print("\nEnter the name of the income\n> ");
        String name = in.nextLine();
        System.out.print("\nEnter the description of the income\n> ");
        String description = in.nextLine();
        System.out.print("\nEnter the category of the income\n> ");
        String category = in.nextLine();
        int recurrence = promptForInt(in, "\nEnter the recurrence of the income per month\n> ");
        double amountPerPayment = promptForDouble(in, "\nEnter the amount of the income\n> ");
        LocalDate beginDate = promptForDate(in, "\nEnter the begin date of the income (YYYY-MM-DD)\n> ");

        Income income = new Income(name, description, category, recurrence, amountPerPayment, beginDate);
        incomes.add(income);
    }

    /**
     * Removes an income from the list based on user input.
     *
     * @param in The Scanner object for user input.
     */
    @Override
    public void remove(Scanner in) {
        Income entryToRemove = Income.search(in, incomes, "income", "remove");

        if (entryToRemove != null) {
            incomes.remove(entryToRemove);
            System.out.println("\nIncome removed successfully!");
        }
    }

    /**
     * Allows the user to edit the details of an existing income.
     *
     * @param in The Scanner object for user input.
     */
    @Override
    public void edit(Scanner in) {
        Income entryToEdit = Income.search(in, incomes, "income", "edit");

        if (entryToEdit != null) {
            Menu.textBox(fields);

            boolean stop = false;
            while (!stop) {
                System.out.print("What would you like to edit?\n> ");
                String fieldToEdit = in.nextLine().toLowerCase();

                switch (fieldToEdit) {
                    case "name":
                        System.out.print("\nEnter the new name of the income\n> ");
                        entryToEdit.setName(in.nextLine());
                        break;
                    case "description":
                        System.out.print("\nEnter the new description of the income\n> ");
                        entryToEdit.setDescription(in.nextLine());
                        break;
                    case "category":
                        System.out.print("\nEnter the new category of the income\n> ");
                        entryToEdit.setCategory(in.nextLine());
                        break;
                    case "recurrence":
                        System.out.print("\nEnter the new recurrence of the income\n> ");
                        entryToEdit.setRecurrence(in.nextInt());
                        in.nextLine();
                        break;
                    case "amount":
                        System.out.print("\nEnter the new amount of the income\n> ");
                        entryToEdit.setAmountPerPayment(in.nextDouble());
                        in.nextLine();
                        break;
                    case "begin date":
                        System.out.print("\nEnter the new begin date of the income (YYYY-MM-DD)\n> ");
                        entryToEdit.setBeginDate(LocalDate.parse(in.nextLine()));
                        break;
                    default:
                        System.out.println("\nThat field doesn't exist");
                        break;
                }

                System.out.print("\nWould you like to edit another field? (y/n)\n> ");

                String ans = in.nextLine();
                stop = ans.equalsIgnoreCase("n");
            }
            System.out.println("Income updated successfully!");
        }
    }

    /**
     * Displays all incomes and allows the user to view more details of a specific income.
     *
     * @param in The Scanner object for user input.
     */
    public void showAll(Scanner in) {
        if (incomes.isEmpty()) {
            System.out.println("\nNothing to show");
            return;
        }

        in.nextLine();

        boolean exit = false;
        while (!exit) {
            Menu.textBox(incomes);
            System.out.print("\nWould you like to see more details of a specific entry? [y/n]\n> ");
            String answer = in.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.print("\nEnter the name of the income: ");
                String nameToSeeMoreOf = in.nextLine();

                boolean found = false;
                for (Entry income : incomes) {
                    if (income.getName().toLowerCase().contains(nameToSeeMoreOf)) {
                        System.out.println(income);
                        found = true;
                        break;
                    } else {
                        System.out.println("That name doesn't exist");
                    }
                }

                if (!found) {
                    System.out.println("Income not found.");
                }
            } else if (answer.equalsIgnoreCase("n")) {
                exit = true;
            } else {
                System.out.println("Invalid option. Please select 'y' or 'n'.");
            }
        }
    }
}