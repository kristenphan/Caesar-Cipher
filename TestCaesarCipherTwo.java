import edu.duke.*;

/**
 * Write a description of TestCaesarCiphertwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        /**
         * Return a string that is every other character from 'messsage' starting with the 'start' position
         * E.g. halfOfString("Qbkm Zgis", 1) = "bmZi"
         */
        
        StringBuilder halfStr = new StringBuilder();
        for (int i = start; i < message.length(); i+=2) {
            halfStr.append(message.charAt(i));
        }
        
        return halfStr.toString();
    }
    
    
    private void testHalfOfString() {
        /**
         * Test halfOfString() method
         */
        String message = "Qbkm Zgis";
        String correctAns = "bmZi";
        int start = 1;
        String halfStr = halfOfString(message, start);
        System.out.println("message: " + message + "\n" +
                           "halfStr: " + halfStr + "\n" +
                           "correct answer: " + halfStr.equals(correctAns));
    }

    
    private int[] countLetters(String message) {
        /**
         * Return an array storing the frequency counts of each letter in the alphabet
         */
        
        // Create a string of all alphabet letters
        String alph = "abcdefghijklmnopqrstuvwxyz";
        // Create an array to keep track of frequency counts of each letter
        int[] freqs = new int[alph.length()];
        
        // Iterate through all letters in message and keep track of frequency counts
        for (int i = 0; i < message.length(); i++) {
            char currChar = Character.toLowerCase(message.charAt(i));
            int idx = alph.indexOf(currChar);
            if (idx != -1) {
                freqs[idx] += 1;
            }
        }
        
        return freqs;
    }
    
    
    private void testCountLetters() {
        /** 
         * Test countLetters() method
         */
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! EEEEWW";
        int[] freqs = countLetters(message);
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < freqs.length; i++) {
            System.out.println("Number of letter " + alph.charAt(i) + ": " + freqs[i]);
        }
    }
    
    
    private int maxIndex(int[] freqs) {
        /**
         * Return the index of the array element of 'freqs' with the largest frequency count
         */
        
        // Create variables to keep track of max frequent count and the respective index
        int maxIdx = -1;
        int maxFreqCount = 0;
        
        // Iterate through all frequent counts and find the index of the highest frequency count
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > maxFreqCount) {
                maxIdx = i;
                maxFreqCount = freqs[i];
            }
        }
        
        return maxIdx;
    }
    
    
    private void testMaxIndex() {
        /**
         * Test maxIndex() method
         */
        int[] freqs = {0, 1, 2, 3, 4, 50, 6, 7, 8, 9};
        int maxIndex = maxIndex(freqs);
        System.out.println("maxIndex = " + maxIndex);
    }
    
    
    private int getKey(String encrypted) {
        /**
         * Return the key of the Caesar Cipher algorithm assuming that the letter in the encrypted string 
         * with the highest frequency count is the encrypted version of letter 'e'
         */
        // count the frequency of each letter in the encrypted string
        int[] freqs = countLetters(encrypted);
        
        // obtain the index of the letter with the highest frequency count in string s
        int maxIdx = maxIndex(freqs);
        
        // assume that said letter is the encrypted version of letter 'e'. calculate the encrcyption key
        int key = maxIdx - 4; 
        if (maxIdx < 4) { // loop around
            key = 26 - (4 - maxIdx);
        }
        
        return key;
    }
    
    
    private String breakCaesarCipher(String encrypted) {
        /**
         * Return a string that is the encrypted message decrypted
         * The original message was encrypted using two unknown keys
         */
        // Split the encrypted message into 2 substrings, each was encrypted with a different key
        String subEncryptedKey1 = halfOfString(encrypted, 0);
        String subEncryptedKey2 = halfOfString(encrypted, 1);
        
        // Identify the encryption keys
        int guessedKey1 = getKey(subEncryptedKey1);
        int guessedKey2 = getKey(subEncryptedKey2);
        System.out.println("guessed key1 = " + guessedKey1 + "\n" +
                           "guessed key2 = " + guessedKey2);
        
        // Create a CaesarCipher object with the encryption keys and decrypt the message
        CaesarCipherTwo cct = new CaesarCipherTwo(guessedKey1, guessedKey2);
        String decrypted = cct.decrypt(encrypted);
        
        return decrypted;
    }
    
    
    private void testBreakCaesarCipher() {
        /**
         * Test breakCaesarCipher() method
         */
        FileResource fr = new FileResource("data/mysteryTwoKeysQuiz.txt");
        String encrypted = fr.asString();
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("encrypted: " + encrypted + "\n" + 
                           "decrypted: " + decrypted);
    }
    
    
    private void simpleTests() {
        /** 
         * Read in a file as a String, create a CaesarCipherTwo object with key17, 3
         * Encrypt the string using the CaesarCipherTwo object
         * print the encrypted string
         * decrypt the encrypted string using the decrypt metho
         */
        // Read in a file as a String. This string is the original message to be encrypted
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        // Create a CaesarCipherTw0 object with key 17 and 3
        int key1 = 21;
        int key2 = 8;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        
        // Encrypt the string using the CaesarCipherTwo object
        String encrypted = cct.encrypt(input);
        
        // Decrypt the encrypted string using breakCaesarCipher method
        String decrypted = breakCaesarCipher(encrypted);
        
        System.out.println("input: " + input + "\n" + 
                           "key1: " +  key1 + "\n" +
                           "key2: " + key2 + "\n" + 
                           "encrypted: " + encrypted + "\n" + 
                           "decrypted: " + decrypted + "\n" + 
                           "correct answer: " + decrypted.equals(input));
    }
    
    
    public static void main (String[] args) {
        TestCaesarCipherTwo test = new TestCaesarCipherTwo();
        test.testBreakCaesarCipher();
    }
}
