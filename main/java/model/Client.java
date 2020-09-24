package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

    @Entity
    @Table
    public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column
    private String clientName;

    @Column
    private String clientPassword;

    @Column
    private String clientEmail;

    @Column
    private Double clientAmmount;

    @OneToMany
    private List<Cookie> favouriteCookies;

    public Client(String clientName, String clientPassword) {
        this.clientName = clientName;
        this.clientPassword = clientPassword;
    }

    public Client(int i, String clientName, String clientPassword) {
        this.clientId = i;
        this.clientName = clientName;
        this.clientPassword = clientPassword;
    }

    public List<Cookie> getFavouriteCookies() {
    return favouriteCookies;
}

    public void setFavouriteCookies(List<Cookie> favouriteCookies) {
        this.favouriteCookies = favouriteCookies;
    }


    public Client() {
    }

    public Client(String clientName, String clientPassword, String clientEmail, Double clientAmmount){
        this.clientName = clientName;
        this.clientPassword = clientPassword;
        this.clientEmail = clientEmail;
        this.clientAmmount = clientAmmount;
    }

    public Client(int clientId, String clientName, String clientPass, String clientEmail, Double clientAmm, List<Cookie> favouriteCookies) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPassword = clientPass;
        this.clientEmail = clientEmail;
        this.clientAmmount = clientAmm;
    }

    public Client(int clientId, String clientName, String clientPass, String clientEmail, Double clientAmm) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPassword = clientPass;
        this.clientEmail = clientEmail;
        this.clientAmmount = clientAmm;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Double getClientAmmount() {
        return clientAmmount;
    }

    public void setClientAmmount(Double clientAmmount) {
        this.clientAmmount = clientAmmount;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientAmmount=" + clientAmmount +
                '}';
    }

    public String update(String cookieType){

        return clientName + "Your favourite cookie " + cookieType + "is back on stock!";

    }
}

