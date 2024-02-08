package file.Reader;

import java.io.*;

public class CodeQuestion {
    public static void main(String[] args) {
        //创建文件
        String filePath = "D:\\java\\project\\Test\\src\\file\\Reader\\chaos.txt";
//        File file = new File(filePath);
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("文件创建成功");
//
//        //向文件写入内容
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(filePath));
//            bufferedWriter.write("张昊宇");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                bufferedWriter.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println("文本写入成功");

        //使用BufferedReader读取乱码对象
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("文本读取成功");
    }
}
