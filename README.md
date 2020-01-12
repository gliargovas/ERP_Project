# ** DANGER-X ERP USER MANUAL **

# Table of contents

- [General Information](#general-information)
  * [Order Management](#order-management)
  * [Warehouse Monitoring](#warehouse-monitoring)
  * [Data Analysis](#data-analysis)
- [System Requirements](#system-requirements)
- [Run the Program](#run-the-program)
- [Menu Description](#menu-description)
  * [Main Menu and Login](#main-menu-and-login)
  * [Cashier](#cashier)
  * [Storekeeper](#storekeeper)
  * [Data Analyst](#data-analyst)
  * [Administrator](#administrator)
  * [Notes while running the program](#notes-while-running-the-program)
- [Editing the data](#editing-the-data)
- [Exporting the data](#exporting-the-data)
- [Authors](#authors)

# General Information
**ERP Program for Order & Warehouse management and Order Analysis**

- For a more detailed description of the program's functions, see Menu Description.

## Order Management

1. *Login*: The user will be able to login as cashier (order manager), warehouse personnel, data analyst or administrator (Access to all features).

2. *Orders Management*: The cashier will be able to record an order on behalf of the customer.  The products are saved in lists each with a unique id.
During the order, the quantity of each product will be selected, the cost of products, VAT and final cost will be calculated, and an order report will be created, which will be stored.
The order manager will be able to process new customers (create new customers, delete, edit items).

3. *Customer information*: (details, history, etc.) will be displayed
When ordering, a question will be asked about which customer the order relates to, with three possible outcomes:
	1. *Registered Customer*: An already existing customer whose id will be given.
	2. *Guest Customer*: A guest customer with no id. (No customer details will be listed, no rewards will be available and no history will be kept)

4. *CustomerLoyaltySystem*: The customer will be given the opportunity to earn points by making orders and redeem them for a discount .

## Warehouse Monitoring

1. *Storage Ressuply Orders*: Orders can be made in order to resupply the warehouse. The system monitors the rate at which orders will reduce the quantity ofproducts in the warehouse inventory and proceed to order additional products through the user.
3. *Edit Product List*: You are be able to edit the product list (create new, edit, delete).
4. *Inventory Details*: The inventory order details (company name of the stock purchased, products and quantities supplied, order cost etc.) will be created by the user.
5. *Storage Resupply Order History*:A procurement history will also be maintained.

## Data Analysis
1. *Order Statistics*: Historical data will be used to generate statistics (revenue, products with most demand, show sales per month and year, etc.
2. *Plots*: The analysed data are used to generate various plots (total sales, monthly sales, most popular product sales, etc.).
3. *Predict Sales for a Following Year*: The sales of a given year will be predicted with linear regression.

# System Requirements

- Java 8 or later is required
- Apache Maven is required

# Run the Program

While in the main directory of the project, run: 
	```
	mvn
	```

A new target directory will be created.
- To start the program, go to **target/appassembler/bin<br/>**
**If on windows**: click on runERP.bat<br/>
**If on linux/unix** click on runERP
	
- To view the class documentation, go to target/site/apidocs and click on **allclasses-frame.html**

- While starting the program, a new directory will be created in **target/appassembler/bin** that will contain the program data. The data can be edited externally.
If you want to run the project with the provided dummy data, paste the contents of DummyData file of the main directory to **target/appassembler/bin** before starting the ERP.

# Menu Description

## Main Menu and Login
At the main menu you should choose the type of user you want to login as (Options 1-4). A default user of type Administrator (Username: admin, Password: admin) is created by default if no user list is found. In order to login successfully, after choosing the user type you want to login as, you will be asked to enter a username and password. If a user with the same username, password and type entered exists, you are logged in. Otherwise you will be returned to the main menu with an error message. In order to save all changes and exit the program, press 5.

## Cashier
The cashier menu consists of several options for managing the customers and their orders. After the cashier login, the user is redirected to the Storekeeper menu. The menu consists of 5 options:
1. **Products**: The user will be redirected to the products menu. The products menu consists of the following options:
	1. **View all products**: Displays all the products with their information.
	2. **Search for Specific Products (by product id)**: The user will be asked to enter the id of the product they want to print. The information of the matched product is printed on the screen. If no products are matched, the appropriate message is displayed and the user is returned to the storage menu.
	3. **Search for Specific Products (by product name)**: The user will be asked to enter the product name or part of the name of the product they want to search. The products (if any) that contain the given string in their name (case is ignored), will be printed on the screen.
	4. **Return to Previous Menu**: The user is redirected to the Cashier menu.
	
2. **Customers**:
	1. **View All Customers**: All the customers with their information are displayed.
	2. **Search for Specific Customers (by customer name)**: The user will be asked to enter the customer name or part of the name they want to search. The customers (if any) that contain the given string in their name (case is ignored), will be printed on the screen.
	3. **Search for Specific Customers (by customer id)**: The user will be asked to enter the id of the customer they want to print. The information of the matched customer is printed on the screen. If no customers are matched, the appropriate message is displayed and the user is returned to the customers menu.
	4. **Search for Specific Customers (by customer telephone)**: The user will be asked to enter the telephone number of the supplier they want to print. The information of the matched supplier is printed on the screen. If no suppliers are matched, the appropriate message is displayed and the user is returned to the customers menu.
	5. **Add a new Customer**: The user will be asked to enter the details of the customer. If entered correctly, a new customer will be created. On invalid input, the appropriate messages are displayed.
	6. **Edit Customer Telephone**: The user will be asked to enter the id of the customer whose telephone will be changed. If the given id is matched, the user is asked to enter the new telephone. The user can press enter without any input to cancel the process. The process is repeated until the user enters a valid telephone number.
	7. **Edit Customer Address**: The user will be asked to enter the id of the customer of whom they want to change the address. If the given id is matched, the user is asked to enter the new address. The user can press enter without any input to cancel the process.
	8. **Delete a Customer**: The user is asked to enter the id of the supplier they want to delete. The user can press enter without any input to cancel the process.
	9. **View Order History** : The whole order history is printed.
	10. **View Specific Customer Order History**: The user wll be asked to enter the id of a customer. If the id is matched to a supplier, the orders that the customer has made will be printed. Else, the appropriate message will be displayed and the user will be returned to the previous menu. 
	11. **Return to Previous Menu**: The user is redirected to the Cashier menu.
	
3. **Make a new customer order**: The user is prompted to choose the type of the customer the order concerns. Three options are available.
	1. **Already Registered Customer**: The order concerns a registered customer. The user will be asked to enter the customer's id before proceeding with the order.
	2. **Guest Customer**: The order concerns a non-registered customer. The user proceeds with the order right away.
	3. **Return to Previous Menu**: The user is redirected to the Cashier menu.
	If options 1 or 2 are chosen, the user proceeds with the order. The order consists of the following stages:<br/>
	The the user is asked to enter the id and the quantities of the products they want to add to the order. In case of the same id being entered more than once, the quantities are added to the already existing product in the basket. In case a product quantity isn't enough, a warning message is displayed and the product is not added to the basket. Also, in case of invalid product id, a warning message is displayed and the user is asked to enter another id. If the user has finished adding products, he can press enter without any other input while asked to enter another product id. They are then asked to enter the total cost of the order. After that, an order preview is displayed and the user is asked to confirm or cancel the order by pressing either "yes" or "no". If "yes" is pressed, if the customer has more than 10000 oints, he will earn a 10% discount on the final price of the order. Then, a new storage order is created and the final order report is printed. If no is pressed, the whole process is cancelled and the user is returned to the storekeeper menu. The customers are rewarded with 5 points for each euro they spend.
	
4. **Save Changes**: Changes made are saved in the data directory.

5. **Logout and Return to Main Menu**: The cashier is logged out and returned to the main menu.

## Storekeeper
The storekeeper menu consists of several options for managing the warehouse, the suppliers and the resupply orders. After the storekeeper login, the user is redirected to the Storekeeper menu. The menu consists of 5 options:
1. **Storage**: The user will be redirected to storage menu. The storage menu consists of the following options:
	1. **View All Products**: All the products with their information are diplayed.
	2. **Search for specific products (by product name)**: The user will be asked to enter the product name or part of the name of the product they want to search. The products (if any) that contain the given string in their name (case is ignored), will be printed on the screen.
	3. **Search for specific products (by product id)**: The user will be asked to enter the id of the product they want to print. The information of the matched product is printed on the screen. If no products are matched, the appropriate message is displayed and the user is returned to the storage menu.
	4. **Add a new product**: The user will be asked to enter the details of the product. If entered correctly, a new product wil be created. On invalid input, the appropriate messages are displayed.
	5. **Delete an existing product** The user will be asked to enter the id of a product they want to delete. If "0" is pressed, the process is cancelled. On invalid input, the appropriate messages are displayed. If no matching id exists, the appropriate message is displayed and the user is returned to the previous menu. 
	6. **Return to previous menu** Redirects the user to the previous menu.
	
2. **Suppliers**: The user will be redirected to suppliers menu. The suppliers menu consists of the following options:
	1. **View all suppliers**: All the suppliers with their information are printed.
	2. **Search for Specific Suppliers (by name)**: The user will be asked to enter the supplier name or part of the name they want to search. The suppliers (if any) that contain the given string in their name (case is ignored), will be printed on the screen.
	3. **Search for Specific Suppliers (by Supplier id)**: The user will be asked to enter the id of the supplier they want to print. The information of the matched supplier is printed on the screen. If no suppliers are matched, the appropriate message is displayed and the user is returned to the supplier menu.
	4. **Search for Specific Suppliers (by telephone number)**: The user will be asked to enter the telephone number of the supplier they want to print. The information of the matched supplier is printed on the screen. If no suppliers are matched, the appropriate message is displayed and the user is returned to the supplier menu.
	5. **Add a new Supplier**: The user will be asked to enter a new supplier's information (name, address and telephone). If entered successfully a new supplier will be created.
	6. **Delete a Supplier**: The user is asked to enter the id of the supplier they want to delete. The user can press enter without any input to cancel the process.
	7. **Edit supplier telephone**: The user will be asked to enter the id of the supplier whose telephone will be changed. If the given id is matched, the user is asked to enter the new telephone. The user can press enter without any input to cancel the process. The process is repeated until the user enters a valid telephone number.
	8. **Edit supplier address**: The user will be asked to enter the id of the supplier of whom they want to change the address. If the given id is matched, the user is asked to enter the new address. The user can press enter without any input to cancel the process.
	9. **View storage resupply order history**: The whole storage resupply order history is printed. 
	10. **View storage resupply order history from specific supplier**: The user wll be asked to enter the id of a supplier. If the id is matched to a supplier, the orders that the supplier has resupplied, will be printed. Else, the appropriate message will be displayed and the user will be returned to the previous menu. 
	11. **Return to the previous menu**: The user is redirected to the Storekeeper menu.
	
3. **Make a new Storage Ressuply Order**: The user is asked to enter the id of the supplier that is responsible for the order. If "enter" is pressed without any input, the process is cancelled and the user is returned to the previous menu. If the user id does not exist the user is asked to enter another id or cancel the operation. If a valid id is entered, the user is asked to enter the id and the quantities of the products they want to add to resupply. In case of the same id being entered more than once, the quantities are added to the already existing product in the supplies list. Also, in case of invalid product id, a warning message is displayed and the user is asked to enter another id. If the user has finished adding products, he can press enter without any other input while asked to enter another product id. They are then asked to enter the total cost of the order. After that, a storage order preview is displayed and the user is asked to confirm or cancel the order by pressing either "yes" or "no". If "yes" is pressed, a new storage order is created and the final order report is printed. If no is pressed, the whole process is cancelled and the user is returned to the storekeeper menu.

4. **Save Changes**: Changes made are saved in the data directory.

5. **Logout and Return to Main Menu**: The storekeeper is logged out and returned to the main menu.


## Data Analyst
**(!)The data analyst menu options may not work as expected if very few orders of only one year have been created**<br/>
The data analyst main menu consists of two options:
1. **Analysis menu**: By pressing 1 the analysis menu is displayed. In this menu the user is able to print specific data about the orders:
	1. **Display sales by year**: The sales of each year are printed.
	2. **Display sales by year for specific year interval**: The user will be asked to enter the first and the final year of the interval of which to print the yearly sales.
	3. **Display cumulative sales by month**: The sum of all the sales is printed grouped by month, ignoring the year the order was made.
	4. **Display sales by month for specific year**: The monthly sales are printed but only for a specific month.
	5. **Return to previous menu**: Returns to the Data Analyst menu.

2. **Plots Menu**: By pressing 2 the plots menu is displayed. The menu consists of the following options:
	1. **Sales Plots**: Provides access to various javafx plots of the sales.The menu consists of the following options:
		1. **Plot sales by year(barchart)**: The user will be asked to enter the start and the end of the year interval of which to plot the yearly sales. If enter is pressed without a year preference, the default interval will be used. A window with a barchart displaying the total sales for the given year interval will be created.
		2. **Plot sales by year (line chart)**: The user will be asked to enter the start and the end of the year interval of which to plot the yearly sales. If enter is pressed without a year preference, the default interval will be used. A window with a linechart displaying the total sales for the given year interval will be created.
		3. **Plot sales by month for specific year interval (line chart)**: The user will be asked to enter the start and the end of the year interval of which to plot the yearly sales. If enter is pressed without a year preference, the default interval will be used. A window with a linechart displaying the total sales for the given year, grouped by month, intervalwill be created.
		4. **Plot cumulative sales by month (bar chart)**: A window with a barchart that displays the cumulative sales, grouped by month, is created.
		5. **Plot sales by month for specific year (barchart)**: The user wil be asked to enter a specific year to plot the monthly sales. A window displaying a barchart with the monthly sales of the given year is created.
		**Important notice**: Only one plot can be displayed at a time, in order to display another plot, you must first restart the ERP.
	2. **Product Plots**: This menu provides access to various charts of the product sales, the product revenue etc. It consists of the following options:
		1. **Best-Sellers Charts**: The following option displays the charts about the best selling products. Four sub-options are available:<br\>
			1.**Best Sellers of all time**: A chart displaying the 10 most popular products along with a star(\*) barchart will display.<br/>
			2.**Best Sellers of certain year and month**: The user will be asked to enter a certain year. If valid input is entered, a chart with the given year's top 10 best-sellers will display along with a star(\*) barchart. <br/>
			3.**Best Sellers of certain year and month**: The user will be asked to enter a certain year and month. If valid input is entered, a chart with the given year and month's top 10 best-sellers will display along with a star(\*) barchart.<br/>
			4.**Return to previous menu**: The user is redirected to the previous menu.<br/>
		2. **Best-Revenue Charts**: The following option displays the charts about the products with the most revenue. Four sub-options are available:<br/>
			1.**Best Revenue of all time**: A chart displaying the top 10 products with the most revenue along with a star(\*) barchart will display.<br/>
			2.**Best Revenue of certain year and month**: The user will be asked to enter a certain year. If valid input is entered, a chart with the given year's top 10 best-revenue products will display along with a star(\*) barchart. <br/>
			3.**Best Revenue of certain year and month**: The user will be asked to enter a certain year and month. If valid input is entered, a chart with the given year and month's top 10 best-revenue products will display along with a star(\*) barchart.<br/>
			4.**Return to previous menu**: The user is redirected to the previous menu.<br/>
		3. **Best-Seller for each month**: The user is asked to enter a specific year. A valid input is given, a chart with each month's top selling products for the given year is created.
		4.**Return to previous menu**: The user is redirected to the previous menu.
	3. **Predict sales for a following year**: The user will be asked to enter the year for which they want to predict the sales. The program, then, returns a prediction for the specific year, by calculating a simple linear regression equation of the orders registered. **The prediction will display be NaN if sales of only one year exist**.
	4. **Return to previous menu**: Logs the user out and returns to previous menu.
## Administrator
The administrator menu consists of the following options:
1. **Create a new user**: you will be asked to enter a new user's information according to the prompts. After the process is complete, a new user will be created.
2. **Change an existing user's password**: you will be asked to enter the user id of the user you wish to change the password. If the user exists, enter the new password (at least 8 characters long).
3. **Delete an existing user**:  you will be asked to enter an existing user's id that you want to delete. A confirmation message will display, if you want to proceed with user deletion type "Yes", anything else will cancel the process. If the user does not exist you are returned to the previous menu.
4. **View all users**: Display all users.
5. **View all products**: Displays all the products registered.
6. **View all suppliers**: Displays all the suppliers registered.
7. **View all orders**: Displays all the orders made.
8. **View specific orders**: You will be asked to enter the id of the customer of whom you want to print the order history. If the customer does not exist, you are returned to the previous menu.
9. **View all storage orders**: Displays all the storage orders made.
10. **View specific storage orders**: You will be asked to enter the id of a supplier. The storage orders that concerned this supplier are printed. If the supplier does not exist, you are returned to the previous menu.
11. **Save changes**: Saves the changes made to the Data directory.
12. **Return to previous menu**: Logs the administrator out and returns to previous menu.

## Notes while running the program
1. The program is able to manage invalid user inputs and they will not result in an error.
2. The plots might not work as expected in case of no orders are created.
3. Only one plot can be created at a time. In order to create another plot a program restart is required.

# Editing the data
*The external editing of data is not advised. Though, in case you want to do so, you should follow the steps below:*
The ERP must not be running while editing the data as they will be overwritten. 
1. Edit the product list:
	1. Open Products.csv and ProductQuantities.csv and Counters.txt in a text editor.
	2. Enter the product data in the following order in Products.csv: unique_id;product_name;category;description;price (e.g. "1;Small Table;furniture;a small table;80.0")
	3. In ProductQuantities.csv enter: id;quantity (e.g. "1;20" this means product with id = 1 has 20 units available).
	4. Change the value of product counter field in Counters.txt to the same number of the total products created (e.g. if product counter was 4 and you created 2 more products, write 6).
	5. Save changes.
2. Edit the registered customer list:
	1. Open Customers.csv in a text editor.
	2. Enter the customer data in the following order: unique_id;name;address;telephone;points (e.g. 1;Corporation inc.;patission 70, athens;2101234567;1). Telephone must be **at most 10 digits long**.
	3. Change the value of the customer counter in Counters.txt accordingly.
	4. Save changes.
3. Edit the supplier list:
	1. Open Suppliers.csv in a text editor.
	2. Enter the supplier data in the following order: unique_id;name;address;telephone (e.g. 2;Supplier S.A.;mesogeion 230, athens;2109876543;). Telephone must be **at most 10 digits long**.
	3. Change the value of the supplier counter in Counters.txt accordingly.
	4. Save changes.
4. Edit the user list:
	1. Open Users.csv.
	2. Enter the user data in the following order: user_typeunique_id;name;surname;username;password (e.g. Cashier;5;Fotis;Papadopoulos;cashier2;12345678). **The username must be unique**. The available user types are: "Admin" for administrator, "Cashier" for cashier, "Storekeeper" for storekeeper, "DataAnalyst" for data analyst.
	3. Change the value of the supplier counter in Counters.txt accordingly.
	4. Save changes.
**(!) It is not advised to edit orders and storage orders externally**

# Exporting the data
- The data of the ERP can be found in **Directory_With_the_unzipped_program/target/appassembler/bin/Data**

You can export the ERP data found in the Data directory. The .csv files can be opened in other programs for further analysis. Programs that have been tested to be capable of importing the data of the ERP for further editing include Microsoft Excel, Matlab and IBM SPSS.

# Authors

**The program was created by:**
- George Liargovas
- George Drosos
- Rania Pilioura
- Eirini Piperou
- Christina Kardami
- Nikos Mavromaras
- Dimitris Tsaousis
- Aggela Trivola


*This manual was created by:* George Liargovas, Eirini Piperou