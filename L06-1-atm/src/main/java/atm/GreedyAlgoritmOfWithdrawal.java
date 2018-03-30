package atm;

import atm.exception.NotEnoughMoneyException;

import java.util.EnumMap;
import java.util.Objects;
/**
 * Класс реализующий жадный адлгоритм выдачи банкнот из хранилища банкомата..
 * Реализует интерфейс {@link AlgorithmOfWithdrawal}
 * @author Artem Prokopov
 * @since 30/03/2018
 * @param <T> тип хранилища.
 * @param <R> тип перечесление оперируемых банкнот.
 * @version 1.0
 */
public class GreedyAlgoritmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money>
        implements AlgorithmOfWithdrawal<T, R> {
    /**
     * Метод запуска алгоритма расчета количесва банкнот которые должны быть выданы из хранилища банкомата.
     * @param atm хранилище банкомата.
     * @param amountOfWithdrawals количество денежных средств требуемые к выдачи.
     * @return результат работы алгоритма в виде коллекции EnumMap<R, Integer>.
     * @throws NotEnoughMoneyException исключение выбрасывается если алгоритм не может завершить свою работу
     *                                  в результате не хватки банкнот в слотах хранилища.
     */
    @Override
    public EnumMap<R, Integer> toExecuteAlgorithm(T atm, Integer amountOfWithdrawals)
            throws NotEnoughMoneyException {
        Objects.requireNonNull(atm);
        Objects.requireNonNull(amountOfWithdrawals);

        EnumMap<R, Integer> result = atm.getContainerResult();
        int temp = amountOfWithdrawals;
        result.clear();
        R[] notEmptySlot = atm.getNotEmptySlot();
        if (notEmptySlot.length == 0) {
            throw new NotEnoughMoneyException("Not enough money!");
        }
        for (R r : notEmptySlot) {
            Integer numberBanknotesSlotAtmStorage = atm.getNumberBanknotesSlotAtmStorage(r);
            int numberBanknotes = temp / r.getValue();
            if (numberBanknotesSlotAtmStorage < numberBanknotes) {
                numberBanknotes = numberBanknotesSlotAtmStorage;
            }
            result.put(r, numberBanknotes);
            temp = temp - numberBanknotes * r.getValue();
            if (temp == 0) {
                break;
            }
        }
        if (temp != 0) {
            throw new NotEnoughMoneyException("Can not issue this amount!");
        }
        return result;
    }
}
