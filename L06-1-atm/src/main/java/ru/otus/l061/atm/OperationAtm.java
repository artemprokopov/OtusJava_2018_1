package ru.otus.l061.atm;

import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
/**
 * Интерфейс операций банкомата, предоставляет необходимые методы, которые  выполняет банкомат.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @param <R> тип перечесление оперируемых банкнот.
 * @version 1.0
 */
public interface OperationAtm<R extends  Enum<R> & Money> {
    /**
     * Метод, реализующий внесение банкнот в хранилище банкомата.
     * @param numberOfBanknotesToDeposit Map содержащая количество банкнот распределенных по номиналам купюр,
     *                          заданных перечислением реализующего интерфейс {@link Money}.
     * @return true если операция завершилась успехом, в противном случае false.
     */
    boolean toDepositMoneyAtm(EnumMap<R, Integer> numberOfBanknotesToDeposit);

    /**
     * Метод, реализующий получение банкнот из хранилища.
     * @param amountOfWithdrawals запрашиваемая сумма денег.
     * @throws OperationAtmCanNotCompleteException исключени выбрасываемое если операция не может быть выполенна.
     */
    void  toWithdrawMoneyAtm(Integer  amountOfWithdrawals) throws OperationAtmCanNotCompleteException;

    /**
     * Метод возвращающий сумму денежных средств на балансе банкомата.
     * @return сумма денежных средст находящихся в хранилище банкомата.
     */
    Integer balanceATM();
}
