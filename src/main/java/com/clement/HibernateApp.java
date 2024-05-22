package com.clement;

import com.clement.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 */
public class HibernateApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Product product = new Product();
            product.setId(1L);
            product.setName("Drink");
            em.getTransaction().commit();
        }

    }
}
