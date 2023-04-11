import java.math.BigInteger;
import java.io.*;

class AffineCipher {

    void Affine_Cipher(File inputFile, File encryptedFile, int key1, int key2) throws IOException {

        try {

            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            StringBuffer result = new StringBuffer();
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // System.out.println(character);
                if (Character.isLetter(character)) {
                    character = (char) ((key1 * (character - 'a') + key2) % 26 + 'a');
                }
                result.append(character);
                // Display the Character
            }

            System.out.println("Affine Cipher applied Succesfully");
            File fencrypt = new File("encrypted.txt");// open encrypted.txt

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter is pointing towards the encrypted .txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());// we write on the encrypted text
                bwriter.close();
                System.out.println("Also Affline encryption has been written in encrypted.txt");
            } catch (Exception e) {
                System.out.println("\n Error at writing into encrypted file in Affline Cipher");
                e.printStackTrace();
            }
            br.close();
        }

        catch (Exception e) {
            System.out.println("\n Error at reading file from original file in Affine Cipher");
            e.printStackTrace();
        }
    }

    void Affine_Decipher(File encryptedFile, File decryptedFile, int key1, int key2) throws IOException {
        try {

            File finput = new File("encrypted.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            StringBuffer result = new StringBuffer();
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            // compute firstKey^-1 aka "modular inverse"
            BigInteger inverse = BigInteger.valueOf(key1).modInverse(BigInteger.valueOf(26));
            // perform actual decryption
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                if (Character.isLetter(character)) {
                    int decoded = inverse.intValue() * (character - 'a' - key2 + 26);
                    character = (char) (decoded % 26 + 'a');
                }
                result.append(character);// Display the Character
            }
            // we write on the encrypted text
            System.out.println("Affine Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");// open decrypted.txt

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter is pointing towards decrypted .txt file
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());// we write decrypted txt
                bwriter.close();
                System.out.println("Also Affline decryption has been written in decrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in Affline Cipher");
                e.printStackTrace();
            }
            br.close();
        } catch (Exception e) {

            System.out.println("\n Error at reading file from encrypted file in Affine Cipher");
            e.printStackTrace();
        }

    }
}