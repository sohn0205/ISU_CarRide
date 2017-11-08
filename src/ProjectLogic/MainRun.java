package ProjectLogic;

import java.io.IOException;
import java.sql.SQLException;

public class MainRun {

    public static void main(String args[]) throws
            SQLException, IOException, ClassNotFoundException {

        Database.init();

        System.out.println("**************************************\n" +
                "*                                   *\n" +
                "* Welcome to ISU's Car Ride System! *\n" +
                "*                                   *" +
                "\n**************************************");
        System.out.println("\nPlease select if you are a: \n(1) new Customer \n(2) Driver \n(3) previous customer that needs to login");
        System.out.println("\nPlease type number of selection here: ");



    }

}

