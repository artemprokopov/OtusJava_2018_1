package ru.otus.l051;



import java.io.File;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Класс утилитный, для реализации методов рефлексии.
 * @author Chibrikov Vitaly, Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
@SuppressWarnings("SameParameterValue")
class ReflectionHelper {
    /**
     *Приватный конструктор с выбросом исключения, для того чтобы не дать создать экземпляр класса.
     */
    private ReflectionHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Полкчение класса по его имени.
     * @param nameClass имя класса.
     * @return либо объект типа Class или если класса не сузествует возвращает null/
     */
    static Class<?> getClass(String nameClass) {
        Class<?> tClass = null;
        try {
            tClass = Class.forName(nameClass);
        } catch (ClassNotFoundException e) {
            System.out.println("Class for test not found!");
        }
        return tClass;
    }

    /**
     * Создание объекта указанного класса.
     * @param type тип создаваемого объекта.
     * @param args аргументы конструктора.
     * @param <T> тип
     * @return объект в случае успеха, иначе null.
     */
    static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args == null || args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Получени  поля объекта по его  имени.
     * @param object объект поле которого мы хотим получить.
     * @param name имя поля.
     * @return поле объекта в случае успеха, если поле не существует возвращается null.
     */
    static Object getFieldValue(Object object, String name) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.canAccess(object);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
        return null;
    }

    /**
     * Присваивание значения полю объекта.
     * @param object объект полю которого присваивается значение.
     * @param name имя  поля.
     * @param value значение поля.
     */
    static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.canAccess(object);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    /**
     * Получение всех методов объекта.
     * @param object объект методы которого хоти получить.
     * @return List методов.
     */
    static List<Method> getMethod(Object object) {
        Method[] methods = null;
        try {
            methods = object.getClass().getMethods();
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return Arrays.asList(methods);
    }

    /**
     * Получени всех аннотированных меодов.
     * @param methods List методов объекта.
     * @param annotationClass перечень аннотаций для поиска.
     * @return HashMap c ключем по типу аннотации, значение List методов аннотированных данной аннотацией.
     */
    static HashMap<Class<?>, List<Method>> getAnotatedMethod(List<Method> methods, Class<?>... annotationClass) {
        HashMap<Class<?>, List<Method>> annotationMethods = new HashMap<>();
        for (Method method : methods) {
            for (Class aClass : annotationClass) {
                if (method.isAnnotationPresent(aClass)) {
                    if (annotationMethods.containsKey(aClass)) {
                        annotationMethods.get(aClass).add(method);
                    } else {
                        annotationMethods.put(aClass, new ArrayList<Method>());
                        annotationMethods.get(aClass).add(method);
                    }
                }
            }
        }
        return annotationMethods;
    }

    /**
     * Вызов метода объекта по его имени.
     * @param object объект у которого вызывается метод.
     * @param name имя метода.
     * @param args аргументы передаваемые в метод.
     * @return результат вызова метода.
     */
    static Object callMethod(Object object, String name, Object... args) {
        Method method = null;
        boolean isAccessible = true;
        try {
            method = object.getClass().getDeclaredMethod(name, toClasses(args));
            isAccessible = method.canAccess(object);
            method.setAccessible(isAccessible);
            return method.invoke(object, args);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
        return null;
    }

    /**
     * Получение массива классов по перечьню объектов.
     * @param args перечень объектов.
     * @return Массив классов.
     */
    private static Class[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
    }

    /**
     * Плучение всех классов зодержащихся в пакете по его имени.
     * @param packageName имя пакета.
     * @return List содержащий все классы пакета.
     */
    static List<Class<?>> getClasses(String packageName)  {

        List<Class<?>> classes = new ArrayList<>();
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

        File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class"));

        for (File file : files) {
            String className = file.getName().replaceAll(".class$", "");
            try {
                classes.add(Class.forName(packageName + "." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

}