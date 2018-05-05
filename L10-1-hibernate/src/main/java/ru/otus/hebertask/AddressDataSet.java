package ru.otus.hebertask;
/**
 * Класс данных, хранит адрес пользователя.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class AddressDataSet extends DataSet {
    /**
     * Поле хранит адрес пользователя.
     */
    private String address;

    /**
     * Конструктор, нужен Hibernate.
     */
    public AddressDataSet() {
    }

    /**
     * Конструктор, усанавливает адрес пользователя.
     * @param address адрес пользователя.
     */
    public AddressDataSet(String address) {
        this.address = address;
    }

    /**
     * Геттер возвращает адрес пользователя.
     * @return адресс пользователяю
     */
    public String getAddress() {
        return address;
    }

}
