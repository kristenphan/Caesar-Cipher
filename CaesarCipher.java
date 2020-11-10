import edu.duke.*;


public class CaesarCipher {
    // initalize CaesarCipher object attributes
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        /**
         * Initlize a CaesarCipher object and its attributes: encryption alphabet, shiftedAlphabet based on key
         */
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key); 
    }
    
    
    public String encrypt(String input) {
        /**
         * Return a string which is the encrypted message of the input message.
         * This method preserves the case of the original message, 
         * meaning the encrypted message consists of letters in the same case of the original letters
         * E.g. encrypt(“First Legion”) = “Cfopq Ibdflk”,
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
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
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
    
    
    public void testEncrypt() {
        // Encrypt a message using the object 'mainKey'
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message);
        System.out.println("original message: " + message);
        System.out.println("key: " + mainKey + "\n" + "encrypted message: " + encrypted);
        
        // Decrypt the encrypted message by creating a new object with key of 29 - mainKey
        CaesarCipher decryptedObj = new CaesarCipher(26 - mainKey);
        String decrypted = decryptedObj.encrypt(encrypted);
        System.out.println("encrypted message decrypted: " + decrypted);
        System.out.println("correct answer: " + decrypted.equals(message));
    }
    
    
    public String decrypt(String encrypted) {
        /**
         * Return a string that is the encrypted String decrypted using the key associated with this CaesarCipher object
         */
        // create a new instance of CaesarCipher class  
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        // Use the encryption key to decrypt the message
        return cc.encrypt(encrypted); 
    }
    
    
    public void testDecrypt() {
        /**
         * Test decrypt() method
         */
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD! EEEEWW";
        String encrypted = encrypt(message);
        String decrypted = decrypt(encrypted);
        System.out.println("original message: " + message);
        System.out.println("encrypted message: " + encrypted);
        System.out.println("decrypted message: " + decrypted);
        System.out.println("correct answer: " + decrypted.equals(message));
    }
    
    
    public static void main(String[] args) {
        int key = 20;
        CaesarCipher test = new CaesarCipher(key);
        test.testEncrypt();
    }
}

