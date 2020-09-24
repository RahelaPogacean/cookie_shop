package model;

import javax.persistence.*;

@Entity
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column
    private String adminUsername = "Rahe";

    @Column
    private String adminPassword;

    public Admin() {
    }

    public Admin(int id, String adminUsername, String adminPassword) {

        this.adminId = id;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminUsername='" + adminUsername + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}
