import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `ReportHandler` class provides methods for generating financial reports,
 * including calculating debt-to-income ratio and identifying top income and expense categories.
 */
public class ReportHandler {

    /**
     * Generates a basic financial report, including total income, total expenses, and debt-to-income ratio.
     * It also provides an option to drill down into more detailed reports.
     *
     * @param incomes  The list of incomes.
     * @param expenses The list of expenses.
     * @param in       The `Scanner` object for user input.
     */
    public static void beginReport(ArrayList<Income> incomes, ArrayList<Expense> expenses, Scanner in) {
        if (incomes.isEmpty() && expenses.isEmpty()) {
            System.out.println("\nThere is nothing to report at this time.");
            return;
        }

        double totalIncome = 0.0;
        double totalExpenses = 0.0;
        double debtRatio;

        for (Income income : incomes) {
            totalIncome += income.getAmountPerMonth();
        }

        for (Expense expense : expenses) {
            totalExpenses += (expense.getAmountPerPayment() * expense.getRecurrence());
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
        System.out.print("\nWould you like to break this down and go deeper? [y/n]\n> ");
        String ans = in.nextLine();

        while (!ans.equals("y") && !ans.equals("n")) {
            System.out.print("\nInvalid input. Enter 'y' or 'n'.\n> ");
            ans = in.nextLine();
        }

        if (ans.equalsIgnoreCase("y")) {
            topThreeCats(incomes, expenses, in);
        }
    }

    /**
     * Identifies and displays the top three income and expense categories based on their total amounts.
     *
     * @param incomes  The list of incomes.
     * @param expenses The list of expenses.
     * @param in       The `Scanner` object for user input.
     */
    public static void topThreeCats(ArrayList<Income> incomes, ArrayList<Expense> expenses, Scanner in) {
        ArrayList<Income> largestThreeIncCats = new ArrayList<>();
        ArrayList<Expense> largestThreeExpCats = new ArrayList<>();
        ArrayList<String> incomeCategories = new ArrayList<>();
        ArrayList<String> expenseCategories = new ArrayList<>();

        String topIncCat1 = "";
        String topIncCat2 = "";
        String topIncCat3 = "";

        String topExpCat1 = "";
        String topExpCat2 = "";
        String topExpCat3 = "";

        double topIncCat1Amount = 0.0;
        double topIncCat2Amount = 0.0;
        double topIncCat3Amount = 0.0;

        double topExpCat1Amount = 0.0;
        double topExpCat2Amount = 0.0;
        double topExpCat3Amount = 0.0;

        for (Income income : incomes) {
            String category = income.getCategory();
            double amount = income.getAmountPerMonth();

            if (amount > topIncCat1Amount) {
                topIncCat3 = topIncCat2;
                topIncCat3Amount = topIncCat2Amount;
                topIncCat2 = topIncCat1;
                topIncCat2Amount = topIncCat1Amount;
                topIncCat1 = category;
                topIncCat1Amount = amount;
            } else if (amount > topIncCat2Amount) {
                topIncCat3 = topIncCat2;
                topIncCat3Amount = topIncCat2Amount;
                topIncCat2 = category;
                topIncCat2Amount = amount;
            } else if (amount > topIncCat3Amount) {
                topIncCat3 = category;
                topIncCat3Amount = amount;
            }
        }

        for (Income income : incomes) {
            String category = income.getCategory();
            if (category.equals(topIncCat1) || category.equals(topIncCat2) || category.equals(topIncCat3)) {
                if (!incomeCategories.contains(category)) {
                    largestThreeIncCats.add(income);
                    incomeCategories.add(category);
                }
            }
        }

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmountPerMonth();

            if (amount > topExpCat1Amount) {
                topExpCat3 = topExpCat2;
                topExpCat3Amount = topExpCat2Amount;
                topExpCat2 = topExpCat1;
                topExpCat2Amount = topExpCat1Amount;
                topExpCat1 = category;
                topExpCat1Amount = amount;
            } else if (amount > topExpCat2Amount) {
                topExpCat3 = topExpCat2;
                topExpCat3Amount = topExpCat2Amount;
                topExpCat2 = category;
                topExpCat2Amount = amount;
            } else if (amount > topExpCat3Amount) {
                topExpCat3 = category;
                topExpCat3Amount = amount;
            }
        }

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            if (category.equals(topExpCat1) || category.equals(topExpCat2) || category.equals(topExpCat3)) {
                if (!expenseCategories.contains(category)) {
                    largestThreeExpCats.add(expense);
                    expenseCategories.add(category);
                }
            }
        }

        largestThreeIncCats.sort((o1, o2) -> Double.compare(o2.getAmountPerMonth(), o1.getAmountPerMonth()));
        largestThreeExpCats.sort((o1, o2) -> Double.compare(o2.getAmountPerMonth(), o1.getAmountPerMonth()));

        if (incomeCategories.isEmpty()) {
            System.out.println("\nNothing to report for income.");
        } else if (incomeCategories.size() == 1) {
            System.out.print("\nYour largest (and only) category of income:");
            System.out.printf("\n%s: $%.2f\n", incomeCategories.getFirst(), topIncCat1Amount);
        } else {
            System.out.printf("\nYour %d largest categories of income:\n\n", incomeCategories.size());
            for (Income income : largestThreeIncCats) {
                System.out.printf("%s: $%.2f\n", income.getCategory(), income.getAmountPerMonth());
            }
        }

