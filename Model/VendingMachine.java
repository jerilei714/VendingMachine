package Model;
public abstract class VendingMachine {
    protected ItemSlot[] itemSlots;                   // item slots in the vending machine
    protected MoneyDenominations moneyDenominations;  // money denominations in the vending machine
    protected Transaction transaction;                // transaction of the vending machine
    protected double totalAmountCollected;            // total amount collected in the transaction
    protected SpecialVendingMachine specialVendingMachine;
    protected ProductSlot[] productSlots;
    // Constructor
    // Takes in the number of item slots in the vending machine
    // Initializes the item slots with empty slots
    // Initializes the money denominations with 10 of each denomination
    // Initializes the transaction with the number of item slots

    public VendingMachine(int numSlots) {
        this.itemSlots = new ItemSlot[numSlots];        // item slots in the vending machine
        this.moneyDenominations = new MoneyDenominations(10, 10, 10, 10, 10, 10, 10, 10, 10);
        this.transaction = new Transaction(numSlots);   // transaction of the vending machine

        // Initialize item slots with empty slots
        for (int i = 0; i < numSlots; i++) {
            itemSlots[i] = new ItemSlot();
        }
    }

    public VendingMachine(int numSlots, int numProductSlots) {
        this.itemSlots = new ItemSlot[numSlots];        // item slots in the vending machine
        this.moneyDenominations = new MoneyDenominations(10, 10, 10, 10, 10, 10, 10, 10, 10);
        this.transaction = new Transaction(numSlots);   // transaction of the vending machine
        this.productSlots = new ProductSlot[numProductSlots];

        // Initialize item slots with empty slots
        for (int i = 0; i < numSlots; i++) {
            itemSlots[i] = new ItemSlot();
        }


        for (int i = 0; i < numProductSlots; i++) {
            productSlots[i] = new ProductSlot();
        }

        //hardcoded ingredients

    }

    // Getters and setters for the attributes of the class

    public ProductSlot[] getProductSlots() {
        return productSlots;
    }

    public ProductSlot getProductSlot(int index) {
        return productSlots[index];
    }

    public void setProductSlots(ProductSlot[] productSlots) {
        this.productSlots = productSlots;
    }

    public int getProductNumSlots() {
        return productSlots.length;
    }

    // getNumSlots() returns the number of item slots in the vending machine

    public int getNumSlots() {
        return itemSlots.length;
    }

    // getItemSlots() returns the item slots in the vending machine

    public ItemSlot getItemSlot(int index) {
        return itemSlots[index];
    }

    // getItemSlots() returns the item slots in the vending machine

    public ItemSlot[] getItemSlots() {
        return itemSlots;
    }

    // getItemSlots() returns the item slots in the vending machine

    public void setItemSlots(ItemSlot[] itemSlots) {
        this.itemSlots = itemSlots;
    }

    // getMoneyDenominations() returns the money denominations in the vending machine

    public void setCurrentMoney(double amount) {
        transaction.setCurrentAmountCollected(amount);
    }

    // setCurToZero() sets the current amount collected in the transaction to 0

    public void setCurToZero() {
        transaction.updateCurrentAmountCollected(0.0);
    }

    // MoneyDenominations getMoneyDenominations() returns the money denominations in the vending machine

    public MoneyDenominations getMoneyDenominations() {
        return moneyDenominations;
    }
    
    // setMoneyDenominations() sets the money denominations in the vending machine

    public void setMoneyDenominations(MoneyDenominations moneyDenominations) {
        this.moneyDenominations = moneyDenominations;
    }

    // getTransaction() returns the transaction of the vending machine

    public Transaction getTransaction() {
        return transaction;
    }

    // setTransaction() sets the transaction of the vending machine

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    // setItem() sets the item in the item slot at the given index

    public void setItem(int slotIndex, Item item, int quantity, double price, int calories) {
        itemSlots[slotIndex].setItem(item);
        itemSlots[slotIndex].setQuantity(quantity);
        itemSlots[slotIndex].setPrice(price);
        itemSlots[slotIndex].setCalories(calories);
    }

    public void setProducts(int slotIndex, Product product, int quantity, double price, int calories)
    {
        productSlots[slotIndex].setProduct(product);
        productSlots[slotIndex].setQuantity(quantity);
        productSlots[slotIndex].setPrice(price);
    }

    //getTotalAmountCollected() returns the total amount collected in the transaction

    public double getTotalAmountCollected() {
        return transaction.getTotalAmountCollected();
    }

    // Methods

    // receivePayment() receives the payment from the customer

    public void receivePayment(double amount) {
        transaction.setCurrentAmountCollected(amount);
    }

    public abstract void dispenseItem(int slotIndex);
    public abstract String produceChange(int choice);
    public abstract void replenishChange();


    //printTransactionSummary() prints the transaction summary

    public String printTransactionSummary(VendingMachine vendingMachine) {
        StringBuilder summary = new StringBuilder();
        summary.append("===== Transaction Summary =====\n");
        summary.append("Starting Inventory:\n");
        
        for (int i = 0; i < itemSlots.length; i++) {
            ItemSlot slot = itemSlots[i];
            int quant = slot.getQuantity() + transaction.getItemQuantitySold(i);;
            summary.append("| Slot " + (i + 1) + ": " + slot.getItem().getName() + " - Quantity: " + quant + "\n");
        }
    
        summary.append("==== Items Sold: ====\n");

        double totalAmount2 = transaction.getTotalAmountCollected();

        for (int i = 0; i < itemSlots.length; i++) {
            ItemSlot slot = itemSlots[i];
            int quantitySold = transaction.getItemQuantitySold(i);
            summary.append(slot.getItem().getName() + " - Quantity Sold: " + quantitySold + "\n");
        }

        summary.append("|== Total Amount Collected (Profit): PHP " + totalAmount2 + " ==|\n");

        //print also the transaction summary with the products sold and the total amount collected
        return summary.toString();
    }

