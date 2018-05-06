package ru.otus.hebertask;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс данных, хранит данные пользователя.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {
    /**
     * Поле хранит имяользователя.
     */
    @Column(name = "name")
    private String name;
    /**
     * Поле хранит возраст пользователя.
     */
    @Column(name = "age")
    private int age;
    /**
     * Поле хранит адрес пользователя.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressDataSet address;
    /**
     * Лист телефонов пользователя.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userDataSet_id",
            nullable = false
    )
    @OrderColumn(
            name = "phone_position",
            nullable = false
    )
    private Set<PhoneDataSet> phones;

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
     * @param userPhones телефоны пользователя.
     */
    public UserDataSet(String name, int age, AddressDataSet address, PhoneDataSet... userPhones) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = new HashSet<>(Arrays.asList(userPhones));
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
    public Set<PhoneDataSet> getPhones() {
        return phones;
    }

    /**
     * Сеттер, устанавливает телефоны пользоваетля.
     * @param phones список телефонов пользователя телефонов пользовател.
     */
    public void setPhones(PhoneDataSet... phones) {
        this.phones = new HashSet<>(Arrays.asList(phones));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserDataSet that = (UserDataSet) o;
        return age == that.age
                && Objects.equals(name, that.name)
                && Objects.equals(address, that.address)
                && Objects.equals(phones, that.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, age, address, phones);
    }
}
