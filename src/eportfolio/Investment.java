package eportfolio;

/**
 * Main class of creating a object of type investment.
 */
public class Investment {
    protected String symbol;
    protected String name;
    protected int quantity;
    protected double price;

    /**
     * Constructor for an Investment.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public Investment(String symbol, String name, int quantity, double price) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getFormatName() {
        return this.name.replace(" ", "-");
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setSymbol(String symbol) {
        this.symbol =  symbol;
    }

    public void setName(String name) {
        this.name =  name;
    }

    public void setQuantity(int quantity) {
        this.quantity =  quantity;
    }

    public void setPrice(double price) {
        this.price =  price;
    }

    /**
     * This function when called will add the given quantity to the already existing quantity
     * @param quantity
     */
    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * This function when called will set the price of the current investment equal to the new investment.
     * @param price
     */
    public void updatePrice(double price) {
        this.price = price;
    }

    /**
     * This function will calculate the new quantity when selling a certain number of the current investment.
     * @param quantity
     */
    public void sellQuantity(int quantity) {
        this.quantity = this.quantity - quantity;
    }
}
