package file.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class filereader {
    public static void main(String[] args) {
        String filepath = "D:\\java\\project\\Test\\src\\file\\Reader\\story.txt";
        File file = new File(filepath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("创建成功");

        FileReader fileReader = null;
        int data = 0;
        try {
            fileReader = new FileReader(filepath);
            while ((data = fileReader.read()) != -1){
                System.out.println((char) data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
