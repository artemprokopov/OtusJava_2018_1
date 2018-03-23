package atm;

import java.util.EnumMap;

public interface AlgorithmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money> {
    void toExecuteAlgorithm(T atm, Integer amountOfWithdrawals, EnumMap<R, Integer> result);
}
