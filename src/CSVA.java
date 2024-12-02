import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
public class CSVA {
    private static final String
    CSVA_FILE = "data.csva";
    public void makeCSVA() throws IOException {
        File  flie = new File(CSVA_FILE);
        if (!file.exsists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSVA_FILE))){
                writer.writer("Type, Name,Amount\n");

            }catch (IOException e)
            {
                System.out.println("Error making CSVA file"+ e.getMessage());
            }
        }
    }
}public void writeToCSVA(ArrayList<Income> incomes, ArrayList<Expense> expenses){
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(CSVA_FILE))){
        writer.writer("Type,Name,Amount\n");
        for (Income income : incomes){
            writer.writer("Income," + income.getName() + "" + income.getAmount()+ "\n");
        }
    }catch (IOException e){
        System.out.println("Error making CSV File." +e.getMessage());
    }
} public void readToCSVA(ArrayList<Income> incomes, ArrayList<Expense> expenses){
    try (BufferedReader reader = new BufferedReader(new FileReader(CSVA_File))){
        string line = reader.readLine();
        while ((line = reader.readLine()) != null){
            String [] parts = line.split(",");
            if (parts.length == 3){
                String type = parts [ 0];
                String name = parts[1];
                double amount = Double.parseDouble(parts[2]);
                if ("Income".equalsIgnoreCase(type)){
                    incomes.add(new Income(name , amount));
                } else if ("Expense".equalsIgnoreCase(type)) {
                    expenses.add(new Expense(name , amount));
                }
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
