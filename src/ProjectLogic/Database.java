package ProjectLogic;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

    /**
     * Creates a simple datasource, does not take in an argument
     *
     */
    private static String url;
    private static String username;
    private static String password;

    public static void init() throws ClassNotFoundException, IOException{

        Properties props = new Properties();
        FileInputStream file = new FileInputStream("database.properties");
        props.load(file);

        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username=props.getProperty("jdbc.username");
        password=props.getProperty("jdbc.password");

        if(password==null){
            password="";
        }
        if(username==null){
            password="";
        }
        if (driver != null) {
            Class.forName(driver);
        }


        try(Connection connection = Database.getConnection()){

            Statement tableBegin = connection.createStatement();
            //Generate CustomerTable
            tableBegin.execute("CREATE TABLE CUSTOMER (" +
                    "RiderID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "RiderName VARCHAR(255), " +
                    "Age INTEGER, " +
                    "CustomerEmail VARCHAR(255), " +
                    "Password VARCHAR(255), " +
                    "Card_Number INTEGER," +
                    "RiderRating INTEGER, " +
                    "UNIQUE (RiderID))");
        }
        catch (SQLException e){
            if(e.getErrorCode()==30000){
                System.out.println("Customer Table Created Already.");
            }
            else{
                System.out.println(e+": "+e.getErrorCode());
            }
        }
        try(Connection con = Database.getConnection()){
            Statement tableBegin = con.createStatement();
            //generate Driver table
            tableBegin.execute("CREATE TABLE DRIVERS (" +
                    "DriverID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
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
            if(e.getErrorCode()==30000){
                System.out.println(e);
            }
            else{
                System.out.println(e+": "+e.getErrorCode());
            }
        }
        try(Connection connection = Database.getConnection()){
            Statement tableBegin = connection.createStatement();
            //Generate Ride Table
            tableBegin.execute("CREATE TABLE RIDES (" +
                    "RideIdentification INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "RiderID INTEGER," +
                    "DriverID INTEGER," +
                    "StartLocation VARCHAR(300)," +
                    "Destination VARCHAR(300)," +
                    "DistanceTraveled DECIMAL(5,2)," +
                    "MonetaryAmount DECIMAL(6,2))");
        }
        catch (SQLException e){
            if(e.getErrorCode()==30000){
                System.out.println("Rides Table Created Already.");
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
