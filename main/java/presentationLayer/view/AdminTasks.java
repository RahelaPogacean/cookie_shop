package presentationLayer.view;

import businessLayer.generateReports.ConcreteGenerator;
import businessLayer.generateReports.Report;
import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class AdminTasks extends JFrame implements IAdminTasksView {

    public static int noOfDays;

    public LoginView login;
    public JFrame frame;
    JLabel page;
    JTextField genReport, xDays;
    JButton clientss, cookiess, reports, back;
    public JPanel panel;

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setFont(new Font("Times New Roman", 1, 20));
        button.setBackground(Color.white);

        return button;
    }

    public void initLabel(){

        page = new JLabel("The current page is assigned \n" +
                "  to the admin.");
        page.setBounds(130, 10, 500, 100);
        page.setFont(new Font("Times New Roman", Font.ITALIC, 24));
        page.setForeground(Color.white);


        genReport = new JTextField(20);
        genReport.setBounds(120, 200, 200, 40);
        genReport.setColumns(10);

        xDays = new JTextField(20);
        xDays.setBounds(120, 290, 200, 40);
        xDays.setColumns(10);
    }

    public void initButtons(){

        clientss = makeButton("Clients");
        clientss.setBounds(120, 110, 200, 40);

        cookiess = makeButton("Cookies");
        cookiess.setBounds(360, 110, 200, 40);

        reports = makeButton("Reports");
        reports.setBounds(360, 200, 200, 40);

        back = makeButton("Back");
        back.setBounds(360, 290, 200, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        panel.setBounds(130, 0, 720, 400);

        panel.add(clientss);
        panel.add(cookiess);
        panel.add(back);
        panel.add(page);
        panel.add(genReport);
        panel.add(xDays);
        panel.add(reports);
        panel.setBackground(new Color(255, 102, 178));
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1000, 1288));

        frame.add(panel);

        ImageIcon icon=new ImageIcon("admin_tasks.jpg");
        JLabel img=new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);
    }

    public void initPage(){

        initButtons();
        initLabel();
        initPanel();
        initFrame();
    }

    public AdminTasks(){

        initPage();

        clientss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    new ClientView();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
                try {
                    showClientView();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        cookiess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    new CookieView();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
                try {
                    showCookieView();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typeReport = genReport.getText();
                noOfDays = Integer.parseInt(xDays.getText());

                ConcreteGenerator concreteGenerator = new ConcreteGenerator();
                try {
                    Report report = concreteGenerator.factoryMethod(typeReport);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (DocumentException ex) {
                    ex.printStackTrace();
                }

            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                frame.setVisible(false);
            }
        });
    }

    @Override
    public void showCookieView() throws SQLException {
        new CookieView();
    }

    @Override
    public void showClientView() throws SQLException {
        new ClientView();

    }

    @Override
    public void showErrorMessage(String message) {

        JOptionPane.showMessageDialog(this, message);
    }

}
