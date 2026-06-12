package com.mycompany.registrationandloginfeature;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class LoginClass {
    
    public void startLogin(String regUser, String regPass){
    
     Scanner input = new Scanner(System.in);
     
     System.out.println("\n___LOGIN TO YOUR ACCOUNT___");
     
     System.out.print("Enter Username:");
     String loginUser = input.nextLine();
     
     System.out.print("Enter Password:");
     String loginPass = input.nextLine();
     // loginUser to check the match
     boolean success = loginUser(regUser, regPass, loginUser, loginPass);
     System.out.println(returnLoginStatus(success));
    }
   // METHOD TO CHECK USERNAME
    public boolean checkUserName(String user) {
        boolean findUnder = false;
    for(int i = 0; i<user.length();i++){
        if(user.charAt(i) == '_'){
            findUnder = true;
    }   
 }
    return (findUnder && user.length()<=5); 
}  

//METHOD TO CHECK PASSWORD COMPLEXITY
    public boolean checkPasswordComplexity(String pass){
        if(pass.length()>=8){
       boolean checkCap = false;
       boolean checkNum = false;
       boolean checkSpec = false;
       
       for(int i = 0;i <pass.length();i++){
         char ch = pass.charAt(i);
        
         if(Character.isUpperCase(ch)){
             checkCap = true;
         }
         if(Character.isDigit(ch)){
             checkNum = true;
         }
         if(!Character.isLetter(ch) && !Character.isDigit(ch)){
             checkSpec = true;
         }
       }
       return(checkCap && checkNum && checkSpec);
        }
        return false;
    }
        
    
   //METHOD TO CHECK CELL PHONE
 public boolean checkCellPhoneNumber(String cell){
 return (cell.startsWith("+27")&& cell.length()==12);
 }
 
//  METHOD TO VERIFY LOGIN DETAILS MATCH
 public boolean loginUser(String user, String pass, String loginUser, String loginPass){
     return (user.equals(loginUser) && pass.equals(loginPass));
 }
 
// METHOD TO RETURN THE LOGIN MESSAGE
 public String returnLoginStatus(boolean success){
     if (success){
         return "Welcome back,it is great to see you again.";
       
     }else{
         return "Username or password incorrect,please try again.";
     }
 }
 
}


     
    
       
      
    
    
    
     
