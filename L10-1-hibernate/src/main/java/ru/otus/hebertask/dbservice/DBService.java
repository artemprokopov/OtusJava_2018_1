package ru.otus.hebertask.dbservice;

import ru.otus.hebertask.UserDataSet;

import java.util.List;

/**
 * Интерфейс получения данных UserDataSet из базы данных.
 * @author Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public interface DBService {
    /**
     * Возвращает состояние транзакции.
     * @return строку состояния транзакции.
     */
    String getLocalStatus();

    /**
     * Сохраняет объект {@link UserDataSet} в базеданных.
     * @param dataSet сохраняемый объект.
     */
    void save(UserDataSet dataSet);

    /**
     * Читает объект типа {@link UserDataSet} по уникальному идентификационному номеру id.
     * @param id уникальный идентификационный номер объекта в базе данных.
     * @return прочитанный из базы данных объект типа {@link UserDataSet}.
     */
    UserDataSet read(long id);

    /**
     * Читает запись из баззы данных объект типа {@link UserDataSet} по имени пользователя.
     * @param name имя пользователя.
     * @return объект типа {@link UserDataSet}.
     */
    UserDataSet readByName(String name);

    /**
     * Читает все записанные объекты типа {@link UserDataSet} из базы данных.
     * @return {@literal List<{@link UserDataSet}>} лист всех объектов типа {@link UserDataSet} находящихся в базе данных.
     */
    List<UserDataSet> readAll();

    /**
     * Закрывает сессию по тениюи сохранинию объектов типа {@link UserDataSet} из базы данных.
     */
    void shutdown();

}
