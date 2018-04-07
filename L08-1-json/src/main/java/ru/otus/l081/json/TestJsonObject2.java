package ru.otus.l081.json;

import java.util.Objects;

public class TestJsonObject2 {

    private Object NULL = null;
    private int intNew = 2;
    private TestJsonObject3 testJsonObject = new TestJsonObject3();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestJsonObject2 that = (TestJsonObject2) o;
        return intNew == that.intNew &&
                Objects.equals(NULL, that.NULL) &&
                Objects.equals(testJsonObject, that.testJsonObject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(NULL, intNew, testJsonObject);
    }
}
