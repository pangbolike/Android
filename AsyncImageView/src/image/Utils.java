package image;

import java.security.MessageDigest;

/**
 * Created by asus on 2015/5/10.
 */
public class Utils {
    public static String MD5(String content){
        return hash(content,"MD5");
    }
    public static String SHA1(String content){
        return hash(content,"SHA-1");
    }
    public static String hash(String content,String algorithm){
        MessageDigest digest;
        byte[] result = null;
        try{
            digest = MessageDigest.getInstance(algorithm);
            result = digest.digest(content.getBytes("UTF-8"));

        }catch(Exception e){
            e.printStackTrace();
        }
        StringBuilder ans = new StringBuilder();
        for (byte b : result){
            ans.append(String.format("%02X",b));
        }
        return ans.toString();
    }
}
