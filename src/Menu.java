import java.util.Scanner;

public class Menu {
    private Scanner in;

    public Menu() {
        in = new Scanner(System.in);
    }

    public void displayMainMenu() {
        boolean exit = false;
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("Main menu:\n----------");
                System.out.print(
                        """
                        1. Income
                        2. Expense
                        3. Help
                        4. Exit
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
                        System.out.println("help");
                        break;
                    case 4:
                        System.out.println("Til next time!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
            }
        }
    }

    public void displayIncomeMenu() {
        boolean exit = false;
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("Income menu:\n--------------------");
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
                        System.out.println("add income");
                        break;
                    case 2:
                        System.out.println("remove income");
                        break;
                    case 3:
                        System.out.println("view all income");
                        break;
                    case 4:
                        System.out.println("edit income");
                        break;
                    case 5:
                        System.out.println("help");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        exit = true;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
            }
        }
    }

    public void displayExpenseMenu() {
        boolean exit = false;
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("Expense menu:\n--------------------");
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
                        System.out.println("add expense");
                        break;
                    case 2:
                        System.out.println("remove expense");
                        break;
                    case 3:
                        System.out.println("view all expenses");
                        break;
                    case 4:
                        System.out.println("edit expense");
                        break;
                    case 5:
                        System.out.println("help");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        exit = true;
                    default:
                        System.out.println("Invalid option\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please select a number.");
            }
        }
    }

    public void displayHelpMenu() {

    }

}