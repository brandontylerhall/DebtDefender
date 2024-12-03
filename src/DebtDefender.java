import java.io.IOException;

public class DebtDefender {
    public static void main(String[] args) throws IOException {
        CSV csv = new CSV();
        csv.createFile();
        csv.readFromCSV(Income.getIncomes(), Expense.getExpenses());

        Menu menu = new Menu();
        System.out.println("Welcome to Debt Defender!");

        menu.displayMainMenu();
    }
}