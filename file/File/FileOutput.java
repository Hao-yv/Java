package file.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutput {
    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        String filepath = "D:\\java\\project\\Test\\src\\file\\news.txt";

        try {
            String str = "Hello, world";
            fileOutputStream = new FileOutputStream(filepath);
            fileOutputStream.write(str.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
