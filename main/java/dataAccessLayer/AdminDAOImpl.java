package dataAccessLayer;

import model.Admin;
import model.Cookie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.Server;

import javax.swing.*;
import java.util.List;

public class AdminDAOImpl {

    private SessionFactory sessionFactory;

    public AdminDAOImpl( ) {
        this.sessionFactory = Server.sessionFactory;
    }


    public void createAdmin(Admin admin) {
        this.sessionFactory.getCurrentSession().save(admin);
    }

    public void updateAdmin(Admin admin) {
        this.sessionFactory.getCurrentSession().update(admin);
    }

    public void deleteAdmin(Admin admin) {
        this.sessionFactory.getCurrentSession().delete(admin);
    }

    @SuppressWarnings("unchecked")
    public List<Admin> viewAllAdmins() {
        return this.sessionFactory.getCurrentSession().createQuery("from Admin ").list();
    }

    public boolean searchAdminByUsername(String username) {
        try(Session session = sessionFactory.openSession()) {
            List<Admin> admins = session.createQuery("from Admin where adminUsername = '"+username+"'", Admin.class).list();
            if(!admins.isEmpty()){
                return true;
            }
            else{
                JOptionPane.showConfirmDialog(null, "Nu s-a gasit adminul cu acest username!", "ERROR", JOptionPane.DEFAULT_OPTION);
                return false;
            }
        } }

    public boolean searchByUsernameAndPassword(String username, String password) {
        try(Session session = sessionFactory.openSession()) {
            List<Admin> admins = session.createQuery("from Admin where adminUsername = '"+username+"' and adminPassword = '"+password+"'", Admin.class).list();
            if(admins.size()>0){
                return  true;
            }
            else{
                return false;
            }
        }
    }

    public Admin searchByUsername(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            List<Admin> admins = session.createQuery("from Admin where adminUsername = '"+username+"' and adminPassword = '"+password+"'", Admin.class).list();
            if (admins.size() > 0) {
                return admins.get(0);
            }
            else
                return null;
        }
    }

}

