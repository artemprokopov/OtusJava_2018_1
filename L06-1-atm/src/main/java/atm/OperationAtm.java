package atm;

import java.util.EnumMap;

public interface OperationAtm {
    <T extends StorageAtm<R>, R extends  Enum<R> & Money> boolean toDepositMoneyAtm(T atm, R nominal,
                                                                                    EnumMap<R, Integer> numberOfBanknotesToDeposit);
    <T extends StorageAtm<R>, R extends  Enum<R> & Money,
            A extends AlgorithmOfWithdrawal> EnumMap<R, Integer> toWithdrawMoneyAtm(T atm, A aow, R nominal,
                                                         Integer  amountOfWithdrawals);
    <T extends StorageAtm<R>, R extends  Enum<R> & Money> Integer balanceATM(T atm);
}
