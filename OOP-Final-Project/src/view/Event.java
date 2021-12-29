package view;

import java.awt.Graphics;

import model.EventModel;

/**
 * 事件显示面板
 */
public class Event extends Panel
{

    private EventModel events;

    protected Event(int x, int y, int w, int h, EventModel events)
    {
        super(x, y, w, h);
        this.events = events;
    }

    public void paint(Graphics g)
    {
        this.paintEvent(g);
    }

    private void paintEvent(Graphics g)
    {
        if (events.getStartTick() < events.getCurTick() && events.getNextTick() >= events.getCurTick())
        {
            g.drawImage(events.getImg(), events.getImgPosition().getPosX(), events.getImgPosition().getPosY(),
                    events.getImgPosition().getPosX() + events.getImg().getWidth(null),
                    events.getImgPosition().getPosY() + events.getImg().getHeight(null), 0, 0,
                    events.getImg().getWidth(null), events.getImg().getHeight(null), null);

        }
    }

    public void initPanel()
    {
    }

}
