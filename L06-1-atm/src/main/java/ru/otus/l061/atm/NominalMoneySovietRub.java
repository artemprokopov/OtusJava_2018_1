package ru.otus.l061.atm;
/**
 * Перечисление купюр советскиих рублей.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */
public enum NominalMoneySovietRub implements Money {
    /**
     * 100 рублей.
     */
    R100(100),
    /**
     * 50 рублей.
     */
    R50(50),
    /**
     * 25 рублей.
     */
    R25(25),
    /**
     * 10 рублей.
     */
    R10(10),
    /**
     * 5 рублей.
     */
    R5(5),
    /**
     * 3 рубля.
     */
    R3(3),
    /**
     * 1 рубль.
     */
    R1(1);
    /**
     * Значение номинала банкноты.
     */
    private final int value;

    /**
     * Конструктор.
     * @param initValue значение инициализирующее поле номинала банкноты.
     */
    private NominalMoneySovietRub(int initValue) {
        this.value = initValue;
    }

    /**
     * Возвращает значение номинала банкноты.
     * @return номинал соответствующей банкноты.
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Тип валюты банкнот.
     * @return тип валюты банкнот.
     */
    @Override
    public TypeofMoney getTypeOfMoney() {
        return TypeofMoney.RUB;
    }
}
