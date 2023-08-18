package Model;

public class Item {
    private String name;            // name of the item
    private double price;           // price of the item
    private int calories;           // calories of the item
    private int ingredientQuantity; // quantity of the item needed as an ingredient in a product

    // Constructor
    // Takes in the name, price, calories, and ingredient quantity of the item
    public Item(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public Item (Item ingredient, int ingredientQuantity) {
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
        this.calories = ingredient.getCalories();
        this.ingredientQuantity = ingredientQuantity;
    }

    // Getters and setters for the attributes of the class

    // getName() returns the name of the item
    public String getName() {
        return name;
    }

    // setName() sets the name of the item
    public void setName(String name) {
        this.name = name;
    }

    // getPrice() returns the price of the item
    public double getPrice() {
        return price;
    }

    // setPrice() sets the price of the item
    public void setPrice(double price) {
        this.price = price;
    }

    // getCalories() returns the calories of the item
    public int getCalories() {
        return calories;
    }

    // setCalories() sets the calories of the item
    public void setCalories(int calories) {
        this.calories = calories;
    }

    // getIngredientQuantity() returns the ingredient quantity of the item
    public int getIngredientQuantity() {
        return ingredientQuantity;
    }

    // setIngredientQuantity() sets the ingredient quantity of the item
    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Calories: " + calories;
        // Customize the format as per your requirement, adding all relevant information.
    }
}