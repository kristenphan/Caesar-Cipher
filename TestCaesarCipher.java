import edu.duke.*;

/**
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher
 * Author: Kristen Phan (https://github.com/kristenphan)
 */
public class TestCaesarCipher {
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
         */
        // Identify the key that was used to encrypt the message
        int key = getKey(encrypted);
        
        // Create a CaesarCipher object with that key and decrypt the message
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(encrypted);
        
        return decrypted;
    }
    
    
    public void simpleTests() {
        /**
         * Test breakCaesarCipher() method 
         */
        // Read in a file as a String. This string is the original message to be encrypted
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        // Create a CaesarCipher object with key 18
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        
        // Encrypt the String read in using the CaesarCipher object
        String encrypted = cc.encrypt(input);
        
        // Decrypt the encrypted String using the decrypt method
        String decrypted = cc.decrypt(encrypted);
        System.out.println("input: " + input + "\n" +
                           "encrypted: " + encrypted + "\n" +
                           "decrypted: " + decrypted + "\n" +
                           "correct answer: " + decrypted.equals(input));
    }
    
    
    public static void main (String[] args) {
        TestCaesarCipher test = new TestCaesarCipher();
        test.simpleTests();
    }
}
