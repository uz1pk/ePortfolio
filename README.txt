ePortfolio Project (Part 2) README

General Information:

	Name: Usman Zaheer
	Email: uzaheer@uoguelph.ca
	Student ID: 1148583
	Assignment: ePortfolio Part 2 (Assignment 2)
	Course Code: CIS 2430
	Professor: Dr. Fei Song
	Last Edited: 2021-11-08

How to Run:

  Compiling/Running the program
	0. OPTIONAL test file included with pre-existing values for search test case 7 along
	with load function testing
	1. Open the terminal within the src (NOT eportfolio project folder) folder
	2. Use the command "javac eportfolio/Portfolio.java"
	3a. Then run the command "java eportfolio/Portfolio"
	3b. or run the command "java eportfolio/Portfolio filename.txt" for sample file
	(file must be consistent with load/save format)
	4. From there it will start the program.

  How to test
	1. Refer to test plan

Program Description:

	An investor often needs to maintain a portfolio of different investments so that the person
can keep track of the actions for buying or selling investments, searching for relevant
investments, updating prices, and calculating the total gain of the portfolio. An investment is
better modeled by an object so that you can distinguish different attributes and apply suitable
methods for accessing and changing these attributes. For this project, we limit ourselves to two
kinds of investments: Stock and MutualFund. With all searching and organziing being done through file I/O
and the use of the hashmap data structure and library.

Test Plan:

Buy functionality: 

  Major Case 1 (Entering Data)
	- Through multiple runs use values to check for proper input include, (35, 9999, -15, 5.5)
	- Enter Ticker symbols fo different sizes along with different cases in order to see if 
	program adjusts for user inputs/errors. (AAPL, ApLp, AAAAAAAAPL, -141A)
	- Enter names of all shapes, cases, sizes and words to test for investment name
	(Bank oF torontO, Apple, LOGITECH)
  Major Case 2 (Investment Existance)
	- Through two seperate runs first check to see if function can add a new investment. Then
	add the same investment and see if the function asks for the investment name.
	(<AAPL, 20, 15.30, Apple Inc>, <AAPL, 10, 19.78>)

Sell functionality: 

  Major Case 1 (Entering Ticker + Price)
	- Check for proper input. Enter ticker which does not exist to check for existing investment
	(XXXX (anything that doesnt exist)). Enter ticker which does exist to see if it is properly
	found, (ticker in list = AAPL, ticker entered = AAPL). Enter any valid price to see if
	payment is properly done (price = any decimal).
  Major Case 2 (Entering Quantity)
	- Check for proper sell values. Enter value greater than investment quantity to check for 
	invalid quantity (quantity = 20, sell quantitity = 21). Enter a value less than investment
	quantity to check for proper selling (quantity = 20, sell quantity = 15). Finally check to 
	see if a stock is removed when quantity is equal to sell quantity (quantity = 20, sell 
	quantity = 20)

Update functionality:

  Major Case 1 (Entering Price):
	- Enter any price value (XX.XX || XX || XX.X). Once done print list to see if properly implemented

Gain functionality:

  Major Case 2 (Proper Gain)
	- Enter gain (or getgain in any case) to test for proper input. 
	Calculate gain for stocks and align it with given output

Search functionality (MUST RUN BUY BEFORE TEST):

  Major Case 1 (Element not in list)
	- Enter any value/name/ticker that is not in the list and check to see if program recognizes
	this and returns to menu
  Major Case 2 (Element is start of list)
	- Enter data which matches the data in the first element of the list to test if the program 
	is able to locate item in first index. (May use any search element to test)
  Major Case 3 (Element is end of list)
	- Enter data which matches the data in the last element of the list to test if the program 
	is able to locate item in last index. (May use any search element to test)	
  Major Case 4 (Element is middle of list)
	- Enter data which matches the data is not the first or last element of the list to
	 test if the program is able to locate item in any index. (May use any search element to test)
  Major Case 5 (Multi-Element Search)
	- Using data which is contained in a invesment within the list use at least 2 or more of the search
	options to test for proper execution of a multi element search
  Major Case 6 (Price Range)
	- Using according price data within an investment within the list, use all 3 of the possible types of 
	price range searches to test for proper execution. (-XX.XX, XX.XX-XX.XX, XX.XX-, XX.XX)
  Major Case 7 (Hash Map Search)
	- Using criteria given in assignment document for how the search should run based on hashmap enter
	according investments and values to test for proper execution of hashmap search. EXAMPLE
	(Investment Names: Toronto bank, Bank of Montreal, Royal bank, Bank of Toronto)
	(Search Filter: "Bank Toronto")
	(Output: Toronto bank and Bank of Toronto are printed)

Load/Save functionality:

  Major Case 1(Consistent loading/saving)
	- Start program with or without a starting file command line argument. (optional)Input multiple
	investments to test in program saving. Type q or quit (non case sensitive) to exit program.
	Reload program using given compiling commands and type "print" to see if all functions were updated
	and properly saved.

Current Limiations:

	There are currently no known limitations within this program. The program follows the
guidelines given by the assignment 1 document and is able to sufficiently perform all given tasks
listed within the document.

Future Improvements:

	For future improvements I would like to have had more knowlege in java contained libraries along
with having more prior practice with hash maps. I believe with these two improvement I would have been
able to make my code more efficient and concise resulting in easy to read and organized code. Although my code
may do all given tasks within the assignment I would like to have been able to also make my code more modular 
to remove as much redundancy as possible. Overall I believe I did a good job and all room for improvements
would so that I end up as a better/more organized programmer.
	
