package ru.otus.l021;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Main {
    private static char[] EMTY_CHAR_ARRAY = new char[0];
    public static void main(String[] args) throws InterruptedException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        printPid();
        Main main = new Main();
        long sizeObjectRuntime = main.watchSizeMemoryObjectWithRuntime(new Object());
        long sizeObjectSizeCalculator = main.watchSizeMemoryObject(new Object());
        System.out.println("Size object of " + Object.class
                + " equals to " + sizeObjectRuntime + " byte" + " with use Runtime");
        System.out.println("Size object of " + Object.class
                + " equals to " + sizeObjectSizeCalculator + " byte" + " with use ObjectSizeCalculator");
        sizeObjectRuntime = main.watchSizeMemoryObjectWithRuntime(new String());
        sizeObjectSizeCalculator = main.watchSizeMemoryObject(new String());
        System.out.println("Size object of " + String.class
                + " equals to " + sizeObjectRuntime + " byte" + " with use Runtime");
        System.out.println("Size object of " + String.class
                + " equals to " + sizeObjectSizeCalculator + " byte" + " with use Runtime");
    }

    public long watchSizeMemoryObject(Object object)  {
        if (Objects.isNull(object)) {
            return -1;
        }
        return ObjectSizeCalculator.getObjectSize(object);
    }

    public long  watchSizeMemoryObjectWithRuntime(Object object) throws InterruptedException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        if (Objects.isNull(object)) {
            return -1;
        }
        int size = 2_000_000;
        Object[] array = new Object[size];
        runGC();
        long startSizeMemory = getSizeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = GetObject.getNeededTypeOfObject(object);
        }
        long finishSizeMemory = getSizeMemory();
        long differencesizeMemory = finishSizeMemory- startSizeMemory;
        return differencesizeMemory / size;
    }

    private static void printPid() {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
    }

    private static class GetObject {

        public static <T> Object getNeededTypeOfObject(T neededTypeOfObject) {
            if (neededTypeOfObject.getClass() == String.class) {
                return new String(EMTY_CHAR_ARRAY);
            }
            if (neededTypeOfObject instanceof Object[]) {
                return ((Object[]) neededTypeOfObject).clone();
            }
            return new Object();
        }
    }

    private static void runGC() throws InterruptedException {
        System.gc();
        System.runFinalization();
        System.gc();
        Thread.sleep(100);
    }

    private static long getSizeMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
