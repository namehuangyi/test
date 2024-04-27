import javax.swing.text.Style;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileInputStreamTest02 {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            in = new FileInputStream("G:\\file.txt");
            // 一次最多读取4个字节
            // byte[] bytes = new byte[10];
            /*int count = in.read(bytes);
            // String s = new String(bytes);
            // System.out.println(s); // abcd
            String s = new String(bytes, 0, count);
            System.out.println(count);
            System.out.println(s);
            count = in.read(bytes);
            // String s1 = new String(bytes);
            // System.out.println(s1); // efcd
            String s1 = new String(bytes, 0, count);
            System.out.println(count);
            System.out.println(s1);*/

            /*int count = 0;
            while ((count = in.read(bytes)) != -1 ){
                String s = new String(bytes, 0, count);
                System.out.print(s);
            }*/

            /*int count = in.read(bytes, 2, 5);
            System.out.println(count);
            for(byte b : bytes){
                System.out.println(b);
            }*/
            /*int read = in.read();
            in.skip(2);
            read = in.read();
            System.out.println(read);
            int available = in.available();
            System.out.println(available);*/
            byte[] bytes = new byte[in.available()];
            int count = in.read(bytes);
            String s = new String(bytes, 0, count);
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
