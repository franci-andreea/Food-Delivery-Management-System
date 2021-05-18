package bll;

import data.FileReader;

import data.FileWriter;
import model.Client;
import model.CompositeProduct;
import model.MenuItem;
import model.Order;
import processing.IDeliveryServiceProcessing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.lang.reflect.Field;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing, Serializable
{
    private HashMap<Order, List<MenuItem>> ordersMap;
    private List<MenuItem> menuItems;
    private List<Client> clientsList;
    private AtomicInteger orderID;
    private AtomicInteger clientID;

    /**
     * Constructor that initializes all the fields of the DeliveryService object
     */
    public DeliveryService()
    {
        ordersMap = new HashMap<>();
        menuItems = new ArrayList<>();
        clientsList = new ArrayList<>();
        orderID = new AtomicInteger();
        clientID = new AtomicInteger();
        orderID.set(0);
        clientID.set(0);
    }

    @Override
    public void generateReportBasedOnHourInterval(String startHour, String endHour) throws IOException
    {
        FileWriter fileWriter = new FileWriter();
        fileWriter.generateReportHoursInterval(ordersMap, startHour, endHour);
    }

    @Override
    public void generateReportsBasedOnNumberOfTimesAProduct(String numberOfTimes) throws IOException
    {
        FileWriter fileWriter = new FileWriter();
        fileWriter.generateReportNumberOfTimesProduct(ordersMap, numberOfTimes);
    }

    @Override
    public void generateReportsBasedOnNumberOfOrdersAndTotalBill(String numberOfOrders, String minimumBillTotal) throws IOException
    {
        FileWriter fileWriter = new FileWriter();
        fileWriter.generateReportNumberOfOrdersAndBillValue(this, numberOfOrders, minimumBillTotal);
    }

    @Override
    public void generateReportsBasedOnSpecifiedDay(String specifiedDay) throws IOException, ParseException
    {
        FileWriter fileWriter = new FileWriter();
        fileWriter.generateReportOnSpecificDay(ordersMap, specifiedDay);
    }

    @Override
    public void createClientAccount(Client client)
    {
        int clientId = clientID.incrementAndGet();
        client.setClientID(clientId);

        clientsList.add(client);
    }

    @Override
    public void importProducts() throws IOException
    {
        FileReader fileReader = new FileReader();
        menuItems = fileReader.readProductsUsingStreams("products.csv");
    }

    @Override
    public void addProduct(MenuItem productToInsert) throws IOException
    {
        menuItems.add(productToInsert);
    }

    @Override
    public void editProduct(String nameOfProductToUpdate, MenuItem updatedProduct)
    {
        MenuItem productToUpdate = findProductByName(nameOfProductToUpdate);

        productToUpdate.setName(updatedProduct.getName());
        productToUpdate.setRating(updatedProduct.getRating());
        productToUpdate.setCalories(updatedProduct.getCalories());
        productToUpdate.setProtein(updatedProduct.getProtein());
        productToUpdate.setFat(updatedProduct.getFat());
        productToUpdate.setSodium(updatedProduct.getSodium());
        productToUpdate.setPrice(updatedProduct.getPrice());
    }

    @Override
    public void deleteProduct(MenuItem productToBeDeleted)
    {
        menuItems.remove(productToBeDeleted);
    }

    @Override
    public CompositeProduct createCompositeProduct(String name, List<MenuItem> products)
    {
        CompositeProduct compositeProduct;

        int totalCalories = 0;
        int totalFat = 0;
        int totalProtein = 0;
        int totalSodium = 0;
        float rating = 0;
        int totalPrice = 0;

        for(MenuItem menuItem : products)
        {
            totalPrice = totalPrice + menuItem.getPrice();
            totalCalories = totalCalories + menuItem.getCalories();
            totalFat = totalFat + menuItem.getFat();
            totalProtein = totalProtein + menuItem.getProtein();
            totalSodium = totalSodium + menuItem.getSodium();
            rating = rating + menuItem.getRating();
        }

        rating = rating / products.size();

        compositeProduct = new CompositeProduct(name, rating, totalCalories, totalProtein, totalFat, totalSodium, totalPrice);

        compositeProduct.setCompositeProduct(products);

        return  compositeProduct;
    }

    @Override
    public Order createOrder(Client client, List<MenuItem> orderedProducts) throws IOException
    {
        int orderId = orderID.incrementAndGet();
        int clientId = client.getClientID();
        Date orderDate = new Date();
        Order orderPlaced = new Order(orderId, clientId, orderDate);
        orderPlaced.setProductsOrdered(orderedProducts);

        ordersMap.put(orderPlaced, orderedProducts);
        generateBill(orderPlaced);

        return orderPlaced;
    }

    /**
     * method to search for a client and find it based on its ID value
     * @param id - the ID value of the client we want to find
     * @return - a Client object representing the client we were looking for
     */
    public Client findClientById(int id)
    {
        for(Client client : clientsList)
            if(client.getClientID() == id)
                return client;

        return null;
    }

    /**
     * method to search for a product based on its name
     * @param nameOfProductToFind - the name of the product we want to find
     * @return - a Product object representing the product with the required name or null in case it does not exist
     */
    public MenuItem findProductByName(String nameOfProductToFind)
    {
        for(MenuItem menuItem : menuItems)
        {
            if(menuItem.getName().equals(nameOfProductToFind))
            {
                System.out.println("found : " + menuItem.getName());
                return menuItem;
            }
        }

        return null;
    }

    @Override
    public List<MenuItem> findProductsByKeyword(String keyword)
    {
        return menuItems.stream().filter(menuItem -> menuItem.getName().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * method to generate the bill
     * @param order - the order for which we generate the bill
     * @throws IOException
     */
    public void generateBill(Order order) throws IOException
    {
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeBill(order);
    }

    /**
     * method to create a JTable based on a list of object of type MenuItem
     * @param objects - the list of objects we want to display as a JTable
     * @return - the JTable with all the information from the list
     * @throws IllegalAccessException
     */
    public JTable createTable(List<MenuItem> objects) throws IllegalAccessException
    {
        DefaultTableModel tableModel = new DefaultTableModel();
        int numberOfColumns = MenuItem.class.getDeclaredFields().length;

        for(Field field : MenuItem.class.getDeclaredFields())
            tableModel.addColumn(field.getName());

        for(Object object : objects)
        {
            Object[] currentObjectRowDetails = new Object[numberOfColumns];
            int currentColumnInTheTable = 0;

            for(Field field : MenuItem.class.getDeclaredFields())
            {
                field.setAccessible(true);
                currentObjectRowDetails[currentColumnInTheTable] = field.get(object);
                currentColumnInTheTable++;
            }

            tableModel.addRow(currentObjectRowDetails);
        }
        return new JTable(tableModel);
    }

    /**
     * Useful getters and setters for the DeliveryService object
     */
    public List<String> getMenuItemsNames()
    {
        List<String> names = new ArrayList<>();
        for(MenuItem menuItem : menuItems)
            names.add(menuItem.getName());

        return names;
    }

    public List<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    public HashMap<Order, List<MenuItem>> getOrdersMap()
    {
        return ordersMap;
    }

    public List<Client> getClientsList()
    {
        return clientsList;
    }

}
