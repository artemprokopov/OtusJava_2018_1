package ru.otus.hebertask.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.otus.hebertask.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
/**
 * Класс DAO для объекта типа {@link UserDataSet}.
 * @author v.chibrikov
 */
public class UserDataSetDAO {
    /**
     * Поле хранит сессию.
     */
    private Session session;

    /**
     * Конструктор создает объект с сессией Hibernate.
     * @param session сессия Hibernate.
     */
    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    /**
     * Выполняте сохранение объекта типа {@link UserDataSet} в базе данных в соответствии с переданной сессией Hibernate.
     * @param dataSet сохраняемый объект типа {@link UserDataSet}.
     */
    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    /**
     * Читает с помощью сесии Hibernate объект типа {@link UserDataSet} по уникальному идентификационному номеру id
     * из базы данных.
     * @param id уникальный идентификационный номерю
     * @return прочитанный объект типа {@link UserDataSet}
     */
    public UserDataSet read(long id) {
        return session.get(UserDataSet.class, id);
    }

    /**
     * Читает с помощью сесии Hibernate объект типа {@link UserDataSet} по имени пользователя.
     * @param name имя пользователя.
     * @return прочитанный объект типа {@link UserDataSet}
     */
    public UserDataSet readByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UserDataSet> query = session.createQuery(criteria);
        return query.uniqueResult();
    }

    /**
     * Читает с помощью сесии Hibernate все записанные объекты в базе данных типа {@link UserDataSet}.
     * @return лист {@literal List<{@link UserDataSet}>} всех записанных в базу данных
     *         объектов типа {@link UserDataSet}.
     */
    public List<UserDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }
}
