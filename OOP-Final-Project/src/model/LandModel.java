package model;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * ����ģ��
 */
public class LandModel extends Tick implements Observer
{
    /**
     * ���ظ���ͼ
     */
    private Image landsIMG;
    /**
     * �յ�
     */
    public final static int SPACE = 1;
    /**
     * 10��ȯ
     */
    public final static int TICKET_10 = 2;
    /**
     * 30��ȯ
     */
    public final static int TICKET_30 = 3;
    /**
     * 50��ȯ
     */
    public final static int TICKET_50 = 4;
    /**
     * �̵�
     */
    public final static int SHOP = 5;
    /**
     * ����¼�
     */
    public final static int NEWS = 6;
    /**
     * ҽԺ
     */
    public final static int HOSPITAL = 7;
    /**
     * ����
     */
    public final static int PRISON = 8;
    /**
     * ����
     * ����TODO
     */
    public final static int BANK = 9;
    /**
     * ���
     */
    public final static int ORIGIN = 10;
    /**
     * �޽���
     */
    public final static int NULL_SET = 0;
    /**
     * ҽԺλ����Ϣ
     */
    public static Point hospital = new Point(0, 0);
    /**
     * ����λ����Ϣ
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
            // ��ʱֻʵ��һ����ͼ
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
     * ��ʼ��Ϸ����
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
