package ru.otus.l061.atm;

import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
/**
 * Интерфейс хранилища банкомата, предоставляет методы для работы хранилища.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @param <T> тип перечесление оперируемых банкнот.
 * @version 1.0
 */
public interface StorageAtm<T extends Enum<T> & Money> {
    /**
     * Метод инициализации слота хранилища.
     * @param nominalBanknotes Номинал банкнот помещаемых в хранилище.
     * @param numberBanknotes количечество банкнот.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    void initSlotAtmStorage(T nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException;

    /**
     * Метод возвращающий количество банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @return количество банеконт в указанном слоте хранилища.
     */
    Integer getNumberBanknotesSlotAtmStorage(T nominalBanknotes);

    /**
     * Уменьшает количесвто банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @param decreaseNumberBanknotes количество банкнот которые изымаются из слота хранилища.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    void decreaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;

    /**
     * Увеличивает количесвто банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @param increaseNumberBanknotes количество банкнот которые вносятся в слота хранилища.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    void increaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer increaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;

    /**
     * Возвращает тип банкнот которыми оперирует хранилище.
     * @return тип банкнот(тип валюты).
     */
    TypeofMoney getTypeCurrencyBanknotesOperation();

    /**
     * Проверяет, является ли слот хранилища пустым или нет.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @return true, если слот хранилища пустой, в противном случае возвращает false.
     */
    boolean slotIsEmpty(T nominalBanknotes);

    /**
     * Метод возвращает сумму остатка денежных средств в хранилище.
     * @return сумма оставшихся денежных средств в хранилище.
     */
    Integer restMoneyInStorage();

    /**
     * Возвращает массив номиналов банкнот, которыми оперирует данное хранилище.
     * @return массив номиналов банкнот типа T.
     */
    T[] getNominalBanknotes();

    /**
     * Возвращает массив номиналов банкнот идентификаторов слотов хранилища которые не являются пустыми.
     * @return массив номиналов банкнот идентификаторов слотов типа Т, которые в данный момент времени не являются пустыми.
     */
    T[] getNotEmptySlot();

    /**
     * Возвращает коллекцию EnumMap<T, Integer>  типовой контейнер результатов работы хранилища, и ожидаемый контейнер
     * работы взаимодействующих обхектов с ним.
     * Костыль, привет дженерикам Java.
     * @return контейнер результата типа EnumMap<T, Integer>.
     */
    EnumMap<T, Integer> getContainerResult();
}
