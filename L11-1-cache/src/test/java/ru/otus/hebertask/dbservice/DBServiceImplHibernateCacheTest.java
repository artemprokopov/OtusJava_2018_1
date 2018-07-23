package ru.otus.hebertask.dbservice;

import org.junit.Test;

import ru.otus.hebertask.AddressDataSet;
import ru.otus.hebertask.PhoneDataSet;
import ru.otus.hebertask.UserDataSet;
import ru.otus.mycache.CacheImpl;
import ru.otus.mycache.ICache;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
/**
 * Тесты класса {@link DBServiceImplHibernate}.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class DBServiceImplHibernateCacheTest {
    /**
     * Поле сохраняемого и загружаемого тестового класса, типа {@link UserDataSet}.
     */
    private UserDataSet userDataSet1;
    {
        AddressDataSet addressDataSet = new AddressDataSet("Prage");
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("+3(222)333-33-33");
        PhoneDataSet phoneDataSet2 = new PhoneDataSet("+4(777)444-44-44");
        userDataSet1 = new UserDataSet("Karton", 22, addressDataSet, phoneDataSet1, phoneDataSet2);
    }
    /**
     * Поле сохраняемого и загружаемого тестового класса, типа {@link UserDataSet}.
     */
    private UserDataSet userDataSet2;
    {
        AddressDataSet addressDataSet = new AddressDataSet("Minsk");
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("+3(66)33-33-33");
        PhoneDataSet phoneDataSet2 = new PhoneDataSet("+4(77)444-77-44");
        userDataSet2 = new UserDataSet("Banton", 55, addressDataSet, phoneDataSet1, phoneDataSet2);
    }
    /**
     * Поле сохраняемого и загружаемого тестового класса, типа {@link UserDataSet}.
     */
    private UserDataSet userDataSet3;
    {
        AddressDataSet addressDataSet = new AddressDataSet("London");
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("+3(55)33-77-33");
        PhoneDataSet phoneDataSet2 = new PhoneDataSet("+4(333)444-77-44");
        userDataSet3 = new UserDataSet("Vanton", 77, addressDataSet, phoneDataSet1, phoneDataSet2);
    }
    /**
     * Поле сохраняемого и загружаемого тестового класса, типа {@link DBServiceImplHibernate}.
     */
    private DBServiceImplHibernate dbServiceImplHibernate = new DBServiceImplHibernate();

    /**
     * Тест метода {@link DBServiceImplHibernateCache}.
     * Работаем с кэшем.
     */
    @Test
    public void saveAndReadByIdCache() {
        ICache<Long, UserDataSet> cacheId = new CacheImpl<>(2, 100000, 100000);
        ICache<String, UserDataSet> cacheName = new CacheImpl<>(2, 100000, 100000);
        DBService dbService = new DBServiceImplHibernateCache(dbServiceImplHibernate, cacheId, cacheName);
        dbService.save(userDataSet1);
        dbService.save(userDataSet2);
        dbService.save(userDataSet3);
        UserDataSet result1 = dbService.read(1);
        UserDataSet result2 = dbService.read(2);
        UserDataSet result3 = dbService.read(3);
        assertEquals(result1, userDataSet1);
        assertEquals(result2, userDataSet2);
        assertEquals(result3, userDataSet3);
        assertEquals(2, cacheId.getHitCount());
        assertEquals(1, cacheId.getMissCount());
    }
    /**
     * Тест метода {@link DBServiceImplHibernateCache}.
     * Работаем с кэшем.
     */
    @Test
    public void saveAndReadByIdCacheTimerMore1000ms() {
        ICache<Long, UserDataSet> cacheId = new CacheImpl<>(2, 1000, 1000);
        ICache<String, UserDataSet> cacheName = new CacheImpl<>(2, 1000, 1000);
        DBService dbService = new DBServiceImplHibernateCache(dbServiceImplHibernate, cacheId, cacheName);
        dbService.save(userDataSet1);
        dbService.save(userDataSet2);
        dbService.save(userDataSet3);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserDataSet result1 = dbService.read(1);
        UserDataSet result2 = dbService.read(2);
        UserDataSet result3 = dbService.read(3);
        assertEquals(result1, userDataSet1);
        assertEquals(result2, userDataSet2);
        assertEquals(result3, userDataSet3);
        assertEquals(0, cacheId.getHitCount());
        assertEquals(3, cacheId.getMissCount());
    }
    /**
     * Тест метода {@link DBServiceImplHibernateCache}.
     * Работаем с кэшем.
     */
    @Test
    public void saveAndReadByNameCache() {
        ICache<Long, UserDataSet> cacheId = new CacheImpl<>(4, 10000, 10000);
        ICache<String, UserDataSet> cacheName = new CacheImpl<>(4, 10000, 10000);
        DBService dbService = new DBServiceImplHibernateCache(dbServiceImplHibernate, cacheId, cacheName);
        dbService.save(userDataSet1);
        dbService.save(userDataSet2);
        dbService.save(userDataSet3);
        UserDataSet result1 = dbService.readByName("Karton");
        UserDataSet result2 = dbService.readByName("Banton");
        UserDataSet result3 = dbService.readByName("Vanton");
        UserDataSet result4 = dbService.readByName("PPPPP");
        assertEquals(result1, userDataSet1);
        assertEquals(result2, userDataSet2);
        assertEquals(result3, userDataSet3);
        assertEquals(result4, null);
        assertEquals(3, cacheName.getHitCount());
        assertEquals(1, cacheName.getMissCount());
    }
}