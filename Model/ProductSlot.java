package Model;
public class ProductSlot {
    private Product product;
    private int quantity;
    private double price;
    // Other properties and methods

    // Constructor, getters, setters, etc.

    public ProductSlot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductSlot(Product product) {
        this.product = product;
        this.quantity = 10;
    }

    public ProductSlot() {
        this.product = null;
        this.quantity = 0;
        this.price = 0.0;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // getQuantity() returns the quantity of the product in the slot

    public int getQuantity() {
        return this.quantity;
    }

    // setQuantity() sets the quantity of the product in the slot

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // getPrice() returns the price of the product in the slot

    public double getPrice() {
        return this.product.getPrice();
    }

    // setPrice() sets the price of the product in the slot

    public void setPrice(double price) {
        this.product.setPrice(price);
    }

    //decreaseQuantity() decreases the quantity of the product in the slot by 1

    public void decrementQuantity() {
        this.quantity--;
    }
}
