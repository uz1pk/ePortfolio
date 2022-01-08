package eportfolio;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Portfolio class keeps tracks of all currently owned investments and is the driver class
 */
 
public class Portfolio {
    private static final ArrayList<Investment> totalInvestments = new ArrayList<>();
    public static final HashMap<String, ArrayList<Integer>> totalMap = new HashMap<>();

    /**
     * When called this function will simply print the program menu
     */
    public static void printMenu() {
        System.out.println("To buy investment type buy");
        System.out.println("To sell investment type sell");
        System.out.println("To update investment type update");
        System.out.println("To get gain of portfolio type gain or getgain");
        System.out.println("To search for an investment type search");
        System.out.println("To print all investments type print");
        System.out.println("To exit type: quit or q");
    }

    /**
     * This function will simply print all existing investments along with all their information
     */
    public static void printInvestments() {
        for(Investment totalInvestment : totalInvestments) {
            System.out.println(totalInvestment);
        }
    }

    /**
     * Returns 1 if the given ticker is a stock investment, returns 2 for mutual fund and 0 if nothing was found
     * @param ticker
     * @return
     */
    public static int getInvestmentType(String ticker) {
        for(Investment totalInvestment : totalInvestments) {
            if(totalInvestment.getSymbol().equals(ticker)) {
                if(totalInvestment instanceof Stock) {
                    return 1;
                }
                else if(totalInvestment instanceof MutualFund) {
                    return 2;
                }
            }
        }
        return 0;
    }

