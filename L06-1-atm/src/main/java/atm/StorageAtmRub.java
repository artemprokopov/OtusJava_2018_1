package atm;

import atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.Objects;

public enum StorageAtmRub implements Atm<NominalMoneySovietRub> {
    INSTANCE;

    private static final EnumMap<NominalMoneySovietRub, Integer> STORAGE_ATM
            = new EnumMap<>(NominalMoneySovietRub.class);
    static {
        for (NominalMoneySovietRub moneySovietRubsr: NominalMoneySovietRub.values()) {
            STORAGE_ATM.put(moneySovietRubsr, 0);
        }
    }

    private static final TypeofMoney NOMINAL_BANKNOTES_OPERATION_ATM = TypeofMoney.RUB;
    private static final int MAX_PLACE_IN_SLOTE_ATM = 100;

    @Override
    public void initSlotAtmStorage(NominalMoneySovietRub nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(numberBanknotes);
        if (numberBanknotes < 1) {
            throw new OperationAtmCanNotCompleteException("ATM can not add 0 and less banknotes in slot storage!");
        }
        STORAGE_ATM.put(nominalBanknotes, numberBanknotes);
    }
    @Override
    public Integer getNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes) {
        Objects.requireNonNull(nominalBanknotes);
        return STORAGE_ATM.get(nominalBanknotes);
    }
    @Override
    public void decreaseNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes,
                                                      Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(decreaseNumberBanknotes);
        if (STORAGE_ATM.get(nominalBanknotes).compareTo(decreaseNumberBanknotes) < 0) {
            throw new OperationAtmCanNotCompleteException("Operation decrease banknotes in ATM storage can not complete!"
                            + "Not enough banknotes in slot" + nominalBanknotes + " ATM storage.");
        }
        STORAGE_ATM.put(nominalBanknotes, (STORAGE_ATM.get(nominalBanknotes) - decreaseNumberBanknotes));
    }

    @Override
    public void increaseNumberBanknotesSlotAtmStorage(NominalMoneySovietRub nominalBanknotes, Integer increaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(increaseNumberBanknotes);
        if (STORAGE_ATM.get(nominalBanknotes) + increaseNumberBanknotes > MAX_PLACE_IN_SLOTE_ATM) {
            throw new OperationAtmCanNotCompleteException("Operation decrease banknotes in ATM storage can not complete!"
                            + "Not enough banknotes in slot" + nominalBanknotes + " ATM storage.");
        }
        STORAGE_ATM.put(nominalBanknotes, (STORAGE_ATM.get(nominalBanknotes) - increaseNumberBanknotes));
    }

    @Override
    public  TypeofMoney getTypeCurrencyBanknotesOperation() {
        return NOMINAL_BANKNOTES_OPERATION_ATM;
    }

    @Override
    public Integer remainMoneyInStorage() {
        Integer count = 0;
        for (NominalMoneySovietRub msr: NominalMoneySovietRub.values()) {
            count = count +  STORAGE_ATM.get(msr) * msr.getValue();
        }
        return count;
    }
}
