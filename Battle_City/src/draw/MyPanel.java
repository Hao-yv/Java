package draw;

import Tank.EnTabk;
import Tank.Hero;
import Tank.Tank;
import assist.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//为了让 Panel 不停重绘子弹，需要设置守护线程
public class MyPanel extends JPanel implements KeyListener, Runnable {

    static Hero hero = null;
    //定义敌人坦克
    static Vector<EnTabk> enTabks = new Vector<>();
    // 定义存放Node对象的Vector，用于恢复敌人坦克坐标
    Vector<Node> nodes = new Vector<>();
    // 定义队列存放炸弹,当子弹击中坦克时，产生爆炸
    static Vector<Bomb> bombs = new Vector<>();

    //定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {


        // 将MyPanel 设置给recorder对象
        Recorder.setEnTabks(enTabks);

        hero = new Hero(850, 100); //初始化自己坦克坐标方向
        hero.setSpeed(10); //设置坦克速度
        int enTankSize = 6; //初始化敌人坦克数量

        switch (key){
            case "1" ->{
                for (int i = 0; i < enTankSize; i++) {
                // 创建敌人坦克
                EnTabk enTabk = new EnTabk((100 * (i + 1)), 0);
                // 将enTanks 设置给 enTank
                enTabk.setEnTabks(enTabks);

                enTabk.setDirect(2);//设置坦克方向
                new Thread(enTabk).start(); // 启动坦克线程

                Shot shot = new Shot(enTabk.getX() + 20, enTabk.getY() + 60, enTabk.getDirect());// 给enemyTank加入一颗子弹
                enTabk.shots.add(shot); // 将子弹加入队列中
                new Thread(shot).start(); // 启动子弹线程

                enTabks.add(enTabk); //将坦克加入
            }
            } // 开始新游戏

            case "2" ->{
                nodes = Recorder.getNodesAndAllEnemyNumRecord();
                for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i); // 取出恢复点位
                // 创建敌人坦克
                EnTabk enTabk = new EnTabk(node.getX(), node.getY());
                // 将enTanks 设置给 enTank
                enTabk.setEnTabks(enTabks);

                enTabk.setDirect(node.getDirect());//设置坦克方向
                new Thread(enTabk).start(); // 启动坦克线程

                Shot shot = new Shot(enTabk.getX() + 20, enTabk.getY() + 60, enTabk.getDirect());// 给enemyTank加入一颗子弹
                enTabk.shots.add(shot); // 将子弹加入队列中
                new Thread(shot).start(); // 启动子弹线程

                enTabks.add(enTabk); //将坦克加入
            }
            } // 继续上局游戏

            default -> System.out.println("输入有误，重新输入");

        }


        // 初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("image/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("image/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("image/bomb_3.gif"));

        // 播放指定音乐
        new AePlayWave("D:\\java\\project\\Test\\Battle_City\\src\\music.wav").start();

    }

    // 编写方法， 显示击毁敌方坦克数量
    public void showInfo(Graphics g){
        //画出玩家总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0); // 绘制出一个敌方坦克
        g.setColor(Color.BLACK); // 重新设置字体颜色为黑
        g.drawString(Recorder.getAllEnemyNum() + "", 1080, 100); // 在指定位置写入击毁敌方坦克数

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        showInfo(g);

        // 判断是否需要画出我方坦克
        if(hero.isLive && hero != null) {
            //利用封装方法绘制坦克
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //画出 hero 子弹
        //如果存在子弹且子弹存活
//        if (hero.shot != null && hero.shot.isLive){
//            //子弹坐标为：x, y, 长为1， 宽为1
//            System.out.println("子弹被重绘");
//            g.draw3DRect(hero.shot.x,hero.shot.y, 5, 5, false);
//
//        }

        // 将 hero 子弹集合遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
                g.draw3DRect(shot.x, shot.y, 5, 5, false);
            } else { // 如果该shot对象已经无效， 就从shots 集合中拿掉
                hero.shots.remove(shot);
            }
        }

        // 如果集合中有炸弹就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前bomb对象life值去绘制对应效果
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }

            // 让炸弹生命值减少
            bomb.lifeDown();
            // 如果bomb life 减少为0， 则从 Bombs 集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌人坦克
        for (int i = 0; i < enTabks.size(); i++) {
            //取出坦克
            EnTabk enTabk = enTabks.get(i);

            // 判断当前坦克是是否存活，如果存活就取出并画出
            if (enTabk.isLive) {
                drawTank(enTabk.getX(), enTabk.getY(), g, enTabk.getDirect(), 0);
                //画出敌人坦克子弹
                for (int j = 0; j < enTabk.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enTabk.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 5, 5, false);
                    } else {
                        enTabk.shots.remove(shot);
                    }

                }
            }
