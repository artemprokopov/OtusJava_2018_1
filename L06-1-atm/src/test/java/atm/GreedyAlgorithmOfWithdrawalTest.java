package atm;

import atm.exception.NotEnoughMoneyException;
import atm.exception.OperationAtmCanNotCompleteException;
import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.assertEquals;
/**
 * Класс тест класса {@link GreedyAlgorithmOfWithdrawal} реализующий жадный адлгоритм
 * выдачи банкнот из хранилища банкомата.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */

public class GreedyAlgorithmOfWithdrawalTest {
    /**
     * Тестируем метод {@link GreedyAlgorithmOfWithdrawal#toExecuteAlgorithm(StorageAtm, Integer)}
     * заполняем хранилище каждый слот по 50 купюр, снимаем 500 денежных единиц
     * ожидаем выдачу 5 купюр номиналом 100.
     * @throws OperationAtmCanNotCompleteException если хранилище не может завершить операцию.
     * @throws NotEnoughMoneyException отсутствует необходимое количествокупбр в хранилище.
     */
    @Test
    public void toExecuteAlgorithm1() throws OperationAtmCanNotCompleteException, NotEnoughMoneyException {
        StorageAtm<NominalMoneySovietRub> storageAtm = StorageAtmRub.INSTANCE;
        AlgorithmOfWithdrawal<StorageAtm<NominalMoneySovietRub>,
                NominalMoneySovietRub> algorithmOfWithdrawal = new GreedyAlgorithmOfWithdrawal<>();
        for (NominalMoneySovietRub nominalMoneySovietRub : storageAtm.getNominalBanknotes()) {
            storageAtm.initSlotAtmStorage(nominalMoneySovietRub, 50);
        }
        EnumMap<NominalMoneySovietRub, Integer> result = new EnumMap<>(NominalMoneySovietRub.class);
        result.put(NominalMoneySovietRub.R100, 5);
        EnumMap<NominalMoneySovietRub, Integer> resultMethod = algorithmOfWithdrawal.toExecuteAlgorithm(storageAtm, 500);
        assertEquals(result, resultMethod);
    }
    /**
     * Тестируем метод {@link GreedyAlgorithmOfWithdrawal#toExecuteAlgorithm(StorageAtm, Integer)}
     * заполняем хранилище каждый слот по 50 купюр, снимаем 460 денежных единиц
     * ожидаем выдачу 4 купюры номиналом 100, 1 купюра номиналом 50, 1 купюра номиналом 10 .
     * @throws OperationAtmCanNotCompleteException если хранилище не может завершить операцию.
     * @throws NotEnoughMoneyException отсутствует необходимое количествокупбр в хранилище.
     */
    @Test
    public void toExecuteAlgorithm2() throws OperationAtmCanNotCompleteException, NotEnoughMoneyException {
        StorageAtm<NominalMoneySovietRub> storageAtm = StorageAtmRub.INSTANCE;
        AlgorithmOfWithdrawal<StorageAtm<NominalMoneySovietRub>,
                NominalMoneySovietRub> algorithmOfWithdrawal = new GreedyAlgorithmOfWithdrawal<>();
        for (NominalMoneySovietRub nominalMoneySovietRub : storageAtm.getNominalBanknotes()) {
            storageAtm.initSlotAtmStorage(nominalMoneySovietRub, 50);
        }
        EnumMap<NominalMoneySovietRub, Integer> result = new EnumMap<>(NominalMoneySovietRub.class);
        result.put(NominalMoneySovietRub.R100, 4);
        result.put(NominalMoneySovietRub.R50, 1);
        result.put(NominalMoneySovietRub.R10, 1);
        EnumMap<NominalMoneySovietRub, Integer> resultMethod = algorithmOfWithdrawal.toExecuteAlgorithm(storageAtm, 460);
        assertEquals(result, resultMethod);
    }
    /**
     * Тестируем метод {@link GreedyAlgorithmOfWithdrawal#toExecuteAlgorithm(StorageAtm, Integer)}
     * заполняем хранилище каждый слот по 50 купюр, снимаем 1289 денежных единиц
     * ожидаем выдачу 12 купюр номиналом 100, 1 купюра номиналом 50, 1 купюра номиналом 25, 1 купюра номиналом 10,
     * 1 купюра номиналом 3, 1 купюра номиналом 1.
     * @throws OperationAtmCanNotCompleteException если хранилище не может завершить операцию.
     * @throws NotEnoughMoneyException отсутствует необходимое количествокупбр в хранилище.
     */
    @Test
    public void toExecuteAlgorithm3() throws OperationAtmCanNotCompleteException, NotEnoughMoneyException {
        StorageAtm<NominalMoneySovietRub> storageAtm = StorageAtmRub.INSTANCE;
        AlgorithmOfWithdrawal<StorageAtm<NominalMoneySovietRub>,
                NominalMoneySovietRub> algorithmOfWithdrawal = new GreedyAlgorithmOfWithdrawal<>();
        for (NominalMoneySovietRub nominalMoneySovietRub : storageAtm.getNominalBanknotes()) {
            storageAtm.initSlotAtmStorage(nominalMoneySovietRub, 50);
        }
        EnumMap<NominalMoneySovietRub, Integer> result = new EnumMap<>(NominalMoneySovietRub.class);
        result.put(NominalMoneySovietRub.R100, 12);
        result.put(NominalMoneySovietRub.R50, 1);
        result.put(NominalMoneySovietRub.R25, 1);
        result.put(NominalMoneySovietRub.R10, 1);
        result.put(NominalMoneySovietRub.R3, 1);
        result.put(NominalMoneySovietRub.R1, 1);
        EnumMap<NominalMoneySovietRub, Integer> resultMethod = algorithmOfWithdrawal.toExecuteAlgorithm(storageAtm, 1289);
        assertEquals(result, resultMethod);
    }
    /**
     * Тестируем метод {@link GreedyAlgorithmOfWithdrawal#toExecuteAlgorithm(StorageAtm, Integer)}
     * заполняем хранилище каждый слот по 10 купюр, снимаем 1289 денежных единиц
     * ожидаем выдачу 10 купюр номиналом 100, 5 купюра номиналом 50, 1 купюра номиналом 25, 1 купюра номиналом 10,
     * 1 купюра номиналом 3, 1 купюра номиналом 1.
     * @throws OperationAtmCanNotCompleteException если хранилище не может завершить операцию.
     * @throws NotEnoughMoneyException отсутствует необходимое количествокупбр в хранилище.
     */
    @Test
    public void toExecuteAlgorithm4() throws OperationAtmCanNotCompleteException, NotEnoughMoneyException {
        StorageAtm<NominalMoneySovietRub> storageAtm = StorageAtmRub.INSTANCE;
        AlgorithmOfWithdrawal<StorageAtm<NominalMoneySovietRub>,
                NominalMoneySovietRub> algorithmOfWithdrawal = new GreedyAlgorithmOfWithdrawal<>();
        for (NominalMoneySovietRub nominalMoneySovietRub : storageAtm.getNominalBanknotes()) {
            storageAtm.initSlotAtmStorage(nominalMoneySovietRub, 10);
        }
        EnumMap<NominalMoneySovietRub, Integer> result = new EnumMap<>(NominalMoneySovietRub.class);
        result.put(NominalMoneySovietRub.R100, 10);
        result.put(NominalMoneySovietRub.R50, 5);
        result.put(NominalMoneySovietRub.R25, 1);
        result.put(NominalMoneySovietRub.R10, 1);
        result.put(NominalMoneySovietRub.R3, 1);
        result.put(NominalMoneySovietRub.R1, 1);
        EnumMap<NominalMoneySovietRub, Integer> resultMethod = algorithmOfWithdrawal.toExecuteAlgorithm(storageAtm, 1289);
        assertEquals(result, resultMethod);
    }
    /**
     * Тестируем метод {@link GreedyAlgorithmOfWithdrawal#toExecuteAlgorithm(StorageAtm, Integer)}
     * заполняем хранилище каждый слот по 1 купюре, снимаем 1289 денежных единиц
     * ожидаем исключение {@link NotEnoughMoneyException}.
     * @throws OperationAtmCanNotCompleteException если хранилище не может завершить операцию.
     * @throws NotEnoughMoneyException отсутствует необходимое количествокупбр в хранилище.
     */
    @Test(expected = NotEnoughMoneyException.class)
    public void toExecuteAlgorithm5() throws OperationAtmCanNotCompleteException, NotEnoughMoneyException {
        StorageAtm<NominalMoneySovietRub> storageAtm = StorageAtmRub.INSTANCE;
        AlgorithmOfWithdrawal<StorageAtm<NominalMoneySovietRub>,
                NominalMoneySovietRub> algorithmOfWithdrawal = new GreedyAlgorithmOfWithdrawal<>();
        for (NominalMoneySovietRub nominalMoneySovietRub : storageAtm.getNominalBanknotes()) {
            storageAtm.initSlotAtmStorage(nominalMoneySovietRub, 1);
        }
        algorithmOfWithdrawal.toExecuteAlgorithm(storageAtm, 1289);
    }
}