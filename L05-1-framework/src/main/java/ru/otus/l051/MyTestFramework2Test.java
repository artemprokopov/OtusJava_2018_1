package ru.otus.l051;
/**
 * Класс для проверки работы простого тестового фрейворка.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public class MyTestFramework2Test {
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest1() {
        System.out.println("MyTestFramework2Test");
        System.out.println("Launch MyTest method 1");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest2() {
        System.out.println("MyTestFramework2Test");
        System.out.println("Launch MyTest method 2");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest3() {
        System.out.println("MyTestFramework2Test");
        System.out.println("Launch MyTest method 3");
    }

}
