package Model;

public class RegularVendingMachine extends VendingMachine {
   
    public RegularVendingMachine(int numSlots) {
        super(numSlots);

        //initialize the slots

        ItemSlot[] nSlots = super.itemSlots;
        for (int i = 0; i < numSlots; i++) {
            nSlots[i] = new ItemSlot();
        }

    }

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

    // Additional methods specific to the RegularVendingMachine (if needed)
    // ...
}

