import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class DES {

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];// 64 bits keys
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

	public void DES_Encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		try {

			encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);// Select Mode =ENCRYPT
			System.out.println("DES Encryption Done");
		} catch (Exception e) {
			System.out.println("Error at Reading original.txt");
		}
	}

	public void DES_Decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		try {
			encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);// Select Mode =DECRYPT
			System.out.println("DES Decryption Done");
		} catch (Exception e) {
			System.out.println("Error at Reading encrypted.txt");

		}

	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());// encryption keys using java crypto library
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");// creating Instance
		SecretKey desKey = skf.generateSecret(dks);// creating keys
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);// Select Mode = Encrypt
			CipherInputStream cis = new CipherInputStream(is, cipher);// for inputing data
			doCopy(cis, os);// copying the data into file
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);// select mode =Decrypt
			CipherOutputStream cos = new CipherOutputStream(os, cipher);// for outputing data
			doCopy(is, cos);// copying the data into file
		}
	}

}
