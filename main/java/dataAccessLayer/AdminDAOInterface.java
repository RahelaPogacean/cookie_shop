package dataAccessLayer;

import model.Admin;

public interface AdminDAOInterface {

    public Admin getAdminById(int adminId);
    public Admin getAdminByName(String adminUsername);
    public Admin getAdminByPassword(String adminPassword);
}
