package ru.otus.hebertask.dbservice;

import org.junit.Ignore;
import org.junit.Test;

import ru.otus.hebertask.AddressDataSet;
import ru.otus.hebertask.PhoneDataSet;
import ru.otus.hebertask.UserDataSet;

import static org.junit.Assert.assertEquals;
/**
 * Тесты класса {@link DBServiceImplHibernate}.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
@Ignore
public class DBServiceImplHibernateTest {
    /**
     * Поле сохраняемого и загружаемого тестового класса, типа {@link UserDataSet}
     */
    private UserDataSet userDataSet;
    {
        AddressDataSet addressDataSet = new AddressDataSet("Moscow");
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("+3(333)333-33-33");
        PhoneDataSet phoneDataSet2 = new PhoneDataSet("+4(444)444-44-44");
        userDataSet = new UserDataSet("Anton", 33, addressDataSet, phoneDataSet1, phoneDataSet2);
    }

    /**
     * Тест метода {@link DBServiceImplHibernate#save(UserDataSet)}.
     */
    @Test
    public void saveAndReadById() {
        DBServiceImplHibernate dbServiceImplHibernate = new DBServiceImplHibernate();
        dbServiceImplHibernate.save(userDataSet);
        UserDataSet result = dbServiceImplHibernate.read(1);
        userDataSet.setId(result.getId());
        assertEquals(result, userDataSet);
    }


}