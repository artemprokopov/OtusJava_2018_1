package ru.orus.l051;


import sun.reflect.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.HashMap;
import java.util.List;

public class MyTestFrameWork {

    public static void main(String[] args) {
        runTestWithClass("ru.orus.l051.MyTestFrameworkTest");
    }

    public static void runTestWithClass(String nameClass) {
        Class aClass = ReflectionHelper.getClass(nameClass);
        Object object = ReflectionHelper.instantiate(aClass, null);
        HashMap<Class<?>, List<Method>> annotationMethods = ReflectionHelper.getAnotatedMethod(ReflectionHelper.getMethod(object),
                MyTest.class, MyBefore.class, MyAfter.class);
        for (Method method : annotationMethods.get(MyBefore.class)) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (Method method : annotationMethods.get(MyTest.class)) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (Method method : annotationMethods.get(MyAfter.class)) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void runTestWithPackage(String namePackage) {

    }


}