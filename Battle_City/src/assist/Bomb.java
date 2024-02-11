package assist;

/**
 * @author HaoyvZhang
 */
public class Bomb {
    public int x, y;               // 爆炸位置
    public int life = 9;           //爆炸生命周期
    public boolean isLive = true;  // 是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 生命值减少
    public void lifeDown() {// 配合图片实现爆炸效果
        if (life > 0) {
            life--;
        }else {
            isLive = false;
        }
    }
}
