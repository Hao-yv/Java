package assist;

import Tank.EnTabk;

import java.io.*;
import java.util.Vector;

public class Recorder {
    // 定义变量， 记录击毁敌方坦克数
    private static int allEnemyNum = 0;

    //定义I/O对象, 准备写数据到文件中
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;

    private static String recordFile = "D:\\java\\project\\Test\\Battle_City\\src\\myRecord.txt";

    // 定义敌人坦克，指向MyPanel 对象的Vector
    public static Vector<EnTabk> enTabks = null;

    // 定义一个Node 的Vector 用于保存敌人的nodes
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnTabks(Vector<EnTabk> enTabks) {
        Recorder.enTabks = enTabks;
    }

    // 返回文件记录
    public static String getRecordFile(){
        return recordFile;
    }

    // 增加一个方法，用于读取文件，恢复相关信息
    public static Vector<Node> getNodesAndAllEnemyNumRecord(){
        try {
            bufferedReader = new BufferedReader(new FileReader(recordFile));
            allEnemyNum = Integer.parseInt(bufferedReader.readLine()); // 将数据转为Integer
            // 循环读取文件，生成nodes集合
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String[] xyd= line.split(" ");

                Node node = new Node(Integer.parseInt(xyd[0]),
                         Integer.parseInt(xyd[1]),
                         Integer.parseInt(xyd[2]));

                nodes.add(node); // 放入nide队列

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return nodes;
    }

    // 当游戏退出时， 将allEnemyNum 保存到 recoedFile
    public static void keepRecord() {

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordFile));
            bufferedWriter.write(allEnemyNum + "\r\n"); // 保存敌人坦克的坐标和方向

            // 遍历敌人坦克， 保存存活坦克坐标
            // 定义一个属性，通过set方法得到敌人坦克
            for (int i = 0; i < enTabks.size(); i++) {
                // 取出敌人坦克
                EnTabk enTabk = enTabks.get(i);
                if(enTabk.isLive){ // 判断敌人坦克是否存活
                    // 保存该坦克的信息
                    String record = enTabk.getX() + " " + enTabk.getY() + " " + enTabk.getDirect();
                    // 将信息写入文件
                    bufferedWriter.write(record + "\r\n");

                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int getAllEnemyNum() { // get击毁敌方坦克数
        return allEnemyNum;
    }

    public static void setAllEnemyNum(int allEnemyNum) { // set 击毁敌方坦克数
        Recorder.allEnemyNum = allEnemyNum;
    }

    // 当我方击毁一辆坦克 allEnemyNum++
    public static void addAllEnemyNum(){
        Recorder.allEnemyNum++;
    }
}
