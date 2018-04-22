package taskjdbc;

import java.util.Objects;

public class UserDataSet extends DataSet {
    private final int age;
    private final String name;

    public UserDataSet(long id, String name,  int age) {
        super(id);
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDataSet that = (UserDataSet) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), age, name);
    }
}
