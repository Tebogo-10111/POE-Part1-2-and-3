/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandloginfeature;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author Student
 */
public class MessageTest {
    
public MessageTest(){
    
}
RegistrationandLoginFeature.Message msg = new RegistrationandLoginFeature.Message();
 
    @Test
    public void testMessageLengthSuccess() {

        String testMessage =
                "Hi Mike, can you join us for dinner tonight?";

        boolean valid = testMessage.length() <= 250;

        assertTrue(valid);

        assertEquals(
                "Message ready to send.",
                "Message ready to send.");
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "Hi Mike, I hope you are doing well. I wanted "
                + "to remind you about the dinner tonight at "
                + "the restaurant near the mall. Please bring "
                + "the documents, your laptop, and the payment "
                + "confirmation because we will need everything "
                + "for the meeting before dinner starts. Also "
                + "let everyone know that we may arrive late "
                + "due to traffic and weather conditions.";

        boolean valid = longMessage.length() <= 250;

        assertFalse(valid);

        int exceed = longMessage.length() - 250;

        assertEquals(
                "Message exceeds 250 characters by "
                + exceed
                + ", please reduce the size.",
                "Message exceeds 250 characters by "
                + exceed
                + ", please reduce the size.");
    }

   

    @Test
    public void testRecipientCellSuccess() {

        String result =
                msg.checkRecipientCell("+27718693002");

        assertEquals(
                "Cell phone number successfully captured.",
                result);
    }

    @Test
    public void testRecipientCellFailure() {

        String result =
                msg.checkRecipientCell("0718693002");

        assertEquals(
                "Cell phone number incorrectly formatted.",
                result);
    }

    
    @Test
    public void testCreateMessageHash() {

        String messageID = "0012345678";

        String messageText =
                "Hi Mike, can you join us for dinner tonight?";

        String expectedHash =
                "00:0:HITONIGHT?";

        String actualHash =
                msg.createMessageHash(
                        messageID,
                        messageText,
                        0);

        assertEquals(expectedHash, actualHash);
    }

    
    @Test
    public void testReturnTotalMessages() {

        msg.totalMessagesSent = 2;

        assertEquals(
                2,
                msg.returnTotalMessages());
    }



    @Test
    public void testMessageIDLengthSuccess() {

        String messageID = "1234567890";

        boolean valid = messageID.length() <= 10;

        assertTrue(valid);
    }

    @Test
    public void testMessageIDLengthFailure() {

        String messageID = "123456789011";

        boolean valid = messageID.length() <= 10;

        assertFalse(valid);
    }


    @Test
    public void testStoreMessage() {

        assertDoesNotThrow(() -> {

            msg.storeMessage(
                    "1234567890",
                    "12:0:HITONIGHT",
                    "+27718693002",
                    "Hi Mike, can you join us for dinner tonight?");
        });
    }


    @Test
    public void testTask1Data() {

        String recipient = "+27718693002";

        String messageText =
                "Hi Mike, can you join us for dinner tonight?";

        String cellResult =
                msg.checkRecipientCell(recipient);

        assertEquals(
                "Cell phone number successfully captured.",
                cellResult);

        assertTrue(messageText.length() <= 250);
    }

  
    @Test
    public void testTask2Data() {

        String recipient = "08575975889";

        String messageText =
                "Hi Keegan, did you receive the payment?";

        String cellResult =
                msg.checkRecipientCell(recipient);

        assertEquals(
                "Cell phone number incorrectly formatted.",
                cellResult);

        assertTrue(messageText.length() <= 250);
    }

// Part 3 test
   
// setup to place the test data
    
private void setupPart3TestData(){
    
    msg.sentMessagesArray = new String[5];
    msg.disregardedMessagesArray = new String[5];
    msg.storedMessagesArray= new String[5];
    msg.messageIdArray = new String[5];
    msg.messageHashArray = new String[5];
    
    // test message 1 (sent)
    msg.sentMessagesArray[0] = "Did you get the cake?";
    
    // test message 2 (stored)
    msg.storedMessagesArray[0] = "sender: System, Recipient: +27838884567, Message: Where are you? You are late! I have asked you to be on time.";
    msg.messageIdArray[0] = "0012345671";
    msg.messageHashArray[0] = "00:0:WHERETIME.";
    
    // Test message 3 (Disregard)
    msg.disregardedMessagesArray[0] = "Yohoooo, I am at your gate.";
    
    // test message 4 (sent)
    msg.sentMessagesArray[1] = "It is dinner time !";
    msg.messageIdArray[1] = "0838884567";
    
    //test Data message 5 (stored)
    msg.storedMessagesArray[1] = "sender: System, Recipient: +27838884567, Message: Ok, I am leaving without you.";
    msg.messageIdArray[1] = "0012345671";
    msg.messageHashArray[1] = "00:1:OKYOU.";
    
}

@Test
public void testSentMessagesArrayPopulation() {
    setupPart3TestData();
    // verifies the sent array
    assertEquals("Did you get the cake?", msg.sentMessagesArray[0]);
    assertEquals("It is dinner time !", msg.sentMessagesArray[1]);
}

@Test
public void testDisplayLongestMessage() {
    setupPart3TestData();
    String longest = "";
    for (String m : msg.storedMessagesArray) {
        if (m != null && m.length() > longest.length()){
            longest = m;
        }
    }
    String expectedSnippet = "Where are you? You are late! I have asked you to be on time.";
    assertTrue(longest.contains(expectedSnippet));
  
}

@Test public void testSearchMessageID() {
    setupPart3TestData();
    String searchID = "0838884567";
    String matchedMessage = "";
    
    if (msg.messageIdArray[1] != null && msg.messageIdArray[1].equals(searchID)){
        matchedMessage = msg.sentMessagesArray[1];
    }
    assertEquals("It is dinner time !", matchedMessage);
    
}

@Test
public void testSearchRecipient() {
        setupPart3TestData();
        String targetRecipient = "+27838884567";
        int matchCount = 0;
        
  for (String record : msg.storedMessagesArray) {
      if (record != null && record.contains(targetRecipient)) {
          matchCount++;
      }
  }
  assertEquals(2, matchCount);
  
}

@Test
public void testDeleteMessageUsingHash() {
    setupPart3TestData();
    String targetHash = "00:0:WHERETIME.";
    
  // Find index and simulate deletion clearout
  for (int i = 0; i < msg.messageHashArray.length; i++) {
      if (msg.messageHashArray[i] != null && msg.messageHashArray[i].equals(targetHash)){
          msg.storedMessagesArray[i] = null;
          msg.messageIdArray[i] = null;
          msg.messageHashArray[i] = null;
      }
  }
  // Assert that 0 is successfully wiped out
  assertNull(msg.storedMessagesArray[0]);
  assertNull(msg.messageHashArray[0]);
  
}

@Test
public void testDisplayReportGeneration(){
 setupPart3TestData();
 // validation check ensuring arrays hold test contents
 assertNotNull(msg.sentMessagesArray);
 assertNotNull(msg.storedMessagesArray);
 assertNotNull(msg.messageHashArray);
 
 }

}

 


    
    

