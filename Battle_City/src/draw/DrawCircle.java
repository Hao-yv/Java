package draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{//JFrame对应窗口

    //定义面板
    private MyPanek mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }

    //设置构造器
    public DrawCircle(){
        //初始化面板
        mp = new MyPanek();
        //把面板放入窗口
        this.add(mp);
        //设置窗口大小
        this.setSize(400, 300);
        //当窗口退出时，程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口显示
        this.setVisible(true);
    }
}

//定义MyPanel, 继承JPanel类以用于绘画图形
class MyPanek extends JPanel{
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);//调用父类方法完成初始化
        g.drawOval(10,10,100, 100);
    }
}