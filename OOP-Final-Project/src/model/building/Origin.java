package model.building;

import constant.GameConstant;

/**
 * ���
 */
public class Origin extends Building {
	/**
	 * ͨ��ʱ�Ľ���
	 */
	private int passReward;
	/**
	 * ͣ��ʱ�Ľ���
	 */
	private int reward;


	public Origin(int posX, int posY) {
		super(posX, posY);
		this.name = "���";
		this.reward = 2000;
		this.passReward = 1000;
	}

	
	public int getPassReward() {
		return passReward;
	}
	public int getReward() {
		return reward;
	}

	public int getEvent() {
		return GameConstant.ORIGIN_EVENT;
	}
	public int passEvent() {
		return GameConstant.ORIGIN_PASS_EVENT;
	}
}
