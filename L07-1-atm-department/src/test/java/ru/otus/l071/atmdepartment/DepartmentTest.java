package ru.otus.l071.atmdepartment;

import org.junit.Test;
import ru.otus.l061.atm.NominalMoneySovietRub;
import ru.otus.l061.atm.StorageAtm;
import ru.otus.l061.atm.StorageAtmRub;
import ru.otus.l061.atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Класс тест класса {@link Department} реализующий департамент АТМ.
 * @author Artem Prokopov
 * @since 10/04/2018
 * @version 1.0
 */

public class DepartmentTest {
    /**
     * Хранилище ATM.
     */
    private StorageAtm<NominalMoneySovietRub> atmN1 = StorageAtmRub.INSTANCE;
    /**
     * Тестовое хранилище АТМ, создано на базе класса {@link StorageAtmRubNotSingltonForTest}.
     */
    private StorageAtm<NominalMoneySovietRub> atmN2 = new StorageAtmRubNotSingltonForTest();
    /**
     * Тестовое хранилище АТМ, создано на базе класса {@link StorageAtmRubNotSingltonForTest}.
     */
    private StorageAtm<NominalMoneySovietRub> atmN3 = new StorageAtmRubNotSingltonForTest();

    /**
     * Инициализтрующие значения для класса {@link AtmDepartment} хранилище {@link DepartmentTest#atmN1}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initAtmN1 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initAtmN1.put(NominalMoneySovietRub.R100, 30);
        initAtmN1.put(NominalMoneySovietRub.R50, 30);
        initAtmN1.put(NominalMoneySovietRub.R25, 30);
        initAtmN1.put(NominalMoneySovietRub.R10, 30);
        initAtmN1.put(NominalMoneySovietRub.R5, 30);
        initAtmN1.put(NominalMoneySovietRub.R3, 30);
        initAtmN1.put(NominalMoneySovietRub.R1, 30);
    }

    /**
     * Инициализтрующие значения для класса {@link AtmDepartment} хранилище {@link DepartmentTest#atmN2}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initAtmN2 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initAtmN1.put(NominalMoneySovietRub.R100, 20);
        initAtmN1.put(NominalMoneySovietRub.R50, 20);
        initAtmN1.put(NominalMoneySovietRub.R25, 20);
        initAtmN1.put(NominalMoneySovietRub.R10, 20);
        initAtmN1.put(NominalMoneySovietRub.R5, 20);
        initAtmN1.put(NominalMoneySovietRub.R3, 20);
        initAtmN1.put(NominalMoneySovietRub.R1, 20);
    }

    /**
     * Инициализтрующие значения для класса {@link AtmDepartment}хранилище {@link DepartmentTest#atmN3}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initAtmN3 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initAtmN1.put(NominalMoneySovietRub.R100, 10);
        initAtmN1.put(NominalMoneySovietRub.R50, 10);
        initAtmN1.put(NominalMoneySovietRub.R25, 10);
        initAtmN1.put(NominalMoneySovietRub.R10, 10);
        initAtmN1.put(NominalMoneySovietRub.R5, 10);
        initAtmN1.put(NominalMoneySovietRub.R3, 10);
        initAtmN1.put(NominalMoneySovietRub.R1, 10);
    }

    /**
     * Инициализтрующие значения для теста метода {@link AtmDepartment#initDefault(EnumMap)}, передаем в метод в составе
     * пакета {@link DepartmentTest#newDefaultInitAtm}, для хранилища {@link DepartmentTest#atmN1}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initNewAtmN1 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initNewAtmN1.put(NominalMoneySovietRub.R100, 40);
        initNewAtmN1.put(NominalMoneySovietRub.R50, 30);
        initNewAtmN1.put(NominalMoneySovietRub.R25, 30);
        initNewAtmN1.put(NominalMoneySovietRub.R10, 30);
        initNewAtmN1.put(NominalMoneySovietRub.R5, 30);
        initNewAtmN1.put(NominalMoneySovietRub.R3, 30);
        initNewAtmN1.put(NominalMoneySovietRub.R1, 30);
    }
    /**
     * Инициализтрующие значения для теста метода {@link AtmDepartment#initDefault(EnumMap)}, передаем в метод в составе
     * пакета {@link DepartmentTest#newDefaultInitAtm}, для хранилища {@link DepartmentTest#atmN2}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initNewAtmN2 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initNewAtmN2.put(NominalMoneySovietRub.R100, 20);
        initNewAtmN2.put(NominalMoneySovietRub.R50, 20);
        initNewAtmN2.put(NominalMoneySovietRub.R25, 20);
        initNewAtmN2.put(NominalMoneySovietRub.R10, 50);
        initNewAtmN2.put(NominalMoneySovietRub.R5, 20);
        initNewAtmN2.put(NominalMoneySovietRub.R3, 20);
        initNewAtmN2.put(NominalMoneySovietRub.R1, 20);
    }
    /**
     * Инициализтрующие значения для теста метода {@link AtmDepartment#initDefault(EnumMap)}, передаем в метод в составе
     * пакета {@link DepartmentTest#newDefaultInitAtm}, для хранилища {@link DepartmentTest#atmN3}.
     */
    private EnumMap<NominalMoneySovietRub, Integer> initNewAtmN3 = new EnumMap<>(NominalMoneySovietRub.class);
    {
        initNewAtmN3.put(NominalMoneySovietRub.R100, 10);
        initNewAtmN3.put(NominalMoneySovietRub.R50, 10);
        initNewAtmN3.put(NominalMoneySovietRub.R25, 10);
        initNewAtmN3.put(NominalMoneySovietRub.R10, 15);
        initNewAtmN3.put(NominalMoneySovietRub.R5, 10);
        initNewAtmN3.put(NominalMoneySovietRub.R3, 10);
        initNewAtmN3.put(NominalMoneySovietRub.R1, 10);
    }

