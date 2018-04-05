package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;

import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


/**
 * @param <T> .
 */
public class Department<T extends Enum<T> & Money> {
    /**
     * .
     */
    private  Map<String, OperationAtmDepartment<T>> storageAtm;

    /**
     * .
     */
    public Department() {
        this.storageAtm = new HashMap<>();
    }

    /**
     * .
     * @param initStorageAtm .
     */
    public Department(Map<String, OperationAtmDepartment<T>> initStorageAtm) {
        this.storageAtm = initStorageAtm;
    }

    /**
     * .
     * @param idAtm .
     * @param addAtm .
     * @return .
     */
    public boolean addAtm(String idAtm, OperationAtmDepartment<T> addAtm) {
        storageAtm.put(idAtm, addAtm);
        return true;
    }

    /**
     * .
     * @param idAtm .
     * @return .
     */
    public boolean deleteAtm(String idAtm) {
        storageAtm.remove(idAtm);
        return true;
    }

    /**
     * .
     * @return .
     * @throws OperationAtmCanNotCompleteException .
     */
    public boolean initAtmToDefault() throws OperationAtmCanNotCompleteException {
         storageAtm.values().forEach((i) -> {
                 try {
                     i.initDefault();
                 } catch (OperationAtmCanNotCompleteException oacnce) {
                   oacnce.printStackTrace();
                 }
                 });
        return true;
    }

    /**
     * .
     * @param initDefaultAtm .
     * @return .
     * @throws OperationAtmCanNotCompleteException .
     */
    public boolean initAtmToDefault(Map<String, EnumMap<T, Integer>> initDefaultAtm) throws OperationAtmCanNotCompleteException {
        initDefaultAtm.keySet().forEach((s) -> {
            try {
                storageAtm.get(s).initDefault(initDefaultAtm.get(s));
            } catch (OperationAtmCanNotCompleteException oacnce) {
                oacnce.printStackTrace();
            }
        });
        return true;
    }

    /**
     * .
     * @return .
     */
    public Integer sumRemainsAtm() {
        Integer i = 0;
        for (OperationAtmDepartment<T> tOperationAtmDepartment : storageAtm.values()) {
            i = i + tOperationAtmDepartment.restMoneyInAtm();
        }
        return i;
    }

}
