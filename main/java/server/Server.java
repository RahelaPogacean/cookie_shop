package server;

import model.Admin;
import model.Client;
import model.Cookie;
import model.FavouriteCookie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import presentationLayer.controller.LoginController;
import presentationLayer.view.ILoginView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.*;


public class Server implements Observer
{

    final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    public static SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


    private static AdminController adminController = new AdminController();
    private static ClientLoginController clientLoginController = new ClientLoginController();
    private static AdminLoginController adminLoginController = new AdminLoginController();
    private static ClientController clientController = new ClientController();

    public static List<Connection> connectedClients = new ArrayList<>();
    public static HashMap<String, Client> onlineClients = new HashMap<String, Client>();

    static ILoginView loginView;
    LoginController loginController;

    private final Cookie cookie;

    public Server(Cookie cookie) throws IOException {

        this.cookie = cookie;
        this.cookie.addObserver(this);

    }

    public static void notifyUsers(String cookieType){
        Set<String> keys = onlineClients.keySet();
        for(Connection connection : connectedClients){
            for(String key : keys){
                if(favouriteMatch(cookieType, onlineClients.get(key).getClientName()) && key.equals(connection.id)){
                    System.out.println("hereee boyy");
                    Connection.write(onlineClients.get(key).update(cookieType), connection.output);
                }
            }
        }
    }

    public static boolean favouriteMatch(String cookieType, String username){
        List<FavouriteCookie> list = clientController.viewFavorites(username);
        for(FavouriteCookie fav : list){
            if(fav.getFavouriteType().equals(cookieType)) {
                return true;
            }
        }
        return false;
    }

//    public static void notifyUsers(String type){
//        ClientService clientService = new ClientService();
//        Client client = clientService.findClientById(Login.idCurrentClient);
//        List<FavouriteCookie> favouriteCookies = clientController.viewFavorites(client.getClientName());
//        for(FavouriteCookie fav : favouriteCookies){
//            if(fav.getFavouriteType().equals(type)) {
//                JOptionPane.showMessageDialog(LoginView.frame, "Your favourite cookie is back on stock!");
//            }
//        }
//}

    @Override
    public void update(Observable o, Object arg) {
        if(o == cookie){
            notifyUsers(cookie.getType());
        }
    }

    static class Connection extends Thread implements Repository, Serializable{

        public Socket clientSocket;
        private final ObjectInputStream input;
        private final ObjectOutputStream output;
        private String id;


        public Connection(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output =new ObjectOutputStream(clientSocket.getOutputStream());
        }

