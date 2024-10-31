public class SavingsAccount extends BankAccount{
    //Kiara Matos-Luna
    private int depositCount;
    private int withdrawCount;
    private final double INTEREST_ADD = 0.20;
    private final double WITHDRAW_FEE = 0.05;

    public SavingsAccount(int newNumber, String initDate, double initCurrentBalance,
                          double initWithdrawLimit, String initAccountHolder){
        super(newNumber, initDate, initCurrentBalance, initWithdrawLimit, initAccountHolder);
        depositCount = 0;
        withdrawCount = 0;
    }

    @Override
    public boolean withdrawMoney(double drawMoney) {
        boolean success = super.withdrawMoney(drawMoney);
        if(success){
            depositCount = 0;
            withdrawCount++;
        }
        if(withdrawCount == 3){
            super.withdrawMoney(getCurrentBalance() * WITHDRAW_FEE);
            withdrawCount = 0;
        }
        return success;
    }
    @Override
    public boolean depositMoney(double depositMoney){
        boolean success = super.depositMoney(depositMoney);
        if(success){
            withdrawCount = 0;
            depositCount++;
        }
        if(depositCount == 10){
            super.depositMoney(getCurrentBalance() * INTEREST_ADD);
            depositCount = 0;
        }
        return success;
    }
}
