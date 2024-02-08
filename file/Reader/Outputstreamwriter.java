package file.Reader;

import java.io.*;

public class Outputstreamwriter {
    public static void main(String[] args) {

        //创建文件
        String filePath = "D:\\java\\project\\Test\\src\\file\\Reader\\writer.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //写入编码文件
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath), "gbk");
            outputStreamWriter.write("好好学习，天天向上");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
