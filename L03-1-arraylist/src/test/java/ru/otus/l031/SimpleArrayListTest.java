package ru.otus.l031;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;



/**
 * Тестовый класс для {@link SimpleArrayList}.
 * @author Artem Prokopov
 * @since 21/11/2017
 * @version 1.0
 */

public class SimpleArrayListTest {
    /**
     * Массив для тестов методов {@link SimpleArrayList#add(Object)}.
     */
    private static Integer[] testArray = {1, 2, 3, 4, 5};
    /**
     * Массив результат для тесто метода  {@link SimpleArrayList#add(int, Object)}.
     */
    private static Integer[] resultArray1 = {1, 2, 6, 3, 4, 5};

    /**
     * Массив результат для тесто метода  {@link SimpleArrayList#update(int, Object)}.
     */
    private static Integer[] resultArrayForUpdate = {1, 2, 6, 4, 5};

    /**
     * Массив результат для тесто метода remove {@link SimpleArrayList#remove(Object)}.
     */
    private static Integer[] resultArrayForremove = {1, 2, 4, 5};

    /**
     * Массив результат для тесто метода remove2  {@link SimpleArrayList#remove(Object)}.
     */
    private static Integer[] resultArrayForremoveFirstElement = {2, 3, 4, 5};

    /**
     * Массив результат для тесто метода remove3  {@link SimpleArrayList#remove(Object)}.
     */
    private static Integer[] resultArrayForremoveLastElement = {1, 2, 3, 4};

