package atm;

import atm.exception.NotEnoughMoney;

import java.util.EnumMap;

public interface AlgorithmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money> {
    EnumMap<R, Integer> toExecuteAlgorithm(T atm, Integer amountOfWithdrawals) throws NotEnoughMoney;
}
