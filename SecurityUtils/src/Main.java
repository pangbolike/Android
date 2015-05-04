import java.io.File;
import java.io.FileInputStream;

/**
 * Created by alexpang on 2015/4/14.
 */
public class Main {
    public static void main(String args[]){
        File file = new File("D:\\1.txt");
        try{
            FileInputStream is = new FileInputStream(file);
            System.out.println(SecurityUtils.encrypt(file).toUpperCase());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
