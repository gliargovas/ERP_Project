
public class LoginTest {

	//create dummy users
	public static void loadUsers() {
		new Cashier ("CName1","Surname1","cuser1","cpass1");
		new Cashier ("CName2","Surname2","cuser2","cpass2");
		new Cashier ("CName3","Surname3","cuser3","cpass3");
		new Cashier ("CName4","Surname4","cuser4","cpass4");
		new Storekeeper ("SName1","Surname1","suser1","spass1");
		new Storekeeper ("SName2","Surname2","suser2","spass2");
		new Storekeeper ("SName3","Surname3","suser3","spass3");
		new Storekeeper ("SName4","Surname4","suser4","spass4");
		new DataAnalyst ("DAName1","Surname1","dauser1","dapass1");
		new DataAnalyst ("DAName2","Surname2","dauser2","dapass2");
		new DataAnalyst ("DAName3","Surname3","dauser3","dapass3");
		new DataAnalyst ("DAName4","Surname4","dauser4","dapass4");
		new Administrator ("AName1","Surname4","auser1","apass1");
		new Administrator ("AName4","Surname4","auser2","apass2");	
		new Administrator ("AName4","Surname4","auser3","apass3");	
		new Administrator ("AName4","Surname4","auser4","apass4");	
	}
	
	public static void main (String args[]) {
		loadUsers();
		// 1st test: correct credentials entered for all users
		try {
			System.out.println("Start");
			Cashier.login("cuser3", "cpass3");
			Storekeeper.login("suser1", "spass1");
			DataAnalyst.login("dauser1", "dapass1");
			Administrator.login("auser1", "apass1");
		} catch (Exception e) {
				System.err.println("Test1 failed!");
				e.printStackTrace();
				System.exit(1);
		}
		// end of 1st test
		
		// 2nd test: wrong credentials given
		System.out.println("Begin test 2");
		try {
			Cashier.login("cuser3", "cpass4");
			System.err.println("Test 2 on cashier failed");
			System.exit(1);
		} catch (Exception e) {
			if (!(e.getMessage().equals("Invalid Credentials"))) {
				System.out.println("Test 2 on cashier failed");
				System.exit(0);
			}
		}
		try {
			Storekeeper.login("cuser3", "cpass3");
			System.err.println("Test 2 on storekeeper failed");
			System.exit(1);
		} catch (Exception e) {
			if (!(e.getMessage().equals("Invalid Credentials"))) {
				System.out.println("Test 2 on storekeeper failed");
				System.exit(0);
			}
		}
		try {
			DataAnalyst.login("dauser11", "dapass1");
			System.err.println("Test 2 on data analyst failed");
			System.exit(1);
		} catch (Exception e) {
			if (!(e.getMessage().equals("Invalid Credentials"))) {
				System.out.println("Test 2 on data analyst failed");
				System.exit(0);
			}
		}
		try {
			Administrator.login("auser1", "auser13");
			System.err.println("Test 2 on administrator failed");
			System.exit(1);
		} catch (Exception e) {
			if (!(e.getMessage().equals("Invalid Credentials"))) {
				System.out.println("Test 2 on administrator failed");
				System.exit(0);
			}
		}
		System.out.println("All tests ok!");
		// end of 2nd test
		System.exit(0);
	}

}
