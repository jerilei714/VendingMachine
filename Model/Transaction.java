package Model;



public class Transaction {
    private int[] itemQuantities;           // item quantities in the transaction
    private int[] productQuantities;        // product quantities in the transaction
    private double totalAmountCollected;    // total amount collected in the transaction
    private double currentAmountCollected;  // current amount collected in the transaction
    private int startingInventory;          // starting inventory of the transaction
    private int endingInventory;            // ending inventory of the transaction

    // Constructor
    // Takes in the number of items in the vending machine
    // Initializes the item quantities to 0

    public Transaction(int numItems) {
        this.itemQuantities = new int[numItems];
        this.currentAmountCollected = 0.0;
        this.totalAmountCollected = 0.0;
        this.startingInventory = 0;
        this.endingInventory = 0;
    }

    // Getters and setters for the attributes of the class

    public int[] getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(int[] productQuantities) {
        this.productQuantities = productQuantities;
    }

    public int getProductQuantitySold(int productIndex) {
        return productQuantities[productIndex];
    }

    public void setProductQuantitySold(int productIndex, int quantity) {
        this.productQuantities[productIndex] = quantity;
    }

    // getItemQuantity() returns the quantity of the item at the given index

    public int getItemQuantitySold(int itemIndex) {
        return itemQuantities[itemIndex];
    }

    // setItemQuantitySold() sets the quantity of the item at the given index

    public void setItemQuantitySold(int itemIndex, int quantity) {
        this.itemQuantities[itemIndex] = quantity;
    }

    // setItemQuantity() sets the quantity of the item at the given index

    public int[] getItemQuantities() {
        return itemQuantities;
    }

    // getCurrentAmountCollected() returns the current amount collected in the transaction

    public double getCurrentAmountCollected() {
        return currentAmountCollected;
    }

    // setCurrentAmountCollected() sets the current amount collected in the transaction

    public void updateCurrentAmountCollected(double currentAmountCollected) {
        this.currentAmountCollected = currentAmountCollected;
    }

    // setCurrentAmountCollected() sets the current amount collected in the transaction

    public void setCurrentAmountCollected(double amount) {
        this.currentAmountCollected += amount;
    }

    // setItemQuantities() sets the item quantities in the transaction

    public void setItemQuantities(int[] itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    // getTotalAmountCollected() returns the total amount collected in the transaction

    public double getTotalAmountCollected() {
        return totalAmountCollected;
    }

    // setTotalAmountCollected() sets the total amount collected in the transaction

    public void setTotalAmountCollected(double totalAmountCollected) {
        this.totalAmountCollected = totalAmountCollected;
    }

    // setTotalAmountCollected() sets the total amount collected in the transaction

    public void setTotalAmountCollected(int itemIndex, double amount) {
        this.totalAmountCollected += amount;
    }

    // getStartingInventory() returns the starting inventory of the transaction

    public int getStartingInventory() {
        return startingInventory;
    }

    // setStartingInventory() sets the starting inventory of the transaction

    public void setStartingInventory(int startingInventory) {
        this.startingInventory = startingInventory;
    }

    // getEndingInventory() returns the ending inventory of the transaction

    public int getEndingInventory() {
        return endingInventory;
    }

    // setEndingInventory() sets the ending inventory of the transaction

    public void setEndingInventory(int endingInventory) {
        this.endingInventory = endingInventory;
    }

    // updateItemQuantity() updates the quantity of the item at the given index
    // Takes in the index of the item and the quantity to be added
    // Adds the quantity to the current quantity
    // Used in the VendingMachine class to update the item quantity in the transaction

    public void updateItemQuantity(int itemIndex, int quantity) {
        itemQuantities[itemIndex] += quantity;
    }

    // updateTotalAmountCollected() updates the total amount collected in the transaction
    // Takes in the amount to be added
    // Adds the amount to the current total amount collected
    // Used in the VendingMachine class to update the total amount collected in the transaction

    public void updateTotalAmountCollected(double amount) {
        totalAmountCollected += amount;
    }

}