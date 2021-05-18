package presentation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EmployeeWindow implements PropertyChangeListener
{
    private JFrame employeeFrame = new JFrame("Employee Section");

    private JPanel notificationPanel;
    private JPanel ordersPanel;

    private JLabel notificationLabel;
    private JButton viewOrdersButton;

    private JScrollPane orders;

    private JLabel emptyLabel = new JLabel(" ");
    private JLabel emptyLabel1 = new JLabel(" ");

    /**
     * Constructor that builds the employee window
     */
    public EmployeeWindow()
    {

        notificationPanel = new JPanel();
        ordersPanel = new JPanel();

        notificationPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.pink));
        ordersPanel.setBorder(BorderFactory.createTitledBorder("Orders"));

        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        ordersPanel.setLayout(new FlowLayout());

        notificationLabel = new JLabel("No new order has been placed");
        viewOrdersButton = new JButton("view orders");

        notificationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emptyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emptyLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewOrdersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        notificationPanel.add(notificationLabel);
        notificationPanel.add(emptyLabel);
        notificationPanel.add(emptyLabel1);
        notificationPanel.add(viewOrdersButton);

        orders = new JScrollPane(new JTable(5, 5));

        ordersPanel.add(orders);

        employeeFrame.setLayout(new FlowLayout());
        employeeFrame.add(notificationPanel);
        employeeFrame.add(ordersPanel);
        employeeFrame.setVisible(true);
        employeeFrame.pack();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
