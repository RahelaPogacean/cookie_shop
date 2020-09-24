package dataAccessLayer;

import model.Comment;
import model.ShoppingCart;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;


public class ShoppingCartDAO implements ShoppingCartDAOInterface {

    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cookie_shop");

    public ShoppingCartDAO()  {
    }

    public void updateShopping(ShoppingCart shoppingCart) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        shoppingCart = entityManager.merge(shoppingCart);
        entityManager.getTransaction().commit();
    }

    public void insertOrder(ShoppingCart shoppingCart) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(shoppingCart);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public ArrayList<ShoppingCart> getShoppingList(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM ShoppingCart e");
        return (ArrayList<ShoppingCart>) query.getResultList();

    }

    public ShoppingCart getShoppingByDate(Date shoppingDate) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<ShoppingCart> q = entityManager.createQuery("SELECT b FROM ShoppingCart b WHERE b.shoppingDate = :shoppingDate", ShoppingCart.class);
        q.setParameter("shoppingDate", shoppingDate);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }
}
