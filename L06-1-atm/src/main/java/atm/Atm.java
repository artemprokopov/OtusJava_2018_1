package atm;

import java.util.EnumMap;
import java.util.Objects;

public class Atm<T extends StorageAtm<R>, R extends  Enum<R> & Money,
        A extends AlgorithmOfWithdrawal<T, R>> implements OperationAtm<R> {

    private final StorageAtm<R> storageAtm;
    private final A algorithm;

    private Atm(T initStorage, A algorithmWithdrawalOperation) {
        Objects.requireNonNull(initStorage);
        Objects.requireNonNull(algorithmWithdrawalOperation);
        this.storageAtm = initStorage;
        this.algorithm = algorithmWithdrawalOperation;
    }

    @Override
    public boolean toDepositMoneyAtm(EnumMap<R, Integer> numberOfBanknotesToDeposit) {
        return false;
    }

    @Override
    public EnumMap<R, Integer> toWithdrawMoneyAtm(Integer amountOfWithdrawals) {
        return null;
    }

    @Override
    public Integer balanceATM() {
        return null;
    }
}
