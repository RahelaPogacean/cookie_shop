package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;

@Entity
@Table

public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shoppingId;

    @Column
    private Date shoppingDate;

    @ManyToOne
    private Client client;

    private HashMap<String, Integer> myCookies;//hash map (type cookie, cantitate)

    public ShoppingCart() {
    }

    public ShoppingCart(Client clientId, Date thisDate, HashMap<String, Integer> myCookies) {
        this.client = clientId;
        this.shoppingDate = thisDate;
        this.myCookies = myCookies;
    }


    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Date getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashMap<String, Integer> getMyCookies() {
        return myCookies;
    }

    public void setMyCookies(HashMap<String, Integer> myCookies) {
        this.myCookies = myCookies;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingId=" + shoppingId +
                ", shoppingDate=" + shoppingDate +
                ", client=" + client +
                '}';
    }
}


