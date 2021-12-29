package model.building;

import constant.GameConstant;

/**
 * 点券位
 */
public class Ticket extends Building {

	private int ticketPoint;


	public Ticket(int posX, int posY, int ticketPoint) {
		super(posX, posY);
		this.name = ticketPoint + "点券位";
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
