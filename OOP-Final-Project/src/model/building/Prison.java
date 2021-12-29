package model.building;

import model.PlayerModel;

import constant.GameConstant;

/**
 * 监狱 玩家到这里可以入狱或者发生其他事件
 */
public class Prison extends Building {

	private PlayerModel player;

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.name = "监狱";
	}

	public int getEvent() {
		return GameConstant.PRISON_EVENT;
	} //当前地图并不可能发生
	public int passEvent()
	{
		return 0;
	}
}
