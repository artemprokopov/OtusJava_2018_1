package ru.otus.hebertask;

import java.util.Arrays;
import java.util.List;

/**
 * Класс данных, хранит данные пользователя.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class UserDataSet extends DataSet {
    /**
     * Поле хранит имяользователя.
     */
    private String name;
    /**
     * Поле хранит возраст пользователя.
     */
    private int age;
    /**
     * Поле хранит адрес пользователя.
     */
    private AddressDataSet address;
    /**
     * Лист телефонов пользователя.
     */
    private List<PhoneDataSet> phones;

    /**
     * Контсруктор, нужен Hibernate.
     */
    public UserDataSet() {
    }

    /**
     * Конструктор.
     * @param name имя пользователя.
     * @param age возраст пользователя.
     * @param address адрес пользователя.
     * @param phones телефоны пользователя.
     */
    public UserDataSet(String name, int age, AddressDataSet address, PhoneDataSet... phones) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = Arrays.asList(phones);
    }

    /**
     * Геттер, возвращает имя пользователя.
     * @return имя пользователя.
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер, устанавливает имяпользователя.
     * @param name имя пользователя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер возвращает возрас пользователя.
     * @return возраст пользователя.
     */
    public int getAge() {
        return age;
    }

    /**
     * Сеттер устанавливате возраст пользователя.
     * @param age возраст пользователя.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Геттер, возвращает адрес пользователя.
     * @return адрес пользователя.
     */
    public AddressDataSet getAddress() {
        return address;
    }

    /**
     * Сеттер, устанавливает адрес пользователя.
     * @param address адрес пользоваетля.
     */
    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    /**
     * Геттер возвращает лист телефоноф пользователя.
     * @return {@literal List<{@link PhoneDataSet}>} телефонов пользовател.
     */
    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    /**
     * Сеттер, устанавливает телефоны пользоваетля.
     * @param phones список телефонов пользователя телефонов пользовател.
     */
    public void setPhones(PhoneDataSet... phones) {
        this.phones = Arrays.asList(phones);
    }
}
