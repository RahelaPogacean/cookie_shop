package client;

import dataAccessLayer.ShoppingCartDAO;
import model.*;
import presentationLayer.controller.MasterController;
import presentationLayer.view.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCartService extends MasterController {

    public ShoppingCartService(){

    }

    public ShoppingCart findShoppingByDate(Date date) {
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        ShoppingCart shoppingCart = shoppingCartDAO.getShoppingByDate(date);

        return shoppingCart;
    }

    public static ArrayList<ShoppingCart> findAllShoppings(){
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        ArrayList<ShoppingCart> shoppingCarts = shoppingCartDAO.getShoppingList();
        return shoppingCarts;
    }

    public void updateShopping(Client clientId, Date thisDate, HashMap<String, Integer> myCookies){
        ShoppingCart shoppingCart = new ShoppingCart(clientId, thisDate, myCookies);
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        shoppingCartDAO.updateShopping(shoppingCart);
    }

    public void addToCart( HashMap<String, Integer> myCookies, Client client, String type, int quantity, String dimension, String topping) throws IOException, ClassNotFoundException {
        CookieService cookieService = new CookieService();
        Cookie cookie = cookieService.findCookieByType(type);
        if(cookie.getStock() - quantity < 0){
            JOptionPane.showMessageDialog(LoginView.frame, "Insufficient cookie stock! ");
        }
        else {
            myCookies.put(type, quantity);
            ShoppingCartService shoppingCartService = new ShoppingCartService();
            Date thisDate = new Date(System.currentTimeMillis());
            shoppingCartService.updateShopping(client, thisDate, myCookies);
        }
    }

    public void buyCookies(Client client, HashMap<String, Integer> myCookies, String dimension, String topping) throws IOException, ClassNotFoundException {

        Set<String> keys = myCookies.keySet();
        CookieService cookieService = new CookieService();
        Double totalSum = 0.0;
        for(Map.Entry<String, Integer> entry: myCookies.entrySet()) {
            Cookie cookie = cookieService.findCookieByType(entry.getKey());
            int stock = cookie.getStock() - entry.getValue();
            cookieService.updateCookie(cookie.getCookieId(), cookie.getType(), cookie.getPrice(), cookie.getQuantityOfSweeteners(), cookie.getRating(), stock);

            if(dimension.equals("small")){
                cookie = new SmallCookie(cookie);
                if(topping.equals("chocolate")){
                    cookie = new ChocolateToppingsDecorator(cookie);
                    totalSum += cookie.getPrice() * entry.getValue();
                    System.out.println("price " + cookie.getPrice() + " totalSum " + totalSum);
                }
                else
                    if(topping.equals("lemon")){
                        cookie = new LemonToppingsDecorator(cookie);
                        totalSum += cookie.getPrice() * entry.getValue();
                    }
                    else
                        if(topping.equals("strawberries")){
                            cookie = new StrawberriesToppingsDecorator(cookie);
                            totalSum += cookie.getPrice() * entry.getValue();
                        }
            }
            else
                if(dimension.equals("medium")){
                    cookie = new SmallCookie(cookie);
                    if(topping.equals("chocolate")){
                        cookie = new ChocolateToppingsDecorator(cookie);
                        totalSum += cookie.getPrice() * entry.getValue();
                    }
                    else
                    if(topping.equals("lemon")){
                        cookie = new LemonToppingsDecorator(cookie);
                        totalSum += cookie.getPrice() * entry.getValue();
                    }
                    else
                    if(topping.equals("strawberries")){
                        cookie = new StrawberriesToppingsDecorator(cookie);
                        totalSum += cookie.getPrice() * entry.getValue();
                    }
                }
                else
                    if(dimension.equals("huge")){
                        cookie = new SmallCookie(cookie);
                        if(topping.equals("chocolate")){
                            cookie = new ChocolateToppingsDecorator(cookie);
                            totalSum += cookie.getPrice() * entry.getValue();
                        }
                        else
                        if(topping.equals("lemon")){
                            cookie = new LemonToppingsDecorator(cookie);
                            totalSum += cookie.getPrice() * entry.getValue();
                        }
                        else
                        if(topping.equals("strawberries")){
                            cookie = new StrawberriesToppingsDecorator(cookie);
                            totalSum += cookie.getPrice() * entry.getValue();
                        }
                    }
        }
        Double newAmm = client.getClientAmmount() - totalSum;
        ClientService clientService = new ClientService();
        clientService.modifyClient(client.getClientId(), client.getClientName(), client.getClientPassword(), client.getClientEmail(), newAmm);

        }
    }

