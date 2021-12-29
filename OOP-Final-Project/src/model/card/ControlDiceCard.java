package model.card;

import constant.GameConstant;
import model.PlayerModel;

/**
 * ������
 */
public class ControlDiceCard extends Card
{
    public ControlDiceCard(PlayerModel owner)
    {
        super(owner);
        this.name = "ControlDiceCard";
        this.chName = "������";
        this.price = 50;
        this.info = "������һ�ε�������";
    }

    @Override
    public int getCardID()
    {
        return GameConstant.CARD_CONTROLDICE;
    }
}
