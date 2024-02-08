package file.objectStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class inPut {
    public static void main(String[] args) {
        //指定反序列化文件,反序列化顺序必须和序列化顺序相同，否则会运行异常
        String filePath = "D:\\java\\project\\Test\\src\\file\\objectStream\\DATA.dat";
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            System.out.println(objectInputStream.readChar());
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readDouble());
            System.out.println(objectInputStream.readObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
