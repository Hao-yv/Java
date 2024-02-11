package Tank;

public class Tank {
    private int x; //坦克横坐标
    private int y; //坦克纵坐标
    private int direct; //坦克方向， 0321 上下左右
    private int speed = 10; //控制坦克速度
    public boolean isLive = true; // 坦克是否存在

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }

    //坦克移动方法
    public void moveUp(){
        y -= speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
    }
    public void moveRight(){
        x += speed;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public void setDirect(int direct){
        this.direct = direct;
    }

    public int getDirect(){
        return direct;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }
}
