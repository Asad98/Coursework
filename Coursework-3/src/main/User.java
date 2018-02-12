/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author asayy
 */
public class User extends Thread{
    private String name;                                                                                                    //Class attributes
    private String surname;
    private BankAcc bankAccount;
    private ArrayList<Double> transactionlist;
    public User(String n, String s, BankAcc bA, ArrayList<Double> tl)                                                       //Constructor for class User
    {
        this.name = n;
        this.surname = s;
        this.bankAccount = bA;
        this.transactionlist=tl;
    }
    public String getUserName(){
        return this.name+" "+this.surname;
    }
    @Override
    public void run()                                                                                                       //This is run when thread is started in the main
    {
        for (int i = 0; i < transactionlist.size(); i++) {
            if (transactionlist.get(i)<0){
                bankAccount.withdraw(transactionlist.get(i),this.name+" "+this.surname);
            }
            if(bankAccount.getAccountBalance()<=0){
                System.out.println("The account balance is"+bankAccount.getAccountBalance()+"To withdraw kindly deposit more");
                break;
            }
            else{
                bankAccount.deposit(transactionlist.get(i),this.name+" "+this.surname);
            }
        }
    }
}
