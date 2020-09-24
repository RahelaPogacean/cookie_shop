package client;

import dataAccessLayer.ClientDAO;
import model.Client;
import model.Cookie;
import presentationLayer.controller.MasterController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends MasterController {

    public void addClient(String clName, String clPass, String clEmail, Double clAmm) throws IOException, ClassNotFoundException {
        ClientDAO ci = new ClientDAO();
        Client c1 = new Client(clName, clPass, clEmail, clAmm);
        connection.sendMessage("addClient");
        connection.sendMessage(c1);
        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
        if(ok)
            ci.insertClient(c1);
    }
    public void addClient2(int clientId, String clName, String clPass, String clEmail, Double clAmm, List<Cookie> cookieList){
        ClientDAO ci = new ClientDAO();

        Client c1 = new Client(clientId, clName, clPass, clEmail, clAmm, cookieList);
        ci.insertClient(c1);
    }

    public void removeClient(int clId) throws IOException, ClassNotFoundException {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientById(clId);
        connection.sendMessage("removeClient");
        connection.sendMessage(client);
        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
        if(ok)
            clientDAO.deleteClient(client);
    }


    public ArrayList<Client> findAll(){
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> clientList = clientDAO.getClientsList();
        return clientList;
    }


    public Client findClientById(int clientId) {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientById(clientId);

        return client;
    }

    public Client findClientByName(String clientName) {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientByName(clientName);

        return client;
    }

    public Client findClientByPassword(String password) {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientByPassword(password);

        return client;
    }

    public void modifyClient(int clientId, String clientName, String clientPass, String clientEmail, Double clientAmm) throws IOException, ClassNotFoundException {
        Client client = new Client(clientId, clientName, clientPass, clientEmail, clientAmm);
        ClientDAO clientDAO = new ClientDAO();
        connection.sendMessage("updateClient");
        connection.sendMessage(client);
        boolean ok = Boolean.parseBoolean(connection.getStringMessage());
        if(ok)
            clientDAO.updateClient(client);
    }

}
