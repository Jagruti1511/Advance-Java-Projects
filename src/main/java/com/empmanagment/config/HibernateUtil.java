package com.empmanagment.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.empmanagment.model.Employee;

public class HibernateUtil {

    private static SessionFactory sf;

    static {

        Configuration config = new Configuration();

        Properties props = new Properties();

        InputStream is = HibernateUtil.class
                .getClassLoader()
                .getResourceAsStream("hibernate.properties");

        try {
            if (is == null) {
                throw new RuntimeException("hibernate.properties file not found");
            }

            props.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        config.setProperties(props);

        config.addAnnotatedClass(Employee.class);

        sf = config.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
}