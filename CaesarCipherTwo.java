
/**
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher
 * Author: Kristen Phan (https://github.com/kristenphan)
 */
public class CaesarCipherTwo {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2) {
        /**
         * Initialize a new CaesarCipher object
         */
        mainKey1 = key1;
        mainKey2 = key2; 
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    
    public String encrypt(String input) {
        /**
         * Return a string that has been encrypted in the following fashion:
         * key1 (object attribute) is used to encrypt every other character with the Caesar Cipher algorithm, starting with the first char
         * key2 (object attribute) is used to encrypt every other character, starting with the second character
         * E.g. encryptTwoKeys(“First Legion”) = “Czojq Ivdzle”
         */
        
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);

        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            //If currChar is in the alphabet
            if (idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar), 
                // depending on the position of currChar
                char newChar;
                if (i % 2 == 0) { // Letter in even idx i.e. odd position. Use key1 to encrypt
                    newChar = shiftedAlphabet1.charAt(idx);
                }      
                else { // Letter in odd idx i.e. even position. Use key2 to encrypt
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                //Replace the ith character of encrypted with newChar while preserving its case
                if (currChar == Character.toUpperCase(currChar)) { // original letter is in uppercase
                    encrypted.setCharAt(i, newChar);
                }
                else { // original letter is in lower case
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    
    private void testEncrypt() {
        /**
         * Test encrypt() method
         */
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(input);
        System.out.println("Key1 = " + mainKey1 + "\n" + 
                           "Key2 = " + mainKey2 + "\n" + 
                           "Original message: " + input + "\n" + 
                           "Encrypted message: " + encrypted);
    }
    
    
    public String decrypt (String encrypted) {
        /**
         * Return a string that is the encrypted string decrypted 
         * provided that the original input message was encrypted using 2 keys in the CaesarCipher object
         */
        
        // Create a new CaesarCipherTwo class object with the decryption keys
        // (decryption key = 26 - encryption key)
        // and decrypt the encrypted message using the CaesarCipher object's keys
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc.encrypt(encrypted);
        
        return decrypted;
    }
    
    
    private void testDecrypt() {
        /** 
         * Test decrypt() method
         */
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String encrypted = encrypt(input);
        //String decrypted = decrypt(encrypted);
        //System.out.println("Key1 = " + mainKey1 + "\n" + 
        //                   "Key2 = " + mainKey2 + "\n" + 
        //                   "Original message: " + input + "\n" + 
        //                   "Encrypted message: " + encrypted + "\n" + 
        //                   "Decrypted message: " + decrypted  + "\n" + 
        //                  "Correct answer: " + decrypted.equals(input));
        
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = decrypt(encrypted);
        System.out.println("key1 = " + mainKey1 + "\n" +
                           "key2 = " + mainKey2 + "\n" +
                           "encrypted = " + encrypted + "\n" +
                           "decrypted = " + decrypted);
    }
    
    
    public static void main (String[] args) {
        int key1 = 14;
        int key2 = 24;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        cct.testDecrypt();
    }
}
