package com.mycompany.registrationandloginfeature;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Student
 */
public class LoginClassTest {
    
    public LoginClassTest() {
    }
    LoginClass login = new LoginClass();
   
    @Test
    public void checkUserName() {
        // "kyl_1" should return true(contains underscore and is 5 characters)
        assertTrue(login.checkUserName("kyl_1"));
        
        //"kyle!!!!!!!" should return False ( is more than 5 characters)
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    

    @Test
    public void CheckPasswordComplexity() {
        //"Ch&&sec@ke99!" should return TRUE
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        
        //"password" should reyurn FALSE ( no capitals, numbers, or Special characters)
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void CheckCellPhoneNumber() {
        // +2783868976
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        
        //08966553
        assertFalse(login.checkCellPhoneNumber("08966553"));
        
    }

    @Test
    public void LoginUser() {
        String expectedSuccess ="Welcome back,it is great to see you again.";
        String expectedFailure ="Username or password incorrect,please try again.";
        // for succesful Login 
        assertEquals(expectedSuccess,login.returnLoginStatus(true));
        
        //for failed Login
        assertEquals(expectedFailure,login.returnLoginStatus(false));
    }

    @Test
    public void ReturnLoginStatus() {
      String expectedSuccess ="Welcome back,it is great to see you again.";
      String expectedFailure ="Username or password incorrect,please try again.";
      
     // the mothod return
      assertEquals(expectedSuccess,login.returnLoginStatus(true));
      assertEquals(expectedFailure,login.returnLoginStatus(false));
      
      
    }
    
}

