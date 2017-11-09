package ProjectLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Daniel Tidyman
 *
 * Updated 8.11.2017
 */
public class Rider {
    private String startLocation;
    private String destination;
    private double distanceTraveled;
    private int rideStyle;
    private String customerEmail;
    private String customerCardNumber;
    private double customerChargeAmount;

    /**
     * Constructs a new Rider object
     */
    public Rider(){

    }

    /**
     * Class to create a brand new Customer
     *
     */
	public void NewCustomer() {

        Scanner customerData = new Scanner(System.in);

        //Ask the Customer for their relevant data
        System.out.println("\nWhat is your first name?");
        String firstName = customerData.next();
        //TODO handle spaces in names
        System.out.println("What is your last name?");
        String lastName =customerData.next();

        System.out.println("What is your age (in years)?");
        //little correcting so certain people don't try to break the code
        while(!customerData.hasNextInt()){
            customerData.next();
            System.out.println("**Invalid age entered!**\nWhat is your age (in years) (please enter only digits)?");
        }
        int customerAge = customerData.nextInt();

        System.out.println("What is your email address?");
        String customerEmail =customerData.next();

        System.out.println("What is your password?");
        String customerPassword = customerData.next();

        System.out.println("What is your credit/debit card number?\nPlease enter as such: 1234567891234567");
        //more error correcting
        while(customerData.next().length() <16 || customerData.next().length()>16){
            customerData.next();
            System.out.println("\n**INVALID CARD NUMBER**\nWhat is your credit/debit card number?\nPlease enter as such: 1234567891234567");
        }
        customerCardNumber = customerData.next();

       try(Connection con = Database.getConnection()){

           String newCustomerQuery = "INSERT INTO CUSTOMER (RiderName,Age,CustomerEmail,Password,Card_Number) VALUES(?,?,?,?,?)";

           PreparedStatement newCustomerStatement = con.prepareStatement(newCustomerQuery);

           //set customer values in SQL and execute
           newCustomerStatement.setString(1,firstName+" "+lastName);
           newCustomerStatement.setInt(2,customerAge);
           newCustomerStatement.setString(3,customerEmail);
           newCustomerStatement.setString(4,customerPassword);
           newCustomerStatement.setString(5,customerCardNumber);
           newCustomerStatement.execute();

           con.close();

       }
       catch (SQLException e){
           System.out.println(e);
       }
    }

    /**
     * This class is designed to gather start location, destination and rideStyle, and query the database for a currently
     * available driver that is nearby and can handle the parameters given. This class also sets the variables that are used
     * to calculate price
     */
    public void callRide(){

        Scanner rideDetails = new Scanner(System.in);

        System.out.println("Please enter your starting address (including City, State and Zip)\nExample: 1234 North Cy Drive, Ames, IA 50010\n");
        startLocation = rideDetails.next();

        System.out.println("Please enter your destination address (including City, State and Zip)\nExample: 1234 North Cy Drive, Ames, IA 50010\n");
        destination = rideDetails.next();

        System.out.println("Please select your ride style by typing the corresponding number: \n(1) Regular\n(2) Car Pool\n(3)Cy-lect\n");
        while(!rideDetails.hasNextInt() || rideDetails.nextInt()<1 || rideDetails.nextInt()>3){
            System.out.println("**INVALID SELECTION**\n \nPlease select your ride style by typing the corresponding number: \n(1) Regular\n(2) Car Pool\n(3)Cy-lect\n");
        }
        rideStyle = rideDetails.nextInt();

        try(Connection connection = Database.getConnection()){

            //TODO fix select
            String rideQuery = "SELECT * FROM DRIVERS WHERE RIDESTYLE=? AND AVAILABILITY = 1";
            PreparedStatement ridePrep = connection.prepareStatement(rideQuery);

            ridePrep.setInt(1,rideStyle);
            //TODO FINISH THIS WE HAVE TO FIND A WAY TO GATHER DISTANCES FROM A POINT IN A DATABASE AND LOCATE DRIVERS

            connection.close();

        }
        catch (SQLException e){
            //TODO
        }
    }

    public void RiderCharges(){

    }
    public void rateDriver(){

    }
    public void customerLogin(){

        Scanner loginScanner = new Scanner(System.in);

        //These next few lines try to gather customer data and search a database for corresponding entries.
        System.out.println("Enter your email: ");
        customerEmail = loginScanner.next();

        System.out.println("Enter your password: ");
        String password = loginScanner.next();

        try(Connection connection = Database.getConnection()){

            //Since all we need is the customerEmail and cardNumber, we just select these two fields
            String loginQuery = "SELECT CustomerEmail, Card_Number FROM CUSTOMER WHERE CustomerEmail=? AND PASSWORD=?";

            //these set and execute the query to try and find the matching customer data
            PreparedStatement loginSearch = connection.prepareStatement(loginQuery);
            loginSearch.setString(1,customerEmail);
            loginSearch.setString(2,password);
            ResultSet resultSet = loginSearch.executeQuery();

            //tests to see if we have correct login information
            while (!resultSet.next()){
                System.out.println("\n**INVALID EMAIL/PASSWORD. PLEASE TRY AGAIN**\n \nEnter your email: ");
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

            System.out.println("\n**LOGIN SUCCESSFUL**");

        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    public String returnLogin(){
        //TODO
        return null;
    }
}
