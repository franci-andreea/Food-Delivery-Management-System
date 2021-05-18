package model;

import java.awt.*;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable
{
    private int orderID;
    private int clientID;
    private Date orderDate;
    private List<MenuItem> productsOrdered;

    /**
     * Constructor to initialize all the fields for the Order object
     * @param orderID
     * @param clientID
     * @param orderDate
     */
    public Order(int orderID, int clientID, Date orderDate)
    {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public int hashCode()
    {
        return Objects.hash(orderID, clientID, orderDate);
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getClientID()
    {
        return clientID;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setProductsOrdered(List<MenuItem> productsOrdered)
    {
        this.productsOrdered = productsOrdered;
    }

    public List<MenuItem> getProductsOrdered()
    {
        return productsOrdered;
    }

    public int totalBillOrder()
    {
        int totalBill = 0;

        for(MenuItem menuItem : productsOrdered)
            totalBill = totalBill + menuItem.computePrice();

        return totalBill;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate +
                ", productsOrdered=" + productsOrdered +
                '}';
    }
}
