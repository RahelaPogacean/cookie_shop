package dataAccessLayer;

import model.Client;

import java.util.ArrayList;

public interface ClientDAOInterface {

    public ArrayList<Client> getClientsList();
    public Client getClientById(int clientId);
    public void insertClient(Client client);
    public void updateClient(Client client);
    public void deleteClient(Client client);
    public Client getClientByName(String clientName);
    public Client getClientByPassword(String clientPassword);

    }