    /**
     * Тестируем метод {@link SimpleArrayList#add(Object)}.
     */
    @Test
    public void add() {
        SimpleArrayList<Integer> integerSimpleArrayListForAdd = new SimpleArrayList<>();
        for (Integer i : testArray) {
            integerSimpleArrayListForAdd.add(i);
        }
        assertEquals(integerSimpleArrayListForAdd.size(), testArray.length);
        Integer[] resultArray = integerSimpleArrayListForAdd.toArray(new Integer[integerSimpleArrayListForAdd.size()]);
        assertArrayEquals(resultArray, testArray);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#add(int, Object)}.
     */
    @Test
    public void add1() {
        SimpleArrayList<Integer> testSimpleArrayListForAdd = new SimpleArrayList<>();
        for (Integer i : testArray) {
            testSimpleArrayListForAdd.add(i);
        }
        assertEquals(testSimpleArrayListForAdd.size(), testArray.length);
        testSimpleArrayListForAdd.add(2, 6);
        assertArrayEquals(testSimpleArrayListForAdd.toArray(new Integer[testSimpleArrayListForAdd.size()]),
                resultArray1);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#add(Object)}.
     * Добавляем больше элементов чем максимальное количество элементов в контейнере,
     * ожидаем исключение OutOfMemoryError.
     */
    @Ignore
    @Test(expected = OutOfMemoryError.class)
    public void add2() {
        Integer[] initArray = new Integer[Integer.MAX_VALUE - 6];
        Arrays.fill(initArray, 1);
        SimpleArrayList<Integer> testSimpleArrayListForAdd1 = new SimpleArrayList<>(initArray);
        for (int i = 0; i < 6; i++) {
            testSimpleArrayListForAdd1.add(1);
        }
    }

    /**
     * Тест метода {@link SimpleArrayList#update(int, Object)}.
     */
    @Test
    public void update() {
        SimpleArrayList<Integer> testSimpleArrayListForUpdate = new SimpleArrayList<>(testArray);
        testSimpleArrayListForUpdate.update(2, 6);
        assertArrayEquals(testSimpleArrayListForUpdate.toArray(new Integer[testSimpleArrayListForUpdate.size()]),
                resultArrayForUpdate);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#remove(Object)}
     * Удаляем элемент 3.
     */
    @Test
    public void remove() {
        SimpleArrayList<Integer> testSimpleArrayListForremove = new SimpleArrayList<>(testArray);
        testSimpleArrayListForremove.remove(new Integer(3));
        assertArrayEquals(testSimpleArrayListForremove.toArray(new Integer[testSimpleArrayListForremove.size()]),
                resultArrayForremove);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#remove(Object)}
     * Передаем несуществующий в массиве объект.
     */
    @Test
    public void remove1() {
        SimpleArrayList<Integer> testSimpleArrayListForremove = new SimpleArrayList<>(testArray);
        Object result = testSimpleArrayListForremove.remove(new Integer(6));
        assertNull(result);
        assertArrayEquals(testSimpleArrayListForremove.toArray(new Integer[testSimpleArrayListForremove.size()]),
                testArray);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#remove(Object)}
     * Тестируем удаление первого элемента.
     */
    @Test
    public void remove2() {
        SimpleArrayList<Integer> testSimpleArrayListForremove = new SimpleArrayList<>(testArray);
        testSimpleArrayListForremove.remove(new Integer(1));
        assertArrayEquals(testSimpleArrayListForremove.toArray(new Integer[testSimpleArrayListForremove.size()]),
                resultArrayForremoveFirstElement);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#remove(Object)}
     * Тестируем удаление последнего элемента.
     */
    @Test
    public void remove3() {
        SimpleArrayList<Integer> testSimpleArrayListForremove = new SimpleArrayList<>(testArray);
        testSimpleArrayListForremove.remove(new Integer(5));
        assertArrayEquals(testSimpleArrayListForremove.toArray(new Integer[testSimpleArrayListForremove.size()]),
                resultArrayForremoveLastElement);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#get(int)}
     * Тестируем удаление последнего элемента.
     */
    @Test
    public void remove4() {
        SimpleArrayList<Integer> testSimpleArrayListForremove = new SimpleArrayList<>(testArray);
        testSimpleArrayListForremove.remove(2);
        assertArrayEquals(testSimpleArrayListForremove.toArray(new Integer[testSimpleArrayListForremove.size()]),
                resultArrayForremove);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#get(int)}.
     * Запрашиваем второй элемент из {@link SimpleArrayListTest#testArray}.
     */
    @Test
    public void get() {
        SimpleArrayList<Integer> testSimpleArrayListForGet = new SimpleArrayList<>(testArray);
        Integer result = testSimpleArrayListForGet.get(2);
        assertEquals(result, new Integer(3));
        assertArrayEquals(testSimpleArrayListForGet.toArray(new Integer[testSimpleArrayListForGet.size()]),
                testArray);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#get(int)}.
     * Запрашиваем элемент -1, ожидаем исключение IndexOutOfBoundsException.
     */
    @Test
            (expected = IndexOutOfBoundsException.class)
    public void get1() {
        SimpleArrayList<Integer> testSimpleArrayListForGet = new SimpleArrayList<>(testArray);
        testSimpleArrayListForGet.get(-1);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#get(int)}.
     * Запрашиваем элемент больше текщего размера, ожидаем исключение IndexOutOfBoundsException.
     */
    @Test
            (expected = IndexOutOfBoundsException.class)
    public void get2() {
        SimpleArrayList<Integer> testSimpleArrayListForGet = new SimpleArrayList<>(testArray);
        testSimpleArrayListForGet.get(testSimpleArrayListForGet.size() + 1);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#get(int)}.
     * Запрашиваем элемент при пустом массиве, ожидаем исключение IndexOutOfBoundsException.
     */
    @Test
            (expected = IndexOutOfBoundsException.class)
    public void get3() {
        SimpleArrayList<Integer> testSimpleArrayListForGet = new SimpleArrayList<>();
        testSimpleArrayListForGet.get(1);
    }
    /**
     * Тестируем метод {@link SimpleArrayList#isEmpty()}.
     *
     */
    @Test
    public void isEmpty() {
        SimpleArrayList<Integer> testSimpleArrayListForIsEmpty = new SimpleArrayList<>();
        assertTrue(testSimpleArrayListForIsEmpty.isEmpty());
        for (Integer i : testArray) {
            testSimpleArrayListForIsEmpty.add(i);
        }
        assertEquals(testSimpleArrayListForIsEmpty.size(), testArray.length);
        int k = testSimpleArrayListForIsEmpty.size();
        for (int i = 0; i < k; i++) {
            testSimpleArrayListForIsEmpty.remove(0);
        }
        assertTrue(testSimpleArrayListForIsEmpty.isEmpty());
    }

    /**
     *Тестируем метод {@link SimpleArrayList#size()}.
     */
    @Test
    public void size() {
        SimpleArrayList<Integer> testSimpleArrayListForSize = new SimpleArrayList<>(testArray);
        assertEquals(testSimpleArrayListForSize.size(), testArray.length);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#findItem(Object)}.
     */
    @Test
    public void findItem() {
        SimpleArrayList<Integer> testSimpleArrayListForFindItem = new SimpleArrayList<>(testArray);
        int result = testSimpleArrayListForFindItem.findItem(3);
        assertEquals(result, 2);
    }

    /**
     * Тестируем метод {@link SimpleArrayList#findItem(Object)}.
     * Передаем не существующий объект.
     */
    @Test
    public void findItem1() {
        SimpleArrayList<Integer> testSimpleArrayListForFindItem = new SimpleArrayList<>(testArray);
        int result = testSimpleArrayListForFindItem.findItem(10);
        assertTrue(result < 0);
    }

    /**
     * Тестируем переопределенный метод {@link SimpleArrayList#equals(Object)}.
     */
    @Test
    public void equals() {
        SimpleArrayList<Integer> testSimpleArrayListForEquals1 = new SimpleArrayList<>(testArray);
        SimpleArrayList<Integer> testSimpleArrayListForEquals2 = new SimpleArrayList<>(testArray);
        assertTrue(testSimpleArrayListForEquals1.equals(testSimpleArrayListForEquals2));
    }
    /**
     * Тестируем переопределенный метод {@link SimpleArrayList#equals(Object)}.
     */
    @Test
    public void equals1() {
        SimpleArrayList<Integer> testSimpleArrayListForEquals1 = new SimpleArrayList<>(testArray);
        SimpleArrayList<Integer> testSimpleArrayListForEquals2 = new SimpleArrayList<>(resultArray1);
        assertEquals(testSimpleArrayListForEquals2.size(), resultArray1.length);
        assertFalse(testSimpleArrayListForEquals1.equals(testSimpleArrayListForEquals2));
    }

    /**
     * Тестируем переопределенный метод {@link SimpleArrayList#hashCode()}.
     * Тестируем на соответствие спецификации, если hashCode не равны, то и объекты не равны,
     * если объекты равны, то hashCode равны.
     */
    @Test
    public void hashCodeTest() {
        SimpleArrayList<Integer> testSimpleArrayListForEquals1 = new SimpleArrayList<>(testArray);
        assertEquals(testSimpleArrayListForEquals1.size(), testArray.length);

        SimpleArrayList<Integer> testSimpleArrayListForEquals3 = new SimpleArrayList<>(testArray);
        assertEquals(testSimpleArrayListForEquals3.size(), testArray.length);

        SimpleArrayList<Integer> testSimpleArrayListForEquals2 = new SimpleArrayList<>(resultArray1);
        assertEquals(testSimpleArrayListForEquals2.size(), resultArray1.length);
        //Если hashCode не равны
        assertFalse(testSimpleArrayListForEquals1.hashCode() == testSimpleArrayListForEquals2.hashCode());
        //то объекты не равны
        assertFalse(testSimpleArrayListForEquals1.equals(testSimpleArrayListForEquals2));
        //Если объекты равны
        assertTrue(testSimpleArrayListForEquals1.equals(testSimpleArrayListForEquals3));
        //то hashCode равны
        assertTrue(testSimpleArrayListForEquals1.hashCode() == testSimpleArrayListForEquals3.hashCode());

    }

    /**
     * Тестируем метод {@link SimpleArrayList#iterator()}.
     */
    @Test
            (expected = NoSuchElementException.class)
    public void iterator() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>(testArray);
        Iterator<Integer> iterator = integerSimpleArrayList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), testArray[0]);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), testArray[1]);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), testArray[2]);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), testArray[3]);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), testArray[4]);
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    /**
     * Тестируем метод {@link SimpleArrayList#forEach}.
     */
    @Test
    public void forEach() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>(testArray);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        integerSimpleArrayList.forEach(System.out::println);
        printStream.close();
        System.setOut(null);
        String result = String.format("1%1$s2%1$s3%1$s4%1$s5%1$s", System.lineSeparator());
        assertEquals(result, byteArrayOutputStream.toString());
    }

    /**
     * Тестируем метод {@link SimpleArrayList#spliterator()}.
     */
    @Test
    public void spliterator1() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>(testArray);
        Spliterator<Integer> spliterator = integerSimpleArrayList.spliterator();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        spliterator.forEachRemaining(System.out::print);
        printStream.close();
        System.setOut(null);
        String result = "12345";
        assertEquals(result, byteArrayOutputStream.toString());
    }
}