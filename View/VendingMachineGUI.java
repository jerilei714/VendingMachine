package View;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.Item;
import Model.ItemSlot;
import Model.Product;
import Model.ProductSlot;
import Model.RegularVendingMachine;
import Model.SpecialVendingMachine;
import Model.Transaction;
import Model.VendingMachine;

public class VendingMachineGUI extends JFrame
{
    private VendingMachine vendingMachine;
    private JButton createVendingMachineButton;
    private JButton testVendingMachineButton;
    private JButton exitButton;


    public VendingMachineGUI()
    {
        super("Vending Machine Simulator");

        setLayout(new BorderLayout());

        // Create a JPanel for the title and set its background color to light yellow
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.YELLOW);

        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("Welcome To Vending Machine Simulator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Create a JPanel to hold the buttons and set its layout to center the components
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        createVendingMachineButton = new JButton("Create a Vending Machine");
        testVendingMachineButton = new JButton("Test a Vending Machine");
        exitButton = new JButton("Exit");

        // Set the button colors
        createVendingMachineButton.setBackground(new Color(255, 200, 100)); // Light orange
        testVendingMachineButton.setBackground(new Color(100, 200, 255));   // Light blue

        // Add the buttons to the buttonPanel with GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20); // Add some spacing around the buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(createVendingMachineButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(testVendingMachineButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(exitButton, gbc);

        // Add the title panel and button panel to the main frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        
        createVendingMachineButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] options = {"Regular", "Special"};
                int type = JOptionPane.showOptionDialog(null, "What type of vending machine would you like to create?", "Create a Vending Machine",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                int numSlots = 0;
                int numProductSlots = 0;
                
                do
                {
                    String numSlotsStr = JOptionPane.showInputDialog("Enter the number of slots (minimum 8):");
                    numSlots = getValidInt(numSlotsStr);
                    if(numSlots == -1)
                    {
                        return;
                    }

                    if(numSlots < 8)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid number of item slots. Minimum is 8.");
                    }
                }while(numSlots < 8);

                if(type == 1)
                {
                    do
                    {
                        String numProductSlotsStr = JOptionPane.showInputDialog("Enter the number of product slots:");
                        numProductSlots = getValidInt(numProductSlotsStr);
                        if(numProductSlots == -1)
                        {
                            return;
                        }
                    }while(numProductSlots < 1);
                }

                if(type == 0)
                {
                    vendingMachine = new RegularVendingMachine(numSlots);
                }else if(type == 1)
                {
                    vendingMachine = new SpecialVendingMachine(numSlots, numProductSlots);
                }else
                {
                    JOptionPane.showMessageDialog(null, "Invalid vending machine type!");
                }

                if(vendingMachine != null)
                {
                    int quantity = 0;

                    for(int i = 0; i < numSlots; i++)
                    {
                        String name = JOptionPane.showInputDialog("Enter the name for item in slot " + (i + 1) + ":");

                        do
                        {
                            String quantityStr = JOptionPane.showInputDialog("Enter the item quantity for item in slot " + (i + 1) + " (minimum 10):");
                            quantity = getValidInt(quantityStr);
                            if(quantity == -1)
                            {
                                return;
                            }

                            if(quantity < 10)
                            {
                                JOptionPane.showMessageDialog(null, "Invalid item quantity. Minimum is 10.");
                            }
                        }while(quantity < 10);

                        String priceStr = JOptionPane.showInputDialog("Enter the price for item in slot " + (i + 1) + ":");
                        double price = getValidDouble(priceStr);
                        if(price == -1)
                        {
                            return;
                        }

                        String caloriesStr = JOptionPane.showInputDialog("Enter the calories for item in slot " + (i + 1) + ":");
                        int calories = getValidInt(caloriesStr);
                        if(calories == -1)
                        {
                            return;
                        }

                        Item item = new Item(name, price, calories);
                        vendingMachine.setItem(i, item, quantity, price, calories);
                    }
                }

