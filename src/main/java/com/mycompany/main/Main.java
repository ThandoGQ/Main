/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;

/**
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

        // Registration validation
        if (!user.checkUserName()) {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
        } else if (!user.checkPasswordComplexity()) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.");
        } else if (!user.checkCellPhoneNumber()) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        } else {
            System.out.println("User registered successfully.");
            // Login + QuickChat menu (reuses the same Scanner)
            QuickChat.start(input, user);
        }

        input.close();
    }
}