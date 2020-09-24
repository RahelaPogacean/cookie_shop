package presentationLayer.controller;

import businessLayer.service.Login;
import model.Admin;
import model.Client;
import presentationLayer.view.AdminTasks;
import presentationLayer.view.CookieVisitView;
import presentationLayer.view.ILoginView;
import presentationLayer.view.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends MasterController {

    private final ILoginView loginView;

    public LoginController(ILoginView loginView) {

        this.loginView = loginView;
    }

    public void userLogin() throws SQLException, IOException, ClassNotFoundException {

        String username = loginView.getUsername();
        String password = loginView.getPassword();

//           username= "Pop Ana";
//           password = "ana";


        Client client = Login.clientLocationInDB(username);

        System.out.println("1 "+ password.equals(""));
        System.out.println("2"+ password.equals(client.getClientPassword()));

        if (!(password.equals("")) && client != null && password.equals(client.getClientPassword())) {

            connection.sendMessage("login");
            connection.sendMessage(username);
            connection.sendMessage(password);

            boolean ok = Boolean.parseBoolean(connection.getStringMessage());

            if(ok){
                JOptionPane.showMessageDialog(LoginView.frame, "Successfully logged in!");
                new CookieVisitView();

            }

        }
        else
        if(password.equals("")){
            JOptionPane.showMessageDialog(LoginView.frame, "All fields must be completed!");
        }

        else
        if(client == null ){
            JOptionPane.showMessageDialog(LoginView.frame, "This user doesn't exist!");
        }

        else
        if(client.getClientName().equals(username) && !(client.getClientPassword().equals(password))){
            JOptionPane.showMessageDialog(LoginView.frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void adminLogin() throws SQLException, IOException, ClassNotFoundException {

        String username = loginView.getUsername();
        String password = loginView.getPassword();

//        username = "Rahe";//for mocking tests
//        password = "necti";


        Admin admin = Login.adminLocationInDB(username);

        if (!(password.equals("")) && admin != null && password.equals(admin.getAdminPassword())) {

            connection.sendMessage("adminLogin");
            connection.sendMessage(username);
            connection.sendMessage(password);

            boolean ok = Boolean.parseBoolean(connection.getStringMessage());

            System.out.println("3"+ ok);

            if(ok){
                JOptionPane.showMessageDialog(LoginView.frame, "Successfully logged in!");
                new AdminTasks();

            }

        }
        else
        if(password.equals("")){
            JOptionPane.showMessageDialog(LoginView.frame, "All fields must be completed!");
        }

        else
        if(admin == null ){
            JOptionPane.showMessageDialog(LoginView.frame, "This admin doesn't exist!");
        }

        else
        if(admin.getAdminUsername().equals(username) && !(admin.getAdminPassword().equals(password))){
            JOptionPane.showMessageDialog(LoginView.frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

