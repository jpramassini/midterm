/*
*MIDTERM ASSIGNMENT
*Written by JP Ramassini
*Last Edited on 10/9/2017
*ISTE 120 - 04
*Professor Chisholm
*/

import java.util.Scanner;

public class midterm {

    static int dayStart = 0;

    public static void main (String args []){

        int dayCount = 0;
        String dayInput = null;
        boolean valid = false;

        String dayString = null;

        int results [][][] = new int [5][2][3];             //initializing main 3D array with fields for days, morning/afternoon, and reading number

        Scanner reader = new Scanner(System.in);

                                                            //while loop to check for valid day input initially
        while(!valid) {
            try {
                System.out.println("Which day would you like to start entry on? (1 = Sunday, 2 = Monday, etc.)");
                dayInput = reader.nextLine();
                dayStart = Integer.parseInt(dayInput);
                if (dayStart > 0 && dayStart < 8) {
                    dayCount = dayStart;
                    valid = true;
                } else {
                    System.out.println("\nPlease enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                reader.nextLine();
            }
        }

        /*
        *The following three for loops iterate through the main array and load it with values,
        *which are received from the user. Along the way, each loop sets the global variable
        *for its matching final output counterpart. I.e. The outer loop sets the dayString var
        *and the middle loop sets the morningOrAfternoon variable. By setting these all now,
        *it makes output much easier down the line and makes the input process much more user
        *friendly.
        */
        for(int i = 0; i < 5; i++) {

            switch (dayCount) {

                case 1: case 8:
                    dayString = "Sunday";
                    break;

                case 2: case 9:
                    dayString = "Monday";
                    break;

                case 3: case 10:
                    dayString = "Tuesday";
                    break;

                case 4: case 11:
                    dayString = "Wednesday";
                    break;

                case 5: case 12:
                    dayString = "Thursday";
                    break;

                case 6: case 13:
                    dayString = "Friday";
                    break;

                case 7: case 14:
                    dayString = "Saturday";
                    break;

            }
            dayCount++;
            //System.out.println(dayString + ": ");         This was a debug statement
            for (int j = 0; j < results[i].length; j++) {
                String morningOrAfternoon = null;
                //System.out.println(j);                    This was a debug statement

                switch (j) {

                    case 0: morningOrAfternoon = "morning";
                        break;

                    case 1: morningOrAfternoon = "afternoon";
                        break;

                }
                for (int k = 0; k < results[i][j].length; k++) {
                    boolean ok = false;
                    String tempInput = null;
                    int temp;

                    while(!ok) {
                        try {
                            System.out.println("Please enter the temperature reading " + (k + 1) + " in degrees F for " + dayString + " " + morningOrAfternoon + " (Between -30 and 120): ");
                            tempInput = reader.nextLine();
                            temp = Integer.parseInt(tempInput);
                            if(temp <= 120 && temp >= -30){
                                results[i][j][k] = temp;
                                ok = true;
                            }
                            else{
                                System.out.println("Please enter a temperature that is between -30 and 120 degrees F.");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Please enter an integer.");
                        }
                    }
                }
            }
        }   //end of "loading for loops"
        int celsiusArray [][][] = celsiusConverter(results);
        tempCalc(results, celsiusArray);
    }   //end of main
    /*
    *****************************************************************************************************
    * This method makes a copy of an array that has converted values - this allows celsius data to be more
    * easily passed to other methods which take 3D arrays as args
    *****************************************************************************************************
    */
    public static int [][][] celsiusConverter(int [][][]fahrenheitArray){
        int [][][] celsiusArray = new int[5][2][3];
        for(int i = 0; i < celsiusArray.length; i++){
            for(int j = 0; j < celsiusArray[i].length; j++){
                for(int k = 0; k < celsiusArray[i][j].length; k++){
                    celsiusArray[i][j][k] = fahrenheitArray[i][j][k];
                    celsiusArray[i][j][k] = (int) Math.round(((celsiusArray[i][j][k] - 32) * 0.5556));
                }
            }
        }
        return celsiusArray;
    }

    /*
    *****************************************************************************************************
    * This method takes arguments of two 3D arrays (one for Fahrenheit and one for Celsius) and does
    * all of the necessary calculations for the requirements. This includes logging the highest and
    * lowest recorded temperatures for the period, recording morning and afternoon averages, and printing
    * both Fahrenheit and Celsius averages.
    *****************************************************************************************************
     */

    public static void tempCalc (int [][][] tempFArray, int [][][] tempCArray){

        int highestSingle = tempFArray[0][0][0];
        int lowestSingle = tempFArray[0][0][0];

        System.out.println(tempFArray[2][0][1] + " " + tempCArray[2][0][1]);

        //initializing extremes of avgs as first average that will be calculated below. All other
        //avgs will be compared to these.
        double highestAvg = (tempFArray[0][0][0] + tempFArray[0][0][1] + tempFArray[0][0][2]) / 3;
        double lowestAvg = (tempFArray[0][0][0] + tempFArray[0][0][1] + tempFArray[0][0][2]) / 3;

        int avgFSum = 0;
        int avgCSum = 0;

        double avgF = 0;
        double avgC = 0;
        double overallAvg = 0;

        for(int i = 0; i < tempFArray.length; i++){                 // the loop controlling both arrays can be handled with the same number as
                                                                    // they are the same size and dimensions
            String dayString = null;
            int dayCount = dayStart;

            switch (dayCount) {

                case 1: case 8:
                    dayString = "Sunday";
                    break;

                case 2: case 9:
                    dayString = "Monday";
                    break;

                case 3: case 10:
                    dayString = "Tuesday";
                    break;

                case 4: case 11:
                    dayString = "Wednesday";
                    break;

                case 5: case 12:
                    dayString = "Thursday";
                    break;

                case 6: case 13:
                    dayString = "Friday";
                    break;

                case 7: case 14:
                    dayString = "Saturday";
                    break;

            }
            dayCount++;


            System.out.println("Day: " + dayString);

            for(int j = 0; j < tempFArray[i].length; j++){
                //TODO Add morning and afternoon switching
                for(int k = 0; k < tempFArray[i][j].length; k++){
                    avgFSum = 0;
                    avgCSum = 0;
                    avgF = 0;
                    avgC = 0;
                    avgFSum += tempFArray[i][j][k];
                    avgCSum += tempCArray[i][j][k];
                    overallAvg += tempFArray[i][j][k];
                    if (highestSingle < tempFArray[i][j][k]) {
                        highestSingle = tempFArray[i][j][k];
                    }

                    if (lowestSingle < tempFArray[i][j][k]){
                        lowestSingle = tempFArray[i][j][k];
                    }
                }
                avgF = (int) Math.round(avgFSum / 3.0);
                if(avgF > highestAvg){
                    highestAvg = avgF;
                }
                if(avgF < lowestAvg){
                    lowestAvg = avgF;
                }

                avgC = (int) Math.round(avgCSum / 3.0);
                System.out.println(" Average - C: " + avgC + "°  Average - F: " + avgF + "°");

            }
        }
        System.out.println("Highest Temperature of the Period: " + highestSingle + " °F");
        System.out.println("Lowest Temperature of the Period: " + lowestSingle + " °F");
        System.out.println("\nHighest Daily Average: " + highestAvg + " °F");
        System.out.println("Lowest Daily Average: " + lowestAvg + " °F");
        System.out.println("Average Temperature: " + (overallAvg / 30) + " °F");
    }





}
