import java.io.*;
import java.util.ArrayList;

public class CSV {
    private static final String CSV_FILE = "data.csv";

    public void makeCSV() throws IOException {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                writer.write("Type, Name,Amount\n");

            } catch (IOException e) {
                System.out.println("Error making csv file" + e.getMessage());
            }
        }
    }


    public void writeToCSV(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE));
            writer.write("Type,Name,Amount\n");
            for (Income income : incomes) {
                writer.write("Income," + income.getName() + " " + income.getAmount() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error making CSV File." + e.getMessage());
        }
    }

    public void readToCSV(ArrayList<Income> incomes, ArrayList<Expense> expenses) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    String name = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    if ("Income".equalsIgnoreCase(type)) {
                        incomes.add(new Income(name, amount));
                    } else if ("Expense".equalsIgnoreCase(type)) {
                        expenses.add(new Expense(name, amount));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV File." + e.getMessage());
        }
    }
}
