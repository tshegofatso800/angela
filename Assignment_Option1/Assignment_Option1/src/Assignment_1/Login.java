 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment_1;

import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

//Khutso_Pollen_Masekwameng

public class Login {
    
    private static HashMap<String, String> userDatabase = new HashMap<>();
    
    
    
     private boolean loginStatus = false;
    
     
    public static boolean checkUserName(String username) throws IllegalArgumentException {
        String regex = "^(?=.*_)[a-zA-Z0-9_]{1,5}$";

        return username.matches(regex);
    }
    
    
    public static boolean checkPasswordComplexity(String password)  throws IllegalArgumentException{
    
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(regex, password);
    }
    
    public static boolean checkCellPhoneNumber(String phone) throws IllegalArgumentException{
        String phoneRegex =  "^\\+27[6-8][0-9]{8}$";
      return Pattern.matches(phoneRegex,phone);
    }
    

     public String registerUser(String username, String password, String phoneNumber)  throws IllegalArgumentException {
         
          if (Register_Pane.firstname.equals("")){
             String Fname = "Please enter your first name";
            JOptionPane.showMessageDialog(null, Fname, "Registration Error", JOptionPane.ERROR_MESSAGE);
            return Fname; 
          }
          
          
          if (Register_Pane.lastname.equals("")){
             String Lname = "Please enter your last name";
            JOptionPane.showMessageDialog(null, Lname, "Registration Error", JOptionPane.ERROR_MESSAGE);
            return Lname; 
          }
          
          
          
        if (!checkUserName(username)) {
              
            String message = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in lenght.";
            JOptionPane.showMessageDialog(null, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
            
            return message;
        }else {
              
            String messagee1 = "Username successfully captured.";
            JOptionPane.showMessageDialog(null, messagee1, "Registration ", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        
        if (!checkPasswordComplexity(password)) {
            
            String message = "Password is not correctly formatted; please ensure that the password contains at least eight characters,  a capital letter, a number, and  a special character.";
            JOptionPane.showMessageDialog(null, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
            
            return message;
        }else {
            
            String messagee1 = "Password successfully  captured.";
            JOptionPane.showMessageDialog(null, messagee1, "Registration ", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        
        if (!checkCellPhoneNumber(phoneNumber)) {
            
            String message = "Cell phone number incorrectly formatted or does not contain international code.";
            JOptionPane.showMessageDialog(null, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
            
            return message;
        }else {
            
            String messagee1 = "Cell phone number successfully added.";
            JOptionPane.showMessageDialog(null, messagee1, "Registration ", JOptionPane.INFORMATION_MESSAGE);
            
        }
        

        userDatabase.put(username, password);
        String successMessage = "Your Account Registration was Successful.";
        JOptionPane.showMessageDialog(null, successMessage, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
       
        return successMessage;
    }

     // Method to verify that the login details entered match the login details stored when the user register
    public boolean loginUser(String username, String password) {
        System.out.println("Attempting login for: " + username);
        System.out.println("Stored Password: " + userDatabase.get(username));
        System.out.println("Entered Password: " + password);

      return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    // Method to return login status message
    public String returnLoginStatus(boolean isSuccess) {
        String message = isSuccess ? 
        "Welcome " + Register_Pane.firstname + " " + Register_Pane.lastname + ", it is great to see you again." :
        "Username or password incorrect, please try again.";
    
        JOptionPane.showMessageDialog(null, message, "Login Status", isSuccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        
      return message;
    }
}
