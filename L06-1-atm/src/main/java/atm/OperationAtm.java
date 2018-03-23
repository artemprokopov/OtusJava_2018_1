package atm;

import java.util.EnumMap;

public interface OperationAtm <R extends  Enum<R> & Money> {
    boolean toDepositMoneyAtm( EnumMap<R, Integer> numberOfBanknotesToDeposit);
    EnumMap<R, Integer> toWithdrawMoneyAtm(Integer  amountOfWithdrawals);
    Integer balanceATM();
}
