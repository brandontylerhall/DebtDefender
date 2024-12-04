import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner in;
    private CSV csvHandler = new CSV();
    private boolean exit = false;

    public Menu() {
        in = new Scanner(System.in);
    }

    public static <T> void textBox(ArrayList<T> list) {
        if (list.isEmpty()) {
            return;
        }

        StringBuilder itemsBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);

            if (item instanceof Income || item instanceof Expense) {
                Entry entry = (Entry) item;
                itemsBuilder.append(entry.getName());
            } else if (item instanceof String) {  // Specifically check for String type
                itemsBuilder.append(item); // Append the String directly, without toString()
            } else {
                itemsBuilder.append(item.toString()); // For other types, use toString()
            }

            if (i % 3 == 2 && i != list.size() - 1) {
                itemsBuilder.append("\n");
            } else if (i != list.size() - 1) {
                itemsBuilder.append(", ");
            }
        }
        String items = itemsBuilder.toString();

        // Calculate width (using the formatted items string)
        int padding = 2;
        int width = items.lines().mapToInt(String::length).max().getAsInt() + 2 * padding;

        System.out.println("\n+" + "-".repeat(width) + "+");

        // Text inside the box (using the formatted items string)
        for (String line : items.split("\n")) {
            System.out.println("|" + " ".repeat(padding) + line + " ".repeat(width - line.length() - padding) + "|");
        }

        System.out.println("+" + "-".repeat(width) + "+");
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void displayMainMenu() {
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("\nMain menu:\n----------");
                System.out.print(
                        """
                        1. Income
                        2. Expense
                        3. Finance Report
                        4. Help
                        5. Exit
                        ----------
                        """
                );
                // @formatter:on

                System.out.print("> ");
                switch (in.nextInt()) {
                    case 1:
                        displayIncomeMenu();
                        break;
                    case 2:
                        displayExpenseMenu();
                        break;
                    case 3:
                        ReportHandler.beginReport(Income.getIncomes(), Expense.getExpenses(), in);
                        break;
                    case 4:
                        System.out.println("help");
                        break;
                    case 5:
                        setExit(true);
                        csvHandler.writeToCSV(Income.getIncomes(), Expense.getExpenses());
                        break;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
                in.nextLine();
            }
        }
    }

    public void displayIncomeMenu() {
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("\nIncome menu:\n--------------------");
                System.out.print(
                        """
                        1. Add an income
                        2. Remove an income
                        3. View all income
                        4. Edit an income
                        5. Help
                        6. Back to main menu
                        7. Exit
                        --------------------
                        """
                );
                // @formatter:on

                System.out.print("> ");
                switch (in.nextInt()) {
                    case 1:
                        in.nextLine();
                        Income income = new Income();
                        income.add(in);
                        break;
                    case 2:
                        in.nextLine();
                        Income removeIncome = new Income();
                        removeIncome.remove(in);
                        break;
                    case 3:
                        Income showIncome = new Income();
                        showIncome.showAll(in);
                        break;
                    case 4:
                        in.nextLine();
                        Income editIncome = new Income();
                        editIncome.edit(in);
                        break;
                    case 5:
                        System.out.println("help");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        setExit(true);
                        csvHandler.writeToCSV(Income.getIncomes(), Expense.getExpenses());
                        break;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
                in.nextLine();
            }
        }
    }

    public void displayExpenseMenu() {
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("\nExpense menu:\n--------------------");
                System.out.print(
                        """
                        1. Add an expense
                        2. Remove an expense
                        3. View all expenses
                        4. Edit an expense
                        5. Help
                        6. Back to main menu
                        7. Exit
                        --------------------
                        """
                );
                // @formatter:on

                System.out.print("> ");
                switch (in.nextInt()) {
                    case 1:
                        in.nextLine();
                        Expense expense = new Expense();
                        expense.add(in);
                        break;
                    case 2:
                        in.nextLine();
                        Expense removeExpense = new Expense();
                        removeExpense.remove(in);
                        break;
                    case 3:
                        Expense showExpense = new Expense();
                        showExpense.showAll(in);
                        break;
                    case 4:
                        Expense editExpense = new Expense();
                        editExpense.edit(in);
                        break;
                    case 5:
                        System.out.println("help");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        setExit(true);
                        csvHandler.writeToCSV(Income.getIncomes(), Expense.getExpenses());
                        break;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
                in.nextLine();
            }
        }
    }

    public void displayHelpMenu() {

    }
}