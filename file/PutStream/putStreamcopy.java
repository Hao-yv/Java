package file.PutStream;

import java.io.*;

public class putStreamcopy {
    public static void main(String[] args) {

        //创建拷贝文件
        String filecopy = "D:\\java\\project\\Test\\src\\file\\PutStream\\copy.jpg";
        File file = new File(filecopy);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("文件" + file.getName() + "创建成功" + "\n" + "路径如下：" + file.getPath());


        String filepath = "D:\\java\\project\\Test\\src\\file\\PutStream\\favicon.jpg";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        int readData = 0;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filepath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filecopy));

            while ((readData = bufferedInputStream.read()) != -1){
                System.out.print((char)(readData));
                bufferedOutputStream.write(readData);
            }
            System.out.print("\n" + "文件拷贝成功，拷贝内容为如上");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bufferedOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