    /**
     * Checks to see if an investment exists given its ticker and what kind of investment it is.
     * Returns false if nothing was found.
     * @param ticker
     * @return
     */
    public static boolean existingInvestment(String ticker) {
        for(Investment totalInvestment : totalInvestments) {
            if(totalInvestment.getSymbol().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    /**
     * When this function is called it will print an investment if it exists. It will search
     * for the investment with the given information, ticker symbol, keywords in the name,
     * target price, and whether to check for values above or below given price.
     * @param ticker
     * @param keywords
     * @param price
     * @param rangeType
     */
    public static void printIfExisting(String ticker, List<String> keywords, double price, int rangeType) {
        if(rangeType == -1) {
            for(Investment totalInvestment : totalInvestments) {
                if(totalInvestment.getPrice() <= price) {
                    if(ticker.isEmpty() && keywords.get(0).equals("")) {
                        System.out.println(totalInvestment);
                    }
                    else {
                        printIfExisting(ticker, keywords);
                    }
                }
            }
        }
        else if(rangeType == 1) {
            for(Investment totalInvestment : totalInvestments) {
                if(totalInvestment.getPrice() >= price) {
                    if(ticker.isEmpty() && keywords.get(0).equals("")) {
                        System.out.println(totalInvestment);
                    }
                    else {
                        printIfExisting(ticker, keywords);
                    }
                }
            }
        }
        else if(rangeType == 0) {
            for(Investment totalInvestment : totalInvestments) {
                if(totalInvestment.getPrice() == price) {
                    if(ticker.isEmpty() && keywords.get(0).equals("")) {
                        System.out.println(totalInvestment);
                    }
                    else {
                        printIfExisting(ticker, keywords);
                    }
                }
            }
        }
    }

    /**
     * When this function is called it will print an investment if it exists. It will search
     * for the investment with the given information, ticker symbol, keywords, lower bound price
     * and upper bound price.
     * @param ticker
     * @param keywords
     * @param priceOne
     * @param priceTwo
     */
    public static void printIfExisting(String ticker, List<String> keywords, double priceOne, double priceTwo) {
        for(Investment totalInvestment : totalInvestments) {
            if(totalInvestment.getPrice() >= priceOne && totalInvestment.getPrice() <= priceTwo) {
                if(ticker.isEmpty() && keywords.get(0).equals("")) {
                    System.out.println(totalInvestment);
                }
                else {
                    printIfExisting(ticker, keywords);
                }
            }
        }
    }

    /**
     * When this function is called it will print an investment if it exists. It will search
     * for the investment with the given information, ticker symbol and keywords.
     * @param ticker
     * @param keywords
     */
    public static void printIfExisting(String ticker, List<String> keywords) {
        List<Integer> indexListOne = new ArrayList<>();
        List<Integer> indexListTwo = new ArrayList<>();
        Set<Integer> intersection = new HashSet<>();

        if(ticker.isEmpty()) {
            if(keywords.size() == 1) {
                if(totalMap.get(keywords.get(0)) != null) {
                    System.out.println("working");
                    indexListOne.addAll(totalMap.get(keywords.get(0)));
                }
            }
            else if(keywords.size() == 2) {
                if(totalMap.get(keywords.get(0)) != null && totalMap.get(keywords.get(1)) != null) {
                    indexListOne.addAll(totalMap.get(keywords.get(0)));
                    indexListTwo.addAll(totalMap.get(keywords.get(1)));
                }
            }
            intersection = getIntersection(indexListOne, indexListTwo);
            for(Integer index : intersection) {
                System.out.println(totalInvestments.get(index));
            }
        }
        else if(keywords.get(0).equals("")) {
            for (Investment totalInvestment : totalInvestments) {
                if(totalInvestment.getSymbol().equals(ticker)) {
                    System.out.println(totalInvestment);
                }
            }
        }
        else {
            if(keywords.size() == 1) {
                indexListOne.addAll(totalMap.get(keywords.get(0)));
            }
            else if(keywords.size() == 2) {
                indexListOne.addAll(totalMap.get(keywords.get(0)));
                indexListTwo.addAll(totalMap.get(keywords.get(1)));
            }
            intersection = getIntersection(indexListOne, indexListTwo);
            for(Integer index : intersection) {
                if(totalInvestments.get(index).getSymbol().equals(ticker)) {
                    System.out.println(totalInvestments.get(index));
                }
            }
        }
    }

    /**
     * This function will return the set containing the intersection of two lists
     * @param listOne
     * @param listTwo
     * @return
     */
    public static Set<Integer> getIntersection(List<Integer> listOne, List<Integer> listTwo) {
        if(listTwo.isEmpty()) {
            return new HashSet<>(listOne);
        }
        else {
            return listOne.stream().distinct().filter(listTwo::contains).collect(Collectors.toSet());
        }
    }   

    /**
     * This function will get the index of an investment if it exists within the one of the two investment fields.
     * It will search for a matching ticker and if found it will return the index of where
     * that investment is located.
     * @param ticker
     * @return
     */
    public static int getIndex(String ticker) {
        int index = 0;
        for(int i = 0; i < totalInvestments.size(); i++) {
            if(totalInvestments.get(i).getSymbol().equals(ticker)) {
                index = i;
                return index;
            }
        }
        System.out.println("Invalid input try again");
        return 0;
    }

    /**
     * This function will update any currently existing function using the given parameters.
     * @param ticker
     * @param currQuantity
     * @param currPrice
     */
    public static void updateCurrentInvestment(String ticker, int currQuantity, double currPrice) {
        int index;

        index = getIndex(ticker);
        totalInvestments.get(index).updatePrice(currPrice);
        totalInvestments.get(index).updateQuantity(currQuantity);
        if(totalInvestments.get(index) instanceof MutualFund fund) {
            fund.calcBuyBookValue(currQuantity, currPrice);
        }
        else {
            ((Stock)totalInvestments.get(index)).calcBuyBookValue(currQuantity, currPrice);
        }
    }

    /**
     * this function will add a new investment to one of the lists(which list is determined by investmentType)
     * @param ticker
     * @param currQuantity
     * @param currPrice
     * @param givenName
     * @param investmentType
     */
    public static void addInvestment(String ticker, int currQuantity, double currPrice, String givenName, int investmentType) {
        if(!existingInvestment(ticker)) {
            if(investmentType == 1) {
                totalInvestments.add(new Stock(ticker, givenName, currQuantity, currPrice));
            }
            else if(investmentType == 2) {
                totalInvestments.add(new MutualFund(ticker, givenName, currQuantity, currPrice));
            }
        }
        else {
            System.out.println("Invalid input try again");
        }
    }

    /**
     * This function when called will sell an investment. It will first search for the investment,
     * then subtract the quantity with the quantity being sold and update the price. It will then return
     * the amount of payment received from that particular investment. If sell quantity is equal to
     * current owned quantity it will simply delete that investment from the list and return the payment received.
     * It will also update portfolio gain variable to keep track of gain.
     * @param ticker
     * @param currQuantity
     * @param currPrice
     * @return
     */
    public static double sellCurrentInvestment(String ticker, int currQuantity, double currPrice) {
        double payment = 0;
        double sellBookValue;
        int index;

        index = getIndex(ticker);
        if(currQuantity == totalInvestments.get(index).getQuantity()) {
            if (totalInvestments.get(index) instanceof MutualFund fund) {
                payment = fund.calculatePayment(currQuantity, currPrice);
            } else {
                payment = ((Stock) totalInvestments.get(index)).calculatePayment(currQuantity, currPrice);
            }
            totalInvestments.remove(index);
            totalMap.clear();
            for (Investment totalInvestment : totalInvestments) {
                ArrayList<Integer> indices = new ArrayList<>();
                String[] userNameSplit = totalInvestment.getName().split(" ");
                for (int i = 0; i < userNameSplit.length; i++) {
                    indices = totalMap.get(userNameSplit[i]);
                    int currIndex = getIndex(totalInvestment.getSymbol());
                    indices.add(currIndex);
                    totalMap.put(userNameSplit[i], indices);
                }
            }
        }
        else if(currQuantity >= totalInvestments.get(index).getQuantity()) {
            System.out.println("Invalid quantity");
            return -1;
        }
        else {
            if(totalInvestments.get(index) instanceof MutualFund fund) {
                sellBookValue = fund.getBookvalue() * ((double)currQuantity/(totalInvestments.get(index).getQuantity()));
                fund.calcSellBookValue(currQuantity);
                fund.updatePrice(currPrice);
                fund.sellQuantity(currQuantity);
                payment = fund.calculatePayment(currQuantity, currPrice);
                fund.updateGain(sellBookValue, payment); 
            }
            else {
                sellBookValue = (((Stock)totalInvestments.get(index)).getBookvalue()) * ((double)currQuantity/(totalInvestments.get(index).getQuantity()));
                ((Stock)totalInvestments.get(index)).calcSellBookValue(currQuantity);
                ((Stock)totalInvestments.get(index)).updatePrice(currPrice);
                ((Stock)totalInvestments.get(index)).sellQuantity(currQuantity);
                payment = ((Stock)totalInvestments.get(index)).calculatePayment(currQuantity, currPrice);
                ((Stock)totalInvestments.get(index)).updateGain(sellBookValue, payment);
            }
        }
        return payment;
    }

    /**
     * This function will prompt the user on details of the investment they want to buy
     * and purchase it for them according to what is inputted.
     */
    public static void buyInvestment() {
        Scanner getInp = new Scanner(System.in);
        String userPrice, userQuant, userName, userType;
        String tickerSym;
        int investmentType;

        System.out.println("Enter 1 to purchase stock or 2 to purchase a mutual fund");
        userType = getInp.nextLine();
        investmentType = Integer.parseInt(userType);
        if(investmentType == 2 || investmentType == 1) {
            System.out.println("Enter the ticker symbol for the investment");
            tickerSym = getInp.nextLine().toLowerCase();
            if(tickerSym.contains(" ")) {
                System.out.println("Ticker symbol must be one word");
                return;
            }
            if(existingInvestment(tickerSym)) {
                int tempInvestmentType = getInvestmentType(tickerSym);
                if(tempInvestmentType == investmentType) {
                    System.out.println("Enter the quantity");
                    userQuant = getInp.nextLine();
                    if(userQuant.contains("-")) {
                        System.out.println("Cannot buy a negative quantity");
                        return;
                    }
                    System.out.println("Enter the price");
                    userPrice = getInp.nextLine();
                    if(userPrice.contains("-")) {
                        System.out.println("Cannot buy at a negative price");
                        return;
                    }
                    updateCurrentInvestment(tickerSym, Integer.parseInt(userQuant), Double.parseDouble(userPrice));
                }
                else if(tempInvestmentType == 1) {
                    System.out.println("This is an already existing stock please try again");
                }
                else if(tempInvestmentType == 2) {
                    System.out.println("This is an already existing mutual fund please try again");
                }
                else {
                    System.out.println("Invalid input please try again");
                }
            }
            else {
                System.out.println("Enter the quantity");
                userQuant = getInp.nextLine();
                System.out.println("Enter the price");
                userPrice = getInp.nextLine();
                System.out.println("Enter the name");
                userName = getInp.nextLine();
                addInvestment(tickerSym, Integer.parseInt(userQuant), Double.parseDouble(userPrice), userName.toLowerCase(), investmentType);
                totalMap.clear();
                for(Investment totalInvestment : totalInvestments) {
                    ArrayList<Integer> indices;
                    String[] userNameSplit = totalInvestment.getName().split(" ");
                    for(int i = 0; i < userNameSplit.length; i++) {
                        if(totalMap.get(userNameSplit[i]) != null) {
                            indices = totalMap.get(userNameSplit[i]);
                        }
                        else {
                            indices = new ArrayList<>();
                        }
                        Integer currIndex = getIndex(totalInvestment.getSymbol());
                        indices.add(currIndex);
                        totalMap.put(userNameSplit[i], indices);
                    }
                }
            }
        }
        else {
            System.out.println("Invalid input");
        }
    }

    /**
     * This function will prompt the user on details of the investment they want to sell
     * and sell it for them according to what is inputted.
     */
    public static void sellInvestment() {
        Scanner getInp = new Scanner(System.in);
        String tickerSym, userQuant, userPrice;
        double payment = 0;
        int index;

        System.out.println("Enter the ticker symbol for the investment");
        tickerSym = getInp.nextLine().toLowerCase();
        if(existingInvestment(tickerSym)) {
            System.out.println("Enter the sell quantity");
            userQuant = getInp.nextLine();
            System.out.println("Enter the sell price");
            userPrice = getInp.nextLine();
            index = getIndex(tickerSym);
            if(totalInvestments.get(index).getQuantity() >= Integer.parseInt(userQuant)) {
                payment = sellCurrentInvestment(tickerSym, Integer.parseInt(userQuant), Double.parseDouble(userPrice));
                if(payment == -1) {
                    System.out.println("Invalid Input");
                    return;
                }
                System.out.println("You have sold " + Integer.parseInt(userQuant) + " shares for " + payment);
            }
            else {
                System.out.println("You have entered an invalid quantity");
            }
        }
        else {
            System.out.println("Invalid input try again");
        }
    }

    /**
     * This function will go through all currently owned investments and prompt the user
     * for new prices. If the user simply presses enter it will move on to the next investment.
     */
    public static void updateInvestment() {
        Scanner getInp = new Scanner(System.in);
        String price;

        if(totalInvestments.isEmpty()) {
            System.out.println("No investments found");
        }
        else {
            for(Investment totalInvestment: totalInvestments) {
                System.out.println("Enter the new price for the following stock: " + totalInvestment.getName());
                price = getInp.nextLine();
                if(!price.isEmpty()) {
                    totalInvestment.updatePrice(Double.parseDouble(price));
                }
            }
        }
    }

    /**
     * This function will calculate the theoretical total gain if the user were to sell all
     * investments at the current given price.
     */
    public static void getGain() {
        double totalGain = 0;

        if(totalInvestments.isEmpty()) {
            System.out.println("No investments found");
        }
        else {
            for(Investment totalInvestment: totalInvestments) {
                if(totalInvestment instanceof MutualFund fund) {
                    if(fund.getGainValue() == 0) {
                        totalGain -= fund.getCommission();
                    }
                    totalGain += fund.getGainValue();
                }
                else {
                    if(((Stock)totalInvestment).getGainValue() == 0) {
                        totalGain -= ((Stock)totalInvestment).getCommission();
                    }
                    totalGain += ((Stock)totalInvestment).getGainValue();
                }
            }
        }
        System.out.println("Total portfolio gain is: " + totalGain);
    }

    /**
     * This function will prompt the user on the details of what investment they are searching for
     * using the fields, ticker symbol(if applicable), keywords(if applicable) and price range(if applicable).
     */
    public static void searchInvestment() {
        Scanner getInp = new Scanner(System.in);
        String[] singlePrice;
        String symbol;
        String investmentName;
        String priceRange;
        double price;
        int rangeType;

        System.out.println("Enter a valid investment symbol or press enter to skip this");
        symbol = getInp.nextLine().toLowerCase();
        System.out.println("Enter a valid investment name or press enter to skip this");
        investmentName = getInp.nextLine().toLowerCase();
        List<String> longWord = Arrays.asList(investmentName.toLowerCase().split(" "));
        System.out.println("Enter a valid price range: X.XX(exact), -X.XX(exact or less than) or X.XX-(exact or greater than) or press enter to skip this");
        priceRange = getInp.nextLine();
        if(!priceRange.isEmpty()) {
            if(priceRange.charAt(0) == '-') {
                singlePrice = priceRange.split("-");
                price = Double.parseDouble(singlePrice[1]);
                rangeType = -1;
                printIfExisting(symbol, longWord, price, rangeType);
            }
            else if(priceRange.charAt(priceRange.length() - 1) == '-') {
                singlePrice = priceRange.split("-");
                price = Double.parseDouble(singlePrice[0]);
                rangeType = 1;
                printIfExisting(symbol, longWord, price, rangeType);
            }
            else if(priceRange.contains("-")) {
                double priceTwo;
                singlePrice = priceRange.split("-");
                price = Double.parseDouble(singlePrice[0]);
                priceTwo = Double.parseDouble(singlePrice[1]);
                printIfExisting(symbol, longWord, price, priceTwo);
            }
            else {
                price = Double.parseDouble(priceRange);
                rangeType = 0;
                printIfExisting(symbol, longWord, price, rangeType);
            }
        } //all the if and elses are to deal with the different types of input possible for price ranges.
        else {
            printIfExisting(symbol, longWord);
        }
    }

    public static void loadFile(File file) {
        totalMap.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currLine;
            while((currLine = reader.readLine()) != null) {
                String[] userData = currLine.split("\\s+");
                if(userData[0].equals("1")) {
                    totalInvestments.add(new Stock(userData[1], userData[2].replace("-", " ").toLowerCase(), Integer.parseInt(userData[3]), Double.parseDouble(userData[4])));
                }
                else if(userData[0].equals("2")){
                    totalInvestments.add(new MutualFund(userData[1], userData[2].replace("-", " ").toLowerCase(), Integer.parseInt(userData[3]), Double.parseDouble(userData[4])));
                }
                else {
                    System.out.println("error occured");
                }
            }   
            for(Investment totalInvestment : totalInvestments) {
                ArrayList<Integer> indices;
                String[] userNameSplit = totalInvestment.getName().split(" ");
                for(int i = 0; i < userNameSplit.length; i++) {
                    if(totalMap.get(userNameSplit[i]) != null) {
                        indices = totalMap.get(userNameSplit[i]);
                    }
                    else {
                        indices = new ArrayList<>();
                    }
                    Integer currIndex = getIndex(totalInvestment.getSymbol());
                    indices.add(currIndex);
                    totalMap.put(userNameSplit[i], indices);
                }
            }
        } catch(IOException error) {
            System.out.println("Error has occured when accessing file");
            error.printStackTrace();
        }
        file.deleteOnExit();
    }

    public static void saveFile(File file) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file.getName()));
            for(Investment totalInvestment : totalInvestments) {
                if(totalInvestment instanceof MutualFund fund) {
                    writer.println(fund.fileFormat());
                }
                else if(totalInvestment instanceof Stock stock) {
                    writer.println(stock.fileFormat());
                }
                else {
                    System.out.println("not working");
                }
            }
            writer.close();
        } catch(IOException error) {
            System.out.println("Error has occured when saving to file");
            error.printStackTrace();
        }
    }

    /**
     * main/driver function to run the code. This will continuously run/print the program menu
     * until the user exits by typing quit or q.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        String input = "";
        File file;

        if(args.length > 0) {
            file = new File(args[0]);
            if(file.exists()) {
                loadFile(file);
            }
            else {
                System.out.println("file with given name does not exist");
            }
        }
        file = new File("test.txt");
        if(file.exists()) {
            loadFile(file);
        }
        while(!input.equalsIgnoreCase("quit") && !input.equalsIgnoreCase("q")) {
            printMenu();
            input = scannerObj.nextLine();
            if(input.equalsIgnoreCase("buy")) {
                buyInvestment();
            }
            else if(input.equalsIgnoreCase("sell")) {
                sellInvestment();
            }
            else if(input.equalsIgnoreCase("update")) {
                updateInvestment();
            }
            else if(input.equalsIgnoreCase("gain") || input.equalsIgnoreCase("getgain")) {
                getGain();
            }
            else if(input.equalsIgnoreCase("search")) {
                searchInvestment();
            }
            else if(input.equalsIgnoreCase("print")) {
                printInvestments();
            }
            else if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                System.out.println("Hope you enjoyed!");
            }
            else {
                System.out.println("Invalid input please try again");
            }
        }
        scannerObj.close();
        saveFile(file);
    }
}
