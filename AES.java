import java.security.MessageDigest;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;// Used to

    // function used to set value of key in case of AES
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        // This MessageDigest class provides applications the functionality of a message
        // digest algorithm,
        // such as SHA-1 or SHA-256. Message digests are secure one-way hash functions
        // that take arbitrary-sized data and output a fixed-length hash value.
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");// SHA 1 is the algorithm used
            key = sha.digest(key);// Digest::SHA is a complete implementation of the NIST Secure Hash Standard
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");// It can be used to construct a SecretKey from a byte array,
                                                      // without having to go through a (provider-based)
                                                      // SecretKeyFactory.
        } catch (Exception e) {
            System.out.println("Error at set_key() in AES Encryption ");
            e.printStackTrace();
        }

    }

    public void AES_Cipher(File inputFile, File encryptedFile, String key) {

        try {

            String cipher_text = "";
            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // System.out.println(character);
                cipher_text += character;
            }
            setKey(key);// User defined Funtion
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// Choosing an instance for cipher
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);// Setting Mode
            String cip = Base64.getEncoder().encodeToString(cipher.doFinal(cipher_text.getBytes("UTF-8")));// Storing
                                                                                                           // encrypted
                                                                                                           // data
            File fencrypt = new File("encrypted.txt");// open the encrypted file to write data in
            System.out.println("AES Cipher applied Succesfully");
            try {
                FileWriter fwriter = new FileWriter(fencrypt);
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(cip);
                bwriter.close();
                System.out.println("Also AES encryption has been written in encrypted.txt");
            } catch (Exception e) {
                System.out.println("\n Error at writing into encrypted file in AES Cipher");
                e.printStackTrace();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("\n Error at reading file from original file in AES Cipher");
            e.printStackTrace();
        }
    }

    public void AES_Decipher(File encryptedFile, File decryptedFile, String key) {

        try {

            String cipher_text = "";
            File finput = new File("encrypted.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // System.out.println(character);
                cipher_text += character;
            }
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// get instance AES
            cipher.init(Cipher.DECRYPT_MODE, secretKey);// Setting mode
            String cip = new String(cipher.doFinal(Base64.getDecoder().decode(cipher_text)));// Storing decrypted data
            System.out.println("AES Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");// open decrypted .txt

            try {
                FileWriter fwriter = new FileWriter(fencrypt);
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(cip);
                bwriter.close();
                System.out.println("Also AES decryption has been written in decrypted.txt");
            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in AES Decipher");
                e.printStackTrace();
            }
            br.close();

        } catch (Exception e) {

            System.out.println("\n Error at reading file from encrypted file in AES Decipher");
            e.printStackTrace();
        }

    }
}