package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class
/**
 * VendingSim is a program that simulates a vending machine.
 * It allows the user to create a vending machine and test its features.
 */

public class VendingSim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Scanner object to read input from user
        VendingMachine vendingMachine = null;

        // Main menu
        // Loop until user chooses to exit
        // Each choice will call the respective method

        // Create the controller for the vending machine model

        while (true) {
            System.out.println("===== Vending Machine Simulator =====");
            System.out.println("1. Create a Vending Machine");
            System.out.println("2. Test a Vending Machine");
            System.out.println("3. Exit");
            System.out.print("|== Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("== Choose the type of vending machine: ==");
                    System.out.println("1. Regular");
                    System.out.println("2. Special");
                    System.out.print("|== Enter your choice: ");
                    int vendingMachineType = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    int numSlots = 0;
                    int numProductSlots = 0;
                    
                    do{
                        System.out.print("|== Enter the number of item slots (minimum 8): ");
                        numSlots = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (numSlots < 8) {
                            System.out.println("ERR: == Invalid number of item slots. Minimum is 8. ==");
                        }
                    } while (numSlots < 8);

                    if (vendingMachineType == 2) {
                        do {
                            System.out.print("|== Enter the number of product slots: ");
                            numProductSlots = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                        } while (numProductSlots < 1);
                    }

                    // Create the vending machine
                    // If vendingMachineType is 1, create a regular vending machine
                    // If vendingMachineType is 2, create a special vending machine
                    // Otherwise, print an error message

                    if (vendingMachineType == 1) {
                        vendingMachine = new RegularVendingMachine(numSlots);
                    } else if (vendingMachineType == 2) {
                        vendingMachine = new SpecialVendingMachine(numSlots, numProductSlots);
                    } else {
                        System.out.println("ERR: == Invalid vending machine type! ==");
                    }

                    // Add items to the vending machine
                    // If vendingMachine is not null, add items to the vending machine
                    // Otherwise, print an error message

                    if (vendingMachine != null) {
                        int quantity;
                        for (int i = 0; i < numSlots - 1; i++) {
                            System.out.println("== Adding item to slot " + (i + 1) + " ==");
                            System.out.print("|== Enter the item name: ");
                            String name = scanner.nextLine();

                            do{
                                System.out.print("|== Enter the item quantity (minimum 10): ");
                                quantity = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                if (quantity < 10) {
                                    System.out.println("ERR: == Invalid item quantity. Minimum is 10. ==");
                                }
                        }while(quantity < 10); // Minimum 10 items

                            System.out.print("|== Enter the item price: ");
                            double price = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline character
                            System.out.print("|== Enter the item calories: ");
                            int calories = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            Item item = new Item(name, price, calories);                // Create the item
                            vendingMachine.setItem(i, item, quantity, price, calories); // Add the item to the vending machine
                            //hardcode an item
                            vendingMachine.setItem(numSlots - 1, new Item("Star Anise", 21, 140), 10, 21, 140);
                        }
                    }

                    if (vendingMachine instanceof SpecialVendingMachine) {
                        SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;

                        for (int i = 0; i < numProductSlots; i++) {
                            System.out.println("== Adding product to product slot " + (i + 1) + " ==");
                
                            // Get product details from user input
                            System.out.print("|== Enter the product name: ");
                            String productName = scanner.nextLine();
                            System.out.print("|== Enter the product description: ");
                            String productDescription = scanner.nextLine();
            
                            System.out.print("|== Enter the product price: ");
                            double productPrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline character
                
                            // Get ingredients for the product
                            List<Item> ingredients = new ArrayList<>();
                            int totalCalories = 0;

                            System.out.println("== Choose the ingredients required for the available pizza: ==");

                            // Display the available ingredients in the vending machine
                            for (int k = 0; k < vendingMachine.getNumSlots(); k++) {
                                ItemSlot itemSlot = vendingMachine.getItemSlot(k);
                                Item item = itemSlot.getItem();
                                System.out.println((k + 1) + ". " + item.getName() + " (" + item.getCalories() + " calories)");
                            }

                            while (true) {
                                System.out.print("|== Enter the slot number to add an ingredient (0 to finish): ");
                                int slotNumber = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                    
                                if (slotNumber == 0) {
                                    break;
                                }
                    
                                if (slotNumber < 1 || slotNumber > vendingMachine.getNumSlots()) {
                                    System.out.println("ERR: == Invalid slot number! Please try again. ==");
                                    continue;
                                }
                    
                                Item selectedIngredient = vendingMachine.getItemSlot(slotNumber - 1).getItem();
                                ingredients.add(selectedIngredient);
                                totalCalories += selectedIngredient.getCalories() + 335;
                            }
                
                            // Create the product
                            Item[] ingredientsArray = ingredients.toArray(new Item[0]);
                            specialVendingMachine.addProductToSlot(i, productName, productDescription, productPrice, totalCalories, ingredientsArray);
                        }
                    }

                    break;
                case 2:
                    // Test the vending machine
                    // If vendingMachine is null, print an error message
                    // Otherwise, perform the tests
                    
                    if (vendingMachine == null) {   
                        System.out.println("ERR: == No vending machine created yet! Please create one first. : ) ==");
                        break;
                    }

                    System.out.println("== Choose the test option: ==");
                    System.out.println("1. Vending Features");
                    System.out.println("2. Maintenance Features");
                    System.out.print("|== Enter your choice: ");
                    int testOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (testOption == 1) {
                        // Perform vending features tests
                        // Loop until user chooses to exit

                        int featureChoice = 0;

                        //ask the user if they want to purchase an Item or a product

                        do{
                            System.out.println("== Choose the feature: ==");
                            System.out.println("1. Purchase an item");
                            System.out.println("2. Purchase a product");
                            System.out.println("3. Exit");
                            System.out.print("|== Enter your choice: ");
                            featureChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            if (featureChoice < 1 || featureChoice > 3) {
                                System.out.println("ERR: == Invalid feature choice! ==");
                            }
                        }while(featureChoice < 1 || featureChoice > 3);

                        if(featureChoice == 1){

                        System.out.println("===== Vending Features =====");
                        System.out.println("== Purchase an item ==");

                            //display items

                            System.out.println("===== Items =====");
                            for (int i = 0; i < vendingMachine.getNumSlots(); i++) {
                                ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                Item item = itemSlot.getItem();
                                System.out.println("Slot:" + (i + 1) + ". " + item.getName() + " (" + item.getPrice() + ") - " + itemSlot.getQuantity() + " left" + " - " + item.getCalories() + " calories");
                            }
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

                            vendingMachine.receivePayment(payment);

                            scanner.nextLine(); // Consume newline character

                            // Purchase an item
                            // Ask the user to enter the slot number
                            // If the slot number is invalid, print an error message

                            System.out.print("|== Enter the slot number to purchase from: ");
                            int slotNumber = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            
                            //Ask the user if they want to continue or exit

                            System.out.print("|== Do you want to (1) Continue or (2) Exit: ");
                            int choice1 = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            if(choice1 == 1) {
                                vendingMachine.dispenseItem(slotNumber - 1);
                                System.out.println("== Your change is: ==");
                                vendingMachine.produceChange(1); //give the change
                                vendingMachine.setCurToZero();  //set current money to zero 
                                
                            }
                            else if(choice1 == 2) {
                                System.out.println("== Thank you for using our vending machine! ==");
                                //give back the money
                                System.out.println("== Your change is: ==");
                                vendingMachine.produceChange(2); //give back the money
                                vendingMachine.setCurToZero();  //set current money to zero

                                break;           
                            }
                            else System.out.println("ERR: == Invalid choice! ==");
                        }
                        else if(featureChoice == 2){

                            int productChoice = 0;

                            System.out.println("===== Vending Features =====");
                            System.out.println("== Purchase a product ==");

                            //ask user whether they want to purchase an available product or a customized product

                            do{
                                System.out.println("== Choose the product: ==");
                                System.out.println("1. Available product");
                                System.out.println("2. Customized product");
                                System.out.println("3. Exit");
                                System.out.print("|== Enter your choice: ");
                                productChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                if (productChoice < 1 || productChoice > 3) {
                                    System.out.println("ERR: == Invalid product choice! ==");
                                }
                            }while(productChoice < 1 || productChoice > 3);

                            //display products

                            if(productChoice == 1){

                                if (vendingMachine instanceof SpecialVendingMachine) {
                                    SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                    System.out.println("===== Available Products =====");
                                    for (int i = 0; i < speVendingMachine.getProductNumSlots(); i++) {
                                        ProductSlot productSlot = speVendingMachine.getProductSlot(i);
                                        Product product = productSlot.getProduct();
                                        System.out.println("Slot:" + (i + 1) + ". " + product.getName() + " (" + product.getPrice() + ") - " + productSlot.getQuantity() + " left" + " - " + product.getCalories() + " calories");
                                    }


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

                                    speVendingMachine.receivePayment(payment);

                                    scanner.nextLine(); // Consume newline character

                                    // Purchase a product
                                    // Ask the user to enter the slot number
                                    // If the slot number is invalid, print an error message

                                    System.out.print("|== Enter the slot number to purchase from: ");
                                    int slotNumber = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline character
                                    
                                    //Ask the user if they want to continue or exit

                                    System.out.print("|== Do you want to (1) Continue or (2) Exit: ");

                                    int choice1 = scanner.nextInt();

                                    scanner.nextLine(); // Consume newline character

                                    if(choice1 == 1) {
                                        speVendingMachine.dispenseProduct(slotNumber - 1);
                                        System.out.println("== Your change is: ==");
                                        speVendingMachine.produceChange(1); //give the change
                                        speVendingMachine.setCurToZero();  //set current money to zero 
                                        
                                    }
                                    else if(choice1 == 2) {
                                        System.out.println("== Thank you for using our vending machine! ==");
                                        //give back the money
                                        System.out.println("== Your change is: ==");
                                        speVendingMachine.produceChange(2); //give back the money
                                        speVendingMachine.setCurToZero();  //set current money to zero

                                        break;           
                                    }
                                    else System.out.println("ERR: == Invalid choice! ==");
                                } else {
                                    System.out.println("ERR: == This vending machine does not support customized products! ==");
                                }

                            }
                            else if(productChoice == 2){
                                // Order a customized product
                                if (vendingMachine instanceof SpecialVendingMachine) {
                                    SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;
                                    specialVendingMachine.orderCustomizedProduct();
                                } else {
                                    System.out.println("ERR: == This vending machine does not support customized products! ==");
                                }
                            }
                            else {
                                System.out.println("ERR: == Invalid product choice! ==");
                            }
                            
                        }
                        
                    } else if (testOption == 2) {
                        // Perform maintenance features tests
                        // Loop until user chooses to exit

                        System.out.println("===== Maintenance Features =====");
                        System.out.println("1. Restock items");
                        System.out.println("2. Set item price");
                        System.out.println("3. Collect payment/money");
                        System.out.println("4. Replenish change");
                        System.out.println("5. Print Transaction History & Update Ending Inventory");
                        System.out.print("|== Enter your choice: ");
                        int maintenanceFeatureChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (maintenanceFeatureChoice == 1) {

                            //ask user whether they want to restock items or products

                           /*  int featureChoice = 0;

                            System.out.println("===== Would you like to restock items or products? =====");
                            System.out.println("1. Items");
                            System.out.println("2. Products");
                            System.out.print("|== Enter your choice: ");
                            featureChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
 */
                            //Display items

                           

                                System.out.println("===== Items =====");
                                for (int i = 0; i < vendingMachine.getNumSlots(); i++) {
                                    ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                    Item item = itemSlot.getItem();
                                    System.out.println("| Slot:" + (i + 1) + ". " + item.getName() + " (" + item.getPrice() + ") - " + itemSlot.getQuantity() + " left");
                                }

                                // Restock items
                                // Ask the user to enter the slot number and quantity
                                // If the slot number is invalid, print an error message

                                System.out.print("|== Enter the slot number to restock: ");
                                int slotNumber = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                System.out.print("|== Enter the quantity to restock: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                vendingMachine.restockItems(slotNumber - 1, quantity);      //restock the items
                            

                            if(vendingMachine instanceof SpecialVendingMachine) {
                                //display products
                                System.out.println("===== Products =====");
                                SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                    for (int i = 0; i < speVendingMachine.getProductNumSlots(); i++) {
                                        ProductSlot productSlot = speVendingMachine.getProductSlot(i);
                                        Product product = productSlot.getProduct();
                                        System.out.println("Slot:" + (i + 1) + ". " + product.getName() + " (" + product.getPrice() + ") - " + productSlot.getQuantity() + " left" + " - " + product.getCalories() + " calories");
                                    }

                                System.out.print("|== Enter the slot number to set restock: ");
                                int slotNumber2 = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                System.out.print("|== Enter the quantity to restock: ");
                                int quantity2 = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                speVendingMachine.restockProducts(slotNumber2 - 1, quantity2);      //restock the products

                            }

                        } else if (maintenanceFeatureChoice == 2) {
                            
                            //Set item price
                            //Display items

                           /*  int featureChoice = 0;

                            if (featureChoice == 1){

                                System.out.println("===== Would you like to set price to items or products? =====");
                                System.out.println("1. Items");
                                System.out.println("2. Products");
                                System.out.print("|== Enter your choice: ");
                                featureChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character */

                                System.out.println("===== Items =====");
                                for (int i = 0; i < vendingMachine.getNumSlots(); i++) {
                                    ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                    Item item = itemSlot.getItem();
                                    System.out.println("| Slot:" + (i + 1) + ". " + item.getName() + " (" + item.getPrice() + ") - " + itemSlot.getQuantity() + " left");
                                }

                                System.out.print("|== Enter the slot number to set price: ");
                                int slotNumber1 = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                System.out.print("|== Enter the new price: ");
                                double price = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline character

                                vendingMachine.getItemSlot(slotNumber1 - 1).getItem().setPrice(price);       //Set the price
                                vendingMachine.setItemPrice(slotNumber1 - 1, price);
                            

                            if(vendingMachine instanceof SpecialVendingMachine) {
                                //display products
                                System.out.println("===== Products =====");
                                SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                    for (int i = 0; i < speVendingMachine.getProductNumSlots(); i++) {
                                        ProductSlot productSlot = speVendingMachine.getProductSlot(i);
                                        Product product = productSlot.getProduct();
                                        System.out.println("Slot:" + (i + 1) + ". " + product.getName() + " (" + product.getPrice() + ") - " + productSlot.getQuantity() + " left" + " - " + product.getCalories() + " calories");
                                    }

                                System.out.print("|== Enter the slot number to set price: ");
                                int slotNumber2 = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                System.out.print("|== Enter the new price: ");
                                double price1 = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline character

                                speVendingMachine.getProductSlot(slotNumber2 - 1).getProduct().setPrice(price1);       //Set the price
                                speVendingMachine.setProductPrice(slotNumber2 - 1, price1);


                            }

                        } else if (maintenanceFeatureChoice == 3) {
                            
                            // Collect payment/money

                            vendingMachine.collectPayment();
                        } else if (maintenanceFeatureChoice == 4) {
                            
                            // Replenish change

                            vendingMachine.replenishChange();
                        } else if (maintenanceFeatureChoice == 5){

                            // Print Transaction History & Update Ending Inventory
                            vendingMachine.printTransactionSummary(vendingMachine);
                            vendingMachine.updateEndingInventory();

                           if(vendingMachine instanceof SpecialVendingMachine) {
                                SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;
                                specialVendingMachine.printTransactionSummary(vendingMachine);
                            }
                        
                        } else {
                            System.out.println("ERR: == Invalid choice! ==");
                        }

                        
                    } else {
                        System.out.println("ERR: == Invalid test option! ==");
                    }

                    break;
                case 3:
                    System.out.println("===== Exiting the program... =====");
                    System.out.println("== Thank you for using our vending machine! ==");
                    System.exit(0);
                default:
                    System.out.println("ERR: == Invalid choice! ==");
            }
        }
        
    }
}


