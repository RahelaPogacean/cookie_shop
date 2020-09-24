import client.Clientt;
import client.CookieService;

import model.Cookie;
import org.junit.Test;
import presentationLayer.controller.MasterController;
import presentationLayer.view.ICookieView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Test
    public void createCookie() throws IOException, ClassNotFoundException {

        Cookie expected = new Cookie(30, "Snickers", 7.0, 100.0, 4.0, 5);
        ICookieView cookieView = mock(ICookieView.class);
        Clientt.Connection connection = mock(Clientt.Connection.class);
        MasterController.setConnection(connection);
        doNothing().when(connection).sendMessage(isA(String.class));
        when(connection.getStringMessage()).thenReturn(String.valueOf(expected));
        String type = "Snickers";
        Double price = 7.0, sweets = 100.0, rating = 4.0;
        int stock = 5, id = 30;
        when(cookieView.getCookieType()).thenReturn(type);
        when(cookieView.getCookiePrice()).thenReturn(price);
        when(cookieView.getCookieSweets()).thenReturn(sweets);
        when(cookieView.getCookieStock()).thenReturn(stock);
        when(cookieView.getCookieRating()).thenReturn(rating);

        CookieService cookieService = new CookieService();
        Cookie cookie = cookieService.createCookie(id, type, price, sweets, rating, stock);

        Cookie actual = cookie;
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void updateCookie() throws IOException, ClassNotFoundException {

        Cookie expected = new Cookie(30, "Snickers", 7.0, 100.0, 4.0, 5);
        ICookieView cookieView = mock(ICookieView.class);
        Clientt.Connection connection = mock(Clientt.Connection.class);
        MasterController.setConnection(connection);
        doNothing().when(connection).sendMessage(isA(String.class));
        when(connection.getStringMessage()).thenReturn(String.valueOf(expected));
        String type = "Snickers";
        Double price = 7.0, sweets = 100.0, rating = 4.0;
        int stock = 5, id = 30;
        when(cookieView.getCookieType()).thenReturn(type);
        when(cookieView.getCookiePrice()).thenReturn(price);
        when(cookieView.getCookieSweets()).thenReturn(sweets);
        when(cookieView.getCookieStock()).thenReturn(stock);
        when(cookieView.getCookieRating()).thenReturn(rating);

        Cookie cookie = new Cookie(id, "dobos", price, sweets, rating, 10);
        CookieService cookieService = new CookieService();
        cookie = cookieService.updateCookie(id, type, price, sweets, rating, stock);

        Cookie actual = cookie;
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void findCookieByType() throws IOException, ClassNotFoundException {

        Cookie expected = new Cookie(1, "cremes", 5.0, 100.0, 4.0, 0);
        ICookieView cookieView = mock(ICookieView.class);
        Clientt.Connection connection = mock(Clientt.Connection.class);
        MasterController.setConnection(connection);
        doNothing().when(connection).sendMessage(isA(String.class));
        when(connection.getStringMessage()).thenReturn(String.valueOf(expected));
        String type = "cremes";

        when(cookieView.getCookieType()).thenReturn(type);
        CookieService cookieService = new CookieService();
        Cookie actual = cookieService.findCookieByType(type);

        assertEquals(expected.toString(), actual.toString());
    }
}
