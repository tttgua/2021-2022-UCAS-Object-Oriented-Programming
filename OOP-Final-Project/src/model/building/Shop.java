package model.building;

import java.util.ArrayList;
import java.util.List;

import constant.GameConstant;
import model.card.*;

/**
 * �̵�ģ��
 *
 *
 */
public class Shop extends Building
{

    /**
     * �����Ʒ����
     */
    public static int MAXITEM = 3;
    private List<Card> cards = new ArrayList<>(MAXITEM);


    public Shop(int posX, int posY)
    {
        super(posX, posY);
        this.name = "�̵�";
    }

    @Override
    public int getEvent()
    {
        return GameConstant.SHOP_EVENT;
    }
    public int passEvent()
    {
        return 0;
    }

    /**
     * Ϊ�̵�Ļ��ܴ���������Ʒ
     */
    public void createCards()
    {
        this.cards = new ArrayList<>(MAXITEM);
        // ������������card
        for (int i = 0; i < MAXITEM; i++)
        {
            int random = (int) (Math.random() * 4);
            switch (random)
            {
                case 0:
                    cards.add(new TaxCard(null));
                    break;
                case 1:
                    cards.add(new ControlDiceCard(null));
                    break;
                case 2:
                    cards.add(new PurchaseCard(null));
                    break;
                case 3:
                    cards.add(new TrapCard(null));
                    break;
            }
        }
    }

    public List<Card> getCards()
    {
        return cards;
    }
}
