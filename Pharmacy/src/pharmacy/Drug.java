/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mustafa-Yehia 
 */
public class Drug implements java.io.Serializable {

    String name, type, producingCompany, bestDealer, availability, legality;

    int barCode, maximumQuantity, minimumQuantity, availableQuantity,
            shelfNumber, storageTemperature;

    double totalSellingPrice, tableSellingPrice, discount, totalBuyingPrice;

    ArrayList<String> dealers = new ArrayList<String>();
    ArrayList<String> substituations = new ArrayList<String>();
    Date expirationDate = new Date();
     // transient  Scanner input = new Scanner(System.in);
        int userInpt=0;
        transient Scanner input = new Scanner(System.in);
   

    public void getName() {
        System.out.format("Drug name :%s", name);
    }

    public void displayDrug() { //view drug is the method inside the main class....displayDrug is the method belongs to the Drug class 
        
        System.out.println("Name : " + name);
        System.out.println("Type :" + type);
        System.out.println("Producing company :" + producingCompany);
        System.out.println("Best dealer :" + bestDealer);
        System.out.println("BarCode :" + barCode);
        System.out.println("Minimum Quantity :" + minimumQuantity);
        System.out.println("Maximum Quantity :" + maximumQuantity);
        System.out.println("Shelf Number :" + shelfNumber);
        System.out.println("Storage temperature :" + storageTemperature);
        System.out.println("Total Selling price :" + totalSellingPrice);
        System.out.println("Total Buying price :" + totalBuyingPrice);
        System.out.println("Table selling price :" + tableSellingPrice);
        System.out.println("Discount value :" + discount);
        System.out.println("Availablity :" + availability);
        System.out.println("Available quantity  :" + availableQuantity);

        System.out.println("Substituations :");

        for (int i = 0; i < substituations.size(); i++) {
            System.out.println(substituations.get(i));
        }

        System.out.println("Dealers :");

        for (int i = 0; i < dealers.size(); i++) {
            System.out.println(dealers.get(i));
        }

    }

    public void editDrug() {
   
        System.out.println("To edit Name press 1 : ");
        System.out.println("To edit Type press 2 : ");
        System.out.println("To edit Producing company prss 3 : ");
        System.out.println("To edit Best dealer press 4 : ");
        System.out.println("To edit BarCode press 5 : ");
        System.out.println("To edit Minimum Quantity press 6 : ");
        System.out.println("To edit Maximum Quantity press 7 : ");
        System.out.println("To edit Shelf Number press 8 : ");
        System.out.println("To edit Storage temperature press 9 : ");
        System.out.println("To edit Total selling price press 10 : ");
        System.out.println("To edit Table price press 11 : ");
        System.out.println("To edit Discount value press 12 : ");
        System.out.println("To edit Availablity press 13  :");
        System.out.println("To edit Legality press 14 : ");
        System.out.println("To edit total Buying press 15 : ");
        System.out.println("To edit available quantity press 16:" + availableQuantity);

        do {
            System.out.println("User input : ");
                input = new Scanner(System.in);

            userInpt = input.nextInt();

            if (userInpt > 16 || userInpt < 0) {
                System.out.println("Invalid input, please insert a valid one !! ");
            }

        } while (userInpt > 16 || userInpt < 0);
        String s;

        switch (userInpt) {

            case 1: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new name : ");
                name = input.nextLine();
            }
            break;

            case 2: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new type : ");
                type = input.nextLine();
            }
            break;

            case 3: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new producing company : ");
                producingCompany = input.nextLine();
            }
            break;

            case 4: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new best Dealer : ");
                bestDealer = input.nextLine();
            }
            break;

            case 5: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new Bar Code  : ");
                barCode = input.nextInt();
            }
            break;

            case 6: {
                            Scanner input = new Scanner(System.in);

                System.out.print("new minimum quantity : ");
                minimumQuantity = input.nextInt();
            }
            break;

            case 7: {            Scanner input = new Scanner(System.in);

                System.out.print("new maximum quantity : ");
                maximumQuantity = input.nextInt();
            }
            break;

            case 8: {            Scanner input = new Scanner(System.in);

                System.out.print("new shelf number: ");
                shelfNumber = input.nextInt();
            }
            break;

            case 9: {            Scanner input = new Scanner(System.in);

                System.out.print("new storage temperature : ");
                storageTemperature = input.nextInt();
            }
            break;

            case 10: {            Scanner input = new Scanner(System.in);

                System.out.print("new total selling  price : ");
                totalSellingPrice = input.nextDouble();
            }
            break;

            case 11: {            Scanner input = new Scanner(System.in);

                System.out.print("new table price : ");
                tableSellingPrice = input.nextDouble();
            }
            break;

            case 12: {            Scanner input = new Scanner(System.in);

                System.out.print("new Discount value : ");
                discount = input.nextDouble();
            }
            break;

            case 13: {            Scanner input = new Scanner(System.in);

                System.out.print("new availability value ( 0 for not available 1 for available : ");
                availability = input.next();  // couldn't implement it using bool as there is no nextBool () in the scanner class 
            }
            break;

            case 14: {            Scanner input = new Scanner(System.in);

                System.out.print("new legality  value ( 0 for illegal  1 for legal : ");
                legality = input.next();
            }
            break;

            case 15: { Scanner input = new Scanner(System.in);
                System.out.print("new total buying  price : ");
                totalBuyingPrice = input.nextDouble();
            }
            break;

            case 16: { Scanner input = new Scanner(System.in);
                System.out.print("new available Quantity : ");
                availableQuantity = input.nextInt();
            }
            break;

        }

    }

}
