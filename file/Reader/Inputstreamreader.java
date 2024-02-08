package file.Reader;

import java.io.*;

public class Inputstreamreader {
    public static void main(String[] args) {
        String filePath = "D:\\java\\project\\Test\\src\\file\\Reader\\chaos.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));
            String readData = bufferedReader.readLine();
            System.out.println(readData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
