
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Blowfish {

	public void Blowfish_Cipher(File inputFile, File outputFile, String key) throws Exception {
		try {

			BlowfishdoCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile, key);// Select Mode=ENCRYPT
			System.out.println("Blowfish Cipher applied Succesfully");
		} catch (Exception e) {
			System.out.println("\n Error at reading file from original file in Blowfish Cipher");
			e.printStackTrace();
		}
	}

	public void Blowfish_Decipher(File inputFile, File outputFile, String key) throws Exception {
		try {

			BlowfishdoCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile, key);// Select Mode=DECRYPT
			System.out.println("Blowfish Decipher applied Succesfully");

		} catch (Exception e) {
			System.out.println("\n Error at reading file from encrypted file in Blowfish Decipher");
			e.printStackTrace();

		}
	}

	public static void BlowfishdoCrypto(int cipherMode, File inputFile, File outputFile, String keyString)
			throws Exception {

		String ALGORITHM = "Blowfish";// It's used in secret key
		Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);// Making S blocks
		Cipher cipher = Cipher.getInstance(ALGORITHM);// getting them in p arrays
		cipher.init(cipherMode, secretKey);// recreating the cipher or randomly set the IV yourself for each subsequent
											// message

		try {

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			// reading data in input file

			byte[] outputBytes = cipher.doFinal(inputBytes);
			// do final reset the internal state to the same IV you started with
			// We do encryption based on bytes in Blowfish

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);
			// we are writing the data in the file
			System.out.println(
					"\n Also Blowfish cipher as been written to encrypted.txt & Decipher has been written to decrypted.txt");
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {

			System.out.println("\n Error at writing file  in Blowfish Cipher / Decipher");
			e.printStackTrace();

		}

	}

}