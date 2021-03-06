package ru.webprak.Utils;

import ru.webprak.Models.Books;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Orders;
import ru.webprak.Models.Instances;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Books.class);
                configuration.addAnnotatedClass(Customers.class);
                configuration.addAnnotatedClass(Orders.class);
                configuration.addAnnotatedClass(Instances.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return sessionFactory;
    }
}
