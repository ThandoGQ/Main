/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Student
 */
public class Message {
    public String messageId;
    public String recipient;
    public String message;
    public String messageHash;
    public static int totalMessages = 0;
    public static ArrayList<Message> allSentMessages = new ArrayList<>();
    public static ArrayList<JSONObject> jsonStore = new ArrayList<>();

    public Message(String messageId, String recipient, String message) {
        this.messageId = messageId;
        this.recipient = recipient;
        this.message = message;
    }

    public static String generateId() {
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(num);
    }

    // From table: Boolean checkMessageID() - ID not more than ten chars
    public Boolean checkMessageID() {
        return this.messageId.length() <= 10;
    }

    // Q9 EXACT STRINGS - Cell number correctly formatted
    public String checkRecipientCell() {
        boolean valid = recipient.matches("^\\+27[0-9]{9}$") || recipient.matches("^0[0-9]{9}$");
        if (valid) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Q9 EXACT STRINGS - Message not more than 250 chars
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    // FIXED to return 00:0:HITONIGHT - removes ? , !
    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        messageHash = firstTwo + ":" + totalMessages + ":" + (first + last).toUpperCase();
        return messageHash;
    }

    // Overloaded for JUnit to test 00:0:HITONIGHT
    public String createMessageHash(int messageNumber) {
        String firstTwo = messageId.substring(0, 2);
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        return firstTwo + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }

    // From table: String SentMessage() - EXACT STRINGS
    public String SentMessage(String choice) {
        switch (choice) {
            case "1": // Send Message
                allSentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case "2": // Disregard Message / Discard
                return "Press 0 to delete the message.";
            case "3": // Store Message
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice";
        }
    }

    public String printMessages() {
        if (allSentMessages.isEmpty()) return "No messages sent yet.";
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

    public void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("messageId", messageId);
        obj.put("messageHash", createMessageHash());
        obj.put("recipient", recipient);
        obj.put("message", message);
        jsonStore.add(obj);
        try (FileWriter file = new FileWriter("storedMessages.json")) {
            JSONArray array = new JSONArray();
            array.addAll(jsonStore);
            file.write(array.toJSONString());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
}
    

