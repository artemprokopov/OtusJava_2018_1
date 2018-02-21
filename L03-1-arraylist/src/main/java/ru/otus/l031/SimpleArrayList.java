package ru.otus.l031;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Класс контейнера основанного на массиве, реализует интерфейс {@link List} и {@link Iterable}.
 * @param <E> тип контейнера.
 * @author Artem Prokopov
 * @since 21/11/2017
 * @version 1.0
 */

public class SimpleArrayList<E> implements List<E> {
    /**
     * Смещение максимального размера массива относительно {@link Integer#MAX_VALUE}.
     */
    private static final int ARRAY_INDEX_MAX_VALUE_OFFSET = 5;
    /**
     * Максимальный размер массива принимается как {@link Integer#MAX_VALUE} - {@link SimpleArrayList#ARRAY_INDEX_MAX_VALUE_OFFSET}.
     */
    private static final int ARRAY_MAX_SIZE = Integer.MAX_VALUE - ARRAY_INDEX_MAX_VALUE_OFFSET;
    /**
     * Размер которым инициализируется массив {@link SimpleArrayList#array} по умолчанию.
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
     * Конструктор по умолчанию, вызывает конструктор с параметром {@link SimpleArrayList#SimpleArrayList(int)}
     * с значением {@link SimpleArrayList#ARRAY_INIT_SIZE}.
     */
    public SimpleArrayList() {
        this(ARRAY_INIT_SIZE);
    }

    /**
     * Конструктор с параметром инициализации размера массива {@link SimpleArrayList#array}.
     * @param initSize параметр инициализации размера массива {@link SimpleArrayList#array}.
     */
    public SimpleArrayList(int initSize) {
        this.array = new Object[initSize];
    }

    /**
     * Конструктор принимет в качестве параметра массив типа T, копия которого используется как элементы
     * контейнера.
     * @param initArray массив иницыализирующий контейнер.
     */
    public SimpleArrayList(E[] initArray) {
        this.array = Arrays.copyOf(initArray, initArray.length);
        this.currentItem = array.length - 1;
    }

    /**
     * Метод добавления элемента в контейнер.
     * Добавляет элемент в хвост массива {@link SimpleArrayList#array}
     * @param addItem добавляемый в контейнер элемент тип E.
     * @return возвращает true если операция выполняется успешно.
     */
    public boolean add(E addItem) {
        checkAddSizeArray();
        this.array[++currentItem] = addItem;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Очищаем контейнер, вместо текущего создает новый пустой.
     */
    @Override
    public void clear() {
        this.array = new Object[ARRAY_INIT_SIZE];
        this.currentItem = -1;
    }

    /**
     * Добавляет элемент в контейнер по индексу, хвост массива сдвигаетс в право.
     * @param indexAddItem индекс элемента вставки.
     * @param addItem      элемент вставки.
     */
    @SuppressWarnings("unused")
    @Override
    public void add(int indexAddItem, E addItem) {
        checkIndex(indexAddItem);
        checkAddSizeArray();
        copyTailArrayWhenAddItem(indexAddItem);
        this.currentItem++;
        array[indexAddItem] = addItem;
    }



    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
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
    @Override
    public E remove(int indexDeleteItem) {
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
    @Override
    public boolean remove(Object deleteItem) {
        int indexDeleteItem = findItem(deleteItem);
        if (indexDeleteItem >= 0) {
            copyTailArrayWhenDeleteItem(indexDeleteItem);
            this.currentItem--;
        }
        return true;
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

    @Override
    public E set(int index, E element) {
        return null;
    }

    /**
     * Проверка есть ли элементы в контейнере.
     * @return true если контейнер не содержит ни одного элемента.
     */
    @Override
    public boolean isEmpty() {
        return this.currentItem < 0;
    }

    @Override
    public boolean contains(Object o) {
        return findItem(o) != -1;
    }

    /**
     * Уменьшает размер массива {@link SimpleArrayList#array}
     * до размера {@link SimpleArrayList#currentItem} + 1.
     */
    public void trim() {
        this.array = Arrays.copyOf(this.array, currentItem + 1);
    }

    /**
     * Виртуально число размера массива(общее число элементов размещённых в контейнере).
     * @return число элементов в контейнере.
     */
    @Override
    public int size() {
        return this.currentItem + 1;
    }

    /**
     * Поиск элемента в контейнере.
     * @param searchItem искомый элемент.
     * @return индекс найденного элемента, в противном случае если элемент не найден < 0.
     */
    public int findItem(Object searchItem) {
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
    @Override
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
     * Проверка индекса на принадлежность диапазону 0 <= i <= {@link SimpleArrayList#currentItem}.
     * @param checkIndex проверяемый индекс.
     */
    private void checkIndex(int checkIndex) {
        if (checkIndex > currentItem || checkIndex < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(checkIndex));
        }
    }

    /**
     * Проверка на то что размер массива {@link SimpleArrayList#array} позволяет провести добавление элемента
     * если не позволяет, массив расширяется.
     */
    private void checkAddSizeArray() {
        if (currentItem + 1 >= array.length) {
            int size = checkMaxSizeArray();
            array = Arrays.copyOf(array, size);
        }
    }

    /**
     * Проверка на то что размер массива {@link SimpleArrayList#array} не выходит за максимально возможный размер
     * {@link SimpleArrayList#ARRAY_MAX_SIZE}, если не выходит возвращает новый возможный размер массива.
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
     * Метод формирует сообщение для генерируемых исключений в методе {@link SimpleArrayList#checkIndex(int)}.
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
        SimpleArrayList<?> that = (SimpleArrayList<?>) o;
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
                    throw new NoSuchElementException("No next element! End of SimpleArrayList!");
                }
                return (E) array[++currentPosition];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}

