package model.building;

import model.PlayerModel;

/**
 * ����������
 */
public abstract class Building
{
    /**
     * ����ӵ����
     */
    protected PlayerModel owner = null;

    /**
     * ��������
     */
    protected String name;

    /**
     * �ɹ�����
     */
    protected boolean purchasable = false;

    /**
     * ��·��
     */
    protected int revenue;
    /**
     * ��ǰ���ݵȼ�
     */
    protected int level;

    /**
     * ����
     */
    protected int posX;
    protected int posY;
    /**
     * ���ȼ�
     */
    protected int maxLevel;


    public Building(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isPurchasable()
    {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable)
    {
        this.purchasable = purchasable;
    }

    public boolean canUpLevel()
    {
        return this.level < maxLevel;
    }

    public int getPrice()
    {
        return 0;
    }

    public PlayerModel getOwner()
    {
        return owner;
    }

    public void setOwner(PlayerModel owner)
    {
        this.owner = owner;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getName()
    {
        return name;
    }

    public String getUpName()
    {
        return name;
    }


    /**
     * ��ȡ�����ܼ�ֵ
     */
    public int getAllPrice()
    {
        return 0;
    }

    public int getRevenue()
    {
        return revenue;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }


    public abstract int getEvent();
    public abstract int passEvent();
}	
