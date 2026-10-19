/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Main {

    public static void main(String[] args) {
          Scanner input = new Scanner(System.in);

       System.out.println("=== Create Account ===");
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();
        System.out.print("Enter Username (must contain _ and <=5 chars): ");
        String username = input.nextLine();
        System.out.print("Enter Password (8+ chars, capital, number, special char): ");
        String password = input.nextLine();
        System.out.print("Enter SA Cell Number (e.g. +27831234567): ");
        String cell = input.nextLine();

        Login user = new Login(firstName, lastName, username, password, cell);

        // Show validation messages
        //System.out.println(user.getUsernameStatus());
       // System.out.println(user.getPasswordStatus());
       //rrr System.out.println(user.getCellPhoneStatus());

        System.out.println(user.registerUser());

        // Part 2 - Login if registration valid
        if (user.checkUserName() && user.checkPasswordComplexity() && user.checkCellPhoneNumber()) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter Username: ");
            String loginU = input.nextLine();
            System.out.print("Enter Password: ");
            String loginP = input.nextLine();
            System.out.println(user.returnLoginStatus(loginU, loginP));
        }

        input.close();
        
         System.out.println("Welcome to QuickChat.");

        boolean run = true;
        while (run) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choose: "); String opt = scanner.nextLine();

            switch (opt) {
                case "1":
                    System.out.print("How many messages? "); int total = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < total; i++) {
                        System.out.print("Recipient: "); String rec = scanner.nextLine();
                        System.out.print("Message: "); String txt = scanner.nextLine();
                        Message msg = new Message(Message.generateId(), rec, txt);

                        System.out.println(msg.checkRecipientCell());
                        if (msg.checkRecipientCell().contains("incorrectly")) { i--; continue; }

                        System.out.println(msg.checkMessageLength());
                        if (msg.checkMessageLength().contains("exceeds")) { i--; continue; }

                        System.out.print("1) Send 2) Disregard 3) Store: "); String choice = scanner.nextLine();
                        System.out.println(msg.SentMessage(choice));
                        if (choice.equals("1")) System.out.println(msg.createMessageHash() + "\n" + msg.printMessages());
                    }
                    System.out.println("Total sent: " + new Message("0","0","0").returnTotalMessagess());
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    run = false;
                    System.out.println("Quitting...");
                    break;
            }
        }
        scanner.close();
    }
}
    

