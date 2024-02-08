package file.objectStream;

import java.io.*;

public class output {
    public static void main(String[] args) {

        //创建新文件
        String filePath = "D:\\java\\project\\Test\\src\\file\\objectStream\\DATA.dat";
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("文件" + file.getName() + "创建成功,路径如下:" + "\n" + file.getPath());

        //序列化文件
        ObjectOutput objectOutput = null;
        try {
            objectOutput = new ObjectOutputStream(new FileOutputStream(filePath));
            objectOutput.writeChar('A');
            objectOutput.writeInt(10);
            objectOutput.writeDouble(20.0);
            objectOutput.writeObject(new Dog("小黄", 5, "黄"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                objectOutput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("文件序列化写入成功");
    }
}


class Dog implements Serializable{
    private String name;
    private int age;
    transient String colour;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Dog(String name, int age, String colour) {
        this.name = name;
        this.age = age;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", colour=" + colour +
                '}';
    }
}