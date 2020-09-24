package server;

import businessLayer.service.AdminHandler;
import dataAccessLayer.AdminDAOImpl;

public class AdminLoginController {


        private AdminDAOImpl adminDAO;

        private AdminHandler adminHandler;

        public AdminLoginController( ) {

            adminDAO = new AdminDAOImpl();
            adminHandler = new AdminHandler();
        }

        public boolean adminLogin(String username, String password){
            return adminHandler.searchByUsernameAndPassword(username, password);
        }

//        public void setRegularUserDAO(ClientDAOImpl regularUserDAO) {
//            this.regularUserDAO = regularUserDAO;
//        }
//
//        public void setRegularUserManager(ClientManager regularUserManager) {
//            this.regularUserManager = regularUserManager;
//        }
    }



