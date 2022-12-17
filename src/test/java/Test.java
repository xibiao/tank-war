import com.tank.war.config.PropertyCfg;
import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;
import com.tank.war.pojo.*;
import com.tank.war.responsibilityChain.*;
import com.tank.war.strategy.DefaultFireStrategy;
import com.tank.war.strategy.FireContext;
import com.tank.war.strategy.FireStrategy;
import com.tank.war.strategy.FourDirFireStrategy;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 12:03
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        init();

        //frame翻译为框架，可以理解为画框，frame通过paint方法在画框中作画(就是把坦克、子弹等物体画出来)
        TankFrame frame = new TankFrame.Builder().setWidth(600).setHeight(600).setGameModel(GameModel.getInstance()).build();
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

    private static void init() {
        //创建我方主坦克
        FireStrategy fourDirFireStrategy = new FourDirFireStrategy();
        Tank tank = new Tank(300,500, Direction.UP, Group.GOOD, new FireContext(fourDirFireStrategy));
        //创建碰撞器链和碰撞器实例
        ColliderChain chain = new ColliderChain();
        Collider btCollider = new BulletTankCollider();
        Collider ttCollider = new TankTankCollider();
        Collider bwCollider = new BulletWallCollider();
        Collider twCollider = new TankWallCollider();
        chain.add(btCollider).add(ttCollider).add(bwCollider).add(twCollider);
        //创建游戏模型实例，所有数据(坦克、子弹、能量块等物体)都在该实例中维护，对外(Frame)提供统一操作入口
        GameModel gameModel = GameModel.getInstance();
        gameModel.setTank(tank);
        gameModel.setChain(chain);
        tank.setGameModel(gameModel);
        //创建敌方坦克
        DefaultFireStrategy defaultFireStrategy = new DefaultFireStrategy();
        int initNum = Integer.parseInt(PropertyCfg.getValue("tank.initTankNum"));
        for (int i = 0; i < initNum; i++) {
            Tank enemy = new Tank(100 + i*100,200,Direction.DOWN,Group.BAD, new FireContext(defaultFireStrategy));
            enemy.setGameModel(gameModel);
            gameModel.add(enemy);
        }
        //创建几堵墙
        new Wall(300,150,150,40);
        //new Wall(200,400,150,40);
        new Wall(100,300,40,150);
        new Wall(400,300,40,150);
    }

}
