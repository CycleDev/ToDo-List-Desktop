package nao.cycledev.todolist.desk.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.*;

public class DBUtil {

	private static SessionFactory sessionFactory;

	private static SessionFactory configureSessionFactory()	throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
            return sessionFactory ;
        } else {
            return configureSessionFactory();
        }
	}
}
