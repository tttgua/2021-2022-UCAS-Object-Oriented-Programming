package model.building;

import model.PlayerModel;

import constant.GameConstant;

/**
 * ���� ��ҵ���������������߷��������¼�
 */
public class Prison extends Building {

	private PlayerModel player;

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.name = "����";
	}

	public int getEvent() {
		return GameConstant.PRISON_EVENT;
	} //��ǰ��ͼ�������ܷ���
	public int passEvent()
	{
		return 0;
	}
}
