package atm;

import atm.exception.NotEnoughMoneyException;
import atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.Objects;
/**
 * Класс реализующий функционал , банкомата.
 * Реализует интерфейс {@link OperationAtm}
 * @author Artem Prokopov
 * @since 30/03/2018
 * @param <T> тип хранилища.
 * @param <R> тип перечесление оперируемых банкнот.
 * @param <A> тип алгоритма работы банкомата.
 * @version 1.0
 */
public class Atm<T extends StorageAtm<R>, R extends  Enum<R> & Money,
        A extends AlgorithmOfWithdrawal<T, R>> implements OperationAtm<R> {
    /**
     * Поле класса хранилища банкомата.
     */
    private final T storageAtm;
    /**
     * Поле хранения адгоритма работы банкомата.
     */
    private final A algorithm;

    /**
     * Конструктор.
     * @param initStorage передаем хранилище с которым работает банкомат.
     * @param algorithmWithdrawalOperation передаетм алгоритм по которому работает выдача банктот банкоматом.
     */
    public Atm(T initStorage, A algorithmWithdrawalOperation) {
        Objects.requireNonNull(initStorage);
        Objects.requireNonNull(algorithmWithdrawalOperation);
        this.storageAtm = initStorage;
        this.algorithm = algorithmWithdrawalOperation;
    }
    /**
     * Метод, реализующий внесение банкнот в хранилище банкомата.
     * @param numberOfBanknotesToDeposit Map содержащая количество банкнот распределенных по номиналам купюр,
     *                          заданных перечислением реализующего интерфейс {@link Money}.
     * @return
     */
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
    /**
     * Метод, реализующий получение банкнот из хранилища.
     * @param amountOfWithdrawals запрашиваемая сумма денег.
     * @throws OperationAtmCanNotCompleteException исключени выбрасываемое если операция не может быть выполенна.
     */
    @Override
    public void toWithdrawMoneyAtm(Integer amountOfWithdrawals) throws OperationAtmCanNotCompleteException {
        EnumMap<R, Integer> result = null;
        try {
            result = algorithm.toExecuteAlgorithm(this.storageAtm, amountOfWithdrawals);
        } catch (NotEnoughMoneyException nem) {
            System.out.println(nem.getMessage());
        }
        if (Objects.isNull(result)) {
            throw new OperationAtmCanNotCompleteException("Operation can not complete!!!");
        }
        for (R r : result.keySet()) {
            storageAtm.decreaseNumberBanknotesSlotAtmStorage(r, result.get(r));
        }
    }
    /**
     * Метод возвращающий сумму денежных средств на балансе банкомата.
     * @return сумма денежных средст находящихся в хранилище банкомата.
     */
    @Override
    public Integer balanceATM() {
        return storageAtm.restMoneyInStorage();
    }
}
