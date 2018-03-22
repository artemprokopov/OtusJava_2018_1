package atm;

import atm.exception.OperationAtmCanNotCompleteException;

public interface Atm<T extends Enum<T>> {
    void initSlotAtmStorage(T nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException;
    Integer getNumberBanknotesSlotAtmStorage(T nominalBanknotes);
    void decreaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;
    void increaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer increaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;
    TypeofMoney getTypeCurrencyBanknotesOperation();

    Integer remainMoneyInStorage();
}
