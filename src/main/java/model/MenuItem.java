package model;

import java.io.Serializable;

public abstract class MenuItem implements Serializable
{
    private String name;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private int count;

    /**
     * Constructor to initialize all the fields for the MenuItem object
     * @param name
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     */
    public MenuItem(String name, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        this.name = name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getRating()
    {
        return rating;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int calories)
    {
        this.calories = calories;
    }

    public int getProtein()
    {
        return protein;
    }

    public void setProtein(int protein)
    {
        this.protein = protein;
    }

    public int getFat()
    {
        return fat;
    }

    public void setFat(int fat)
    {
        this.fat = fat;
    }

    public int getSodium()
    {
        return sodium;
    }

    public void setSodium(int sodium)
    {
        this.sodium = sodium;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public abstract int computePrice();

    @Override
    public String toString()
    {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
