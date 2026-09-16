/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class Login {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String cellNumber;

    public Login(String firstName, String lastName, String username, String password, String cellNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // Boolean: checkUserName()
    // This method ensures that any username contains an underscore (_) and is no more than 5 chars
    public Boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Boolean: checkPasswordComplexity()
    public Boolean checkPasswordComplexity() {
        return password.length() >= 8
                && password.matches(".[A-Z].")
                && password.matches(".[0-9].")
                && password.matches(".[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    // Boolean: checkCellPhoneNumber()
    // REFERENCE: Regex for SA number with international code
   
    public Boolean checkCellPhoneNumber() {
        // Condition: contains international code (+27) and correct length
        // SA format: +27831234567 (12 chars) - allows +27 + 9 digits
        return cellNumber.matches("^\\+27[0-9]{9}$") && cellNumber.length() <= 13;
    }

    // String registerUser()
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "User registered successfully.";
    }

    // Boolean loginUser() - verifies login details match registration details
    public Boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // String returnLoginStatus - returns messaging for successful / failed login
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
