package server;

import model.Admin;
import model.Client;
import model.Cookie;
import model.FavouriteCookie;

import java.io.IOException;

public interface Repository {


    String getStringMessage() throws IOException, ClassNotFoundException;

    Cookie getCookieMessage() throws IOException, ClassNotFoundException;

    Client getClientMessage() throws IOException, ClassNotFoundException;

    Admin getAdminMessage() throws IOException, ClassNotFoundException;

    FavouriteCookie getFavouriteCookieMessage() throws IOException, ClassNotFoundException;

    void sendMessage(String message) throws IOException;

    void sendMessage(Cookie cookie) throws IOException;

    void sendMessage(Client client) throws IOException;

    void sendMessage(Admin admin) throws IOException;

    void sendMessage(FavouriteCookie favouriteCookie) throws IOException;
}
