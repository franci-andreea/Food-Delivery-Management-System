package presentation;

import bll.DeliveryService;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInWindow
{
    private JFrame logInFrame = new JFrame("Log In");

    private JPanel logInPanel;

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton logInButton;
    private JButton registerButton;

    private String ADMIN_USER = "admin";
    private String ADMIN_PASS = "admin";

    private String EMP_USER = "employee";
    private String EMP_PASs = "employee";

    /**
     * Constructor that builds the log in window
     */
    public LogInWindow()
    {
        logInFrame.setSize(200, 100);

        logInPanel = new JPanel();

        logInPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        logInPanel.setLayout(new GridLayout(3, 2, 10, 10));

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        logInButton = new JButton("Log In");
        registerButton = new JButton("New Client? Register here");

        logInPanel.add(usernameLabel);
        logInPanel.add(usernameField);
        logInPanel.add(passwordLabel);
        logInPanel.add(passwordField);
        logInPanel.add(logInButton);
        logInPanel.add(registerButton);

        logInFrame.setLayout(new FlowLayout());
        logInFrame.add(logInPanel);
        logInFrame.setVisible(true);
        logInFrame.setLocationRelativeTo(null);
        logInFrame.pack();
    }

    /**
     * methods that initializes the buttons for the log in window
     * @param deliveryService
     */
    public void initializeClientLogInButton(DeliveryService deliveryService)
    {
        this.addLogInButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("LOG IN WINDOW: " + deliveryService.getClientsList());

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int found = 0;

                for(Client client : deliveryService.getClientsList())
                {
                    if(username.equals(client.getUsername()) && password.equals(client.getPassword()))
                    {
                        found = 1;

                        ClientWindow clientWindow = new ClientWindow();

                        logInFrame.setVisible(false);

                        clientWindow.initializeViewProductsButton(deliveryService);
                        clientWindow.initializeSearchButton(deliveryService);
                        clientWindow.initializeAddProductToOrderButton(deliveryService);
                        clientWindow.initializeAddProductFromSearchToOrderButton(deliveryService);
                        clientWindow.initializePlaceOrderButton(deliveryService, client.getClientID());

                    }
                }

                if(found == 0)
                {
                    JOptionPane.showMessageDialog(logInFrame, "Incorrect username or password");
                }
            }
        });
    }

    public void initializeAdminLogInButton(DeliveryService deliveryService)
    {
        this.addLogInButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if(username.equals(ADMIN_USER) && password.equals(ADMIN_PASS))
                {
                    AdministratorWindow administratorWindow = new AdministratorWindow();

                    logInFrame.setVisible(false);

                    administratorWindow.initializeImportProductsButton(deliveryService);
                    administratorWindow.initializeAddProductButton(deliveryService);
                    administratorWindow.initializeEditProductButton(deliveryService);
                    administratorWindow.initializeDeleteProductButton(deliveryService);
                    administratorWindow.initializeAddBaseProductToCompositeProductButton(deliveryService);
                    administratorWindow.initializeCreateCompositeProductButton(deliveryService);
                    administratorWindow.initializeGenerateReportsButton(deliveryService);
                }
                else
                {
                    JOptionPane.showMessageDialog(logInFrame, "Invalid username or password");
                }
            }
        });
    }

    public void initializeEmployeeLogInButton(DeliveryService deliveryService)
    {
        this.addLogInButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if(username.equals(EMP_USER) && password.equals(EMP_PASs))
                {
                    EmployeeWindow employeeWindow = new EmployeeWindow();
                    logInFrame.setVisible(false);
                    //trebuie sa pregatesti butoanele aici
                }
                else
                {
                    JOptionPane.showMessageDialog(logInFrame, "Invalid username or password");
                }
            }
        });
    }

    public void initializeRegisterButton(DeliveryService deliveryService)
    {
        this.addRegisterButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RegisterWindow registerWindow = new RegisterWindow();
                logInFrame.setVisible(false);

                registerWindow.initializeCreateAccountButton(deliveryService);
            }
        });
    }

    public void addLogInButtonPressedActionListener(ActionListener actionListener)
    {
        logInButton.addActionListener(actionListener);
    }

    public void addRegisterButtonPressedActionListener(ActionListener actionListener)
    {
        registerButton.addActionListener(actionListener);
    }

    public JFrame getLogInFrame()
    {
        return logInFrame;
    }
}
