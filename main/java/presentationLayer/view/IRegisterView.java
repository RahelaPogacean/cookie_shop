package presentationLayer.view;


import java.sql.SQLException;

interface IRegisterDataProvider {

    String getUsername();
    String getPassword();
   // int getIdTextField();
    String getEmailTextField();
    String getConfirmPasswordField();
}

interface IRegisterViewProvider {

    void showRegularView() throws SQLException;
    void showErrorMessage(String message);
}

public interface IRegisterView extends IRegisterDataProvider, IRegisterViewProvider{

}
