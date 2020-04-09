import java.math.BigInteger;
import java.util.Scanner;

class AffineCipher {

    static String encrypt(String input , int firstKey, int secondKey) {
        StringBuilder builder = new StringBuilder();
        for (int in = 0; in < input.length(); in++) {
            char character = input.charAt(in);
            if (Character.isLetter(character)) {
                character = (char) ((firstKey * (character - 'a') + secondKey) % 26 + 'a');
            }
            builder.append(character);
        }
        return builder.toString();
    }

    static String decrypt(String input ,int firstKey, int secondKey) {
        StringBuilder builder = new StringBuilder();
        // compute firstKey^-1 aka "modular inverse"
        BigInteger inverse = BigInteger.valueOf(firstKey).modInverse(BigInteger.valueOf(26));
        // perform actual decryption
        for (int in = 0; in < input.length(); in++) {
            char character = input.charAt(in);
            if (Character.isLetter(character)) {
                int decoded = inverse.intValue() * (character - 'a' - secondKey + 26);
                character = (char) (decoded % 26 + 'a');
            }
            builder.append(character);
        }
        return builder.toString();
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Enter Text to encrypt & it's Key's (Int's)");
        String input= sc.next();
        int key1=sc.nextInt();
        int key2=sc.nextInt();
        String cipher = encrypt(input,key1,key2);
        String deciphered = decrypt(cipher,key1,key2);
        System.out.println("Original text : " + input);
        System.out.println("Encrypted text : " + cipher);
        System.out.println("Decryted text : " + deciphered);
    }


}