import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;
import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Explode;
import com.tank.war.pojo.Tank;
import com.tank.war.pojo.TankFrame;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 12:03
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        //Tank tank = new Tank();
        Tank tank = new Tank(300,500, Direction.UP, Group.GOOD);
        //Bullet bullet = new Bullet(200,200,Direction.DOWN);
        TankFrame frame = new TankFrame.Builder().setWidth(600).setHeight(600).setTank(tank).build();
        //创建敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank enemy = new Tank(200 + i*80,200,Direction.DOWN,Group.BAD);
            //enemy.setEnemy(true);
            enemy.setTankFrame(frame);
            frame.getEnemyTanks().add(enemy);
        }
        tank.setTankFrame(frame);
        //死循环让Frame一直重画，否则只在服务启动时画一次，后续界面就无任何响应了
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

}
