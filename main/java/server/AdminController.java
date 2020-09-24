package server;

import businessLayer.service.AdminHandler;
import businessLayer.service.ClientHandler;
import businessLayer.service.CookieHandler;
import dataAccessLayer.ClientDAOImpl;
import dataAccessLayer.CookieDAOImpl;
import model.Client;
import model.Cookie;

import java.io.IOException;

public class AdminController {

    private CookieDAOImpl cookieDAO;
    private ClientDAOImpl clientDAO;
    private CookieHandler cookieHandler;
    private ClientHandler clientHandler;
    private AdminHandler adminHandler;

    public AdminController(){

        cookieDAO = new CookieDAOImpl();
        clientDAO = new ClientDAOImpl();
        cookieHandler = new CookieHandler();
        clientHandler = new ClientHandler();

    }

    public String createCookie(Cookie cookie){
        if(cookieHandler.createCookie(cookie))
            return "Cookie successfully created!";

        else
            return "Couldn't create the cookie";

    }

    public String deleteCookie(Cookie cookie){
        if(cookieHandler.deleteCookie(cookie))
            return "Cookie successfully removed";

        else
            return "Couldn't remove the cookie";
    }

    public String updateCookie(Cookie cookie){
        if(cookieHandler.updateCookie(cookie))
            return "SCookie successfully updated";

        else
            return "Couldn't update the cookie";
    }

    public String searchCookie(){
        return cookieHandler.viewAllCookies().toString();
    }

    public Cookie searchCookieByType(String type){
        return  cookieHandler.searchByType(type);
    }

    public Cookie searchCookieByPrice(Double price){
        return  cookieHandler.searchByPrice(price);
    }

    public Cookie searchCookieBySweets(Double sweets){
        return  cookieHandler.searchBySweets(sweets);
    }

    public Cookie searchCookieByRating(Double rating){
        return  cookieHandler.searchByRating(rating);
    }

//    public String buyCookie(int cookieId) throws IOException, ClassNotFoundException {
//        if(cookieHandler.buyCookie(cookieId))
//            return "Successfully bought!";
//        else
//            return "Couldn't buy the cookie!";
//    }

    public String createClient(Client user){
            if(clientHandler.createUser(user))
                return "Successfully added!";

            return "Couldn't add the user!";
    }

    public String deleteClient(Client user){
        if(clientDAO.deleteUser(user))
            return "Client successfully deleted!";

        else
            return "Couldn't delete the client!";

    }

    public String updateClient(Client user){
        if(clientDAO.updateUser(user))
            return "Client successfully updated!";
        else
            return "Couldn't update the client!";

    }

    public String searchClient(String username){
        String clients;
        if("".equals(username)){
            clients = clientDAO.viewAllUsers().toString();
        }
        else
        {
            Client regularUser = clientHandler.searchByUsername(username);
            if(regularUser!=null) {
                clients = regularUser.toString();
            }
            else {
                clients = "";
            }
        }
        return clients;
    }

    public Client getClient(String username){
        return clientHandler.searchByUsername(username);
    }


}