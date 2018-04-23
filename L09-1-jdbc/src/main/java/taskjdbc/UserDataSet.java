package taskjdbc;

import java.util.Objects;

/**
 * Класс хранени данных пользовател.
 */
public class UserDataSet extends DataSet {
    /**
     * Поле возраста пользователя.
     */
    private final int age;
    /**
     * Имя пользователя.
     */
    private final String name;

    /**
     * Конструктор.
     * @param id параметр уникального идентификационного номера пользователя.
     * @param name имя пользвателя.
     * @param age возраст пользователья.
     */
    public UserDataSet(long id, String name,  int age) {
        super(id);
        assert (age > 0);
        this.age = age;
        this.name = name;
    }

    /**
     * Геттер для поля {@link UserDataSet#age}.
     * @return возраст пользователя.
     */
    public int getAge() {
        return age;
    }

    /**
     * Геттер для поля {@link UserDataSet#name}.
     * @return имя пользователя.
     */
    public String getName() {
        return name;
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
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), age, name);
    }
}
