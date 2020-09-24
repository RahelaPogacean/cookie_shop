package businessLayer.service;

import dataAccessLayer.AdminDAOImpl;
import model.Admin;

import java.util.List;

public class AdminHandler {

    private AdminDAOImpl adminDAO = new AdminDAOImpl();

    public AdminHandler(){

    }
    public void createAdmin(Admin admin) {
        adminDAO.createAdmin(admin);
    }

    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminDAO.deleteAdmin(admin);
    }

    public List<Admin> viewAllAdmins() {
        return adminDAO.viewAllAdmins();
    }


    public boolean searchByUsernameAndPassword(String username, String password) {
        return adminDAO.searchByUsernameAndPassword(username,password);
    }

    public Admin searchAdmin(String username, String password) {
        return adminDAO.searchByUsername(username,password);
    }

    public AdminDAOImpl getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAOImpl adminDAO) {
        this.adminDAO = adminDAO;
    }
}

