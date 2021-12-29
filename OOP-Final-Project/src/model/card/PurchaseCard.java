package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * ���ؿ�
 */
public class PurchaseCard extends Card {

	public PurchaseCard(PlayerModel owner) {
		super(owner);
		this.name = "PurchaseCard";
		this.chName = "���ؿ�";
		this.price = 25;
		this.info = "ǿ�ƹ����㵱ǰ���ڵ�����";
	}

	public int getCardID() {
		return GameConstant.CARD_PURCHASE;
	}

}
