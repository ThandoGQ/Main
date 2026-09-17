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
public class LoginTest {
    
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(true, user.checkUserName()); 
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("Lebron", "James", "leJames", "B@sketba11!", "+27838968976");
        assertEquals(false, user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(true, user.checkPasswordComplexity()); 
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login user = new Login("Lebron", "James", "lbj_23", "password", "+27838968976");
        assertEquals(false, user.checkPasswordComplexity()); 
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(true, user.checkCellPhoneNumber()); 
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "08966555");
        assertEquals(false, user.checkCellPhoneNumber()); 
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(false, user.loginUser("lbj_23", "wrong"));
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(true, user.loginUser("lbj_23", "B@sketba11!")); 
    }
    
    @Test
    public void testLoginUsernameSuccessful(){
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(true, user.loginUser("lbj_23", "B@sketba11!"));
    }
    
     @Test
    public void testLoginUsernameUnsuccessful(){
        Login user = new Login("Lebron", "James", "lbj_23", "B@sketba11!", "+27838968976");
        assertEquals(false, user.loginUser("lbj23", "B@sketba11!"));
    }
}
   
    


