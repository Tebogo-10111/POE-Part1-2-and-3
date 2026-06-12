package com.mycompany.registrationandloginfeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;

public class RegistrationandLoginFeature {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ================= REGISTRATION =================
        System.out.println("___REGISTRATION___");

        System.out.print("Create Username: ");
        String user = input.nextLine();

        System.out.print("Create Password: ");
        String pass = input.nextLine();

        System.out.print("Enter Cell Phone (+27...): ");
        String cell = input.nextLine();

        // USERNAME CHECK
        boolean findUnder = false;

        for (int i = 0; i < user.length(); i++) {

            if (user.charAt(i) == '_') {
                findUnder = true;
            }
        }

        boolean checkUser = false;

        if (findUnder && user.length() <= 5) {
            checkUser = true;
        }

        // PASSWORD CHECK
        boolean checkPass = false;

        if (pass.length() >= 8) {

            boolean checkCap = false;
            boolean checkNum = false;
            boolean checkSpec = false;

            for (int i = 0; i < pass.length(); i++) {

                char ch = pass.charAt(i);

                if (Character.isUpperCase(ch)) {
                    checkCap = true;
                }

                if (Character.isDigit(ch)) {
                    checkNum = true;
                }

                if (!Character.isLetterOrDigit(ch)) {
                    checkSpec = true;
                }
            }

            if (checkCap && checkNum && checkSpec) {
                checkPass = true;
            }
        }

        // CELL CHECK
        boolean checkCell = false;

        String cellPattern = "^\\+27[0-9]{9}$";

        if (cell.matches(cellPattern)) {
            checkCell = true;
        }

        // ================= STATUS =================
        System.out.println("\n___STATUS___");

        if (checkUser) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username incorrectly formatted.");
        }

        if (checkPass) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password incorrectly formatted.");
        }

        if (checkCell) {
            System.out.println("Cell phone number successfully captured.");
        } else {
            System.out.println("Cell phone incorrectly formatted.");
        }

        // ================= LOGIN =================
        if (checkUser && checkPass && checkCell) {

            System.out.println("\nSUCCESS: Registration complete!");

            System.out.println("\n___LOGIN___");

            System.out.print("Enter username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter password: ");
            String loginPass = input.nextLine();

            // LOGIN VALIDATION
            if (loginUser.equals(user) && loginPass.equals(pass)) {

                System.out.println("\nLogin successful!");
                System.out.println("Welcome to QuickChat!");

                // OPEN MESSAGE SYSTEM
                Message msg = new Message();
                msg.SentMessage();

            } else {

                System.out.println("Username or password incorrect.");
            }
        }
    
    }
