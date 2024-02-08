package file.Reader;

import java.io.*;

public class buffercopy {
    public static void main(String[] args) {
        String srcFilePath = "D:\\java\\project\\Test\\src\\file\\Reader\\BufferFile.txt";
        String copyFilePath = "D:\\java\\project\\Test\\src\\file\\Reader\\copyFile.txt";

        File file = new File(copyFilePath);
        //创建文件
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("文件创建成功, 路径如下" + "\n"+ file.getPath());

        //读取, 写入源文件
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String line;

        try {
            bufferedReader = new BufferedReader(new FileReader(srcFilePath));
            bufferedWriter = new BufferedWriter(new FileWriter(copyFilePath));
            System.out.println("拷贝内容为：");
            while ((line = bufferedReader.readLine()) != null){
                bufferedWriter.write(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭流
            try {
                bufferedWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("文件拷贝成功, 拷贝文件路径为" + "\n" + file.getPath());

    }
}
