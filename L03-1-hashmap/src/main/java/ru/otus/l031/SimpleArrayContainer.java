package ru.otus.l021;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

import static java.util.Spliterator.ORDERED;

/**
 * Класс контейнера основанного на массиве, реализует интерфейс {@link SimpleContainer} и {@link Iterable}.
 * @param <E> тип контейнера.
 * @author Artem Prokopov
 * @since 21/11/2017
 * @version 1.0
 */

public class SimpleArrayContainer<E> implements SimpleContainer<E> {
    /**
     * Смещение максимального размера массива относительно {@link Integer#MAX_VALUE}.
     */
    private static final int ARRAY_INDEX_MAX_VALUE_OFFSET = 5;
    /**
     * Максимальный размер массива принимается как {@link Integer#MAX_VALUE} - {@link SimpleArrayContainer#ARRAY_INDEX_MAX_VALUE_OFFSET}.
     */
    private static final int ARRAY_MAX_SIZE = Integer.MAX_VALUE - ARRAY_INDEX_MAX_VALUE_OFFSET;
    /**
     * Размер которым инициализируется массив {@link SimpleArrayContainer#array} по умолчанию.
     */
    private static final int ARRAY_INIT_SIZE = 10;
    /**
     * Хранилище элементов контейнера.
     */
    private Object[] array;
    /**
     * Номер последнего элемента в контейнере, при пустом контейнере равен -1.
     */
    private int currentItem = -1;


    /**
     * Конструктор по умолчанию, вызывает конструктор с параметром {@link SimpleArrayContainer#SimpleArrayContainer(int)}
     * с значением {@link SimpleArrayContainer#ARRAY_INIT_SIZE}.
     */
    public SimpleArrayContainer() {
        this(ARRAY_INIT_SIZE);
    }

    /**
     * Конструктор с параметром инициализации размера массива {@link SimpleArrayContainer#array}.
     * @param initSize параметр инициализации размера массива {@link SimpleArrayContainer#array}.
     */
    public SimpleArrayContainer(int initSize) {
        this.array = new Object[initSize];
    }

    /**
     * Конструктор принимет в качестве параметра массив типа T, копия которого используется как элементы
     * контейнера.
     * @param initArray массив иницыализирующий контейнер.
     */
    public SimpleArrayContainer(E[] initArray) {
        this.array = Arrays.copyOf(initArray, initArray.length);
        this.currentItem = array.length - 1;
    }

    /**
     * Метод добавления элемента в контейнер.
     * Добавляет элемент в хвост массива {@link SimpleArrayContainer#array}
     * @param addItem добавляемый в контейнер элемент тип E.
     * @return возвращает true если операция выполняется успешно.
     */
    public boolean add(E addItem) {
        checkAddSizeArray();
        this.array[++currentItem] = addItem;
        return true;
    }

    /**
     * Добавляет элемент в контейнер по индексу, хвост массива сдвигаетс в право.
     * @param indexAddItem индекс элемента вставки.
     * @param addItem      элемент вставки.
     */
    @SuppressWarnings("unused")
    public void add(int indexAddItem, E addItem) {
        checkIndex(indexAddItem);
        checkAddSizeArray();
        copyTailArrayWhenAddItem(indexAddItem);
        this.currentItem++;
        array[indexAddItem] = addItem;
    }

    /**
     * Заменяет элемент в контейнере.
     * @param indexUpdateItem индекс заменяемого элемента.
     * @param itemUpdate обновляемый элемент.
     * @return если операция добавления завершилась успешно возвращает true.
     */
    public boolean update(int indexUpdateItem, E itemUpdate) {
        checkIndex(indexUpdateItem);
        this.array[indexUpdateItem] = itemUpdate;
        return true;
    }

    /**
     * Удаляет элемент по индексу.
     * @param indexDeleteItem индекс удаляемого элемента.
     * @return в слуучае успеха возвращает удаленный элемент.
     */
    @SuppressWarnings("unchecked")
    public E delete(int indexDeleteItem) {
        checkIndex(indexDeleteItem);
        E oldValue = (E) array[indexDeleteItem];
        copyTailArrayWhenDeleteItem(indexDeleteItem);
        this.currentItem--;
        return oldValue;
    }

    /**
     * Удаляет первый найденный с начала контейнера элемент.
     * @param deleteItem Удаляемый элемент.
     * @return в случае успеха возвращает удалённый элемент, в противном случае если такого элемента нет null.
     */
    @SuppressWarnings("unchecked")
    public E delete(E deleteItem) {
        E oldValue = null;
        int indexDeleteItem = findItem(deleteItem);
        if (indexDeleteItem >= 0) {
            oldValue = (E) array[indexDeleteItem];
            copyTailArrayWhenDeleteItem(indexDeleteItem);
            this.currentItem--;
        }
        return oldValue;
    }

    /**
     * Возвращает элемент контейнера по индексу.
     * @param indexItem индекс элемента.
     * @return элемент контейнера.
     */
    @SuppressWarnings("unchecked")
    public E get(int indexItem) {
        checkIndex(indexItem);
        return (E) array[indexItem];
    }

    /**
     * Проверка есть ли элементы в контейнере.
     * @return true если контейнер не содержит ни одного элемента.
     */
    public boolean isEmpty() {
        return this.currentItem < 0;
    }

