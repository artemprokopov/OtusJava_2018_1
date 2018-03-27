package atm;

import atm.exception.NotEnoughMoney;

import java.util.EnumMap;
import java.util.Objects;

public class GreedyAlgoritmOfWithdrawal<T extends StorageAtm<R>, R extends  Enum<R> & Money>
        implements AlgorithmOfWithdrawal<T, R> {
    @Override
    public EnumMap<R, Integer> toExecuteAlgorithm(T atm, Integer amountOfWithdrawals)
            throws NotEnoughMoney {
        Objects.requireNonNull(atm);
        Objects.requireNonNull(amountOfWithdrawals);

        EnumMap<R, Integer> result = atm.getConteynerResult();
        int temp = amountOfWithdrawals;
        result.clear();
        R[] notEmtySlot = atm.getNotEmptySlot();
        if (notEmtySlot.length == 0) {
            throw new NotEnoughMoney("Not enough money!");
        }
        for (R r : notEmtySlot) {
            Integer numberBanknotesSlotAtmStorage = atm.getNumberBanknotesSlotAtmStorage(r);
            int numberBanknotes = temp / r.getValue();
            if (numberBanknotesSlotAtmStorage < numberBanknotes) {
                numberBanknotes = numberBanknotesSlotAtmStorage;
            }
            result.put(r, numberBanknotes);
            temp = temp - numberBanknotes * r.getValue();
            if (temp == 0) {
                break;
            }
        }
        if (temp != 0) {
            throw new NotEnoughMoney("Can not issue this amount!");
        }
        return result;
    }
}
