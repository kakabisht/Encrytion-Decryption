import java.io.*;

class CaesarCipher {

    // Encrypts text using a shift od s

    public void Caesar_Cipher(File inputFile, File encryptedFile, int keyFile) {
        try {

            StringBuffer result = new StringBuffer();// String buffer to store the result of the value
            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // Algorithm for Caesar Cipher
                if (Character.isUpperCase(character)) {
                    char ch = (char) (((int) character + keyFile - 65) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) character + keyFile - 97) % 26 + 97);
                    result.append(ch);
                }
            }
            System.out.println("Caesar Cipher applied Succesfully");
            File fencrypt = new File("encrypted.txt");// open the encrypted file to write data in

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter is pointing towards the encrypted .txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());// we write on the encrypted text
                bwriter.close();
                System.out.println("Also Caesar encryption has been written in encrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into encrypted file in Caesar Cipher");
                e.printStackTrace();
            }
            br.close();

        } catch (Exception e) {
            System.out.println("\n Error at reading file from original file in Caesar Cipher");
            e.printStackTrace();

        }
    }

    public void Caesar_Decipher(File encryptedFile, File decryptedFile, int keyFile) {
        try {
            // we write on the encrypted text
            StringBuffer result = new StringBuffer();
            File finput = new File("encrypted.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // Algorithm for Caesar-Decipher
                if (character >= 'a' && character <= 'z') {
                    char ch = (char) (character - keyFile);

                    if (ch < 'a') {
                        ch = (char) (ch + 'z' - 'a' + 1);
                    }
                    result.append(ch);

                } else if (character >= 'A' && character <= 'Z') {
                    char ch = (char) (character - keyFile);

                    if (ch < 'A') {
                        ch = (char) (ch + 'Z' - 'A' + 1);
                    }
                    result.append(ch);

                } else {
                    result.append(character);
                }
            }

            System.out.println("Caesar Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");// open decrypted.txt file

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);// fwriter is pointing towards decrypted .txt file
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());// we write decrypted txt
                bwriter.close();
                System.out.println("Also Caesar decryption has been written in decrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in Caesar Decipher");
                e.printStackTrace();
            }
            br.close();

        }

        catch (Exception e) {
            System.out.println("\n Error at reading file from encrypted file in Caesar Decipher");
            e.printStackTrace();

        }

    }
}