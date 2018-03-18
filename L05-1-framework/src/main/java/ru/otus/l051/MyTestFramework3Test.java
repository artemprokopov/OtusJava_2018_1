package ru.otus.l051;

import ru.otus.l051.annotation.MyBefore;
import ru.otus.l051.annotation.MyTest;

/**
 * Класс для проверки работы простого тестового фрейворка.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public class MyTestFramework3Test {
    /**
     * Метод выполняющияся до запуска каждого тестового метода.
     */
    @MyBefore
    public void testMyBefore1() throws Exception {
        System.out.println("MyTestFramework3Test");
        System.out.println("Launch MyBefore method 1");
        throw new Exception("test My Before exception MyTestFramework3Test");
    }

    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest1() {
        System.out.println("MyTestFramework3Test");
        System.out.println("Launch MyTest method 1");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest2() {
        System.out.println("MyTestFramework3Test");
        System.out.println("Launch MyTest method 2");
    }
    /**
     * Метод теста.
     */
    @MyTest
    public void testMyTest3() {
        System.out.println("MyTestFramework3Test");
        System.out.println("Launch MyTest method 3");
    }

}
