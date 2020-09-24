package dataAccessLayer;

import model.Admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AdminDAO implements AdminDAOInterface {

    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cookie_shop");

    public AdminDAO (){

    }

    public Admin getAdminById(int adminId){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Admin admin = entityManager.find(Admin.class, adminId);
        entityManager.getTransaction().commit();
        return admin;
    }

    public Admin getAdminByName(String adminUsername) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Admin> q = entityManager.createQuery("SELECT b FROM Admin b WHERE b.adminUsername = :adminUsername", Admin.class);
        q.setParameter("adminUsername", adminUsername);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }

    public Admin getAdminByPassword(String adminPassword) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Admin> q = entityManager.createQuery("SELECT b FROM Admin b WHERE b.adminPassword = :adminPassword", Admin.class);
        q.setParameter("adminPassword", adminPassword);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }


}
