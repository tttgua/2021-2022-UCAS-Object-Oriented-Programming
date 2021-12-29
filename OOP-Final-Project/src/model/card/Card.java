package model.card;

import model.PlayerModel;
/**
 *  卡片模型抽象类
 */
public abstract class Card
{
    /**
     *  卡片英文名字
     */
    protected String name;
    /**
     *  卡片中文名字
     */
    protected String chName;
    /**
     *  卡片功能简介
     */
    protected String info;
    /**
     *  拥有者
     */
    protected PlayerModel owner;
    /**
     *  卡片价格
     */
    protected int price = 100;

    protected Card(PlayerModel owner)
    {
        this.owner = owner;
    }

    public abstract int getCardID();

    public String getName()
    {
        return name;
    }

    public PlayerModel getOwner()
    {
        return owner;
    }

    public void setOwner(PlayerModel owner)
    {
        this.owner = owner;
    }

    public int getPrice()
    {
        return price;
    }

    public String getChName()
    {
        return chName;
    }

    public String getInfo()
    {
        return info;
    }
}
