package com.clement;

import com.clement.entity.Product;
import com.clement.entity.inheritance.Book;
import com.clement.entity.inheritance.Electronic;
import com.clement.entity.oneToMany.Comment;
import com.clement.entity.oneToMany.Post;
import com.clement.persistance.CustomPersistenceUnitInfo;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
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
//            product.setId(1L);
//            product.setName("Beer");
//            product.setPrice(20);
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

            //inheritance

//            Book b1 = new Book();
//            b1.setAuthor("clement");
//
//            Electronic e1 = new Electronic();
//            e1.setVoltage(20);
//
//            em.persist(b1);
//            em.persist(e1);

            //JPQL

            Post p = new Post();
            p.setTitle("test");
            p.setContent("Hello");

            Comment c = new Comment();
            c.setContents("hello");

            c.setPost(p);
            p.setComments(List.of(c));


            em.persist(p);
            em.persist(c);

//            String query = "SELECT p FROM Product p";
//            String query = "SELECT p FROM Product p WHERE p.name = 'Beer'";
//            String query = "SELECT p FROM Product p WHERE p.name =:name";

//            String query = "SELECT p,c FROM Post p INNER JOIN p.comments c";

//           Query q = em.createQuery("SELECT p FROM Product p");

//
//            TypedQuery<Object[]> q = em.createQuery(query, Object[].class);

//            q.setParameter("name", "Beer");

//            q.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1]));

            //criteria query
//            CriteriaBuilder builder = em.getCriteriaBuilder();
//            CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
//
//            Root<Comment> commentRoot = cq.from(Comment.class);

//            cq.select(commentRoot); //SELECT c FROM Comment c
//            cq.select(commentRoot.get("contents")); // SELECT c.content FROM Comment c
//            cq.multiselect(commentRoot.get("contents"), commentRoot.get("id"))
//                    .orderBy(builder.asc(commentRoot.get("id")));

//            TypedQuery<Comment> query = em.createQuery(cq);
//
//            TypedQuery<Object[]> query = em.createQuery(cq);
//
//            query.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1]));
//
            // Entity Graph
            EntityGraph<?> graph = em.createEntityGraph(Comment.class);
            graph.addAttributeNode("post"); //Node with which Comment have a relationship

            // you can use subGraph to also go down the graph

            em.createQuery("SELECT a FROM Comment a", Comment.class)
                    .setHint("jakarta.persistence.loadgraph", graph)
                    .getResultList().forEach(a -> System.out.println(a.getPost()));


            em.getTransaction().commit();
        }

    }
}
