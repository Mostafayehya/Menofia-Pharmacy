/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.util.Scanner;

/**
 *
 * @author Mustafa-Yehia 
 */
public class Supplier  implements java.io.Serializable {

    String name, email, type;
    double ownedMoney;
    int mobileNumber;
    Date payingDebtDate = new Date();

    transient Scanner userInpt = new Scanner(System.in);
    int userChoice;

    public void displaySupplier() {
        System.out.printf("Supplier name : %s%n OwnedMoney : %s%n Supplier email : %s%n Mobile Number : %d%n type : %s %n",
                name, ownedMoney, email, mobileNumber, type);
        payingDebtDate.displayDate();

    }

    public Date getPayingDeptDate() {
        return payingDebtDate;
    }

    public void editSupplier() {

        System.out.println("To edit Name press 1 : ");
        System.out.println("To edit E-mail press 2 : ");
        System.out.println("To edit type prss 3 : ");
        System.out.println("To edit money owned  press 4 : ");
        System.out.println("To edit Mobile number press 5 : ");
        System.out.println("To edit date of paying debt press 6 : ");

        do {
            System.out.println("User input : ");

            userChoice = userInpt.nextInt();

            if (userChoice > 6 || userChoice < 0) {
                System.out.println("Invalid input, please insert a valid one !! ");
            }

        } while (userChoice > 6 || userChoice < 0);

        switch (userChoice) {

            case 1: {
                System.out.print("new name : ");
                name = userInpt.nextLine();
            }
            break;

            case 2: {
                System.out.print("new E-Mail : ");
                email = userInpt.nextLine();
            }
            break;

            case 3: {
                System.out.print("new type : ");
                type = userInpt.nextLine();
            }
            break;

            case 4: {
                System.out.print("new value of money owned : ");
                ownedMoney = userInpt.nextDouble();
            }
            break;

            case 5: {
                System.out.print("new mobile nubmer  : ");
                mobileNumber = userInpt.nextInt();
            }
            break;

            case 6: {
                System.out.print("new date of paying debt : ");
                payingDebtDate.setDate();
            }
            break;

        }
    }
}
