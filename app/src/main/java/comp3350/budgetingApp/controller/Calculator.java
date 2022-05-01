package comp3350.budgetingApp.controller;

import android.widget.EditText;
import android.widget.Spinner;

public class Calculator {
     /** This method return a integer that is the total amount of mortgage**/
    public static double Total(String buytotal, String percent){
        double inputTotal;
        String text;

        inputTotal = (Double.parseDouble(buytotal) * Double.parseDouble(percent) * 0.01);

        return inputTotal;
    }

    /** This method take the information from EdiText and Spinner, and use a double of total mortgage amount as parameter**/
    /** It would return a string which would be displayed at the of calculator as the payment details.**/

    public static String displayDetail(EditText row4Edit, Spinner spinner1, double inputTotal){
        String Rate, length;
        /**this baseRate is the double formation of rate due to the unit of rate is %**/
        double baseRate;
        int year, month;
        double totalPayment, perTime, extra;
        Rate = row4Edit.getText().toString();
        baseRate = Double.parseDouble(Rate) * 0.01 ;
        length = spinner1.getSelectedItem().toString();
        /** Year is the int phase of length**/
        year = Integer.parseInt(length);
        month = year * 12;
        totalPayment = inputTotal * year * baseRate +inputTotal;
        perTime = totalPayment/month;
        /**pertime is the monthly payment amount**/

        

        extra = totalPayment - inputTotal;

        String text = "Your total mortgage amount is " + String.format("%.2f", inputTotal) + "k\nTotal payment is " + String.format("%.2f", totalPayment) + "k\nTotal interest you have to pay is " + String.format("%.2f", extra) + "k\nTotal period " + month + " months\nMonthly payment is " + String.format("%.2f", perTime * 1000) + "CDN";

        return text;
    }
};