    /**
     * Пакет инициализирующих значений пакета для теста метода {@link AtmDepartment#initDefault(EnumMap)}.
     */
    private Map<String, EnumMap<NominalMoneySovietRub, Integer>> newDefaultInitAtm = new HashMap<>();
    {
        newDefaultInitAtm.put("Atm1", initNewAtmN1);
        newDefaultInitAtm.put("Atm2", initNewAtmN2);
        newDefaultInitAtm.put("Atm3", initNewAtmN3);
    }

    /**
     * Создаем класс AtmDepartment для работы с хранилищем {@link DepartmentTest#atmN1}.
     */
    private  OperationAtmDepartment<NominalMoneySovietRub> operationAtmDepartment1 =
            new AtmDepartment<>(atmN1, initAtmN1);
    /**
     * Создаем класс AtmDepartment для работы с хранилищем {@link DepartmentTest#atmN2}.
     */
    private  OperationAtmDepartment<NominalMoneySovietRub> operationAtmDepartment2 =
            new AtmDepartment<>(atmN2, initAtmN2);
    /**
     * Создаем класс AtmDepartment для работы с хранилищем {@link DepartmentTest#atmN3}.
     */
    private  OperationAtmDepartment<NominalMoneySovietRub> operationAtmDepartment3 =
            new AtmDepartment<>(atmN3, initAtmN3);

    /**
     * Тест метода {@link Department#initAtmToDefault()}.
     * @throws OperationAtmCanNotCompleteException исключение генерируется если операция не может быть выполнена хранилищем.
     */
    @Test
    public void initAtmToDefault() throws OperationAtmCanNotCompleteException {
        Department<NominalMoneySovietRub> department = new Department<>();
        department.addAtm("Atm1", operationAtmDepartment1);
        department.addAtm("Atm2", operationAtmDepartment2);
        department.addAtm("Atm3", operationAtmDepartment3);
        department.initAtmToDefault();
        for (NominalMoneySovietRub nominalMoneySovietRub : initAtmN1.keySet()) {
            assertEquals(initAtmN1.get(nominalMoneySovietRub), atmN1.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
        for (NominalMoneySovietRub nominalMoneySovietRub : initAtmN2.keySet()) {
            assertEquals(initAtmN1.get(nominalMoneySovietRub), atmN2.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
        for (NominalMoneySovietRub nominalMoneySovietRub : initAtmN3.keySet()) {
            assertEquals(initAtmN1.get(nominalMoneySovietRub), atmN3.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
    }

    /**
     * Тест метода {@link Department#initAtmToDefault(Map)}.
     * @throws OperationAtmCanNotCompleteException исключение генерируется если операция не может быть выполнена хранилищем.
     */
    @Test
    public void initAtmToDefaultWithNewValue() throws OperationAtmCanNotCompleteException {
        Department<NominalMoneySovietRub> department = new Department<>();
        department.addAtm("Atm1", operationAtmDepartment1);
        department.addAtm("Atm2", operationAtmDepartment2);
        department.addAtm("Atm3", operationAtmDepartment3);
        department.initAtmToDefault(newDefaultInitAtm);
        for (NominalMoneySovietRub nominalMoneySovietRub : initNewAtmN1.keySet()) {
            assertEquals(initNewAtmN1.get(nominalMoneySovietRub), atmN1.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
        for (NominalMoneySovietRub nominalMoneySovietRub :initNewAtmN2.keySet()) {
            assertEquals(initNewAtmN2.get(nominalMoneySovietRub), atmN2.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
        for (NominalMoneySovietRub nominalMoneySovietRub : initNewAtmN3.keySet()) {
            assertEquals(initNewAtmN3.get(nominalMoneySovietRub), atmN3.getNumberBanknotesSlotAtmStorage(nominalMoneySovietRub));
        }
    }
}