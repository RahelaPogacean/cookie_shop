package businessLayer.service;


import dataAccessLayer.ClientDAOImpl;
import model.Client;

import java.util.List;

public class ClientHandler {

    private ClientDAOImpl regularUserDAO = new ClientDAOImpl();

    public ClientHandler(){

    }

    public boolean createUser(Client regularUser) {
        return regularUserDAO.createUser(regularUser);
    }

    public boolean updateUser(Client regularUser) {
        return regularUserDAO.updateUser(regularUser);
    }

    public boolean deleteUser(Client regularUser) {
        return regularUserDAO.deleteUser(regularUser);
    }

    public List<Client> viewAllUsers() {
        return regularUserDAO.viewAllUsers();
    }

    public Client searchByUsername(String username) {
        return regularUserDAO.searchByUsername(username);
    }

    public boolean searchByUsernameAndPassword(String username, String password) {
        return regularUserDAO.searchByUsernameAndPassword(username,password);
    }

    public void setRegularUserDAO(ClientDAOImpl regularUserDAO) {
        this.regularUserDAO = regularUserDAO;
    }
}

