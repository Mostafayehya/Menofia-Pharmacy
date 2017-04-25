/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mustafa-Yehia 
 */

public class Customer  implements java.io.Serializable {

    String name;
    ArrayList<Bill> bills = new ArrayList<Bill>();
    int phoneNumber,customerID;
    static int customerNumber; 
    double totalDebt;//not included in the uml but it is necessary for communicating in case of ordering a special drug . 

   public Customer(){
       customerNumber++;
       customerID=customerNumber;
   }

    public void editCustomer() {
        System.out.println("To edit Name press 1");
        System.out.println("To edit phone number press 2");
        System.out.println("To edit total dept press 3");
        Scanner input = new Scanner(System.in);
        int editChoice = input.nextInt();

        if (editChoice == 1) {
            System.out.println("Enter new name :");
            name = input.nextLine();

        }

        if (editChoice == 2) {
            System.out.print("Enter new phone number :");
            phoneNumber = input.nextInt();

        }

        if (editChoice == 3) {
            System.out.print("Enter new total debt :");
            totalDebt = input.nextDouble();

        }

    }
    
    public void addBill(Bill toAdd){
        
        bills.add(toAdd);
    }

    public void viewCustomer() {

        System.out.println("Name :" + name);
        System.out.println("Phone number :" + phoneNumber);
        System.out.println("Total debt :" + totalDebt);
        System.out.println("Number of bills :" + bills.size());
        viewBills();

    }

    public void viewBills() {

        System.out.println("Bill number          Debt value          Bill date");
        for (int i = 0; i < bills.size(); i++) {
            System.out.print(bills.get(i). billID + "            " + bills.get(i).debt + "          ");
            bills.get(i).date.displayDate();
            System.out.println();
        }
    }

}
