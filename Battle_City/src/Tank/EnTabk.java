package Tank;

import assist.Shot;

import java.util.Vector;


public class EnTabk extends Tank implements Runnable {
    //在敌人坦克中使用 Vector 保存多个 Shot
    public Vector<Shot> shots = new Vector<>();
    // public boolean isLive = true; //坦克是否存在， 这段代码是敌我坦克共性， 直接利用父类继承
    // 增加成员， EnTank 可以得到敌人坦克的 Vector
    Vector<EnTabk> enTabks = new Vector<>();

    // 提供一个方法，可以将MyPanel 成员的 static Vector<EnTabk> enTabks = new Vector<>();
    public void setEnTabks(Vector<EnTabk> enTabks) {
        this.enTabks = enTabks;
    }

    // 编写方法， 判断当前敌人坦克是否和 enTanks 中的其他坦克发生重叠或碰撞
    public boolean isTouchEnTank() {

        // 判断当前敌人坦克（this）方向
        switch (this.getDirect()) {

            case 0 -> {
                // 让当前敌人坦克和其他所有敌人坦克比较
                for (int i = 0; i < enTabks.size(); i++) {
                    // 从vector 里面取出一个坦克
                    EnTabk enTabk = enTabks.get(i);
                    // 不能和自己比较
                    if (enTabk != this) {
                        // 如果敌人坦克方向为 上 或 下
                        // 若敌人坦克是 上 / 下 x的范围为：[enTank.getX(), enTank.getX() + 40]
                        //                   y的范围为：[enTank.getY(), enTank.getY() + 60]
                        if (enTabk.getDirect() == 0 || enTabk.getDirect() == 2) {
                            // 当前坦克左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 40
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右上角坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enTabk.getX()
                                    && this.getX() + 40 <= enTabk.getX() + 40
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 60) {
                                return true;
                            }

                        }

                        // 如果敌人坦克方向为 左 或 右
                        // 如果敌人坦克的位置是 左 / 右  x的范围为：[enTank.getX() ,enTank.getX() + 60]
                        //                          y的范围为：[enTank.getY() ,enTank.getY() + 40]
                        if (enTabk.getDirect() == 1 || enTabk.getDirect() == 3) {
                            // 当前坦克左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 60
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 40) {
                                return true;
                            }

                            // 当前坦克左上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 40 >= enTabk.getX()
                                    && this.getX() + 40 <= enTabk.getX() + 60
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
            } // 上

            case 1 -> {
                for (int i = 0; i < enTabks.size(); i++) {
                    // 从vector 里面取出一个坦克
                    EnTabk enTabk = enTabks.get(i);
                    // 不能和自己比较
                    if (enTabk != this) {
                        // 如果敌人坦克方向为 上 或 下
                        // 若敌人坦克是 上 / 下 x的范围为：[enTank.getX(), enTank.getX() + 40]
                        //                   y的范围为：[enTank.getY(), enTank.getY() + 60]
                        if (enTabk.getDirect() == 0 || enTabk.getDirect() == 2) {
                            // 当前坦克右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enTabk.getX()
                                    && this.getX() + 60 <= enTabk.getX() + 40
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enTabk.getX()
                                    && this.getX() + 60 <= enTabk.getX() + 40
                                    && this.getY() + 40 >= enTabk.getY()
                                    && this.getY() + 40 <= enTabk.getY() + 60) {
                                return true;
                            }

                        }

                        // 如果敌人坦克方向为 左 或 右
                        // 如果敌人坦克的位置是 左 / 右  x的范围为：[enTank.getX() ,enTank.getX() + 60]
                        //                          y的范围为：[enTank.getY() ,enTank.getY() + 40]
                        if (enTabk.getDirect() == 1 || enTabk.getDirect() == 3) {
                            // 当前坦克右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enTabk.getX()
                                    && this.getX() + 60 <= enTabk.getX() + 60
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 40) {
                                return true;
                            }

                            // 当前坦克右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enTabk.getX()
                                    && this.getX() + 60 <= enTabk.getX() + 60
                                    && this.getY() + 40 >= enTabk.getY()
                                    && this.getY() + 40 <= enTabk.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
            } // 下

            case 2 -> {
                for (int i = 0; i < enTabks.size(); i++) {
                    // 从vector 里面取出一个坦克
                    EnTabk enTabk = enTabks.get(i);
                    // 不能和自己比较
                    if (enTabk != this) {
                        // 如果敌人坦克方向为 上 或 下
                        // 若敌人坦克是 上 / 下 x的范围为：[enTank.getX(), enTank.getX() + 40]
                        //                   y的范围为：[enTank.getY(), enTank.getY() + 60]
                        if (enTabk.getDirect() == 0 || enTabk.getDirect() == 2) {
                            // 当前坦克右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enTabk.getX()
                                    && this.getX() + 60 <= enTabk.getX() + 40
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右上角坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enTabk.getX()
                                    && this.getX() + 40 <= enTabk.getX() + 60
                                    && this.getY() + 60 >= enTabk.getY()
                                    && this.getY() + 60 <= enTabk.getY() + 40) {
                                return true;
                            }
                        }

                        // 如果敌人坦克方向为 左 或 右
                        // 如果敌人坦克的位置是 左 / 右  x的范围为：[enTank.getX() ,enTank.getX() + 60]
                        //                          y的范围为：[enTank.getY() ,enTank.getY() + 40]
                        if (enTabk.getDirect() == 1 || enTabk.getDirect() == 3) {
                            // 当前坦克左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 60
                                    && this.getY() + 60 >= enTabk.getY()
                                    && this.getY() + 60 <= enTabk.getY() + 40) {
                                return true;
                            }

                            // 当前坦克右下角坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enTabk.getX()
                                    && this.getX() + 40 <= enTabk.getX() + 60
                                    && this.getY() + 60 >= enTabk.getY()
                                    && this.getY() + 60 <= enTabk.getY() + 40) {
                                return true;

                            }
                        }
                    }
                }
            } // 左

            case 3 -> { // 向右
                for (int i = 0; i < enTabks.size(); i++) {
                    // 从vector 里面取出一个坦克
                    EnTabk enTabk = enTabks.get(i);
                    // 不能和自己比较
                    if (enTabk != this) {
                        // 如果敌人坦克方向为 上 或 下
                        // 若敌人坦克是 上 / 下 x的范围为：[enTank.getX(), enTank.getX() + 40]
                        //                   y的范围为：[enTank.getY(), enTank.getY() + 60]
                        if (enTabk.getDirect() == 0 || enTabk.getDirect() == 2) {
                            // 当前坦克左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 40
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 60) {

                                return true;
                            }

                            // 当前坦克左下角坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 60
                                    && this.getY() + 40 >= enTabk.getY()
                                    && this.getY() + 40 <= enTabk.getY() + 40) {

                                return true;
                            }
                        }
                        // 如果敌人坦克方向为 左 或 右
                        // 如果敌人坦克的位置是 左 / 右  x的范围为：[enTank.getX() ,enTank.getX() + 60]
                        //                          y的范围为：[enTank.getY() ,enTank.getY() + 40]
                        if (enTabk.getDirect() == 1 || enTabk.getDirect() == 3) {
                            // 当前坦克左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 60
                                    && this.getY() >= enTabk.getY()
                                    && this.getY() <= enTabk.getY() + 40) {
                                return true;
                            }

                            // 当前坦克左下角坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enTabk.getX()
                                    && this.getX() <= enTabk.getX() + 60
                                    && this.getY() + 40 >= enTabk.getY()
                                    && this.getY() + 40 <= enTabk.getY() + 40) {
                                return true;
                            }
                        }

                    }
                }


            } // 右

        }

        return false;
    }

    public EnTabk(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            // 如果该坦克存活且对应子弹消亡， 就创建一颗子弹放入到shots集合中，并启动
            if (shots.size() == 0 && isLive) {
                Shot s = null;
                // 判断坦克方向，创建对应子弹
                switch (getDirect()) {
                    case 0 -> s = new Shot(getX() + 20, getY(), 0);
                    case 1 -> s = new Shot(getX() + 60, getY() + 20, 1);
                    case 2 -> s = new Shot(getX() + 20, getY() + 60, 2);
                    case 3 -> s = new Shot(getX(), getY() + 20, 3);
                }

                // 将对应方向子弹加入集合中
                shots.add(s);

                // 启动
                new Thread(s).start();
            }

            // 根据坦克方向来回移动
            switch (getDirect()) { // 让坦克保持一个方向走一定步数
                case 0 -> { // 向上
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouchEnTank()) {
                            moveUp();
                        }
                        // 休眠 50 毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                } // 向上

                case 1 -> { // 向右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnTank()) {
                            moveRight();
                        }
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } // 向右

                case 2 -> { // 向下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnTank()) {
                            moveDown();
                        }
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } // 向下

                case 3 -> { // 向左
                    for (int i = 0; i < 30; i += 2) {
                        if (getX() > 0 && !isTouchEnTank()) {
                            moveLeft();
                        }
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } // 向左
            }


            //随机使得坦克改变方向
            setDirect((int) (Math.random() * 4));

            //如果坦克死亡，对应线程结束
            if (!isLive) {
                break;
            }

        }
    }
}
