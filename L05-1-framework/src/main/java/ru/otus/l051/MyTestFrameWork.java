package ru.otus.l051;


import ru.otus.l051.annotation.MyAfter;
import ru.otus.l051.annotation.MyBefore;
import ru.otus.l051.annotation.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Простой тестовый фреймворк.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public class MyTestFrameWork {
    /**
     * Главный метод, запускаем методы фреймворка.
     * @param args аргументы передаваемые в программу.
     */
    public static void main(String[] args) {
        runTestWithClass("ru.otus.l051.MyTestFrameworkTest");
        runTestWithPackage("ru.otus.l051");
    }

    /**
     * Метод для теста класса по имени.
     * @param nameClass имя тестового класса.
     */
    public static void runTestWithClass(String nameClass) {
        System.out.println("Run method runTestWithClass.");
        Class aClass = ReflectionHelper.getClass(nameClass);
        runTest(aClass);
        System.out.println("Finish method runTestWithClass.");
    }

    /**
     * Метод для запуска тестовых классов в указанном пакете, тестовыми считаются классы
     * имя которых оканчивается на Test.
     * @param namePackage имя пакета в котором нужно запустить тестовые классы.
     */
    public static void runTestWithPackage(String namePackage) {
        System.out.println("Run method runTestWithPackage.");
        List<Class<?>>  classList = ReflectionHelper.getClasses(namePackage);
        for (Class<?> aClass : classList) {
            if (aClass.getTypeName().endsWith("Test")
                    && !aClass.isInterface()
                    && !aClass.isEnum()
                    && !aClass.isPrimitive()) {
                runTest(aClass);
            }
        }
        System.out.println("Finish method runTestWithPackage.");
    }

    /**
     * Метод поиска аннотированных аннотациями {@link MyTest}, {@link MyBefore}, {@link MyAfter} методов,
     * и их запуска.
     * @param clazz класс в котором производится поиск.
     */
    private static void  runTest(Class<?> clazz) {
        Object object = ReflectionHelper.instantiate(clazz);
        HashMap<Class<?>, List<Method>> annotationMethods = ReflectionHelper
                .getAnnotatedMethod(ReflectionHelper.getMethod(object),
                MyTest.class, MyBefore.class, MyAfter.class);
        List<Method> listBefore = annotationMethods.get(MyBefore.class);
        if (listBefore == null) {
            listBefore = new ArrayList<>();
        }
        List<Method> listAfter = annotationMethods.get(MyAfter.class);
        if (listAfter == null) {
            listAfter = new ArrayList<>();
        }
        List<Method> listTest = annotationMethods.get(MyTest.class);
        if (listTest == null) {
            listAfter = new ArrayList<>();
        }
        for (Method method : listTest) {
            object = ReflectionHelper.instantiate(clazz);
            if (!runTestMethod(object, listBefore)) {
                break;
            }
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            runTestMethod(object, listAfter);
        }
    }

    /**
     * Вынос повторяющегося кода.
     * @param object объект у которого вызываются методы
     * @param methodList лист методов.
     * @return возвращает true в случае успеха, в случае исключения возвращает false;
     */
    private static boolean runTestMethod(Object object, List<Method> methodList) {
        for (Method method : methodList) {
            try {
                method.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}