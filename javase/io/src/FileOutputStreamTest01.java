import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest01 {
    public static void main(String[] args) {
        try {
            // 创建文件字节输入流对象,false表示清空原来的内容
            FileOutputStream out = new FileOutputStream("G:\\file.txt1", false);
            /*out.write(97);
            out.write(98);
            out.write(99);
            out.write(100);*/
            byte[] bytes = {97, 98, 99, 100};
            out.write(bytes);
            out.write(bytes, 0, 2);
            // 转换成byte数组
            byte[] bs = "动力节点老杜".getBytes();
            out.write(bs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
