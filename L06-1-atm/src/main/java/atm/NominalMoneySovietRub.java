package atm;

public enum NominalMoneySovietRub implements Money {
    R100(100),
    R50(50),
    R25(25),
    R10(10),
    R5(5),
    R3(3),
    R1(1);

    private final int value;

    private NominalMoneySovietRub(int initValue) {
        this.value = initValue;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public TypeofMoney getTypeOfMoney() {
        return TypeofMoney.RUB;
    }
}
