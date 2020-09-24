package client;

import model.Admin;
import model.Client;
import model.Cookie;
import model.FavouriteCookie;
import presentationLayer.view.LoginView;
import server.Repository;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import static presentationLayer.controller.MasterController.setConnection;

public class Clientt{

    public static class Connection extends Thread implements Repository, Serializable {

        public  final Socket socket;
        private final ObjectOutputStream output;
        private final ObjectInputStream input;

        private boolean connected;
        private String id;


        public Connection(Socket socket) throws IOException {

            this.socket = socket;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            id = String.valueOf(ThreadLocalRandom.current().nextInt());

        }

        @Override
        public void run()
        {
            while (socket.isConnected())
            {
                connected = true;
                try
                {
                    boolean serverHasData = socket.getInputStream().available() != 0;

                    if (serverHasData) {
                        Object msg = input.readObject();
                        JOptionPane.showConfirmDialog(null,Instant.now() + " Got from server: " + msg);
                        System.out.println(Instant.now() + " Server disconnected");
                    }
                    Thread.sleep(500);
                }
                catch (InterruptedException | IOException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

            connected = false;
        }

        @Override
        public String getStringMessage() throws IOException, ClassNotFoundException {
            if(connected){
                return (String)input.readObject();
            }
            else
                return "";
        }

        @Override
        public Cookie getCookieMessage() throws IOException, ClassNotFoundException {
           if(connected){
               return (Cookie)input.readObject();
           }
           else
               return null;
        }

        @Override
        public Client getClientMessage() throws IOException, ClassNotFoundException {
            if(connected){
                return (Client)input.readObject();
            }
            else
                return null;
        }

        @Override
        public Admin getAdminMessage() throws IOException, ClassNotFoundException {
            if(connected){
                return (Admin) input.readObject();
            }
            else
                return null;
        }

        @Override
        public FavouriteCookie getFavouriteCookieMessage() throws IOException, ClassNotFoundException {
            if(connected){
                return (FavouriteCookie) input.readObject();
            }
            else
                return null;
        }


        @Override
        public void sendMessage(String message) throws IOException
        {
            output.writeObject(message);
        }

        @Override
        public void sendMessage(Cookie cookie) throws IOException
        {
            if (connected == true){
                output.writeObject(cookie);
            }
        }
        @Override
        public void sendMessage(Client client) throws IOException
        {
            if (connected == true){
                output.writeObject(client);
            }
        }

        @Override
        public void sendMessage(Admin admin) throws IOException
        {
            if (connected == true){
                output.writeObject(admin);
            }
        }

        @Override
        public void sendMessage(FavouriteCookie favouriteCookie) throws IOException {

            if (connected == true){
                output.writeObject(favouriteCookie);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost", 5002);
        Connection connection = new Connection(socket);

        setConnection(connection);

        connection.sendMessage(connection.id);
        connection.start();

        LoginView loginView = new LoginView();//
        loginView.setVisible(true);

        //connection.sendMessage("closeClient");
        connection.sendMessage(connection.id);


        System.out.println("id  " + connection.id);

        //socket.close();
        //System.exit(0);
    }

}