        @Override
        public void run()
        {
            try
            {
                String name = null;
                while (clientSocket.isConnected())
                {
                    String operation = (String)input.readObject();
                    switch (operation){
                        case "login": {

                            name = getStringMessage();

                            boolean ok = clientLoginController.login(name, getStringMessage());
                            if(ok){
                                onlineClients.put(this.id, adminController.getClient(name));
                            }

                            connectedClients.add(this);
                            System.out.println("online "+ onlineClients.toString());
                            System.out.println("connected " + connectedClients.toString());

                            System.out.println(" user login");
                            sendMessage(String.valueOf(ok));
                            break;
                        }
                        case "adminLogin":
                            name = getStringMessage();
                            boolean ok = adminLoginController.adminLogin(name, getStringMessage());

                            System.out.println("2 admin login "+ String.valueOf(ok));

                            sendMessage(String.valueOf(ok));
                            break;

                        case "addCookie":{
                            Cookie cookie = getCookieMessage();
                            //new Server(cookie);
                            System.out.println("add");
                            System.out.println(cookie);
                            sendMessage(adminController.createCookie(cookie));

                            break;
                        }
                        case "removeCookie": {
                            Cookie cookie = getCookieMessage();
                            System.out.println("remove");
                            sendMessage(adminController.deleteCookie(cookie));
                            break;
                        }

//                        case "updateCookie": {
//                            Cookie cookie = getCookieMessage();
//                            new Server(cookie);//
//                            System.out.println("update");
//                            sendMessage(adminController.updateCookie(cookie));
//                            break;
//                        }

                        case "addClient": {
                            Client client = getClientMessage();
                            System.out.println("create user");
                            sendMessage(adminController.createClient(client));
                            break;
                        }

                        case "updateClient":{
                            Client client = getClientMessage();
                            System.out.println("update user");
                            sendMessage(adminController.updateClient(client));
                            break;
                        }
                        case "removeClient": {
                            Client client = getClientMessage();
                            System.out.println("remove client");
                            sendMessage(adminController.deleteClient(client));
                            break;
                        }

                        case "searchCookieByType":
                            Cookie cookie = getCookieMessage();
                            System.out.println("received type   "+ cookie);
                            sendMessage(adminController.searchCookieByType(cookie.getType()));
                            break;

                        case "searchCookieBySweets":
                            cookie = getCookieMessage();
                            System.out.println("received sweets  "+ cookie);
                            sendMessage(adminController.searchCookieBySweets(cookie.getQuantityOfSweeteners()));
                            break;

                        case "searchCookieByPrice":
                            cookie = getCookieMessage();
                            System.out.println("received  price "+ cookie);
                            sendMessage(adminController.searchCookieByPrice(cookie.getPrice()));
                            break;

                        case "searchCookieByRating":
                            cookie = getCookieMessage();
                            System.out.println("received  rating "+ cookie);
                            sendMessage(adminController.searchCookieByRating(cookie.getRating()));
                            break;

                        case "addFavourite":
                            FavouriteCookie favouriteCookie = getFavouriteCookieMessage();
                            System.out.println(favouriteCookie);
                            System.out.println("add favorite");
                            clientController.addFavourite(favouriteCookie.getClientName(), favouriteCookie.getFavouriteType());
                            break;

                        case "closeClient": {
                            String id = getStringMessage();
                            onlineClients.remove(id);
                            System.out.println("Pop out : " + id);
                            System.out.println(onlineClients);
                            break;
                        }

                        case "removeFavourite":
                            favouriteCookie = getFavouriteCookieMessage();
                            System.out.println(favouriteCookie);
                            System.out.println("remove favorite");
                            clientController.removeFavourite(favouriteCookie.getClientName(), favouriteCookie.getFavouriteType());
                    }

                    boolean clientHasData = clientSocket.getInputStream().available() > 0;

                    if (clientHasData) {
                        String msg = (String) input.readObject();
                        System.out.println(Instant.now() + " Got from client: " + msg);

                        System.out.println(Instant.now() + " Sending response: ACK");
                        output.writeObject("ACK");
                    }

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println(Instant.now() + " Client disconnected?");
            }
            catch (IOException | ClassNotFoundException  e)
            {
                e.printStackTrace();
            }

            try
            {
                output.close();
                input.close();
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public static void write(Object obj, ObjectOutputStream output) {
            try{
                output.writeObject(obj);
            }
            catch(IOException e){ e.printStackTrace(); }
        }

        @Override
        public String getStringMessage() throws IOException, ClassNotFoundException {

           return (String)input.readObject();
        }

        @Override
        public Cookie getCookieMessage() throws IOException, ClassNotFoundException {
            return (Cookie)input.readObject();
        }

        @Override
        public Client getClientMessage() throws IOException, ClassNotFoundException {
            return (Client) input.readObject();
        }

        @Override
        public Admin getAdminMessage() throws IOException, ClassNotFoundException {
            return (Admin) input.readObject();
        }

        @Override
        public FavouriteCookie getFavouriteCookieMessage() throws IOException, ClassNotFoundException {
            return (FavouriteCookie)input.readObject();
        }

        @Override
        public void sendMessage(String message) throws IOException {
            output.writeObject(message);
        }

        @Override
        public void sendMessage(Cookie cookie) throws IOException {
            output.writeObject(cookie);
        }

        @Override
        public void sendMessage(Client client) throws IOException {
            output.writeObject(client);
        }

        @Override
        public void sendMessage(Admin admin) throws IOException {
            output.writeObject(admin);
        }

        @Override
        public void sendMessage(FavouriteCookie favouriteCookie) throws IOException {
            output.writeObject(favouriteCookie);
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        try (ServerSocket socket = new ServerSocket(5002))
        {
            while (true)
            {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept();
                Connection connection = new Connection(clientSocket);
                connection.id = connection.getStringMessage();
               //connectedClients.add(connection);
                connection.start();

            }
        }
    }
}