//             else { //如果当前坦克死亡就从队列中删除
//                enTabks.remove(enTabk);
//            }
        }

    }

    //编写方法，画出坦克

    /**
     * @param x      坦克左上角 x 坐标
     * @param y      坦克左上角 y 坐标
     * @param g      画笔
     * @param direct 坦克方向(上， 下， 左， 右)
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0 -> g.setColor(Color.cyan); // 敌方坦克
            case 1 -> g.setColor(Color.yellow); // 我方坦克
        }

        //根据坦克方向设置坦克
        switch (direct) {
            case 0 -> {//绘制上方向坦克
                g.fill3DRect(x, y, 10, 60, false); //绘制左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //绘制右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //绘制右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); //绘制车顶
                g.fillOval(x + 10, y + 20, 20, 20); //绘制炮桶
                g.drawLine(x + 20, y + 30, x + 20, y); //绘制炮管
            }

            case 1 -> {//绘制右方向坦克
                g.fill3DRect(x, y, 60, 10, false); //绘制左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //绘制右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //绘制车身主体
                g.fillOval(x + 20, y + 10, 20, 20); //绘制车顶
                g.fillOval(x + 20, y + 10, 20, 20); //绘制炮桶
                g.drawLine(x + 30, y + 20, x + 60, y + 20); //绘制炮管
            }

            case 2 -> {//绘制下方向坦克
                g.fill3DRect(x, y, 10, 60, false); //绘制左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //绘制右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //绘制右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); //绘制车顶
                g.fillOval(x + 10, y + 20, 20, 20); //绘制炮桶
                g.drawLine(x + 20, y + 30, x + 20, y + 60); //绘制炮管
            }

            case 3 -> {//绘制左方向坦克
                g.fill3DRect(x, y, 60, 10, false); //绘制左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //绘制右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //绘制车身主体
                g.fillOval(x + 20, y + 10, 20, 20); //绘制车顶
                g.fillOval(x + 20, y + 10, 20, 20); //绘制炮桶
                g.drawLine(x + 30, y + 20, x, y + 20); //绘制炮管
            }

            default -> System.out.println("暂时没有处理");
        }
    }

    //判断我方子弹是否击中敌人坦克时，需要将子弹集合中所有子弹取出，和敌方所有坦克做判断
    public static void hitEntank(){
        //遍历我方子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive) {//如果当前子弹不为空且还存活
                // 遍历敌方所有坦克
                for (int i = 0; i < enTabks.size(); i++) {
                    EnTabk enTabk = enTabks.get(i); // 取出所有坦克，依次判断
                    hitTank(shot, enTabk); // 判断是否击中
                }
            }
        }

    }

    public static void hitHero(){
        // 遍历所有敌人坦克
        for (int i = 0; i < enTabks.size(); i++){
            // 取出敌人坦克
            EnTabk enTabk = enTabks.get(i);
            // 遍历该坦克中的所有子弹
            for (int j = 0; j < enTabk.shots.size(); j++) {
                // 取出所有子弹
                Shot shot = enTabk.shots.get(j);
                // 判断 shot 是否击中我方坦克
                if(hero.isLive && shot.isLive){
                    hitTank(shot, hero);
                }
            }
        }
    }

    public static void hitTank(Shot shot, Tank enTabk) {
        //判断是否击中坦克
        switch (enTabk.getDirect()) {
            case 0, 2 -> { //坦克方向向上或向下
                if (shot.x > enTabk.getX() && shot.x < enTabk.getX() + 40 && shot.y > enTabk.getY() && shot.y < enTabk.getY() + 60) {
                    shot.isLive = false; // 当子弹进入敌方坦克范围时，子弹消失
                    enTabk.isLive = false; //当子弹进入敌方坦克范围， 地方坦克消失
                    enTabks.remove(enTabk); // 删除死亡坦克
                    if(enTabk instanceof EnTabk) {
                        Recorder.addAllEnemyNum(); // 当击毁一个敌方坦克，击毁数 +1
                    }
                    // 创建Bomb对象加入集合中
                    Bomb bomb = new Bomb(enTabk.getX(), enTabk.getY());
                    bombs.add(bomb);
                }
            }
            case 1, 3 -> {
                if (shot.x > enTabk.getX() && shot.x < enTabk.getX() + 60 && shot.y > enTabk.getY() && shot.y < enTabk.getY() + 40) {
                    shot.isLive = false; // 当子弹进入敌方坦克范围时，子弹消失
                    enTabk.isLive = false; //当子弹进入敌方坦克范围， 地方坦克消失
                    enTabks.remove(enTabk); // 删除死亡坦克
                    if(enTabk instanceof EnTabk) {
                        Recorder.addAllEnemyNum(); // 当击毁一个敌方坦克，击毁数 +1
                    }

                    // 创建Bomb对象加入集合中
                    Bomb bomb = new Bomb(enTabk.getX(), enTabk.getY());
                    bombs.add(bomb);
                }
            }
        }

    }

    //有字符输出时，该方法触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当某个键被按下时， 该方法触发
    @Override
    public void keyPressed(KeyEvent e) {
        //改变坦克方向
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //实现按下 W 时， 坦克方向向上
            hero.setDirect(0);//
            //修改坦克坐标 y -= 1
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            //实现按下 A 时， 坦克方向向左
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //实现按下 S 时，坦克方向向下
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            //实现按下 D 时， 坦克放下向右
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        }


        //如果用户按下 J ，就发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            hero.shotEemyTank();
        }

        //面板重绘
        this.repaint();
    }

    //当某个键被释放时， 该方法触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    //运行线程
    @Override
    public void run() {//每隔100毫秒， 重绘区域

        while (true) {
            //休眠 100 毫秒
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            //判断是否击中坦克
//            if (hero.shot != null && hero.shot.isLive) {//如果当前子弹不为空且还存活
//                // 遍历敌方所有坦克
//                for (int i = 0; i < enTabks.size(); i++) {
//                    EnTabk enTabk = enTabks.get(i); // 取出所有坦克，依次判断
//                    hitTank(hero.shot, enTabk); // 判断是否击中
//                }
//            }

            // 多个子弹判断是否击中坦克
            hitEntank();

            // 判断敌人子弹是否击中我方
            hitHero();

            this.repaint();
        }
    }
}
