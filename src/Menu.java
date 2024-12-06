import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Menu class provides a command-line interface for managing
 * income and expenses. It allows users to add, remove, view, and edit
 * entries, as well as generate financial reports.
 */
public class Menu {
    private Scanner in;
    private CSV csvHandler = new CSV();
    private boolean exit = false;

    /**
     * Constructs a Menu object and initializes the scanner for user input.
     */
    public Menu() {
        in = new Scanner(System.in);
    }

    /**
     * Displays a list of items in a formatted text box.
     *
     * @param list The list of items to display.
     * @param <T>  The type of items in the list.
     */
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
            } else if (item instanceof String) {
                itemsBuilder.append(item);
            } else {
                itemsBuilder.append(item.toString());
            }

            if (i % 3 == 2 && i != list.size() - 1) {
                itemsBuilder.append("\n");
            } else if (i != list.size() - 1) {
                itemsBuilder.append(", ");
            }
        }
        String items = itemsBuilder.toString();

        int padding = 2;
        int width = items.lines().mapToInt(String::length).max().getAsInt() + 2 * padding;

        System.out.println("\n+" + "-".repeat(width) + "+");

        for (String line : items.split("\n")) {
            System.out.println("|" + " ".repeat(padding) + line + " ".repeat(width - line.length() - padding) + "|");
        }

        System.out.println("+" + "-".repeat(width) + "+");
    }

    /**
     * Sets the exit flag to indicate whether the program should end.
     *
     * @param exit true to exit the program, false to continue.
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Displays the main menu and handles user input for menu navigation.
     */
    public void displayMainMenu() {
        while (!exit) {
            try {
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
                        displayHelpMenu();
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

    /**
     * Displays the income menu and handles user input for managing incomes.
     */
    public void displayIncomeMenu() {
        while (!exit) {
            try {
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

    /**
     * Displays the expense menu and handles user input for managing expenses.
     */
    public void displayExpenseMenu() {
        while (!exit) {
            try {
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

    /**
     * Displays the help menu and provides information about the system, incomes, and expenses.
     */

    // TODO: add String helpLocation to the parameters of this method and refactor
    public void displayHelpMenu() {
        while (!exit) {
            try {
                System.out.println("\nHelp menu:\n----------");
                System.out.print(""" 
                        1. System help
                        2. Income help
                        3. Expense help
                        4. Back to main menu
                        5. Exit
                        ----------
                        """);
                System.out.print("> ");
                switch (in.nextInt()) {
                    case 1:
                        System.out.println("""
                                System issues can be frustrating! Here's some basic troubleshooting:

                                * Restart the program: This often resolves minor glitches.
                                * Check your input: Make sure you're entering information in the correct format.
                                """);
                        break;
                    case 2:
                        System.out.println("""
                                Tracking your income is essential for managing your finances!

                                Here's how to enter your income:

                                * Name: Give this income source a short, descriptive name (e.g., "Paycheck", "Side Hustle").
                                * Description:  Add more details about this income source if needed.
                                * Category: Enter the category that best fits this income (e.g., "Salary", "Investments").
                                * Amount:  Enter the numerical value of your income (e.g., 2500.50).
                                * Recurrence: How many times per month do you receive this income?
                                  (e.g., "1" for monthly, "2" for bi-weekly)
                                * Begin Date: Enter the date when you started receiving this income (e.g., "2024-12-01").

                                Tips for accurate tracking:

                                * Be sure to enter the correct amount and recurrence to avoid miscalculations.
                                * If you have multiple income sources, enter each one separately.
                                """);
                        break;
                    case 3:
                        System.out.println("""
                                Keeping track of your expenses helps you understand where your money goes!

                                Here's how to enter your expenses:

                                * Name:  Provide a clear name for the expense (e.g., "Rent", "Groceries", "Student Loan").
                                * Description: Add more details about this expense if needed.
                                * Category: Enter the category that best describes this expense (e.g., "Housing", "Food", "Debt").
                                * Amount: Enter the numerical value of the expense (e.g., 100, 50.75).
                                * Recurrence: How many times per month does this expense occur?
                                  (e.g., "1" for monthly, "4" for weekly)
                                * Begin Date: Enter the date when you started incurring this expense (e.g., "2023-05-15").

                                Tips for effective expense tracking:

                                * Be as detailed as possible when naming your expenses (e.g., "Electric Bill" instead of just "Bills").
                                """);
                        break;
                    case 4:
                        displayMainMenu();
                        break;
                    case 5:
                        setExit(true);
                        break;
                    default:
                        System.out.println("Invalid option. Select a number from the list.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
                in.nextLine();
            }
        }
    }
}