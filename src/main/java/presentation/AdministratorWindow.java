package presentation;

import bll.DeliveryService;
import model.BaseProduct;
import model.CompositeProduct;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorWindow
{
    private JFrame adminFrame = new JFrame("Administrator Section");

    private JPanel manageProductsPanel;
    private JPanel composedProductsPanel;
    private JPanel generateReportPanel;

    private JButton importProductsButton;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    private JLabel productsNamesLabel = new JLabel("product name");
    private JComboBox<MenuItem> productsNames;
    private JLabel newNameLabel = new JLabel("new name");
    private JTextField newNameField;
    private JLabel ratingLabel = new JLabel("rating");
    private JTextField ratingField;
    private JLabel caloriesLabel = new JLabel("calories");
    private JTextField caloriesField;
    private JLabel proteinLabel = new JLabel("protein");
    private JTextField proteinField;
    private JLabel fatLabel = new JLabel("fat");
    private JTextField fatField;
    private JLabel sodiumLabel = new JLabel("sodium");
    private JTextField sodiumField;
    private JLabel priceLabel = new JLabel("price");
    private JTextField priceField;

    private JComboBox<MenuItem> baseProductJComboBox;
    private JButton addToComposedProductButton;
    private JTextArea listOfProducts;
    private JButton createComposedProductButton;
    private JLabel composedProductNameLabel = new JLabel("Provide a name for your new composite product");
    private JTextField nameNewComposedProduct;

    private JLabel startHour;
    private JTextField startHourField;
    private JLabel endHour;
    private JTextField endHourField;
    private JLabel numberOfTimesAProductAppears;
    private JTextField numberOfTimesAProductAppearsField;
    private JLabel numberOfOrdersTakenByClient;
    private JTextField numberOfOrdersTakenByClientField;
    private JLabel minimumValue;
    private JTextField minimumValueField;
    private JLabel theDayForTheReport;
    private JTextField theDayForTheReportField;
    private JButton generateButton;

    private List<MenuItem> baseProductsSelectedForComposite = new ArrayList<>();

    /**
     * Constructor that builds the administrator window
     */
    public AdministratorWindow()
    {
        manageProductsPanel = new JPanel();
        composedProductsPanel = new JPanel();
        generateReportPanel = new JPanel();

        manageProductsPanel.setBorder(BorderFactory.createTitledBorder("Manage Products"));
        composedProductsPanel.setBorder(BorderFactory.createTitledBorder("Composed Products"));
        generateReportPanel.setBorder(BorderFactory.createTitledBorder("Generate Reports"));

        //set up the box layout
        manageProductsPanel.setLayout(new GridLayout(10, 2, 10, 10));
        composedProductsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        generateReportPanel.setLayout(new GridLayout(0, 2, 10, 20));

        productsNames = new JComboBox();
        newNameField = new JTextField();
        ratingField = new JTextField();
        caloriesField = new JTextField();
        proteinField = new JTextField();
        fatField = new JTextField();
        sodiumField = new JTextField();
        priceField = new JTextField();

        importProductsButton = new JButton("import products");
        addProductButton = new JButton("add product");
        editProductButton = new JButton("edit product");
        deleteProductButton = new JButton("delete product");

        manageProductsPanel.add(productsNamesLabel);
        manageProductsPanel.add(productsNames);

        manageProductsPanel.add(newNameLabel);
        manageProductsPanel.add(newNameField);

        manageProductsPanel.add(ratingLabel);
        manageProductsPanel.add(ratingField);

        manageProductsPanel.add(caloriesLabel);
        manageProductsPanel.add(caloriesField);

        manageProductsPanel.add(proteinLabel);
        manageProductsPanel.add(proteinField);

        manageProductsPanel.add(fatLabel);
        manageProductsPanel.add(fatField);

        manageProductsPanel.add(sodiumLabel);
        manageProductsPanel.add(sodiumField);

        manageProductsPanel.add(priceLabel);
        manageProductsPanel.add(priceField);

        manageProductsPanel.add(importProductsButton);
        manageProductsPanel.add(addProductButton);
        manageProductsPanel.add(editProductButton);
        manageProductsPanel.add(deleteProductButton);

        baseProductJComboBox = new JComboBox<>();
        addToComposedProductButton = new JButton("add");
        listOfProducts = new JTextArea(5, 0);
        createComposedProductButton = new JButton("create");
        nameNewComposedProduct = new JTextField();
        composedProductsPanel.add(baseProductJComboBox);
        composedProductsPanel.add(addToComposedProductButton);
        composedProductsPanel.add(composedProductNameLabel);
        composedProductsPanel.add(nameNewComposedProduct);
        composedProductsPanel.add(listOfProducts);
        composedProductsPanel.add(createComposedProductButton);

        startHour = new JLabel("start hour");
        startHourField = new JTextField();
        endHour = new JLabel("end hour");
        endHourField = new JTextField();
        numberOfTimesAProductAppears = new JLabel("number of times a product appears");
        numberOfTimesAProductAppearsField = new JTextField();
        numberOfOrdersTakenByClient = new JLabel("number of orders taken by a client");
        numberOfOrdersTakenByClientField = new JTextField();
        minimumValue = new JLabel("minimum value of the order's bill");
        minimumValueField = new JTextField();
        theDayForTheReport = new JLabel("introduce the date of the day you want to generate the report (yyyyMMdd");
        theDayForTheReportField = new JTextField();
        generateButton = new JButton("generate");
        generateReportPanel.add(startHour);
        generateReportPanel.add(startHourField);
        generateReportPanel.add(endHour);
        generateReportPanel.add(endHourField);
        generateReportPanel.add(numberOfTimesAProductAppears);
        generateReportPanel.add(numberOfTimesAProductAppearsField);
        generateReportPanel.add(numberOfOrdersTakenByClient);
        generateReportPanel.add(numberOfOrdersTakenByClientField);
        generateReportPanel.add(minimumValue);
        generateReportPanel.add(minimumValueField);
        generateReportPanel.add(theDayForTheReport);
        generateReportPanel.add(theDayForTheReportField);
        generateReportPanel.add(generateButton);

        adminFrame.setLayout(new FlowLayout());
        adminFrame.add(manageProductsPanel);
        adminFrame.add(composedProductsPanel);
        adminFrame.add(generateReportPanel);
        adminFrame.setVisible(true);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.pack();
        //adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * methods to intialize the buttons for the administrator window
     */
    public void initializeImportProductsButton(DeliveryService deliveryService)
    {
        this.addImportButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    deliveryService.importProducts();
                    productsNames.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    productsNames.revalidate();
                    baseProductJComboBox.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    baseProductJComboBox.revalidate();
                    manageProductsPanel.repaint();
                    adminFrame.getContentPane().revalidate();
                    adminFrame.getContentPane().repaint();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void initializeAddProductButton(DeliveryService deliveryService)
    {
        this.addAddProductButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String newName = newNameField.getText();
                float rating = Float.parseFloat(ratingField.getText());
                int calories = Integer.parseInt(caloriesField.getText());
                int protein = Integer.parseInt(proteinField.getText());
                int fat = Integer.parseInt(fatField.getText());
                int sodium = Integer.parseInt(sodiumField.getText());
                int price = Integer.parseInt(priceField.getText());

                MenuItem newProduct = new BaseProduct(newName, rating, calories, protein, fat, sodium, price);

                try
                {
                    deliveryService.addProduct(newProduct);
                    productsNames.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    productsNames.revalidate();
                    baseProductJComboBox.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    baseProductJComboBox.revalidate();
                    manageProductsPanel.repaint();
                    adminFrame.getContentPane().revalidate();
                    adminFrame.getContentPane().repaint();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void initializeEditProductButton(DeliveryService deliveryService)
    {
        this.addEditProductButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String productToUpdateName = productsNames.getSelectedItem().toString();
                String newName = newNameField.getText();
                float rating = Float.parseFloat(ratingField.getText());
                int calories = Integer.parseInt(caloriesField.getText());
                int protein = Integer.parseInt(proteinField.getText());
                int fat = Integer.parseInt(fatField.getText());
                int sodium = Integer.parseInt(sodiumField.getText());
                int price = Integer.parseInt(priceField.getText());

                BaseProduct editedProduct = new BaseProduct(newName, rating, calories, protein, fat, sodium, price);

                deliveryService.editProduct(productToUpdateName, editedProduct);
                productsNames.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                productsNames.revalidate();
                baseProductJComboBox.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                baseProductJComboBox.revalidate();
                manageProductsPanel.repaint();
                adminFrame.getContentPane().revalidate();
                adminFrame.getContentPane().repaint();
            }
        });
    }

    public void initializeDeleteProductButton(DeliveryService deliveryService)
    {
        this.addDeleteProductButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String productToDeleteName;
                if(newNameField.getText() == null || newNameField.getText().isEmpty())
                {
                    productToDeleteName = productsNames.getSelectedItem().toString();
                }
                else
                    productToDeleteName = newNameField.getText();

                MenuItem productToDelete = deliveryService.findProductByName(productToDeleteName);

                deliveryService.deleteProduct(productToDelete);
                productsNames.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                productsNames.revalidate();
                baseProductJComboBox.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                baseProductJComboBox.revalidate();
                manageProductsPanel.repaint();
                adminFrame.getContentPane().revalidate();
                adminFrame.getContentPane().repaint();
            }
        });
    }

    public void initializeAddBaseProductToCompositeProductButton(DeliveryService deliveryService)
    {
        this.addBaseProductToCompositeProductButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String baseProductSelectedName = baseProductJComboBox.getSelectedItem().toString();
                MenuItem baseProductSelected = deliveryService.findProductByName(baseProductSelectedName);

                System.out.println("verificare : produs selectat = " + baseProductSelectedName);
                baseProductsSelectedForComposite.add(baseProductSelected);
                listOfProducts.append(baseProductSelectedName);
                listOfProducts.append("\n");
            }
        });

    }

    public void initializeCreateCompositeProductButton(DeliveryService deliveryService)
    {
        this.addCreateCompositeProductButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String compositeProductGivenName = nameNewComposedProduct.getText();
                System.out.println("VERIFICARE : nume introdus pentru compositeProduct = " + compositeProductGivenName);
                CompositeProduct newCreatedCompositeProduct = deliveryService.createCompositeProduct(compositeProductGivenName, baseProductsSelectedForComposite);

                try
                {
                    deliveryService.addProduct(newCreatedCompositeProduct);
                    listOfProducts.setText("");
                    productsNames.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    productsNames.revalidate();
                    baseProductJComboBox.setModel(new DefaultComboBoxModel(deliveryService.getMenuItemsNames().toArray()));
                    baseProductJComboBox.revalidate();
                    manageProductsPanel.repaint();
                    adminFrame.getContentPane().revalidate();
                    adminFrame.getContentPane().repaint();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void initializeGenerateReportsButton(DeliveryService deliveryService)
    {
        this.addGenerateReportsButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String startHourIntroduced = startHourField.getText();
                String endHourIntroduced = endHourField.getText();

                if(!(startHourIntroduced.isEmpty() && endHourIntroduced.isEmpty()))
                {
                    try
                    {
                        deliveryService.generateReportBasedOnHourInterval(startHourIntroduced, endHourIntroduced);
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }

                if(!numberOfTimesAProductAppearsField.getText().isEmpty())
                {
                    try
                    {
                        deliveryService.generateReportsBasedOnNumberOfTimesAProduct(numberOfTimesAProductAppearsField.getText());
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }

                if(!(numberOfOrdersTakenByClientField.getText().isEmpty() && minimumValueField.getText().isEmpty()))
                {
                    try
                    {
                        deliveryService.generateReportsBasedOnNumberOfOrdersAndTotalBill(numberOfOrdersTakenByClientField.getText(), minimumValueField.getText());
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }

                if(!(theDayForTheReportField.getText().isEmpty()))
                {
                    try
                    {
                        deliveryService.generateReportsBasedOnSpecifiedDay(theDayForTheReportField.getText());
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    } catch (ParseException parseException)
                    {
                        parseException.printStackTrace();
                    }
                }
            }
        });
    }

    public void addGenerateReportsButtonPressedActionListener(ActionListener actionListener)
    {
        generateButton.addActionListener(actionListener);
    }

    public void addDeleteProductButtonPressedActionListener(ActionListener actionListener)
    {
        deleteProductButton.addActionListener(actionListener);
    }

    public void addEditProductButtonPressedActionListener(ActionListener actionListener)
    {
        editProductButton.addActionListener(actionListener);
    }

    public void addAddProductButtonPressedActionListener(ActionListener actionListener)
    {
        addProductButton.addActionListener(actionListener);
    }

    public void addImportButtonPressedActionListener(ActionListener actionListener)
    {
        importProductsButton.addActionListener(actionListener);
    }

    public void addBaseProductToCompositeProductButtonPressedActionListener(ActionListener actionListener)
    {
        addToComposedProductButton.addActionListener(actionListener);
    }

    public void addCreateCompositeProductButtonPressedActionListener(ActionListener actionListener)
    {
        createComposedProductButton.addActionListener(actionListener);
    }

}
