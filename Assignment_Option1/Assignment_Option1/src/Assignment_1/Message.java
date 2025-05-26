/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment_1;

import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;


public class Message  {
    
    
    
    private static int totalMessages = 0;
    private static final ArrayList<String> sentMessages = new ArrayList<>();
    private String messageID;
    private String recipient;
    private String message ;
    
    
    public Message(String messageID, String recipient, String message, String sender, String timestamp) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
    }
    
    
    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
    public int checkRecipientCell() {
        return (recipient.length() <= 10 && recipient.matches("\\+?[0-9]+")) ? 1 : 0;
    }
    
    public String createMessageHash() {
        return messageID.substring(0, 2) + ":" + message.length() + ":" + message.split(" ")[0] + message.split(" ")[message.split(" ").length - 1];
    }
    
    public String sentMessage() {
        
    while (true) { 
        String optionPicker = JOptionPane.showInputDialog(null, "Welcome to QuickChat\n" + "1. Send Messages\n" + "2. Show Recently Sent Messages\n" + "3. Quit","QuickChat Menu",JOptionPane.QUESTION_MESSAGE );

        if (optionPicker == null) return "Exit"; 

        switch (optionPicker) {
            case "1": // Send Messages
                try {
                    int messageCount = Integer.parseInt(JOptionPane.showInputDialog(
                        null, "Enter number of messages to send:",JOptionPane.QUESTION_MESSAGE));
                    
                    for (int i = 0; i < messageCount; i++) {
                        
                        String content = JOptionPane.showInputDialog(null, "Enter message content for message " + (i+1) + ":" , JOptionPane.QUESTION_MESSAGE);
         
                        String sender = "User";
                        String messageID = String.valueOf(System.currentTimeMillis());
                        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

              
                        Message currentMessage = new Message(messageID,Register_Pane.phonenumber, content, sender, timestamp);

                  
                        String choice = JOptionPane.showInputDialog(null,
                            "Message Options for message " + (i+1) + ":\n" + "0. Send Message\n" +"1. Disregard Message\n" + "2. Store Message", JOptionPane.QUESTION_MESSAGE);

                        switch (choice) {
                            case "0": 
                                String details = "Message Sent Successfully!\n" +
                                    "Message ID: " + currentMessage.messageID + "\n" +
                                    "Message Hash: " + currentMessage.createMessageHash() + "\n" +
                                    "RecipientID: " + currentMessage.recipient + "\n" +
                                    "Content: " + currentMessage.message;
                                
                                JOptionPane.showMessageDialog(null, details);
                                sentMessages.add(details);
                                totalMessages++;
                                break;
                            
                            case "1": 
                                JOptionPane.showMessageDialog(null, "Message disregarded", "Disregard",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            
                            case "2": 
                                currentMessage.storeMessage();
                                JOptionPane.showMessageDialog(null, "Message stored successfully!", "Store",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid choice - message disregarded","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number input!","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "2": 
                 JOptionPane.showMessageDialog(null, "Coming Soon","Under Construction",JOptionPane.INFORMATION_MESSAGE);
                break;

            case "3":
                return "Exit";

            default:
                JOptionPane.showMessageDialog(null, "Invalid option selected!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    public String printMessages() {
        return String.join("\n", sentMessages);
    }
    
    public int returnTotalMessages() {
        return totalMessages;
    }
    
    public void storeMessage() {
        JSONArray messagesArray = new JSONArray();
        JSONObject messageObject = new JSONObject();
        
        messageObject.put("MessageID", messageID);
        messageObject.put("MessageHash", createMessageHash());
        messageObject.put("Recipient", recipient);
        messageObject.put("Message", message);
        messagesArray.add(messageObject);
        
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(messagesArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Message ID: " + messageID + "\n" + "Message Hash: " + createMessageHash() + "\n" + "Recipient: " + recipient + "\n" +"Message: " + message;
    }
}
