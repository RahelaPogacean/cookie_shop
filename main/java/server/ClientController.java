package server;

import businessLayer.service.CookieHandler;
import businessLayer.service.FavouriteHandler;
import dataAccessLayer.CookieDAOImpl;
import dataAccessLayer.FavouriteDAO;
import model.FavouriteCookie;

import java.util.List;

public class ClientController {

        private CookieDAOImpl cookieDAO;
        private CookieHandler cookieHandler;
        private FavouriteHandler favouriteHandler;
        private FavouriteDAO favouriteDAO;

        public ClientController( ) {
            this.cookieDAO = new CookieDAOImpl();
            this.cookieHandler = new CookieHandler();
            this.favouriteDAO = new FavouriteDAO();
            this.favouriteHandler = new FavouriteHandler();
        }

        public String searchByPrice(String value){
            Double price = Double.parseDouble(value);
            return cookieHandler.searchByPrice(price).toString();
        }

        public String searchByRating(String rating){
            Double ratingF = Double.parseDouble(rating);
            return cookieHandler.searchByRating(ratingF).toString();
        }

//        public int favoriteListSize(String username){
//            return favouriteHandler.viewAll(username).size();
//        }

        public List<FavouriteCookie> viewFavorites(String username){
            return favouriteHandler.viewAll(username);
        }

        public void addFavourite(String username, String type) {
            FavouriteCookie favouriteCookie = new FavouriteCookie(username, type);
            favouriteHandler.createFavourite(favouriteCookie);
        }

        public void removeFavourite(String username, String type) {
            FavouriteCookie favouriteCookie = new FavouriteCookie(username, type);
            favouriteHandler.deleteFavourite(favouriteCookie);
        }

}
