import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    //Kiara Matos-Luna
    private int curAccountNumber;
    private ArrayList<BankAccount> accounts;
    private final double BALANCE_THRESHOLD = 500;

    public Bank(){
        accounts = new ArrayList<BankAccount>();
        curAccountNumber = 0;
    }

    public void setCurAccountNumber(int accNumber){
        if(accNumber > 0)
            curAccountNumber = accNumber;
    }
    public int getCurAccountNumber(){return curAccountNumber;}

    public void addAccount(BankAccount account){
        accounts.add(account);
    }

    public BankAccount findAccount(int accountNumber){
        BankAccount foundAccount = null;

        for(int accNum = 0; accNum < accounts.size(); accNum++){
            BankAccount acc = accounts.get(accNum);
            if(acc.getNumber() == accountNumber){
                foundAccount = acc;
                break;
            }
        }
        return foundAccount;
    }
   public boolean deleteAccount(int accountNumber){
        boolean success = false;
        BankAccount foundAccount = null;

        foundAccount = findAccount(accountNumber);
        if(foundAccount != null){
            accounts.remove(foundAccount);
            success = true;
        }

        return success;
   }

   public double getAverageBalance(){
        double averageBalances = 0.0;
        double total = 0.0;

        for(int i = 0; i < accounts.size(); i++){
            BankAccount balances = accounts.get(i);
            total+= balances.getCurrentBalance();
        }
        averageBalances = total / accounts.size();

        return averageBalances;
   }

   public double getMaximumBalance(){
        double maxBalance = 0.0;

        for(int i = 0; i < accounts.size(); i++){
            BankAccount balances = accounts.get(i);
            if(balances.getCurrentBalance() > maxBalance)
                maxBalance = balances.getCurrentBalance();
        }

        return maxBalance;
   }

   public double getMinimumBalance(){
        double minBalance = getMaximumBalance();

        for(int i = 0; i < accounts.size(); i++){
            BankAccount balances = accounts.get(i);
            double curBalance = balances.getCurrentBalance();
            if(curBalance < minBalance){
                minBalance = curBalance;
                if(minBalance == 0.0)
                    break;
            }
        }

        return minBalance;
   }

   public ArrayList<BankAccount> getLowBalanceAccounts(){
        ArrayList<BankAccount> lowAccounts = new ArrayList<BankAccount>();

        for(int i = 0; i < accounts.size(); i++){
            BankAccount tmpAcc = accounts.get(i);
            if(tmpAcc.getCurrentBalance() <= BALANCE_THRESHOLD)
                lowAccounts.add(tmpAcc);
        }

        return lowAccounts;
   }
   public void save(String filePath) throws Exception{
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        for (int i = 0; i < accounts.size(); i++){
            BankAccount msg = accounts.get(i);
            osw.write(msg.convertFromText());
        }

        osw.close();
        fos.close();
   }

   public void load(String filePath) throws Exception {
       FileInputStream fis = new FileInputStream(filePath);
       Scanner fileScanner = new Scanner(fis);

       String accText = "";
       String textLine = "";


       while (fileScanner.hasNextLine()) {
            textLine = fileScanner.nextLine();

            if(textLine.contains("End")) {
                accText = textLine + " ";
                BankAccount account = new BankAccount(0, "",
                        0.0, 0.0, "");
                account.loadFromText(accText);
                accounts.add(account);
            }
       }
       fis.close();
   }

}
