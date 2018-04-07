package ru.otus.l081.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class TestJsonObject {

    private Object NULL = null;
    private int i = 1;
    private long l = 1000L;
    private float f = 0.2f;
    private double d = 0.000003;
    private boolean b = true;
    private Integer I = 100;
    private Long L = 200L;
    private Float F = 0.5F;
    private Double D = 0.236598;
    private Boolean B = true;
    private BigInteger bigI = BigInteger.valueOf(100000000);
    private BigDecimal bigDec = BigDecimal.valueOf(2.44566);
    private String hello = " Hello";
    private String stnul = null;
    private String[] stringArray = {"1", "2", "3", "4"};
    private String[] stringArray1 = {"aaaa", null, "cccc"};
    private int[] intArray = {1, 2, 3};
    private long[] longArray = {1, 2, 3, 4};
    private float[] floatArray = {0.1F, 0.00005F, 0.9999F};
    private double[] doubleArray = {3D, 0.2D, 3D, 0.2D, 3D, 0.2D};
    private Integer[] IntegerArray = {1, 2, null, 3, 4, 5};
    private Long[] LongArray = {45L, 56L, 67L};
    private Float[] FloatArray = {4.677F, 45.6789F, 0F };
    private Double[] DoubleArray = {4.5, 45.677, 7.890};
    private BigInteger[] BigIntArray = {BigInteger.valueOf(3534534L), BigInteger.valueOf(3242314L)};
    private BigDecimal[] BigDecArray = {BigDecimal.valueOf(2365.6464), BigDecimal.valueOf(4352354,2354234)};
    private TestJsonObject2 testJsonObject2 = new TestJsonObject2();
    private TestJsonObject2[] testJsonObject2Array = {new TestJsonObject2(), new TestJsonObject2()};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestJsonObject that = (TestJsonObject) o;
        return i == that.i
                && l == that.l
                && Float.compare(that.f, f) == 0 &&
                Double.compare(that.d, d) == 0 &&
                b == that.b &&
                Objects.equals(NULL, that.NULL) &&
                Objects.equals(I, that.I) &&
                Objects.equals(L, that.L) &&
                Objects.equals(F, that.F) &&
                Objects.equals(D, that.D) &&
                Objects.equals(B, that.B) &&
                Objects.equals(bigI, that.bigI) &&
                Objects.equals(bigDec, that.bigDec) &&
                Objects.equals(hello, that.hello) &&
                Objects.equals(stnul, that.stnul) &&
                Arrays.equals(stringArray, that.stringArray) &&
                Arrays.equals(stringArray1, that.stringArray1) &&
                Arrays.equals(intArray, that.intArray) &&
                Arrays.equals(longArray, that.longArray) &&
                Arrays.equals(floatArray, that.floatArray) &&
                Arrays.equals(doubleArray, that.doubleArray) &&
                Arrays.equals(IntegerArray, that.IntegerArray) &&
                Arrays.equals(LongArray, that.LongArray) &&
                Arrays.equals(FloatArray, that.FloatArray) &&
                Arrays.equals(DoubleArray, that.DoubleArray) &&
                Arrays.equals(BigIntArray, that.BigIntArray) &&
                Arrays.equals(BigDecArray, that.BigDecArray) &&
                Objects.equals(testJsonObject2, that.testJsonObject2) &&
                Arrays.equals(testJsonObject2Array, that.testJsonObject2Array);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(NULL, i, l, f, d, b, I, L, F, D, B, bigI, bigDec, hello, stnul, testJsonObject2);
        result = 31 * result + Arrays.hashCode(stringArray);
        result = 31 * result + Arrays.hashCode(stringArray1);
        result = 31 * result + Arrays.hashCode(intArray);
        result = 31 * result + Arrays.hashCode(longArray);
        result = 31 * result + Arrays.hashCode(floatArray);
        result = 31 * result + Arrays.hashCode(doubleArray);
        result = 31 * result + Arrays.hashCode(IntegerArray);
        result = 31 * result + Arrays.hashCode(LongArray);
        result = 31 * result + Arrays.hashCode(FloatArray);
        result = 31 * result + Arrays.hashCode(DoubleArray);
        result = 31 * result + Arrays.hashCode(BigIntArray);
        result = 31 * result + Arrays.hashCode(BigDecArray);
        result = 31 * result + Arrays.hashCode(testJsonObject2Array);
        return result;
    }
}
