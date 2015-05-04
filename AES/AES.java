import javax.crypto.KeyGenerator;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by alexpang on 2015/2/6.
 */
public class AES {
    static Cipher cipher = null;
    public static byte[] Encrypt(String content,String password)
    {
        try{
            if (cipher == null){
                cipher = Cipher.getInstance("AES");
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String Decrypt(byte[] concent,String password)
    {
        try{
            if (cipher == null){
                cipher = Cipher.getInstance("AES");
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(concent);
            return new String(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
