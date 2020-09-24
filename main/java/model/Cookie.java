package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;

    @Entity
    @Table

public class Cookie extends Observable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cookieId;

    @Column
    private String type;

    @Column
    private Double price;

    @Column
    private Double quantityOfSweeteners;

    @Column
    private int stock;

    @Column
    private Double rating;

    @OneToMany
    private List<Comment> comments;//
    public Cookie(String type, Double price, Double quantityOfSweeteners, int stock){

        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
        this.stock = stock;
    }

    public Cookie(int cookieId, String type, Double price, Double quantityOfSweeteners, int stock){
        this.cookieId = cookieId;
        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
        this.stock = stock;
    }

    public Cookie(int cookieId, String type, Double price, Double quantityOfSweeteners, Double rating){
        this.cookieId = cookieId;
        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
        this.rating = rating;
    }


    public Cookie(int cookieId, String type, Double price, Double quantityOfSweeteners) {
        this.cookieId = cookieId;
        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
    }

    public Cookie(){

    }

    public Cookie(int cookieId, String type, Double price, Double quantityOfSweeteners, Double rating, int stock) {
        this.cookieId = cookieId;
        this.type = type;
        this.price = price;
        this.quantityOfSweeteners = quantityOfSweeteners;
        this.rating = rating;
        this.stock = stock;
    }

    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantityOfSweeteners() {
        return quantityOfSweeteners;
    }

    public void setQuantityOfSweeteners(Double quantityOfSweeteners) {
        this.quantityOfSweeteners = quantityOfSweeteners;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        @Override
    public String toString() {
        return "Cookie{" +
                "cookieId=" + cookieId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantityOfSweeteners=" + quantityOfSweeteners +
                ", stock=" + stock +
                ", rating=" + rating +
                '}';
    }

   public void cookieBack(){
        setChanged();
        notifyObservers();
   }
}
