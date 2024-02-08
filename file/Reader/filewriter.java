package file.Reader;

import java.io.*;

public class filewriter {
    public static void main(String[] args) {
        String filePath = "D:\\java\\project\\Test\\src\\file\\Reader\\note.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("创建成功");

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write("Hello, World", 0, 3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileWriter.close();
                System.out.println("写入结束");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileReader fileReader = null;
        int data = 0;
        try {
            fileReader = new FileReader(filePath);
            System.out.println("读取写入内容为：");
            while ((data = fileReader.read()) != -1 ){
                System.out.print((char) data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
