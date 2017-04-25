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
public class Date  implements java.io.Serializable{

    int day, month, year;

    public void setDate() {
        System.out.print("Enter Day :");
        Scanner userInput = new Scanner(System.in);
        day = userInput.nextInt();

        System.out.print("Enter month :");
        month = userInput.nextInt();

        System.out.print("Enter year :");
        year = userInput.nextInt();

    }

    public boolean compareDate(Date x) {

        return x.day == day && x.month == month && x.year == year;
    }

    public void displayDate() {
        //System.out.println();       in inner display methods try to only display information and leave sequance escaping for the calling position to handle
        System.out.format("%i/%i/%i \n", day, month, year);
    }

}
