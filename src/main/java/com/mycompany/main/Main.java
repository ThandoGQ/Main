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
          Scanner scanner = new Scanner(System.in);

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
        System.out.println(user.getUsernameStatus());
        System.out.println(user.getPasswordStatus());
        System.out.println(user.getCellPhoneStatus());

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
    }
}
    

