import java.util.NoSuchElementException;

public class RegisteredCustomerMenuTest {
	
	public static void loadObjects() {
		new RegisteredCustomer("nikos", "peloponisou 107", 2105024098, 100);
		new RegisteredCustomer("maria", "peloponisou 10", 2105054468, 98);
		new RegisteredCustomer("giorgos", "rodoy 7", 2105024345, 540);
		new RegisteredCustomer("giorgos", "kanari 17", 2105027698, 340);
		new RegisteredCustomer("xristina", "mavrogenous 117", 2134024098, 2300);
		new RegisteredCustomer("rania", "kanari 237", 2106014234, 153);
		new RegisteredCustomer("aggela", "antoniadou 137", 2105043298, 123);
		new RegisteredCustomer("marios", "derigni 27", 2105026548, 154);
		new RegisteredCustomer("kostas", "mavromateon 47", 2109871098, 54);
		new RegisteredCustomer("dimitris", "xarilaou trikoupi 57", 2109854098, 345);
		new RegisteredCustomer("sotiris", "athanasiou 106", 2105024543, 356);
		new RegisteredCustomer("eva", "mpoumpoulinas 106", 2105984098, 67);
		new RegisteredCustomer("evi", "rodou 74", 2105025428, 156);
		new RegisteredCustomer("katerina", "patision 90", 2104356098, 124);
		new RegisteredCustomer("iggy", "valtetsiou 37", 2105024548, 165);
		new RegisteredCustomer("pelopidas", "peloponisou 56", 2105432098, 650);
		}
	
	public static void main(String[] args) {
		loadObjects();
		try {
			System.out.println("\n\nChage customer address test\n");
			RegisteredCustomer.changeCustomerAddressMenu();
			System.out.println("\n\nChage customer name test\n");
			RegisteredCustomer.changeCustomerNameMenu();
			System.out.println("\n\nChage customer points test\n");
			RegisteredCustomer.changeCustomerPointsMenu();
			System.out.println("\n\nChage customer telephone test\n");
			RegisteredCustomer.changeCustomerTelephoneMenu();
			System.out.println("\n\nDelete customer test\n");
			RegisteredCustomer.deleteCustomerMenu();
			System.out.println("\n\nRegister new customer test\n");
			RegisteredCustomer.registerNewCustomerMenu();
			RegisteredCustomer.printAllCustomers();
			System.out.println("\n\nSearch and print customer by address test\n");
			RegisteredCustomer.searchAndPrintCustomerByAddressMenu();
			System.out.println("\n\nSearch and print customer by id test\n");
			RegisteredCustomer.searchAndPrintCustomerByIdMenu();
			System.out.println("\n\nSearch and print customer by name test\n");
			RegisteredCustomer.searchAndPrintCustomerByNameMenu();
			System.out.println("\n\nSearch and print customers by points test\n");
			RegisteredCustomer.searchAndPrintCustomerByPointsMenu();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception occured test failed");
			System.exit(1);
		}
	}
	
}
