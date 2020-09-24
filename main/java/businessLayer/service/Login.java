package businessLayer.service;

import model.Admin;
import model.Client;

public class Login {

    public static int idCurrentClient = 0;

    public Login() {

    }

    public static Client clientLocationInDB(String user) {

        ClientService clientService = new ClientService();
        Client client = clientService.findClientByName(user);//client found in db

        System.out.println("client" + client);

        if (client != null) {
            if (!(user.equals("Rahe"))) {//daca e regular user
                idCurrentClient = client.getClientId();
            }
            System.out.println(client);
            return client;
        }

        return null;
    }


    public static Admin adminLocationInDB(String user) {

        AdminService adminService = new AdminService();
        Admin admin = adminService.findAdminByName(user);//employee found in db

        if (admin != null) {
            return admin;
        }

        return null;
    }
}


