package businessLayer.service;

import client.CookieService;
import dataAccessLayer.CookieDAOImpl;
import model.Client;
import model.Cookie;
import model.ShoppingCart;
import presentationLayer.view.CookieVisitView;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class CookieHandler {

    private CookieDAOImpl cookieDAO = new CookieDAOImpl();

    public CookieHandler(){

    }

    public boolean createCookie(Cookie cookie) {
        return cookieDAO.createCookie(cookie);
    }

    public boolean updateCookie(Cookie cookie) {
        return cookieDAO.updateCookie(cookie);
    }

    public boolean deleteCookie(Cookie cookie) {
        return cookieDAO.deleteCookie(cookie);
    }

    public List<Cookie> viewAllCookies() {
        return cookieDAO.viewAllCookies();
    }


//    public boolean buyCookie(int cookieId) throws IOException, ClassNotFoundException {
//
//        Date thisDate = new Date(System.currentTimeMillis());
//
//        CookieService cookieService = new CookieService();
//        Cookie cookie = cookieService.findCookieById(cookieId);
//
//        ClientService clientService = new ClientService();
//
//        Client client = clientService.findClientById(Login.idCurrentClient);
//        ShoppingCart shoppingCart = new ShoppingCart(client, cookie, thisDate);
//
//
//        Double newSum = client.getClientAmmount() - cookie.getPrice();
//
//        if (cookie.getStock() > 0) {
//
//            if (newSum < 100) {
//                JOptionPane.showMessageDialog(CookieVisitView.frame, "Insufficient funds! The operation cannot be processed!");
//                return false;
//            } else {
//                clientService.modifyClient(client.getClientId(), client.getClientName(), client.getClientPassword(), client.getClientEmail(), newSum);
//                //cookieService.removeCookie(cookieId);
//                cookieService.updateStock(cookieId, cookie.getType(), cookie.getPrice(), cookie.getQuantityOfSweeteners(), cookie.getRating(), cookie.getStock() - 1);
//                String message = "The client with ID " + client.getClientId() + " bought a cookie named   " + cookie.getType() + " in the amount of  " + cookie.getPrice() + ".";
//                JOptionPane.showMessageDialog(CookieVisitView.frame, message);
//                return true;
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(CookieVisitView.frame, "Insufficient stock! The operation cannot be processed!");
//            return false;
//        }
//    }


    public Cookie searchByType(String type) {
        return cookieDAO.searchByType(type);
    }

    public Cookie searchByPrice(Double price){
        return cookieDAO.searchByPrice(price);
    }

    public Cookie searchBySweets(Double sweets){
        return cookieDAO.searchBySweets(sweets);
    }

    public Cookie searchByRating(Double rating){
        return cookieDAO.searchByRating(rating);
    }

}