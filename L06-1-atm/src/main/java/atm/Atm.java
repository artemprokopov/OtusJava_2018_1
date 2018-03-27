package atm;

import atm.exception.NotEnoughMoney;
import atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.Objects;

public class Atm<T extends StorageAtm<R>, R extends  Enum<R> & Money,
        A extends AlgorithmOfWithdrawal<T, R>> implements OperationAtm<R> {

    private final T storageAtm;
    private final A algorithm;

    private Atm(T initStorage, A algorithmWithdrawalOperation) {
        Objects.requireNonNull(initStorage);
        Objects.requireNonNull(algorithmWithdrawalOperation);
        this.storageAtm = initStorage;
        this.algorithm = algorithmWithdrawalOperation;
    }

    @Override
    public boolean toDepositMoneyAtm(EnumMap<R, Integer> numberOfBanknotesToDeposit) {
        try {
            for (R r : storageAtm.getNominalBanknotes()) {
                storageAtm.increaseNumberBanknotesSlotAtmStorage(r, numberOfBanknotesToDeposit.get(r));
            }
        } catch (OperationAtmCanNotCompleteException oacnc) {
            System.out.println(oacnc.getMessage());
        }
        return false;
    }

    @Override
    public void toWithdrawMoneyAtm(Integer amountOfWithdrawals) throws OperationAtmCanNotCompleteException {
        EnumMap<R, Integer> result = null;
        try {
            result = algorithm.toExecuteAlgorithm(this.storageAtm, amountOfWithdrawals);
        } catch (NotEnoughMoney nem) {
            System.out.println(nem.getMessage());
        }
        if (Objects.isNull(result)) {
            throw new OperationAtmCanNotCompleteException("Operation can not complete!!!");
        }
        for (R r : result.keySet()) {
            storageAtm.decreaseNumberBanknotesSlotAtmStorage(r, result.get(r));
        }
    }

    @Override
    public Integer balanceATM() {
        return storageAtm.remainMoneyInStorage();
    }
}
