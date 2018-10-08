package br.com.leilao.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
//			Configuration configuration = new Configuration();
//			configuration.configure();
//
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.applySettings(configuration.getProperties()).build();
//			
//			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//			return sessionFactory;

			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			        .configure( "hibernate.cfg.xml" )
			        .build();

			        Metadata metadata = new MetadataSources( standardRegistry )
			        .getMetadataBuilder()
			        .build();

			        return metadata.getSessionFactoryBuilder().build();
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Falha ou tentar criar SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}