                if(vendingMachine instanceof SpecialVendingMachine)
                {
                    SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;

                    for(int i = 0; i < numProductSlots; i++)
                    {
                        String productName = JOptionPane.showInputDialog("Enter the name for product in slot " + (i + 1) + ":");
                        String productDescription = JOptionPane.showInputDialog("Enter the description for product in slot " + (i + 1) + ":");

                        String productPriceStr = JOptionPane.showInputDialog("Enter the price for product in slot " + (i + 1) + ":");
                        double productPrice = getValidDouble(productPriceStr);
                        if(productPrice == -1)
                        {
                            return;
                        }

                        List<Item> ingredients = new ArrayList<>();
                        int totalCalories = 0;

                        StringBuilder ingredientsList = new StringBuilder("===== Ingredients =====\n");
                        for(int j = 0; j < vendingMachine.getNumSlots(); j++)
                        {
                            ItemSlot itemSlot = vendingMachine.getItemSlot(j);
                            Item item = itemSlot.getItem();
                            ingredientsList.append("| Slot:").append(j + 1).append(". ").append(item.getName()).append(" (").append(item.getPrice()).append(") - ").append(itemSlot.getQuantity()).append(" left\n");
                        }
                        ingredientsList.append("=====================");
                        
                        do{

                            String slotNumberStr = JOptionPane.showInputDialog("Enter the slot number to add an ingredient (0 to finish):" + ingredientsList.toString());
                            int slotNumber = getValidInt(slotNumberStr);
                            if(slotNumber == -1)
                            {
                                return;
                            }

                            if(slotNumber == 0)
                            {
                                break;
                            }

                            if(slotNumber < 1 || slotNumber > vendingMachine.getNumSlots())
                            {
                                JOptionPane.showMessageDialog(null, "Invalid slot number! Please try again.");
                                continue;
                            }

                            Item selectedIngredient = vendingMachine.getItemSlot(slotNumber - 1).getItem();
                            ingredients.add(selectedIngredient);
                            totalCalories += selectedIngredient.getCalories() + 335;

                        }while(true);

                        Item[] ingredientsArray = ingredients.toArray(new Item[0]);
                        specialVendingMachine.addProductToSlot(i, productName, productDescription, productPrice, totalCalories, ingredientsArray);
                    }
                }
            }
        });

        testVendingMachineButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(vendingMachine != null)
                {
                    String[] options = {"Vending Features", "Maintenance Features"};
                    int set = JOptionPane.showOptionDialog(null, "Which set of features would you like to test?", "Test a Vending Machine",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if(set == 0)
                    {
                        String[] featureOptions = {"Purchase an item", "Purchase a product", "Exit"};
                        int featureChoice = JOptionPane.showOptionDialog(null, "Choose the feature:", "Vending Features",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, featureOptions, featureOptions[0]);

                        if(featureChoice == -1)
                        {
                            return; // User closed the dialog or clicked outside the options
                        }

                        switch(featureChoice)
                        {
                            case 0:
                                //Logic goes here;

                                System.out.println("===== Vending Features =====");
                                System.out.println("== Purchase an item ==");

                                // Display items in a separate window using JTextArea
                                JTextArea itemsTextArea = new JTextArea(10, 30);
                                JScrollPane itemsScrollPane = new JScrollPane(itemsTextArea);
                                itemsTextArea.setEditable(false);

                                // Populate the itemsTextArea with the items
                                StringBuilder itemsList = new StringBuilder("===== Items =====\n");
                                for(int i = 0; i < vendingMachine.getNumSlots(); i++)
                                {
                                    ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                    Item item = itemSlot.getItem();
                                    itemsList.append("Slot:").append(i + 1).append(". ").append(item.getName()).append(" (").append(item.getPrice()).append(") - ").append(itemSlot.getQuantity()).append(" left").append(" - ").append(item.getCalories()).append(" calories\n");
                                }
                                itemsList.append("=====================");
                                itemsTextArea.setText(itemsList.toString());

                                // Show items in a separate window
                                JFrame itemsFrame = new JFrame("Items");
                                itemsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                itemsFrame.add(itemsScrollPane);
                                itemsFrame.pack();
                                itemsFrame.setLocationRelativeTo(null);
                                itemsFrame.setVisible(true);

                                // Ask the user to enter the money using GUI dialogs
                                JPanel moneyPanel = new JPanel();
                                moneyPanel.setLayout(new GridLayout(9, 2));
                                moneyPanel.add(new JLabel("How many 1000's:"));
                                JTextField thousandsField = new JTextField();
                                moneyPanel.add(thousandsField);
                                moneyPanel.add(new JLabel("How many 500's:"));
                                JTextField fiveHundredsField = new JTextField();    
                                moneyPanel.add(fiveHundredsField);
                                moneyPanel.add(new JLabel("How many 200's:"));
                                JTextField twoHundredsField = new JTextField();
                                moneyPanel.add(twoHundredsField);
                                moneyPanel.add(new JLabel("How many 100's:"));
                                JTextField hundredsField = new JTextField();
                                moneyPanel.add(hundredsField);
                                moneyPanel.add(new JLabel("How many 50's:"));
                                JTextField fiftiesField = new JTextField();
                                moneyPanel.add(fiftiesField);
                                moneyPanel.add(new JLabel("How many 20's:"));
                                JTextField twentiesField = new JTextField();
                                moneyPanel.add(twentiesField);
                                moneyPanel.add(new JLabel("How many 10's:"));
                                JTextField tensField = new JTextField();
                                moneyPanel.add(tensField);
                                moneyPanel.add(new JLabel("How many 5's:"));
                                JTextField fivesField = new JTextField();
                                moneyPanel.add(fivesField);
                                moneyPanel.add(new JLabel("How many 1's:"));
                                JTextField onesField = new JTextField();
                                moneyPanel.add(onesField);

                                int result = JOptionPane.showConfirmDialog(null, moneyPanel, "Enter the money", JOptionPane.OK_CANCEL_OPTION);
                                if(result == JOptionPane.CANCEL_OPTION)
                                {
                                    itemsFrame.dispose(); // Close the items window if the user cancels
                                    return;
                                }

                                int thousands = Integer.parseInt(thousandsField.getText());
                                int fiveHundreds = Integer.parseInt(fiveHundredsField.getText());
                                int twoHundreds = Integer.parseInt(twoHundredsField.getText());
                                int hundreds = Integer.parseInt(hundredsField.getText());
                                int fifties = Integer.parseInt(fiftiesField.getText());
                                int twenties = Integer.parseInt(twentiesField.getText());
                                int tens = Integer.parseInt(tensField.getText());
                                int fives = Integer.parseInt(fivesField.getText());
                                int ones = Integer.parseInt(onesField.getText());

                                // Payment calculation
                                int payment = (thousands * 1000) + (fiveHundreds * 500) + (twoHundreds * 200) + (hundreds * 100) + (fifties * 50) + (twenties * 20) + (tens * 10) + (fives * 5) + ones;

                                // Receive payment
                                vendingMachine.receivePayment(payment);

                                // Ask the user to enter the slot number using a GUI dialog
                                String slotNumberStr = JOptionPane.showInputDialog("Enter the slot number to purchase from:");
                                int slotNumber = getValidInt(slotNumberStr);
                                if(slotNumber == -1)
                                {
                                    itemsFrame.dispose(); // Close the items window if the user cancels
                                    return;
                                }

                                String[] continueOptions = {"Continue", "Exit"};
                                int choice1 = JOptionPane.showOptionDialog(null, "Do you want to continue or exit?", "Continue or Exit",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, continueOptions, continueOptions[0]);

                                if(choice1 == 0)
                                { // 0 corresponds to "Continue" in the options array
                                    // Dispense the customized product
                                        vendingMachine.dispenseItem(slotNumber - 1);
                                        JOptionPane.showMessageDialog(null, "Item " + vendingMachine.getItemSlot(slotNumber -1).getItem().getName() + " dispensed successfully!");
                    
                                
                                    try
                                    {
                                        String produceChange = vendingMachine.produceChange(1);
                                        JOptionPane.showMessageDialog(null, "Change Produced " + produceChange);
                                    }catch(Exception e1)
                                    {
                                        JOptionPane.showMessageDialog(null, "An error occurred while collecting the payment: " + e1.getMessage());
                                    }
                                        vendingMachine.setCurToZero(); // Set current money to zero
                                }else if(choice1 == 1)
                                { // 1 corresponds to "Exit" in the options array
                                    JOptionPane.showMessageDialog(null, "Thank you for using the vending machine!");
                                    // Give back the money
                                    try
                                    {
                                        String produceChange = vendingMachine.produceChange(2);
                                        JOptionPane.showMessageDialog(null, "Change Produced " + produceChange);
                                    }catch(Exception e2)
                                    {
                                        JOptionPane.showMessageDialog(null, "An error occurred while collecting the payment: " + e2.getMessage());
                                    }
                                        vendingMachine.setCurToZero(); // Set current money to zero
                                }else
                                {
                                    JOptionPane.showMessageDialog(null, "Invalid choice!");
                                }
                                 
                                itemsFrame.dispose(); // Close the items window after the purchase is complete
                                break;

                            case 1:
                                //Logic goes here;

                                if (!(vendingMachine instanceof SpecialVendingMachine)) {
                                    JOptionPane.showMessageDialog(null, "This vending machine does not support products!");
                                    break;
                                }


                                System.out.println("===== Vending Features =====");
                                System.out.println("== Purchase a Product ==");


                                String[] prodOptions = {"Available product", "Customized product", "Exit"};

                                    // Handle the selected choice based on the user's i

                                    int productChoice = JOptionPane.showOptionDialog(
                                            null,
                                            "Choose the product:",
                                            "Product Features",
                                            JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            prodOptions,
                                            prodOptions[0]
                                    );

                                    switch (productChoice) {
                                        case 0:

                                            purchaseAvailableProduct();
                                            
                                            break;
                                        case 1:

                                            orderCustomizedProduct();
                                            
                                            break;
                                        case 2:
                                            // Exit the program or perform other actions
                                            break;
                                    }

                                // Populate the itemsTextArea with the items
                                
                                
                                break;
                        }
                    }else if(set == 1)
                    {
                        String[] maintenanceOptions = {
                            "Restock items",
                            "Set item price",
                            "Collect payment/money",
                            "Replenish change",
                            "Print Transaction History & Update Ending Inventory"
                        };

                        int maintenanceFeatureChoice = JOptionPane.showOptionDialog(
                            null,
                            "Choose the feature:",
                            "Maintenance Features",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            maintenanceOptions,
                            maintenanceOptions[0]
                        );

                        if(maintenanceFeatureChoice == -1)
                        {
                            return; // User clicked "Cancel" or closed the dialog
                        }

                        // Incrementing the selected choice since JOptionPane gives an index value
                        maintenanceFeatureChoice++;

                        switch(maintenanceFeatureChoice)
                        {
                            case 1:
                                while(true)
                                {
                                    // Display items
                                    StringBuilder itemsList = new StringBuilder("===== Items =====\n");
                                    for(int i = 0; i < vendingMachine.getNumSlots(); i++)
                                    {
                                        ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                        Item item = itemSlot.getItem();
                                        itemsList.append("| Slot:").append(i + 1).append(". ").append(item.getName()).append(" (").append(item.getPrice()).append(") - ").append(itemSlot.getQuantity()).append(" left\n");
                                    }
                                    itemsList.append("=====================");

                                    // Display products
                                    StringBuilder productsList = new StringBuilder();
                                    if(vendingMachine instanceof SpecialVendingMachine)
                                    {
                                        SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                        productsList.append("===== Products =====\n");
                                        for(int i = 0; i < speVendingMachine.getProductNumSlots(); i++)
                                        {
                                            ProductSlot productSlot = speVendingMachine.getProductSlot(i);
                                            Product product = productSlot.getProduct();
                                            productsList.append("| Slot:").append(i + 1).append(". ").append(product.getName()).append(" (").append(product.getPrice()).append(") - ").append(productSlot.getQuantity()).append(" left\n");
                                        }
                                        productsList.append("=====================");
                                    }

                                    // Create a JLabel to display the items and products lists
                                    JLabel listsLabel = new JLabel("<html>" + itemsList.toString() + "<br><br>" + productsList.toString() + "</html>");

                                    // Create JTextFields for slot number and quantity input
                                    JTextField slotNumberField = new JTextField(5);
                                    JTextField quantityField = new JTextField(5);

                                    // Create a JPanel to hold the input fields
                                    JPanel inputPanel = new JPanel();
                                    inputPanel.setLayout(new GridLayout(2, 2));
                                    inputPanel.add(new JLabel("Enter the slot number to restock:"));
                                    inputPanel.add(slotNumberField);
                                    inputPanel.add(new JLabel("Enter the quantity to restock:"));
                                    inputPanel.add(quantityField);

                                    // Create an array of JPanels to display items list, products list, and input fields
                                    JPanel[] panels = {
                                            new JPanel(new BorderLayout()),
                                            new JPanel(new BorderLayout()),
                                            inputPanel
                                    };

                                    // Add the listsLabel to the first panel
                                    panels[0].add(listsLabel);

                                    while(true)
                                    {
                                        // Ask the user to choose whether to restock items or products
                                        String[] restockOptions = {"Items", "Products"};
                                        int restockChoice = JOptionPane.showOptionDialog(
                                                null,
                                                panels,
                                                "Restock",
                                                JOptionPane.DEFAULT_OPTION,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                restockOptions,
                                                restockOptions[0]
                                        );

                                        // Check if the user clicked "Cancel" or closed the dialog
                                        if(restockChoice == -1)
                                        {
                                            return;
                                        }

                                        // Incrementing the selected choice since JOptionPane gives an index value
                                        restockChoice++;

                                        // Parse slot number and quantity from JTextFields
                                        int slotNumber = getValidInt(slotNumberField.getText());
                                        int quantity = getValidInt(quantityField.getText());

                                        // Check if the slot number and quantity are valid
                                        if(slotNumber == -1 || quantity == -1)
                                        {
                                            return;
                                        }

                                        // Check if the slot number exists in the vending machine
                                        if(restockChoice == 1 && (slotNumber < 1 || slotNumber > vendingMachine.getNumSlots()))
                                        {
                                            JOptionPane.showMessageDialog(null, "Invalid slot number for items. Please enter a valid slot number.");
                                            continue;
                                        }

                                        if(restockChoice == 2 && (vendingMachine instanceof SpecialVendingMachine)
                                                && (slotNumber < 1 || slotNumber > ((SpecialVendingMachine) vendingMachine).getProductNumSlots()))
                                        {
                                            JOptionPane.showMessageDialog(null, "Invalid slot number for products. Please enter a valid slot number.");
                                            continue;
                                        }

                                        // Restock items or products based on the user's choice
                                        if(restockChoice == 1)
                                        {
                                            vendingMachine.restockItems(slotNumber - 1, quantity);
                                            JOptionPane.showMessageDialog(null, "Items restocked successfully!");
                                        }else if(vendingMachine instanceof SpecialVendingMachine && restockChoice == 2)
                                        {
                                            SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                            speVendingMachine.restockProducts(slotNumber - 1, quantity);
                                            JOptionPane.showMessageDialog(null, "Products restocked successfully!");
                                        }

                                        // Update the listsLabel to reflect the changes after restocking
                                        itemsList = new StringBuilder("===== Items =====\n");
                                        for(int i = 0; i < vendingMachine.getNumSlots(); i++)
                                        {
                                            ItemSlot itemSlot = vendingMachine.getItemSlot(i);
                                            Item item = itemSlot.getItem();
                                            itemsList.append("| Slot:").append(i + 1).append(". ").append(item.getName()).append(" (").append(item.getPrice()).append(") - ").append(itemSlot.getQuantity()).append(" left\n");
                                        }
                                        itemsList.append("=====================");

                                        productsList = new StringBuilder();
                                        if(vendingMachine instanceof SpecialVendingMachine)
                                        {
                                            SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;
                                            productsList.append("===== Products =====\n");
                                            for(int i = 0; i < speVendingMachine.getProductNumSlots(); i++)
                                            {
                                                ProductSlot productSlot = speVendingMachine.getProductSlot(i);
                                                Product product = productSlot.getProduct();
                                                productsList.append("| Slot:").append(i + 1).append(". ").append(product.getName()).append(" (").append(product.getPrice()).append(") - ").append(productSlot.getQuantity()).append(" left\n");
                                            }
                                            productsList.append("=====================");
                                        }

                                        listsLabel.setText("<html>" + itemsList.toString() + "<br><br>" + productsList.toString() + "</html");

                                        break; // Break the loop after successfully restocking items or products
                                    }
                                }

                            case 2:
                                //Logic goes here;

                                // Display items

                                // Create a panel for the set price form
                                JPanel setPriceFormPanel = new JPanel();
                                setPriceFormPanel.setLayout(new BoxLayout(setPriceFormPanel, BoxLayout.Y_AXIS));
                                JLabel slotNumberLabel = new JLabel("Enter the slot number to set price:");
                                JTextField slotNumberField = new JTextField();
                                JLabel priceLabel = new JLabel("Enter the new price:");
                                JTextField priceField = new JTextField();
                                setPriceFormPanel.add(slotNumberLabel);
                                setPriceFormPanel.add(slotNumberField);
                                setPriceFormPanel.add(priceLabel);
                                setPriceFormPanel.add(priceField);

                                while(true)
                                {
                                    // Ask the user to choose whether to set price for items or products
                                    String[] priceOptions = {"Items", "Products"};
                                    int priceChoice = JOptionPane.showOptionDialog(
                                            null,
                                            "Choose the feature:",
                                            "Set Price",
                                            JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            priceOptions,
                                            priceOptions[0]
                                    );

                                    // Check if the user clicked "Cancel" or closed the dialog
                                    if(priceChoice == -1)
                                    {
                                        return;
                                    }

                                    // Incrementing the selected choice since JOptionPane gives an index value
                                    priceChoice++;

                                    if(priceChoice == 1)
                                    {
                                        displayItems(vendingMachine);
                                    }else if(priceChoice == 2 && vendingMachine instanceof SpecialVendingMachine)
                                    {
                                        displayProducts((SpecialVendingMachine) vendingMachine);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "This vending machine does not support products.\n");
                                        return;
                                    }

                                    // Ask the user to enter the slot number and price
                                    String slotNumberStr = JOptionPane.showInputDialog("Enter the slot number to set price:");
                                    String priceStr = JOptionPane.showInputDialog("Enter the new price:");

                                    // Check if the user clicked "Cancel" or closed the dialogs
                                    if(slotNumberStr == null || priceStr == null)
                                    {
                                        return;
                                    }

                                    int slotNumber = getValidInt(slotNumberStr);
                                    double price = getValidDouble(priceStr);

                                    // Check if the slot number and price are valid
                                    if(slotNumber == -1 || price == -1)
                                    {
                                        return;
                                    }

                                    // Check if the slot number exists in the vending machine
                                    boolean isValidSlot = false;
                                    if(priceChoice == 1 && (slotNumber >= 1 && slotNumber <= vendingMachine.getNumSlots()))
                                    {
                                        isValidSlot = true;
                                    }else if(priceChoice == 2 && vendingMachine instanceof SpecialVendingMachine
                                            && (slotNumber >= 1 && slotNumber <= ((SpecialVendingMachine) vendingMachine).getProductNumSlots()))
                                    {
                                        isValidSlot = true;
                                    }

                                    if(!isValidSlot)
                                    {
                                        JOptionPane.showMessageDialog(null, "Invalid slot number. Please enter a valid slot number.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }else
                                    {
                                        // Set the price for items or products based on the user's choice
                                        boolean priceUpdated = false;
                                        if(priceChoice == 1)
                                        {
                                            if(vendingMachine.getItemSlot(slotNumber - 1).getItem().getPrice() != price)
                                            {
                                                vendingMachine.getItemSlot(slotNumber - 1).getItem().setPrice(price);
                                                vendingMachine.setItemPrice(slotNumber - 1, price);
                                                priceUpdated = true;
                                            }
                                        }else if (priceChoice == 2)
                                        {
                                            if(vendingMachine instanceof SpecialVendingMachine){
                                                if(((SpecialVendingMachine) vendingMachine).getProductSlot(slotNumber - 1).getProduct().getPrice() != price)
                                                {
                                                    ((SpecialVendingMachine) vendingMachine).getProductSlot(slotNumber - 1).getProduct().setPrice(price);
                                                    ((SpecialVendingMachine) vendingMachine).setProductPrice(slotNumber - 1, price);
                                                    priceUpdated = true;
                                                }
                                            }
                                            
                                            
                                        }
                                        

                                        if(priceUpdated)
                                        {
                                            JOptionPane.showMessageDialog(null, "Item price has been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        }else
                                        {
                                            JOptionPane.showMessageDialog(null, "Item price is already set to the new price.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }

                            case 3:
                                try
                                {
                                    String paymentCollected = vendingMachine.collectPayment();
                                    JOptionPane.showMessageDialog(null, "Payment Collected: " + paymentCollected);
                                }catch(Exception e1)
                                {
                                    JOptionPane.showMessageDialog(null, "An error occurred while collecting the payment: " + e1.getMessage());
                                }
                                break;

                            case 4:
                                try
                                {
                                    vendingMachine.replenishChange();
                                    JOptionPane.showMessageDialog(null, "Change replenished successfully!");
                                }catch(Exception e2)
                                {
                                    JOptionPane.showMessageDialog(null, "An error occurred while replenishing the change: " + e2.getMessage());
                                }
                                break;

                            case 5:
                                try
                                {
                                    String transactionSummary = vendingMachine.printTransactionSummary(vendingMachine);
                                    String endingInventory = vendingMachine.updateEndingInventory();
                                    JOptionPane.showMessageDialog(null, "Transaction Summary: \n" + transactionSummary);
                                    JOptionPane.showMessageDialog(null, "Ending Inventory Updated: \n" + endingInventory);
                                }catch(Exception e3)
                                {
                                    JOptionPane.showMessageDialog(null, "An error occurred while updating the transaction history and inventory: " + e3.getMessage());
                                }
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Invalid choice!");
                                break;
                        }
                    }
                }else
                {
                    JOptionPane.showMessageDialog(null, "No vending machine created yet! Please create one first.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    public double getValidDouble(String input)
    {
        double value;

        if(input == null)
        {
            return -1;
        }

        try
        {
            value = Double.parseDouble(input);
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
            return -1;
        }

        if(value < 0)
        {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a positive number.");
            return -1;
        }

        return value;
    }

    private int getValidInt(String input) {
    int value;

    if (input == null) {
        return -1;
    }

    try {
        value = Integer.parseInt(input.trim()); // Parse the input after trimming any whitespace
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
        return -1;
    }

    if (value < 0) {
        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a positive number.");
        return -1;
    }

    return value;
}



    private static void displayItems(VendingMachine vendingMachine) {
        StringBuilder itemsList = new StringBuilder("===== Items =====\n");
        for (int i = 0; i < vendingMachine.getNumSlots(); i++) {
            ItemSlot itemSlot = vendingMachine.getItemSlot(i);
            Item item = itemSlot.getItem();
            itemsList.append("| Slot:").append(i + 1).append(". ").append(item.getName()).append(" (").append(item.getPrice()).append(") - ").append(itemSlot.getQuantity()).append(" left\n");
        }
        itemsList.append("=====================");
        JOptionPane.showMessageDialog(null, itemsList.toString(), "Items", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayProducts(SpecialVendingMachine vendingMachine) {
        StringBuilder productsList = new StringBuilder("===== Products =====\n");
        for (int i = 0; i < vendingMachine.getProductNumSlots(); i++) {
            ProductSlot productSlot = vendingMachine.getProductSlot(i);
            Product product = productSlot.getProduct();
            productsList.append("| Slot:").append(i + 1).append(". ").append(product.getName()).append(" (").append(product.getPrice()).append(") - ").append(productSlot.getQuantity()).append(" left - ").append(product.getCalories()).append(" calories\n");
        }
        productsList.append("=====================");
        JOptionPane.showMessageDialog(null, productsList.toString(), "Products", JOptionPane.INFORMATION_MESSAGE);
    }

       private void orderCustomizedProduct() {

        
        // Create a dialog to choose the sauce
        String[] sauceOptions = {"Marinara", "Pesto"};
        int sauceChoice = JOptionPane.showOptionDialog(
            this,
            "Choose your sauce:",
            "Customize Your Product",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            sauceOptions,
            sauceOptions[0]
        );

        if (sauceChoice == JOptionPane.CLOSED_OPTION) {
            return; // User closed the dialog without making a choice
        }

        String selectedSauce = sauceOptions[sauceChoice];

        // Create a dialog to choose the ingredients
        List<Item> selectedIngredients = new ArrayList<>();
        int totalCalories = selectedSauce.equals("Marinara") ? 450 : 300;
        double productPrice = selectedSauce.equals("Marinara") ? 300 : 350;

        // Display the available ingredients in the vending machine
        StringBuilder ingredientOptions = new StringBuilder();
        for (int i = 0; i < vendingMachine.getNumSlots(); i++) {
            ItemSlot itemSlot = vendingMachine.getItemSlot(i);
            Item item = itemSlot.getItem();
            ingredientOptions.append((i + 1)).append(". ").append(item.getName()).append("\n");
        }

        int ingredientChoice = 0;
        do {
            String slotNumberStr = JOptionPane.showInputDialog(
                this,
                "Enter the slot number to add an ingredient (0 to finish):\n" + ingredientOptions,
                "Customize Your Product",
                JOptionPane.QUESTION_MESSAGE
            );

            if (slotNumberStr == null) {
                return; // User canceled the dialog
            }

            ingredientChoice = getValidInt(slotNumberStr);
            if (ingredientChoice == -1) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.");
                continue;
            }

            if (ingredientChoice == 0) {
                break;
            }

            if (ingredientChoice < 1 || ingredientChoice > vendingMachine.getNumSlots()) {
                JOptionPane.showMessageDialog(this, "Invalid slot number! Please try again.");
                continue;
            }

            Item selectedIngredient = vendingMachine.getItemSlot(ingredientChoice - 1).getItem();
            selectedIngredients.add(selectedIngredient);

            totalCalories += selectedIngredient.getCalories();
            productPrice += selectedIngredient.getPrice();

        } while (true);

        // Create the customized product
        String productName = "Custom Pizza with " + selectedSauce;
        String productDescription = "A delicious pizza with " + selectedSauce + " sauce and custom toppings";
        Item[] ingredientsArray = selectedIngredients.toArray(new Item[0]);
        Product customizedProduct = new Product(productName, productDescription, productPrice, totalCalories, selectedSauce, ingredientsArray);

        // Display the customized product details to the user
        StringBuilder productDetails = new StringBuilder();
        productDetails.append("== Your customized product: ==\n")
            .append(customizedProduct.toString()).append("\n")
            .append("== Description: ").append(productDescription).append(" ==\n")
            .append("== Ingredients: ==\n");
        for (Item ingredient : ingredientsArray) {
            productDetails.append(ingredient.getName()).append("\n");
        }
        productDetails.append("== Total Calories: ").append(totalCalories).append(" ==\n")
            .append("== Total Price: ").append(productPrice).append(" ==\n");

        JOptionPane.showMessageDialog(this, productDetails.toString(), "Customized Product Details", JOptionPane.INFORMATION_MESSAGE);

        // Now you can proceed with the payment process using another dialog or GUI components.
        // Implement the payment logic here.

        

       // Ask the user to enter the money using GUI dialogs
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new GridLayout(9, 2));
        moneyPanel.add(new JLabel("How many 1000's:"));
        JTextField thousandsField = new JTextField();
        moneyPanel.add(thousandsField);
        moneyPanel.add(new JLabel("How many 500's:"));
        JTextField fiveHundredsField = new JTextField();    
        moneyPanel.add(fiveHundredsField);
        moneyPanel.add(new JLabel("How many 200's:"));
        JTextField twoHundredsField = new JTextField();
        moneyPanel.add(twoHundredsField);
        moneyPanel.add(new JLabel("How many 100's:"));
        JTextField hundredsField = new JTextField();
        moneyPanel.add(hundredsField);
        moneyPanel.add(new JLabel("How many 50's:"));
        JTextField fiftiesField = new JTextField();
        moneyPanel.add(fiftiesField);
        moneyPanel.add(new JLabel("How many 20's:"));
        JTextField twentiesField = new JTextField();
        moneyPanel.add(twentiesField);
        moneyPanel.add(new JLabel("How many 10's:"));
        JTextField tensField = new JTextField();
        moneyPanel.add(tensField);
        moneyPanel.add(new JLabel("How many 5's:"));
        JTextField fivesField = new JTextField();
        moneyPanel.add(fivesField);
        moneyPanel.add(new JLabel("How many 1's:"));
        JTextField onesField = new JTextField();
        moneyPanel.add(onesField);

        int result = JOptionPane.showConfirmDialog(null, moneyPanel, "Enter the money", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.CANCEL_OPTION){
            return;
        }

        int thousands = Integer.parseInt(thousandsField.getText());
        int fiveHundreds = Integer.parseInt(fiveHundredsField.getText());
        int twoHundreds = Integer.parseInt(twoHundredsField.getText());
        int hundreds = Integer.parseInt(hundredsField.getText());
        int fifties = Integer.parseInt(fiftiesField.getText());
        int twenties = Integer.parseInt(twentiesField.getText());
        int tens = Integer.parseInt(tensField.getText());
        int fives = Integer.parseInt(fivesField.getText());
        int ones = Integer.parseInt(onesField.getText());

        // Calculate the payment
        int payment = (thousands * 1000) + (fiveHundreds * 500) + (twoHundreds * 200) + (hundreds * 100) + (fifties * 50)
            + (twenties * 20) + (tens * 10) + (fives * 5) + ones;

        vendingMachine.receivePayment(payment);
        dispenseCustomizedProduct(customizedProduct);
        try
            {
            String produceChange = vendingMachine.produceChange(1);
            JOptionPane.showMessageDialog(null, "Change Produced " + produceChange);
        }catch(Exception e1)
        {
            JOptionPane.showMessageDialog(null, "An error occurred while collecting the payment: " + e1.getMessage());
        }
            vendingMachine.setCurToZero(); // Set current money to zero
    }

        private void dispenseCustomizedProduct(Product customProduct) {
        if (customProduct == null) {
            JOptionPane.showMessageDialog(this, "Product is not available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item[] ingredients = customProduct.getIngredients();
        boolean allIngredientsAvailable = true;

        // Check if all ingredients are available in the vending machine
        for (Item ingredient : ingredients) {
            boolean foundIngredient = false;
            for (ItemSlot itemSlot : vendingMachine.getItemSlots()) {
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

            // Implement the logic to dispense the customized product as a GUI
            // Here, we will use JOptionPane to show the steps of the preparation process.
            StringBuilder preparationSteps = new StringBuilder();
            preparationSteps.append("Your delicious pizza is being prepared!\n")
                .append("Kneading dough...\n");

            if (customProduct.getSauce().equals("Marinara")) {
                preparationSteps.append("Pouring marinara sauce\n");
            } else if (customProduct.getSauce().equals("Pesto")) {
                preparationSteps.append("Pouring pesto sauce\n");
            }

            preparationSteps.append("Topping with ");
            for (int i = 0; i < ingredients.length; i++) {
                preparationSteps.append(ingredients[i].getName());
                if (i < ingredients.length - 1) {
                    preparationSteps.append(", ");
                }
            }
            preparationSteps.append("\n")
                .append("Putting in the oven...\n")
                .append("Delicious pizza baking!\n")
                .append("Packing your delicious pizza...\n")
                .append("Pizza done!\n")
                .append("Enjoy your ").append(customProduct.getName()).append(" pizza!\n");

            JOptionPane.showMessageDialog(this, preparationSteps.toString(), "Dispense Customized Product", JOptionPane.INFORMATION_MESSAGE);

            // Update transaction
            Transaction transac = vendingMachine.getTransaction();
            transac.setCurrentAmountCollected(-customProduct.getPrice());
            transac.updateTotalAmountCollected(customProduct.getPrice());
        } else {
            JOptionPane.showMessageDialog(this, "Product cannot be dispensed due to missing ingredients.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void purchaseAvailableProduct() {
    // Check if the vending machine supports customized products
    if (!(vendingMachine instanceof SpecialVendingMachine)) {
        JOptionPane.showMessageDialog(
            this,
            "This vending machine does not support customized products!",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    SpecialVendingMachine speVendingMachine = (SpecialVendingMachine) vendingMachine;

    displayProducts(speVendingMachine);

    // Display the available products

        //ask for the money input

    JPanel moneyPanel = new JPanel();
    moneyPanel.setLayout(new GridLayout(9, 2));
    moneyPanel.add(new JLabel("How many 1000's:"));
    JTextField thousandsField = new JTextField();
    moneyPanel.add(thousandsField);
    moneyPanel.add(new JLabel("How many 500's:"));
    JTextField fiveHundredsField = new JTextField();    
    moneyPanel.add(fiveHundredsField);
    moneyPanel.add(new JLabel("How many 200's:"));
    JTextField twoHundredsField = new JTextField();
    moneyPanel.add(twoHundredsField);
    moneyPanel.add(new JLabel("How many 100's:"));
    JTextField hundredsField = new JTextField();
    moneyPanel.add(hundredsField);
    moneyPanel.add(new JLabel("How many 50's:"));
    JTextField fiftiesField = new JTextField();
    moneyPanel.add(fiftiesField);
    moneyPanel.add(new JLabel("How many 20's:"));
    JTextField twentiesField = new JTextField();
    moneyPanel.add(twentiesField);
    moneyPanel.add(new JLabel("How many 10's:"));
    JTextField tensField = new JTextField();
    moneyPanel.add(tensField);
    moneyPanel.add(new JLabel("How many 5's:"));
    JTextField fivesField = new JTextField();
    moneyPanel.add(fivesField);
    moneyPanel.add(new JLabel("How many 1's:"));
    JTextField onesField = new JTextField();
    moneyPanel.add(onesField);

    int result = JOptionPane.showConfirmDialog(null, moneyPanel, "Enter the money", JOptionPane.OK_CANCEL_OPTION);
    if(result == JOptionPane.CANCEL_OPTION){
        return;
    }

    int thousands = Integer.parseInt(thousandsField.getText());
    int fiveHundreds = Integer.parseInt(fiveHundredsField.getText());
    int twoHundreds = Integer.parseInt(twoHundredsField.getText());
    int hundreds = Integer.parseInt(hundredsField.getText());
    int fifties = Integer.parseInt(fiftiesField.getText());
    int twenties = Integer.parseInt(twentiesField.getText());
    int tens = Integer.parseInt(tensField.getText());
    int fives = Integer.parseInt(fivesField.getText());
    int ones = Integer.parseInt(onesField.getText());


    // Calculate the payment
    int payment = (thousands * 1000) + (fiveHundreds * 500) + (twoHundreds * 200) + (hundreds * 100) + (fifties * 50)
        + (twenties * 20) + (tens * 10) + (fives * 5) + ones;

    speVendingMachine.receivePayment(payment);

    // Ask the user to enter the slot number
    String slotNumberStr = JOptionPane.showInputDialog(this, "Enter the slot number to purchase from:");
    int slotNumber = getValidInt(slotNumberStr);
    if (slotNumber == -1) {
        return;
    }

    // Ask the user if they want to continue or exit
    String[] options = { "Continue", "Exit" };
    int choice = JOptionPane.showOptionDialog(
        this,
        "Do you want to continue or exit?",
        "Continue or Exit",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );

    if (choice == 0) {
        // User chose to continue
        dispenseProduct(slotNumber - 1);
        try{
            String produceChange = speVendingMachine.produceChange(1);
            JOptionPane.showMessageDialog(null, "Change Produced " + produceChange);
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null, "An error occurred while collecting the payment: " + e1.getMessage());
        }
            speVendingMachine.setCurToZero(); // Set current money to zero
    } else if (choice == 1) {
        // User chose to exit
        JOptionPane.showMessageDialog(
            this,
            "Thank you for using our vending machine!",
            "Exit",
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JOptionPane.showMessageDialog(
            this,
            "Invalid choice!",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }

        public void dispenseProduct(int slotIndex) {
        int numProductSlots = vendingMachine.getProductNumSlots();
        Transaction transac = vendingMachine.getTransaction();

        if (slotIndex < 0 || slotIndex >= numProductSlots) {
            JOptionPane.showMessageDialog(null, "Invalid product slot index.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductSlot productSlot = vendingMachine.getProductSlot(slotIndex);
        Product product = productSlot.getProduct();

        if (product == null) {
            JOptionPane.showMessageDialog(null, "Product is not available in this slot.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item[] ingredients = product.getIngredients();

        // Check if all ingredients are available in the vending machine
        boolean allIngredientsAvailable = true;
        for (Item ingredient : ingredients) {
            boolean foundIngredient = false;
            for (ItemSlot itemSlot : vendingMachine.getItemSlots()) {
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
            StringBuilder message = new StringBuilder();
            message.append("==================================\n");
            message.append("Your delicious pizza is being prepared!\n");
            message.append("Kneading dough...\n");
            message.append("Pouring pizza sauce on the dough...\n");

            Item[] selectedIngredients = product.getIngredients();
            message.append("Topping with ");
            for (int i = 0; i < selectedIngredients.length; i++) {
                message.append(selectedIngredients[i].getName());
                if (i < selectedIngredients.length - 1) {
                    message.append(", ");
                }
            }
            message.append("\n");

            message.append("Putting in the oven...\n");
            message.append("Delicious pizza baking!\n");
            message.append("Packing your delicious pizza...\n");
            message.append("Pizza done!\n");
            message.append("Enjoy your delicious ").append(product.getName()).append(" pizza!\n");
            message.append("|== Product dispensed: ").append(product.getName()).append("\n");
            message.append("==================================");

            JOptionPane.showMessageDialog(null, message.toString(), "Product Dispensed", JOptionPane.INFORMATION_MESSAGE);

            productSlot.decrementQuantity();

            // Update transaction
            transac.updateItemQuantity(slotIndex, 1);
            transac.setCurrentAmountCollected(-product.getPrice());
            transac.updateTotalAmountCollected(product.getPrice());
        } else {
            JOptionPane.showMessageDialog(null, "Product cannot be dispensed due to missing ingredients.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        VendingMachineGUI app = new VendingMachineGUI();
        app.setSize(400, 300);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
    }
}