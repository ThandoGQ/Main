/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Student
 */
public class Message {

    public String messageId;
    public String recipient;
    public String message;
    public String messageHash;

    public static int totalMessages = 0;
    public static ArrayList<Message> allSentMessages = new ArrayList<>();
    public static ArrayList<String> jsonStore = new ArrayList<>();

    public Message(String messageId, String recipient, String message) {
        this.messageId = messageId;
        this.recipient = recipient;
        this.message = message;
    }

    // Generates a random 10-digit message ID
    public static String generateId() {
        Random rand = new Random();
        long num = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(num);
    }

    // Recipient must be +27 followed by 9 digits, or 0 followed by 9 digits
    public String checkRecipientCell() {
        boolean valid = recipient.matches("^\\+27[0-9]{9}$") || recipient.matches("^0[0-9]{9}$");
        if (valid) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Message must be 250 characters or less
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    // Uses the stored hash if there is one, otherwise builds it from the current message count
    public String createMessageHash() {
        if (messageHash != null) {
            return messageHash;
        }
        return createMessageHash(totalMessages);
    }

    // Hash format: first two ID digits : message number : FIRSTWORD+LASTWORD (letters/digits only, uppercase)
    // e.g. 00:0:HITONIGHT
    public String createMessageHash(int number) {
        String firstTwo = messageId.substring(0, 2);
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        messageHash = firstTwo + ":" + number + ":" + (first + last).toUpperCase();
        return messageHash;
    }

    // 1 = Send, 2 = Disregard, 3 = Store
    public String SentMessage(String choice) {
        switch (choice) {
            case "1": // Send
                createMessageHash(totalMessages);
                allSentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case "2": // Disregard
                return "Press 0 to delete the message.";
            case "3": // Store
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice";
        }
    }

    public String printMessages() {
        if (allSentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : allSentMessages) {
            sb.append("MessageID: ").append(m.messageId).append("\n");
            sb.append("Message Hash: ").append(m.messageHash).append("\n");
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.message).append("\n\n");
        }
        return sb.toString();
    }

    public int returnTotalMessagess() {
        return totalMessages;
    }

    // Same method with the normal spelling
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Saves stored messages to storedMessages.json (written by hand, no library needed)
    public void storeMessage() {
        String obj = "{\"messageId\":\"" + esc(messageId) + "\","
                + "\"messageHash\":\"" + esc(createMessageHash()) + "\","
                + "\"recipient\":\"" + esc(recipient) + "\","
                + "\"message\":\"" + esc(message) + "\"}";
        jsonStore.add(obj);

        try (FileWriter file = new FileWriter("storedMessages.json")) {
            file.write("[" + String.join(",", jsonStore) + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escapes backslashes and quotes so the JSON stays valid
    private static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessageHash() {
        return messageHash;
    }
}