import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class DES {

	
	public static void DES_Encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void DES_Decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	public static void main(String[] args) {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Key for DES ");
			String key = sc.next(); // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream("original.txt");
			FileOutputStream fos = new FileOutputStream("encrypted.txt");
			encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("encrypted.txt");
			FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
			decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
