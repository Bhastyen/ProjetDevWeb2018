package encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;




public class cryptText {

	public static String crypt(String text){
		try {
			// genere keygen
			KeyGenerator keygen;
			keygen = KeyGenerator.getInstance("DES");
			keygen.init(56);
			Key key = keygen.generateKey();
		
		    // genere cipher
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
            
			// renvoie la chaine cryptee 			
			return new String(cipher.doFinal(text.getBytes()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
}
