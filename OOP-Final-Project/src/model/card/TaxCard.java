package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * ��˰��
 */
public class TaxCard extends Card
{

    public TaxCard(PlayerModel owner)
    {
        super(owner);
        this.name = "TaxCard";
        this.chName = "��˰��";
        this.price = 50;
        this.info = "��һ�������ȡ���ֽ�10%˰��";
    }

    public int getCardID()
    {
        return GameConstant.CARD_TAX;
    }

}