        if (expenseCategories.isEmpty()) {
            System.out.println("\nNothing to report for expenses.");
        } else if (expenseCategories.size() == 1) {
            System.out.print("\nYour largest (and only) category of expense:");
            System.out.printf("\n%s: $%.2f\n", expenseCategories.getFirst(), topIncCat1Amount);
        } else {
            System.out.printf("\nYour %d largest categories of expense:\n\n", expenseCategories.size());
            for (Expense expense : largestThreeExpCats) {
                System.out.printf("%s: $%.2f\n", expense.getCategory(), expense.getAmountPerMonth());
            }
        }

        System.out.print("\nWould you like to break this report down further still? [y/n]\n> ");
        String ans = in.nextLine();

        while (!ans.equals("y") && !ans.equals("n")) {
            System.out.print("\nInvalid input. Enter 'y' or 'n'.\n> ");
            ans = in.nextLine();
        }

        if (ans.equals("y")) {
            topThree(largestThreeIncCats, largestThreeExpCats);
        }
    }

    /**
     * Identifies and displays the top three individual incomes and expenses based on their amounts.
     *
     * @param incomes  The list of incomes.
     * @param expenses The list of expenses.
     */
    public static void topThree(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        ArrayList<Income> largestThreeIncomes = new ArrayList<>();
        ArrayList<Expense> largestThreeExpenses = new ArrayList<>();
        double largestAmount1 = 0.0;
        double largestAmount2 = 0.0;
        double largestAmount3 = 0.0;

        for (Income income : incomes) {
            if (income.getAmountPerMonth() > largestAmount1) {
                largestAmount3 = largestAmount2;
                largestAmount2 = largestAmount1;
                largestAmount1 = income.getAmountPerMonth();
            } else if (income.getAmountPerMonth() > largestAmount2) {
                largestAmount3 = largestAmount2;
                largestAmount2 = income.getAmountPerMonth();
            } else if (income.getAmountPerMonth() > largestAmount3) {
                largestAmount3 = income.getAmountPerMonth();
            }
        }

        for (Income income : incomes) {
            if (income.getAmountPerMonth() == largestAmount1 || income.getAmountPerMonth() == largestAmount2 || income.getAmountPerMonth() == largestAmount3) {
                largestThreeIncomes.add(income);
            }
        }

        largestThreeIncomes.sort((o1, o2) -> Double.compare(o2.getAmountPerMonth(), o1.getAmountPerMonth()));

        if (largestThreeIncomes.isEmpty()) {
            System.out.println("\nThere is no income report at this time.");
        } else {
            if (largestThreeIncomes.size() == 1) {
                System.out.print("\nYour largest (and only) income:\n");
                System.out.printf("%s: %.2f", largestThreeIncomes.getFirst().getName(), largestThreeIncomes.getFirst().getAmountPerMonth());
            } else {
                System.out.printf("\nYour %d largest incomes in the %s category:\n", largestThreeIncomes.size(), largestThreeIncomes.getFirst().getCategory());
                for (Income income : largestThreeIncomes) {
                    System.out.printf("%s: %.2f\n", income.getName(), income.getAmountPerMonth());
                }
            }
        }

        largestAmount1 = 0.0;
        largestAmount2 = 0.0;
        largestAmount3 = 0.0;

        for (Expense expense : expenses) {
            if (expense.getAmountPerMonth() > largestAmount1) {
                largestAmount3 = largestAmount2;
                largestAmount2 = largestAmount1;
                largestAmount1 = expense.getAmountPerMonth();
            } else if (expense.getAmountPerMonth() > largestAmount2) {
                largestAmount3 = largestAmount2;
                largestAmount2 = expense.getAmountPerMonth();
            } else if (expense.getAmountPerMonth() > largestAmount3) {
                largestAmount3 = expense.getAmountPerMonth();
            }
        }

        for (Expense expense : expenses) {
            if (expense.getAmountPerMonth() == largestAmount1 || expense.getAmountPerMonth() == largestAmount2 || expense.getAmountPerMonth() == largestAmount3) {
                largestThreeExpenses.add(expense);
            }
        }

        largestThreeExpenses.sort((o1, o2) -> Double.compare(o2.getAmountPerMonth(), o1.getAmountPerMonth()));

        if (largestThreeExpenses.isEmpty()) {
            System.out.println("\nThere is no expense report at this time.");
        } else {
            if (largestThreeExpenses.size() == 1) {
                System.out.print("\nYour largest (and only) expense:\n");
                System.out.printf("%s: %.2f", largestThreeExpenses.getFirst().getName(), largestThreeExpenses.getFirst().getAmountPerMonth());
            } else {
                System.out.printf("\nYour %d largest expenses in the %s category:\n", largestThreeExpenses.size(), largestThreeExpenses.getFirst().getCategory());
                for (Expense expense : largestThreeExpenses) {
                    System.out.printf("%s: %.2f\n", expense.getName(), expense.getAmountPerMonth());
                }
            }
        }
    }
}