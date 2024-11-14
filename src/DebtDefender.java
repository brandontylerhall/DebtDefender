import java.util.Scanner;

public class DebtDefender {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Debt Defender");

        boolean exit = false;
        while (!exit) {
            System.out.println("Main menu:");
            System.out.println(
                    """
                        1. Income
                        2. Expense
                        3. Help
                        4. Exit
                    """
            );

            System.out.print("> ");
            switch (in.nextInt()) {
                case 1:
                    System.out.println("income");
                    break;
                case 2:
                    System.out.println("expense");
                    break;
                case 3:
                    System.out.println("help");
                    break;
                case 4:
                    System.out.println("Til next time!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option\n");
            }
        }
    }
}
