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
public class Bill implements java.io.Serializable {

    int userChoice, billID = 0;
    static transient int billNumber = 0;
    transient Scanner userInpt = new Scanner(System.in);
    double moneyPaid, debt, totalPrice, profit;
    Date date = new Date();
    ArrayList<Drug> containers = new ArrayList<>();
    ArrayList<Integer> drugQuantityInEachBill = new ArrayList<>();
    String customerName;          // when adding the customer name to each bill this shall save alot of work of "client and non client categorizing" 

    public Bill() {
        billNumber++;
        billID = billNumber;
    }

    public void addDrug(Drug d, int drugQuantity) {
        containers.add(d);
        drugQuantityInEachBill.add(drugQuantity);
    }

    public void removeDrug(Drug d, int drugQuantity) {
        containers.remove(d);
        drugQuantityInEachBill.remove(drugQuantity);
    }

    public double getProfitPerBill() {

        for (int i = 0; i < containers.size(); i++) {
            profit += (containers.get(i).totalSellingPrice - containers.get(i).totalBuyingPrice); // calculating profit per bill 
        }

        return profit;
    }

    public void displayBill() {

        System.out.print("bill date : ");
        date.displayDate();
        System.out.println("Customer name: " + customerName);
        System.out.println("Bill ID : " + billID);

        for (int i = 0; i < containers.size(); i++) {

            System.out.printf("Drug Name : %s      amount : %d       price : %d %n", containers.get(i).name,
                    drugQuantityInEachBill.get(i), containers.get(i).totalSellingPrice);
            totalPrice = totalPrice + containers.get(i).totalSellingPrice;
        }
        System.out.printf("totalPrice = %d %n" + totalPrice);

        System.out.printf("%n debt = %d %n" + debt);
    }

    public void editBill() {

        System.out.println("To edit Bill number press 1 : ");
        System.out.println("To edit money paid press 2 : ");
        System.out.println("To edit debt value prss 3 : ");
        System.out.println("To edit total price press 4 : ");
        System.out.println("To edit Customer name press 5 : ");

        do {
            System.out.println("User input : ");

            userChoice = userInpt.nextInt();

            if (userChoice > 5 || userChoice < 0) {
                System.out.println("Invalid input, please insert a valid one !! ");
            }

        } while (userChoice > 5 || userChoice < 0);

        switch (userChoice) {

            case 1: {
                System.out.print("new Bill ID : ");
                billID = userInpt.nextInt();
            }
            break;

            case 2: {
                System.out.print("new money paid value : ");
                moneyPaid = userInpt.nextDouble();
            }
            break;

            case 3: {
                System.out.print("new debt value : ");
                debt = userInpt.nextDouble();
            }
            break;

            case 4: {
                System.out.print("new total price   : ");
                totalPrice = userInpt.nextDouble();
            }
            break;

            case 5: {
                System.out.print("new Customer name : ");
                customerName = userInpt.nextLine();
            }
            break;

        }
    }

    public int getDrugIndex(String drugName) {
        int n = -1;
        for (int i = 0; i < containers.size(); i++) {
            if (drugName == containers.get(i).name) {
                n = i;
            }
        }
        return n;
    }

}
