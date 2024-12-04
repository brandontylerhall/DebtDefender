import java.util.ArrayList;
import java.util.Scanner;

public class ReportHandler {
    public static void beginReport(ArrayList<Income> incomes, ArrayList<Expense> expenses, Scanner in) {
        if (incomes.isEmpty() && expenses.isEmpty()) {
            System.out.println("\nThere is nothing to report at this time.");
            return;
        }

        double totalIncome = 0.0;
        double totalExpenses = 0.0;
        double debtRatio = 0.0;

        for (Income income : incomes) {
            totalIncome += (income.getAmount() * income.getRecurrence());
        }

        for (Expense expense : expenses) {
            totalExpenses += (expense.getAmount() * expense.getRecurrence());
        }

        debtRatio = (totalExpenses / totalIncome) * 100;

        System.out.printf("\nYour total monthly income comes out to: $%.2f/mo\n", totalIncome);
        System.out.printf("\nYour total monthly expenses comes out to: $%.2f/mo\n", totalExpenses);
        System.out.printf("\nYour monthly debt to income comes out to: %.2f%%.\n" +
                "\nThis means that for every dollar you make, %.2f cents goes to paying off debt.\n", debtRatio, debtRatio);

        if (debtRatio >= 0 && debtRatio <= 36) {
            System.out.println("\nYou're doing great managing your money! Keep doing what you're doing!");
        } else if (debtRatio > 36 && debtRatio <= 49) {
            System.out.println("\nYou're doing good! Look at your expenses and see if you can bring that number down!");
        } else if (debtRatio > 49 && debtRatio <= 70) {
            System.out.println("\nThis isn't ideal. You should greatly consider reevaluating some of your expenses and get that number well below 50%.");
        } else if (debtRatio > 70 && debtRatio <= 90) {
            System.out.println("\nYou're essentially living paycheck to paycheck. You should consider a deep dive into your expenses and make some dire adjustments.");
        } else if (debtRatio > 100) {
            System.out.println("\nBy my calculations, you're spending more than you're making. You should greatly consider seeking professional assistance.");
        }

        in.nextLine();
        System.out.printf("\nWould you like to break this down and go deeper? [y/n]\n> ");
        String ans = in.nextLine();

        while (!ans.equals("y") && !ans.equals("n")) {
            System.out.printf("\nInvalid input. Enter 'y' or 'n'.\n> ");
            ans = in.nextLine();
        }

        if (ans.equalsIgnoreCase("y")) {
            categoryReport(incomes, expenses, in);
        }

    }


    public static void categoryReport(ArrayList<Income> incomes, ArrayList<Expense> expenses, Scanner in) {
        ArrayList<Income> largestThreeIncomeCategories = new ArrayList<>();
        ArrayList<Expense> largestThreeExpenseCategories = new ArrayList<>();


        System.out.print("\nWould you like to break this report down further? [y/n]\n> ");

        in.nextLine();
        String ans = in.nextLine();

        while (!ans.equals("y") && !ans.equals("n")) {
            System.out.println("Invalid input. Enter 'y' or 'n'.");
            ans = in.nextLine();
        }

        if (ans.equals("y")) {
            topThree(incomes, expenses, in);
        }


    }

    public static void topThree(ArrayList<Income> incomes, ArrayList<Expense> expenses, Scanner in) {
        ArrayList<Income> largestThreeIncomes = new ArrayList<>();
        ArrayList<Expense> largestThreeExpenses = new ArrayList<>();
        double largestAmount1 = 0.0;
        double largestAmount2 = 0.0;
        double largestAmount3 = 0.0;

        for (Income income : incomes) {
            if (income.getAmount() > largestAmount1) {
                largestAmount3 = largestAmount2;
                largestAmount2 = largestAmount1;
                largestAmount1 = income.getAmount();
            } else if (income.getAmount() > largestAmount2) {
                largestAmount3 = largestAmount2;
                largestAmount2 = income.getAmount();
            } else if (income.getAmount() > largestAmount3) {
                largestAmount3 = income.getAmount();
            }
        }

        for (Income income : incomes) {
            if (income.getAmount() == largestAmount1 || income.getAmount() == largestAmount2 || income.getAmount() == largestAmount3) {
                largestThreeIncomes.add(income);
            }
        }

        largestThreeIncomes.sort((o1, o2) -> Double.compare(o2.getAmount(), o1.getAmount()));

        if (largestThreeIncomes.isEmpty()) {
            System.out.println("\nThere is no income report at this time.");
        } else {
            if (largestThreeIncomes.size() == 1) {
                System.out.print("\nYour largest incomes:");
                Menu.textBox(largestThreeIncomes);
            } else {
                System.out.printf("\nYour %d largest incomes:", largestThreeIncomes.size());
                Menu.textBox(largestThreeIncomes);
            }
        }

        largestAmount1 = 0.0;
        largestAmount2 = 0.0;
        largestAmount3 = 0.0;

        for (Expense expense : expenses) {
            if (expense.getAmount() > largestAmount1) {
                largestAmount3 = largestAmount2;
                largestAmount2 = largestAmount1;
                largestAmount1 = expense.getAmount();
            } else if (expense.getAmount() > largestAmount2) {
                largestAmount3 = largestAmount2;
                largestAmount2 = expense.getAmount();
            } else if (expense.getAmount() > largestAmount3) {
                largestAmount3 = expense.getAmount();
            }
        }

        for (Expense expense : expenses) {
            if (expense.getAmount() == largestAmount1 || expense.getAmount() == largestAmount2 || expense.getAmount() == largestAmount3) {
                largestThreeExpenses.add(expense);
            }
        }

        largestThreeExpenses.sort((o1, o2) -> Double.compare(o2.getAmount(), o1.getAmount()));

        if (largestThreeExpenses.isEmpty()) {
            System.out.println("\nThere is no expense report at this time.");
        } else {
            if (largestThreeExpenses.size() == 1) {
                System.out.print("\nYour largest expense:");
                Menu.textBox(largestThreeExpenses);
            } else {
                System.out.printf("\nYour %d largest expenses:", largestThreeExpenses.size());
                Menu.textBox(largestThreeExpenses);
            }
        }


    }
}
