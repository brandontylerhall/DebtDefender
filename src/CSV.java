import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CSV {
    private static final String CSV_FILE = "data.csv";

    public CSV() {
    }

    public void createFile() throws IOException {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("Name,Description,Category,Recurrence,Amount,Begin Date,Type\n");
            } catch (IOException e) {
                System.out.println("Error making csv file" + e.getMessage());
            }
        }
    }

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

    public void readFromCSV(ArrayList<Income> incomes, ArrayList<Expense> expenses) throws FileNotFoundException {
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
                    income.setAmount(parseDouble(values[4]));
                    income.setBeginDate(LocalDate.parse(values[5]));
                    incomes.add(income);
                } else if (values[6].equals("Expense")) {
                    Expense expense = new Expense();
                    expense.setName(values[0]);
                    expense.setDescription(values[1]);
                    expense.setCategory(values[2]);
                    expense.setRecurrence(parseInt(values[3]));
                    expense.setAmount(parseDouble(values[4]));
                    expense.setBeginDate(LocalDate.parse(values[5]));
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from CSV." + e.getMessage());
        }
    }
}
