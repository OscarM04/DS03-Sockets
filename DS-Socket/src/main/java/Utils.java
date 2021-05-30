import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {

    public static String encryptMD5InHex(String message){
        // Encrypt message using MD5
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest( message.getBytes(UTF_8));

        //Convert encrypted message in Hexadecimal
        StringBuilder converterHex = new StringBuilder();
        for (byte b : result) {
            converterHex.append(String.format("%02x", b));
        }
        return converterHex.toString();
    }

    public static String decodeBase64String(String string){
        byte[] decodedBytes = Base64.getDecoder().decode(string);
        return new String(decodedBytes);
    }
}
