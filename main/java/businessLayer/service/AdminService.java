package businessLayer.service;

import dataAccessLayer.AdminDAO;
import model.Admin;

public class AdminService {

    public Admin findAdminById(int adminId) {
        AdminDAO adminDAO = new AdminDAO();
        Admin adminn = adminDAO.getAdminById(adminId);

        return adminn;
    }

    public Admin findAdminByName(String adminUsername) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.getAdminByName(adminUsername);

        return admin;
    }

    public Admin findAdminByPassword(String adminPassword) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.getAdminByName(adminPassword);

        return admin;
    }


}
