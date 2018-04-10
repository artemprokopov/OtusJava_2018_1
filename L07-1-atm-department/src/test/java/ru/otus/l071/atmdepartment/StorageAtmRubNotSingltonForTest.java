package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.NominalMoneySovietRub;
import ru.otus.l061.atm.StorageAtm;
import ru.otus.l061.atm.TypeofMoney;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

/**
 * Хранилище, заменяем сингтоне на основе enum, для тестов, реализует интерфейс {@link StorageAtm}.
 * СОЗДАН ДЛЯ ТЕСТОВ {@link Department}.
 * ОБЯЗАТЕЛЬНО ДОЛЖЕН БЫТЬ КОПИЕЙ {@link ru.otus.l061.atm.StorageAtmRub}
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */
class StorageAtmRubNotSingltonForTest implements StorageAtm<NominalMoneySovietRub> {
    /**
     * Хранилище.
     */
    private  final EnumMap<NominalMoneySovietRub, Integer> storageAtm
            = new EnumMap<>(NominalMoneySovietRub.class);

    /**
     * Заполняем хранилище нулевыми значениями.
     */
    {
        for (NominalMoneySovietRub moneySovietRub: NominalMoneySovietRub.values()) {
            storageAtm.put(moneySovietRub, 0);
        }
    }

    /**
     * Конструктор, фэйкового объекта хранилища, для Тестов {@link Department}.
     */
    StorageAtmRubNotSingltonForTest() {
        System.out.println("Create test object StorageAtmRubNotSingltonForTest");
    }

    /**
     * Значение тип валюты которыми оперирует хранилище(далее возможно ввести метод изменяющи данное значение),
     * но так как мы имеем сингл тоне на основе enum, то типизировать его динамически не получится, посему
     * получится лучше делать хранилища отдельно.
     */
    private static final TypeofMoney NOMINAL_BANKNOTES_OPERATION_ATM = TypeofMoney.RUB;
    /**
     * Константа, максимально количество банкнот в слоте хранилища.
     */
    private static final int MAX_PLACE_IN_SLOTE_ATM = 100;
    /**
     * Константа, минимальное количество банкнот в слоте хранилища.
     */
    private static final int MIN_PLACE_IN_SLOTE_ATM = 0;

    /**
     * Метод инициализации слота хранилища.
     * @param nominalBanknotes Номинал банкнот помещаемых в хранилище.
     * @param numberBanknotes количечество банкнот.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    @Override
    public void initSlotAtmStorage(NominalMoneySovietRub nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(numberBanknotes);
        if (numberBanknotes < 1) {
            throw new OperationAtmCanNotCompleteException("ATM can not add 0 and less banknotes in slot storage!");
        }
        storageAtm.put(nominalBanknotes, numberBanknotes);
    }
    /**
     * Метод возвращающий количество банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @return количество банеконт в указанном слоте хранилища.
     */
    @Override
    public Integer getNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes) {
        Objects.requireNonNull(nominalBanknotes);
        return storageAtm.get(nominalBanknotes);
    }
    /**
     * Уменьшает количесвто банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @param decreaseNumberBanknotes количество банкнот которые изымаются из слота хранилища.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    @Override
    public void decreaseNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes,
                                                      Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(decreaseNumberBanknotes);
        if (storageAtm.get(nominalBanknotes).compareTo(decreaseNumberBanknotes) < 0) {
            throw new OperationAtmCanNotCompleteException("Operation decrease banknotes in ATM storage can not complete!"
                            + "Not enough banknotes in slot" + nominalBanknotes + " ATM storage.");
        }
        storageAtm.put(nominalBanknotes, (storageAtm.get(nominalBanknotes) - decreaseNumberBanknotes));
    }
    /**
     * Увеличивает количесвто банкнот в слоте хранилища.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @param increaseNumberBanknotes количество банкнот которые вносятся в слота хранилища.
     * @throws OperationAtmCanNotCompleteException искоючение выбрасываемое если операция не может быть выполнена.
     */
    @Override
    public void increaseNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes, Integer increaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(increaseNumberBanknotes);
        if (storageAtm.get(nominalBanknotes) + increaseNumberBanknotes > MAX_PLACE_IN_SLOTE_ATM) {
            throw new OperationAtmCanNotCompleteException("Operation decrease banknotes in ATM storage can not complete!"
                            + "Not enough banknotes in slot" + nominalBanknotes + " ATM storage.");
        }
        storageAtm.put(nominalBanknotes, (storageAtm.get(nominalBanknotes) - increaseNumberBanknotes));
    }
    /**
     * Возвращает тип банкнот которыми оперирует хранилище.
     * @return тип банкнот(тип валюты).
     */
    @Override
    public  TypeofMoney getTypeCurrencyBanknotesOperation() {
        return NOMINAL_BANKNOTES_OPERATION_ATM;
    }
    /**
     * Метод возвращает сумму остатка денежных средств в хранилище.
     * @return сумма оставшихся денежных средств в хранилище.
     */
    @Override
    public Integer restMoneyInStorage() {
        Integer count = 0;
        for (NominalMoneySovietRub msr: NominalMoneySovietRub.values()) {
            count = count +  storageAtm.get(msr) * msr.getValue();
        }
        return count;
    }
    /**
     * Проверяет, является ли слот хранилища пустым или нет.
     * @param nominalBanknotes номинал банкнот, определяет слот хранилища.
     * @return true, если слот хранилища пустой, в противном случае возвращает false.
     */
    @Override
    public boolean slotIsEmpty(NominalMoneySovietRub nominalBanknotes) {
        return storageAtm.get(nominalBanknotes) == MIN_PLACE_IN_SLOTE_ATM;
    }
    /**
     * Возвращает массив номиналов банкнот идентификаторов слотов хранилища которые не являются пустыми.
     * @return массив номиналов банкнот идентификаторов слотов типа Т, которые в данный момент времени не являются пустыми.
     */
    @Override
    public  NominalMoneySovietRub[]  getNominalBanknotes() {
       return NominalMoneySovietRub.values();
    }
    /**
     * Возвращает массив номиналов банкнот, которыми оперирует данное хранилище.
     * @return массив номиналов банкнот типа T.
     */
    @Override
    public NominalMoneySovietRub[] getNotEmptySlot() {
        List<NominalMoneySovietRub> result = new ArrayList();
        for (NominalMoneySovietRub nominalMoneySovietRub : NominalMoneySovietRub.values()) {
            if (!this.slotIsEmpty(nominalMoneySovietRub)) {
                result.add(nominalMoneySovietRub);
            }
        }
        return result.toArray(new NominalMoneySovietRub[result.size()]);
    }
    /**
     * Возвращает коллекцию EnumMap<T, Integer>  типовой контейнер результатов работы хранилища, и ожидаемый контейнер
     * работы взаимодействующих обхектов с ним.
     * Костыль, привет дженерикам Java.
     * @return контейнер результата типа EnumMap<T, Integer>.
     */
    @Override
    public EnumMap<NominalMoneySovietRub, Integer> getContainerResult() {
        return new EnumMap<>(NominalMoneySovietRub.class);
    }
}
