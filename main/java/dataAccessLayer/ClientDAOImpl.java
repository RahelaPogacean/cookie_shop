package dataAccessLayer;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.Server;

import java.util.List;

public class ClientDAOImpl {

    private SessionFactory sessionFactory;

    public ClientDAOImpl( ) {
        this.sessionFactory = Server.sessionFactory;
    }

    public boolean createUser(Client regularUser) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Client> regularUsers = session.createQuery("from Client where clientName = '"+regularUser.getClientName()+"'", Client.class).list();
            if(regularUsers.size() == 0 ){
                session.save(regularUser);
                session.getTransaction().commit();
                return true;
            }
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateUser(Client regularUser) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Client> regularUsers = session.createQuery("from Client where clientName = '"+regularUser.getClientName()+"'", Client.class).list();
            if(regularUsers.size()>0){
                regularUsers.get(0).setClientName(regularUser.getClientName());
                regularUsers.get(0).setClientPassword(regularUser.getClientPassword());
                regularUsers.get(0).setClientEmail(regularUser.getClientEmail());
                regularUsers.get(0).setClientAmmount(regularUser.getClientAmmount());

                session.update(regularUsers.get(0));
                session.getTransaction().commit();
                return true;
            }
            else{
                return false;
            }

        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteUser(Client regularUser) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Client> regularUsers = session.createQuery("from Client where clientName = '"+regularUser.getClientName()+"' and clientPassword= '"+regularUser.getClientPassword()+"'", Client.class).list();
            if(regularUsers.size()>0){
                session.delete(regularUsers.get(0));
                session.getTransaction().commit();
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            return  false;
        }
    }

    public List<Client> viewAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            List<Client> regularUsers = session.createQuery("from Client ", Client.class).list();

            for (Client regularUser : regularUsers) {
                System.out.println(">> Regular user: " + regularUser.getClientName());
            }
            return regularUsers;
        }
    }

    public Client searchByUsername(String username) {
        try(Session session = sessionFactory.openSession()) {
            List<Client> regularUsers = session.createQuery("from Client where clientName = '"+username+"'", Client.class).list();
            if(regularUsers.size()>0){
                return  regularUsers.get(0);
            }
            else{
                return null;
            }
        }
    }


    public boolean searchByUsernameAndPassword(String username, String password) {
        try(Session session = sessionFactory.openSession()) {
            List<Client> regularUsers = session.createQuery("from Client where clientName = '"+username+"' and clientPassword = '"+password+"'", Client.class).list();
            if(regularUsers.size()>0){
                return  true;
            }
            else{
                return false;
            }
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
