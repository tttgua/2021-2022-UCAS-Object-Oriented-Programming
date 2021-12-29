package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * 陷害卡
 */
public class TrapCard extends Card {

	public TrapCard(PlayerModel owner) {
		super(owner);
		this.name = "TrapCard";
		this.chName = "陷害卡";
		this.price = 40;
		this.info = "陷害一名玩家，使其入狱三天";
	}

	public int getCardID() {
		return GameConstant.CARD_TRAP;
	}

}
