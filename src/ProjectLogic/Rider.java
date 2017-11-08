package ProjectLogic;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Rider {
    public String startLocation;
    public String destination;
    public int rideStyle;

    private String customerEmail;
    private String customerCardNumber;
    private double customerChargeAmount;

    /**
     * Class to create a brand new Customer
     *
     */
	public void newCustomer() {

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

        //TODO add a password hash to be super secure?

        System.out.println("What is your credit/debit card number?\nPlease enter as such: 1234567891234567");
        //more error correcting

        while(!customerData.hasNextInt()){
            System.out.println("**INVALID CARD NUMBER ENTERED** \nWhat is your 16 digit credit/debit card number?\nPlease enter as such: 1234567891234567");
        }
        int cardNumber = customerData.nextInt();
        //TODO add the algorithm that can check for valid card numbers and check the PIN/Expiri date?

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
     * This class is designed to gather start location, destination and rideStyle, and query the database for a currently
     * available driver that is nearby and can handle the parameters given. This class also sets the variables that are used
     * to calculate price
     */
    public void callRide(){

        Scanner rideDetails = new Scanner(System.in);

        System.out.println("Please enter your starting address (including City, State and Zip)\nExample: 1234 North Cy Drive, Ames, IA 50010");
        startLocation = rideDetails.next();

        System.out.println("Please enter your destination address (including City, State and Zip)\nExample: 1234 North Cy Drive, Ames, IA 50010");
        destination = rideDetails.next();

        System.out.println("Please select your ride style by typing the corresponding number: \n(1) Regular\n(2) Car Pool\n(3)Cy-lect");
        while(!rideDetails.hasNextInt() || rideDetails.nextInt()<1 || rideDetails.nextInt()>3){
            System.out.println("**INVALID SELECTION**\n \nPlease select your ride style by typing the corresponding number: \n(1) Regular\n(2) Car Pool\n(3)Cy-lect");
        }

        rideStyle = rideDetails.nextInt();

        try(Connection connection = Database.getConnection()){

            //TODO fix select
            String rideQuery = "SELECT FROM DRIVERS WHERE RIDESTYLE=?";
            PreparedStatement ridePrep = connection.prepareStatement(rideQuery);

            ridePrep.setInt(1,rideStyle);
            //TODO FINISH THIS

            connection.close();

        }
        catch (SQLException e){

        }
        finally {
            rideDetails.close();
        }

    }

    public void RiderCharges(){

    }
    public void rateDriver(int starRating){

    }
    public void customerLogin(){

        Scanner loginScanner = new Scanner(System.in);

        System.out.println("Enter your email: ");
        customerEmail = loginScanner.next();

        System.out.println("Enter your password: ");
        String password = loginScanner.next();

        try(Connection connection = Database.getConnection()){

            String loginQuery = "SELECT CustomerEmail, Card_Number FROM CUSTOMER WHERE EMAIL=? AND PASSWORD=?";

            PreparedStatement loginSearch = connection.prepareStatement(loginQuery);
            loginSearch.setString(1,customerEmail);
            loginSearch.setString(2,password);

            ResultSet resultSet = loginSearch.executeQuery();

            //tests to see if we have correct login information
            while (resultSet.getString(1).isEmpty()){
                System.out.println("**INVALID EMAIL/PASSWORD. PLEASE TRY AGAIN**\n \nEnter your email: ");
                customerEmail = loginScanner.next();

                System.out.println("Enter your password: ");
                password = loginScanner.next();

                loginSearch.setString(1,customerEmail);
                loginSearch.setString(2,password);

                resultSet = loginSearch.executeQuery();
            }

            //set customer data so we can charge 💰💰💰
            customerEmail=resultSet.getString(1);
            customerCardNumber = resultSet.getString(2);

            System.out.println("**LOGIN SUCCESSFUL**");

        }
        catch (SQLException e){

        }
    }
}
