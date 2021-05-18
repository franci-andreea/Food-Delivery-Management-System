package data;

import bll.DeliveryService;
import model.Client;
import model.MenuItem;
import model.Order;

import java.io.IOException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FileWriter implements Serializable
{
    /**
     * already explained in DeliveryService class
     * method used to write the bill for the order
     * @param order - the order we write the bill for
     * @throws IOException
     */
    public void writeBill(Order order) throws IOException
    {
        java.io.FileWriter fileWriter = new java.io.FileWriter("GeneratedBill" + order.getOrderID() + ".txt");

        int totalBill = 0;
        List<MenuItem> orderedProducts = order.getProductsOrdered();

        fileWriter.write("Order with id = " + order.getOrderID() + "\n");
        fileWriter.write("Placed at " + order.getOrderDate() + "\n");
        fileWriter.write("Client ID = " + order.getClientID() + "\n");
        fileWriter.write("Ordered Products:\n");
        fileWriter.write("\t");
        for (MenuItem orderedProduct : orderedProducts)
        {
            if (orderedProduct != orderedProducts.get(orderedProducts.size() - 1))
                fileWriter.write(orderedProduct.getName() + ", ");
            else
                fileWriter.write(orderedProduct.getName() + ".");
            totalBill = totalBill + orderedProduct.getPrice();
        }

        fileWriter.write("\nTotal order bill = " + totalBill + "\n");
        fileWriter.close();
    }

    /**
     * method to generate a report based on a criteria - hour interval
     * @param orderMap - the deliveryService's orders map filled with all the placed orders
     * @param startHour - the left side of the hour interval
     * @param endHour - the right side of the hour interval
     * @throws IOException
     */
    public void generateReportHoursInterval(HashMap<Order, List<MenuItem>> orderMap, String startHour, String endHour) throws IOException
    {
        java.io.FileWriter fileWriter = new java.io.FileWriter("ReportHoursInterval.txt");

        List<Order> foundOrders;

        int intStartHour = Integer.parseInt(startHour);
        int intEndHour = Integer.parseInt(endHour);

        foundOrders = orderMap.keySet().stream().filter(menuItems -> {
            DateFormat dateFormat = new SimpleDateFormat("k");
            int hour = Integer.parseInt(dateFormat.format(menuItems.getOrderDate()));

            return intStartHour <= hour && hour <= intEndHour;
        }).collect(Collectors.toList());

        for (Order order : foundOrders)
        {
            fileWriter.write("Order ID = " + order.getOrderID() + "\n");
            fileWriter.write("Client ID = " + order.getClientID() + "\n");
            fileWriter.write("Order date = " + order.getOrderDate() + "\n");
            fileWriter.write("Products ordered:\n");
            fileWriter.write("\t");

            for(MenuItem eachProduct : order.getProductsOrdered())
            {
                if (eachProduct != order.getProductsOrdered().get(order.getProductsOrdered().size() - 1))
                    fileWriter.write(eachProduct.getName() + ", ");
                else
                    fileWriter.write(eachProduct.getName() + ".");
            }

            fileWriter.write("\n\n\n");
        }

        fileWriter.close();
    }

    /**
     * method to generate a report based on a criteria - a specified number of times a product has been ordered so far
     * @param orderMap - the deliveryService's orders map filled with all the placed orders
     * @param numberOfTimes - the specified number
     * @throws IOException
     */
    public void generateReportNumberOfTimesProduct(HashMap<Order, List<MenuItem>> orderMap, String numberOfTimes) throws IOException
    {
        java.io.FileWriter fileWriter = new java.io.FileWriter("ReportNumberOfTimesAProductWasBought.txt");

        int intNumberOfTimes = Integer.parseInt(numberOfTimes);

        List<Order> foundOrders = orderMap.keySet().stream().filter(order -> {

            for(MenuItem menuItem : order.getProductsOrdered())
            {
                if(menuItem.getCount() >= intNumberOfTimes)
                    return true;
            }
            return false;
        }).collect(Collectors.toList());

        for (Order order : foundOrders)
        {
            fileWriter.write("Order ID = " + order.getOrderID() + "\n");
            fileWriter.write("Client ID = " + order.getClientID() + "\n");
            fileWriter.write("Order date = " + order.getOrderDate() + "\n");
            fileWriter.write("Products ordered:\n");
            fileWriter.write("\t");

            for(MenuItem eachProduct : order.getProductsOrdered())
            {
                if (eachProduct != order.getProductsOrdered().get(order.getProductsOrdered().size() - 1))
                    fileWriter.write(eachProduct.getName() + "(count = " + eachProduct.getCount() + "), ");
                else
                    fileWriter.write(eachProduct.getName() + "(count = " + eachProduct.getCount() + ").");
            }

            fileWriter.write("\n\n\n");
        }

        fileWriter.close();

    }

    /**
     * method to generate a report based on a criteria - a specified number of orders palced by a client and a minimum total bill value
     * @param deliveryService - the deliveryService object used to call the findClientById
     * @param numberOfOrders - the specified number
     * @param minimumBillTotal - the specified minimum value
     * @throws IOException
     */
    public void generateReportNumberOfOrdersAndBillValue(DeliveryService deliveryService, String numberOfOrders, String minimumBillTotal) throws IOException
    {
        java.io.FileWriter fileWriter = new java.io.FileWriter("ReportNumberOfOrdersAndMinimumBill.txt");

        int intNumberOfOrders = Integer.parseInt(numberOfOrders);
        int intMinimumBillTotal = Integer.parseInt(minimumBillTotal);

        List<Order> foundOrders = deliveryService.getOrdersMap().keySet().stream().filter(order -> {
            if(order.totalBillOrder() >= intMinimumBillTotal)
            {
                Client currentClient = deliveryService.findClientById(order.getClientID());
                System.out.println("currentClient = " + currentClient.toString());
                return currentClient.getNumberOfOrders() >= intNumberOfOrders;
            }
            return false;
        }).collect(Collectors.toList());

        for (Order order : foundOrders)
        {
            fileWriter.write("Order ID = " + order.getOrderID() + "\n");
            fileWriter.write("Client ID = " + order.getClientID() + "\n");
            fileWriter.write("Order date = " + order.getOrderDate() + "\n");
            fileWriter.write("Products ordered:\n");
            fileWriter.write("\t");

            for(MenuItem eachProduct : order.getProductsOrdered())
            {
                if (eachProduct != order.getProductsOrdered().get(order.getProductsOrdered().size() - 1))
                    fileWriter.write(eachProduct.getName() + "(count = " + eachProduct.getCount() + "), ");
                else
                    fileWriter.write(eachProduct.getName() + "(count = " + eachProduct.getCount() + ").");
            }

            fileWriter.write("\n\n\n");
        }

        fileWriter.close();
    }

    /**
     * method to generate a report based on a criteria - a specified date to print all the orders along with the frequency
     * of the products from that order
     * @param orderMap - the deliveryService's orders map filled with all the placed orders
     * @param specifiedDay - the specified day
     * @throws IOException
     * @throws ParseException
     */
    public void generateReportOnSpecificDay(HashMap<Order, List<MenuItem>> orderMap, String specifiedDay) throws IOException, ParseException
    {
        java.io.FileWriter fileWriter = new java.io.FileWriter("ReportOnSpecifiedDay.txt");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<Order> foundOrders = orderMap.keySet().stream().filter(order -> {
            String orderDate = sdf.format(order.getOrderDate());
            return orderDate.equals(specifiedDay);
        }).collect(Collectors.toList());

        for (Order order : foundOrders)
        {
            fileWriter.write("Order ID = " + order.getOrderID() + "\n");
            fileWriter.write("Client ID = " + order.getClientID() + "\n");
            fileWriter.write("Order date = " + order.getOrderDate() + "\n");
            fileWriter.write("Products ordered:\n");
            fileWriter.write("\t");

            for(MenuItem eachProduct : order.getProductsOrdered())
            {
                if (eachProduct != order.getProductsOrdered().get(order.getProductsOrdered().size() - 1))
                    fileWriter.write(eachProduct.getName() + "(count = " + Collections.frequency(order.getProductsOrdered(), eachProduct) + "), ");
                else
                    fileWriter.write(eachProduct.getName() + "(count = " + Collections.frequency(order.getProductsOrdered(), eachProduct) + ").");
            }

            fileWriter.write("\n\n\n");
        }

        fileWriter.close();
    }

}
