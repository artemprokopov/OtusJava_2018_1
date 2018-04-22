package taskjdbc;

import java.util.Objects;

public abstract class DataSet {
    private final long id;

    public DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return id == dataSet.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}