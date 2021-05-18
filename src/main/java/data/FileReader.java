package data;

import model.BaseProduct;
import model.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Serializable
{
    private String line = "";
    private String delimitator = ",";
    private String[] productItem;
    private String name;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    /**
     * method to read and parse entries from a .csv file in a list
     * @param fileName - the file from which we extract the data and fill the list
     * @return - the populated list
     * @throws IOException
     */
    public List<MenuItem> readProductsUsingStreams(String fileName) throws IOException
    {
        List<MenuItem> menuItems = new ArrayList<>();

        BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
        br.lines().skip(1).forEach((line) -> //skip the first line because it contains the fields' names
            {
                productItem = line.split(delimitator); //separate the data stored in each column

                name = productItem[0];
                name = name.substring(0, name.length() - 1);
                rating = Float.parseFloat(productItem[1]);
                calories = Integer.parseInt(productItem[2]);
                protein = Integer.parseInt(productItem[3]);
                fat = Integer.parseInt(productItem[4]);
                sodium = Integer.parseInt(productItem[5]);
                price = Integer.parseInt(productItem[6]);

                BaseProduct baseProduct = new BaseProduct(name, rating, calories, protein, fat, sodium, price);

                int alreadyInTheMenu = 0;

                for(MenuItem menuItem : menuItems)
                {
                    if(menuItem.getName().equals(baseProduct.getName()))
                    {
                        alreadyInTheMenu = 1;
                        break;
                    }
                }

                if(alreadyInTheMenu == 0) //make sure we don't take duplicates
                    menuItems.add(baseProduct);
            });

        return menuItems;
    }

}
