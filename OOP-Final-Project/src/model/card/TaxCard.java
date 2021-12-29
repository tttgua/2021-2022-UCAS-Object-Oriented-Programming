package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * 查税卡
 */
public class TaxCard extends Card
{

    public TaxCard(PlayerModel owner)
    {
        super(owner);
        this.name = "TaxCard";
        this.chName = "查税卡";
        this.price = 50;
        this.info = "向一名玩家收取其现金10%税额";
    }

    public int getCardID()
    {
        return GameConstant.CARD_TAX;
    }

}
