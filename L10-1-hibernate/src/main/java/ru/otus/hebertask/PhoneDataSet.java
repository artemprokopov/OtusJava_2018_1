package ru.otus.hebertask;
/**
 * Класс данных, хранит телефонный номер.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class PhoneDataSet extends DataSet {
    /**
     * Поле хранит телефонный номер.
     */
    private String number;

    /**
     * Конструктор потумолчанию, важно для Hibernate.
     */
    public PhoneDataSet() {
    }

    /**
     * Конструктор, устанавливает телефонный номер.
     * @param number телефонный номер.
     */
    public PhoneDataSet(String number) {
        this.number = number;
    }

    /**
     * Геттер, возвращает установелнный телефонный номер.
     * @return телефонный номер который хранит объект.
     */
    public String getNumber() {
        return number;
    }
}
