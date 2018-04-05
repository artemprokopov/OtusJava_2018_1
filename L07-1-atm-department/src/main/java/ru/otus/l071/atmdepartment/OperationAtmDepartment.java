package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;

/**
 * Интерфейс определяющий методы операций доступных департаменту АТМ.
 * @author Artem Prokopov
 * @since 04/04/2018
 * @param <T> тип оперируемых денежных единиц.
 * @version 1.0
 */
public interface OperationAtmDepartment<T extends Enum<T> & Money> {
    /**
     * Иетод возвращает остаток денежных средств в АТМ.
     * @return сумма сотатка денежных средств в АТМ.
     */
    Integer restMoneyInAtm();

    /**
     * Инициализирует банкомат по умолчанию.
     * @return true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException выбрасывается в случае невозможности завершить операцию АТМ.
     */
    boolean initDefault() throws OperationAtmCanNotCompleteException;

    /**
     * Инициализирует банкомат значениями переданными в параметре.
     * @param initAtm EnumMap<T, Integer> с инициализирующими значениями для АТМ.
     * @return  true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException .
     */
    boolean initDefault(EnumMap<T, Integer> initAtm) throws OperationAtmCanNotCompleteException;
}
