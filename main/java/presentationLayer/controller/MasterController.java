package presentationLayer.controller;

import client.Clientt;
import server.Repository;

public class MasterController {

    public static Repository connection;

    public static void setConnection(Clientt.Connection connection) {

        MasterController.connection = connection;
    }
}
