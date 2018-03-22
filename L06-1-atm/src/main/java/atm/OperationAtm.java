package atm;

import java.util.EnumMap;

public interface OperationAtm<T extends Atm<R>, R extends  Enum<R> & Money, A extends AlgorithmOfWithdrawal> {
    boolean toDepositMoneyAtm(T atm, R nominal,
                              EnumMap<R, Integer> numberOfBanknotesToDeposit);
    EnumMap<R, Integer> toWithdrawMoneyAtm(T atm, A aow, R nominal,
                                                         Integer  amountOfWithdrawals);
    Integer balanceATM(T atm);
}
