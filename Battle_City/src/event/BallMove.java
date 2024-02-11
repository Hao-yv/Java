package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author HaoyvZhang
 */
public class BallMove extends JFrame{
    MyPanel myPanel = null;

    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    //构造器
    public BallMove(){
        myPanel = new MyPanel();
        this.add(myPanel);
        this.setSize(400, 300);
        this.addKeyListener(myPanel); //窗口JFrame 对象可以监听键盘实践， 即哪个键被触发
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}

//用来画小球
//KeyListener 是一个监听器接口， 用于监听键盘时间
class MyPanel extends JPanel implements KeyListener {
    //为了让小球移动，
    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20); //填充小球，默认黑色
    }

    //有字符输出，该方法触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当某个键被按下， 该方法触发
    @Override
    public void keyPressed(KeyEvent e) {

        //java会给每一个键分配一个 code 值，利用 KeyEvent.VK_ 方法实现
        if(e.getKeyCode() == KeyEvent.VK_S){// 球向下移动
            y++;
           // System.out.println(y);
        }else if (e.getKeyCode() == KeyEvent.VK_W){ // 球向上移动
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_D){ // 球向右移动
            x++;
        }else if (e.getKeyCode() == KeyEvent.VK_A){ // 球向左移动
            x--;
        }

        //面板重绘
        this.repaint();

    }

    //当某个键被释放， 该方法触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}