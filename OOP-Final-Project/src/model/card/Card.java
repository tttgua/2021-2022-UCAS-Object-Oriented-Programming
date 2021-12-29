package model.card;

import model.PlayerModel;
/**
 *  ��Ƭģ�ͳ�����
 */
public abstract class Card
{
    /**
     *  ��ƬӢ������
     */
    protected String name;
    /**
     *  ��Ƭ��������
     */
    protected String chName;
    /**
     *  ��Ƭ���ܼ��
     */
    protected String info;
    /**
     *  ӵ����
     */
    protected PlayerModel owner;
    /**
     *  ��Ƭ�۸�
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
