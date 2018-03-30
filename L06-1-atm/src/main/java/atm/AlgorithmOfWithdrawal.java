package atm;

import atm.exception.NotEnoughMoneyException;

import java.util.EnumMap;
/**
 * Интерфейс определяющий методы реализации алгоритмов выдачи денежных средств банкоматом.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @param <R> тип перечесление оперируемых банкнот.
 * @param <T> тип хранилища.
 * @version 1.0
 */
public interface AlgorithmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money> {
    /**
     * Метод запуска алгоритма расчета количесва банкнот которые должны быть выданы из хранилища банкомата.
     * @param atm хранилище банкомата.
     * @param amountOfWithdrawals количество денежных средств требуемые к выдачи.
     * @return результат работы алгоритма в виде коллекции EnumMap<R, Integer>.
     * @throws NotEnoughMoneyException исключение выбрасывается если алгоритм не может завершить свою работу
     *                                  в результате не хватки банкнот в слотах хранилища.
     */
    EnumMap<R, Integer> toExecuteAlgorithm(T atm, Integer amountOfWithdrawals) throws NotEnoughMoneyException;
}
