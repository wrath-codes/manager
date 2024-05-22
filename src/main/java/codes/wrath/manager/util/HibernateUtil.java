package codes.wrath.manager.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = createSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        try {
            // cria uma fábrica de sessões a partir do hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();

            ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                    .build();

            // faz o build da fábrica de sessões
            SessionFactory factory = configuration.buildSessionFactory(registry);

            // retorna a fábrica de sessões
            return factory;

        } catch (Throwable ex) {
            System.out.println("A fábrica de sessões não pode ser criada. Erro: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
