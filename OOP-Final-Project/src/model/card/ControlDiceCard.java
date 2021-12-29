package model.card;

import constant.GameConstant;
import model.PlayerModel;

/**
 * 控骰卡
 */
public class ControlDiceCard extends Card
{
    public ControlDiceCard(PlayerModel owner)
    {
        super(owner);
        this.name = "ControlDiceCard";
        this.chName = "控骰卡";
        this.price = 50;
        this.info = "控制下一次的掷点数";
    }

    @Override
    public int getCardID()
    {
        return GameConstant.CARD_CONTROLDICE;
    }
}
