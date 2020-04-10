import java.io.*;
import java.math.BigInteger;
import java.security.Key;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;


public class Java_Project {

    // Caesar Cipher & Decipher
    
    public static void Caesar_Cipher(File inputFile, File encryptedFile, int keyFile)  {
        try  {
            
            StringBuffer result = new StringBuffer();//String buffer to store the result of the value
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
            File fencrypt = new File("encrypted.txt");//open the encrypted file to write data in

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter is pointing towards the encrypted .txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());//we write on the encrypted text
                bwriter.close();
                System.out.println("Also Caesar encryption has been written in encrypted.txt");
           
            } catch (Exception e) {
                System.out.println("\n Error at writing into encrypted file in Caesar Cipher");
                e.printStackTrace();
            }
            br.close();

        } 
        catch (Exception e) {
            System.out.println("\n Error at reading file from original file in Caesar Cipher");
            e.printStackTrace();

        }
    }

    public static void Caesar_Decipher(File encryptedFile, File decryptedFile, int keyFile)  {
       try {
        //we write on the encrypted text
            StringBuffer result = new StringBuffer();
            File finput = new File("encrypted.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                char character = (char) c; // converting integer to char
                // Algorithm for Caesar-Decipher
                if(character >= 'a' && character <= 'z')
                {
                    char ch = (char)(character - keyFile);
                    
                    if(ch < 'a'){
                        ch = (char)(ch + 'z' - 'a' + 1);
                    }
                    result.append(ch);

                }
                else if(character >= 'A' && character <= 'Z'){
                    char ch = (char)(character - keyFile);
                    
                    if(ch < 'A'){
                        ch = (char)(ch + 'Z' - 'A' + 1);
                    }
                    result.append(ch);

                }
                else {
                    result.append(character);
                }
            }

            System.out.println("Caesar Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");//open decrypted.txt file 
            
            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter is pointing towards decrypted .txt file 
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());//we write decrypted txt
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


    // Vigenere Cipher & Decipher 
    
    //A function to generate a key for the Viginere function as the key is ,
    //based on the viginere table

    public static String VgenerateKey(File inputFile, String key)  {
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
            
        } 
        catch (Exception e) {
            System.out.println("\n Error at Generating Key in Vigenere Cipher");
            e.printStackTrace();  
        }
        
        return key;
     }

    public static void Vigenere_Cipher(File inputFile, File encryptedFile, String key) throws IOException {

        try {
            File finput = new File("original.txt"); // Creation of File Descriptor for input file
            FileReader fr = new FileReader(finput); // Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
            String cipher_text = "";
            int c = 0;
            int i = 0;
            while ((c = br.read()) != -1) // Read char by Char
            {
                //Algorithm for Vigenere Ciphere
                char character = (char) c; // converting integer to char
                // converting in range 0-25
                int x = (character + key.charAt(i)) % 26;
                // convert into alphabets(ASCII)
                x += 'A';
                i++;
    
                cipher_text += (char) (x);
    
            }
            System.out.println("Vigenere Cipher applied Succesfully");
            File fencrypt = new File("encrypted.txt");//open the encrypted.txt file 
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter is pointing towards encrypted.txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(cipher_text);//writes encrypted text in the ifle
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

    
    public static void Vigenere_Decipher(File encryptedFile, File decryptedFile, String key) throws IOException {

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
                if(character < 65 || character > 90){ //ASCII character (capital letter)
                    throw new IllegalArgumentException("" +"Ciphertext must contain only capital letters");
                }
                //subtract shift modularly
                char decyphered = character - getShift(key, i) < 65 ? (char)((character - getShift(key, i)) + 26) : (char)(character - getShift(key, i));
                result.append(decyphered);
                i++;
            }
            br.close();

            System.out.println("Vigenere Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");//open decrypted.txt file
            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter points towards decrypted.txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());//we write it inside decrypted.txt
                bwriter.close();
                System.out.println("Also Vigenere decryption has been written in decrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in Vigenere Cipher");
                e.printStackTrace();
            }
        } 
        catch (Exception e) {
            System.out.println("\n Error at reading file from encrypted file in Viginere Cipher");
            e.printStackTrace();

        }
        
        
    }

    // Function Used to return exace Key value of every character

    private static int getShift(String key, int i) {
        if(key.charAt(i % key.length()) < 65 || key.charAt(i % key.length()) > 90){
            throw new IllegalArgumentException("" +"Key phrase must contain only capital letters");
        }
    return ((int)key.charAt(i % key.length())) - 65;
}


    // Affine Cipher & Decipher 

    static void Affine_Cipher(File inputFile, File encryptedFile, int key1, int key2) throws IOException {
        
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
                    character = (char) ((key1* (character - 'a') + key2) % 26 + 'a');
                }
                result.append(character);
                // Display the Character
            }

            System.out.println("Affine Cipher applied Succesfully");
            File fencrypt = new File("encrypted.txt");//open encrypted.txt

            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter is pointing towards the encrypted .txt
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());//we write on the encrypted text
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
    
    static void Affine_Decipher(File encryptedFile, File decryptedFile, int key1, int key2) throws IOException {
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
            //we write on the encrypted text
            System.out.println("Affine Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");//open decrypted.txt
            
            StringBuffer sb = result;
            try {
                FileWriter fwriter = new FileWriter(fencrypt);//fwriter is pointing towards decrypted .txt file 
                BufferedWriter bwriter = new BufferedWriter(fwriter);
                bwriter.write(sb.toString());//we write decrypted txt
                bwriter.close();
                System.out.println("Also Affline decryption has been written in decrypted.txt");

            } catch (Exception e) {
                System.out.println("\n Error at writing into decrypted file in Affline Cipher");
                e.printStackTrace();
            }
            br.close();
        } 
        catch (Exception e) {

            System.out.println("\n Error at reading file from encrypted file in Affine Cipher");
            e.printStackTrace();
        }
        
            
    }


    //AES Encryption & Decryption

    private static SecretKeySpec secretKey;
    private static byte[] key;//Used to 

    //function used to set value of key in case of AES
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        //This MessageDigest class provides applications the functionality of a message digest algorithm, 
        //such as SHA-1 or SHA-256. Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value. 
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");//SHA 1 is the algorithm used 
            key = sha.digest(key);//Digest::SHA is a complete implementation of the NIST Secure Hash Standard
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");//It can be used to construct a SecretKey from a byte array, without having to go through a (provider-based) SecretKeyFactory. 
        } 
        catch (Exception e) {
            System.out.println("Error at set_key() in AES Encryption ");
            e.printStackTrace();
        } 

    }

    public static void AES_Cipher(File inputFile, File encryptedFile, String key){
        
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
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//Choosing an instance for cipher
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//Setting Mode 
            String cip= Base64.getEncoder().encodeToString(cipher.doFinal(cipher_text.getBytes("UTF-8")));//Storing encrypted data
            File fencrypt = new File("encrypted.txt");//open the encrypted file to write data in
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
        } 
        catch (Exception e) {
            System.out.println("\n Error at reading file from original file in AES Cipher");
            e.printStackTrace();
        }
    }

    public static void AES_Decipher(File encryptedFile, File decryptedFile, String key){
        
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
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//get instance AES
            cipher.init(Cipher.DECRYPT_MODE, secretKey);//Setting mode
            String cip=new String(cipher.doFinal(Base64.getDecoder().decode(cipher_text)));//Storing decrypted data
            System.out.println("AES Decipher applied Succesfully");
            File fencrypt = new File("decrypted.txt");//open decrypted .txt

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

    // BlowFish Encryption & Decryption 

    public static void Blowfish_Cipher(File inputFile, File outputFile,String key)throws Exception {
        try {
    
            BlowfishdoCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile,key);//Select Mode=ENCRYPT 
            System.out.println("Blowfish Cipher applied Succesfully");    
        } 
        catch (Exception e) {
            System.out.println("\n Error at reading file from original file in Blowfish Cipher");
            e.printStackTrace();
        }
    }

	public static void Blowfish_Decipher(File inputFile, File outputFile,String key) throws Exception {
        try {
    
            BlowfishdoCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile,key);//Select Mode=DECRYPT
	    	System.out.println("Blowfish Decipher applied Succesfully");

        } catch (Exception e) {
            System.out.println("\n Error at reading file from encrypted file in Blowfish Decipher");
            e.printStackTrace();

        }
    }

	public static void BlowfishdoCrypto(int cipherMode, File inputFile,File outputFile,String keyString) throws Exception {
        
        String ALGORITHM = "Blowfish";//It's used in secret key    	
        Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);//Making S blocks
		Cipher cipher = Cipher.getInstance(ALGORITHM);//getting them in p arrays
		cipher.init(cipherMode, secretKey);// recreating the cipher or randomly set the IV yourself for each subsequent message

        try {
        
            FileInputStream inputStream = new FileInputStream(inputFile);
		    byte[] inputBytes = new byte[(int) inputFile.length()];
		    inputStream.read(inputBytes);
            //reading data in input file 

            byte[] outputBytes = cipher.doFinal(inputBytes);
            // do final reset the internal state to the same IV you started with
            //We do encryption based on bytes in Blowfish

		    FileOutputStream outputStream = new FileOutputStream(outputFile);
		    outputStream.write(outputBytes);
            //we are writing the data in the file 
            System.out.println("\n Also Blowfish cipher as been written to encrypted.txt & Decipher has been written to decrypted.txt");
            inputStream.close();
            outputStream.close();
            
        } 
        catch (Exception e) {

            System.out.println("\n Error at writing file  in Blowfish Cipher / Decipher");
            e.printStackTrace();

        }
		
		
    }

    //DES Encryption & Decryption

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];//64 bits keys
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
    }
    public static void DES_Encrypt(String key, InputStream is, OutputStream os) throws Throwable {
        try {
            
            encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);//Select Mode =ENCRYPT
            System.out.println("DES Encryption Done");
        } catch (Exception e) {
            System.out.println("Error at Reading original.txt");
        }
    }

	public static void DES_Decrypt(String key, InputStream is, OutputStream os) throws Throwable {
        try {
            encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);//Select Mode =DECRYPT
            System.out.println("DES Decryption Done");
        } catch (Exception e) {
            System.out.println("Error at Reading encrypted.txt");

        }
        
    }

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());//encryption keys using java crypto library 
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");//creating Instance 
		SecretKey desKey = skf.generateSecret(dks);// creating keys 
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);//Select Mode = Encrypt
			CipherInputStream cis = new CipherInputStream(is, cipher);//for inputing data  
			doCopy(cis, os);//copying the data into file
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);// select mode =Decrypt
			CipherOutputStream cos = new CipherOutputStream(os, cipher);//for outputing data
			doCopy(is, cos);//copying the data into file
		}
	}

	
    
    // Main function

    
    public static void main(String[] args) throws Throwable {

        System.out.println("Welcome to Java project we will be using \n 3 basic ciphers \n 2 Inbuilt functions which provide high security");

        int choice;

        System.out.println("\n Press \n 1 For Encrypting file \n 2 For Decrypting File");

        Scanner sc = new Scanner(System.in);

        choice = sc.nextInt();

        File inputFile = new File("original.txt");// Input the file to encrypt ,

        if (choice == 1) {
            File encryptedFile = new File("encrypted.txt");
            try {
                System.out.println("\n Choice for Ciphers :\t1 Caesar Cipher \t2 Vigenère Cipher \t3 Affine Cipher");
                System.out.println("\n Choice for In built Ciphers  :\t4 AES Cipher \t5 BlowFish Cipher \t6 DES Cipher");
                // WE are using only symmetric Ciphers as decoding them is completely easy due
                // to computational power required by decryption

                int encryption_choice = sc.nextInt();
                if (encryption_choice == 1) {
                    int key;
                    System.out.println("\n Caesar Cipher , Please Enter the Key (Int) ");
                    key = sc.nextInt();
                    Caesar_Cipher(inputFile, encryptedFile, key);//Call Caesar Function
                }
                if (encryption_choice == 2) {

                    System.out.println("\n Vigenère Cipher , Please Enter the Keyword (CAPITAL String) ");
                    String keyword = sc.next();
                    String key = VgenerateKey(inputFile, keyword);
                    System.out.println("Key generated in Viginere");
                    Vigenere_Cipher(inputFile, encryptedFile, key);
                    System.out.println("Encryption");
                }
                if (encryption_choice == 3) {
                    int key1, key2;
                    System.out.println("\n Affine Cipher , Please Enter the Key's (Int's) ");
                    key1 = sc.nextInt();
                    key2 = sc.nextInt();
                    Affine_Cipher(inputFile, encryptedFile, key1, key2);

                }
                if (encryption_choice == 4) {
                    String key;
                    System.out.println("\n AES Cipher , Please Enter the Key (String) ");
                    key = sc.next();
                    System.out.println("Key has been taken");
                    AES_Cipher(inputFile, encryptedFile, key);
                }
                if (encryption_choice == 5) {
                    String key;
                    System.out.println("\n BlowFish Cipher , Please Enter the Key (String) ");
                    key=sc.next();
                    Blowfish_Cipher(inputFile, encryptedFile, key);

                }
                if (encryption_choice==6)
                {
                    System.out.println("Enter Key for DES ");
			        String key = sc.next(); // needs to be at least 8 characters for DES

			        FileInputStream fis = new FileInputStream("original.txt");
			        FileOutputStream fos = new FileOutputStream("encrypted.txt");
			        DES_Encrypt(key, fis, fos);

                } 
                else {
                    System.out.println("\n Choose A correct option");

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
                System.out.println("\n Choice for Deciphers :\t1 Caesar Decipher \t2 Vigenère Decipher \t3 Affine Decipher");
                System.out.println("\n Choice for In built Deciphers  :\t4 AES Decipher \t5 BlowFish Decipher \t6 DES Decipher");
                int decryption_choice = sc.nextInt();
                if (decryption_choice == 1) {
                    int key;
                    System.out.println("\n Caesar Cipher , Please Enter the Key (Int)");
                    key = sc.nextInt();
                    Caesar_Decipher(encryptedFile, decryptedFile, key);
                }
                if (decryption_choice == 2) {

                    System.out.println("\n Vigenère Cipher , Please Enter the Keyword (CAPITAL STRING) ");
                    String key = sc.next();
                    Vigenere_Decipher(encryptedFile, decryptedFile, key);
                }
                if (decryption_choice == 3) {
                    int key1, key2;
                    System.out.println("\n Affine Cipher , Please Enter the Key's (INT'S) ");
                    key1 = sc.nextInt();
                    key2 = sc.nextInt();
                    Affine_Decipher(encryptedFile, decryptedFile, key1, key2);

                }
                if (decryption_choice == 4) {
                    String key;
                    System.out.println("\n AES Cipher , Please Enter the Key (STRING) ");
                    key = sc.next();
                    System.out.println("Key has been taken");
                    AES_Decipher(encryptedFile, decryptedFile, key);
                }
                if (decryption_choice == 5) {
                    String key;
                    System.out.println("\n BlowFish Cipher , Please Enter the Key (STRING) ");
                    key=sc.next();
                    Blowfish_Decipher(encryptedFile, decryptedFile, key);

                } 
                if (decryption_choice == 6) {
                    String key;
                    System.out.println("\n DES Cipher , Please Enter the Key  ");
                    key=sc.next();
                    FileInputStream fis2 = new FileInputStream("encrypted.txt");
			        FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
			        DES_Decrypt(key, fis2, fos2);    
                }

                else {
                    System.out.println("\n Choose A correct option");

                }

            }
                catch (Exception e) {

                    System.out.println("Error at Input of decryption choice");
                    e.printStackTrace();
               }
        }

        sc.close();

    }

    
}
