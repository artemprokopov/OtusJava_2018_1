package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;
import ru.otus.l061.atm.StorageAtm;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;

/**
 * Класс реализующий интерфейс {@link OperationAtmDepartment}, для работы с АТМ на уровне департамента.
 * @author Artem Prokopov
 * @since 04/04/2018
 * @param <T> тип оперируемых денежных единиц.
 * @version 1.0
 */
public class AtmDepartment<T extends Enum<T> & Money> implements OperationAtmDepartment<T> {
    /**
     * Ссылка на управляемый АТМ.
     */
    private final StorageAtm<T> storageAtm;
    /**
     * Поле инициализации АТМ по умолчанию.
     */
    private final EnumMap<T, Integer> defaultAtm;

    /**
     * Конструктор, инициализирует поля.
     * @param initStorageAtm поле ссылки на управляемый АТМ.
     * @param initDefault поле конфигурации инициализации АТМ по умолчению.
     */
    public AtmDepartment(StorageAtm<T> initStorageAtm, EnumMap<T, Integer> initDefault) {
        this.storageAtm = initStorageAtm;
        this.defaultAtm = initDefault;
    }

    /**
     * Иетод возвращает остаток денежных средств в АТМ.
     * @return сумма сотатка денежных средств в АТМ.
     */
    @Override
    public Integer restMoneyInAtm() {
        return storageAtm.restMoneyInStorage();
    }
    /**
     * Инициализирует банкомат по умолчанию.
     * @return true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException выбрасывается в случае невозможности завершить операцию АТМ.
     */
    @Override
    public boolean initDefault() throws OperationAtmCanNotCompleteException {
        for (T t : defaultAtm.keySet()) {
            storageAtm.initSlotAtmStorage(t, defaultAtm.get(t));
        }
        return true;
    }

    /**
     * Инициализирует банкомат значениями переданными в параметре.
     * @param initAtm EnumMap<T, Integer> с инициализирующими значениями для АТМ.
     * @return  true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException ыбрасывается в случае невозможности завершить операцию АТМ.
     */
    @Override
    public boolean initDefault(EnumMap<T, Integer> initAtm) throws OperationAtmCanNotCompleteException {
        for (T t : initAtm.keySet()) {
            storageAtm.initSlotAtmStorage(t, defaultAtm.get(t));
        }
        return true;
    }
}
