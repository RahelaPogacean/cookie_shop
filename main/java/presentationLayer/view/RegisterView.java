package presentationLayer.view;

import presentationLayer.controller.RegisterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterView extends JFrame implements IRegisterView{

    public static JFrame frame;
    public JPanel panel;
    public JLabel titleRegister, pncLabel, userLabel, passLabel, confirmPassLabel, addressLabel;
    public JButton registerButton, loginButton;

    private JTextField usernameTextField, idTextField, emailTextField;
    private JPasswordField passwordField, confirmPasswordField;


    public JLabel makeLabel(String title) {

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        label.setForeground(Color.white);
        return label;
    }

    public JButton makeButton(String title) {

        JButton button = new JButton(title);
        button.setFont(new Font("Times New Roman", 1, 20));
        button.setBackground(Color.white);

        return button;
    }

    public void initPanel() {

        panel = new JPanel();
        panel.setLayout((LayoutManager) null);
        panel.setBounds(56, 445, 460, 490);
        panel.add(pncLabel);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(confirmPassLabel);
        panel.add(addressLabel);
        panel.add(idTextField);
        panel.add(usernameTextField);
        panel.add(passwordField);
        panel.add(confirmPasswordField);
        panel.add(emailTextField);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.setBackground(new Color(255, 0, 127));
    }

    public void initFrame() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1000));
        frame.add(panel);

        ImageIcon icon = new ImageIcon("second.jpg");
        JLabel img = new JLabel(icon);
        img.add(panel);
        frame.setContentPane(img);
        frame.setVisible(true);
    }

    public void initLabels() {

        pncLabel = makeLabel(" ID");
        pncLabel.setBounds(50, 70, 100, 40);

        userLabel = makeLabel("Username");
        userLabel.setBounds(50, 120, 100, 40);

        passLabel = makeLabel("Password");
        passLabel.setBounds(50, 170, 100, 40);

        confirmPassLabel = makeLabel("Confirm Password");
        confirmPassLabel.setBounds(50, 220, 300, 40);

        addressLabel = makeLabel("Email");
        addressLabel.setBounds(50, 270, 100, 40);
    }

    public void initButtons() {

        registerButton = makeButton("Register");
        registerButton.setBounds(140, 340, 200, 30);

        loginButton = makeButton("<<Already have an account? Login!>>");
        loginButton.setBounds(55, 410, 370, 30);
    }

    public void initTextFields() {

        idTextField = new JTextField(10);
        idTextField.setBounds(240, 80, 200, 25);
        idTextField.setColumns(10);

        usernameTextField = new JTextField(10);
        usernameTextField.setBounds(240, 130, 200, 25);
        usernameTextField.setColumns(10);

        passwordField = new JPasswordField(10);
        passwordField.setBounds(240, 180, 200, 25);

        confirmPasswordField = new JPasswordField(10);
        confirmPasswordField.setBounds(240, 230, 200, 25);

        emailTextField = new JTextField(10);
        emailTextField.setBounds(240, 280, 200, 25);
        emailTextField.setColumns(10);
    }

    public void initPage() {

        initLabels();
        initButtons();
        initTextFields();
        initPanel();
        initFrame();
    }


    public RegisterView() throws SQLException {

        initPage();

        RegisterController registerController = new RegisterController(this);
        registerButton.addActionListener(e -> {
            try {
                registerController.userRegister();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginView();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
    }


        @Override
        public void showRegularView () throws SQLException {
             //new RegularView().setVisible(true);
            new CookieVisitView();//
        }

        @Override
        public String getUsername () {
            return usernameTextField.getText();
        }

        @Override
        public String getPassword () {
            return passwordField.getText();
        }

        /*@Override
        public int getIdTextField () {
            return Integer.parseInt(idTextField.getText());
        }

         */


        @Override
        public String getEmailTextField () {
            return emailTextField.getText();
        }

        @Override
        public String getConfirmPasswordField () {
            return confirmPasswordField.getText();
        }

        @Override
        public void showErrorMessage (String message){
            JOptionPane.showMessageDialog(this, message);
        }
    }

