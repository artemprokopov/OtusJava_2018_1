package ru.otus.l051;

import ru.otus.l051.annotation.MyAfter;
import ru.otus.l051.annotation.MyBefore;
import ru.otus.l051.annotation.MyTest;

/**
 * Класс для проверки работы простого тестового фрейворка.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public class MyTestFrameworkTest {
    /**
     * Метод выполняющияся после каждого тестового метода.
     */
    @MyAfter
    public void testMyAfter1() {
        System.out.println("Launch MyAfter method 1");
    }
    /**
     * Метод выполняющияся после каждого тестового метода.
     */
    @MyAfter
    public void testMyAfter2() {
        System.out.println("Launch MyAfter method 2");
    }
    /**
     * Метод выполняющияся после каждого тестового метода.
     */
    @MyAfter
    public void testMyAfter3() {
        System.out.println("Launch MyAfter method 3");
    }
    /**
     * Метод выполняющияся до запуска каждого тестового метода.
     */
    @MyBefore
    public void testMyBefore1() {
        System.out.println("Launch MyBefore method 1");
    }
    /**
     * Метод выполняющияся до запуска каждого тестового метода.
     */
    @MyBefore
    public void testMyBefore2() {
        System.out.println("Launch MyBefore method 2");
    }
    /**
     * Метод выполняющияся до запуска каждого тестового метода.
     */
    @MyBefore
    public void testMyBefore3() {
        System.out.println("Launch MyBefore method 3");
    }

    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest1() {
        System.out.println("Launch MyTest method 1");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest2() {
        System.out.println("Launch MyTest method 2");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest3() {
        System.out.println("Launch MyTest method 3");
    }

}
