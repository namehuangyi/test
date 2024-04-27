import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamTest {
    public static void main(String[] args) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("G:\\BaiduNetdiskDownload\\资源\\springmvc\\document-md文件请安装typora工具打开\\第1章 初识SpringMVC.md");
            byte[] bytes = new byte[1024];
            out = new FileOutputStream("G:\\第1章 初识SpringMVC.md");
            int count = 0;
            while ((count = in.read(bytes)) != -1){
                out.write(bytes, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
