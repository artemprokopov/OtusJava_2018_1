package ru.otus.l021;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws InterruptedException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        Main main = new Main();
        main.printPid();
        main.watchSizeMemoryObject(new Object());
        main.watchSizeMemoryObjectWithRuntime(Object.class, 0);
        main.watchSizeMemoryObject(new String(""));
        main.watchSizeMemoryObjectWithRuntime(String.class, 0);
        main.watchSizeMemoryObject(new int[1]);
        main.watchSizeMemoryObjectWithRuntime(int[].class, 0);
    }

    private void watchSizeMemoryObject(Object object)  {
        if (Objects.isNull(object)) {
            return;
        }
        System.out.println("Size object of "
                + object.getClass() + " equals to "
                + ObjectSizeCalculator.getObjectSize(object) + " byte"
                + " with use ObjectSizeCalculator");
    }

    private void  watchSizeMemoryObjectWithRuntime(Class cl, int arraySize) throws InterruptedException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {

        long sizeObject = 0;
        if (cl == String.class) {
            sizeObject = getSizeStringObject(cl);
        } else if (cl == int[].class) {
            sizeObject = getSizeArrayObject(cl, arraySize);
        } else {
            sizeObject = getSizeSimpleObject(cl);
        }
        System.out.println("Size object of "
                + cl + " equals to "
                + sizeObject + " byte"
                + " with use Runtime");

    }

    private void printPid() {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
    }

    private long getSizeSimpleObject(Class cl) throws InterruptedException,
            IllegalAccessException,
            InstantiationException {
        int size = 2_000_000;
        Thread.sleep(100);
        Runtime runtime = Runtime.getRuntime();
        Object[] array = new Object[size];
        long startMemorySize = runtime.maxMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = cl.newInstance();
        }
        long finishMemorySize = runtime.maxMemory() - runtime.freeMemory();
        return  (finishMemorySize - startMemorySize) / size;
    }

    private long getSizeArrayObject(Class cl, int size) throws InterruptedException {
        Thread.sleep(100);
        Runtime runtime = Runtime.getRuntime();
        long startMemorySize = runtime.maxMemory() - runtime.freeMemory();
        Object newArray = Array.newInstance(cl, size);
        long finishMemorySize = runtime.maxMemory() - runtime.freeMemory();
        return  finishMemorySize - startMemorySize;
    }

    private long getSizeStringObject(Class cl) throws InterruptedException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        int size = 8;
        Thread.sleep(100);
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        long startMemorySize = runtime.maxMemory() - runtime.freeMemory();
        String st1 = new String(new char[0]);
        String st2 = new String(new char[0]);
        String st3 = new String(new char[0]);
        String st4 = new String(new char[0]);
        String st5 = new String(new char[0]);
        String st6 = new String(new char[0]);
        String st7 = new String(new char[0]);
        String st8 = new String(new char[0]);
        long finishMemorySize = runtime.maxMemory() - runtime.freeMemory();
        long diferentMemorySize = finishMemorySize - startMemorySize;
        long result = Math.round(((double) diferentMemorySize )/ size);
        return  result;
    }
}
