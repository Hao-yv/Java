package assist;

import assist.Recorder;
import draw.MyPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author HaoyvZhang
 * 前端实现
 */
public class TankGame extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    Scanner sc = new Scanner(System.in);

    public TankGame(){
        System.out.println("请输入选择(1: 新游戏 2: 继续上局)");
        String key = sc.nextLine();
        mp = new MyPanel(key);
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1300, 787);
        this.addKeyListener(mp);//监听键盘输入
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭时程序结束
        this.setVisible(true);//显示窗口

        // 在 JFrame 相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.out.println("游戏进度已保存");
                System.exit(0);
            }
        });
    }

}
