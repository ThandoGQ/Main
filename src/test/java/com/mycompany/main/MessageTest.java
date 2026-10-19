/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
      @Test
    public void testMessageHash_Task1() {
        // Must return 00:0:HITONIGHT
        String id = "0012345678";
        Message msg = new Message(id, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash(0));
    }

    @Test
    public void testHashesInLoop() {
        String[] texts = {"Hi Mike, can you join us for dinner tonight?", "Hi Keegan, did you receive the payment?"};
        String[] expected = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};
        for (int i = 0; i < texts.length; i++) {
            Message m = new Message("00"+10000000, "+27718693002", texts[i]);
            assertEquals(expected[i], m.createMessageHash(i));
        }
    }

    @Test
    public void testMessageIDCreated() {
        String id = Message.generateId();
        Message m = new Message(id, "+27718693002", "test");
        System.out.println("Message ID generated: " + id);
        assertTrue(m.checkMessageID());
        assertEquals(10, id.length());
    }

    @Test
    public void testRecipientSuccess() {
        Message m = new Message(Message.generateId(), "+27718693002", "test");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure_Task2() {
        Message m = new Message(Message.generateId(), "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", m.checkRecipientCell());
    }

    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message(Message.generateId(), "+27718693002", "Hi Keegan, did you receive the payment?");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        Message m = new Message(Message.generateId(), "+27718693002", "a".repeat(260));
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.", m.checkMessageLength());
    }

    @Test
    public void testMessageSentAllOptions() {
        Message m = new Message(Message.generateId(), "+27718693002", "test");
        assertEquals("Message successfully sent.", m.SentMessage("1"));
        assertEquals("Press 0 to delete the message.", m.SentMessage("2"));
        assertEquals("Message successfully stored.", m.SentMessage("3"));
    }

    @Test
    public void testTask1Flow() {
        Message m1 = new Message(Message.generateId(), "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", m1.SentMessage("1"));
        System.out.println("Total: " + m1.returnTotalMessagess());
    }

    @Test
    public void testTask2Flow() {
        Message m2 = new Message(Message.generateId(), "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", m2.SentMessage("2"));
    }
}
    

