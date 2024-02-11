package assist;


public class Shot implements Runnable{
    public int x; // 子弹 x 坐标
    public int y; // 子弹 y 坐标
    public int direct; // 子弹方向
    public int speed = 10; // 子弹速度
    public boolean isLive = true; //子弹是否存在

    //构造射击行为
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }




    @Override
    public void run() {
        while (true){

            //休眠 50 毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //根据方向改变子弹 x, y 坐标
            switch (direct){
                case 0 -> y -= speed; // 子弹方向向上
                case 1 -> x += speed; // 子弹方向向右
                case 2 -> y += speed; // 子弹方向向下
                case 3 -> x -= speed; // 子弹方向向左
            }

            //System.out.println("当前方向为：" + x + " , " + y);

            //当子弹移动到面板边界时，子弹销毁
            //当子弹生命结束时，子弹销毁
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750) || !isLive){
                isLive = false;
                 //System.out.println("子弹销毁");
                break;
            }
        }
    }
}
