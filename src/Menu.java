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
                // What the following comment does is simply tell IntelliJ to not format the code when you press Ctrl + Alt + L (the shortcut to have the IDE reformat your code)
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
                        System.out.println("We hope you will use our service next time!");
                        exit = true;
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
                        System.out.println("income.add()");
                        break;
                    case 2:
                        System.out.println("income.remove()");
                        break;
                    case 3:
                        System.out.println("showAll()");
                        break;
                    case 4:
                        System.out.println("income.edit()");
                        break;
                    case 5:
                        System.out.println("help()");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        exit = true;
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
                        System.out.println("expense.add()");
                        break;
                    case 2:
                        System.out.println("expense.remove()");
                        break;
                    case 3:
                        System.out.println("showAll()");
                        break;
                    case 4:
                        System.out.println("expense.edit()");
                        break;
                    case 5:
                        System.out.println("help()");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        exit = true;
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

    // Right now I'm unsure how I want to go about implementing the help method. I have some thinking to do on this one.
    public void displayHelpMenu() {
        boolean exit = false;
        while (!exit) {
            try {
                // @formatter:off
                System.out.println("Help menu:\n--------------------");
                System.out.print(
                        """
                        1. 
                        2. 
                        3. 
                        4. 
                        5. 
                        6. 
                        7. 
                        --------------------
                        """
                );
                // @formatter:on

                System.out.print("> ");
                switch (in.nextInt()) {
                    case 1:
                        System.out.println("If your having trouble understanding please go to our about page");
                        break;
                    case 2:
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("");
                        break;
                    case 4:
                        System.out.println("");
                        break;
                    case 5:
                        System.out.println("");
                        break;
                    case 6:
                        displayMainMenu();
                        break;
                    case 7:
                        exit = true;
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

}
