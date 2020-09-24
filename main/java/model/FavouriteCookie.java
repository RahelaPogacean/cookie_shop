package model;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    @Table( name = "favouritecookie" )
    public class FavouriteCookie implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String favouriteType;
        private String clientName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFavouriteType() {
            return favouriteType;
        }

        public void setFavouriteType(String favouriteType) {
            this.favouriteType = favouriteType;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public FavouriteCookie() {

       }

        public FavouriteCookie(String favouriteType, String clientName) {
            this.favouriteType = favouriteType;
            this.clientName = clientName;
        }

        @Override
        public String toString() {
            return "FavouriteCookie{" +
                    "id=" + id +
                    ", favouriteType='" + favouriteType + '\'' +
                    ", clientName='" + clientName + '\'' +
                    '}';
        }
    }
