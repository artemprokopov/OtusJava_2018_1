package ru.otus.l061.atm;
/**
 * Интерфейс определяющий тип денег.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */
public interface Money {
    /**
     * Метод возвращает номинал денежной купюры.
     * @return целочисленный номинал денежной купюры.
     */
    int getValue();

    /**
     * Возвращает тип валюты.
     * @return тип валюты.
     */
    TypeofMoney getTypeOfMoney();
}
