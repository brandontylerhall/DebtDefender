import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Income extends Entry {
    private static ArrayList<Income> incomes = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>(Arrays.asList("Name", "Description", "Category", "Recurrence", "Amount", "Begin Date"));

    public Income() {
    }

    public Income(String name, String description, String category, int recurrence, double amount, LocalDate beginDate) {
        super(name, description, category, recurrence, amount, beginDate);
    }

    public static ArrayList<Income> getIncomes() {
        return incomes;
    }

    public static void setIncomes(ArrayList<Income> incomes) {
        Income.incomes = incomes;
    }

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

    @Override
    public void add(Scanner in) {
        System.out.print("Enter the name of the income\n> ");
        String name = in.nextLine();
        System.out.print("Enter the description of the income\n> ");
        String description = in.nextLine();
        System.out.print("Enter the category of the income\n> ");
        String category = in.nextLine();
        int recurrence = promptForInt(in, "Enter the recurrence of the income per month\n> ");
        double amount = promptForDouble(in, "Enter the amount of the income\n> ");
        LocalDate beginDate = promptForDate(in, "Enter the begin date of the income (YYYY-MM-DD)\n> ");

        Income income = new Income(name, description, category, recurrence, amount, beginDate);
        incomes.add(income);
    }

    @Override
    public void remove(Scanner in) {
        Income entryToRemove = Income.search(in, incomes, "income", "remove");

        if (entryToRemove != null) {
            incomes.remove(entryToRemove);
            System.out.println("Income removed successfully!");
        }
    }

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
                        System.out.print("Enter the new name of the income\n> ");
                        entryToEdit.setName(in.nextLine());
                        break;
                    case "description":
                        System.out.print("Enter the new description of the income\n> ");
                        entryToEdit.setDescription(in.nextLine());
                        break;
                    case "category":
                        System.out.print("Enter the new category of the income\n> ");
                        entryToEdit.setCategory(in.nextLine());
                        break;
                    case "recurrence":
                        System.out.print("Enter the new recurrence of the income\n> ");
                        entryToEdit.setRecurrence(in.nextInt());
                        in.nextLine();
                        break;
                    case "amount":
                        System.out.print("Enter the new amount of the income\n> ");
                        entryToEdit.setAmount(in.nextDouble());
                        in.nextLine();
                        break;
                    case "begin date":
                        System.out.print("Enter the new begin date of the income (YYYY-MM-DD)\n> ");
                        entryToEdit.setBeginDate(LocalDate.parse(in.nextLine()));
                        break;
                    default:
                        System.out.println("That field doesn't exist");
                        break;
                }

                System.out.print("Would you like to edit another field? (y/n)\n> ");

                String ans = in.nextLine();
                stop = ans.equalsIgnoreCase("n");
            }
            System.out.println("Income updated successfully!");
        }
    }

    public void showAll(Scanner in) {
        if (incomes.isEmpty()) {
            System.out.println("Nothing to show");
            return;
        }

        in.nextLine();

        boolean exit = false;
        while (!exit) {
            Menu.textBox(incomes);
            System.out.print("Would you like to see more details of a specific entry? [y/n]: ");
            String answer = in.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.print("Enter the name of the income: ");
                String nameToSeeMoreOf = in.nextLine();

                boolean found = false;
                // Iterates through incomes, attempting to find the one the user wants
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