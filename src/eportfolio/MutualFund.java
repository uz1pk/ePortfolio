package eportfolio;

/**
 * Subclass of Investment class and is the main class for creating Mutual Fund objects.
 */
public class MutualFund extends Investment {
    private double bookValue = 0;
    private double gain;
    private static final double COMMISSION = 45;

    /**
     * Constructor for Investment type Mutual Fund.
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public MutualFund(String symbol, String name, int quantity, double price) {
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
     * This function will calculate the book value when you purchase a mutual fund.
     * It will then set this value to the book value of the current object.
     * @param currQuantity
     * @param currPrice
     */
    public void calcBuyBookValue(int currQuantity, double currPrice) {
        this.bookValue += (currQuantity * currPrice);
    }

    /**
     * This function will calculate the book value when you sell a mutual fund.
     * It will then set this value to the book value of the current object.
     * @param currQuantity
     */
    public void calcSellBookValue(int currQuantity) {
        int remainingQuantity = this.quantity - currQuantity;
        this.bookValue = this.bookValue * ((double)remainingQuantity/this.quantity);
    }

    /**
     * This function will calculate the gain if the current mutual fund object was completely sold
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
     * To string override to print a mutual fund
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
        return "2 " + getSymbol() + " " + getFormatName() + " " + getQuantity() + " " + getPrice();
    }
}