    /**
     * Уменьшает размер массива {@link SimpleArrayContainer#array}
     * до размера {@link SimpleArrayContainer#currentItem} + 1.
     */
    public void trim() {
        this.array = Arrays.copyOf(this.array, currentItem + 1);
    }

    /**
     * Виртуально число размера массива(общее число элементов размещённых в контейнере).
     * @return число элементов в контейнере.
     */
    public int size() {
        return this.currentItem + 1;
    }

    /**
     * Поиск элемента в контейнере.
     * @param searchItem искомый элемент.
     * @return индекс найденного элемента, в противном случае если элемент не найден < 0.
     */
    public int findItem(E searchItem) {
        int result = -1;
        for (int i = 0; i <= currentItem; i++) {
            if (array[i].equals(searchItem)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает все элементы контейнера как массив типа E.
     * @param resultArray массив определяющий тип возвращаемого массива.
     * @param <E> Тип возвращаемого массива.
     * @return массив содержащий все элементы контейнера.
     */
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] resultArray) {
        if (resultArray.length < currentItem + 1) {
            return (E[]) Arrays.copyOf(array, currentItem + 1, resultArray.getClass());
        }
        System.arraycopy(array, 0, resultArray, 0, currentItem + 1);
        if (resultArray.length > currentItem + 1) {
            resultArray[currentItem + 1] = null;
        }
        return resultArray;
    }

    /**
     * Проверка индекса на принадлежность диапазону 0 <= i <= {@link SimpleArrayContainer#currentItem}.
     * @param checkIndex проверяемый индекс.
     */
    private void checkIndex(int checkIndex) {
        if (checkIndex > currentItem || checkIndex < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(checkIndex));
        }
    }

    /**
     * Проверка на то что размер массива {@link SimpleArrayContainer#array} позволяет провести добавление элемента
     * если не позволяет, массив расширяется.
     */
    private void checkAddSizeArray() {
        if (currentItem + 1 >= array.length) {
            int size = checkMaxSizeArray();
            array = Arrays.copyOf(array, size);
        }
    }

    /**
     * Проверка на то что размер массива {@link SimpleArrayContainer#array} не выходит за максимально возможный размер
     * {@link SimpleArrayContainer#ARRAY_MAX_SIZE}, если не выходит возвращает новый возможный размер массива.
     * Если увеличение размера не возможно, то генерирует исключение {@link OutOfMemoryError}
     * @return новый допустимый размер массива.
     */
    private int checkMaxSizeArray() {
        if (ARRAY_MAX_SIZE - (currentItem + 1) == 0) {
            throw new OutOfMemoryError("The array index is greater than the maximum possible values");
        }
        int newSize = array.length * 2;
        return ARRAY_MAX_SIZE - array.length > array.length ? newSize : ARRAY_MAX_SIZE;
    }

    /**
     * Копирует хвост массива начиная indexDeleteItem + 1 на один элемент влево.
     * @param indexDeleteItem индекс удаляемого элемента.
     */
    private void copyTailArrayWhenDeleteItem(int indexDeleteItem) {
        System.arraycopy(array, indexDeleteItem + 1,
                array, indexDeleteItem, currentItem - indexDeleteItem);
    }

    /**
     * Копирует хвост массива начиная indexAddItem на один элемент вправо, освобождает элемент для добавления нового.
     * @param indexAddItem индекс добавляемого элемента.
     */
    private void copyTailArrayWhenAddItem(int indexAddItem) {
        System.arraycopy(array, indexAddItem,
                array, indexAddItem + 1, currentItem + 1 - indexAddItem + 1);
    }

    /**
     * Метод формирует сообщение для генерируемых исключений в методе {@link SimpleArrayContainer#checkIndex(int)}.
     * @param index индекс для формирования строки сообщения.
     * @return сформированную строку.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + (currentItem + 1);
    }

    /**
     * Переопределяем метод equals.
     * @param o объект с которым сравниваетм.
     * @return true если объекты равны, в противном случае false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArrayContainer<?> that = (SimpleArrayContainer<?>) o;
        return currentItem == that.currentItem
                && Arrays.equals(array, that.array);
    }

    /**
     * Переопределяем метод hashCode.
     * @return новый сгенерированный hashCode объекта.
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(array);
        result = 31 * result + currentItem;
        return result;
    }

    /*
     * Имплементируем методы интерфейса {@link Iterable}.
     */
    /**
     * Реализуем метод интерфейса {@link Iterable#iterator()}.
     * @return итератор типа <E>.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentPosition = -1;
            @Override
            public boolean hasNext() {
                return currentPosition + 1 <= currentItem;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element! End of SimpleArrayContainer!");
                }
                return (E) array[++currentPosition];
            }
        };
    }

    /**
     * Переопределяем метод {@link Iterable#forEach(Consumer)}.
     * @param action действие реализующее функциональным интерфейсом {@link Consumer}.
     */
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        for (Object t :array) {
            action.accept((E) t);
        }
    }

    /**
     * Переопределяем {@link Iterable#spliterator()}.
     * @return Spliterator<E>.
     */
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(array, 0, currentItem + 1, ORDERED);
    }
}

