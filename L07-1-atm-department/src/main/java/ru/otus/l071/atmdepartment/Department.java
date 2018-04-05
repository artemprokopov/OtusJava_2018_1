package ru.otus.l071.atmdepartment;

import ru.otus.l061.atm.Money;

import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


/**
 * Класс реализующий функционал департамента группы АТМ.
 * @author Artem Prokopov
 * @since 04/04/2018
 * @param <T> тип оперируемых денежных единиц.
 * @version 1.0
 */
public class Department<T extends Enum<T> & Money> {
    /**
     * Поле хранящее группу управляемых АТМ. Параметр String, является уникальным идентификатором управляемого АТМ.
     */
    private final Map<String, OperationAtmDepartment<T>> storageAtm;

    /**
     * Конструктор. Создает пустое хранилище {@link Department#storageAtm}
     */
    public Department() {
        this.storageAtm = new HashMap<>();
    }

    /**
     * Конструктор, принимает и инициализирует хранилище {@link Department#storageAtm} предопределенным перечнем.
     * @param initStorageAtm перечень группы управляемых АТМ.
     */
    public Department(Map<String, OperationAtmDepartment<T>> initStorageAtm) {
        this.storageAtm = initStorageAtm;
    }

    /**
     * Добавление в перечень АТМ {@link Department#storageAtm} новых управляемых АТМ.
     * @param idAtm String уникальный идентификатор АТМ.
     * @param addAtm добавляемый АТМ.
     * @return  true если операция завершилась успехом, в противном случае вернет false.
     */
    public boolean addAtm(String idAtm, OperationAtmDepartment<T> addAtm) {
        storageAtm.put(idAtm, addAtm);
        return true;
    }

    /**
     * Удаляет из хранилища {@link Department#storageAtm} управляемы.
     * @param idAtm String уникальный идентификатор АТМ..
     * @return  true если операция завершилась успехом, в противном случае вернет false.
     */
    public boolean deleteAtm(String idAtm) {
        storageAtm.remove(idAtm);
        return true;
    }

    /**
     * Метод переводит все управляемые АТМ находящиеся в хранилище {@link Department#storageAtm} в состояние по умолчанию.
     * @return true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException ыбрасывается в случае невозможности завершить операцию АТМ.
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
     * Инициализирует все управляемые АТМ находящихся в хранилище {@link Department#storageAtm},
     * в соответсвии с конфигурациями переданных в initDefaultAtm, типа {@literal Map<String, EnumMap<T, Integer>>},
     * где String уникальный идентификатор АТМ, {@literal EnumMap<T, Integer>}  инициализирующие значения.
     * @param initDefaultAtm перечень инициализирующих значений в соответствии с уникальными id АТМ.
     * @return true если операция завершилась успехом, в противном случае вернет false.
     * @throws OperationAtmCanNotCompleteException выбрасывается в случае невозможности завершить операцию АТМ.
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
     * Возвразает сумму остаков денежных средств по всем управляемы АТМ находящихся в перечне АТМ {@link Department#storageAtm}.
     * @return сумму всех остатков денежных средств в управляемых АТМ.
     */
    public Integer sumRemainsAtm() {
        Integer i = 0;
        for (OperationAtmDepartment<T> tOperationAtmDepartment : storageAtm.values()) {
            i = i + tOperationAtmDepartment.restMoneyInAtm();
        }
        return i;
    }

}
