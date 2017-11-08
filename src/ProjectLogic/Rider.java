package ProjectLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Rider {
<<<<<<< Updated upstream
=======
    /**
     * Class to create a brand new Customer
     *
     * @throws SQLException
     */
	public void newCustomer() throws SQLException {

	    Scanner customerData = new Scanner(System.in);

	    //Ask the Customer for their relevant data
        System.out.println("What is your first name?");
	    String firstName = customerData.next().toUpperCase();

        System.out.println("What is your last name?");
        String lastName =customerData.next().toUpperCase();

        System.out.println("What is your age (in years)?");
        //little correcting so certain people don't try to break the code
        while(!customerData.hasNextInt()){
            System.out.println("**Invalid age entered!**\nWhat is your age (in years) (please only digits)?");
        }
        int customerAge = customerData.nextInt();

        System.out.println("What is your email address?");
        String customerEmail =customerData.next().toUpperCase();

        System.out.println("What is your password?");
        String customerPassword = customerData.next();

        System.out.println("What is your credit/debit card number?\nPlease enter as such: 1234567891234567");
        //more error correcting
        while(!customerData.hasNextInt()){
            System.out.println("**INVALID CARD NUMBER ENTERED** \nWhat is your 16 digit credit/debit card number?\nPlease enter as such: 1234567891234567");
        }
        int cardNumber = customerData.nextInt();

       try(Connection con = Database.getConnection()){

           String newCustomerQuery = "INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?)";

           PreparedStatement newCustomerStatement = con.prepareStatement(newCustomerQuery);

           //set customer values in SQL and execute
           newCustomerStatement.setString(1,firstName);
           newCustomerStatement.setString(2,lastName);
           newCustomerStatement.setInt(3,customerAge);
           newCustomerStatement.setString(4,customerEmail);
           newCustomerStatement.setString(5,customerPassword);
           newCustomerStatement.setInt(6,cardNumber);
           newCustomerStatement.execute();

           con.close();

       }

       catch (SQLException e){


       }
       finally {
           customerData.close();
       }

    }

    /**
     * This class is designed to gather start, end and rideStyle, and query the database for a currently
     * available driver that is nearby and can handle the parameters given
     *
     * @param startLocation the starting location for the rider, will be asked for in main loop
     * @param endLocation the ending location for the rider, will be asked for in main loop
     * @param rideStyle style of ride: Economy, Cy-Pool, or Cy-lect
     */
    public void callRide(String startLocation, String endLocation, String rideStyle){



    }

    public void RiderCharges(){

    }
    public void rateDriver(int starRating){

    }
    public void customerLogin(){

    }
>>>>>>> Stashed changes
}
