package ru.orus.l051;



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
 * Created by tully.
 */
@SuppressWarnings("SameParameterValue")
class ReflectionHelper {
    private ReflectionHelper() {
        throw new UnsupportedOperationException();
    }

    static Class<?> getClass(String nameClass) {
        Class<?> tClass = null;
        try {
            tClass = Class.forName(nameClass);
        } catch (ClassNotFoundException e) {
            System.out.println("Class for test not found!");
        }
        return tClass;
    }

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

    static List<Method> getMethod(Object object) {
        Method[] methods = null;
        try {
            methods = object.getClass().getMethods();
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return Arrays.asList(methods);
    }
    
    static HashMap<Class<?>,List<Method>> getAnotatedMethod(List<Method> methods, Class<?>... annotationClass) {
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

    static private Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    static List<Class<?>> findClasses(String packageName) throws ClassNotFoundException {

        List<Class<?>> classes = new ArrayList<>();
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

        File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class"));

        for (File file : files) {
            String className = file.getName().replaceAll(".class$", "");
                classes.add(Class.forName(packageName + "." + className));
        }
        return classes;
    }

}