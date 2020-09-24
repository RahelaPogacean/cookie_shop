package client;

import dataAccessLayer.FavouriteDAO;
import model.FavouriteCookie;
import presentationLayer.controller.MasterController;

import java.io.IOException;

public class FavouriteService extends MasterController {

    public void addFavourite(String username, String type) throws IOException, ClassNotFoundException {
        FavouriteDAO favouriteDAO = new FavouriteDAO();
        FavouriteCookie favouriteCookie = new FavouriteCookie(username, type);
        connection.sendMessage("addFavourite");
        connection.sendMessage(favouriteCookie);

        favouriteDAO.createFavourite(favouriteCookie);
    }

    public void removeFavourite(String username, String type) throws IOException, ClassNotFoundException {
        FavouriteDAO favouriteDAO = new FavouriteDAO();
        FavouriteCookie favouriteCookie = new FavouriteCookie(username, type);
        connection.sendMessage("removeFavourite");
        connection.sendMessage(favouriteCookie);

        favouriteDAO.deleteFavourite(favouriteCookie);
    }
}
