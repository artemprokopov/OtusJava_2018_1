package ru.otus.hebertask.dbservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.hebertask.EmtyDataSet;
import ru.otus.hebertask.PhoneDataSet;
import ru.otus.hebertask.UserDataSet;
import ru.otus.hebertask.dao.UserDataSetDAO;

import java.util.List;
import java.util.function.Function;
/**
 * Класс сохраниения объекта типа  {@link UserDataSet} в базе данных с помощью Hibernate,
 * реализует интерфейс {@link DBService}.
 * @author v.chibrikov, Artem Prokopov
 * @since 05/05/2018
 * @version 1.0
 */
public class DBServiceImplHibernate implements DBService {
    /**
     * Поле хранит созданную фабрику сессий Hibernate.
     */
    private final SessionFactory sessionFactory;

    /**
     * Конструктор, создает сессию Hibernate, с базой данных MySQL.
     */
    public DBServiceImplHibernate() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(EmtyDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/otus");
        configuration.setProperty("hibernate.connection.username", "otus");
        configuration.setProperty("hibernate.connection.password", "12345");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    /**
     * Конструктор. Создает объект с фабрикой сессий хранящийся в поле {@link DBServiceImplHibernate#sessionFactory}
     * на основании переданной конфигурации.
     * @param configuration сонфигурация фабрикисессий Hibernate.
     */
    public DBServiceImplHibernate(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    /**
     * Метод создает фабрику сессий Hibernate на основании конфигурации.
     * @param configuration заданная конфигурация.
     * @return созданная фабрика сессий.
     */
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Возвращает статус текущей операции фабрики сессий Hibrnate.
     * @return значение текущего статуса {@link org.hibernate.resource.transaction.spi.TransactionStatus}.
     */
    public String getLocalStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    /**
     * Сохраняет объект типа {@link UserDataSet} в базе данных, с использованием {@link UserDataSetDAO}.
     * @param dataSet сохраняемый объект.
     */
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(dataSet);
        }
    }

    /**
     * Читает объект типа {@link UserDataSet} по уникальному идентификационному номеру id,
     * с использованием {@link UserDataSetDAO}.
     * @param id уникальный идентификационный номер объекта в базе данных.
     * @return прочитанный из базы данных объект типа {@link UserDataSet}.
     */
    public UserDataSet read(long id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
    }
    /**
     * Читает запись из баззы данных объект типа {@link UserDataSet} по имени пользователя,
     * с использованием {@link UserDataSetDAO}.
     * @param name имя пользователя.
     * @return объект типа {@link UserDataSet}.
     */
    public UserDataSet readByName(String name) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readByName(name);
        });
    }

    /**
     * Читает с помощью сесии Hibernate все записанные объекты в базе данных типа {@link UserDataSet},
     * с использованием {@link UserDataSetDAO}.
     * @return лист {@literal List<{@link UserDataSet}>} всех записанных в базу данных
     *      объектов типа {@link UserDataSet}.
     */
    public List<UserDataSet> readAll() {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readAll();
        });
    }
    /**
     * Закрывает сессию по тениюи сохранинию объектов типа {@link UserDataSet} из базы данных.
     */
    public void shutdown() {
        sessionFactory.close();
    }

    /**
     * Метод запускает операции в созданной сессии Hibernate.
     * @param function инициализиремая операция.
     * @param <R> параметрический результат.
     * @return результат выполнения операции в открытой сесии Hibernate.
     */
    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
