package ru.otus.hebertask;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mUserDataSet", fetch = FetchType.EAGER)
    private AddressDataSet userAddress;
    /**
     * Лист телефонов пользователя.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ElementCollection(targetClass = PhoneDataSet.class)
    private List<PhoneDataSet> userPhones;

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
        this.userAddress = address;
        this.userPhones = Arrays.asList(phones);
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
    public AddressDataSet getUserAddress() {
        return userAddress;
    }

    /**
     * Сеттер, устанавливает адрес пользователя.
     * @param userAddress адрес пользоваетля.
     */
    public void setUserAddress(AddressDataSet userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * Геттер возвращает лист телефоноф пользователя.
     * @return {@literal List<{@link PhoneDataSet}>} телефонов пользовател.
     */
    public List<PhoneDataSet> getUserPhones() {
        return userPhones;
    }

    /**
     * Сеттер, устанавливает телефоны пользоваетля.
     * @param userPhones список телефонов пользователя телефонов пользовател.
     */
    public void setUserPhones(PhoneDataSet... userPhones) {
        this.userPhones = Arrays.asList(userPhones);
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
                && Objects.equals(userAddress, that.userAddress)
                && Objects.equals(userPhones, that.userPhones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, age, userAddress, userPhones);
    }
}
