package model.building;

import constant.GameConstant;

/**
 * ��ȯλ
 */
public class Ticket extends Building {

	private int ticketPoint;


	public Ticket(int posX, int posY, int ticketPoint) {
		super(posX, posY);
		this.name = ticketPoint + "��ȯλ";
		this.ticketPoint = ticketPoint;
	}

	public int getTicketPoint() {
		return ticketPoint;
	}

	@Override
	public int getEvent() {
		return GameConstant.TICKET_EVENT;
	}
	public int passEvent()
	{
		return 0;
	}
}
