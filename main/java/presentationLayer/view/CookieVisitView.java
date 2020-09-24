package presentationLayer.view;

import businessLayer.service.*;
import client.CommentService;
import client.CookieService;
import client.FavouriteService;
import client.ShoppingCartService;
import model.Client;
import model.Comment;
import model.Cookie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CookieVisitView {

    LoginView login;
    public static JFrame frame;
    public static DefaultTableModel model, model2;
    public JScrollPane scrollPane, scrollPane2;
    public JTable tabel, tabel2;
    public JPanel panel;
    public JLabel n, c, p, a, ratingLabel, quantity, dimension, topping;
    public JButton Type, Price, Sweets, buy, view, back, reset, rateStar, addfav, removeFav, addComm, like, dislike, viewComms, addToCart;
    JTextField idd, type, price, sweeteners, rate, quan, dim, top;
    public JTextArea commentsArea;

    public static JToggleButton[] buttons;
    public static ButtonGroup btnGroup = null;
    public static int[] ratings;
    public static ImageIcon starSelectedIcon, starUnselectedIcon;

    public static HashMap<String, Integer> myCookies = new HashMap<>();
    public static String dimm;
    public static String topp;

    public static JToggleButton createJButton(ImageIcon icon) {

        JToggleButton returnValue = new JToggleButton(icon);

        returnValue.setContentAreaFilled(false);
        returnValue.setDisabledIcon(starSelectedIcon);
        returnValue.setPressedIcon(starSelectedIcon);
        returnValue.setSelectedIcon(starSelectedIcon);
        returnValue.setRolloverEnabled(true);
        returnValue.setRolloverIcon(starSelectedIcon);
        returnValue.setRolloverSelectedIcon(starSelectedIcon);
        // btnGroup.add(returnValue);

        return returnValue;
    }

    public void initializeRatingStar(){
         starSelectedIcon = new ImageIcon(new ImageIcon("selectedStar.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
         starUnselectedIcon = new ImageIcon(new ImageIcon("unselectedStar.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
         buttons = new JToggleButton[5];
         ratings = new int[5];
    }

    public static JTable makeTable(ArrayList<Cookie> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("Id");
        column.add("Type");
        column.add("Price");
        column.add("QuantityOfSweeteners");
        column.add("Stock");
        column.add("Rating");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());

        Object[] v = new Object[6];
        JTable table = new JTable();
        table.setBackground(new Color(240, 19, 56));

        for (Cookie c : list) {
            v[0] = c.getCookieId();
            v[1] = c.getType();
            v[2] = c.getPrice();
            v[3] = c.getQuantityOfSweeteners();
            v[4] = c.getStock();
            v[5] = c.getRating();
            model.addRow(v);
        }
        table.setModel(model);

        JTableHeader header = table.getTableHeader();
        TableColumnModel col = header.getColumnModel();
        for (int i = 0; i < column.size(); i++) {
            TableColumn tc = col.getColumn(i);
            tc.setHeaderValue(column.get(i));
        }

        Font font = new Font("Times New Roman", 1, 22);
        table.setFont(font);
        table.setRowHeight(40);

        for (int i = 1; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setMinWidth(100);

        return table;
    }

    public static JTable makeTableComment(ArrayList<Comment> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("Id");
        column.add("Type");
        column.add("Client");
        column.add("Description");
        column.add("Likes");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());

        Object[] v = new Object[5];
        JTable table = new JTable();
        table.setBackground(new Color(240, 19, 56));

        for (Comment c : list) {
            v[0] = c.getCommentId();
            v[1] = c.getTypeCookie();
            v[2] = c.getClientName();
            v[3] = c.getContent();
            v[4] = c.getLikeCount();
            model.addRow(v);
        }
        table.setModel(model);

        JTableHeader header = table.getTableHeader();
        TableColumnModel col = header.getColumnModel();
        for (int i = 0; i < column.size(); i++) {
            TableColumn tc = col.getColumn(i);
            tc.setHeaderValue(column.get(i));
        }

        Font font = new Font("Times New Roman", 1, 22);
        table.setFont(font);
        table.setRowHeight(40);

        for (int i = 1; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setMinWidth(100);

        return table;
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        int column = source.columnAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, column)+"";

        JOptionPane.showMessageDialog(null, s);
    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 25));

        return label;
    }

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setForeground(Color.white);
        button.setBackground(Color.darkGray);
        button.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

        return button;
    }

    public void processTable(){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(450, 40, 700, 230);
        tabel.setBounds(70, 40, 700, 500);
        tabel.setVisible(true);

        scrollPane.setViewportView(tabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    public void processTable2(){

        scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(450, 300, 700, 230);
        tabel2.setBounds(70, 40, 700, 300);
        tabel2.setVisible(true);

        scrollPane2.setViewportView(tabel2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void initTextFields(){

        quan = new JTextField(20);
        quan.setBounds(160, 5, 230, 30);
        quan.setColumns(10);

        dim = new JTextField(20);
        dim.setBounds(700, 5, 50, 30);
        dim.setColumns(10);

        top = new JTextField(20);
        top.setBounds(950, 5, 50, 30);
        top.setColumns(10);

        idd = new JTextField(20);
        idd.setBounds(160, 55, 230, 30);
        idd.setColumns(10);

        type = new JTextField(20);
        type.setBounds(160, 125, 230, 30);
        type.setColumns(10);

        price = new JTextField(20);
        price.setBounds(160, 195, 230, 30);
        price.setColumns(10);

        sweeteners = new JTextField(20);
        sweeteners.setBounds(160, 265, 230, 30);
        sweeteners.setColumns(10);

        rate = new JTextField(20);
        rate.setBounds(160, 335, 230, 30);
        rate.setColumns(10);

        commentsArea = new JTextArea();
        commentsArea.setColumns(10);
        commentsArea.setTabSize(100);
        commentsArea.setBounds(270, 400, 150, 110);
    }

    public void initButtons() {

        Type = makeButton("Type");
        Type.setBounds(20, 400, 100, 40);

        Price = makeButton("Price");
        Price.setBounds(20, 450, 100, 40);

        Sweets = makeButton("Sweets");
        Sweets.setBounds(20, 500, 100, 40);

        buy = makeButton("Buy");
        buy.setBounds(150, 400, 100, 40);

        view = makeButton("View");
        view.setBounds(150, 450, 100, 40);

        back = makeButton("Back");
        back.setBounds(150, 500, 100, 40);

        reset = makeButton("Reset");
        reset.setBounds(150, 550, 100, 40);

        rateStar = makeButton("Rating");
        rateStar.setBounds(20, 550, 100, 40);

        addfav = makeButton("Add Favourite");
        addfav.setBounds(20, 600, 140, 40);

        removeFav = makeButton("Remove Favourite");
        removeFav.setBounds(180, 600, 140, 40);

        addToCart = makeButton("Add to Cart");
        addToCart.setBounds(350, 600, 140, 40);

        addComm = makeButton("Add Comm");
        addComm.setBounds(1030, 550, 140, 40);

        viewComms = makeButton("View Comms");
        viewComms.setBounds(1030, 600, 140, 40);

        like = makeButton("Like");
        like.setBounds(300, 550, 100, 40);

        dislike = makeButton("Dislike");
        dislike.setBounds(450, 550, 100, 40);

    }

    public void initLabels(){

        quantity = makeLabel("QUANTITY: ");
        quantity.setBounds(25, 5, 500, 40);

        dimension = makeLabel("DIMENSION: ");
        dimension.setBounds(540, 5, 500, 40);

        topping = makeLabel("TOPPING: ");
        topping.setBounds(800, 5, 500, 40);

        n = makeLabel("ID: ");
        n.setBounds(45, 50, 500, 40);

        c = makeLabel("TYPE: ");
        c.setBounds(35, 120, 500, 40);

        p = makeLabel("PRICE: ");
        p.setBounds(40, 190, 500, 40);

        a = makeLabel("SWEETENERS: ");
        a.setBounds(7, 260, 500, 40);

        ratingLabel = makeLabel("RATING: ");
        ratingLabel.setBounds(20, 330, 500, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(170, 130, 1200, 650);
        panel.setBackground(new Color(240, 19, 56));

        initButtons();
        initLabels();
        initTextFields();

        panel.add(Type);
        panel.add(Price);
        panel.add(Sweets);
        panel.add(buy);
        panel.add(view);
        panel.add(back);
        panel.add(reset);
        panel.add(rateStar);
        panel.add(addfav);
        panel.add(removeFav);
        panel.add(addToCart);
        panel.add(addComm);
        panel.add(viewComms);
        panel.add(like);
        panel.add(dislike);
        panel.add(type);
        panel.add(price);
        panel.add(sweeteners);
        panel.add(rate);
        panel.add(commentsArea);
        panel.add(idd);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);
        panel.add(ratingLabel);
        panel.add(quan);
        panel.add(quantity);
        panel.add(dim);
        panel.add(dimension);
        panel.add(top);
        panel.add(topping);


        ArrayList<Cookie> list =  new ArrayList<Cookie>();
        tabel = makeTable(list);
        processTable();
        panel.add(scrollPane);

        ArrayList<Comment> list2 =  new ArrayList<Comment>();
        tabel2 = makeTableComment(list2);
        processTable2();
        panel.add(scrollPane2);



    }

    public void initFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1000));
        frame.add(panel);


        ImageIcon icon=new ImageIcon("cook.jpg");
        JLabel img=new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);
    }

    public void initPage(){

        initPanel();
        initFrame();
    }

    public CookieVisitView() throws SQLException {

        initPage();
        initializeRatingStar();


        for(int i = 0;i < buttons.length;i++) {
            buttons[i] = createJButton(starUnselectedIcon);
            buttons[i].setBounds(590 + 80 * i, 560, 80, 80);

            JToggleButton toggleButton = buttons[i];

            int finalI = i;
            ratings[i] = finalI + 1;//procesare cookie id

            ActionListener boardListener = new ActionListener (){
                public void actionPerformed(ActionEvent e){

                    int id = Integer.parseInt(idd.getText());
                    CookieService cookieService = new CookieService();
                    Cookie cookie = cookieService.findCookieById(id);

                    AbstractButton abstractButton =
                            (AbstractButton) e.getSource();

                    boolean selected = abstractButton.getModel().isSelected();

                    if (selected) {
                        toggleButton.setIcon(starSelectedIcon);
                       // cookie.setRating(ratings[finalI]);
                        Double myRating = cookie.getRating();
                        myRating = (myRating + ratings[finalI]) / 2;
                        cookieService.modifyCookie2(cookie.getCookieId(), cookie.getType(), cookie.getPrice(), cookie.getQuantityOfSweeteners(), myRating);
                        System.out.println("rating = " + myRating);
                    }

                    if (!selected) {
                        toggleButton.setIcon(starUnselectedIcon);
                    }
                    System.out.println("Action - selected=" + selected + "\n");
                }
            };

            buttons[i].addActionListener(boardListener);

            panel.add(buttons[i]);
            frame.add(panel);
            frame.setVisible(true);
        }

        Type.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();
                String typeC = type.getText();

                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieByType(typeC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        Price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();
                Double priceC = Double.parseDouble(price.getText());

                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieByPrice(priceC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ;

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        Sweets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();
                Double sweetC = Double.parseDouble(sweeteners.getText());

                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieBySweets(sweetC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        addfav.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

               // int id = Integer.parseInt(idd.getText());

               // CookieService cookieService = new CookieService();
               // Cookie cookie = cookieService.findCookieById(id);
               // cookieService.addFavouriteCookie(Login.idCurrentClient, id);

                String typeC = type.getText();

                ClientService clientService = new ClientService();
                Client client = clientService.findClientById(Login.idCurrentClient);//
                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieByType(typeC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                FavouriteService favouriteService = new FavouriteService();

               // FavouriteCookie favouriteCookie = new FavouriteCookie(client.getClientName(), cookie.getType());

                try {
                    favouriteService.addFavourite(client.getClientName(), cookie.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        removeFav.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                // int id = Integer.parseInt(idd.getText());

                // CookieService cookieService = new CookieService();
                // Cookie cookie = cookieService.findCookieById(id);
                // cookieService.addFavouriteCookie(Login.idCurrentClient, id);

                String typeC = type.getText();

                ClientService clientService = new ClientService();
                Client client = clientService.findClientById(Login.idCurrentClient);//
                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieByType(typeC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                FavouriteService favouriteService = new FavouriteService();

                // FavouriteCookie favouriteCookie = new FavouriteCookie(client.getClientName(), cookie.getType());

                try {
                    favouriteService.removeFavourite(client.getClientName(), cookie.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });


        rateStar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();
                Double ratingS = Double.parseDouble(rate.getText());

                CookieService cookieService = new CookieService();
                Cookie cookie = null;
                try {
                    cookie = cookieService.findCookieByRating(ratingS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.add(cookie);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        addComm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Comment> list2 = new ArrayList<Comment>();

                String typeC = type.getText();
                ClientService clientService = new ClientService();
                Client client = clientService.findClientById(Login.idCurrentClient);
                String content = commentsArea.getText();

                CommentService commentService = new CommentService();
                Comment comment = new Comment(typeC, client.getClientName(), content);
                try {
                    commentService.addComment(typeC, client.getClientName(), content);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                System.out.println(comment);

                list2.add(comment);

                tabel2=makeTableComment(list2);
                processTable2();
                panel.add(scrollPane2);
                frame.setVisible(true);
            }
        });

        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quan.getText());
                String typeC = type.getText();
                 dimm = dim.getText();
                 topp = top.getText();

                ShoppingCartService shoppingCartService = new ShoppingCartService();
                Date thisDate = new Date(System.currentTimeMillis());

                ClientService clientService = new ClientService();
                Client client = clientService.findClientById(Login.idCurrentClient);

                try {
                    shoppingCartService.addToCart(myCookies, client, typeC, quantity, dimm, topp);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                JOptionPane.showMessageDialog(LoginView.frame, client.getClientName() + "added to cart " + quantity + " pieces of " + typeC);

                System.out.println("my cookies " + myCookies);

            }
        });

        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                ClientService clientService = new ClientService();
                Client client = clientService.findClientById(Login.idCurrentClient);

                ShoppingCartService shoppingCartService = new ShoppingCartService();
                try {
                    shoppingCartService.buyCookies(client, myCookies, dimm, topp);
                    JOptionPane.showMessageDialog(LoginView.frame, client.getClientName() + "   bought  cookies");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });


        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> arrayList = new ArrayList<>();

                CookieService cookieService = new CookieService();
                arrayList = cookieService.findAllCookies();

                tabel = makeTable(arrayList);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }

        });

        viewComms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Comment> arrayList = new ArrayList<>();

                CommentService commentService = new CommentService();
                arrayList = commentService.findAllComments();

                tabel2 = makeTableComment(arrayList);
                processTable2();
                panel.add(scrollPane2);
                frame.setVisible(true);
            }
        });

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Comment> list2 = new ArrayList<>();

                int id = Integer.parseInt(idd.getText());//id-ul comment-ului
                CommentService commentService = new CommentService();
                Comment comment = commentService.findCommentById(id);

                comment.setLikeCount(comment.getLikeCount() + 1);
                try {
                    commentService.updateComment(id, comment.getTypeCookie(), comment.getClientName(), comment.getContent(), comment.getLikeCount());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                list2.add(comment);

                tabel2=makeTableComment(list2);
                processTable2();
                panel.add(scrollPane2);
                frame.setVisible(true);
            }
        });

        dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Comment> list2 = new ArrayList<>();

                int id = Integer.parseInt(idd.getText());//id-ul comment-ului
                CommentService commentService = new CommentService();
                Comment comment = commentService.findCommentById(id);

                comment.setLikeCount(comment.getLikeCount() - 1);
                try {
                    commentService.updateComment(id, comment.getTypeCookie(), comment.getClientName(), comment.getContent(), comment.getLikeCount());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                list2.add(comment);

                tabel2=makeTableComment(list2);
                processTable2();
                panel.add(scrollPane2);
                frame.setVisible(true);
            }
        });


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                frame.setVisible(false);
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                type.setText("");
                idd.setText("");
                sweeteners.setText("");
                price.setText("");

                frame.setVisible(true);
            }
        });
    }

}
