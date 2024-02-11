package Tank;

import assist.Shot;

import java.util.Vector;

public class Hero extends Tank{
    // 定义一个 Shoot 对象, 表示一个射击（线程）
    public Shot shot = null;

    // 可以发射多颗子弹
    public Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEemyTank(){

        // 控制发射指定数量的子弹
        if(shots.size() == 5) {
            return;
        }

        // 创建 Shot 对象， 根据当前 Hero 对象的位置和方向来创建 Shot
        switch (getDirect()){
            case 0 -> shot = new Shot(getX() + 20, getY(), 0);//向上发射子弹
            case 1 -> shot = new Shot(getX() + 60, getY() + 20, 1);//向右发射子弹
            case 2 -> shot = new Shot(getX() + 20, getY() + 60, 2);//向下发射子弹
            case 3 -> shot = new Shot(getX(), getY() + 20, 3);//向左发射子弹
        }

        //将新建的子弹放入集合中
        shots.add(shot);
        //启动 Short 线程
        new Thread(shot).start();
    }
}
