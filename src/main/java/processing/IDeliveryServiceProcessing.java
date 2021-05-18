package processing;

import model.Client;
import model.CompositeProduct;
import model.MenuItem;
import model.Order;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;

public interface IDeliveryServiceProcessing
{
    /**
     * method to import the products from the .csv file
     * @throws IOException
     */
    public void importProducts() throws IOException;

    /**
     * method to add a product to the list of the base products extracted from the .csv file
     * @param productToInsert
     * @throws IOException
     */
    public void addProduct(MenuItem productToInsert) throws IOException;

    /**
     * method to edit a base product
     * @param nameOfProductToUpdate
     * @param updatedProduct
     */
    public void editProduct(String nameOfProductToUpdate, MenuItem updatedProduct);

    /**
     * method to delete a base product
     * @param nameOfProductToBeDeleted
     */
    public void deleteProduct(MenuItem nameOfProductToBeDeleted);

    /**
     * method to create a composite product and add it to the list of products
     * @param name
     * @param products
     * @return
     */
    public CompositeProduct createCompositeProduct(String name, List<MenuItem> products);

    /**
     * methods to generate the reports based on different criteria
     * @param startHour
     * @param endHour
     * @throws IOException
     */
    public void generateReportBasedOnHourInterval(String startHour, String endHour) throws IOException;
    public void generateReportsBasedOnNumberOfTimesAProduct(String numberOfTimes) throws IOException;
    public void generateReportsBasedOnNumberOfOrdersAndTotalBill(String numberOfOrders, String minimumBillTotal) throws IOException;
    public void generateReportsBasedOnSpecifiedDay(String specifiedDay) throws IOException, ParseException;

    /**
     * method to create a client account
     * @param client
     */
    public void createClientAccount(Client client);

    /**
     * method to find a product by a keyword
     * @param keyword - the keyword that should be in the product's name
     * @return a list of all the products that contain that keyword in their name or null in case there is no product
     */
    public List<MenuItem> findProductsByKeyword(String keyword);

    /**
     * method to create an order
     * @param client - the client that placed the order
     * @param orderedProducts - the list of all the products that were added to the cart and form the order
     * @return - the Order object representing the newly placed order
     * @throws IOException
     */
    public Order createOrder(Client client, List<MenuItem> orderedProducts) throws IOException;
}
