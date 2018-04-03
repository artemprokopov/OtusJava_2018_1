package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;

/**
 *
 * @param <T>
 */
public interface OperationAtmDepartment<T extends Enum<T> & Money> {
    /**
     *
     * @return .
     */
    Integer restMoneyInAtm();

    /**
     *
     * @return .
     * @throws OperationAtmCanNotCompleteException .
     */
    boolean initDefault() throws OperationAtmCanNotCompleteException;

    /**
     *
     * @param initAtm .
     * @return .
     * @throws OperationAtmCanNotCompleteException .
     */
    boolean initDefault(EnumMap<T, Integer> initAtm) throws OperationAtmCanNotCompleteException;
}
