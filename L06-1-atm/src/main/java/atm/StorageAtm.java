package atm;

import atm.exception.OperationAtmCanNotCompleteException;

public interface StorageAtm<T extends Enum<T> & Money> {
    void initSlotAtmStorage(T nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException;
    Integer getNumberBanknotesSlotAtmStorage(T nominalBanknotes);
    void decreaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;
    void increaseNumberBanknotesSlotAtmStorage(T nominalBanknotes, Integer increaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException;
    TypeofMoney getTypeCurrencyBanknotesOperation();

    boolean slotIsEmpty(T nominalBanknotes);

    Integer remainMoneyInStorage();

    T[] getNominalBanknotes();
}
