package ru.orus.l051;

public class MyTestFrameworkTest {
    @MyAfter
    public void testMyAfter1() {
        System.out.println("Launch MyAfter method 1");
    }

    @MyAfter
    public void testMyAfter2() {
        System.out.println("Launch MyAfter method 2");
    }

    @MyAfter
    public void testMyAfter3() {
        System.out.println("Launch MyAfter method 3");
    }

    @MyBefore
    public void testMyBefore1() {
        System.out.println("Launch MyBefore method 1");
    }

    @MyBefore
    public void testMyBefore2() {
        System.out.println("Launch MyBefore method 2");
    }

    @MyBefore
    public void testMyBefore3() {
        System.out.println("Launch MyBefore method 3");
    }

    @MyTest
    public void testMyTest1() {
        System.out.println("Launch MyTest method 1");
    }

    @MyTest
    public void testMyTest2() {
        System.out.println("Launch MyTest method 2");
    }

    @MyTest
    public void testMyTest3() {
        System.out.println("Launch MyTest method 3");
    }

}
