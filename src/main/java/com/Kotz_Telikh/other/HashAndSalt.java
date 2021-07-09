package com.Kotz_Telikh.other;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

public class HashAndSalt {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();


    public static String bytesToStringHex(byte[] bytes){
        char[] hexChars = new char[bytes.length * 2];
        for (int j=0; j < bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j*2] = hexArray[v >>> 4];
            hexChars[j* 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    // Δημιουργει ενα τυχαιο string 5 χαρακτηρων για να χρησιμοποιηθει ως salt
    public static String createSalt(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static byte[] hashPass(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}
