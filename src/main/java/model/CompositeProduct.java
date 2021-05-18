package model;

import java.util.List;

public class CompositeProduct extends MenuItem
{
    private List<MenuItem> compositeProduct;

    public CompositeProduct(String name, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        super(name, rating, calories, protein, fat, sodium, price);
    }

    public void setCompositeProduct(List<MenuItem> compositeProduct)
    {
        this.compositeProduct = compositeProduct;
    }

    @Override
    public int computePrice()
    {
        int totalPrice = 0;
        for (MenuItem menuItem : compositeProduct)
        {
            totalPrice = totalPrice + menuItem.getPrice();
        }

        return totalPrice;
    }

}
