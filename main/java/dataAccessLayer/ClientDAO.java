package dataAccessLayer;

import java.util.ArrayList;
import model.Client;

import javax.persistence.*;


public class ClientDAO implements ClientDAOInterface{

    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cookie_shop");

    public ClientDAO() {

    }

    public ArrayList<Client> getClientsList(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Client e");
        return (ArrayList<Client>) query.getResultList();
    }


    public Client getClientById(int clientId){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, clientId);
        entityManager.getTransaction().commit();
        return client;

    }

    public void insertClient(Client client) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateClient(Client client) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        client = entityManager.merge(client);
        entityManager.getTransaction().commit();
    }

    public void deleteClient(Client client) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
        entityManager.getTransaction().commit();
    }

//    public void deleteFromFavourites(Client client, Cookie cookie) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        //entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
//
//        entityManager.getTransaction().commit();
//    }


    public Client getClientByName(String clientName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Client> q = entityManager.createQuery("SELECT b FROM Client b WHERE b.clientName = :clientName", Client.class);
        q.setParameter("clientName", clientName);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }

    public Client getClientByPassword(String clientPassword) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Client> q = entityManager.createQuery("SELECT b FROM Client b WHERE b.clientPassword = :clientPassword", Client.class);
        q.setParameter("clientPassword", clientPassword);

        entityManager.getTransaction().commit();

        return q.getSingleResult();
    }

}
