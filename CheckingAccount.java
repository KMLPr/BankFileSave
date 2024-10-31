public class CheckingAccount extends BankAccount{
    //Kiara Matos-Luna
    private int depositCount;
    private final double INTEREST_ADD = 0.05;
    public CheckingAccount(int newNumber, String initDate, double initCurrentBalance,
                           double initWithdrawLimit, String initAccountHolder){
        super(newNumber, initDate, initCurrentBalance, initWithdrawLimit, initAccountHolder);
        depositCount = 0;
    }

    @Override
    public boolean withdrawMoney(double drawMoney) {
        boolean success = super.withdrawMoney(drawMoney);
        if(success)
            depositCount = 0;
        return success;
    }

    @Override
    public boolean depositMoney(double depositMoney) {
        boolean success = super.depositMoney(depositMoney);
        if(success)
            depositCount++;
        if(depositCount == 10) {
            super.depositMoney(getCurrentBalance() * INTEREST_ADD);
            depositCount = 0;
        }
        return success;
    }
}
