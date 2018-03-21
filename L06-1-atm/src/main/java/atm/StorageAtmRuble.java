package atm;

import atm.exception.OperationAtmCanNotCompleteException;

import java.util.EnumMap;
import java.util.Objects;

public enum StorageAtmRuble {
    INSTANCE;

    private static final EnumMap<MoneySovietRub, Integer> STORAGE_ATM
            = new EnumMap<MoneySovietRub, Integer>(MoneySovietRub.class);

    private static final String NOMINAL_BANKNOTES_OPERATION_ATM = "RUB";

    void initSlotAtmStorage(MoneySovietRub nominalBanknotes, Integer numberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(numberBanknotes);
        if (numberBanknotes < 1) {
            throw new OperationAtmCanNotCompleteException("Can not add 0 and less banknotes in slot ATM storage!");
        }
        STORAGE_ATM.put(nominalBanknotes, numberBanknotes);
    }

    public Integer getNumberBanknotesSlotAtmStorage(MoneySovietRub nominalBanknotes) {
        Objects.requireNonNull(nominalBanknotes);
        return STORAGE_ATM.get(nominalBanknotes);
    }

    public void decreaseNumberBannknotesSlotAtmStorage(MoneySovietRub nominalBanknotes,
                                                       Integer decreaseNumberBanknotes)
            throws OperationAtmCanNotCompleteException {
        Objects.requireNonNull(nominalBanknotes);
        Objects.requireNonNull(decreaseNumberBanknotes);
        if (STORAGE_ATM.get(nominalBanknotes).compareTo(decreaseNumberBanknotes) < 0) {
            throw new OperationAtmCanNotCompleteException("Operation decrease banknotes in ATM storage can not complete!" +
                    "Not enough banknotes in slot" + nominalBanknotes + " ATM storage.");
        }
        STORAGE_ATM.put(nominalBanknotes, (STORAGE_ATM.get(nominalBanknotes) - decreaseNumberBanknotes));
    }

    public  String getTypeCurrencyBanknotesOperation() {
        return NOMINAL_BANKNOTES_OPERATION_ATM;
    }

    public Integer remainMoneyInStorage() {
        Integer count = 0;
        for (MoneySovietRub msr: MoneySovietRub.values()) {
            count = count +  STORAGE_ATM.get(msr) * msr.getValue();
        }
        return count;
    }
}
