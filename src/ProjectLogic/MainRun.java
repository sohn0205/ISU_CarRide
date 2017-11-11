package ProjectLogic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRun {

    public static void main(String args[]) throws
            SQLException, IOException, ClassNotFoundException {
        System.out.println("Going online...Please be patient\n");
        Database.init();
        System.out.println("You are now connected.\n");

        Scanner mainMenu = new Scanner (System.in);

        System.out.println("**************************************\n" +
                "*                                   *\n" +
                "* Welcome to ISU's Car Ride System! *\n" +
                "*                                   *" +
                "\n**************************************");
        System.out.println("\nPlease select if you are a: \n(1) new Customer \n(2) Driver \n(3) previous customer that needs to login");
        System.out.println("\nPlease type number of selection here: ");

        switch(mainMenu.nextInt()){
            case 1:
                Rider rider = new Rider();

                rider.customerLogin();
            case 2:
                Rider rider1 = new Rider();
                rider1.NewCustomer();

        }



    }

}

