import java.io.*;

public class VigenereCipher {

    private static int getShift(String key, int i) {
        /**
         * Returns exact Key value for each character
         */
        if (key.charAt(i % key.length()) < 65 || key.charAt(i % key.length()) > 90) {
            throw new IllegalArgumentException("" + "Key phrase must contain only capital letters");
        }
        return ((int) key.charAt(i % key.length())) - 65;
    }

    public String VgenerateKey(File inputFile, String key) {
        try {

            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c;
                key += (character);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("\n Error at Generating Key in Vigenere Cipher");
            e.printStackTrace();
        }

        return key;
    }

    public void Vigenere_Cipher(File inputFile, File encryptedFile, String key) throws IOException {

        try {
            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            String cipher_text = "";
            int c = 0;
            int i = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                // Algorithm for Vigenere Ciphere
                char character = (char) c; // converting integer to char
                // converting in range 0-25
                int x = (character + key.charAt(i)) % 26;
                // convert into alphabets(ASCII)
                x += 'A';
                i++;

                cipher_text += (char) (x);

            }
            System.out.println("Vigenere Cipher applied Succesfully");
            File fencrypt = new File("encrypted.txt");// open the encrypted.txt file
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter is pointing towards encrypted.txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(cipher_text);// writes encrypted text in the ifle
                bwriter.close();
                System.out.println("Also Vigenere encryption has been written in encrypted.txt");
            } catch (Exception e) {
                System.out.println("\n Error at writing into encrypted file in Vigenere Cipher");
                e.printStackTrace();

            }
            br.close();

        } catch (Exception e) {
            System.out.println("\n Error at reading file from original file in Viginere Cipher");
            e.printStackTrace();
        }

    }

    public void Vigenere_Decipher(File encryptedFile, File decryptedFile, String key) throws IOException {

        try {

            StringBuffer result = new StringBuffer();
            File finput = new File("encrypted.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            int i = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {

                char character = (char) c; // converting integer to char
                if (character < 65 || character > 90) { // ASCII character (capital letter)
                    throw new IllegalArgumentException("" + "Ciphertext must contain only capital letters");
                }
                // subtract shift modularly
                char decyphered = character - getShift(key, i) < 65 ? (char) ((character - getShift(key, i)) + 26)
                        : (char) (character - getShift(key, i));
                result.append(decyphered);
                i++;
            }
            br.close();

            System.out.println("Vigenere Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");// open decrypted.txt file
            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter points towards decrypted.txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());// we write it inside decrypted.txt
                bwriter.close();
                System.out.println("Also Vigenere decryption has been written in decrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in Vigenere Cipher");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("\n Error at reading file from encrypted file in Viginere Cipher");
            e.printStackTrace();

        }
    }

}