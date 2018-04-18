package taskjdbc;

public class UserDataSet extends DataSet {
    private int age;
    private String name;

    public UserDataSet(long id, int age, String name) {
        super(id);
        this.age = age;
        this.name = name;
    }
}
