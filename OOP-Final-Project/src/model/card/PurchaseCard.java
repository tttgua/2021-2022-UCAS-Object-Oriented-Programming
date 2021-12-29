package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * 购地卡
 */
public class PurchaseCard extends Card {

	public PurchaseCard(PlayerModel owner) {
		super(owner);
		this.name = "PurchaseCard";
		this.chName = "购地卡";
		this.price = 25;
		this.info = "强制购买你当前所在的土地";
	}

	public int getCardID() {
		return GameConstant.CARD_PURCHASE;
	}

}
