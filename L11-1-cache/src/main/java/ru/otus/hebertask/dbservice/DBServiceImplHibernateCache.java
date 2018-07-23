package ru.otus.hebertask.dbservice;

import ru.otus.hebertask.UserDataSet;
import ru.otus.mycache.ICache;

import java.util.List;

public class DBServiceImplHibernateCache implements DBService {

    private final DBService dbService;
    private final ICache<Long, UserDataSet> cacheID;
    private final ICache<String, UserDataSet> cacheName;

    public DBServiceImplHibernateCache(DBService dbService,
                                       ICache<Long, UserDataSet> cacheForIdKey,
                                       ICache<String, UserDataSet> cacheForNameKey) {
        this.dbService = dbService;
        this.cacheID = cacheForIdKey;
        this.cacheName = cacheForNameKey;
    }

    @Override
    public String getLocalStatus() {
        return dbService.getLocalStatus();
    }

    @Override
    public void save(UserDataSet dataSet) {
        dbService.save(dataSet);
        cacheName.put(dataSet.getName(), dataSet);
        cacheID.put(dataSet.getId(), dataSet);
    }

    @Override
    public UserDataSet read(long id) {
        UserDataSet result = cacheID.get(id);
        if (result != null) {
            return result;
        }
        return dbService.read(id);
    }

    @Override
    public UserDataSet readByName(String name) {
        UserDataSet result = cacheName.get(name);
        if (result != null) {
            return result;
        }
        UserDataSet resultReadByName = dbService.readByName(name);
        if (resultReadByName != null) {
            cacheName.put(resultReadByName.getName(), resultReadByName);
        }
        return resultReadByName;
    }

    @Override
    public List<UserDataSet> readAll() {
        return dbService.readAll();
    }

    @Override
    public void shutdown() {
        dbService.shutdown();
    }
}