    //updateEndingInventory() updates the ending inventory after the transaction is completed and prints the ending inventory

    public String updateEndingInventory() {
        double totalAmount1 = transaction.getTotalAmountCollected();

        for (int i = 0; i < itemSlots.length; i++) {        //updates the ending inventory
            ItemSlot slot = itemSlots[i];

            int quantitySold = transaction.getItemQuantitySold(i);
            slot.setQuantity(slot.getQuantity() - quantitySold);
        }

        StringBuilder endingInventory = new StringBuilder();

        endingInventory.append("===== Ending Inventory =====\n"); //prints the ending inventory
        for (int i = 0; i < itemSlots.length; i++) {
            ItemSlot slot = itemSlots[i];
            endingInventory.append("Slot " + (i + 1) + ": " + slot.getItem().getName() + " - Quantity: " + slot.getQuantity() + "\n");
        }

        endingInventory.append("|== Total Amount Collected: PHP " + totalAmount1 + "==|\n");

        endingInventory.append("==== Items Sold: ====\n");
        for (int i = 0; i < itemSlots.length; i++) {
            ItemSlot slot = itemSlots[i];
            int quantitySold = transaction.getItemQuantitySold(i);
            endingInventory.append(slot.getItem().getName() + " - Quantity Sold: " + quantitySold + "\n");
        }

        endingInventory.append("===== Ending Money Denominations =====\n");
        endingInventory.append("| 1000 PHP: " + moneyDenominations.getCount1000() + "\n");
        endingInventory.append("| 500 PHP: " + moneyDenominations.getCount500() + "\n");
        endingInventory.append("| 100 PHP: " + moneyDenominations.getCount100() + "\n");
        endingInventory.append("| 50 PHP: " + moneyDenominations.getCount50() + "\n");
        endingInventory.append("| 20 PHP: " + moneyDenominations.getCount20() + "\n");
        endingInventory.append("| 10 PHP: " + moneyDenominations.getCount10() + "\n");
        endingInventory.append("| 5 PHP: " + moneyDenominations.getCount5() + "\n");
        endingInventory.append("| 1 PHP: " + moneyDenominations.getCount1() + "\n");

        endingInventory.append("===== Ending Machine Balance =====\n");

        double totalAmount = 0;
        for (int i = 0; i < itemSlots.length; i++) {
            ItemSlot slot = itemSlots[i];
            int quantitySold = transaction.getItemQuantitySold(i);
            totalAmount += slot.getPrice() * quantitySold;
        }
    
        totalAmount += moneyDenominations.getCount1000() * 1000;
        totalAmount += moneyDenominations.getCount500() * 500;
        totalAmount += moneyDenominations.getCount100() * 100;
        totalAmount += moneyDenominations.getCount50() * 50;
        totalAmount += moneyDenominations.getCount20() * 20;
        totalAmount += moneyDenominations.getCount10() * 10;
        totalAmount += moneyDenominations.getCount5() * 5;
        totalAmount += moneyDenominations.getCount1() * 1;

        endingInventory.append("|== Total Amount Left in Vending Machine: PHP " + totalAmount + "\n");
        endingInventory.append("|== Total Profit: PHP " + totalAmount1 + "\n");
    
        return endingInventory.toString();
    }

    //restockItems() restocks the items in the vending machine

    public void restockItems(int slotIndex, int quantity) {
        ItemSlot slot = itemSlots[slotIndex];
        slot.setQuantity(slot.getQuantity() + quantity);
    }

    //restockItems() method but without the parameters



    //setItemPrice() sets the price of the item

    public void setItemPrice(int slotIndex, double price) {
        ItemSlot slot = itemSlots[slotIndex];
        slot.setPrice(price);
    }  

    //collectPayment() collects the payment from the customer
    //prints the total amount collected and the money denominations
    //prints an error message if no payment is received

    public String collectPayment() {
        totalAmountCollected = transaction.getTotalAmountCollected();
        String paymentSummary = "|== Total amount collected: PHP " + totalAmountCollected + " ==|\n";

        if (totalAmountCollected == 0) {
            paymentSummary += "ERR: == No payment received.\n";
            return paymentSummary;
        }

        paymentSummary += "===== Money Denominations Left =====\n" +
            "| 1000 PHP: " + moneyDenominations.getCount1000() + "\n" +
            "| 500 PHP: " + moneyDenominations.getCount500() + "\n" +
            "| 100 PHP: " + moneyDenominations.getCount100() + "\n" +
            "| 50 PHP: " + moneyDenominations.getCount50() + "\n" +
            "| 20 PHP: " + moneyDenominations.getCount20() + "\n" +
            "| 10 PHP: " + moneyDenominations.getCount10() + "\n" +
            "| 5 PHP: " + moneyDenominations.getCount5() + "\n" +
            "| 1 PHP: " + moneyDenominations.getCount1() + "\n";
        return paymentSummary;
    }


}