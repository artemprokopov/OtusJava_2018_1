package ru.otus.l021;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.util.Objects;
/**
 * VM options -Xmx1024m -Xms1024m.
 * jconsole, connect to pid
 */
public class Main {

    public static void main(String[] args) {
        printPid();
        getSizeObjectCompareAndPrintResult(new Object());
        getSizeObjectCompareAndPrintResult(new String());
        getSizeObjectCompareAndPrintResult(new Object[0]);
        getSizeObjectCompareAndPrintResult(new Object[1]);
        getSizeObjectCompareAndPrintResult(new Object[5]);
        getSizeObjectCompareAndPrintResult(new Object[8]);
    }

    /**
     * Метод принимает объект размер которого необходимо вычислить, вычисляет его двумя способами и
     * выводит в консоль результаты. Данный вариант работает для объекта Object(), String("")(пустая строка),
     * массив произвольного размера не более 8.
     * @param object объект размер которого необходимо выислить.
     */
    public static void getSizeObjectCompareAndPrintResult(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("The argument could not be processed!");
        }
        long sizeObjectRuntime = 0;
        long sizeObjectSizeCalculator = 0;
        try {
            sizeObjectRuntime = watchSizeMemoryObjectWithRuntime(object);
            sizeObjectSizeCalculator = watchSizeMemoryObject(object);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Size object of " + object.getClass()
                + " equals to " + sizeObjectRuntime + " byte with use Runtime");
        System.out.println("Size object of " + object.getClass()
                + " equals to " + sizeObjectSizeCalculator + " byte with use ObjectSizeCalculator");
        if (sizeObjectRuntime != sizeObjectSizeCalculator) {
            System.out.println("The result obtained using Runtime and ObjectSize are NOT equal");
        } else {
            System.out.println("The result obtained using Runtime and ObjectSize are equal");
        }
    }

    /**
     * Вычисляем размер объекта с помощью пакта {@link ObjectSizeCalculator}
     * и метода {@link ObjectSizeCalculator#getObjectSize(Object)}.
     * @param object объект размер которого необходимо выислить.
     * @return размер объекта в байтах.
     */
    private static long watchSizeMemoryObject(Object object)  {
        if (Objects.isNull(object)) {
            return -1;
        }
        return ObjectSizeCalculator.getObjectSize(object);
    }

    /**
     * Вычисляем размер объекта с помощью замера объема памяти занятого приложением, с заполнением
     * массив размаром 15_000_000 элементов, оценкой размеров занимаемой памяти приложением после
     * заполнения массива элементами размер которого необходимо определить. Полученная разница
     * в занимаемой памяти делится на количество элементов массива.
     * так как для данного варианта вычисления размера требуется создать
     * большое количество элементов в массиве, что ведет к большому расходу памяти, для данног варианта
     * выполнения данного метода
     * @param object объект размер которого необходимо выислить.
     * @return размер элемента в байтах, в случае не успеха в проведенной операции возвращает
     * (в данный момент времени при передаче методу null) -1.
     * @throws InterruptedException возможен выброс исключения при запуске GC {@link Main#runGC}.
     */
    private static long  watchSizeMemoryObjectWithRuntime(Object object) throws InterruptedException {
        if (Objects.isNull(object)) {
            return -1;
        }
        int size = 15_000_000;
        Object[] array = new Object[size];
        runGC();
        long startSizeMemory = getSizeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = GetObject.getNeededTypeOfObject(object);
        }
        long finishSizeMemory = getSizeMemory();
        return Math.round((double) (finishSizeMemory - startSizeMemory) / size);
    }

    /**
     * Выводим в консоль pid процесса.
     */
    private static void printPid() {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
    }

    /**
     * Внутренний класс для генерации объектов для заполнения массива.
     */
    private static  class GetObject {

        private static final char[] EMPTY_CHAR_ARRAY = new char[0];

        static Object getNeededTypeOfObject(Object neededTypeOfObject) {
            if (neededTypeOfObject.getClass() == String.class) {
                return  new String(EMPTY_CHAR_ARRAY);
            }
            if (neededTypeOfObject instanceof Object[]) {
                return  Array.newInstance(((Object[]) neededTypeOfObject).getClass(),
                        ((Object[]) neededTypeOfObject).length);
            }
            return  new Object();
        }
    }

    /**
     * Запускаем сборщик мусора.
     * @throws InterruptedException.
     */
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
