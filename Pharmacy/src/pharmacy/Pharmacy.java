/*
 *This is a console application to manage a pharmacy . 
 //When moving project from a pc to another make sure to change the path of the data files in serializing and deserializing methods .
 // editDrug needs to include editing substituations and dealers 
 * 
 */ //Relative path 
/*
 *DESIGN , DEBUGGING AND TESTING  WERE THE MOST TIME CONSUMING TASKS IN THE PROJECT !! 

 */
package pharmacy;

import java.io.*;
import java.util.*;

/**
 *
 * @author Mustafa-Yehia
 */
public class Pharmacy {

    /**
     * @param args the command line arguments
     */
    static int choice;

    /*public static void InitializeObjects(ArrayList<Drug> DrugsDB,ArrayList<Customer> CustomerDB,ArrayList<Supplier> SuppliersDB,ArrayList<Drug> ToBuyListDB,ArrayList<Bill>SalesDB){
        
     }*/
    //
    //When moving project from a pc to another make sure to change the path of the files in serializing and deserialing methods .
    //
    public static <T> void Serialize(ArrayList<T> DB, String name) {
        try {                             //serializing CustomerDB .
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Mostafa\\Desktop\\java programs\\pharmacy 2\\Pharmacy\\src\\pharmacy\\" + name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(DB);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //
    //When moving project from a pc to another make sure to change the path of the files in serializing and deserialing methods .
    //
    public static <T> ArrayList<T> Deserialize(ArrayList<T> DB, String name) {
        try {                                 //Deserializing DrugsDB object .
            FileInputStream fis = new FileInputStream("C:\\Users\\Mostafa\\Desktop\\java programs\\pharmacy 2\\Pharmacy\\src\\pharmacy\\" + name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            DB = (ArrayList<T>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return DB;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return DB;

        }
        return DB;
    }

    public static void viewDrug(ArrayList<Drug> DrugsDB) {          //view drug is the method inside the main class....displayDrug is the method belongs to the Drug class 
        Scanner userInput = new Scanner(System.in);
        int drugIndex;

        System.out.print("Insert the barcode : ");
        int barCode = userInput.nextInt();
        System.out.println();

        //   ArrayList<Drug> DrugsDB = new ArrayList<Drug>();
        for (drugIndex = 0; drugIndex < DrugsDB.size(); drugIndex++) //liner search O(N)  " slow " , implemented for simplicity regarding the DB size , but this could be much far optimized 
        {
            if (DrugsDB.get(drugIndex).barCode == barCode) {
                DrugsDB.get(drugIndex).displayDrug();
                break;
            }
        }
        //the following code can be seperated in a dedicated method called Edit drug , but i have no idea
        //what is the best place to put it into 1)inside this method  2) drug's class 3) before the main function 

        System.out.println("To Edit drug's data press 1 ");
        System.out.println("To return to the main screen press any key");
        int x = userInput.nextInt();
        if (x == 1) {
            DrugsDB.get(drugIndex).editDrug();
        }
        Serialize(DrugsDB, "DrugsDB.ser");

    }  // all the following methods are defined as static to eliminate the error of calling a non static method from a static context 

    public static void addDrugToDB(ArrayList<Drug> DrugsDB) {
        Drug newDrug = new Drug();
        Scanner userInput = new Scanner(System.in);
        int x = 0;

        do { // looping to add quntities to DB or to insert a correct barCode (different one ) 
            System.out.print("Insert BarCode : ");
            newDrug.barCode = userInput.nextInt();

            for (Drug drug : DrugsDB) {
                if (newDrug.barCode == drug.barCode) {
                    System.out.println("The drug inserted is already existed in the DB%n Press 1 to adjust the  Quantity of this drug%nOr 2 to try again !!%n");
                    x = userInput.nextInt();
                    if (x == 1) {
                        System.out.print("Insert Quantity you want to add : ");
                        drug.availableQuantity += userInput.nextInt();
                    }
                }
            }

        } while (x == 1 || x == 2);
        userInput.nextLine();
        System.out.print("Insert Name : ");
        newDrug.name = userInput.nextLine();

        System.out.print("Insert producing company : ");
        newDrug.producingCompany = userInput.nextLine();

        System.out.print("Insert Type : ");
        newDrug.type = userInput.nextLine();

        System.out.print("Insert Best dealer : ");
        newDrug.bestDealer = userInput.nextLine();

        System.out.print("Insert Minimum Quantity : ");
        newDrug.minimumQuantity = userInput.nextInt();

        System.out.print("Insert Maximum Quantity : ");
        newDrug.maximumQuantity = userInput.nextInt();

        System.out.print("Insert Shelf Number : ");
        newDrug.shelfNumber = userInput.nextInt();

        System.out.print("Insert Storage temperature : ");
        newDrug.storageTemperature = userInput.nextInt();

        System.out.print("Insert Total Selling price : ");
        newDrug.totalSellingPrice = userInput.nextDouble();

        System.out.print("Insert Total Buying price : ");
        newDrug.totalBuyingPrice = userInput.nextDouble();

        System.out.print("Insert Table selling price : ");
        newDrug.tableSellingPrice = userInput.nextDouble();

        System.out.print("Insert Discount value : ");
        newDrug.discount = userInput.nextDouble();
        System.out.print("Insert Availablity  (1) for available and 0 for unavailable :");
        newDrug.availability = userInput.next();
        System.out.print("Available quantity  : ");
        newDrug.availableQuantity = userInput.nextInt();

        System.out.println("Insert Substituations (One per line)  : ");

        String y;
        do {
            String toAdd;
            userInput.nextLine();
            toAdd = userInput.nextLine();
            newDrug.substituations.add(toAdd);
            System.out.println("Substituation added !! ");
            System.out.println("To add another substitution press y  , To continue inserting the rest of information press any other key ");
            y = userInput.next();
        } while (y.equals("y"));

        System.out.println("Insert Dealers (One per line)  : ");

        do {
            String toAdd;
            userInput.nextLine();
            toAdd = userInput.nextLine();
            newDrug.dealers.add(toAdd);
            System.out.println("To add another Dealer press y  , To continue inserting the rest of information press any other key ");
            y = userInput.next();
        } while (y.equals("y"));

        DrugsDB.add(newDrug);
        System.out.println("Drug added to the DB !!%nTo view its details press 1 or any other key to back to the main menue  : ");

        x = userInput.nextInt();
        if (x == 1) {
            newDrug.displayDrug();
        }

        Serialize(DrugsDB, "DrugsDB.ser");
    }

    public static void viewSupplier(ArrayList<Supplier> SuppliersDB, ArrayList<Drug> DrugsDB) {
        Scanner userInput = new Scanner(System.in);
        int supplierIndex;

        System.out.print("Insert supplier's name : ");
        String supName = userInput.nextLine();
        System.out.println();

        for (supplierIndex = 0; supplierIndex < DrugsDB.size(); supplierIndex++) //liner search O(N)  " slow " , implemented for simplicity regarding the DB size , but this could be much far optimized 
        {
            if (SuppliersDB.get(supplierIndex).name.equals(supName)) {
                SuppliersDB.get(supplierIndex).displaySupplier();
                break;
            }
        }

        System.out.println("To Edit supplier's data press 1 ");
        System.out.println("To return to the main screen press any key");
        int x = userInput.nextInt();
        if (x == 1) {
            SuppliersDB.get(supplierIndex).editSupplier();

        }

        Serialize(SuppliersDB, "SuppliersDB.ser");

    }

    public static void addSupplier(ArrayList<Supplier> SuppliersDB) {

        Supplier newSupplier = new Supplier();
        System.out.print("insert Supplier's name :");

        Scanner userInput = new Scanner(System.in);
        newSupplier.name = userInput.nextLine();

        System.out.print("insert Owend money to the supplier :");
        newSupplier.ownedMoney = userInput.nextDouble();

        System.out.print("insert supplier mail:");
        newSupplier.email = userInput.next();

        System.out.print("insert mobile number:");
        newSupplier.mobileNumber = userInput.nextInt();

        System.out.print("insert type ( store or a company) number:");
        newSupplier.type = userInput.next();

        System.out.print("insert mobile number:");
        newSupplier.mobileNumber = userInput.nextInt();

        System.out.print("insert day to pay debt :");
        newSupplier.payingDebtDate.setDate();

        System.out.print("Supplier added successfully !! , to view its information press 1%nTo edit its information press 2 %nTo return to the main menue press any other key :");
        int choice = userInput.nextInt();

        if (choice == 1) {
            newSupplier.displaySupplier();
        }
        if (choice == 2) {
            newSupplier.editSupplier();
        }
        SuppliersDB.add(newSupplier);

        Serialize(SuppliersDB, "SuppliersDB.ser");

    }

    public static void viewSalesList(ArrayList<Bill> SalesDB) {

        Scanner userInput = new Scanner(System.in);
        int choice2;
        Date date = new Date();
        double dayProfit = 0, totalSalesValue = 0;

        System.out.print("Insert the day   : ");
        date.day = userInput.nextInt();
        System.out.print("Insert the month : ");
        date.month = userInput.nextInt();
        System.out.print("Insert the year  : ");
        date.year = userInput.nextInt();

        System.out.println("The bills in " + date.day + "/" + date.month + "/" + date.year + " are the following ");

        for (int i = 0; i < SalesDB.size(); i++) {

            if (SalesDB.get(i).date.compareDate(date)) {

                System.out.format("Customer name :%s%n", SalesDB.get(i).customerName);
                System.out.format("Bill number :%d%n", SalesDB.get(i).billID);
                System.out.format("Bill total price :%f%n", SalesDB.get(i).totalPrice);
                System.out.format("Money paid  :%f%n", SalesDB.get(i).moneyPaid);
                System.out.format("Debet :%f%n", SalesDB.get(i).debt);

                dayProfit += SalesDB.get(i).getProfitPerBill();
                totalSalesValue += SalesDB.get(i).totalPrice; //total price of the bill ....calculating the total salesVAlue in this day 

                System.out.println("To edit this bill press 2");
                choice2 = userInput.nextInt();

                if (choice2 == 2) {
                    SalesDB.get(i).editBill();

                    System.out.println("You edited Bill successfully !!");

                }

            }

        }

        System.out.format("%n%nTotal sales value in this day:%f%n", totalSalesValue);
        System.out.format("Total profit in this day  :%f%n", dayProfit);

        Serialize(SalesDB, "SalesDB.ser");

    }

    public static void addBillToSalesList(ArrayList<Bill> SalesDB) {
        Bill newBill = new Bill();
        System.out.print("insert Bill date: ");
        Scanner userInput = new Scanner(System.in);
        newBill.date.setDate();

        System.out.print("Insert Customer name: ");
        newBill.customerName = userInput.nextLine();

        System.out.print("Insert Bill id: ");
        newBill.billID = userInput.nextInt();

        System.out.print("Insert tottal price : ");
        newBill.totalPrice = userInput.nextDouble();

        System.out.print("Insert Money paid : ");
        newBill.moneyPaid = userInput.nextDouble();

        System.out.print("Insert debt: ");
        newBill.debt = userInput.nextDouble();

        System.out.print("Bill added !!\nTo view its information press 1\nTo edit information press 2\nTo back to the main menue press any other key :");
        int choice = userInput.nextInt();

        if (choice == 1) {
            newBill.displayBill();
        }
        if (choice == 2) {
            newBill.editBill();
        }

        SalesDB.add(newBill);

        Serialize(SalesDB, "SalesDB.ser");

    }

    public static void viewToBuyList(ArrayList<Drug> ToBuyListDB) {
        Scanner userInput = new Scanner(System.in);
        int choice3;
        double x = 0;

        System.out.print("Name           Quantity            Price%n");
        for (int i = 0; i < ToBuyListDB.size(); i++) {
            x += ToBuyListDB.get(i).totalBuyingPrice;
            System.out.print(ToBuyListDB.get(i).name + "           " + ToBuyListDB.get(i).minimumQuantity + "          " + ToBuyListDB.get(i).totalBuyingPrice + "%n");
        }

        System.out.println("Total cost of Buying drugs in this list : " + x + "%n%n");

        System.out.println("To add a drug to the list press 1 :");
        System.out.println("To edit list press 2 :");
        System.out.println("To remove a drug from the list press 3 :");
        System.out.println("To back to the main menue press any key : ");

        choice3 = userInput.nextInt();

        if (choice3 == 1) {           //user choosed to add a drug to the list 
            System.out.print("Enter the name of the drug : ");
            Drug toAdd = new Drug();
            toAdd.name = userInput.nextLine();
            System.out.print("%nEnter the quantity of the drug : ");
            toAdd.minimumQuantity = userInput.nextInt();
            System.out.print("%nEnter the price of the Drug : ");
            toAdd.totalBuyingPrice = userInput.nextDouble();

            ToBuyListDB.add(toAdd);

            System.out.print("%nDrug added successfully !!%n");

        }

        if (choice3 == 2) { //user choosed to edit the list 
            System.out.print("Enter the name of the drug : ");
            Drug toEdit = new Drug();
            int index = 0;
            String name = new String();
            name = userInput.nextLine();

            for (int i = 0; i < ToBuyListDB.size(); i++) {  //linear search O(N)  can be obtimized to O(log N) 
                if (name.equals(ToBuyListDB.get(i).name)) {
                    toEdit = ToBuyListDB.get(i);
                    index = i;
                    break;
                }
            }

            System.out.print("To edit  the name of the drug press 1 : ");
            System.out.print("To edit  the quantitiy of the drug press 2 : ");
            System.out.print("To edit the buying price of the drug press 3 : ");

            choice3 = userInput.nextInt();    //warning !! this may cause a problem as i used the same varible to control the flow of two diffrent nested scenarios 

            if (choice3 == 1) {   //A nested choice inside the edit procedure to edit the name of a specified drug                   
                System.out.print("Enter the new name : ");
                ToBuyListDB.get(index).name = userInput.nextLine();
                System.out.print("%Nname edited successfully !! ");
            }

            if (choice3 == 2) {  //A nested choice inside the edit procedure to edit the quantity of a specified drug            
                System.out.print("Enter the new quantity : ");
                ToBuyListDB.get(index).minimumQuantity = userInput.nextInt();
                System.out.print("%nQuantity  edited successfully !! ");
            }

            if (choice3 == 3) { // ~~~ to edit the price ~~~                     
                System.out.print("Enter the new price : ");
                ToBuyListDB.get(index).totalBuyingPrice = userInput.nextDouble();
                System.out.print("%nPrice edited successfully !! ");
            }

        }

        if (choice3 == 3) { //this shall not confilic with the previous "nested choices" as the condition of the outer choice with value 2 is never met !! 
            //so I THINK it is absolutely safe to use tha same varibale to control outer and inner flows of the proceduer.
            System.out.println("Enter the name of the Drug you want to delete : ");
            String toBeDeletedDrug;
            toBeDeletedDrug = userInput.nextLine();

            for (int i = 0; i < ToBuyListDB.size(); i++) {  //linear search O(N)  can be obtimized to O(log N) 
                if (toBeDeletedDrug.equals(ToBuyListDB.get(i).name)) {
                    ToBuyListDB.remove(i);
                }
            }
        }

        Serialize(ToBuyListDB, "ToBuyListDB.ser");

    }

    public static void addDrugToBuyList(ArrayList<Drug> ToBuyListDB) {

        System.out.print("Enter the name of the drug : ");
        Drug toAdd = new Drug();
        Scanner userInput = new Scanner(System.in);

        toAdd.name = userInput.nextLine();
        System.out.print("%nEnter the quantity of the drug : ");
        toAdd.minimumQuantity = userInput.nextInt();
        System.out.print("%nEnter the price of the Drug : ");
        toAdd.totalBuyingPrice = userInput.nextDouble();

        System.out.println("Drug added Successfuly !!\n To view its information press 1\nTo edit its information press2\nTo back to the main menue press any other key ");
        int choice = userInput.nextInt();
        if (choice == 1) {
            toAdd.displayDrug();
        }
        if (choice == 2) {
            toAdd.editDrug();
        }

        ToBuyListDB.add(toAdd);

        Serialize(ToBuyListDB, "ToBuyListDB.ser");

    }

    public static void viewCustomer(ArrayList<Customer> CustomerDB) {

        System.out.println("Enter the name of the Customer : ");
        String stringInput;
        int localChoice;
        int index = 0;             //this is the index of the customer choosen by the user 
        Scanner userInput = new Scanner(System.in);
        stringInput = userInput.nextLine();

        for (int i = 0; i < CustomerDB.size(); i++) {  //linear search O(N)  can be obtimized to O(log N) 
            if (stringInput.equals(CustomerDB.get(i).name)) {                   //searching in the DB using name for the requested customer. 
                CustomerDB.get(i).viewCustomer();                              // display the information of the customer if found 
                index = i;
            }
        }
        do {
            System.out.println("To view the details of a certian bill press 1");
            System.out.println("To edit customer's information press 2");
            System.out.println("To back to the main menue press any other key ");

            localChoice = userInput.nextInt();  //this is to contorl the flow inside this method can't use the golbal choice variable as it may cause conflicts.

            if (localChoice == 1) {
                System.out.println("Enter the number of the bill :");

                int Number = userInput.nextInt();

                for (int i = 0; i < CustomerDB.get(index).bills.size(); i++) { // the number of bills of the choosen customer ( not searching in all bills ). 
                    if (Number == CustomerDB.get(index).bills.get(i).billID) //comparing the wanted bill number with the numbers of bills of the choosen customer 
                    {
                        CustomerDB.get(index).bills.get(i).displayBill();
                        System.out.print("To edit bill's details press 2....or press any other key to continue:");
                        int c = userInput.nextInt();
                        if (c == 2) {
                            CustomerDB.get(index).bills.get(i).editBill();
                        }

                        break;
                    }

                }
            }

            if (localChoice == 2) {
                CustomerDB.get(index).editCustomer();
            }

        } while (localChoice == 1 || localChoice == 2);

        Serialize(CustomerDB, "CustomerDB.ser");

    }

    public static void addCustomer(ArrayList<Customer> CustomerDB) {

        Customer toAdd = new Customer();

        System.out.println("Insert customer name :");
        Scanner userInput = new Scanner(System.in);
        toAdd.name = userInput.nextLine();

        System.out.println("Insert customer ID :");
        toAdd.customerID = userInput.nextInt();

        System.out.println("Insert customer debt :");
        toAdd.totalDebt = userInput.nextDouble();

        System.out.println("Insert number of  Bills you want to add  : ");

        int choice = userInput.nextInt();

        for (int i = 0; i < choice; i++) {

            Bill newBill = new Bill();

            System.out.println("Insert Bill ID : ");
            newBill.billID = userInput.nextInt();

            System.out.println("Insert Bill date : ");
            newBill.date.setDate();

            System.out.println("Insert debt of this bill  : ");
            newBill.debt = userInput.nextDouble();

            System.out.println("Insert money paid for this bill : ");
            newBill.moneyPaid = userInput.nextDouble();

            System.out.println("Insert total price of this bill: ");
            newBill.totalPrice = userInput.nextDouble();

            toAdd.bills.add(newBill);

        }

        System.out.println("Customer added successfully !!\nTo edit press 1 ,\n To view press 2\npress any ther key to back to the main menue");
        choice = userInput.nextInt();

        if (choice == 1) {
            toAdd.editCustomer();
        }
        if (choice == 2) {
            toAdd.viewCustomer();
        }

        CustomerDB.add(toAdd);

        Serialize(CustomerDB, "CustomerDB.ser");

    }

    public static void sellDrug(ArrayList<Customer> CustomerDB, ArrayList<Bill> SalesDB, ArrayList<Drug> DrugsDB) {
        Bill sellingBill = new Bill();
        Drug toSell = new Drug();
        String s;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter the date of the Bill: ");
        sellingBill.date.setDate();
        do {
            System.out.println("Enter the name of the drug:");

            toSell.name = userInput.nextLine();

            System.out.println("Enter the barcode of the drug:");   //used bar code in acual operations as dealing with numbers in searching and inputting has less errors         
            toSell.barCode = userInput.nextInt();

            System.out.println("Enter the quantity of the drug : ");
            toSell.maximumQuantity = userInput.nextInt();

            for (Drug DrugsDB2 : DrugsDB) {

                if (toSell.barCode == DrugsDB2.barCode && toSell.maximumQuantity <= DrugsDB2.availableQuantity) // check if the drug is available with the required quantity 
                {
                    sellingBill.addDrug(DrugsDB2, toSell.maximumQuantity);  // adding DrugsDB.get(i) and not toSell as the later contains the price . 
                    DrugsDB2.availableQuantity -= toSell.maximumQuantity;  //reducing the sold quantity  from the available quantity !!
                    System.out.println("Drug is available with the required quantity and added to the Bill successfully !!");
                }
            }

            System.out.print("Enter another drug ? press Y : ");
            s = userInput.next();

        } while (s.equals("y"));

        System.out.print("Enter money paid : ");
        sellingBill.moneyPaid = userInput.nextDouble();

        System.out.print("Enter Customer name:");
        sellingBill.customerName = userInput.nextLine();

        sellingBill.debt = sellingBill.totalPrice - sellingBill.moneyPaid;

        if (sellingBill.debt > 0) {  // i need to store this customer as he didn't pay all the money ,but make sure that it is not existed already in the database. 

            Customer newCustomer = new Customer();
            newCustomer.name = sellingBill.customerName;
            Boolean isExisted = false;
            for (Customer custom : CustomerDB) { // searching wheither this customer is already existed in the DB or not . 
                if (custom.name.equals(newCustomer.name)) {
                    isExisted = true;
                    newCustomer = custom;        //now newCustomer is referencing the custom object (both reference the same memory field ) . 
                }
            }
            if (isExisted == true) {
                newCustomer.bills.add(sellingBill);
                newCustomer.totalDebt += sellingBill.debt;
            } else {
                newCustomer.totalDebt += sellingBill.debt;
                System.out.print("Enter Customer's phone number:");
                newCustomer.phoneNumber = userInput.nextInt();
                newCustomer.bills.add(sellingBill);               //adding this bill to the custmer account on the data base . 
                CustomerDB.add(newCustomer);                      //adding this customer to the customer's Database             
            }

            SalesDB.add(sellingBill);
            sellingBill.displayBill();

        }

        Serialize(CustomerDB, "CustomerDB.ser");
        Serialize(SalesDB, "SalesDB.ser");

    }

    public static void editDrugs(ArrayList<Drug> DrugsDB) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.print("Insert the bar code of the Drug : ");

            int barcode = scan.nextInt();

            for (int i = 0; i < DrugsDB.size(); i++) {
                //System.out.print("toEdit.barcode = " + toEdit.barCode + "barcode= "+barcode);
                if (DrugsDB.get(i).barCode == barcode) {
                    DrugsDB.get(i).editDrug();
                }
            }
            System.out.print("\nnPress 1 to edit another durg\nnPress any other key to back to the main menu: ");

            choice = scan.nextInt();

        } while (1 == choice);

        Serialize(DrugsDB, "DrugsDB.ser");

    }

    public static void displayDB(ArrayList<Drug> DrugsDB) {

        for (Drug toView : DrugsDB) {
            toView.displayDrug();

            System.out.println("##########################################");
            System.out.println("##########################################");
        }
    }

    public static void main(String[] args) {

        ArrayList<Drug> DrugsDB = new ArrayList<Drug>();
        ArrayList<Customer> CustomerDB = new ArrayList<Customer>();
        ArrayList<Supplier> SuppliersDB = new ArrayList<Supplier>();
        ArrayList<Drug> ToBuyListDB = new ArrayList<Drug>();
        ArrayList<Bill> SalesDB = new ArrayList<Bill>();;

        //InitializeObjects(DrugsDB,CustomerDB,SuppliersDB,ToBuyListDB,SalesDB);

        /*Fetching data from files*/
        DrugsDB = Deserialize(DrugsDB, "DrugsDB.ser");
        CustomerDB = Deserialize(CustomerDB, "CustomerDB.ser");
        SuppliersDB = Deserialize(SuppliersDB, "SuppliersDB.ser");
        ToBuyListDB = Deserialize(ToBuyListDB, "ToBuyListDB.ser");
        SalesDB = Deserialize(SalesDB, "SalesDB.ser");

        Scanner userInput = new Scanner(System.in);

        System.out.println("DrugsDB size : " + DrugsDB.size());

        do {

            System.out.println("Welcome to Manaifa's pharmacy managment program,%nplease choose one of the following options or press Q to exit : ");

            System.out.println("To view a Drug prss 1");
            System.out.println("To view supplier's information prss 2");
            System.out.println("To view sales lists prss 3");
            System.out.println("To view To-buy list prss 4");
            System.out.println("To view a customer prss 5");
            System.out.println("To sell a Drug or make a bill prss 6");
            System.out.println("To add a new drug to the database press 7");
            System.out.println("To update a drug's information in the database press  8");
            System.out.println("To add a supplier press 9");
            System.out.println("To add a bill to the sales list Enter 10");
            System.out.println("To add a drug to the To-buy list Enter 11");
            System.out.println("To add a customer to the database Enter 12");
            System.out.println("To view all the drugs in the DB Enter  13");
            System.out.println("To quit the program enter 14");

            System.out.println("IMPORTANT NOTE !!\n Make sure inserting valid values for each choice as the program sometimes tend To CRASH\n in case of inserting a mismatched values (integers instead of characters and vice versa\n YOU HAVE BEEN WARNED !!");
            System.out.println("This bug will take alot of  redundant code to be solved, we promise it will be fixed in the coming releases.");
            do {

                System.out.print("userInput : ");
                choice = userInput.nextInt();

                if (choice == 14) {
                    return;
                }

                if (choice > 14 || choice < 0) {
                    System.out.println("Invalid input, please Try again with a valid input between 1 and 9 ");
                }

            } while (choice > 14 || choice < 0);                   //test this and make sure it run correctly for non intger values (if the the user input char ) 

            // loop if any other input "invalid inputs "                     
            switch (choice) {
                case 1:
                    viewDrug(DrugsDB);
                    DrugsDB = Deserialize(DrugsDB, "DrugsDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 2:
                    viewSupplier(SuppliersDB, DrugsDB);
                    DrugsDB = Deserialize(DrugsDB, "DrugsDB.ser");
                    SuppliersDB = Deserialize(SuppliersDB, "SuppliersDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 3:
                    viewSalesList(SalesDB);
                    SalesDB = Deserialize(SalesDB, "SalesDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 4:
                    viewToBuyList(ToBuyListDB);
                    ToBuyListDB = Deserialize(ToBuyListDB, "ToBuyListDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 5:
                    viewCustomer(CustomerDB);
                    CustomerDB = Deserialize(CustomerDB, "CustomerDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 6:
                    sellDrug(CustomerDB, SalesDB, DrugsDB);
                    DrugsDB = Deserialize(DrugsDB, "DrugsDB.ser");
                    CustomerDB = Deserialize(CustomerDB, "CustomerDB.ser");
                    SalesDB = Deserialize(SalesDB, "SalesDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 7:
                    addDrugToDB(DrugsDB);
                    DrugsDB = Deserialize(DrugsDB, "DrugsDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 8:
                    editDrugs(DrugsDB);
                    Serialize(DrugsDB, "DrugsDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 9:
                    addSupplier(SuppliersDB);
                    SuppliersDB = Deserialize(SuppliersDB, "SuppliersDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 10:
                    addBillToSalesList(SalesDB);
                    SalesDB = Deserialize(SalesDB, "SalesDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");

                    break;

                case 11:
                    addDrugToBuyList(ToBuyListDB);
                    ToBuyListDB = Deserialize(ToBuyListDB, "ToBuyListDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;
                case 12:
                    addCustomer(CustomerDB);
                    CustomerDB = Deserialize(CustomerDB, "CustomerDB.ser");
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 13:
                    displayDB(DrugsDB);
                    System.out.println("##########################################");
                    System.out.println("##########################################");
                    break;

                case 14:
                    Serialize(DrugsDB, "DrugsDB.ser");
                    Serialize(CustomerDB, "CustomerDB.ser");
                    Serialize(SuppliersDB, "SuppliersDB.ser");
                    Serialize(ToBuyListDB, "ToBuyListDB.ser");
                    Serialize(SalesDB, "SalesDB.ser");
                    System.exit(choice);
                    break;
            }

            Serialize(DrugsDB, "DrugsDB.ser");
            Serialize(CustomerDB, "CustomerDB.ser");
            Serialize(SuppliersDB, "SuppliersDB.ser");
            Serialize(ToBuyListDB, "ToBuyListDB.ser");
            Serialize(SalesDB, "SalesDB.ser");
        } while (choice != 14);

    }
}
