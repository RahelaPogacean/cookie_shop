package presentationLayer.view;

import presentationLayer.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class LoginView extends JFrame implements ILoginView {

    public static JFrame frame;
    public JPanel panel;
    public JLabel  userLabel, passLabel;
    public JButton adminButton, userButton, registerButton;

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    String username, password;


    public JButton makeButton(String title){

        JButton thisButton = new JButton(title);
        thisButton.setFont(new Font("Times New Roman", 1, 20));
        thisButton.setBackground(Color.white);

        return thisButton;
    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        label.setForeground(Color.white);

        return label;
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1000));
        frame.add(panel);

        ImageIcon icon=new ImageIcon("firstt.jpg");
        JLabel img=new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);


    }
    public void initPanel(){

        panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        panel.setBounds(360,370, 650, 330);

        panel.setBackground(Color.pink);

        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(usernameTextField);
        panel.add(passwordTextField);
        panel.add(registerButton);
        panel.add(adminButton);
        panel.add(userButton);
    }

    public void initButtons(){

        adminButton = makeButton("Administrator");
        adminButton.setBounds(100, 60, 180, 40);

        userButton = makeButton("User");
        userButton.setBounds(360, 60, 180, 40);

        registerButton = makeButton("<<No account? Create one!>>");
        registerButton.setBounds(120, 260, 400, 30);
    }

    public void initLabels(){


        userLabel = makeLabel("username");
        userLabel.setBounds(130, 120, 100, 40);

        passLabel = makeLabel("password");
        passLabel.setBounds(130, 170, 100, 40);
    }

    public void initTextFields(){

        usernameTextField = new JTextField(10);
        usernameTextField.setBounds(290, 130, 200, 25);
        usernameTextField.setColumns(10);

        passwordTextField = new JPasswordField(10);
        passwordTextField.setBounds(290, 180, 200, 25);
        passwordTextField.setColumns(10);
    }


    public void initPage(){

        initButtons();
        initLabels();
        initTextFields();
        initPanel();
        initFrame();
    }

    public LoginView() throws IOException {

        initPage();

        LoginController loginController = new LoginController(this);

        userButton.addActionListener(e -> {


            try {
                loginController.userLogin();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }


        });

        adminButton.addActionListener(e -> {

            try {
                loginController.adminLogin();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });


        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    new RegisterView();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            });



        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
    }

    @Override
    public void showAdminView() {
        new AdminTasks();
    }

    @Override
    public void showRegularView() throws SQLException {
        new CookieVisitView();
    }

    @Override
    public String getUsername() {
        return usernameTextField.getText();
    }

    @Override
    public String getPassword() {
        return passwordTextField.getText();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}

