import java.util.ArrayList;
public class RegisteredCustomer extends Customer {
	//instance variables
	private int id;
	private int points;
	//staitc variables
	private static int idcounter = 0;
	private static ArrayList<RegisteredCustomer> customers = new ArrayList<RegisteredCustomer>();
	public RegisteredCustomer(String name, String surname, String adress, int telephone, int points) {
		super(name, surname, adress, telephone);
		this.points = points;
		this.id = idcounter;
		//adding registered customer to arraylist
		customers.add(this);
		idcounter++;
		//adding customer to csv file
		FileHandler.writeToCSV(this);
	}

}
