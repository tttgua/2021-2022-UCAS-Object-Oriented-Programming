package model.card;


import constant.GameConstant;
import model.PlayerModel;

/**
 * �ݺ���
 */
public class TrapCard extends Card {

	public TrapCard(PlayerModel owner) {
		super(owner);
		this.name = "TrapCard";
		this.chName = "�ݺ���";
		this.price = 40;
		this.info = "�ݺ�һ����ң�ʹ����������";
	}

	public int getCardID() {
		return GameConstant.CARD_TRAP;
	}

}