// THE START OF PART 2

 public static class Message {
    public int totalMessagesSent = 0;
    
    // Part 3: Array Declaration
    
    public String[] sentMessagesArray;
    public String[] disregardedMessagesArray;
    public String[] storedMessagesArray;
    public String[] messageHashArray;
    public String[] messageIdArray;
    
    // Track count for data Part 3
    
   private int sentCount = 0;
   private int disregardCount = 0;
   private int storedCount = 0;
   
   
 // ================= MESSAGE SYSTEM =================
    public void SentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.print("How many messages do you wish to enter? ");
        int totalMessages = input.nextInt();
        input.nextLine();
        
        // Part 3: Initialize Arrays
        
        sentMessagesArray = new String[totalMessages];
        disregardedMessagesArray = new String[totalMessages];
        storedMessagesArray = new String[totalMessages];
        messageHashArray = new String[totalMessages];
        messageIdArray = new String[totalMessages];

        for (int i = 0; i < totalMessages; i++) {

            System.out.println("\n--- Message Entry " + (i + 1) + " ---");

            // RECIPIENT
            System.out.print("Enter recipient cell number: ");
            String recipient = input.nextLine();

            String cellStatus = checkRecipientCell(recipient);
            System.out.println(cellStatus);

            // MESSAGE TEXT
            System.out.print("Enter message text: ");
            String messageText = input.nextLine();

            if (messageText.length() > 250) {

                System.out.println(
                        "Please enter a message of less than 250 characters.");

            } else {

                System.out.println("Message ready to send.");
            }

            // RANDOM MESSAGE ID
            Random rand = new Random();

            long messageID
                    = 1000000000L
                    + (long) (rand.nextDouble() * 9000000000L);

            // MESSAGE HASH
            String messageHash
                    = createMessageHash(
                            String.valueOf(messageID),
                            messageText,
                            i);

            // OPTIONS
            System.out.println("\nSend Message Options:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");
            System.out.print("Choose option: ");

            int subOption = input.nextInt();
            input.nextLine();

            switch (subOption) {

                case 1 -> {
                    System.out.println("Message successfully sent");

                    totalMessagesSent++;
                    
                    // Part 3: Populating sent Arrays
                    
                    sentMessagesArray[sentCount] = messageText;
                    sentCount++;
                    

                    System.out.println("\n=== MESSAGE DETAILS ===");

                    System.out.println("Message ID: " + messageID);
                    System.out.println("Message Hash: " + messageHash);
                    System.out.println("Recipient: " + recipient);
                    System.out.println("Message: " + messageText);

                    storeMessage(
                            String.valueOf(messageID),
                            messageHash,
                            recipient,
                            messageText);
                }

                case 2 -> {
                    System.out.println("Message disregarded.");
                    
                    // Part 3 Populating sent Arrays
                    disregardedMessagesArray[disregardCount] = messageText;
                    disregardCount++;
                }

                case 3 -> {
                    System.out.println("Message stored.");
                    
                    // Part 3 Populating sent Arrays
                    storedMessagesArray[storedCount] = "sender: System, Recipient: " + recipient + ", Message: " + messageText;
                    messageIdArray[storedCount] = String.valueOf(messageID);
                    messageHashArray[storedCount] = messageHash;
                    storedCount++;
                    
                    storeMessage(
                            String.valueOf(messageID),
                            messageHash,
                            recipient,
                            messageText);
                }

                default -> System.out.println("Invalid selection.");
            }
        }

        System.out.println(
                "\nTotal messages sent: "
                + returnTotalMessages());
        
        // Part 3 Triggering the new main menu option
        displayStoredMessagesMenu(input);
    }
    
    // Part 3 MENU A to F
    
    public void displayStoredMessagesMenu(Scanner input) {
        boolean exitMenu = false;
       
      while (!exitMenu) {
          System.out.println("\n==== 4) STORED MESSAGES MENU ====");
          System.out.println("a) Display sender and recipient of all stored messages");
          System.out.println("b) Display the longest stored message");
          System.out.println("c) Search for a message ID");
          System.out.println("d) Search all messages for a particular recipient");
          System.out.println("e) Delete a message using message using message hash");
          System.out.println("f) Display full report of all stored mrssages");
          System.out.println("g) Exit");
          System.out.println("Select an option (a-g): ");
          
          String selection = input.nextLine().toLowerCase();
          
          switch (selection) {
              
              case "a" -> {
                  System.out.println("\n--- Stored Senders & Recipients ---");
                  for (int i = 0; i < storedCount; i++) {
                      if (storedMessagesArray[i] != null) {
                          System.out.println(storedMessagesArray[i]);
                      }
                  }
                }
                  
              case "b" -> {
                  System.out.println("\n--- Longest Stored Message ---");
                  String longest = "";
                  for (int i = 0; i < storedCount; i++) {
                      if (storedMessagesArray[i] != null && storedMessagesArray[i].length() > longest.length()) {
                          longest = storedMessagesArray[i];
                          
                          
                      }
                  }
                  System.out.println(longest.isEmpty() ? "No stored messages found." : longest);
                }
                  
              case "c" -> {
                  System.out.print("Enter Message ID to search: ");
                  String searchId = input.nextLine();
                  boolean idFound = false;
                  for (int i = 0; i < storedCount; i++){
                      if (messageIdArray[i] != null && messageIdArray[i].equals(searchId)){
                          System.out.println("Found! Details: " + storedMessagesArray[i]);
                          idFound = true;
                          break;
                      }
                  }
                  if (!idFound) System.out.println("Message ID found.");
                }
                  
              case "d" -> {
                  System.out.print("Enter recipient cell to search: ");
                  String searchRecipient = input.nextLine();
                  boolean recipientFound = false;
                  for (int i = 0; i < storedCount; i++) {
                      if (storedMessagesArray[i] != null && storedMessagesArray[i].contains(searchRecipient)) {
                          System.out.println("- " + storedMessagesArray[i]);
                          recipientFound = true;
                      }
                  }
                  if (!recipientFound) System.out.println("No message found for this recipient.");
                }
                  
              case "e" -> {
                  System.out.print("Enter Message Hash to delete: ");
                  String targetHash = input.nextLine();
                  boolean hashFound = false;
                  for (int i = 0; i < storedCount; i++) {
                      if (messageHashArray[i] != null && messageHashArray[i].equalsIgnoreCase(targetHash)) {
                          storedMessagesArray[i] = null;
                          messageIdArray[i] = null;
                          messageHashArray[i] = null;
                          System.out.println("Message successfully removed from working arrays.");
                          hashFound = true;
                          break;
                      }
                  }
                  if (!hashFound) System.out.println("message Hash code match failed.");
                }
                  
              case "f" -> {
                  System.out.println("\n===== STORED TASK REPORT =====");
                  for (int i = 0; i < storedCount; i++) {
                      if (messageIdArray[i] != null) {
                          System.out.println("ID: " + messageIdArray[i]);
                          System.out.println("Hash: " + messageHashArray[i]);
                          System.out.println("Details: " + storedMessagesArray[i]);
                          System.out.println("-----------------------------------");
                          
                      }
                  }
                }
                  
              case "g" -> {
                  exitMenu = true;
                  System.out.println("Exiting Stored Messages View.");
                }
                  
              default -> System.out.println("Invalid selection option.");
          }
      }
    }

    // ================= CHECK CELL =================
    public String checkRecipientCell(String recipient) {

        String cellPattern = "^(\\+27|0)[0-9]{9}$";

        if (recipient.matches(cellPattern)) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number incorrectly formatted.";
        }
    }

    // ================= MESSAGE HASH =================
    public String createMessageHash(
            String id,
            String text,
            int loopIndex) {

        String idPart = id.substring(0, 2);

        int firstSpace = text.indexOf(" ");
        int lastSpace = text.lastIndexOf(" ");

        String firstWord;
        String lastWord;

        if (firstSpace == -1) {

            firstWord = text;
            lastWord = text;

        } else {

            firstWord = text.substring(0, firstSpace);
            lastWord = text.substring(lastSpace + 1);
        }

        String hash
                = idPart + ":"
                + loopIndex + ":"
                + firstWord + lastWord;

        return hash.toUpperCase();
    }

    // ================= TOTAL =================
    public int returnTotalMessages() {

        return totalMessagesSent;
    }

    // ================= JSON STORAGE =================
    public void storeMessage(
            String mID,
            String mHash,
            String cell,
            String txt) {

        try {

            JSONObject msgData = new JSONObject();

            msgData.put("MessageID", mID);
            msgData.put("MessageHash", mHash);
            msgData.put("Recipient", cell);
            msgData.put("MessageText", txt);

            try (FileWriter file = new FileWriter("message.json", true)) {
                file.write(msgData.toString() + "\n");
            }

        } catch (IOException e) {
        
    }
            System.out.println("Error saving file");
        }
 }
}