package atm;

import java.util.EnumMap;

public interface AlgorithmOfWithdrawal<T extends Atm<R>, R extends  Enum<R> & Money> {
    EnumMap<R, Integer> toExecuteAlgorithm(T atm, R nominal,
                                                         Integer amountOfWithdrawals);
}
