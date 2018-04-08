package ru.otus.l071.atmdepartment;

import org.junit.Test;
import ru.otus.l061.atm.Atm;
import ru.otus.l061.atm.GreedyAlgorithmOfWithdrawal;
import ru.otus.l061.atm.NominalMoneySovietRub;
import ru.otus.l061.atm.StorageAtmRub;


/**
 *
 */

public class DepartmentTest {
    /**
     *
     */
    private Atm<StorageAtmRub, NominalMoneySovietRub,
            GreedyAlgorithmOfWithdrawal<StorageAtmRub, NominalMoneySovietRub>> atmN1
            = new Atm<>(StorageAtmRub.INSTANCE, new GreedyAlgorithmOfWithdrawal());
    /**
     *
     */
    private Atm<StorageAtmRubNotSingltonForTest, NominalMoneySovietRub,
            GreedyAlgorithmOfWithdrawal<StorageAtmRubNotSingltonForTest, NominalMoneySovietRub>> atmN2
            = new Atm<>(new StorageAtmRubNotSingltonForTest(), new GreedyAlgorithmOfWithdrawal());

    /**
     *
     */
    @Test
    public void initAtmToDefault() {
    }
}