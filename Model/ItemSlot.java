package Model;
public class ItemSlot {
    private Item item;      // item in the slot
    private int quantity;   // quantity of the item in the slot
    private double price;   // price of the item in the slot
    private int calories;   // calories of the item in the slot

    // Constructor
    // Takes in the item, quantity, price and calories of the item

    public ItemSlot() {
        this.item = null;
        this.quantity = 0;
        this.price = 0.0;
        this.calories = 0;
    }

    // Getters and setters for the attributes of the class

    // getItem() returns the item in the slot

    public Item getItem() {
        return item;
    }

    // setItem() sets the item in the slot

    public void setItem(Item item) {
        this.item = item;
    }

    // getQuantity() returns the quantity of the item in the slot

    public int getQuantity() {
        return quantity;
    }

    // setQuantity() sets the quantity of the item in the slot

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // getPrice() returns the price of the item in the slot

    public double getPrice() {
        return price;
    }

    // setPrice() sets the price of the item in the slot

    public void setPrice(double price) {
        this.price = price;
    }

    // getCalories() returns the calories of the item in the slot

    public int getCalories() {
        return calories;
    }

    // setCalories() sets the calories of the item in the slot

    public void setCalories(int calories) {
        this.calories = calories;
    }

    // incrementQuantity() increments the quantity of the item in the slot by 1
    // It is used when the user purchases an item from the vending machine

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}