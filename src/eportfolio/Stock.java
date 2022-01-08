package eportfolio;

/**
 * Subclass of Investment class and is the main class for creating Stock objects.
 */
public class Stock extends Investment {
    private double bookValue = 0.0;
    private double gain;
    private static final double COMMISSION = 9.99;

    /**
     * Constructor for Investment type Stock.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public Stock(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        calcBuyBookValue(quantity, price);
    }

    public double getBookvalue() {
        return this.bookValue;
    }

    public void setBookvalue(double bookValue) {
        this.bookValue = bookValue;
    }

    public double getCommission() {
        return COMMISSION;
    }

    public double getGainValue() {
        return this.gain;
    }

    public void setGainValue(double gain) {
        this.gain = gain;
    }

    /**
     * Updates the gain based on what was sold using the formula below
     * @param sellBookValue
     * @param payment
     */
    public void updateGain(double sellBookValue, double payment) {
        this.gain = (payment - sellBookValue);
    }

    /**
     * This function will calculate the book value when you purchase a stock.
     * It will then set this value to the book value of the current object.
     * @param currQuantity
     * @param currPrice
     */
    public void calcBuyBookValue(int currQuantity, double currPrice) {
        this.bookValue += (currQuantity * currPrice + COMMISSION);
    }

    /**
     * This function will calculate the book value when you sell a stock.
     * It will then set this value to the book value of the current object.
     * @param currQuantity
     */
    public void calcSellBookValue(int currQuantity) {
        int remainingQuantity = this.quantity - currQuantity;
        this.bookValue = this.bookValue * ((double)remainingQuantity/this.quantity);
    }

    /**
     * This function will calculate the gain if the current stock object was completely sold
     * @return
     */
    public double calculateGain() {
        return (this.quantity * this.price - COMMISSION) - this.bookValue;
    }

    /**
     * This function will calculate the payment if the current stock object was sold partially.
     * @param currQuantity
     * @param currPrice
     * @return
     */
    public double calculatePayment(int currQuantity, double currPrice) {
        return currPrice * currQuantity - COMMISSION;
    }

    /**
     * To string override to print a stock
     * @return
     */
    public String toString() {
        return "Symbol: " + getSymbol() + "\nName: " + getName() + "\nQuantity: " + getQuantity() + "\nPrice: " + getPrice() + "\nBook Value: " + getBookvalue();
    }

    /**
     * Returns the format the data will be printed to the file
     * @return
     */
    public String fileFormat() {
        return "1 " + getSymbol() + " " + getFormatName() + " " + getQuantity() + " " + getPrice();
    }
}
