package client;


import dataAccessLayer.CookieDAO;
import model.Cookie;
import presentationLayer.controller.MasterController;
import presentationLayer.view.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CookieService extends MasterController {

    public static boolean validateStock;
    private String message;

    public Cookie createCookie(int id, String cookieType, Double cookiePrice, Double cookieSweet, Double rating, int stock) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = new Cookie(id, cookieType, cookiePrice, cookieSweet, rating, stock);

        cookieDAO.insertCookie(cookie);
        return cookie;
    }

    public Cookie updateCookie(int cookieId, String cookieType, Double cookiePrice, Double cookieSweet, Double rating, int stock){
        Cookie cookie = new Cookie(cookieId, cookieType, cookiePrice, cookieSweet, rating, stock);
        CookieDAO cookieDAO = new CookieDAO();
        cookieDAO.updateCookie(cookie);
        return cookie;
    }

    public void addCookie(String cookieType, Double cookiePrice, Double cookieSweet, int stock) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = new Cookie(cookieType, cookiePrice, cookieSweet, stock);

        connection.sendMessage("addCookie");
        connection.sendMessage(cookie);
        boolean ok = Boolean.parseBoolean(connection.getStringMessage());

        if(ok){
            cookieDAO.insertCookie(cookie);
        }
    }

    public void removeCookie(int cookieId) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieById(cookieId);

        connection.sendMessage("removeCookie");
        connection.sendMessage(cookie);
        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
        if(ok)
            cookieDAO.deleteCookie(cookie);
    }


    public ArrayList<Cookie> findAllCookies(){
        CookieDAO cookieDAO = new CookieDAO();
        ArrayList<Cookie> cookieArrayList = cookieDAO.getCookiesList();
        return cookieArrayList;
    }

    public Cookie findCookieById(int cookieId) {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieById(cookieId);

        return cookie;
    }

    public Cookie findCookieByPrice(Double cookiePrice) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieByPrice(cookiePrice);
        connection.sendMessage("searchCookieByPrice");
        connection.sendMessage(cookie);

        return cookie;
    }

    public Cookie findCookieBySweets(Double cookieSweets) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieBySweeteners(cookieSweets);
        connection.sendMessage("searchCookieBySweets");
        connection.sendMessage(cookie);

        return cookie;
    }

    public Cookie findCookieByType(String cookieType) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieByType(cookieType);
        connection.sendMessage("searchCookieByType");
        connection.sendMessage(cookie);//

        return cookie;
    }

    public Cookie findCookieByRating(Double rating) throws IOException, ClassNotFoundException {
        CookieDAO cookieDAO = new CookieDAO();
        Cookie cookie = cookieDAO.getCookieByRating(rating);
        connection.sendMessage("searchCookieByRating");
        connection.sendMessage(cookie);

        return cookie;
    }

    public void modifyCookie(int cookieId, String cookieType, Double cookiePrice, Double cookieSweet, int stock) throws IOException, ClassNotFoundException {
        Cookie cookie = new Cookie(cookieId, cookieType, cookiePrice, cookieSweet, stock);
        CookieDAO cookieDAO = new CookieDAO();
        //connection.sendMessage("updateCookie");
       // connection.sendMessage(cookie);
//        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
//        if(ok)
            cookieDAO.updateCookie(cookie);
    }

    public void modifyCookie2(int cookieId, String cookieType, Double cookiePrice, Double cookieSweet, Double rating){
        Cookie cookie = new Cookie(cookieId, cookieType, cookiePrice, cookieSweet, rating);
        CookieDAO cookieDAO = new CookieDAO();
        cookieDAO.updateCookie(cookie);
    }

    public void updateStock(int cookieId, String cookieType, Double cookiePrice, Double cookieSweet, Double rating, int stock) throws IOException, ClassNotFoundException {

        Cookie cookie = new Cookie(cookieId, cookieType, cookiePrice, cookieSweet, rating, stock);
        CookieDAO cookieDAO = new CookieDAO();

        if(cookie.getStock() == 0){
            cookieDAO.updateCookie(cookie);
            JOptionPane.showMessageDialog(LoginView.frame, "Your favourite cookie is back on stock!");
            cookie.cookieBack();

        }
        cookieDAO.updateCookie(cookie);

        System.out.println("this stock" + cookie.getStock());

//        connection.sendMessage("updateCookie");
//        connection.sendMessage(cookie);
//        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
//        if(ok)

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
