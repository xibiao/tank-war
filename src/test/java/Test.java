import com.tank.war.enums.Direction;
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
        Tank tank = new Tank(200,200, Direction.DOWN);
        TankFrame frame = new TankFrame.Builder().setWidth(1200).setHeight(800).setTank(tank).build();
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
