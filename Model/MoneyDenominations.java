package Model;


public class MoneyDenominations {
    private int count1000;  // count of 1000 PHP
    private int count500;   // count of 500 PHP
    private int count200;   // count of 200 PHP
    private int count100;   // count of 100 PHP
    private int count50;    // count of 50 PHP
    private int count20;    // count of 20 PHP
    private int count10;    // count of 10 PHP
    private int count5;     // count of 5 PHP
    private int count1;     // count of 1 PHP

    // Constructor
    // Takes in the count of each denomination
    // Initializes the attributes of the class

    public MoneyDenominations(int count1000, int count500, int count200, int count100, int count50,int count20, int count10, int count5, int count1) {
        this.count1000 = count1000;
        this.count500 = count500;
        this.count200 = count200;
        this.count100 = count100;
        this.count50 = count50;
        this.count20 = count20;
        this.count10 = count10;
        this.count5 = count5;
        this.count1 = count1;
    }

    // Getters and setters for the attributes of the class

    // getCount1000() returns the count of 1000 PHP

    public int getCount1000() {
        return count1000;
    }

    // setCount1000() sets the count of 1000 PHP

    public void setCount1000(int count1000) {
        this.count1000 = count1000;
    }

    // getCount500() returns the count of 500 PHP

    public int getCount500() {
        return count500;
    }

    // setCount500() sets the count of 500 PHP

    public void setCount500(int count500) {
        this.count500 = count500;
    }
    
    // getCount200() returns the count of 200 PHP

    public int getCount200() {
        return count200;
    }

    // setCount200() sets the count of 200 PHP

    public void setCount200(int count200) {
        this.count200 = count200;
    }

    // getCount100() returns the count of 100 PHP

    public int getCount100() {
        return count100;
    }
    
    // setCount100() sets the count of 100 PHP

    public void setCount100(int count100) {
        this.count100 = count100;
    }

    // getCount50() returns the count of 50 PHP

    public int getCount50() {
        return count50;
    }

    // setCount50() sets the count of 50 PHP

    public void setCount50(int count50) {
        this.count50 = count50;
    }

    // getCount20() returns the count of 20 PHP 

    public int getCount20() {
        return count20;
    }

    // setCount20() sets the count of 20 PHP

    public void setCount20(int count20) {
        this.count20 = count20;
    }

    // getCount10() returns the count of 10 PHP

    public int getCount10() {
        return count10;
    }

    // setCount10() sets the count of 10 PHP

    public void setCount10(int count10) {
        this.count10 = count10;
    }

    // getCount5() returns the count of 5 PHP

    public int getCount5() {
        return count5;
    }

    // setCount5() sets the count of 5 PHP

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    // getCount1() returns the count of 1 PHP

    public int getCount1() {
        return count1;
    }

    // setCount1() sets the count of 1 PHP

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    // collectMoney() adds the count of each denomination to the existing count of each denomination
    // Takes in the count of each denomination
    // Returns nothing

    public void collectMoney(int count1000, int count500, int count200, int count100, int count50,int count20, int count10, int count5, int count1) {
        this.count1000 += count1000;
        this.count500 += count500;
        this.count200 += count200;
        this.count100 += count100;
        this.count50 += count50;
        this.count20 += count20;
        this.count10 += count10;
        this.count5 += count5;
        this.count1 += count1;
    }

    // replenishMoney() sets the count of each denomination to the given count of each denomination
    // Takes in the count of each denomination
    // Returns nothing

    public void replenishMoney(int count1000, int count500, int count200, int count100, int count50,int count20, int count10, int count5, int count1) {
        this.count1000 = count1000;
        this.count500 = count500;
        this.count200 = count200;
        this.count100 = count100;
        this.count50 = count50;
        this.count20 = count20;
        this.count10 = count10;
        this.count5 = count5;
        this.count1 = count1;
    }
}