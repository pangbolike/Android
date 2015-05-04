import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by alexpang on 2015/4/14.
 */
public class SecurityUtils {
    private static final char[] hexdigits = new char[]{
            '0', '1', '2', '3', '4',//
            '5', '6', '7', '8', '9',//
            'a', 'b', 'c', 'd', 'e',//
            'f'};
    public static String encrypt(File file)
    {
        return encrypt(file,"MD5");
    }
    public static String encrypt(File file,String algorithm)
    {
        if (file == null || !file.exists() || !file.isFile())
        {
            return null;
        }
        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            FileInputStream is = new FileInputStream(file);
            int count;
            byte[] buf = new byte[8192];
            while((count = is.read(buf)) > 0)
            {
                digest.update(buf,0,count);
            }
            return bytesToHexStr(digest.digest());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String bytesToHexStr(byte[] byteBuf) {
        if (byteBuf == null || byteBuf.length == 0) {
            return null;
        }
        char[] buf = new char[2 * byteBuf.length];
        for (int i = 0; i < byteBuf.length; i++) {
            byte b = byteBuf[i];
            buf[2 * i + 1] = hexdigits[b & 0xF];
            b = (byte) (b >>> 4);
            buf[(2 * i)] = hexdigits[b & 0xF];
        }
        return new String(buf);
    }
}
