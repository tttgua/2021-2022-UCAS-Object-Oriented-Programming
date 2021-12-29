package model.building;

import constant.GameConstant;

/**
 * 起点
 */
public class Origin extends Building {
	/**
	 * 通过时的奖励
	 */
	private int passReward;
	/**
	 * 停留时的奖励
	 */
	private int reward;


	public Origin(int posX, int posY) {
		super(posX, posY);
		this.name = "起点";
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
