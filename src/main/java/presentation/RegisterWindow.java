package presentation;

import bll.DeliveryService;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow
{
    private JFrame registerFrame = new JFrame("Registration Section");

    private JPanel informationPanel;
    private JPanel messagePanel;

    private JLabel realNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField realNameField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton createAccountButton;

    private JTextArea messageArea;

    /**
     * constructor that builds the register window
     */
    public RegisterWindow()
    {
        informationPanel = new JPanel();
        messagePanel = new JPanel();

        informationPanel.setBorder(BorderFactory.createTitledBorder("Introduce your new account details"));
        messagePanel.setBorder(BorderFactory.createEmptyBorder());

        informationPanel.setLayout(new GridLayout(5, 2, 15, 15));
        messagePanel.setLayout(new FlowLayout());

        realNameLabel = new JLabel("First Name & Last Name");
        realNameField = new JTextField();

        phoneNumberLabel = new JLabel("Phone number");
        phoneNumberField = new JTextField();

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        createAccountButton = new JButton("Create Account");


        informationPanel.add(realNameLabel);
        informationPanel.add(realNameField);
        informationPanel.add(phoneNumberLabel);
        informationPanel.add(phoneNumberField);
        informationPanel.add(usernameLabel);
        informationPanel.add(usernameField);
        informationPanel.add(passwordLabel);
        informationPanel.add(passwordField);
        informationPanel.add(createAccountButton);

        messageArea = new JTextArea(20, 20);

        messagePanel.add(messageArea);

        registerFrame.setLayout(new FlowLayout());
        registerFrame.add(informationPanel);
        registerFrame.add(messagePanel);
        registerFrame.setVisible(true);
        registerFrame.pack();
    }

    /**
     * methods that initialize the buttons for the register window
     */
    public void initializeCreateAccountButton(DeliveryService deliveryService)
    {
        this.addCreateAccountButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String realNameIntroduced = realNameField.getText();
                String phoneNumberIntroduced = phoneNumberField.getText();
                String usernameIntroduced = usernameField.getText();
                String passwordIntroduced = new String(passwordField.getPassword());

                for(Client client : deliveryService.getClientsList())
                {
                    if(client.getUsername().equals(usernameIntroduced))
                    {
                        JOptionPane.showMessageDialog(registerFrame, "This username is already taken, please introduce a different one");
                        return;
                    }
                }

                Client newClient = new Client(0, realNameIntroduced, usernameIntroduced, passwordIntroduced, phoneNumberIntroduced);
                deliveryService.createClientAccount(newClient);

                messageArea.append("Client " + realNameIntroduced + " has been successfully created:\n");
                messageArea.append("ID: " + newClient.getClientID() + "\n");
                messageArea.append("Phone number: " + phoneNumberIntroduced + "\n");
                messageArea.append("Username: " + usernameIntroduced + "\n");

                JOptionPane.showMessageDialog(registerFrame, "Account created successfully");

                System.out.println("REGISTER WINDOW AFTER PRESSING CREATE ACCOUNT: " + deliveryService.getClientsList());
            }
        });
    }

    public void addCreateAccountButtonPressedActionListener(ActionListener actionListener)
    {
        createAccountButton.addActionListener(actionListener);
    }
}
