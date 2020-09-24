package presentationLayer.view;

import businessLayer.service.ClientService;
import model.Client;

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

public class ClientView implements IClientView{

    private AdminTasks adminTasks;
    public static JFrame frame;
    public static DefaultTableModel model;
    public JScrollPane scrollPane;
    public JTable tabel;
    public JPanel panel;
    public JLabel n, c, p, a, am;
    public  JButton add, update, delete, view, withdraw, back, reset;
    JTextField idd, name, pass, email, ammount;


    public static JTable makeTable(ArrayList<Client> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("Id");
        column.add("Name");
        column.add("Password");
        column.add("Email");
        column.add("Ammount");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());
        //model.setColumnIdentifiers((Vector) Arrays.asList(column));//

        Object[] v = new Object[5];
        JTable table = new JTable();
        table.setBackground(new Color(255, 51, 153));

        for (Client c : list) {
            v[0] = c.getClientId();
            v[1] = c.getClientName();
            v[2] = c.getClientPassword();
            v[3] = c.getClientEmail();
            v[4] = c.getClientAmmount();
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
        table.setRowHeight(20);

        for (int i = 1; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setMinWidth(130);

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
        button.setBackground(new Color(153, 0, 76));
        button.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

        return button;
    }

    public void processTable(){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(440, 60, 800, 700);
        tabel.setBounds(70, 40, 700, 500);
        tabel.setVisible(true);
        scrollPane.setBackground(new Color(255, 51, 153));

        scrollPane.setViewportView(tabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void initTextFields(){

        idd = new JTextField(15);
        idd.setBounds(160, 55, 230, 30);
        idd.setColumns(10);

        name = new JTextField(15);
        name.setBounds(160, 125, 230, 30);
        name.setColumns(10);

        pass = new JTextField(15);
        pass.setBounds(160, 195, 230, 30);
        pass.setColumns(10);

        email = new JTextField(15);
        email.setBounds(160, 265, 230, 30);
        email.setColumns(10);

        ammount = new JTextField(15);
        ammount.setBounds(160, 335, 230, 30);
        ammount.setColumns(10);
    }

    public void initButtons(){

        add = makeButton("Add");
        add.setBounds(90, 450, 100, 40);

        update = makeButton("Update");
        update.setBounds(90, 550, 100, 40);

        delete = makeButton("Delete");
        delete.setBounds(90, 650, 100, 40);

        view = makeButton("View");
        view.setBounds(270, 450, 100, 40);

        back = makeButton("Back");
        back.setBounds(270, 550, 100, 40);

        reset = makeButton("Reset");
        reset.setBounds(270, 650, 100, 40);
    }

    public void initLabels(){

        n = makeLabel("ID: ");
        n.setBounds(45, 50, 500, 40);

        c = makeLabel("NAME: ");
        c.setBounds(25, 120, 500, 40);

        p = makeLabel("PASSWORD: ");
        p.setBounds(23, 190, 500, 40);

        a = makeLabel("EMAIL: ");
        a.setBounds(25, 260, 500, 40);

        am = makeLabel("AMMOUNT: ");
        am.setBounds(25, 330, 500, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(70, 120, 1300, 805);
        panel.setBackground(new Color(255, 204, 204));

        initButtons();
        initLabels();
        initTextFields();

        panel.add(add);
        panel.add(update);
        panel.add(delete);
        panel.add(view);
        panel.add(back);
        panel.add(reset);
        panel.add(name);
        panel.add(pass);
        panel.add(email);
        panel.add(ammount);
        panel.add(idd);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);
        panel.add(am);


        ArrayList<Client> list =  new ArrayList<Client>();
        tabel = makeTable(list);
        processTable();
        panel.add(scrollPane);

    }

    public void initFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1060));
        frame.add(panel);


        ImageIcon icon=new ImageIcon("clients.jpg");
        JLabel img=new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);
    }

    public void initPage(){

        initPanel();
        initFrame();
    }

    public ClientView() throws SQLException {

        initPage();


        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Client> list = new ArrayList<Client>();
                // int idC = Integer.parseInt(idd.getText());
                String nameC = name.getText();
                String passC = pass.getText();
                String emailC = email.getText();
                Double ammC = Double.parseDouble(ammount.getText());

                Client cl = new Client( nameC, passC, emailC, ammC);
                list.add(cl);//update table with new client

                ClientService clientService = new ClientService();
                try {
                    clientService.addClient(nameC, passC, emailC, ammC);
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

                ArrayList<Client> list = new ArrayList<Client>();
                int pncC = Integer.parseInt(idd.getText());

                String newName = name.getText();
                String newPass = pass.getText();
                String newEmail = email.getText();
                Double newAmm = Double.parseDouble(ammount.getText());

                ClientService clientService = new ClientService();
                try {
                    clientService.modifyClient(pncC, newName, newPass, newEmail, newAmm);
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

                ArrayList<Client> list = new ArrayList<Client>();
                int pncC = Integer.parseInt(idd.getText());

                Client c1 = null;
                ClientService clientService = new ClientService();
                try {
                    clientService.removeClient(pncC);
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

                ArrayList<Client> arrayList = new ArrayList<>();

                ClientService clientService = new ClientService();
                arrayList = clientService.findAll();

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

                name.setText("");
                idd.setText("");
                email.setText("");
                pass.setText("");
                ammount.setText("");

                frame.setVisible(true);
            }
        });


    }

    @Override
    public String getUsername() {
        return name.getText();
    }

    @Override
    public String getPassword() {
        return pass.getText();
    }

    @Override
    public String getEmail() {
        return email.getText();
    }

    @Override
    public Double getAmmount() {
        return Double.parseDouble(ammount.getText());
    }
}
