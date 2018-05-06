package ru.otus.hebertask;



import javax.persistence.Column;
import javax.persistence.Entity;
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
public class PhoneDataSet extends DataSet {
    /**
     * Поле хранит телефонный номер.
     */
    @Column(name = "number")
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
}
