package ru.otus.hebertask;



import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;


/**
 * Класс данных, хранит телефонный номер.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet implements Comparable<PhoneDataSet> {
    /**
     * Поле хранит телефонный номер.
     */
    @Column(name = "number")
    private String number;
    /**
     * Отношение ManyToOne.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDataSet_id", updatable = false, insertable = false)
    @NotNull
    private UserDataSet userPhones;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneDataSet that = (PhoneDataSet) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(PhoneDataSet o) {
        return number.compareTo(o.number);
    }
}
