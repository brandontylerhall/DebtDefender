import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * The CSV class provides methods for reading and writing financial data
 * (incomes and expenses) to a CSV file named "data.csv".
 */
public class CSV {
    private static final String CSV_FILE = "data.csv";

    /**
     * Constructs a CSV object.
     */
    public CSV() {
    }

    /**
     * Creates the "data.csv" file if it does not exist.
     * The file is initialized with a header row containing column names.
     */
    public void createFile() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("Name,Description,Category,Recurrence,Amount,Begin Date,Type\n");
            } catch (IOException e) {
                System.out.println("Error making csv file" + e.getMessage());
            }
        }
    }

    /**
     * Writes the provided lists of incomes and expenses to the "data.csv" file.
     * The data is written in CSV format with each line representing an income or expense.
     *
     * @param incomes  The list of Income objects to write to the file.
     * @param expenses The list of Expense objects to write to the file.
     */
    public void writeToCSV(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            writer.write("Name,Description,Category,Recurrence,Amount,Begin Date,Type\n");

            for (Income income : incomes) {
                writer.append(income.toCSVString()).append(",Income\n");
            }

            for (Expense expense : expenses) {
                writer.append(expense.toCSVString()).append(",Expense\n");
            }

        } catch (IOException e) {
            System.out.println("Error writing to CSV." + e.getMessage());
        }
    }

    /**
     * Reads income and expense data from the "data.csv" file and populates the provided lists.
     * Each line in the file is parsed and converted into an Income or Expense object.
     *
     * @param incomes  The list of Income objects to be populated with data from the file.
     * @param expenses The list of Expense objects to be populated with data from the file.
     */
    public void readFromCSV(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        try (Scanner scanner = new Scanner(new File(CSV_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values[6].equals("Income")) {
                    Income income = new Income();
                    income.setName(values[0]);
                    income.setDescription(values[1]);
                    income.setCategory(values[2]);
                    income.setRecurrence(parseInt(values[3]));
                    income.setAmountPerPayment(parseDouble(values[4]));
                    income.setBeginDate(LocalDate.parse(values[5]));
                    incomes.add(income);
                } else if (values[6].equals("Expense")) {
                    Expense expense = new Expense();
                    expense.setName(values[0]);
                    expense.setDescription(values[1]);
                    expense.setCategory(values[2]);
                    expense.setRecurrence(parseInt(values[3]));
                    expense.setAmountPerPayment(parseDouble(values[4]));
                    expense.setBeginDate(LocalDate.parse(values[5]));
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from CSV." + e.getMessage());
        }
    }
}