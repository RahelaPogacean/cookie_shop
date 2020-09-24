package dataAccessLayer;


import model.Admin;
import model.Client;
import model.Comment;
import model.Cookie;

import javax.persistence.*;
import java.util.ArrayList;

public class CommentDAO  {

    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cookie_shop");

    public CommentDAO (){

    }


    public Comment getCommentByCookieType(String type) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Comment> q = entityManager.createQuery("SELECT b FROM Comment b WHERE b.typeCookie = :type", Comment.class);
        q.setParameter("type", type);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }

    public Comment getCommentByClient(String clientName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Comment> q = entityManager.createQuery("SELECT b FROM Comment b WHERE b.clientName = :clientName", Comment.class);
        q.setParameter("clientName", clientName);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }

    public void addComment(Comment comment){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateComment(Comment comment) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        comment = entityManager.merge(comment);
        entityManager.getTransaction().commit();
    }

    public ArrayList<Comment> getCommentsList() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Comment e");
        return (ArrayList<Comment>) query.getResultList();
    }

    public Comment getCommentById(int id){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.getTransaction().commit();
        return comment;
    }
}
