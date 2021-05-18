package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AppMainWindow
{
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton clientButton;
    private JButton adminButton;
    private JButton employeeButton;

    /**
     * Constructor that initializes the main window's appearance
     */
    public AppMainWindow()
    {
        mainFrame = new JFrame("Food Delivery Management System");

        mainPanel = new JPanel();

        clientButton = new JButton("Client");
        adminButton = new JButton("Admin");
        employeeButton = new JButton("Employee");

        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        mainPanel.setLayout(new GridLayout());

        mainPanel.add(clientButton);
        mainPanel.add(new JPanel());
        mainPanel.add(adminButton);
        mainPanel.add(new JPanel());
        mainPanel.add(employeeButton);

        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void addClientButtonPressedActionListener(ActionListener actionListener)
    {
        clientButton.addActionListener(actionListener);
    }

    public void addAdminButtonPressedActionListener(ActionListener actionListener)
    {
        adminButton.addActionListener(actionListener);
    }

    public void addEmployeeButtonPressedActionListener(ActionListener actionListener)
    {
        employeeButton.addActionListener(actionListener);
    }
}
