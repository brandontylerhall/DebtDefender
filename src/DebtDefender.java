/**
 * The DebtDefender class is the main class of the application.
 * It initializes the program, loads data from a CSV file,
 * and starts the main menu.
 */
public class DebtDefender {

    /**
     * The main method of the DebtDefender application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CSV csv = new CSV();
        csv.createFile();
        csv.readFromCSV(Income.getIncomes(), Expense.getExpenses());

        Menu menu = new Menu();
        System.out.println("Welcome to Debt Defender!");

        menu.displayMainMenu();
    }
}