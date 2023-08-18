package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecialVendingMachine extends VendingMachine {

    private ProductSlot[] productSlots;

    public SpecialVendingMachine(int numSlots, int numProductSlots) {
        super(numSlots);

        productSlots = new ProductSlot[numProductSlots];

        for (int i = 0; i < numProductSlots; i++) {
            productSlots[i] = new ProductSlot();
        }

    }

    //getter and setter for productSlots

    public ProductSlot[] getProductSlots() {
        return productSlots;
    }

    public ProductSlot getProductSlot(int index) {
        return productSlots[index];
    }

    public void setProductSlots(ProductSlot[] productSlots) {
        this.productSlots = productSlots;
    }

    //number of product slots

    public int getProductNumSlots() {
        return productSlots.length;
    }


    public void addProductToSlot(int slotIndex, String name, String description, double price, int calories, Item[] ingredients) {
        if (slotIndex >= 0 && slotIndex < productSlots.length) {
            Product product = new Product(name, description, price, calories, ingredients);
            productSlots[slotIndex] = new ProductSlot(product, 10);
        } else {
            System.out.println("ERR: Invalid product slot index.");
        }
    }

    public Product getProductFromSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < productSlots.length) {
            return productSlots[slotIndex].getProduct();
        } else {
            System.out.println("ERR: Invalid product slot index.");
            return null;
        }
    }

    public void orderCustomizedProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Customizing Your Product ==");

        // Ask the user to choose the sauce (marinara or pesto)
        System.out.println("1. Marinara Sauce");
        System.out.println("2. Pesto Sauce");
        System.out.print("|== Choose your sauce (1/2): ");
        int sauceChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String sauce;
        if (sauceChoice == 1) {
            sauce = "Marinara";
        } else if (sauceChoice == 2) {
            sauce = "Pesto";
        } else {
            System.out.println("ERR: == Invalid sauce choice! Customization canceled. ==");
            return;
        }

        System.out.println("== Choose the ingredients for your pizza: ==");

        // Display the available ingredients in the vending machine
        for (int i = 0; i < getNumSlots(); i++) {
            ItemSlot itemSlot = getItemSlot(i);
            Item item = itemSlot.getItem();
            System.out.println((i + 1) + ". " + item.toString());
        }

        // Ask the user to select the ingredients
        List<Item> selectedIngredients = new ArrayList<>();
        int totalCalories = 0;
        double productPrice = 0;

        while (true) {
            System.out.print("|== Enter the slot number to add an ingredient (0 to finish): ");
            int slotNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (slotNumber == 0) {
                break;
            }

            if (slotNumber < 1 || slotNumber > getNumSlots()) {
                System.out.println("ERR: == Invalid slot number! Please try again. ==");
                continue;
            }

            Item selectedIngredient = getItemSlot(slotNumber - 1).getItem();
            selectedIngredients.add(selectedIngredient);
    
            if (sauce.equals("Marinara")) {
                totalCalories = 450;
            } else if (sauce.equals("Pesto")) {
                totalCalories = 300;
            }

            for (Item ingredient : selectedIngredients) {
                totalCalories += ingredient.getCalories();
            }
        }

        // Create the customized product
        String productName = "Custom Pizza with " + sauce;
        String productDescription = "A delicious pizza with " + sauce + " sauce and custom toppings";
        //the prize of the customized product is calculated here (the initial price of the pizza is added to the price of the ingredients)
        
        if(sauce.equals("Marinara")) productPrice = 300;
        else if(sauce.equals("Pesto")) productPrice = 350;
        for (Item ingredient : selectedIngredients) {
            productPrice += ingredient.getPrice();
        }

        Item[] ingredientsArray = selectedIngredients.toArray(new Item[0]);
        Product customizedProduct = new Product(productName, productDescription, productPrice, totalCalories, sauce, ingredientsArray);

        // Display the customized product
        System.out.println("== Your customized product: ==");
        System.out.println(customizedProduct);
        //dislplay description of the customized product
        System.out.println("== Description: " + productDescription + " ==");
        // Display the ingredients of the customized product
        System.out.println("== Ingredients: ==");
        for (Item ingredient : ingredientsArray) {
            //use something so that it displays the actual ingredient name and not the hash
            System.out.println(ingredient.toString());
        }
        // Display the total calories of the customized product
        System.out.println("== Total Calories: " + totalCalories + " ==");
        //display the price of the customized product

        System.out.println("== Total Price: " + productPrice + " ==");

        System.out.println("=====================");
        System.out.println("|== Enter the money: ");

       //Here is where the user enters the money

        System.out.print("| How many 1000's:");
        int thousands = scanner.nextInt();
        System.out.print("| How many 500's:");
        int fiveHundreds = scanner.nextInt();
        System.out.print("| How many 200's:");
        int twoHundreds = scanner.nextInt();
        System.out.print("| How many 100's:");
        int hundreds = scanner.nextInt();
        System.out.print("| How many 50's:");
        int fifties = scanner.nextInt();
        System.out.print("| How many 20's:");
        int twenties = scanner.nextInt();
        System.out.print("| How many 10's:");
        int tens = scanner.nextInt();
        System.out.print("| How many 5's:");
        int fives = scanner.nextInt();
        System.out.print("| How many 1's:");
        int ones = scanner.nextInt();

        //Payment is calculated here

        int payment = (thousands * 1000) + (fiveHundreds * 500) + (twoHundreds * 200) + (hundreds * 100) + (fifties * 50) + (twenties * 20) + (tens * 10) + (fives * 5) + ones;

        receivePayment(payment);

        scanner.nextLine(); // Consume newline character

        // Ask the user if they want to order the customized product

        System.out.print("|== Do you want to (1) Continue or (2) Exit: ");

        int choice1 = scanner.nextInt();

        scanner.nextLine(); // Consume newline character

        if (choice1 == 1) {
            dispenseCustomizedProduct(customizedProduct);
            System.out.println("== Your change is: ==");
            produceChange(1); //give the change
            setCurToZero();  //set current money to zero 

        } else if (choice1 == 2) {
            System.out.println("== Thank you for using our vending machine! ==");
            //give back the money
            System.out.println("== Your change is: ==");
            produceChange(2); //give back the money
            setCurToZero();  //set current money to zero

        } else System.out.println("ERR: == Invalid choice! ==");
    }


    public void dispenseCustomizedProduct(Product customProduct){

        Transaction transac = getTransaction();

        if (customProduct == null) {
            System.out.println("ERR: == Product is not available.");
            return;
        }

        Item[] ingredients = customProduct.getIngredients();

        // Check if all ingredients are available in the vending machine
        boolean allIngredientsAvailable = true;
        for (Item ingredient : ingredients) {
            boolean foundIngredient = false;
            for (ItemSlot itemSlot : getItemSlots()) {
                if (itemSlot.getItem() == ingredient && itemSlot.getQuantity() > 0) {
                    // Decrement the quantity of the ingredient in the vending machine
                    itemSlot.decrementQuantity();
                    foundIngredient = true;
                    break;
                }
            }
            if (!foundIngredient) {
                allIngredientsAvailable = false;
                break;
            }
        }

        if (allIngredientsAvailable) {
            // If all ingredients are available, dispense the product

            System.out.println("==================================");
            System.out.println("Your delicious pizza is being prepared!");
            System.out.println("Kneading dough...");
            if (customProduct.getSauce().equals("Marinara")) {
                System.out.println("Pouring marinara sauce");
            } else if (customProduct.getSauce().equals("Pesto")) {
                System.out.println("Pouring pesto sauce");
            } else 

            System.out.print("Topping with ");
            for (int i = 0; i < ingredients.length; i++) {
                System.out.print(ingredients[i].getName());
                if (i < ingredients.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            
            System.out.println("Putting in the oven...");
            System.out.println("Delicious pizza baking!");
            System.out.println("Packing your delicious pizza...");
            System.out.println("Pizza done!");
            System.out.println("Enjoy your " + customProduct.getName()+ " pizza!");
            System.out.println("==================================");
            

            // Update transaction
            
            transac.setCurrentAmountCollected(-customProduct.getPrice());
            transac.updateTotalAmountCollected(customProduct.getPrice());
        } else {
            System.out.println("ERR: == Product cannot be dispensed due to missing ingredients.");
        }

    }

    public void dispenseProduct(int slotIndex) {
        int numProductSlots = getProductNumSlots();
        Transaction transac = getTransaction();
    
        if (slotIndex < 0 || slotIndex >= numProductSlots) {
            System.out.println("ERR: == Invalid product slot index.");
            return;
        }
    
        ProductSlot productSlot = getProductSlot(slotIndex);
        Product product = productSlot.getProduct();
    
        if (product == null) {
            System.out.println("ERR: == Product is not available in this slot.");
            return;
        }
    
        Item[] ingredients = product.getIngredients();
    
        // Check if all ingredients are available in the vending machine
        boolean allIngredientsAvailable = true;
        for (Item ingredient : ingredients) {
            boolean foundIngredient = false;
            for (ItemSlot itemSlot : getItemSlots()) {
                if (itemSlot.getItem() == ingredient && itemSlot.getQuantity() > 0) {
                    // Decrement the quantity of the ingredient in the vending machine
                    itemSlot.decrementQuantity();
                    foundIngredient = true;
                    break;
                }
            }
            if (!foundIngredient) {
                allIngredientsAvailable = false;
                break;
            }
        }
    
        if (allIngredientsAvailable) {
            // If all ingredients are available, dispense the product
            System.out.println("==================================");
            System.out.println("Your delicious pizza is being prepared!");
            System.out.println("Kneading dough...");
            System.out.println("Pouring pizza sauce on the dough...");

                Item[] selectedIngredients = product.getIngredients();
                System.out.print("Topping with ");
                for (int i = 0; i < selectedIngredients.length; i++) {
                    System.out.print(selectedIngredients[i].getName());
                    if (i < selectedIngredients.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();

            System.out.println("Putting in the oven...");
            System.out.println("Delicious pizza baking!");
            System.out.println("Packing your delicious pizza...");
            System.out.println("Pizza done!");
            System.out.println("Enjoy your delicious " + product.getName() +" pizza!");
            productSlot.decrementQuantity();
            System.out.println("|== Product dispensed: " + product.getName());
            System.out.println("==================================");
    
            // Update transaction
            
            //update item quantity sold in transaction
            transac.updateItemQuantity(slotIndex, 1);

            transac.setCurrentAmountCollected(-product.getPrice());
            transac.updateTotalAmountCollected(product.getPrice());
        } else {
            System.out.println("ERR: == Product cannot be dispensed due to missing ingredients.");
        }
    }
    
    //@Override
    //public void printTransactionSummary()

    



    @Override
    public void dispenseItem(int slotIndex) {
        // Implement the specific dispenseItem logic for the regular vending machine
        // ...

        int numSlots = super.getNumSlots();


        if (slotIndex < 0 || slotIndex >= numSlots) {
            System.out.println("ERR: == Invalid slot index.");
            return;
        }

        ItemSlot slot = super.itemSlots[slotIndex];

        if (slot.getQuantity() <= 0) {
            System.out.println("ERR: == Item is out of stock.");
            return;
        }

        Transaction transac = super.getTransaction();

        if (transac.getCurrentAmountCollected() < slot.getPrice()) {
            System.out.println("ERR: == Insufficient payment.");
            return;
        }

        // Dispense item

        slot.setQuantity(slot.getQuantity() - 1);
        transac.updateItemQuantity(slotIndex, 1);
        transac.setCurrentAmountCollected(-slot.getPrice());
        transac.updateTotalAmountCollected(slot.getPrice());

        System.out.println("|== Item dispensed: " + slot.getItem().getName());
    }

    


    @Override
    public String produceChange(int choice) {
    // Implement the specific produceChange logic for the regular vending machine
    // ...

    double totalAmountCollected = transaction.getCurrentAmountCollected();

    if (totalAmountCollected == 0) {
        return "== Thank you for using our vending machine. ==";
    }

    double change = totalAmountCollected;

        int count1000 = 0;
        int count500 = 0;
        int count200 = 0;
        int count100 = 0;
        int count50 = 0;
        int count20 = 0;
        int count10 = 0;
        int count5 = 0;
        int count1 = 0;

        while (change >= 1000 && moneyDenominations.getCount1000() > 0) {
            change -= 1000;
            count1000++;
            moneyDenominations.setCount1000(moneyDenominations.getCount1000() - 1);
        }

        while (change >= 500 && moneyDenominations.getCount500() > 0) {
            change -= 500;
            count500++;
            moneyDenominations.setCount500(moneyDenominations.getCount500() - 1);
        }

        while (change >= 200 && moneyDenominations.getCount200() > 0) {
            change -= 200;
            count200++;
            moneyDenominations.setCount200(moneyDenominations.getCount200() - 1);
        }

        while (change >= 100 && moneyDenominations.getCount100() > 0) {
            change -= 100;
            count100++;
            moneyDenominations.setCount100(moneyDenominations.getCount100() - 1);
        }

        while (change >= 50 && moneyDenominations.getCount50() > 0) {
            change -= 50;
            count50++;
            moneyDenominations.setCount50(moneyDenominations.getCount50() - 1);
        }

        while (change >= 20 && moneyDenominations.getCount20() > 0) {
            change -= 20;
            count20++;
            moneyDenominations.setCount20(moneyDenominations.getCount20() - 1);
        }

        while (change >= 10 && moneyDenominations.getCount10() > 0) {
            change -= 10;
            count10++;
            moneyDenominations.setCount10(moneyDenominations.getCount10() - 1);
        }

        while (change >= 5 && moneyDenominations.getCount5() > 0) {
            change -= 5;
            count5++;
            moneyDenominations.setCount5(moneyDenominations.getCount5() - 1);
        }

        while (change >= 1 && moneyDenominations.getCount1() > 0) {
            change -= 1;
            count1++;
            moneyDenominations.setCount1(moneyDenominations.getCount1() - 1);
        }

    StringBuilder output = new StringBuilder();

    if (change > 0) {
        output.append("ERR: == Not enough change in the machine. Transaction cancelled.\n");
        // Roll back the transaction
        
            transaction.setCurrentAmountCollected(totalAmountCollected);
            moneyDenominations.setCount1000(moneyDenominations.getCount1000() + count1000);
            moneyDenominations.setCount500(moneyDenominations.getCount500() + count500);
            moneyDenominations.setCount200(moneyDenominations.getCount200() + count200);
            moneyDenominations.setCount100(moneyDenominations.getCount100() + count100);
            moneyDenominations.setCount50(moneyDenominations.getCount50() + count50); 
            moneyDenominations.setCount20(moneyDenominations.getCount20() + count20);
            moneyDenominations.setCount10(moneyDenominations.getCount10() + count10);
            moneyDenominations.setCount5(moneyDenominations.getCount5() + count5);
            moneyDenominations.setCount1(moneyDenominations.getCount1() + count1);
    } else {
        output.append("Change produced:\n");
        output.append("| 1000 PHP: ").append(count1000).append("\n");
        output.append("| 500 PHP: ").append(count500).append("\n");
        output.append("| 200 PHP: ").append(count200).append("\n");
        output.append("| 100 PHP: ").append(count100).append("\n");
        output.append("| 50 PHP: ").append(count50).append("\n");
        output.append("| 20 PHP: ").append(count20).append("\n");
        output.append("| 10 PHP: ").append(count10).append("\n");
        output.append("| 5 PHP: ").append(count5).append("\n");
        output.append("| 1 PHP: ").append(count1).append("\n");

        output.append("Thank you for using our Vending Machine!\n");

        // If the user cancels the transaction, the money is returned to the vending machine
        if (choice == 2) {
            output.append("== Thank you for using our vending machine. ==\n");
            
                System.out.println("== Thank you for using our vending machine. ==");
                transaction.setCurrentAmountCollected(0);
                moneyDenominations.setCount1000(moneyDenominations.getCount1000() + count1000);
                moneyDenominations.setCount500(moneyDenominations.getCount500() + count500);
                moneyDenominations.setCount200(moneyDenominations.getCount200() + count200);
                moneyDenominations.setCount100(moneyDenominations.getCount100() + count100);
                moneyDenominations.setCount50(moneyDenominations.getCount50() + count50);   
                moneyDenominations.setCount20(moneyDenominations.getCount20() + count20);
                moneyDenominations.setCount10(moneyDenominations.getCount10() + count10);
                moneyDenominations.setCount5(moneyDenominations.getCount5() + count5);
                moneyDenominations.setCount1(moneyDenominations.getCount1() + count1);
        }
    }
    return output.toString();
}
    

    @Override
    public void replenishChange() {
        // Implement the specific replenishChange logic for the regular vending machine
        // ...

        moneyDenominations.setCount1000(50);
        moneyDenominations.setCount500(50);
        moneyDenominations.setCount100(50);
        moneyDenominations.setCount50(50);
        moneyDenominations.setCount20(50);
        moneyDenominations.setCount10(50);
        moneyDenominations.setCount5(50);
        moneyDenominations.setCount1(50);
    }

    // Additional methods specific to the SpecialVendingMachine (if needed)
    // ...

    public void restockProducts(int slotProduct, int quantity){
        ProductSlot productSlot = productSlots[slotProduct];
        productSlot.setQuantity(productSlot.getQuantity() + quantity);
    }

    public void setProductPrice(int slotProduct, double price){
        ProductSlot productSlot = productSlots[slotProduct];
        productSlot.setPrice(price);
    }

}