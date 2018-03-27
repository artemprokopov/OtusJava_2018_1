package atm;

import atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;

public interface OperationAtm <R extends  Enum<R> & Money> {
    boolean toDepositMoneyAtm( EnumMap<R, Integer> numberOfBanknotesToDeposit);
    void  toWithdrawMoneyAtm(Integer  amountOfWithdrawals) throws OperationAtmCanNotCompleteException;
    Integer balanceATM();
}
