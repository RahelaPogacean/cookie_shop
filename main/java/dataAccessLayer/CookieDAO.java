package dataAccessLayer;

import model.Cookie;

import javax.persistence.*;
import java.util.ArrayList;

public class CookieDAO implements CookieDAOInterface{

    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cookie_shop");

    public CookieDAO(){

    }

    public void insertCookie(Cookie cookie){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cookie);//comment for tests !!!!!!!!!!!!!!!!!!!!!!

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Cookie  getCookieById(int id){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Cookie cookie = entityManager.find(Cookie.class, id);
        entityManager.getTransaction().commit();
        return cookie;
    }

    public Cookie getCookieByType(String cookieType){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Cookie> q = entityManager.createQuery("SELECT b FROM Cookie b WHERE b.type = :cookieType", Cookie.class);
        q.setParameter("cookieType", cookieType);
        entityManager.getTransaction().commit();
        return q.getSingleResult();
    }


    public Cookie  getCookieByPrice(Double price){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Cookie> q = entityManager.createQuery("SELECT b FROM Cookie b WHERE b.price = :price", Cookie.class);
        q.setParameter("price", price);
        entityManager.getTransaction().commit();
        return q.getSingleResult();
    }

    public Cookie  getCookieBySweeteners(Double sweets){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Cookie> q = entityManager.createQuery("SELECT b FROM Cookie b WHERE b.quantityOfSweeteners = :sweets", Cookie.class);
        q.setParameter("sweets", sweets);
        entityManager.getTransaction().commit();
        return q.getSingleResult();
    }

    public Cookie  getCookieByRating(Double rating){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Cookie> q = entityManager.createQuery("SELECT b FROM Cookie b WHERE b.rating = :rating", Cookie.class);
        q.setParameter("rating", rating);
        entityManager.getTransaction().commit();
        return q.getSingleResult();
    }

    public ArrayList<Cookie> getCookiesList() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Cookie e");
        return (ArrayList<Cookie>) query.getResultList();
    }

    public Cookie updateCookie(Cookie cookie) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        cookie = entityManager.merge(cookie);

        entityManager.getTransaction().commit();
        return cookie;
    }

    public void deleteCookie(Cookie cookie) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(cookie) ? cookie : entityManager.merge(cookie));
        entityManager.getTransaction().commit();
    }

}
