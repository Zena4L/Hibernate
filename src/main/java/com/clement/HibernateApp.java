package com.clement;

import com.clement.entity.CapitalCity;
import com.clement.entity.Country;
import com.clement.entity.Product;
import com.clement.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class HibernateApp {
    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        Map<String, String> props = new HashMap<>();

        // defining the properties
        props.put("hibernate.show_sql", "true"); //show only sqls
        props.put("hibernate.hbm2ddl.auto", "create"); //don't use create only use none in production //create , none , update


        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

//            Product product = new Product();
////            product.setId(1L);
//            product.setName("Beer");
//
//
////            em.find(Product.class, 1);
//
//            em.persist(product);  //add to the context -> Not an insert query

//            em.getReference(Product.class, 1);

//            CapitalCity capitalCity = new CapitalCity();
//            capitalCity.setName("Accra");
//
//            Country country = new Country();
//            country.setName("Ghana");
//            country.setCapitalCity(capitalCity);
//
//            capitalCity.setCountry(country);

            // the persisting order doesn't matter because persist != insert
//            em.persist(capitalCity);
//            em.persist(country);

            em.getTransaction().commit();
        }

    }
}
