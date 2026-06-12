/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Student
 */
public class message {
    

    public int totalMessagesSent = 0;
  
 
    



    // ================= MESSAGE SYSTEM =================
    public void SentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.print("How many messages do you wish to enter? ");
        int totalMessages = input.nextInt();
        input.nextLine();

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

                case 1:

                    System.out.println("Message successfully sent");

                    totalMessagesSent++;

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

                    break;

                case 2:

                    System.out.println("Message disregarded.");

                    break;

                case 3:

                    System.out.println("Message stored.");

                    storeMessage(
                            String.valueOf(messageID),
                            messageHash,
                            recipient,
                            messageText);

                    break;

                default:

                    System.out.println("Invalid selection.");
            }
        }

        System.out.println(
                "\nTotal messages sent: "
                + returnTotalMessages());
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

            FileWriter file
                    = new FileWriter("message.json", true);

            file.write(msgData.toString() + "\n");

            file.close();

        } catch (IOException e) {
        
    }
            System.out.println("Error saving file");
        }
 }


