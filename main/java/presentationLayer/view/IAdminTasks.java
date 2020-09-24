package presentationLayer.view;

import java.sql.SQLException;

//interface IAdminDataProvider{
//
////        String getCookieType();
////        Double getCookiePrice();
////        Double getCookieSweets();
////
////        String getUsername();
////        String getPassword();
//
//}

interface IAdminTasksViewProvider{

    void showCookieView() throws SQLException;
    void showClientView() throws SQLException;
    void showErrorMessage(String message);
}

interface IAdminTasksView extends IAdminTasksViewProvider {


}



