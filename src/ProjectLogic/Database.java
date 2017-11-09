package ProjectLogic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    /**
     * Creates a simple datasource, does not take in an argument
     */

    private static String url;
    private static String username;
    private static String password;

    public static void init() throws ClassNotFoundException, IOException{


        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://45.55.88.122:3306/carRide?autoreconnect=true&useSSL=false";
        username="carRide";
        password="sha256saltedMyCornBeef";

        if(password==null){
            password="";
        }
        if(username==null){
            password="";
        }

        try(Connection connection = Database.getConnection()){

            Statement tableBegin = connection.createStatement();
            //Generate CustomerTable
            tableBegin.execute("CREATE TABLE CUSTOMER (" +
                    "RiderID int NOT NULL AUTO_INCREMENT," +
                    "RiderName VARCHAR(255), " +
                    "Age INTEGER, " +
                    "CustomerEmail VARCHAR(255), " +
                    "Password VARCHAR(255), " +
                    "Card_Number INTEGER," +
                    "RiderRating INTEGER, " +
                    "UNIQUE (RiderID))");
        }
        catch (SQLException e){
            if(e.getErrorCode()==1050){
                //If it gets here it's fine, code is working!
            }
            else{
                System.out.println(e+": "+e.getErrorCode());
            }
        }
        try(Connection con = Database.getConnection()){
            Statement tableBegin = con.createStatement();
            //generate Driver table
            tableBegin.execute("CREATE TABLE DRIVERS (" +
                    "DriverID int NOT NULL AUTO_INCREMENT, " +
                    "DriverName VARCHAR(255), " +
                    "Email VARCHAR (255), " +
                    "Password VARCHAR(255), " +
                    "RideStyle INTEGER," +
                    "LicensePlate VARCHAR(7)," +
                    "Make VARCHAR (20)," +
                    "Model VARCHAR (20)," +
                    "Model_Year INTEGER," +
                    "Availability INTEGER," +
                    "Card_Number INTEGER," +
                    "DriverRating INTEGER," +
                    "DriverEarningsarned DECIMAL(6,2)," +
                    "UNIQUE (DriverID))");

        }
        catch (SQLException e){
            if(e.getErrorCode()==1050){
                //if this is thrown we're fine!
            }
            else{
                System.out.println(e+": "+e.getErrorCode());
            }
        }
        try(Connection connection = Database.getConnection()){
            Statement tableBegin = connection.createStatement();
            //Generate Ride Table
            tableBegin.execute("CREATE TABLE RIDES (" +
                    "RideIdentification int NOT NULL AUTO_INCREMENT," +
                    "RiderID INTEGER," +
                    "DriverID INTEGER," +
                    "StartLocation VARCHAR(300)," +
                    "Destination VARCHAR(300)," +
                    "DistanceTraveled DECIMAL(5,2)," +
                    "MonetaryAmount DECIMAL(6,2)," +
                    "UNIQUE (RideIdentification))");
        }
        catch (SQLException e){
            if(e.getErrorCode()==1050){
                //If this is thrown, tables are created. We're fine!
            }
            else{
                System.out.println(e+": "+e.getErrorCode());
            }
        }
    }

    /**
     * @return database connection
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

}
