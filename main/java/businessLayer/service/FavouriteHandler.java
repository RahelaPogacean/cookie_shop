package businessLayer.service;


import dataAccessLayer.FavouriteDAO;
import model.FavouriteCookie;

import java.util.List;

public class FavouriteHandler {

    private FavouriteDAO favoriteDAO = new FavouriteDAO();

    public FavouriteHandler() {
    }

    public boolean createFavourite(FavouriteCookie favorite) {

        return favoriteDAO.createFavourite(favorite);
    }

    public boolean deleteFavourite(FavouriteCookie favorite) {
        return favoriteDAO.deleteFavourite(favorite);
    }

    public List<FavouriteCookie> viewAll(String username) {
        return favoriteDAO.viewAll(username);
    }
}


