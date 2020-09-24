package dataAccessLayer;

import model.Cookie;

import java.util.ArrayList;

public interface CookieDAOInterface {

    public void insertCookie(Cookie cookie);
    public Cookie  getCookieById(int id);
    public Cookie getCookieByType(String cookieType);
    public Cookie  getCookieByPrice(Double price);
    public Cookie  getCookieBySweeteners(Double sweets);
    public Cookie  getCookieByRating(Double rating);
    public ArrayList<Cookie> getCookiesList();
    public Cookie updateCookie(Cookie cookie);
    public void deleteCookie(Cookie cookie);

}
