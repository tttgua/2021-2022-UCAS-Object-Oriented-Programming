package model;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * 土地模型
 */
public class LandModel extends Tick implements Observer
{
    /**
     * 土地格子图
     */
    private Image landsIMG;
    /**
     * 空地
     */
    public final static int SPACE = 1;
    /**
     * 10点券
     */
    public final static int TICKET_10 = 2;
    /**
     * 30点券
     */
    public final static int TICKET_30 = 3;
    /**
     * 50点券
     */
    public final static int TICKET_50 = 4;
    /**
     * 商店
     */
    public final static int SHOP = 5;
    /**
     * 随机事件
     */
    public final static int NEWS = 6;
    /**
     * 医院
     */
    public final static int HOSPITAL = 7;
    /**
     * 监狱
     */
    public final static int PRISON = 8;
    /**
     * 银行
     * ——TODO
     */
    public final static int BANK = 9;
    /**
     * 起点
     */
    public final static int ORIGIN = 10;
    /**
     * 无建筑
     */
    public final static int NULL_SET = 0;
    /**
     * 医院位置信息
     */
    public static Point hospital = new Point(0, 0);
    /**
     * 监狱位置信息
     */
    public static Point prison = new Point(0, 0);

    public LandModel()
    {
        this.landsIMG = new ImageIcon("images/land/land.jpg").getImage();
    }

    public Image getLandsIMG()
    {
        return landsIMG;
    }

    protected int[][] land1 = {
            // 暂时只实现一个地图
            {ORIGIN, SPACE, SPACE, SPACE, SPACE, SPACE, SHOP, SPACE,
                    SPACE, SPACE, SPACE, SPACE, NEWS},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {NEWS, TICKET_10, TICKET_30, TICKET_50, HOSPITAL, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, PRISON, TICKET_50, TICKET_30, TICKET_10, NEWS},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {NEWS, SPACE, SPACE, SPACE, SPACE, SPACE, SHOP, SPACE,
                    SPACE, SPACE, SPACE, SPACE, TICKET_50}};
    protected int[][] land2 = {
            // for debug
            {ORIGIN, NEWS, NEWS, NEWS, NEWS, NEWS, NEWS, NEWS,
                    SPACE, SPACE, SPACE, SPACE, TICKET_30},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {NEWS, TICKET_10, TICKET_50, HOSPITAL, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, PRISON, TICKET_50, TICKET_10, NEWS},
            {SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
                    NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE},
            {TICKET_10, SPACE, SPACE, SPACE, SPACE, SPACE, SHOP, SPACE,
                    SPACE, SPACE, SPACE, SPACE, TICKET_50}};

    protected int[][] land;

    public int[][] getLand()
    {
        return land;
    }

    /**
     * 开始游戏设置
     */
    public void initGame()
    {
        land = land1;
    }

    public void update(long tick)
    {
        this.curTick = tick;
    }
}
