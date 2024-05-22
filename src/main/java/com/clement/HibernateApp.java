package com.clement;

import com.clement.entity.Product;
import com.clement.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

/**
 * Hello world!
 */
public class HibernateApp {
    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Product product = new Product();
            product.setId(2L);
            product.setName("Beer");

            em.persist(product);  //add to the context -> Not an insert query

            em.getTransaction().commit();
        }

    }
}
