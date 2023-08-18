package Model;
public class Product {
    private String name;
    private String description;
    private double price;
    private int calories;
    private String sauce;
    private Item[] ingredients; // List of toppings required for the pizza

    // Constructors, getters, setters, and other methods

    public Product(String name, String description, double price, int calories, String sauce, Item[] ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.sauce = sauce;
        this.ingredients = ingredients;
    }

    public Product(String name, String description, double price, int calories, Item[] ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    public Product(String name, String description, double price, Item[] ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getCalories() {
        return this.calories;
    }

    public Item[] getIngredients() {
        return this.ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
        }
    }

    public void setPrice(double price) {
        if (price < 0) {
            this.price = 0.0;
        } else {
            this.price = price;
        }
    }

    public void setCalories(int calories) {
        if (calories < 0) {
            this.calories = 0;
        } else {
            this.calories = calories;
        }
    }

    public void setIngredients(Item[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getSauce() {
        return this.sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
}
