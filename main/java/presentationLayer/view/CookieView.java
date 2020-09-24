package presentationLayer.view;

import client.CookieService;
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

public class CookieView implements ICookieView {

    private AdminTasks adminTasks;
    public static JFrame frame;
    public static DefaultTableModel model;
    public JScrollPane scrollPane;
    public JTable tabel;
    public JPanel panel;
    public JLabel n, c, p, a, s;
    public  JButton add, update, delete, view, withdraw, back, reset, updateStock;
    JTextField idd, type, price, sweeteners, stock, rating;

    public static JToggleButton[] buttons;
    public static ButtonGroup btnGroup = null;
    public static int[] ratings;
    public static ImageIcon starSelectedIcon, starUnselectedIcon;

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
        table.setBackground(new Color(255, 153, 153));

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
            table.getColumnModel().getColumn(i).setMinWidth(150);

        return table;

    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 25));

        return label;
    }

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setForeground(Color.white);
        button.setBackground(new Color(204, 102, 0));
        button.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

        return button;
    }

    public void processTable(){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(410, 80, 700, 500);
        tabel.setBounds(70, 40, 700, 500);
        tabel.setVisible(true);

        scrollPane.setViewportView(tabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void initTextFields(){

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

        stock = new JTextField(20);
        stock.setBounds(160, 335, 230, 30);
        stock.setColumns(10);
    }

    public void initButtons(){

        add = makeButton("Add");
        add.setBounds(70, 450, 100, 40);

        update = makeButton("Update");
        update.setBounds(70, 550, 100, 40);

        delete = makeButton("Delete");
        delete.setBounds(70, 650, 100, 40);

        view = makeButton("View");
        view.setBounds(230, 450, 100, 40);

        back = makeButton("Back");
        back.setBounds(230, 550, 100, 40);

        reset = makeButton("Reset");
        reset.setBounds(230, 650, 100, 40);

        updateStock = makeButton("Update Stock");
        updateStock.setBounds(340, 650, 130, 40);
    }

    public void initLabels(){

        n = makeLabel("ID: ");
        n.setBounds(45, 50, 500, 40);

        c = makeLabel("TYPE: ");
        c.setBounds(25, 120, 500, 40);

        p = makeLabel("PRICE: ");
        p.setBounds(45, 190, 500, 40);

        a = makeLabel("SWEETENERS: ");
        a.setBounds(9, 260, 500, 40);

        s = makeLabel("STOCK: ");
        s.setBounds(9, 330, 500, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(170, 130, 1130, 705);
        panel.setBackground(new Color(255, 153, 153));

        initButtons();
        initLabels();
        initTextFields();

        panel.add(add);
        panel.add(update);
        panel.add(delete);
        panel.add(view);
        panel.add(back);
        panel.add(reset);
        panel.add(updateStock);
        panel.add(type);
        panel.add(price);
        panel.add(sweeteners);
        panel.add(stock);
        panel.add(idd);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);
        panel.add(s);


        ArrayList<Cookie> list =  new ArrayList<Cookie>();
        tabel = makeTable(list);
        processTable();
        panel.add(scrollPane);

    }

    public void initFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1000));
        frame.add(panel);

        ImageIcon icon=new ImageIcon("cookies.jpg");
        JLabel img=new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);
    }

    public void initPage(){

        initPanel();
        initFrame();
    }

    public CookieView() throws SQLException {

        initPage();
        initializeRatingStar();

        for(int i = 0;i < buttons.length;i++) {
            buttons[i] = createJButton(starUnselectedIcon);
            buttons[i].setBounds(590 + 80 * i, 590, 80, 80);

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

                        Double myRating = Double.valueOf(ratings[finalI]);
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



        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                 String typeC = type.getText();
                 Double priceC = Double.parseDouble(price.getText());
                 Double sweetC = Double.parseDouble(sweeteners.getText());
                int stockC = Integer.parseInt(stock.getText());

                Cookie cookie = new Cookie(typeC, priceC, sweetC, stockC);
                list.add(cookie);

                CookieService cookieService = new CookieService();
                try {
                    cookieService.addCookie(typeC, priceC, sweetC, stockC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);

            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                int idC = Integer.parseInt(idd.getText());
                String typeC = type.getText();
                Double priceC = Double.parseDouble(price.getText());
                Double sweetC = Double.parseDouble(sweeteners.getText());

                int st = Integer.parseInt(stock.getText());

                CookieService cookieService = new CookieService();
                Cookie cookie = cookieService.findCookieById(idC);

                //cookieService.modifyCookie2(idC, cookie.getType(), cookie.getPrice(), cookie.getQuantityOfSweeteners(),cookie.getRating());

                try {
                    cookieService.modifyCookie(idC, typeC, priceC, sweetC,  cookie.getStock());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                tabel=makeTable(list);
                processTable();

                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        updateStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                int idC = Integer.parseInt(idd.getText());
                int st = Integer.parseInt(stock.getText());

                CookieService cookieService = new CookieService();
                Cookie cookie = cookieService.findCookieById(idC);

                try {
                    cookieService.updateStock(idC, cookie.getType(), cookie.getPrice(), cookie.getQuantityOfSweeteners(),cookie.getRating(), st);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                tabel=makeTable(list);
                processTable();

                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Cookie> list = new ArrayList<Cookie>();

                int idC = Integer.parseInt(idd.getText());
                String typeC = type.getText();
                Double priceC = Double.parseDouble(price.getText());
                Double sweetC = Double.parseDouble(sweeteners.getText());

                Cookie c1 = null;
                CookieService cookieService = new CookieService();
                try {
                    cookieService.removeCookie(idC);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                list.remove(c1);
                tabel=makeTable(list);

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
        @Override
        public String getCookieType() {
            if(type.getText() != null){
                return type.getText();
            };
            return null;
        }

        @Override
        public Double getCookiePrice() {
            if( Double.parseDouble(price.getText()) != 0.0) {
                return Double.parseDouble(price.getText());
            };
            return null;
        }

        @Override
        public Double getCookieSweets() {
            if( Double.parseDouble(sweeteners.getText()) != 0.0){
                return Double.parseDouble(price.getText());
            }
            return null;

        }

        @Override
        public int getCookieStock() {
            if(Integer.parseInt(stock.getText()) != 0){
                return Integer.parseInt(stock.getText());
            }
            return 0;
        }

        @Override
        public Double getCookieRating() {
            if(Double.parseDouble(rating.getText()) != 0.0){
                return  Double.parseDouble(rating.getText());
            }
            return null;
        }


}
