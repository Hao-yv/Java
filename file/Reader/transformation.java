package file.Reader;

import java.io.*;

public class transformation {
    public static void main(String[] args) {

        //创建文件
        String FilePath = "D:\\java\\project\\Test\\src\\file\\Reader\\trans.txt";
        File file = new File(FilePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //写入混乱编码文字
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(FilePath), "gbk");
            outputStreamWriter.write("这是一段练习文本，没什么实在意义");
            System.out.println("文本写入成功");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //读取混乱编码文字
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(FilePath));
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.print(readLine);
            }
            System.out.println("\n" + "转码前文件读取成功， 如上");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //文件转码
        String readNew = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath), "gbk"));
            while ((readNew = bufferedReader.readLine()) != null){
                System.out.print(readNew);
            }
            System.out.println("\n" + "转码后文件内容如上");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
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

        //新创建文件改写
        String copyPath = "D:\\java\\project\\Test\\src\\file\\Reader\\copyNew.txt";
        file = new File(copyPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("新文件\"" + file.getName() + "\"创建成功");

        //将先前改写文件写入新文件中
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath), "gbk"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(copyPath), "UTF-8");
            while ((readNew = bufferedReader.readLine()) != null){
                outputStreamWriter.write(readNew);
            }
            System.out.println("转码后文本内容写入成功, 路径如下" + "\n" + file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                outputStreamWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
