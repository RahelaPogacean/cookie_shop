package dataAccessLayer;

import model.ShoppingCart;

import java.sql.Date;
import java.util.ArrayList;;

public interface ShoppingCartDAOInterface {

    public void updateShopping(ShoppingCart shoppingCart);
    public void insertOrder(ShoppingCart shoppingCart);
    public ArrayList<ShoppingCart> getShoppingList();
    public ShoppingCart getShoppingByDate(Date shoppingDate);

}
