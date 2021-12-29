package model.building;

import constant.GameConstant;

/**
 * ���� ��1~5����
 */
public class House extends Building
{
    private int price;
    private String[] nameString = {"�յ�", "ƽ��", "�õ�", "�̳�", "����", "Ħ���¥"};

    public House(int posX, int posY)
    {
        super(posX, posY);
        this.maxLevel = 5;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    @Override
    public int getPrice()
    {
        if (this.level == 0)
        {
            this.price = 500;
        } else
        {
            this.price = 1000 * this.level;
        }
        return price;
    }

    /**
     * ��ȡ�����ܼ�ֵ�����ؿ������������
     */
    @Override
    public int getAllPrice()
    {
        int price = 0;
        for (int i = 0; i <= level; i++)
        {
            price += 500 * i;
        }
        return price;
    }

    /**
     * ��·��
     */
    @Override
    public int getRevenue()
    {
        this.revenue = 0;
        for (int i = 1; i <= this.level; i++)
        {
            this.revenue += 300 * i;
        }
        return this.revenue;
    }

    @Override
    public String getName()
    {
        return this.nameString[this.level];
    }

    /**
     * �����һ����������
     */
    @Override
    public String getUpName()
    {
        if (this.level >= this.nameString.length - 1)
        {
            return "null";
        }
        return this.nameString[this.level + 1];
    }

    public int getEvent()
    {
        return GameConstant.HOUSE_EVENT;
    }
    public int passEvent()
    {
        return 0;
    }
}
