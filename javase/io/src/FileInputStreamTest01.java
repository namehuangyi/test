import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamTest01 {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            in = new FileInputStream("G:\\file.txt");
            while (true){
                // 一次读一个字节，返回字节本身
                int read = in.read();
                if (read == -1) break;
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
