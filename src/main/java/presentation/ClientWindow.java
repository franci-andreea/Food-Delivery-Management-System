package presentation;

import bll.DeliveryService;
import model.Client;
import model.MenuItem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

public class ClientWindow
{
    private JFrame clientFrame = new JFrame("Client section");

    private JPanel viewProductsPanel;
    private JPanel searchProductPanel;
    private JPanel makeOrderPanel;

    private JScrollPane listOfProductsFromMenu;
    private JButton viewProductsMenuButton;
    private JButton addProductFromListToOrderButton;

    private JTextField searchProductNameField;
    private JButton searchButton;
    private JComboBox<String> productListResulted;
    private JButton addProductToOrderButton;

    private JTextArea productsAddedToCart;
    private JButton placeOrderButton;

    private List<MenuItem> orderProducts = new ArrayList<>();

    /**
     * Constructor that builds the client window
     */
    public ClientWindow()
    {
        clientFrame.setPreferredSize(new Dimension(1770, 560));

        viewProductsPanel = new JPanel();
        searchProductPanel = new JPanel();
        makeOrderPanel = new JPanel();

        viewProductsPanel.setBorder(BorderFactory.createTitledBorder("View Products"));
        searchProductPanel.setBorder(BorderFactory.createTitledBorder("Search Product"));
        makeOrderPanel.setBorder(BorderFactory.createTitledBorder("Order Cart"));

        viewProductsPanel.setLayout(new BoxLayout(viewProductsPanel, BoxLayout.Y_AXIS));
        searchProductPanel.setLayout(new GridLayout(2, 2, 10, 10));
        makeOrderPanel.setLayout(new BoxLayout(makeOrderPanel, BoxLayout.Y_AXIS));

        listOfProductsFromMenu = new JScrollPane();
        viewProductsMenuButton = new JButton("view menu's products");
        addProductFromListToOrderButton = new JButton("add product");
        listOfProductsFromMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        addProductFromListToOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewProductsMenuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewProductsPanel.add(listOfProductsFromMenu);
        viewProductsPanel.add(addProductFromListToOrderButton);
        viewProductsPanel.add(viewProductsMenuButton);

        searchProductNameField = new JTextField();
        searchButton = new JButton("search");
        productListResulted = new JComboBox();
        addProductToOrderButton = new JButton("add to cart");
        searchProductPanel.add(searchProductNameField);
        searchProductPanel.add(searchButton);
        searchProductPanel.add(productListResulted);
        searchProductPanel.add(addProductToOrderButton);

        productsAddedToCart = new JTextArea(10, 20);
        placeOrderButton = new JButton("place order");
        productsAddedToCart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        placeOrderButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        makeOrderPanel.add(productsAddedToCart);
        makeOrderPanel.add(placeOrderButton);

        clientFrame.setLayout(new FlowLayout());
        clientFrame.add(viewProductsPanel);
        clientFrame.add(searchProductPanel);
        clientFrame.add(makeOrderPanel);
        clientFrame.setVisible(true);
        clientFrame.pack();
    }

    /**
     * methods that initialize the buttons from the client window
     */
    public void initializeViewProductsButton(DeliveryService deliveryService)
    {
        this.addViewProductsButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    viewProductsPanel.removeAll();
                    listOfProductsFromMenu = new JScrollPane(deliveryService.createTable(deliveryService.getMenuItems()));
                    viewProductsPanel.add(listOfProductsFromMenu);
                    viewProductsPanel.add(addProductFromListToOrderButton);
                    viewProductsPanel.add(viewProductsMenuButton);
                    viewProductsPanel.revalidate();
                    viewProductsPanel.repaint();
                } catch (IllegalAccessException illegalAccessException)
                {
                    illegalAccessException.printStackTrace();
                }
            }
        });
    }

    public void initializeSearchButton(DeliveryService deliveryService)
    {
        this.addSearchButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String keywordIntroduced = searchProductNameField.getText();
                List<MenuItem> results = deliveryService.findProductsByKeyword(keywordIntroduced);
                List<String> resultsNames = new ArrayList<>();
                for(MenuItem menuItem : results)
                {
                   resultsNames.add(menuItem.getName());
                }
                productListResulted.setModel(new DefaultComboBoxModel(resultsNames.toArray()));
                productListResulted.revalidate();
                searchProductPanel.repaint();
                clientFrame.getContentPane().revalidate();
                clientFrame.getContentPane().repaint();
            }
        });
    }

    public void initializeAddProductToOrderButton(DeliveryService deliveryService)
    {
        this.addAddProductToOrderButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
    }

    public void initializeAddProductFromSearchToOrderButton(DeliveryService deliveryService)
    {
        this.addAddProductFromSearchToOrderButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedName = productListResulted.getSelectedItem().toString();
                orderProducts.add(deliveryService.findProductByName(selectedName));
                productsAddedToCart.append(selectedName);
                productsAddedToCart.append("\n");
            }
        });
    }

    public void initializePlaceOrderButton(DeliveryService deliveryService, int clientID)
    {
        this.addPlaceOrderButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client activeClient = deliveryService.findClientById(clientID);

                try
                {
                    for(MenuItem menuItem : orderProducts)
                    {

                        int increment = menuItem.getCount();
                        increment++;
                        menuItem.setCount(increment);
                    }

                    int ordersNumber = activeClient.getNumberOfOrders();
                    ordersNumber++;
                    activeClient.setNumberOfOrders(ordersNumber);

                    deliveryService.createOrder(activeClient, orderProducts);

                    JOptionPane.showMessageDialog(clientFrame, "Order placed successfully!");

                } catch (IOException ioException)
                {
                    JOptionPane.showMessageDialog(clientFrame, "Error while processing the order!");
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void addAddProductToOrderButtonPressedActionListener(ActionListener actionListener)
    {
        addProductFromListToOrderButton.addActionListener(actionListener);
    }

    public void addSearchButtonPressedActionListener(ActionListener actionListener)
    {
        searchButton.addActionListener(actionListener);
    }

    public void addViewProductsButtonPressedActionListener(ActionListener actionListener)
    {
        viewProductsMenuButton.addActionListener(actionListener);
    }

    public void addAddProductFromSearchToOrderButtonPressedActionListener(ActionListener actionListener)
    {
        addProductToOrderButton.addActionListener(actionListener);
    }

    public void addPlaceOrderButtonPressedActionListener(ActionListener actionListener)
    {
        placeOrderButton.addActionListener(actionListener);
    }
}
