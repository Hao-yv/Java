package file.File;

import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {
        create01();
        create02();
        create03();


    }

    public static void create01(){
        String filePath = "D:\\java\\project\\Test\\src\\file\\news.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("创建成功");
    }

    public static void create02(){
        File parentFile = new File("D:\\java\\project\\Test\\src\\file");
        String fileName = "news2.txt";
        File file = new File(parentFile, fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("创建2成功");
    }

    public static void create03(){
        String parentPath = "D:\\";
        String fileName = "java\\project\\Test\\src\\file\\new3.txt";
        File file = new File(parentPath, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("创建3成功");
    }
}

