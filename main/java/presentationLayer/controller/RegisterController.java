package presentationLayer.controller;

import businessLayer.service.ClientService;
import presentationLayer.view.CookieVisitView;
import presentationLayer.view.IRegisterView;
import presentationLayer.view.RegisterView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    private final IRegisterView registerView;

    public RegisterController(IRegisterView registerView) {

        this.registerView = registerView;
    }

        public void userRegister() throws SQLException, IOException, ClassNotFoundException {

            String username = registerView.getUsername();
            String password = registerView.getPassword();
           // int id = registerView.getIdTextField();
            String confirm = registerView.getConfirmPasswordField();
            String email = registerView.getEmailTextField();

            ClientService clientService = new ClientService();

            if (username == "" || password == "" || confirm == "" || email == "") {
                JOptionPane.showMessageDialog(RegisterView.frame, "All the fields must be completed!!!");
            }

            else
            if (!(password.equals(confirm))) {
                JOptionPane.showMessageDialog(RegisterView.frame, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            else
            if(!(username.equals("")) && !(password.equals("")) && !(confirm.equals("")) && !(email.equals("")) && password.equals(confirm )){

                clientService.addClient(username, password, email, null);//insert into db
                JOptionPane.showMessageDialog(RegisterView.frame, "Your account has been created!");
                new CookieVisitView();
            }
        }
    }
