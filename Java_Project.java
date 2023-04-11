import java.io.*;
import java.util.Scanner;

public class Java_Project {

    public static void main(String[] args) throws Throwable {

        System.out.println(
                "Welcome to Java project we will be using \n 3 basic ciphers \n 3 Inbuilt functions for addtional security");

        int choice;

        System.out.println("\n Press \n 1 For Encrypting file \n 2 For Decrypting File");

        Scanner sc = new Scanner(System.in);

        choice = sc.nextInt();

        File inputFile = new File("original.txt");// Input the file to encrypt ,

        if (choice == 1) {
            File encryptedFile = new File("encrypted.txt");
            try {
                System.out.println("\n Choice for Ciphers :\t1 Caesar Cipher \t2 Vigenère Cipher \t3 Affine Cipher");
                System.out.println("\n Choice for In built Ciphers :\t4 AES Cipher \t5 BlowFish Cipher \t6 DES Cipher");
                // WE are using only symmetric Ciphers as decoding them is completely easy due
                // to computational power required by decryption

                int encryption_choice = sc.nextInt();
                if (encryption_choice == 1) {
                    int key;
                    System.out.println("\n Caesar Cipher , Please Enter the Key (Int) ");
                    key = sc.nextInt();
                    CaesarCipher encObject = new CaesarCipher();
                    encObject.Caesar_Cipher(inputFile, encryptedFile, key);
                } else if (encryption_choice == 2) {

                    System.out.println("\n Vigenère Cipher , Please Enter the Keyword (CAPITAL String) ");
                    String keyword = sc.next();
                    VigenereCipher encObject = new VigenereCipher();
                    String key = encObject.VgenerateKey(inputFile, keyword);
                    System.out.println("Key generated in Viginere");
                    encObject.Vigenere_Cipher(inputFile, encryptedFile, key);
                } else if (encryption_choice == 3) {
                    int key1, key2;
                    System.out.println("\n Affine Cipher , Please Enter the Key's (Int's) ");
                    key1 = sc.nextInt();
                    key2 = sc.nextInt();
                    AffineCipher encObject = new AffineCipher();
                    encObject.Affine_Cipher(inputFile, encryptedFile, key1, key2);

                } else if (encryption_choice == 4) {
                    String key;
                    System.out.println("\n AES Cipher , Please Enter the Key (String) ");
                    key = sc.next();
                    System.out.println("Key has been taken");
                    AES encObject = new AES();
                    encObject.AES_Cipher(inputFile, encryptedFile, key);
                } else if (encryption_choice == 5) {
                    String key;
                    System.out.println("\n BlowFish Cipher , Please Enter the Key (String) ");
                    key = sc.next();
                    Blowfish encObject = new Blowfish();
                    encObject.Blowfish_Cipher(inputFile, encryptedFile, key);

                } else if (encryption_choice == 6) {
                    System.out.println("Enter Key for DES ");
                    String key = sc.next(); // needs to be at least 8 characters for DES

                    FileInputStream fis = new FileInputStream("original.txt");
                    FileOutputStream fos = new FileOutputStream("encrypted.txt");
                    DES encObject = new DES();
                    encObject.DES_Encrypt(key, fis, fos);

                } else {
                    System.out.println("\n Choose a correct option");
                }
            } catch (Exception e) {
                System.out.println("Error at Input of encryption choice");
                e.printStackTrace();
            }
        }

        if (choice == 2) {
            File encryptedFile = new File("encrypted.txt");
            File decryptedFile = new File("decrypted.txt");

            try {
                System.out.println(
                        "\n Choice for Deciphers :\t1 Caesar Decipher \t2 Vigenère Decipher \t3 Affine Decipher");
                System.out.println(
                        "\n Choice for In built Deciphers  :\t4 AES Decipher \t5 BlowFish Decipher \t6 DES Decipher");
                int decryption_choice = sc.nextInt();
                if (decryption_choice == 1) {
                    int key;
                    System.out.println("\n Caesar Cipher , Please Enter the Key (Int)");
                    key = sc.nextInt();
                    CaesarCipher decObject = new CaesarCipher();
                    decObject.Caesar_Decipher(encryptedFile, decryptedFile, key);
                } else if (decryption_choice == 2) {

                    System.out.println("\n Vigenère Cipher , Please Enter the Keyword (CAPITAL STRING) ");
                    String key = sc.next();
                    VigenereCipher decObject = new VigenereCipher();
                    decObject.Vigenere_Decipher(encryptedFile, decryptedFile, key);
                } else if (decryption_choice == 3) {
                    int key1, key2;
                    System.out.println("\n Affine Cipher , Please Enter the Key's (INT'S) ");
                    key1 = sc.nextInt();
                    key2 = sc.nextInt();
                    AffineCipher decObject = new AffineCipher();
                    decObject.Affine_Decipher(encryptedFile, decryptedFile, key1, key2);

                } else if (decryption_choice == 4) {
                    String key;
                    System.out.println("\n AES Cipher , Please Enter the Key (STRING) ");
                    key = sc.next();
                    System.out.println("Key has been taken");
                    AES decObject = new AES();
                    decObject.AES_Decipher(encryptedFile, decryptedFile, key);
                } else if (decryption_choice == 5) {
                    String key;
                    System.out.println("\n BlowFish Cipher , Please Enter the Key (STRING) ");
                    key = sc.next();
                    Blowfish decObject = new Blowfish();
                    decObject.Blowfish_Decipher(encryptedFile, decryptedFile, key);

                } else if (decryption_choice == 6) {
                    String key;
                    System.out.println("\n DES Cipher , Please Enter the Key  ");
                    key = sc.next();
                    FileInputStream fis2 = new FileInputStream("encrypted.txt");
                    FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
                    DES decObject = new DES();
                    decObject.DES_Decrypt(key, fis2, fos2);
                }

                else {
                    System.out.println("\n Choose a correct option");

                }

            } catch (Exception e) {

                System.out.println("Error at Input of decryption choice");
                e.printStackTrace();
            }
        }

        sc.close();

    }

}
