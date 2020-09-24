package presentationLayer.view;

import java.sql.SQLException;

interface ILoginDataProvider {

    String getUsername();
    String getPassword();
}

interface IViewProvider {

    void showAdminView();
    void showRegularView() throws SQLException;
    void showErrorMessage(String message);
}

public interface ILoginView extends ILoginDataProvider, IViewProvider{
}
