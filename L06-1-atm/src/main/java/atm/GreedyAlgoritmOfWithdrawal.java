package atm;

import java.util.EnumMap;
import java.util.Objects;

public class GreedyAlgoritmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money>
        implements AlgorithmOfWithdrawal<T, R> {
    @Override
    public void toExecuteAlgorithm(T atm, Integer amountOfWithdrawals,  EnumMap<R, Integer> result) {
        Objects.requireNonNull(atm);
        Objects.requireNonNull(amountOfWithdrawals);
        Objects.requireNonNull(result);
        for (R rs: atm.getNominalBanknotes()) {

        }
    }
}
