package ru.otus.hebertask;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Класс данных, хранит адрес пользователя.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {
    /**
     * Поле хранит адрес пользователя.
     */
    @Column(name = "address")
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
        AddressDataSet that = (AddressDataSet) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }
}
