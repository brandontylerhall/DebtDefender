import java.util.ArrayList;
import java.util.Comparator;

public class ReportHandler {
    public static void generateReport(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        ArrayList<Income> highestThreeIncomes = new ArrayList<>();
        ArrayList<Expense> highestThreeExpenses = new ArrayList<>();
        double highestAmount1 = 0.0;
        double highestAmount2 = 0.0;
        double highestAmount3 = 0.0;


        for (Income income : incomes) {
            if (income.getAmount() > highestAmount1) {
                highestAmount3 = highestAmount2;
                highestAmount2 = highestAmount1;
                highestAmount1 = income.getAmount();
            } else if (income.getAmount() > highestAmount2) {
                highestAmount3 = highestAmount2;
                highestAmount2 = income.getAmount();
            } else if (income.getAmount() > highestAmount3) {
                highestAmount3 = income.getAmount();
            }
        }

        for (Income income : incomes) {
            if (income.getAmount() == highestAmount1 || income.getAmount() == highestAmount2 || income.getAmount() == highestAmount3) {
                highestThreeIncomes.add(income);
            }
        }

        highestThreeIncomes.sort(new Comparator<Income>() {
            @Override
            public int compare(Income o1, Income o2) {
                return Double.compare(o2.getAmount(), o1.getAmount());
            }
        });


        System.out.println("\nYour top three highest incomes:");
        Menu.textBox(highestThreeIncomes);

        highestAmount1 = 0.0;
        highestAmount2 = 0.0;
        highestAmount3 = 0.0;

        for (Expense expense : expenses) {
            if (expense.getAmount() > highestAmount1) {
                highestAmount3 = highestAmount2;
                highestAmount2 = highestAmount1;
                highestAmount1 = expense.getAmount();
            } else if (expense.getAmount() > highestAmount2) {
                highestAmount3 = highestAmount2;
                highestAmount2 = expense.getAmount();
            } else if (expense.getAmount() > highestAmount3) {
                highestAmount3 = expense.getAmount();
            }
        }

        for (Expense expense : expenses) {
            if (expense.getAmount() == highestAmount1 || expense.getAmount() == highestAmount2 || expense.getAmount() == highestAmount3) {
                highestThreeExpenses.add(expense);
            }
        }

        highestThreeExpenses.sort(new Comparator<Expense>() {
            @Override
            public int compare(Expense o1, Expense o2) {
                return Double.compare(o2.getAmount(), o1.getAmount());
            }
        });

        System.out.println("\nYour top three highest expenses:");
        Menu.textBox(highestThreeExpenses);
    }
}
