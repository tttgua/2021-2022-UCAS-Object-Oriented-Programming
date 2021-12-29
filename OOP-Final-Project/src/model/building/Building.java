package model.building;

import model.PlayerModel;

/**
 * 建筑抽象类
 */
public abstract class Building
{
    /**
     * 建筑拥有者
     */
    protected PlayerModel owner = null;

    /**
     * 建筑名称
     */
    protected String name;

    /**
     * 可购买性
     */
    protected boolean purchasable = false;

    /**
     * 过路费
     */
    protected int revenue;
    /**
     * 当前房屋等级
     */
    protected int level;

    /**
     * 坐标
     */
    protected int posX;
    protected int posY;
    /**
     * 最大等级
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
     * 获取房屋总价值
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
