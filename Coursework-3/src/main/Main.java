/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    String name;
    String surname;

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");
        Scanner input = new Scanner(System.in);                                                           //For input from the console
        ArrayList<User> user = new ArrayList<>();                                                         //Arraylist of objects of user
        int i = 4;                                                                                        //Count of users made
        BankAcc bankacc = null;
        double accbalance = 0;
        long accountno = 0;

        while (true) {
            System.out.println("1)Create Bank Account\n2)Create User\n3)Run Simulation\n4)List of users\n5)Retrieve Account balance\n6)Exit\nEnter your choice : ");     //Menu
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Enter the account number of the bank account you want to create - ");
                accountno = input.nextLong();
                System.out.println("Enter the account balance of the bank account you created - ");
                accbalance = input.nextDouble();
                bankacc = new BankAcc(accountno, accbalance);                                                  //Instance of class Bankacc with set account number and account balance
                System.out.println("The Bank account has been created - " + accountno + " with account balance of " + accbalance);
            }
            if (choice == 2) {
                if (bankacc == null) {
                    System.out.println("Please create a bank account with an account balance and account number to continue");
                } else {
                    ArrayList<Double> arList = new ArrayList<>();                                                   //Temporary arraylist made initially to add transactions based on questions
                    Collections.addAll(arList, 50.0, 10.0, -20.0, 10.0, -20.0, 20.0, 10.0, 50.0, -10.0, 10.0, -10.0, 50.0);     //Adding elements to the Arraylist
                    ArrayList<Double> arList1 = new ArrayList<>();
                    Collections.addAll(arList1, 20.0, 20.0, -20.0, 50.0, -20.0, 10.0, 50.0, 50.0, -20.0, 10.0, 10.0);
                    ArrayList<Double> arList2 = new ArrayList<>();
                    Collections.addAll(arList2, 50.0, 10.0, 10.0, -10.0, -10.0, 50.0, 20.0, -10.0, -20.0);
                    ArrayList<Double> arList3 = new ArrayList<>();
                    Collections.addAll(arList3, 50.0, 10.0, -20.0, 20.0, 10.0, -20.0);
                    user.add(new User("Saul", "Goodman", bankacc, arList));                                            //Adding objects to the arraylist of class User
                    user.add(new User("Walter", "White", bankacc, arList1));
                    user.add(new User("Jessie", "Pinkman", bankacc, arList2));
                    user.add(new User("Hank", "Schradar", bankacc, arList3));
                    System.out.println("The default users -" + user.get(0).getUserName() + ", " + user.get(1).getUserName() + ", " + user.get(2).getUserName() + ", " + user.get(3).getUserName() + " have been created, to create another user or users enter more otherwise type stop");
                    String userschoice = input.next();
                    if (userschoice.equals("more")) {
                        while (true) {
                            System.out.println("Enter your name - ");
                            String name = input.next();
                            System.out.println("Enter your surname - ");
                            String surname = input.next();
                            ArrayList<Double> TL = new ArrayList<>();                                                                  //temp Arraylist to be filled by user
                            while (true) {
                                System.out.println("Enter the amount depicted by - or + before the amount to withdraw or deposit");
                                double amount = input.nextDouble();
                                System.out.println("Do you wish to continue adding transactions? Enter y for yes and n for no");
                                String ch = input.next();
                                if (ch.equals("n")) {
                                    break;
                                }
                                if (ch.equals("y")) {
                                    System.out.println("You have selected to do more transactions");
                                    TL.add(amount);
                                } else {
                                    System.out.println("You have entered an incorrect option and are exiting the program");
                                    break;
                                }
                            }
                            user.add(new User(name, surname, bankacc, TL));                                                            //Adding user defined object to Arrayllist of class User
                            i++;                                                                                                    //Keeping count of users made
                            System.out.println("Do you wish to continue adding users? Enter y for yes and n for no");
                            String ch1 = input.next();
                            if (ch1.equals("n")) {
                                System.out.println("You have selected to stop adding users. Please run the simulation after this process");
                                break;
                            }
                            if (ch1.equals("y")) {
                                System.out.println("You have selected to add more users");
                            } else {
                                System.out.println("You have selected an incorrect/invalid option so the program is assuming you want to stop entering users");
                                break;
                            }
                        }
                    } else if (userschoice.equals("stop")) {
                        System.out.println("You have chosen to not enter any more custom users. Please run simulation to view the transactins of the defaukt users");
                    }
                }
            }
            if (choice == 3) {
                if(user.isEmpty()){
                    System.out.println("Create users first to go ahead with the simulation");
                }
                else{
                if (bankacc == null) {
                    System.out.println("Please create a bank account with an account balance and account number to continue");
                } else {
                    for (int j = 0; j < i; j++) {
                        user.get(j).start();                                                                                    //starting all threads
                    }
                }
                }
            }
            if (choice == 4) {
                if(user.isEmpty()){
                    System.out.println("Create users first to see the list");
                }
                else{
                    System.out.println("-------------------------------  LIST OF USERS  --------------------------");
                    for (int j = 0; j < i; j++) {
                        System.out.println((j + 1) + ") " + user.get(j).getUserName());                                               //getting name from class user and printing                                 //starting all threads
                    }
                    System.out.println("---------------------------------------------------------------------------");
                }
            }
            if (choice == 5) {
                if (bankacc == null) {
                    System.out.println("Please create a bank account with an account balance and account number to continue");
                } else {
                    System.out.println("The account balance after withdrawing/depositing is - " + bankacc.getAccountBalance());   //getting account balance from class BankAcc
                }
            }
            if (choice == 6) {
                System.exit(0);                                                                                             //exiting the program
            }
        }
    }

}
