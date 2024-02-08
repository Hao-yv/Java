package file.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileExcise {
    public static void main(String[] args) throws IOException {
        String filepath = "D:\\java\\project\\Test\\src\\file\\news.txt";
        File file = new File(filepath);
        file.createNewFile();

        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        String str = "Hello, world";
        fileOutputStream.write(str.getBytes());


        //文件输出
        FileInputStream fileInputStream = new FileInputStream(filepath);
        int readData = 0;
        while ((readData = fileInputStream.read()) != -1){
            System.out.println((char) (readData));
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
