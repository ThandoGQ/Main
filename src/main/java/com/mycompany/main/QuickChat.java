/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.Scanner;

/**
 * QuickChat - handles the login step and the messaging menu.
 * Uses the existing Login and Message classes.
 *
 * @author Student
 */
public class QuickChat {

    /**
     * Logs the user in, then shows the QuickChat menu.
     * The Scanner is passed in (and NOT closed here) so Main stays in control of System.in.
     */
    public static void start(Scanner scanner, Login user) {

        // ---------- LOGIN ----------
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        String status = user.returnLoginStatus(enteredUsername, enteredPassword);
        System.out.println(status);

        if (!user.loginUser(enteredUsername, enteredPassword)) {
            return; // login failed, do not open QuickChat
        }

        // ---------- MENU ----------
        System.out.println("\nWelcome to QuickChat.");
        boolean run = true;

        while (run) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    sendMessages(scanner);
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    run = false;
                    System.out.println("Quitting...");
                    break;
                default:
                    System.out.println("Invalid option, please choose 1, 2 or 3.");
            }
        }
    }

    /** Option 1: ask how many messages, then capture, validate and send/disregard/store each one. */
    private static void sendMessages(Scanner scanner) {
        int count = readPositiveInt(scanner, "How many messages do you wish to enter? ");

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + count + " ---");

            System.out.print("Recipient cell number (e.g. +27718693002): ");
            String rec = scanner.nextLine().trim();

            System.out.print("Message: ");
            String txt = scanner.nextLine();

            Message msg = new Message(Message.generateId(), rec, txt);

            // Validate recipient number
            String cellResult = msg.checkRecipientCell();
            System.out.println(cellResult);
            if (cellResult.contains("incorrectly")) {
                i--;        // repeat this message
                continue;
            }

            // Validate message length (max 250 characters)
            String lengthResult = msg.checkMessageLength();
            System.out.println(lengthResult);
            if (lengthResult.contains("exceeds")) {
                i--;        // repeat this message
                continue;
            }

            // Send / Disregard / Store
            System.out.print("1) Send  2) Disregard  3) Store: ");
            String choice = scanner.nextLine().trim();
            System.out.println(msg.SentMessage(choice));

            if (choice.equals("1")) {
                System.out.println("Message ID:   " + msg.getMessageId());
                System.out.println("Message Hash: " + msg.createMessageHash());
                System.out.println("Recipient:    " + rec);
                System.out.println("Message:      " + txt);
            }
        }

        System.out.println("\nTotal messages sent: " + new Message("0", "0", "0").returnTotalMessagess());
    }

    /** Keeps asking until the user types a whole number greater than 0. */
    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n > 0) {
                    return n;
                }
            } catch (NumberFormatException ignored) {
                // fall through to the message below
            }
            System.out.println("Please enter a whole number greater than 0.");
        }
    }
}