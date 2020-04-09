import java.util.*;


class CaesarCipher 
{ 

    // Encrypts text using a shift od s 
    public static String encrypt(String text, int key)
    //text is the String to encrypt & key is keyword 
    { 
        StringBuffer result= new StringBuffer(); 
  
        for (int i=0; i<text.length(); i++) 
        { 
             //Case for Uppercase alphabets 
            if (Character.isUpperCase(text.charAt(i))) 
            { 
                char ch = (char)(((int)text.charAt(i) + 
                                  key - 65) % 26 + 65); 
                result.append(ch); 
            } 
            //Case for Lowercase alphabets
            else
            { 
                char ch = (char)(((int)text.charAt(i) + 
                                  key - 97) % 26 + 97); 
                result.append(ch); 
            } 
        } 
        System.out.println("Encrypted Message = " + result);
        return result.toString();

    } 

    public static void decrypt(String message, int key) 
    {
        char ch;
        String decryptedMessage="";
		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			//for Lower case aplhabets
			if(ch >= 'a' && ch <= 'z'){
	            ch = (char)(ch - key);
	            
	            if(ch < 'a'){
	                ch = (char)(ch + 'z' - 'a' + 1);
	            }
	            
	            decryptedMessage += ch;
            }
            //for Upper alphabets 
	        else if(ch >= 'A' && ch <= 'Z'){
	            ch = (char)(ch - key);
	            
	            if(ch < 'A'){
	                ch = (char)(ch + 'Z' - 'A' + 1);
	            }
	            
	            decryptedMessage += ch;
	        }
	        else {
	        	decryptedMessage += ch;
	        }
		}
		
		System.out.println("Decrypted Message = " + decryptedMessage);
	}

		
		
  
    // Driver code 
    public static void main(String[] args) 
    { 
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Enter Text to encrypt & it's Key");
        String text= sc.next();
        int key=sc.nextInt();
        System.out.println("Text  : " + text); 
        System.out.println("Shift : " + key);
        System.out.println("\n Encryption");
        String encrypt_text=encrypt(text, key);
        System.out.println("\n Decryption");
        decrypt(encrypt_text, key);
    } 
}