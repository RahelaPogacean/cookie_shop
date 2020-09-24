package server;

import businessLayer.service.ClientHandler;
import dataAccessLayer.ClientDAOImpl;

public class ClientLoginController {

        private ClientDAOImpl clientDAO;

        private ClientHandler clientHandler;


        public ClientLoginController() {
            clientDAO = new ClientDAOImpl();
            clientHandler = new ClientHandler();
        }

        public boolean login(String username, String password){
            return clientHandler.searchByUsernameAndPassword(username, password);
        }

//        public void setClientDAO(ClientDAOImpl clientDAO) {
//            this.clientDAO = clientDAO;
//        }

//        public void setRegularUserManager(ClientHandler clientHandler) {
//            this.regularUserManager = clientHandler;
//        }
    }

