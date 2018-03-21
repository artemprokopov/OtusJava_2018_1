package atm;

public enum MoneySovietRub {
    R100(100),
    R50(50),
    R25(25),
    R10(10),
    R5(5),
    R3(3),
    R1(1);

    private final int value;

    private MoneySovietRub (int initValue) {
        this.value = initValue;
    }

    public int getValue() {
        return value;
    }
}
