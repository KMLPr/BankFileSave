import java.util.Scanner;

public class BankAccount {
    //Kiara Matos-Luna
    private String accountHolder;
    private String dateCreated;
    private double currentBalance;
    private double withdrawLimit;
    private double curAmountWithdrawn;
    private int number;


    public BankAccount(){
        accountHolder = "";
        dateCreated = " ";
        currentBalance = 0.0;
        withdrawLimit = 0.0;
        curAmountWithdrawn = withdrawLimit;
        number = 0;

    }
    public BankAccount(int newNumber,
                String initDate, double initCurrentBalance, double initWithdrawLimit, String initAccountHolder){
        accountHolder = initAccountHolder;
        dateCreated = initDate;
        currentBalance = initCurrentBalance;
        withdrawLimit = initWithdrawLimit;
        curAmountWithdrawn = withdrawLimit;
        number = newNumber;

    }
    public void setNumber(int newNumber){
        if(newNumber > 0)
            number = newNumber;
    }
    public int getNumber(){return number;}
    public void setCurrentBalance(double newBalance){
        if(newBalance >= 0)
            currentBalance = newBalance;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }
    public void setWithdrawLimit(double newLimit){
        if(newLimit > 0)
            withdrawLimit = newLimit;
    }
    public double getWithdrawLimit(){return withdrawLimit;}
    public void setDateCreated(String newDate){
        if(!newDate.isEmpty())
            dateCreated = newDate;
    }
    public String getDateCreated(){return dateCreated;}


    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        if(!accountHolder.isEmpty())
            this.accountHolder = accountHolder;
    }
    public boolean withdrawMoney(double drawMoney){
        boolean withdrawSuccess = false;

        if(withdrawLimit >= drawMoney && drawMoney <= currentBalance) {
            withdrawSuccess = true;
            currentBalance -= drawMoney;
            curAmountWithdrawn -= drawMoney;
        }

        return withdrawSuccess;
    }

    public boolean depositMoney(double depositMoney){
        boolean depositSuccess = false;

        if(depositMoney > 0){
            currentBalance+= depositMoney;
            depositSuccess = true;
        }

        return depositSuccess;
    }

    public String toString(){
        String info = "";

        info+= "Account Number: " + number + "\n";
        info+=  "Created on: " + dateCreated + "\n";
        info += "The current balance: " + currentBalance +
                ", with the withdrawal limit: " + withdrawLimit + "\n";
        info+= "For Account Holder: " + accountHolder + "\n";

        return info;
    }
    public String convertFromText(){
        String text = "";
        text +=  number + " " + dateCreated;
        text += " " + currentBalance + " " + withdrawLimit + " " + accountHolder + " End\n";

        return text;
    }
    public void loadFromText(String text){
        Scanner strScanner = new Scanner(text);

        number = strScanner.nextInt();
        dateCreated = strScanner.next();
        currentBalance = strScanner.nextDouble();
        withdrawLimit = strScanner.nextDouble();
        accountHolder = strScanner.next();
    }

}
