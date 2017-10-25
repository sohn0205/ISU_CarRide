package ProjectLogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    /**
     * Creates a simple datasource, does not take in an argument
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
    }
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

}
