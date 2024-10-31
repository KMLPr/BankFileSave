import java.util.ArrayList;
import java.util.Scanner;

public class BanksAPP {
    //Kiara Matos-Luna

    public static void displayFilteredMessagesMenu(ArrayList<BankAccount> messagesToDisplay)
    {
        for (int i = 1; i <= messagesToDisplay.size(); i++) {
            if(messagesToDisplay.get(i - 1) != null)
                System.out.println( messagesToDisplay.get(i - 1));
        }
    }

    public static void main(String[] args) {
        final String pathToSave = "bankAccounts.txt";
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        BankAccount curAccount = null;
        int option = 0;

        System.out.println("Greetings from the Bank! Thank you for choosing our services today." + "\n");
        try {
            bank.load(pathToSave);
        } catch(Exception e){
            System.out.println("No previous data was found. There are no created accounts");
        }
        
        while(option != 7){
            ArrayList<BankAccount> lowAccounts = new ArrayList<BankAccount>();

            System.out.println("1. Create a new account");
            System.out.println("2. Perform actions on a created account");
            System.out.println("3. Delete an existing account");
            System.out.println("4. Display the average of ALL account balances");
            System.out.println("5. Display the maximum and minimum account balances");
            System.out.println("6. Display all accounts that have a low balance");
            System.out.println("7. Quit");

            option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("Please enter your name, the date, and your withdraw limit" +
                            " (separated by spaces):");
                    String name = scanner.next();
                    String date = scanner.next();
                    double limit = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Now please input a current balance for your account:");
                    double balance = scanner.nextDouble();
                    System.out.println("Please enter your account number: ");
                    bank.setCurAccountNumber(scanner.nextInt());
                    System.out.println("What kind of account would you like? ( 'C' for Checking or" +
                            " 'S' for Savings Account):");
                    char accountType = scanner.next().charAt(0);
                    if(accountType == 'C' || accountType == 'c'){
                        curAccount = new CheckingAccount(bank.getCurAccountNumber(), date, balance, limit, name);
                        System.out.println("For every 10 deposits in a row with no withdrawal, you will earn " +
                                " 5% interest to your current balance!");
                    }
                    else if(accountType == 'S' || accountType == 's'){
                        curAccount = new SavingsAccount(bank.getCurAccountNumber(), date, balance, limit, name);
                        System.out.println("For every 10 deposits into your account, your current balance" +
                                " will gain 20% interest.");
                        System.out.println("However, for every 3 withdrawals in a row with no deposits" +
                                ", we will take a 5% fee from your current balance!!");
                    }

                    int choice = 0;
                    while(choice != 4){
                        System.out.println("1. Check current balance");
                        System.out.println("2. Deposit Money");
                        System.out.println("3. Withdraw Money");
                        System.out.println("4. Quit");
                        choice = scanner.nextInt();
                        if(choice == 1)
                            System.out.println("Your current balance is: " + curAccount.getCurrentBalance());
                        else if(choice == 2){
                            System.out.println("How much would you like to deposit?");
                            double deposit = scanner.nextDouble();
                            boolean success = curAccount.depositMoney(deposit);
                            if(success)
                                System.out.println("Money was deposited!");
                            else
                                System.out.println("Could not deposit money");
                        }
                        else if(choice == 3){
                            System.out.println("How much would you like to withdraw?");
                            double withdraw = scanner.nextDouble();
                            boolean success = curAccount.withdrawMoney(withdraw);
                            if(success)
                                System.out.println("You successfully withdrew the money!");
                            else
                                System.out.println("Could not withdraw money");
                        }
                    }
                    bank.addAccount(curAccount);
                    System.out.println(curAccount);
                    break;
                case 2:
                    System.out.println("Which account would you like to use? (Please enter an account number):");
                    int number = scanner.nextInt();
                    curAccount = bank.findAccount(number);
                    if(curAccount == null)
                        System.out.println("Account could not be found");
                    else{
                        choice = 0;
                        while(choice != 4){
                            System.out.println("1. Check current balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Quit");
                            choice = scanner.nextInt();

                            if(choice == 1)
                                System.out.println("Your current balance is: " + curAccount.getCurrentBalance());
                            else if(choice == 2){
                                System.out.println("How much would you like to deposit?");
                                double deposit = scanner.nextDouble();
                                boolean success = curAccount.depositMoney(deposit);
                                if(success)
                                    System.out.println("Money was deposited!");
                                else
                                    System.out.println("Could not deposit money");
                            }
                            else if(choice == 3){
                                System.out.println("How much would you like to withdraw?");
                                double withdraw = scanner.nextDouble();
                                boolean success = curAccount.withdrawMoney(withdraw);
                                if(success)
                                    System.out.println("You successfully withdrew the money!");
                                else
                                    System.out.println("Could not withdraw money");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Please enter an existing account you would like to delete:");
                    number = scanner.nextInt();
                    boolean success = bank.deleteAccount(number);
                    if(success)
                        System.out.println("The account was successfully deleted!");
                    else
                        System.out.println("Could not find account to delete");
                    break;
                case 4:
                    System.out.println("The average balance of all the accounts is: " +
                            bank.getAverageBalance());
                    break;
                case 5:
                    System.out.println("The maximum account balance is: " + bank.getMaximumBalance() +
                            " and the minimum balance is: " + bank.getMinimumBalance() + "\n");
                    break;
                case 6:
                    lowAccounts = bank.getLowBalanceAccounts();
                    if(lowAccounts.isEmpty())
                        System.out.println("There are no accounts below the balance threshold");
                    else
                        displayFilteredMessagesMenu(lowAccounts);
                    break;
                case 7:
                    bank.setCurAccountNumber(0);
                    try {
                        bank.save(pathToSave);
                    } catch (Exception e) {
                        System.out.println("Unable to save the bank accounts to file!!");
                    }
                    System.out.println("Have a nice day!");
                    break;
            }
        }
    }


}
