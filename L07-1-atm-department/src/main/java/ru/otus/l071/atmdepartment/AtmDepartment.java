package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;
import ru.otus.l061.atm.StorageAtm;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;

/**
 *
 * @param <T>
 */
public class AtmDepartment<T extends Enum<T> & Money> implements OperationAtmDepartment<T> {
    /**
     *
     */
    private final StorageAtm<T> storageAtm;
    /**
     *
     */
    private final EnumMap<T, Integer> defaultAtm;

    /**
     *
     * @param initStorageAtm .
     * @param initDefault .
     */
    public AtmDepartment(StorageAtm<T> initStorageAtm, EnumMap<T, Integer> initDefault) {
        this.storageAtm = initStorageAtm;
        this.defaultAtm = initDefault;
    }

    /**
     *
     * @return .
     */
    @Override
    public Integer restMoneyInAtm() {
        return storageAtm.restMoneyInStorage();
    }

    @Override
    public boolean initDefault() throws OperationAtmCanNotCompleteException {
        for (T t : defaultAtm.keySet()) {
            storageAtm.initSlotAtmStorage(t, defaultAtm.get(t));
        }
        return true;
    }

    /**
     *
     * @param initAtm .
     * @return .
     * @throws OperationAtmCanNotCompleteException .
     */
    @Override
    public boolean initDefault(EnumMap<T, Integer> initAtm) throws OperationAtmCanNotCompleteException {
        for (T t : initAtm.keySet()) {
            storageAtm.initSlotAtmStorage(t, defaultAtm.get(t));
        }
        return true;
    }
}
